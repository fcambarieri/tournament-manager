package tournament.manager

class CombatWeight {

	int minWeight
	int maxWeight
	
    static constraints = {
		minWeight(nullable:false, validator: { val, obj -> 
			if(!val.isNumber()) {
				return "Invalid Data Type"
			} 
			if (maxWeight != null && val >= maxWeight) {
				return "minWeight is gratter than maxWeight "
			}
		})
		maxWeight(nullable:false, validator: { val, obj ->
			if(!val.isNumber()) {
				return "Invalid Data Type"
			}
			if (minWeight != null && val <= minWeight) {
				return "maxWeight is lower than minWeight "
			}
		})
    }
	
	static belongsTo = [combatCategory : CombatCategory]
}
