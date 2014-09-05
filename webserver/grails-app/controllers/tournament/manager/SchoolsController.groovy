package tournament.manager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured

@Transactional(readOnly = true)
@Secured("hasRole('ROLE_USER')")
class SchoolsController extends AbstractController{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		params.owner = getCurrentUser()
		def schoolsInstanceList = Schools.list(params)
        //respond Schools.list(params), model:[schoolsInstanceCount: Schools.count()]
		def model = [schoolsInstanceList:schoolsInstanceList, "schoolsInstanceCount":schoolsInstanceList.size()]
		listFormView(model, params)
    }

    def show(Schools schoolsInstance) {
//        respond schoolsInstance
		formView(["action":"save"], params)
    }

    def create() {
//        respond new Schools(params)
		formView(["action":"save"], params)
    }

    @Transactional
    def save() {
        /*if (schoolsInstance == null) {
            notFound()
            return
        }*/
        Schools schoolsInstance = new Schools(params)
		schoolsInstance.owner = getCurrentUser()
        if (schoolsInstance.hasErrors()) {
//            respond schoolsInstance.errors, view:'create'
			formView(["action":"save","instance":schoolsInstance], params)
            return
        }

        if (!schoolsInstance.save (flush:true)) {
			formView(["action":"save","instance":schoolsInstance], params)
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), schoolsInstance.id])
		redirect view:"index", controller:"schools"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), schoolsInstance.id])
//                redirect schoolsInstance
//            }
//            '*' { respond schoolsInstance, [status: CREATED] }
//        }
    }

    def edit() {
		Schools schoolsInstance = findSchool(params.id)
//        respond schoolsInstance
		editFormView(editModel(schoolsInstance), params)
    }

    @Transactional
    def update() {
//		Schools schoolsInstance = Schools.get(params.id)
		Schools schoolsInstance = findSchool(params.id)
		if (schoolsInstance == null) {
            notFound()
            return
        }
        schoolsInstance.name = params.name
		schoolsInstance.phoneNumber = params.phoneNumber
		schoolsInstance.email = params.email
		schoolsInstance.owner = getCurrentUser()

        if (schoolsInstance.hasErrors()) {
//            respond schoolsInstance.errors, view:'edit'
			editFormView(editModel(schoolsInstance), params)
            return
        }

        if (!schoolsInstance.save (flush:true)) {
			editFormView(editModel(schoolsInstance), params)
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
		redirect view:"index", controller:"schools"

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
//                redirect schoolsInstance
//            }
//            '*'{ respond schoolsInstance, [status: OK] }
//        }
    }

    @Transactional
    def delete() {
		Schools schoolsInstance = findSchool(params.id)
        if (schoolsInstance == null) {
            notFound()
            return
        }
		
		if (!schoolsInstance.delete (flush:true)) {
			flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
		} else {
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
		}

		redirect view:"index", controller:"schools"
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Schools.label', default: 'Schools'), schoolsInstance.id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
    }

    protected void notFound() {
		flash.errorMessage = message(code: 'default.not.found.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), params.id])
		redirect view:"index", controller:"schools"
	}
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'schoolsInstance.label', default: 'Schools'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
//    }

	public Object getDomainInstance(Object params) {
		return new Schools(params);
	}
	
	def findSchool(String id) {
		def criteria = Schools.createCriteria()
		def school = criteria.get{
			eq("id", new Long(id))
			eq("owner", getCurrentUser())
		}
		return school
	}
}
