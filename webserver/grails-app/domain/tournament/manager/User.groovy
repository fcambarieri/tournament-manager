package tournament.manager

class User {

	String email
	String firstName
	String lastName
	String password
	String status
	String verificationCode
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		email(nullable:false, blank:false, size: 5..30, unique: true)
		firstName(nullable:false, blank:false, size: 2..30)
		lastName(nullable:false, blank:false, size: 2..30)
		password(nullable:false, blank:false, size: 10..60)
		status(nullable:false, blank:false, size: 3..60)
		verificationCode(nullable:false, blank:false, size: 10..60)
    }
	
}
