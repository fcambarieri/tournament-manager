package tournament.manager

class LoginController extends AbstractController {

	UserService userService
	
	def loginForm () {
		if (isUserLogin()) {
			redirect(view: "account")
		}
	}
	
	def doLogin(LoginCommand cmd) {
		if (cmd.hasErrors()) {
			redirect(action: 'loginForm', model:cmd.errors)
			return
		}
		def user = userService.login(cmd.email, cmd.password)
		if (user) {
			session.user=user
			redirect(view: "account")
		}
		render(view:"loginForm", model:["loginError":true]) 
	}
	
	def logout() {
		
	}
	
	def verificationCode () {
		
	}
	
	def forgetMyPassword() {
	
	}
	
	def resetPassword() {
		
	}
	
}

class LoginCommand {
	
	String email
	String password
	
	static constraints = {
		email(nullable:false, blank:false, size: 5..30)
		password(nullable:false, blank:false, size: 10..60)
	}
}