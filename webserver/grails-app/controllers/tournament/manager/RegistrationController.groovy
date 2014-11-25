package tournament.manager

import com.grailsrocks.emailconfirmation.EmailConfirmationService;

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured;
import tournament.manager.auth.Role;
import tournament.manager.auth.User
import tournament.manager.auth.UserRole;
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@Secured("permitAll")
class RegistrationController {

	EmailConfirmationService emailConfirmationService
	def springSecurityService
	def userDetailsService
	def authenticationManager
	
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
					autoLogin(user.username, user.password)
					sendConfirmationEmail(user)
					render (view:"congrats",model:[userInstance:user])
				}
		}
	}
	
	private void autoLogin(username, password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (userDetails, password, userDetails.getAuthorities());
//		authenticationManager.authenticate(auth);
		if(auth.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
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
		
		User  user= User.findByVerificationCode(params.id)
		if(!user){
			return;
		}

		user.status = "active"
		//userInstance.enabled=true;
		if (!user.save(flush: true)) {
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

