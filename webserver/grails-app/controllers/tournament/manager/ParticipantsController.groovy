package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ParticipantsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Participants.list(params), model:[participantsInstanceCount: Participants.count()]
    }

    def show(Participants participantsInstance) {
        respond participantsInstance
    }

    def create() {
        respond new Participants(params)
    }

    @Transactional
    def save(Participants participantsInstance) {
        if (participantsInstance == null) {
            notFound()
            return
        }

        if (participantsInstance.hasErrors()) {
            respond participantsInstance.errors, view:'create'
            return
        }

        participantsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), participantsInstance.id])
                redirect participantsInstance
            }
            '*' { respond participantsInstance, [status: CREATED] }
        }
    }

    def edit(Participants participantsInstance) {
        respond participantsInstance
    }

    @Transactional
    def update(Participants participantsInstance) {
        if (participantsInstance == null) {
            notFound()
            return
        }

        if (participantsInstance.hasErrors()) {
            respond participantsInstance.errors, view:'edit'
            return
        }

        participantsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
                redirect participantsInstance
            }
            '*'{ respond participantsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Participants participantsInstance) {

        if (participantsInstance == null) {
            notFound()
            return
        }

        participantsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Participants.label', default: 'Participants'), participantsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'participantsInstance.label', default: 'Participants'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
