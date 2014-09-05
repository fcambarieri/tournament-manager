package tournament.manager

enum Sex {
	
	MALE("male"),
	FEMALE("female"),
	BOTH("both")
	
	String name
	
	private Sex(String n) {
		this.name = n
	}
	
	public String toString() {
		return name
	}
	
	public Sex findSex(String theName) {
		if (theName == null) {
			return null
		}
		for(Sex s : Sex.values()) {
			if (s.name.toLowerCase() == theName.toLowerCase()) {
				return s
			}
		}
		return null
	}
}
