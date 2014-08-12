package tournament.manager

import grails.transaction.Transactional

@Transactional
class TournamentService {

    def serviceMethod() {

    }
	
	def listTournamentByUser(params) {
		return Tournament.list(params)
	}
}
