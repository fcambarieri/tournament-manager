package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FormCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FormCategory.list(params), model:[formCategoryInstanceCount: FormCategory.count()]
    }

    def show(FormCategory formCategoryInstance) {
        respond formCategoryInstance
    }

    def create() {
        respond new FormCategory(params)
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
