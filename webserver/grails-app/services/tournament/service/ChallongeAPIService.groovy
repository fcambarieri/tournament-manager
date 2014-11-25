package tournament.service

import grails.converters.JSON;
import grails.transaction.Transactional


//import groovyx.net.http.*
//import groovyx.net.http.*
//import static groovyx.net.http.ContentType.*
//import static groovyx.net.http.Method.*


import org.springframework.beans.factory.InitializingBean;

import tournament.manager.rest.RestClient;

class ChallongeAPIService implements InitializingBean {

	static transactional = false
	
//	private static final String TOKEN = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
//	
//	static final String URL = "https://challonge.com/api/v1"
	
	String token = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
	String baseUrl = "https://challonge.com/api/v1"
	RestClient restClient;
	
	String tournamentsUri ="/tournaments"
   
	public void afterPropertiesSet() throws Exception {
		restClient = new RestClient(baseUrl)
	}
	
	def createTournament(tournament) {
		def json = tournament as JSON
		def uri = appendToken(tournamentsUri)
		def resp = restClient.post(uri, json.toString())
		return resp.data	
	}
	
	def getTournament(Long chTournamentId) {
		def uri = appendToken(tournamentsUri.concat("/$chTournamentId".toString()))
		def resp = restClient.get(uri)
		return resp.data
	}
	
	def deleteTournament(Long chTournamentId) {
		def resp = restClient.delete("$tournamentsUri/$chTournamentId".toString())
		return resp.status == 200
	}
	
	def startTournament(Long chTournamentId) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/start")
		def resp = restClient.post(uri, "{}")
		return resp.data
	}
	
	def resetTournament(Long chTournamentId) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/reset")
		def resp = restClient.post(uri, "{}")
		return resp.data
	}
	
	def finalizeTournament(Long chTournamentId) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/finalize")
		def resp = restClient.post(uri, "{}")
		return resp.data
	}
	
	/*
	 * {
		    "participants": [
		        {
		            "name": "andrea",
		            "misc": "bpp-123"
		        },
		        {
		            "name": "yoli",
		            "misc": "480o0"
		        }
		    ]
		}
	 * */
	def fullFillParticipants(Long chTournamentId, participants) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/participants/bulk_add")
		def json = participants as JSON
		def resp = restClient.post(uri, json.toString())
		return resp.data
	}
	
	def createParticipant(Long chTournamentId, participant) {
		///:tournament/participants.format
		def uri = appendToken("$tournamentsUri/$chTournamentId/participants")
		def json = participant as JSON
		def resp = restClient.post(uri, json.toString())
		return resp.data
	}
	
	def deleteParticipant(Long chTournamentId, Long participantId) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/participants/$participantId")
		def resp = restClient.delete(uri)
		return resp.status == 200
	}
	
	def randomizeTournament(Long chTournamentId) {
		def uri = appendToken("$tournamentsUri/$chTournamentId/participants/randomize")
		def resp = restClient.post(uri, "{}")
		return resp.data
	}
	
	def appendToken(String uri) {
		return "${uri}.json?api_key=$token".toString()
	}
}
