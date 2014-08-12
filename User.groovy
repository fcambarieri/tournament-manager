package tournament.manager

import javax.persistence.Transient;

class User {

	String email
	String firstName
	String lastName
	String password
	
	
	@Transient String confirmPassword
	String status
	String verificationCode
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		email(nullable:false, blank:false, size: 5..30, unique: true)
		firstName(nullable:false, blank:false, size: 2..30)
		lastName(nullable:false, blank:false, size: 2..30)
		password(nullable:false, blank:false, size: 10..60)
		confirmPassword(nullable:false, blank:false, size: 10..60, validate : {val, obj ->
			if (confirmPassword != password) {
				return "password is not equal"
			}
		})
		status(nullable:false, blank:false, size: 3..60)
		verificationCode(nullable:false, blank:false, size: 10..60)
    }
	
}
