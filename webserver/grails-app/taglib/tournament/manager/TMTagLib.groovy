package tournament.manager

class TMTagLib {
	
//    static defaultEncodeAs = 'html'
	
	static namespace = "mat"
	
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
