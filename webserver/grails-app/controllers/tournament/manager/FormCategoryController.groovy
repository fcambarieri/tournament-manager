package tournament.manager


import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class FormCategoryController extends AbstractController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //respond FormCategory.list(params), model:[formCategoryInstanceCount: FormCategory.count()]

        def formCategoryInstanceList = FormCategory.list(params)
//        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/list", formCategoryInstanceList:formCategoryInstanceList]
//        render (view:"/home/forms/form", model:model)
		listFormView([formCategoryInstanceList:formCategoryInstanceList,formCategoryInstanceCount:formCategoryInstanceList.size()], params)
    }

    def show(FormCategory formCategoryInstance) {
        //respond formCategoryInstance
//        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/show", formCategoryInstance:formCategoryInstance, instance:formCategoryInstance]
//        render (view:"/home/forms/form", model:model)
		formView(["action":"save",tournaments:getTournaments(), tournamentSelected: getTournamentSelected()], params)
    }

    def create() {
        //respond new FormCategory(params)
//		def formCategoryInstance = new FormCategory(params)
//        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/create", formCategoryInstance:formCategoryInstance, instance:formCategoryInstance]
//        render (view:"/home/forms/form", model:model)
		formView(["action":"save",tournaments:getTournaments(), tournamentSelected: getTournamentSelected()], params)
    }

    @Transactional
    def save(FormCategory formCategoryInstance) {
        if (formCategoryInstance == null) {
            notFound()
            return
        }

        if (formCategoryInstance.hasErrors()) {
//            respond formCategoryInstance.errors, view:'create'
			formView([instance:formCategoryInstance,tournaments:getTournaments()], params)
            return
        }

        if (!formCategoryInstance.save (flush:true)) {
			formView([instance:formCategoryInstance,tournaments:getTournaments()], params)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), formCategoryInstance.id])
		
		println "forward: ${request.forwardURI}"
		
		redirect view:"index", controller:"formCategory"
			
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), formCategoryInstance.id])
//                redirect formCategoryInstance
//            }
//            '*' { respond formCategoryInstance, [status: CREATED] }
//        }
    }

    def edit(FormCategory formCategoryInstance) {
//        respond formCategoryInstance
		def model = editModel(formCategoryInstance)
		model.tournaments=getTournaments()
		editFormView(model, params)
    }

    @Transactional
    def update() {
		
		FormCategory formCategoryInstance = FormCategory.get(params.id)
		
        if (formCategoryInstance == null) {
            notFound()
            return
        }
		
		formCategoryInstance.minAge = Long.parseLong(params.minAge)
		formCategoryInstance.maxAge = Long.parseLong(params.maxAge)
		

        if (formCategoryInstance.hasErrors()) {
//            respond formCategoryInstance.errors, view:'edit'
			def model = editModel(formCategoryInstance)
			model.tournaments=getTournaments()
			editFormView(model, params)
            return
        }

        if (!formCategoryInstance.save (flush:true)) {
			def model = editModel(formCategoryInstance)
			model.tournaments=getTournaments()
			editFormView(model, params)
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
		
		println "forward: ${request.forwardURI} "
		
		redirect view:"index", controller:"formCategory"

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
//                redirect formCategoryInstance
//            }
//            '*'{ respond formCategoryInstance, [status: OK] }
//        }
    }

    @Transactional
    def delete(FormCategory formCategoryInstance) {

        if (formCategoryInstance == null) {
            notFound()
            return
        }

//        if (!formCategoryInstance.delete (flush:true)) {
//			
//		}
		
		if (!formCategoryInstance.delete (flush:true)) {
			flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
		} else {
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
		}
		
		
//		flash.message = message(code: 'default.deleted.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
		redirect view:"index",controller:"formCategory"

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
    }

    protected void notFound() {
		flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), params.id])
		redirect action: "index", method: "GET"
//        request.withFormat {
//            form multipartForm {
//                flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
    }

	public Object getDomainInstance(Object params) {
		return new FormCategory(params)
	}
}
