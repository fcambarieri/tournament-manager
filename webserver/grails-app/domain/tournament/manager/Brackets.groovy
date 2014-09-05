package tournament.manager

class Brackets {

	String status
	String externalId
	String name
	
	
    static constraints = {
		combatWeight(nullable:true)
		formCategory(nullable:true)
    }
	
	static belongsTo = [
		tournament:Tournament,
		belt:Belt,
		combatWeight : CombatWeight,
		formCategory : FormCategory
	]
}
