package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
import grails.converters.JSON

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class BeltController extends AbstractController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Belt.list(params), model:[beltInstanceCount: Belt.count()]
    }

    def show(Belt beltInstance) {
        respond beltInstance
    }

    def create() {
        respond new Belt(params)
    }

    @Transactional
    def save(Belt beltInstance) {
        if (beltInstance == null) {
            notFound()
            return
        }

        if (beltInstance.hasErrors()) {
            respond beltInstance.errors, view:'create'
            return
        }

        beltInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'beltInstance.label', default: 'Belt'), beltInstance.id])
                redirect beltInstance
            }
            '*' { respond beltInstance, [status: CREATED] }
        }
    }

    def ajaxSave () {
        Belt belt = new Belt()
        if (params.id) {
            belt = Belt.get(params.id)
        }
        belt.description = params.description
        belt.tournament = Tournament.get(params.tournamentId)
        def map
        if (belt.save(flush:true)) {
            map = ["status":201,"entity":["id":belt.id, "description":belt.description, "tournament.id":belt.tournament.id]]
        } else {
            map = ["status":400,"errors":belt.errors]
        }
        
        def json = map as JSON   
        render json
    }

    def edit(Belt beltInstance) {
        println "belt: $beltInstance"
        render ( view:'/home/forms/editModal', model:[beltInstance:beltInstance,"title":message(code: 'beltInstance.label', default: 'Belt'),"templateName":"/belt/form"])
    }

    @Transactional
    def update(Belt beltInstance) {
        if (beltInstance == null) {
            notFound()
            return
        }

        if (beltInstance.hasErrors()) {
            respond beltInstance.errors, view:'edit'
            return
        }

        beltInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Belt.label', default: 'Belt'), beltInstance.id])
                redirect beltInstance
            }
            '*'{ respond beltInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Belt beltInstance) {

        if (beltInstance == null) {
            notFound()
            return
        }

        beltInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Belt.label', default: 'Belt'), beltInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'beltInstance.label', default: 'Belt'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
