package tournament.manager

class Brackets {

	String status
	String externalId
	String name
	String url
	
	
    static constraints = {
		status(nullable:true)
		combatWeight(nullable:true)
		formCategory(nullable:true)
		name(nullable:false, blank:false, size: 2..256)
		url(nullable:true, blank:false, size: 2..256)
    }
	
	static belongsTo = [
		tournament:Tournament,
		belt:Belt,
		combatWeight : CombatWeight,
		formCategory : FormCategory
	]
}
