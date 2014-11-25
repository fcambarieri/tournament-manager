// Place your Spring DSL code here
beans = {
	
	challongeAPIService(tournament.service.ChallongeAPIService) { bean ->
		baseUrl="https://api.challonge.com/v1/"
		token = "xnkm37dqxpyozzbhqp6wtocipwqaba2kwg8xdvwz"
	}
}
