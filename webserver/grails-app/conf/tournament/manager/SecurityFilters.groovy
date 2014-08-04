package tournament.manager

class SecurityFilters {

	static nonAuthenticatedActions = [
		//[controller:'authentication', action:'*'],
		[controller:'login', action:'*'],
		[controller:'registration', action:'*']
		
	]
	
	def filters = {
		accessFilter(controller:'*', action:'*') {
			before = {
				boolean needsAuth = !nonAuthenticatedActions.find {
					(it.controller == controllerName) && ((it.action == '*')
					  || (it.action == actionName))
				}
			if (needsAuth) {
//			  return applicationContext.authenticationService.filterRequest(
//					 request, response,
//					 "${request.contextPath}/login/loginForm" )
					response.sendRedirect("${request.contextPath}/login/loginForm")
					return false
				} else return true
					
			}
		}
	}
	
	
//    def filters = {
//        all(controller:'*', action:'*') {
//            before = {
//
//            }
//            after = { Map model ->
//
//            }
//            afterView = { Exception e ->
//
//            }
//        }
//    }
}
