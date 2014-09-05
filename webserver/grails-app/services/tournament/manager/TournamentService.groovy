package tournament.manager

import grails.transaction.Transactional

@Transactional
class TournamentService extends AbstractService{

	
	def listTournamentByOwner(owner) {
		return Tournament.findAllByOwner(owner)
	}
	
	def listTournamentByUser(params) {
		return Tournament.list(params)
	}
	
	def listBeltByTournaments(Long tournamentId) {
		def criteria = Belt.createCriteria()
		def belts = criteria.list {
			eq("tournament.id", tournamentId)
		}
		return belts
	}
	
	def listCombatCategoryByTournament(Long tournamentId) {
		def criteria = CombatCategory.createCriteria()
		def categories = criteria.list {
			eq("tournament.id", tournamentId)
		}
		return categories
	}
	
	def listCombatWeightCategory(Long categoryId) {
		def criteria = CombatWeight.createCriteria()
		def categories = criteria.list {
			eq("combatCategory.id", categoryId)
		}
		return categories
	}
	
	def listSchools() {
		def criteria = Schools.createCriteria()
		def schools = criteria.list {
			eq("owner", getCurrentUser())
		}
		return schools
	}
	
	
	/*public void setTournamentSelected(Long tournamentId) {
		def criteria = Tournament.createCriteria()
		this.tournamentSelected = criteria.get {
			eq("owner", getCurrentUser())
			eq("id", tournamentId)
		}
	}
	
	public void setTournamentSelected(Tournament tournamentSelected) {
		if (!tournamentSelected.owner.equals(getCurrentUser())) {
			throw new RuntimeException("Forbiden!") 
		}
		this.tournamentSelected = tournamentSelected;
	}*/
}
