package tournament.manager


import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class FormCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //respond FormCategory.list(params), model:[formCategoryInstanceCount: FormCategory.count()]

        def formCategoryInstanceList = FormCategory.list(params)
        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/list", formCategoryInstanceList:formCategoryInstanceList]
        render (view:"/home/forms/form", model:model)
    }

    def show(FormCategory formCategoryInstance) {
        //respond formCategoryInstance
        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/show", formCategoryInstance:formCategoryInstance]
        render (view:"/home/forms/form", model:model)
    }

    def create() {
        //respond new FormCategory(params)
        def model = [ "controller":"Poomse Category","action": params.action, "title":"Poomse","templateName":"/formCategory/create", formCategoryInstance: new FormCategory(params)]
        render (view:"/home/forms/form", model:model)
    }

    @Transactional
    def save(FormCategory formCategoryInstance) {
        if (formCategoryInstance == null) {
            notFound()
            return
        }

        if (formCategoryInstance.hasErrors()) {
            respond formCategoryInstance.errors, view:'create'
            return
        }

        formCategoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), formCategoryInstance.id])
                redirect formCategoryInstance
            }
            '*' { respond formCategoryInstance, [status: CREATED] }
        }
    }

    def edit(FormCategory formCategoryInstance) {
        respond formCategoryInstance
    }

    @Transactional
    def update(FormCategory formCategoryInstance) {
        if (formCategoryInstance == null) {
            notFound()
            return
        }

        if (formCategoryInstance.hasErrors()) {
            respond formCategoryInstance.errors, view:'edit'
            return
        }

        formCategoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
                redirect formCategoryInstance
            }
            '*'{ respond formCategoryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FormCategory formCategoryInstance) {

        if (formCategoryInstance == null) {
            notFound()
            return
        }

        formCategoryInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FormCategory.label', default: 'FormCategory'), formCategoryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'formCategoryInstance.label', default: 'FormCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
