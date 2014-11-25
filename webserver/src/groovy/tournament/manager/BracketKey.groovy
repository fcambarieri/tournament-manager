package tournament.manager

class BracketKey {

	def belt
	def competition
	def bracket

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((belt == null) ? 0 : belt.hashCode());
		result = prime * result
				+ ((competition == null) ? 0 : competition.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BracketKey other = (BracketKey) obj;
		if (belt == null) {
			if (other.belt != null)
				return false;
		} else if (!belt.equals(other.belt))
			return false;
		if (competition == null) {
			if (other.competition != null)
				return false;
		} else if (!competition.equals(other.competition))
			return false;
		return true;
	}	
	
}
