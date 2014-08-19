package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CombatWeightController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CombatWeight.list(params), model:[combatWeightInstanceCount: CombatWeight.count()]
    }

    def show(CombatWeight combatWeightInstance) {
        respond combatWeightInstance
    }

    def create() {
        respond new CombatWeight(params)
    }

    @Transactional
    def save(CombatWeight combatWeightInstance) {
        if (combatWeightInstance == null) {
            notFound()
            return
        }

        if (combatWeightInstance.hasErrors()) {
            respond combatWeightInstance.errors, view:'create'
            return
        }

        combatWeightInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'combatWeightInstance.label', default: 'CombatWeight'), combatWeightInstance.id])
                redirect combatWeightInstance
            }
            '*' { respond combatWeightInstance, [status: CREATED] }
        }
    }

    def edit(CombatWeight combatWeightInstance) {
        respond combatWeightInstance
    }

    @Transactional
    def update(CombatWeight combatWeightInstance) {
        if (combatWeightInstance == null) {
            notFound()
            return
        }

        if (combatWeightInstance.hasErrors()) {
            respond combatWeightInstance.errors, view:'edit'
            return
        }

        combatWeightInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CombatWeight.label', default: 'CombatWeight'), combatWeightInstance.id])
                redirect combatWeightInstance
            }
            '*'{ respond combatWeightInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CombatWeight combatWeightInstance) {

        if (combatWeightInstance == null) {
            notFound()
            return
        }

        combatWeightInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CombatWeight.label', default: 'CombatWeight'), combatWeightInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'combatWeightInstance.label', default: 'CombatWeight'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
