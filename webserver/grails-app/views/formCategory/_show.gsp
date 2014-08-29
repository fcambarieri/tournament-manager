
<%@ page import="tournament.manager.FormCategory" %>

		<div id="show-formCategory" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list formCategory">
			
				<g:if test="${formCategoryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="formCategory.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${formCategoryInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formCategoryInstance?.minAge}">
				<li class="fieldcontain">
					<span id="minAge-label" class="property-label"><g:message code="formCategory.minAge.label" default="Min Age" /></span>
					
						<span class="property-value" aria-labelledby="minAge-label"><g:fieldValue bean="${formCategoryInstance}" field="minAge"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formCategoryInstance?.maxAge}">
				<li class="fieldcontain">
					<span id="maxAge-label" class="property-label"><g:message code="formCategory.maxAge.label" default="Max Age" /></span>
					
						<span class="property-value" aria-labelledby="maxAge-label"><g:fieldValue bean="${formCategoryInstance}" field="maxAge"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formCategoryInstance?.tournament}">
				<li class="fieldcontain">
					<span id="tournament-label" class="property-label"><g:message code="formCategory.tournament.label" default="Tournament" /></span>
					
						<span class="property-value" aria-labelledby="tournament-label"><g:link controller="tournament" action="show" id="${formCategoryInstance?.tournament?.id}">${formCategoryInstance?.tournament?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:formCategoryInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${formCategoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
