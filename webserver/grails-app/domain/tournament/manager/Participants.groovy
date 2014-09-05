package tournament.manager

import java.util.Date;

class Participants {

	String name
	String lastName
	String combatStatus
	String formStatus
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		name(blank:false, size:2..30)
		lastName(blank:false, size:2..30)
		combatStatus(nullable:true)
		formStatus(nullable:true)
		formCategory(nullable: true)
		//combatCategory(nullable: true)
		combatWeight(nullable: true)
    }
	
	static belongsTo = [
//			student:Students, 
			school:Schools,
			tournament:Tournament,
			belt:Belt,
			formCategory:FormCategory,
			//combatCategory:CombatCategory,
			combatWeight: CombatWeight,
	]
}
