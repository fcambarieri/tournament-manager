package tournament.manager

class CombatCategory {

	String name
	int minAge
	int maxAge
	Sex sex
	
    static constraints = {
		name(size:2..60)
		minAge(nullable:false)
		maxAge(nullable:false)
    }
	
	static belongsTo = [tournament:Tournament]
	
	static hasMany = [weights : CombatWeight]
}
