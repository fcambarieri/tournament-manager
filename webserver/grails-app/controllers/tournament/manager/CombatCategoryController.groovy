package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class CombatCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //params.owner = getCurrentUser()
        //respond CombatCategory.list(params), model:[combatCategoryInstanceCount: CombatCategory.count()]
        def combatCategoryInstanceList = CombatCategory.list(params)
        def model = [ "controller":"Combat Category","action": params.action, "title":"combat","templateName":"/combatCategory/list", combatCategoryInstanceList:combatCategoryInstanceList]

        render (view:"/home/forms/form", model:model)
    }

    def show(CombatCategory combatCategoryInstance) {
        render (view:"/home/forms/form", model:[combatCategoryInstance:combatCategoryInstance, "controller":"Combat Category","action": params.action, "title":"combat","templateName":"/combatCategory/show"])
    }

    def create() {
        respond new CombatCategory(params)
    }

    @Transactional
    def save(CombatCategory combatCategoryInstance) {
        if (combatCategoryInstance == null) {
            notFound()
            return
        }

        if (combatCategoryInstance.hasErrors()) {
            respond combatCategoryInstance.errors, view:'create'
            return
        }

        combatCategoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'combatCategoryInstance.label', default: 'CombatCategory'), combatCategoryInstance.id])
                redirect combatCategoryInstance
            }
            '*' { respond combatCategoryInstance, [status: CREATED] }
        }
    }

    def edit(CombatCategory combatCategoryInstance) {
        //respond combatCategoryInstance
        render (view:"/home/forms/form", model:[combatCategoryInstance:combatCategoryInstance, "controller":"Combat Category","action": params.edit, "title":"combat","templateName":"/combatCategory/edit"])
    }

    @Transactional
    def update(CombatCategory combatCategoryInstance) {
        if (combatCategoryInstance == null) {
            notFound()
            return
        }

        if (combatCategoryInstance.hasErrors()) {
            respond combatCategoryInstance.errors, view:'edit'
            return
        }

        combatCategoryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
                redirect combatCategoryInstance
            }
            '*'{ respond combatCategoryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CombatCategory combatCategoryInstance) {

        if (combatCategoryInstance == null) {
            notFound()
            return
        }

        combatCategoryInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CombatCategory.label', default: 'CombatCategory'), combatCategoryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
}
