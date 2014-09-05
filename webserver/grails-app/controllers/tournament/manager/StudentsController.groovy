package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class StudentsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Students.list(params), model:[studentsInstanceCount: Students.count()]
    }

    def show(Students studentsInstance) {
        respond studentsInstance
    }

    def create() {
        respond new Students(params)
    }

    @Transactional
    def save(Students studentsInstance) {
        if (studentsInstance == null) {
            notFound()
            return
        }

        if (studentsInstance.hasErrors()) {
            respond studentsInstance.errors, view:'create'
            return
        }

        studentsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'studentsInstance.label', default: 'Students'), studentsInstance.id])
                redirect studentsInstance
            }
            '*' { respond studentsInstance, [status: CREATED] }
        }
    }

    def edit(Students studentsInstance) {
        respond studentsInstance
    }

    @Transactional
    def update(Students studentsInstance) {
        if (studentsInstance == null) {
            notFound()
            return
        }

        if (studentsInstance.hasErrors()) {
            respond studentsInstance.errors, view:'edit'
            return
        }

        studentsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Students.label', default: 'Students'), studentsInstance.id])
                redirect studentsInstance
            }
            '*'{ respond studentsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Students studentsInstance) {

        if (studentsInstance == null) {
            notFound()
            return
        }

        studentsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Students.label', default: 'Students'), studentsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'studentsInstance.label', default: 'Students'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
