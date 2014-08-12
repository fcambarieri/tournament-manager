package tournament.manager

import tournament.manager.auth.User;

class Tournament {

	String name
	String status //inscription_open, inscription_close, open
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(nullable:false, blank:false, size: 2..60)
		status(nullable:false, blank:false, size: 2..30)
    }
	
	static belongsTo = [owner: User]
}
