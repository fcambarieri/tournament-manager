package tournament.manager

class CombatWeight {

	int minWeight
	int maxWeight
	
    static constraints = {
		minWeight(nullable:false)
		maxWeight(nullable:false)
    }
	
	static belongsTo = [combatCategory : CombatCategory]
	
	public String toString() {
		return "$minWeight - $maxWeight kg"
	}
}
