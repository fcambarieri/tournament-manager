package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class ParticipantsController extends AbstractController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond Participants.list(params), model:[participantsInstanceCount: Participants.count()]
		def participantsInstanceList = Participants.list(params)
		listFormView([participantsInstanceList:participantsInstanceList,participantsInstanceCount:participantsInstanceList.size()], params)
    }
	
	def participantModel(params) {
		def model = [tournaments : getTournaments(), schools:tournamentService.listSchools()]
		
		if (params.tournamentId) {
			Long tournamentId = new Long(params.tournamentId)
			model.tournamentSelected = model.tournaments.find {it.id == tournamentId}
			model.belts = tournamentService.listBeltByTournaments(tournamentId)
			model.combatCategories = tournamentService.listCombatCategoryByTournament(tournamentId)
			model.formCategories = FormCategory.where{tournament.id==tournamentId}
		}
		if (params.combatCategoryId) {
			Long combatCategoryId = new Long(params.combatCategoryId)
			model.combatWeights = tournamentService.listCombatWeightCategory(combatCategoryId)
		}
		
		return model
	}

    def show(Participants participantsInstance) {
        respond participantsInstance
    }

    def create() {
//        respond new Participants(params)
		def model = participantModel(params)
		model.action = "save"
		model.scriptName ="/participants/participantsJs"
		formView(model, params)
		
//		def entity = assertModel(model) 
//		if (entity == null) {
//			
//			return
//		}
//		flash.errorMessage = message(code: 'default.entity.tournament.errorConfiguration', args: [message(code: "default.entity.${entity}.title".toString())])
//		redirect action:"settings", controller : "tournament"
    }
	
	def assertModel(model) {
		if (model.tournaments.size() == 0) {
			return "tournament"
		} else if (model.belts.size() == 0) {
			return "belt"
		} else if (model.combatCategories.size() == 0) {
			return "combatCategory"
		} else if (model.schools.size() == 0) {
			return "schools"
		}
		return null
	}

    @Transactional
    def save(Participants participantsInstance) {		
        if (participantsInstance == null) {
            notFound()
            return
        }
		
//		if ("none".equals(params.formCategory.id) || params.formCategory.id == null) {
//			participantsInstance.formCategory.discard()
//			participantsInstance.formCategory = null
//		}
//		if ("none".equals(params.combatWeight.id) || params.combatWeight.id == null) {
//			participantsInstance.combatWeight.discard()
//			participantsInstance.combatWeight = null
//		}

        if (participantsInstance.hasErrors()) {
//            respond participantsInstance.errors, view:'create'
			def model = participantModel(params)
			model.instance = participantsInstance
			model.scriptName ="/participants/participantsJs"
			formView(model, params)
            return
        }

        if (!participantsInstance.save (flush:true)) {
			def model = participantModel(params)
			model.instance = participantsInstance
			model.scriptName ="/participants/participantsJs"
			formView(model, params)
			return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), participantsInstance.id])
		redirect view:"index"
		
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), participantsInstance.id])
//                redirect participantsInstance
//            }
//            '*' { respond participantsInstance, [status: CREATED] }
//        }
    }

    def edit() {
//        respond participantsInstance
		Participants participantsInstance = Participants.get(params.id)
		params.tournamentId = participantsInstance.tournament.id
		params.combatCategoryId = participantsInstance.combatWeight?.combatCategory?.id
		def model = editModel(participantsInstance)
		model.putAll(participantModel(params))
		model.scriptName ="/participants/participantsJs"
		editFormView(model, params)
    }

    @Transactional
    def update(Participants participantsInstance) {
        if (participantsInstance == null) {
            notFound()
            return
        }

        if (participantsInstance.hasErrors()) {
//            respond participantsInstance.errors, view:'edit'
			def model = editModel(participantsInstance)
			model.putAll(participantModel())
			model.scriptName ="/participants/participantsJs"
			editFormView(model, params)
            return
        }

        if (!participantsInstance.save (flush:true)) {
			def model = editModel(participantsInstance)
			model.putAll(participantModel())
			model.scriptName ="/participants/participantsJs"
			editFormView(model, params)
			return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
		redirect view:"index", controller:"participants"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
//                redirect participantsInstance
//            }
//            '*'{ respond participantsInstance, [status: OK] }
//        }
    }

    @Transactional
    def delete(Participants participantsInstance) {

        if (participantsInstance == null) {
            notFound()
            return
        }

        if (!participantsInstance.delete (flush:true)) {
			flash.errorMessage = message(code: 'default.deleted.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
			
        } else {
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
		}
//		redirect action:"index", method:"GET"
		
        request.withFormat {
            form multipartForm {
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
		flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), params.id])
		redirect action: "index", method: "GET"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
    }

	public Object getDomainInstance(Object params) {
		return new Participants(params)
	}
}
