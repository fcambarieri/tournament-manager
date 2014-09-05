package tournament.manager

import grails.transaction.Transactional

@Transactional
abstract class AbstractService {

	def springSecurityService
	
	def getCurrentUser() {
		return springSecurityService.currentUser
	}
}
