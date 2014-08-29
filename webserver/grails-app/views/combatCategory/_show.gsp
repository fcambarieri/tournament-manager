
<%@ page import="tournament.manager.CombatCategory" %>

<div id="show-combatCategory" class="content scaffold-show" role="main">
	<h1><g:message code="default.show.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
	</g:if>
	<ol class="property-list combatCategory">
	
		<g:if test="${combatCategoryInstance?.name}">
		<li class="fieldcontain">
			<span id="name-label" class="property-label"><g:message code="combatCategory.name.label" default="Name" /></span>
			
				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${combatCategoryInstance}" field="name"/></span>
			
		</li>
		</g:if>
	
		<g:if test="${combatCategoryInstance?.minAge}">
		<li class="fieldcontain">
			<span id="minAge-label" class="property-label"><g:message code="combatCategory.minAge.label" default="Min Age" /></span>
			
				<span class="property-value" aria-labelledby="minAge-label"><g:fieldValue bean="${combatCategoryInstance}" field="minAge"/></span>
			
		</li>
		</g:if>
	
		<g:if test="${combatCategoryInstance?.maxAge}">
		<li class="fieldcontain">
			<span id="maxAge-label" class="property-label"><g:message code="combatCategory.maxAge.label" default="Max Age" /></span>
			
				<span class="property-value" aria-labelledby="maxAge-label"><g:fieldValue bean="${combatCategoryInstance}" field="maxAge"/></span>
			
		</li>
		</g:if>
	
		<g:if test="${combatCategoryInstance?.sex}">
		<li class="fieldcontain">
			<span id="sex-label" class="property-label"><g:message code="combatCategory.sex.label" default="Sex" /></span>
			
				<span class="property-value" aria-labelledby="sex-label"><g:fieldValue bean="${combatCategoryInstance}" field="sex"/></span>
			
		</li>
		</g:if>
	
		<g:if test="${combatCategoryInstance?.tournament}">
		<li class="fieldcontain">
			<span id="tournament-label" class="property-label"><g:message code="combatCategory.tournament.label" default="Tournament" /></span>
			
				<span class="property-value" aria-labelledby="tournament-label"><g:link controller="tournament" action="show" id="${combatCategoryInstance?.tournament?.id}">${combatCategoryInstance?.tournament?.encodeAsHTML()}</g:link></span>
			
		</li>
		</g:if>
	
	</ol>
	<g:form url="[resource:combatCategoryInstance, action:'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="btn btn-primary" action="edit" resource="${combatCategoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
			<g:link class="btn btn-primary" action="delete" resource="${combatCategoryInstance}"onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');><g:message code="default.button.delete.label" default="Delete" /></g:link>
		</fieldset>
	</g:form>
</div>
