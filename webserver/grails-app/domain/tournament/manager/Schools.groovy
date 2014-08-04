package tournament.manager

class Schools {

	String name
	String phoneNumber
	String email
	Date dateCreated
	Date dateUpdated
	
    static constraints = {
		name(blank:false, size:2..30)
		phoneNumber(nullable:true)
		email(nullable:true)
    }
	
	static belongsTo = [owner:User]
}
