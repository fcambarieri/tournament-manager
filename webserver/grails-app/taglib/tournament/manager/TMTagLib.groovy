package tournament.manager

class TMTagLib {
	
//    static defaultEncodeAs = 'html'
	
	static namespace = "mat"
	
	def modal = { attrs ->
		def model = [
			template:attrs.template?:"", 
			controller:attrs.controller?:"", 
			action:attrs.action?:"", 
			title:attrs.title?:"New Form", 
			formName:attrs.formName?:"myForm"
		]
		out << render (template: "/home/templates/modal", model:model)
	}

	def logout = { attrs ->
		StringBuilder sb = new StringBuilder()
		sb.append "<form name='logoutForm' method='POST' action='/tournament-manager/j_spring_security_logout'>"
		sb.append "<input type='hidden' name='' value=''>"
		sb.append "<a HREF='javascript:document.logoutForm.submit()' class='${attrs.class}'>Logout</a>"
		sb.append "</form> "
		out << sb.toString()
	}
}
