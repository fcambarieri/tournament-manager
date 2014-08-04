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
}
