package tournament.service

import grails.test.mixin.TestFor
import spock.lang.Specification
import tournament.manager.rest.RestClient;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ChallongeAPIService)
class ChallongeAPIServiceSpec extends Specification {

	private static final String TOKEN = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
	//
	private static final String URL = "https://challonge.com/api/v1"
	
	
    def setup() {
		//	private static final String TOKEN = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
		//
		//	static final String URL = "https://challonge.com/api/v1"
    }

    def cleanup() {
    }

    void "Create tournament"() {
		ChallongeAPIService service = new ChallongeAPIService()
		service.baseUrl = URL
		service.token = TOKEN
		service.afterPropertiesSet()
		
		
		
    }
}
