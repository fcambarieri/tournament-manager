package tournament.manager

class CombatCategory {

	String name
	int minAge
	int maxAge
	Sex sex
	
    static constraints = {
		name(size:2..60)
		minAge(nullable:false, validator: { val, obj ->
			if(!val.isNumber()) {
				return "Invalid Data Type"
			}
			if (maxAge != null && val >= maxAge) {
				return "min age is gratter than max age "
			}
		})
		maxAge(nullable:false, validator: { val, obj ->
			if(!val.isNumber()) {
				return "Invalid Data Type"
			}
			if (minAge != null && val <= minAge) {
				return "max age is lower than min age "
			}
		})
		
    }
	
	static belongsTo = [tournament:Tournament]
}
