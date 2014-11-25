package tournament.manager

import grails.transaction.Transactional

@Transactional
class BracketService {

	def challongeAPIService

	def countParticipant (Long tournamentId) {
		def participantsCount = Participants.createCriteria().count { eq("tournament.id",tournamentId) }
		return participantsCount
	}

	def potencialBracket(Long tournamentId) {

		def participants = Participants.createCriteria().list {  eq("tournament.id",tournamentId)  }

		//		def poomseParticipants = Participants.createCriteria().list {
		//			eq("tournament.id",tournamentId)
		//		}

		def combatBrackets = [:]
		def poomseBrackets = [:]

		for(Participants p : participants) {
			if (p.combatWeight != null) {
				BracketKey key = new BracketKey('belt':p.belt, 'competition':p.combatWeight, "bracket" : findBracketCombat(p.belt, p.combatWeight))
				if (!combatBrackets.containsKey(key)) {
					combatBrackets.put(key, [])
				}
				combatBrackets.get(key).add(p)
			}
			if (p.formCategory != null) {
				BracketKey key = new BracketKey('belt':p.belt, 'competition':p.formCategory)
				if (!poomseBrackets.containsKey(key)) {
					poomseBrackets.put(key, [])
				}
				poomseBrackets.get(key).add(p)
			}
		}

		return [combatParticipants:combatBrackets, poomseParticipants:poomseBrackets]
	}

	def findBracketPoomse(Belt belt, FormCategory formCategory) {
		def criteria = Brackets.createCriteria()
		def bracket = criteria.get {
			eq("belt",belt)
			eq("formCategory", formCategory)
		}
		return bracket
	}

	def findBracketCombat(Belt belt, CombatWeight combatWeight) {
		def criteria = Brackets.createCriteria()
		def bracket =  criteria.get {
			eq("belt",belt)
			eq("combatWeight", combatWeight)
		}
		return bracket
	}

	def listParticipantsByBeltAndCombatWeight(Belt belt, CombatWeight combatWeight) {
		def participants = Participants.createCriteria().list {
			eq("belt",belt)
			eq("combatWeight", combatWeight)
		}
		return participants
	}

	def listParticipantsByBeltAndFormatCategory(Belt belt, FormCategory formCategory) {
		def participants = Participants.createCriteria().list {
			eq("belt",belt)
			eq("formCategory", formCategory)
		}
		return participants
	}

	def createCombatBracket(Belt belt, CombatWeight combatWeight) {
		Brackets bracket = findBracketCombat(belt, combatWeight)
		if (bracket != null) {
			return bracket
		}

		def participants = listParticipantsByBeltAndCombatWeight(belt, combatWeight)

		if (participants.size() == 0) {
			throw new RuntimeException("No existe competidores para $belt - $combatWeight")
		}

		bracket = new Brackets()
		Tournament tournament = belt.tournament
		bracket.name = "${combatWeight.combatCategory.name} ${belt.description} ${combatWeight.toString()}"
		bracket.belt = belt
		bracket.combatWeight = combatWeight
		bracket.tournament = tournament


		CombatCategory category = combatWeight.combatCategory

		def challongeTournament = [
			"tournament":[
				"name":	bracket.name,
				"tournament_type" : "single elimination",
				"description" : "${tournament.name} Sexo[${combatWeight.combatCategory.sex.trimName}] ${belt.description} ${combatWeight.combatCategory.name} ${combatWeight.toString()}",
				"url": "${category.name}_${category.sex.trimName}_${belt.description}_${combatWeight.minWeight}_${combatWeight.maxWeight}_kg".toString().replaceAll("-", "_").replaceAll(" ", "_").toLowerCase()
			]
		]


//		def data = challongeAPIService.createTournament(challongeTournament)
//		println "Tournament: $data"
//		bracket.externalId = "${data.tournament.id}"
//		bracket.url = "${data.tournament.live_image_url}"
//
//		def chParticipants = []
//
//		participants.each { p ->
//			chParticipants << ["name": "${p.lastName}, ${p.name}".toString(), "misc":"${p.id}".toString()]
//		}
//
//		def map = ["participants":chParticipants]
//
//		challongeAPIService.fullFillParticipants(data.tournament.id, map)
//
//		bracket.save(flush:true, failOnError:true)
//
//		challongeAPIService.startTournament(data.tournament.id)

		return createBracket(challongeTournament, participants, bracket)
	}


	def createPoomseBracket(Belt belt, FormCategory formCategory) {
		Brackets bracket = findBracketPoomse(belt, combatWeight)
		if (bracket != null) {
			return bracket
		}

		def participants = listParticipantsByBeltAndFormatCategory(belt, formCategory)

		if (participants.size() == 0) {
			throw new RuntimeException("No existe competidores para $belt - $formCategory")
		}

		bracket = new Brackets()
		Tournament tournament = belt.tournament
		bracket.name = "${formCategory.name} ${belt.description}"
		bracket.belt = belt
		bracket.formCategory = formCategory
		bracket.tournament = tournament

		def challongeTournament = [
			"tournament":[
				"name":	bracket.name,
				"tournament_type" : "single elimination",
				"description" : "${tournament.name} ${belt.description} ${formCategory.name}",
				"url": "${formCategory.name}_${belt.description}".toString().replaceAll("-", "_").replaceAll(" ", "_").toLowerCase()
			]
		]
		
		
		return createBracket(challongeTournament, participants, bracket)

	}

	def createBracket(challongeTournament, participants, bracket) {

		def data = challongeAPIService.createTournament(challongeTournament)
		bracket.externalId = "${data.tournament.id}"
		bracket.url = "${data.tournament.live_image_url}"

		def chParticipants = []

		participants.each { p ->
			chParticipants << ["name": "${p.lastName}, ${p.name}".toString(), "misc":"${p.id}".toString()]
		}

		def map = ["participants":chParticipants]

		challongeAPIService.fullFillParticipants(data.tournament.id, map)

		if (!bracket.save(flush:true)) {
			challongeAPIService.deleteTournament(data.tournament.id)
			return [message:"cound not create bracket", errors: bracket.errors]
		}

		challongeAPIService.startTournament(data.tournament.id)

		return bracket
	}


}
