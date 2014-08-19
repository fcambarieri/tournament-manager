package tournament.manager

class TournamentController extends AbstractController{

	TournamentService tournamentService
	
    def index() { 
		int max = params.max ? Integer.parseInt(params.max) : 10
		params.max = Math.min(max ?: 10, 100)
		params.user = getCurrentUser()
		def tournaments = tournamentService.listTournamentByUser(params)
		render (view:"index", model:[tournaments: tournaments, total: tournaments.size()])
	}
	
//	def list(Integer max) {
//		params.max = Math.min(max ?: 10, 100)
//		params.user = getCurrentUser()
//		def tournaments = tournamentService.listTournamentByUser(params)
//		render (view:"index", model:[tournaments: tournaments, total: tournaments.size()])
//	}
	
	def create() {
		[tournamentInstance: new Tournament(params)]
	}
	
	def save () {
		def tournamentInstance = new Tournament(params)
		tournamentInstance.owner = getCurrentUser()
		if (!tournamentInstance.save(flush: true)) {
			render(view: "create", model: [tournamentInstance: tournamentInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'tournament.label', default: 'Tournament'), tournamentInstance.id])
		//render(view: "index", id: tournamentInstance.id)
		redirect (view:"index")
	}
	
	def settings () {
		
	}
}
