package tournament.manager

import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROLE_USER')")
abstract class AbstractController {

	def springSecurityService
	def tournamentService
	 
	static String TOURNAMENT = "tournament"
	
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
	
	def getTournaments() {
		return tournamentService.listTournamentByOwner(getCurrentUser())
	}

	def formView(model, params) {
		def map = initModel(model,params)
		render (view:"/home/forms/form", model:map)
	}
	
	def editFormView(model, params) {
		def map = initModel(model,params)
		map.action = "update"
		map.buttonName = "update"
		map.buttonValue = message(code: 'default.button.update.label', default: 'Update')
		map.method="PUT"
		render (view:"/home/forms/form", model:map)
	}

	void setTournamentSelected(Long tournamentId) {
		def criteria = Tournament.createCriteria()
		def tournamentSelected = criteria.get {
			eq("owner", getCurrentUser())
			eq("id", tournamentId)
		}
		session."${TOURNAMENT}" = tournamentSelected
	}

	def getTournamentSelected() {
		return session."${TOURNAMENT}"	
	}

	
	private def initModel (model, params) {
		def map = [
			"instance":getDomainInstance(),
			"controller": getControllerName(),
			"action": "create",
			"title": getTitle(),
			"templateName": "/${getControllerName()}/form",
			"buttonName":"create",
			"buttonValue":message(code: 'default.button.create.label', default: 'Create'),
			"buttonNameTemplate":"/home/forms/formButtons",
			"method":"POST"
			]
		if (model != null) {
			map.putAll(model)
		}
		return map
	}
	
	def listFormView(model, params) {
		def map = initModel(model,params)
		map.controllerName = getControllerName()
		map.action = "create"
		map.templateName="/${getControllerName()}/list"
		map.buttonNameTemplate = "/home/forms/listButtons"
		//formView(map, params)
		render (view:"/home/forms/blank", model:map)
	}
	
	def abstract getDomainInstance(params)
	
	def getTitle() {
		String code = "default.entity.${getControllerName()}.title"
		return message(code: code)
	}
	
	def getControllerName() {
		String name = this.getClass().getSimpleName()
		name = name.replaceAll("Controller", "");
		name = name.substring(0).toLowerCase() + name.substring(1, name.length())
		return name
	}
	
	def editModel(instance) {
		return [instance:instance,
			"action":"update",
			"templateName":"/${getControllerName()}/form"]
	}

	def initblankModel (model, template, params) {
		def map = [
			"instance":getDomainInstance(),
			"controller": getControllerName(),
			"action": "create",
			"title": getTitle(),
			"templateName": template,
			]
		if (model != null) {
			map.putAll(model)
		}
		return map
	}
	
	def blankView(model, template, params) {
		def map = initblankModel(model, template,params)
		render (view:"/home/forms/blank", model:map)
	}
}
