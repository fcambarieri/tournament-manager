package tournament.service

import grails.transaction.Transactional


//import groovyx.net.http.*
//import groovyx.net.http.*
//import static groovyx.net.http.ContentType.*
//import static groovyx.net.http.Method.*


import org.springframework.beans.factory.InitializingBean;

class ChallongeAPIService implements InitializingBean {

	static transactional = false
	
//	private static final String TOKEN = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
//	
//	static final String URL = "https://challonge.com/api"
	
	String token
	String baseUrl
//	HTTPBuilder client
   
	public void afterPropertiesSet() throws Exception {
//		client = new HTTPBuilder(baseUrl)
		
	}
}
