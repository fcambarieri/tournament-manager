package tournament.manager

import grails.transaction.Transactional

@Transactional
class BracketService {

	def potencialBracket(Long tournamentId) {

		def participants = Participants.createCriteria().list { 
			eq("tournament.id",tournamentId) 
		}

//		def poomseParticipants = Participants.createCriteria().list { 
//			eq("tournament.id",tournamentId) 
//		}
		
		def combatBrackets = [:]
		def poomseBrackets = [:]
		
		for(Participants p : participants) {
			if (p.combatWeight != null) {
				BracketKey key = new BracketKey('belt':p.belt, 'competition':p.combatWeight)
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
		
		return [combatBrackets:combatBrackets, poomseBrackets:poomseBrackets]
	}
}
