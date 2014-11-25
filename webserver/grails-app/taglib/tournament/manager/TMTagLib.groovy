package tournament.manager

import tournament.manager.utils.GravatarUtil;
import tournament.manager.utils.MD5Util;

class TMTagLib {
	
//    static defaultEncodeAs = 'html'
	def springSecurityService 
	static namespace = "mat"
	
	def getCurrentUser() {
		return springSecurityService.currentUser
	}
	
	def welcome = { attrs ->
		if (springSecurityService.isLoggedIn()) {
			String msg =  message(code: 'default.welcome.label', args: [currentUser.username])
			out << msg
		}
	}
	
	def imgAvatar = { attrs ->
		if (springSecurityService.isLoggedIn()) {
			def currentUser = getCurrentUser()
			def email = currentUser.email
			if (email != null) {
				def size = attrs.size != null ? attrs : ""
				def src = GravatarUtil.gravatarImage(email)
				def tag = "<img src='${src}${size}' class='${attrs.class}'/>"
				out << tag.toString()
			}
		}
	}
	
	def scriptAvatar = { attrs ->
		//<script src="https://www.gravatar.com/205e460b479e2e5b48aec07710c08d50.json?callback=changeTitle" type="text/javascript"></script>
		if (attrs.callback == null) {
			throw new NullPointerException("Callback method is null")
		}
		if (springSecurityService.isLoggedIn()) {
			def currentUser = getCurrentUser()
			def email = currentUser.email
			if (email != null) {
				def callback = attrs.callback
				def src = GravatarUtil.gravatarImage(email)
				def tag = "<script type=\"text/javascript\" src='${src}.json?callback=$callback?'></script>"
				out << tag.toString()
			}
		}
	}
	
	/*def modal = { attrs ->
		def model = [
			template:attrs.template?:"", 
			controller:attrs.controller?:"", 
			action:attrs.action?:"", 
			title:attrs.title?:"New Form", 
			formName:attrs.formName?:"myForm"
		]
		out << render (template: "/home/templates/modal", model:model)
	}*/

	def logout = { attrs ->
		StringBuilder sb = new StringBuilder()
		sb.append "<form name='logoutForm' method='POST' action='/tournament-manager/j_spring_security_logout'>"
		sb.append "<input type='hidden' name='' value=''>"
		sb.append "<a HREF='javascript:document.logoutForm.submit()' class='${attrs.class}'>Logout</a>"
		sb.append "</form> "
		out << sb.toString()
	}

	def modal = { attrs ->
		String template = render (template:attrs.template)
        StringBuilder sb = new StringBuilder()
        sb.append "<div class='modal fade' id='${attrs.modalName}' tabindex='-1' role='dialog' aria-hidden='true'>"
		sb.append "    <div class='modal-dialog'>"
		sb.append "        <div class='modal-content'>"
		sb.append "            <div class='modal-header'>"
		sb.append "                <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>"
		sb.append "                <h4 class='modal-title'><i class='fa fa-envelope-o'></i>${attrs.title}</h4>"
		sb.append "            </div>"
		sb.append "            <form id='${attrs.formName}'>"
		sb.append "                <div class='modal-body'>"
		sb.append "                <div id='formMessage'/>"
		sb.append template
		sb.append "                </div>"
		sb.append "                <div class='modal-footer clearfix'>"
		sb.append "                    <button id='discard' type='button' class='btn btn-danger' data-dismiss='modal'><i class='fa fa-times'></i>Discard</button>"
		sb.append "		                    <button id='${attrs.buttonName}' type='button' class='btn btn-primary'><i class='fa fa-check'></i>Save</button>"
		sb.append "		                </div>"
		sb.append "            </form>"
		sb.append "        </div>"
		sb.append "    </div>"
		sb.append "</div>"
		out << sb.toString()
	}
	
}
