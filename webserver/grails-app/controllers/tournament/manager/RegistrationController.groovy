package tournament.manager

class RegistrationController {

	def index() {
		[model: new User()]
	}

	def save() {
		def existUser = User.findByEmail(params.email)
		if (existUser) {
			flash.message = "Saving error"
			redirect (action:"index")
		} else {
			User user = new User()
			user.email = params.email
			user.password = params.password
			user.firstName = params.firstName
			user.lastName = params.lastName
			user.status = "pending_confirmation"
			user.verificationCode = UUID.randomUUID().toString()
			if (!user.save(flush:true)){
				flash.message = "Saving error"
				render (view:"index", model:[userInstance:user])
			} else {
				session.user = user
				redirect (view:"congrats")
			}
		}
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

