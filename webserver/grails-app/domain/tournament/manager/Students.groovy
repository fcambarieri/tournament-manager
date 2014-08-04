package tournament.manager

class Students {

	String name
	String lastName
	String docNumber
	Sex sex
	Date birthDate
	String picture
	
    static constraints = {
		docNumber(nullable:true)
		picture(nullable:true)
    }
	
	static belongsTo = [school:Schools]
}
