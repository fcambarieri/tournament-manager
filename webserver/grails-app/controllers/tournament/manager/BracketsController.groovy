package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class BracketsController extends AbstractController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def bracketService
	
	def potencialBrackets () {
		def tournaments = getTournaments()
		
		def model = [
			tournaments:tournaments,
			poomseParticipants:[:],
			combatParticipants:[:],
			title:message(code: 'default.entity.brackets', default: 'Brackets'),
			scriptName:"/brackets/bracketJs"
		]
		
		if (params.tournamentId != null && params.tournamentId != "null") {
			Long tournamentId = new Long (params.tournamentId)
			model.tournamentSelected = Tournament.get(tournamentId)
			def brackets = bracketService.potencialBracket(tournamentId)
			model.putAll(brackets)
			model.countParticipant = bracketService.countParticipant(tournamentId)
		}
		
		if ("${params.sort}" == "count") {
			model.combatParticipants = model.combatParticipants.sort{ a, b -> 
				if ("${params.order}" == "asc") {
					a.value.size() <=> b.value.size()
				} else {
					b.value.size() <=> a.value.size()
				}
			}
			
			
			model.poomseParticipants = model.poomseParticipants.sort{ a, b ->
				
				if ("${params.order}" == "asc") {
					return a.value.size() <=> b.value.size()
				}  else {
					return b.value.size() <=> a.value.size()
				}
				
			}
		}
		
		
		model.combatParticipantsCount = model.combatParticipants.size() 
		model.poomseParticipantsCount = model.poomseParticipants.size() 
		
		blankView(model, "/brackets/settings", params)
	}
	
	def createBracket() {
		Long beltId = new Long(params.beltId)
		String type = params.type
		Long categoryId = new Long(params.categoryId)
		Brackets bracket 
		Belt belt = Belt.get(beltId)
		if (belt == null) {
			flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'beltInstance.label', default: 'Belt'), beltId])
			redirect action: "potencialBrackets", method: "GET"
			return
		}
		if (type == "combat") {
			CombatWeight category = CombatWeight.get(categoryId)
			if (category == null) {
				flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'combatWeightInstance.label', default: 'CombatWeight'), categoryId])
				redirect action: "potencialBrackets", method: "GET"
				return
			} 
			bracket = bracketService.createCombatBracket( belt, category)
		} else if (type == "poomse"){
			FormCategory category = FormCategory.get(categoryId)
			if (category == null) {
				flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'formCategoryInstance.label', default: 'Poomse Category'), categoryId])
				redirect action: "potencialBrackets", method: "GET"
				return
			}
			bracket = bracketService.createPoomseBracket(belt, category)
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'bracketsInstance.label', default: 'Brackets'), bracket.id])
		redirect action: "potencialBrackets", method: "GET"
	}
	
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Brackets.list(params), model:[bracketsInstanceCount: Brackets.count()]
    }

    def show(Brackets bracketsInstance) {
        respond bracketsInstance
    }

    def create() {
        respond new Brackets(params)
    }

    @Transactional
    def save(Brackets bracketsInstance) {
        if (bracketsInstance == null) {
            notFound()
            return
        }

        if (bracketsInstance.hasErrors()) {
            respond bracketsInstance.errors, view:'create'
            return
        }

        bracketsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bracketsInstance.label', default: 'Brackets'), bracketsInstance.id])
                redirect bracketsInstance
            }
            '*' { respond bracketsInstance, [status: CREATED] }
        }
    }

    def edit(Brackets bracketsInstance) {
        respond bracketsInstance
    }

    @Transactional
    def update(Brackets bracketsInstance) {
        if (bracketsInstance == null) {
            notFound()
            return
        }

        if (bracketsInstance.hasErrors()) {
            respond bracketsInstance.errors, view:'edit'
            return
        }

        bracketsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Brackets.label', default: 'Brackets'), bracketsInstance.id])
                redirect bracketsInstance
            }
            '*'{ respond bracketsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Brackets bracketsInstance) {

        if (bracketsInstance == null) {
            notFound()
            return
        }

        bracketsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Brackets.label', default: 'Brackets'), bracketsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
		flash.message = message(code: 'default.not.found.message', args: [message(code: 'bracketsInstance.label', default: 'Brackets'), params.id])
		redirect action: "index", method: "GET"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bracketsInstance.label', default: 'Brackets'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
    }

	public Object getDomainInstance(Object params) {
		return new Brackets(params);
	}
}
