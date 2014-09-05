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
			scriptName:"/brackets/bracketJs"
		]
		
		if (params.tournamentId) {
			Long tournamentId = new Long (params.tournamentId)
		
			def brackets = bracketService.potencialBracket(tournamentId)
			model.putAll(brackets)
//			def combatParticipants = Participants.createCriteria().list {
//				eq("tournament.id",tournamentId)
//			}
//			
//			def poomseParticipants = Participants.createCriteria().list {
//				eq("tournament.id",tournamentId)
//			}
			
//			model.poomseParticipants = poomseParticipants
//			model.combatParticipants = combatParticipants
		}
		
		//listFormView([tournaments:tournaments,poomseParticipants:poomseParticipants, combatParticipants:combatParticipants], params)
		blankView(model, "/brackets/settings", params)
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
