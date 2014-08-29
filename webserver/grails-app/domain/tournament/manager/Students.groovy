package tournament.manager

class Students {

	String name
	String lastName
	String docNumber
	Sex sex
	Date birthDate
	String picture
	
    static constraints = {
		name(blank:false, size:2..30)
		lastName(blank:false, size:2..30)
		docNumber(nullable:true)
		picture(nullable:true)
    }
}
