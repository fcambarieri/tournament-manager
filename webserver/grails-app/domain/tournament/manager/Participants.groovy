package tournament.manager

class Participants {

	String combatStatus
	String formStatus
	Date dateCreated
	Date lastUpdated
	
    static constraints = {
		combatStatus(nullable:true)
		formStatus(nullable:true)
		formCategory(nullable: true)
		combatCategory(nullable: true)
		combatWeight(nullable: true)
    }
	
	static belongsTo = [
			student:Students, 
			tournament:Tournament,
			belt:Belt,
			formCategory:FormCategory,
			combatCategory:CombatCategory,
			combatWeight: CombatWeight
		]
}
