package tournament.manager

abstract class AbstractController {

    def sanitizeParams() { 
		def sanitizedParams = [:]
		params.each { k , v ->
			sanitizedParams.put(k, v.encodeAsHTML())
		}
		//sanitizedParams.putAll(params)
		return sanitizedParams
	}
	
	def getCurrentUser() {
		return session.user
	}
	
	def isUserLogin() {
		return getCurrentUser() != null
	}
}
