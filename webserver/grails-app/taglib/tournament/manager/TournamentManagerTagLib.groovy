package tournament.manager

import grails.util.Environment;
import tournament.manager.web.Minimizer

class TournamentManagerTagLib {
	static defaultEncodeAs = 'html'
	
	
	static namespace = "tm"

	def commonHeader = {
		//out << render (template: "templates/css")
	}

	def commonFooter = {

	}

	def commonScripts = {

	}

	def loginHeader = {

	}

	def minimize = { attrs, body ->

		Environment.executeForCurrentEnvironment {
			development {
				//out << body()
				out << body()
			}
			test { out << body() }
			production {
				out << new Minimizer().minimize(body() as String)
			}
		}
	}
	
}
