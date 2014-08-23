package tournament.manager

import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROLE_USER')")
abstract class AbstractController {

	def springSecurityService
	
    def sanitizeParams() { 
		def sanitizedParams = [:]
		params.each { k , v ->
			sanitizedParams.put(k, v.encodeAsHTML())
		}
		//sanitizedParams.putAll(params)
		return sanitizedParams
	}
	
	def getCurrentUser() {
		return springSecurityService.currentUser
	}


}
