package tournament.manager

enum TournamentStatus {
	
	REGISTRATION("registration"),
	STARTED("started"),
	END("end")
	
	String name
	
	private TournamentStatus(String n) {
		this.name = n
	}
	
	public String toString() {
		return name
	}
}
