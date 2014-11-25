package tournament.manager

enum Sex {
	
	MALE("male", "M"),
	FEMALE("female","F"),
	BOTH("both","U")
	
	String name
	String trimName
	
	private Sex(String n, String m) {
		this.name = n
		this.trimName = m
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
	
	public String toTrimName(Sex sex) {
		return sex.trimName
	}
}
