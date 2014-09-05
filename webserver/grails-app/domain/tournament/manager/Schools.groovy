package tournament.manager

import tournament.manager.auth.User;

class Schools {

	String name
	String phoneNumber
	String email
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, size:2..30)
		phoneNumber(nullable:true)
		email(nullable:true)
    }
	
	static belongsTo = [owner:User]

	static hasMany = [participants : Participants]

	static mapping = {
		autoTimestamp true
	}
	
	public String toString() {
		return "$name"
	}
}
