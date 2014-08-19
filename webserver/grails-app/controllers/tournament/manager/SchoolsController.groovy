package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SchoolsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Schools.list(params), model:[schoolsInstanceCount: Schools.count()]
    }

    def show(Schools schoolsInstance) {
        respond schoolsInstance
    }

    def create() {
        respond new Schools(params)
    }

    @Transactional
    def save(Schools schoolsInstance) {
        if (schoolsInstance == null) {
            notFound()
            return
        }

        if (schoolsInstance.hasErrors()) {
            respond schoolsInstance.errors, view:'create'
            return
        }

        schoolsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), schoolsInstance.id])
                redirect schoolsInstance
            }
            '*' { respond schoolsInstance, [status: CREATED] }
        }
    }

    def edit(Schools schoolsInstance) {
        respond schoolsInstance
    }

    @Transactional
    def update(Schools schoolsInstance) {
        if (schoolsInstance == null) {
            notFound()
            return
        }

        if (schoolsInstance.hasErrors()) {
            respond schoolsInstance.errors, view:'edit'
            return
        }

        schoolsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
                redirect schoolsInstance
            }
            '*'{ respond schoolsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Schools schoolsInstance) {

        if (schoolsInstance == null) {
            notFound()
            return
        }

        schoolsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
