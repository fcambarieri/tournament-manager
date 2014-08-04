package tournament.manager

class RegistrationController {

    def index() { 
		[model: new User()]
	}
	
	def save(RegistrationCommand cmd) {
		if (cmd.hasErrors()) {
			redirect (action:"index", model:cmd)
		}
		User user = new User()
		user.email = cmd.email
		user.password = cmd.password
		user.firstName = cmd.firstName
		user.lastName = cmd.lastName
		user.status = "pending_confirmation"
		user.verificationCode = UUID.randomUUID().toString()
		if (!user.save(flush:true)){
			redirect (action:"index", model:cmd)
		}
		redirect (view:"congrats")
	}
	
	def congrats () {
		
	}
}

class RegistrationCommand {
	
	String email
	String firstName
	String lastName
	String password
	String confirmPassword
	
	
	static constraints = {
		email(nullable:false, blank:false, size: 5..30)
		firstName(nullable:false, blank:false, size: 2..30)
		lastName(nullable:false, blank:false, size: 2..30)
		password(nullable:false, blank:false, size: 10..60)
		confirmPassword(nullable:false, blank:false, size: 10..60, validator : { val, obj ->
			if (password != confirmPassword) {
				return "password are not equals"
			}
		})
	}
}

