package tournament.manager

class FormCategory {

	String name
	int minAge
	int maxAge
	
    static constraints = {
		name(size:2..60)
		minAge(nullable:false)
		maxAge(nullable:false)
    }
	
	static belongsTo = [tournament:Tournament]
}
