package tournament.manager



import static org.springframework.http.HttpStatus.*

import javax.swing.text.html.ListView;

import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class CombatCategoryController extends AbstractController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //params.owner = getCurrentUser()
        //respond CombatCategory.list(params), model:[combatCategoryInstanceCount: CombatCategory.count()]
        def combatCategoryInstanceList = CombatCategory.list(params)
		def tournaments = tournamentService.listTournamentByUser(params)
        def model = [ "controller":"Combat Category","action": params.action,"templateName":"/combatCategory/list", combatCategoryInstanceList:combatCategoryInstanceList]
//
//        render (view:"/home/forms/form", model:model)
		listFormView(model, params)
    }

    def show(CombatCategory combatCategoryInstance) {
        //render (view:"/home/forms/form", model:[combatCategoryInstance:combatCategoryInstance,instance:combatCategoryInstance, "controller":"Combat Category","action": params.action, "title":"combat","templateName":"/combatCategory/show"])
//		formView([instance:combatCategoryInstance, "controller":"Combat Category","action": "save", "templateName":"/combatCategory/show"], params)
		formView(["action":"save",tournaments:getTournaments(), tournamentSelected: getTournamentSelected()], params)
    }

    def create() {
        //respond new CombatCategory(params)
//		formView(["action":"save"], params)
		formView(["action":"save",tournaments:getTournaments(), tournamentSelected: getTournamentSelected()], params)
    }
	
	def getDomainInstance(params) {
		return new CombatCategory(params)
	}
	
    @Transactional
    def save(CombatCategory combatCategoryInstance) {
        if (combatCategoryInstance == null) {
            notFound()
            return
        }

        if (combatCategoryInstance.hasErrors()) {
            //respond combatCategoryInstance.errors, view:'create'
			formView([instance:combatCategoryInstance,tournaments:getTournaments()], params)
            return
        }

        if (!combatCategoryInstance.save(flush:true)) {
			formView([instance:combatCategoryInstance,tournaments:getTournaments()], params)
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'combatCategoryInstance.label', default: 'CombatCategory'), combatCategoryInstance.id])
		redirect view:"index", controller:"combatCategory"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'combatCategoryInstance.label', default: 'CombatCategory'), combatCategoryInstance.id])
//                redirect combatCategoryInstance
//            }
//            '*' { respond combatCategoryInstance, [status: CREATED] }
//        }
    }

    def edit(CombatCategory combatCategoryInstance) {
        //respond combatCategoryInstance
        //render (view:"/home/forms/form", model:[combatCategoryInstance:combatCategoryInstance,instance:combatCategoryInstance, "controller":"Combat Category","action": params.edit, "title":"combat","templateName":"/combatCategory/edit"])
//		def model=[instance:combatCategoryInstance,
//			"title":"Combat category",
//			"action":"update",
//			"templateName":"/combatCategory/form"]
		def model = editModel(combatCategoryInstance)
		model.tournaments=getTournaments()
		editFormView(model, params)
    }
	
//	private def editModel(instance) {
//		return [instance:instance,
//			"action":"update",
//			"templateName":"/combatCategory/form"]
//	}

    @Transactional
    def update() {
		CombatCategory combatCategoryInstance = CombatCategory.get(params.id)
        if (combatCategoryInstance == null) {
            notFound()
            return
        }
		def myParmas = params
		def sex = params.sex
		def tournament = params.tournament
		combatCategoryInstance.name = params.name
		combatCategoryInstance.minAge = Integer.parseInt(params.minAge)
		combatCategoryInstance.maxAge = Integer.parseInt(params.maxAge)
		combatCategoryInstance.sex = params.sex//Sex.findSex(params.sex)
		combatCategoryInstance.tournament = Tournament.get(params.tournament.id)

        if (combatCategoryInstance.hasErrors()) {
            //respond combatCategoryInstance.errors, view:'edit'
			//formView([instance:combatCategoryInstance], params)
//			editFormView(editModel(combatCategoryInstance), params)
			def model = editModel(combatCategoryInstance)
			model.tournaments=getTournaments()
			editFormView(model, params)
            return
        }

        if (!combatCategoryInstance.save (flush:true)) {
			//formView([instance:combatCategoryInstance], params)
//			editFormView(editModel(combatCategoryInstance), params)
			def model = editModel(combatCategoryInstance)
			model.tournaments=getTournaments()
			editFormView(model, params)
		}

		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'combatCategoryInstance.label', default: 'CombatCategory'), combatCategoryInstance.id])
		//listFormView([instance:combatCategoryInstance], params)
		redirect view:"index", controller:"combatCategory"
		
//        request.withFormat {
//            form multipartForm {
//                flash.errorMessage = message(code: 'default.updated.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
//                redirect combatCategoryInstance
//            }
//            '*'{ respond combatCategoryInstance, [status: OK] }
//        }
    }

    @Transactional
    def delete(CombatCategory combatCategoryInstance) {

        if (combatCategoryInstance == null) {
            notFound()
            return
        }

        if (!combatCategoryInstance.delete (flush:true)) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
		} else {
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
		}
		
		
		redirect view:"index", controller:"combatCategory"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'combatCategoryInstance.label', default: 'CombatCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
//	def getTitle() {
//		return message(code: 'default.button.create.label', default: 'Create')
//	};
}
