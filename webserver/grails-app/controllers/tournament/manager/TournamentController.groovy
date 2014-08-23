package tournament.manager

import grails.converters.JSON
import grails.util.Holder;
import grails.util.Holders;

import org.springframework.security.access.annotation.Secured

//class TournamentController extends AbstractController{
@Secured("hasRole('ROLE_USER')")
class TournamentController {
//	def grailsApplication
	TournamentService tournamentService
	def springSecurityService
	
    def index() { 
		int max = params.max ? Integer.parseInt(params.max) : 10
		params.max = Math.min(max ?: 10, 100)
		params.user = getCurrentUser()
		def tournaments = tournamentService.listTournamentByUser(params)
		render (view:"index", model:[tournaments: tournaments, total: tournaments.size()])
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		params.user = getCurrentUser()
		def tournaments = tournamentService.listTournamentByUser(params)
		render (view:"index", model:[tournaments: tournaments, total: tournaments.size()])
	}
	
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
		def config =  Holders.config
		String uri = "${config.grails.serverURL}${request.getSession().getServletContext().getContextPath()}/tournament/index?ok=true".toString()
		println "uri:$uri"
//		redirect (view:"index", controller:"tournament", model:[ok:true])
		redirect (uri:uri)
	}
	
	def ajaxSave() {
		def tournamentInstance = new Tournament(params)
		tournamentInstance.owner = getCurrentUser()
		if (!tournamentInstance.save(flush: true)) {
			//render(view: "create", model: [tournamentInstance: tournamentInstance])
			def errors = tournamentInstance.errors
			def json = [status: "400", errors : errors] as JSON 
			render json
		} else {
			def map = ["id":tournamentInstance, 
				"name":tournamentInstance.name,
				"status":tournamentInstance.status.toString(), 
				"date_created":tournamentInstance.dateCreated,
				"last_updated":tournamentInstance.lastUpdated]
			def json = map as JSON
			render json
		}
	}
	
	private getCurrentUser() {
		boolean isLoggedIn = springSecurityService.isLoggedIn()
		return isLoggedIn ? springSecurityService.loadCurrentUser() : null
	}
	def settings () {
		
	}
}
