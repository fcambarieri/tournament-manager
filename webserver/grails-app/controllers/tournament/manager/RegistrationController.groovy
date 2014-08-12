package tournament.manager

import com.grailsrocks.emailconfirmation.EmailConfirmationService;

import grails.plugin.springsecurity.SpringSecurityService
import tournament.manager.auth.Role;
import tournament.manager.auth.User
import tournament.manager.auth.UserRole;

class RegistrationController {

	EmailConfirmationService emailConfirmationService
	SpringSecurityService springSecurityService
	
	def index() {
		[model: new User()]
	}

	def save() {
		def existUser = User.findByEmail(params.email)
		if (existUser) {
			flash.message = "Email ${params.email} already register"
			render (controller:"home",view:"index",model:[userInstance:new User(params)])
		} else {
				User user = fullFieldUser(params)
				if (!user.save(flush:true)){
					flash.message = "Saving error"
					render (view:"index", model:[userInstance:user])
				} else {
					UserRole.create(user, Role.findByAuthority("ROLE_USER"), true)
					springSecurityService.reauthenticate(user.username)
					sendConfirmationEmail(user)
					render (view:"congrats",model:[userInstance:user])
				}
		}
	}
	
	def sendConfirmationEmail(User user) {
		// Send a confirmation with custom email template and model
		emailConfirmationService.sendConfirmation(
			model:[account:user, code:"${user.verificationCode}"],
			view:'/mailtemplates/confirm_signup',
			to:user.email,
			subject:"Please confirm!")
	}
	
	def fullFieldUser(params) {
		User user = new User()
		user.username = params.username
		user.email = params.email
		user.password = params.password
		user.confirmPassword = params.confirmPassword
		user.firstName = params.firstName
		user.lastName = params.lastName
		user.status = "pending_confirmation"
		user.verificationCode = UUID.randomUUID().toString()
		user
	}

	def confirm () {
		
		User user = User.findByConfirmCode(id)
		if(!userInstance){
			return;
		}

		user.status = "active"
		//userInstance.enabled=true;
		if (!userInstance.save(flush: true)) {
			render(view: "congrats", model: [userInstance:user, message: 'Problem activating account.'])
			return
		}
		render(view: "congrats", model: [userInstance:user, message: 'You account is successfully activated.'])
	}
	
	def congrats () {

	}
}

//class RegistrationCommand {
//
//	String email
//	String firstName
//	String lastName
//	String password
//	String confirmPassword
//
//
//	static constraints = {
//		email(nullable:false, blank:false, size: 5..30)
//		firstName(nullable:false, blank:false, size: 2..30)
//		lastName(nullable:false, blank:false, size: 2..30)
//		password(nullable:false, blank:false, size: 10..60)
//		confirmPassword(nullable:false, blank:false, size: 10..60, validator : { val, obj ->
//			if (password != confirmPassword) {
//				return "password are not equals"
//			}
//		})
//	}
//}

