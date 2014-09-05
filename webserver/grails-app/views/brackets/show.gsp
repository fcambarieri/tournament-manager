
<%@ page import="tournament.manager.Brackets" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'brackets.label', default: 'Brackets')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-brackets" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-brackets" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list brackets">
			
				<g:if test="${bracketsInstance?.combatWeight}">
				<li class="fieldcontain">
					<span id="combatWeight-label" class="property-label"><g:message code="brackets.combatWeight.label" default="Combat Weight" /></span>
					
						<span class="property-value" aria-labelledby="combatWeight-label"><g:link controller="combatWeight" action="show" id="${bracketsInstance?.combatWeight?.id}">${bracketsInstance?.combatWeight?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.formCategory}">
				<li class="fieldcontain">
					<span id="formCategory-label" class="property-label"><g:message code="brackets.formCategory.label" default="Form Category" /></span>
					
						<span class="property-value" aria-labelledby="formCategory-label"><g:link controller="formCategory" action="show" id="${bracketsInstance?.formCategory?.id}">${bracketsInstance?.formCategory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.belt}">
				<li class="fieldcontain">
					<span id="belt-label" class="property-label"><g:message code="brackets.belt.label" default="Belt" /></span>
					
						<span class="property-value" aria-labelledby="belt-label"><g:link controller="belt" action="show" id="${bracketsInstance?.belt?.id}">${bracketsInstance?.belt?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.externalId}">
				<li class="fieldcontain">
					<span id="externalId-label" class="property-label"><g:message code="brackets.externalId.label" default="External Id" /></span>
					
						<span class="property-value" aria-labelledby="externalId-label"><g:fieldValue bean="${bracketsInstance}" field="externalId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="brackets.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${bracketsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.status}">
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="brackets.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${bracketsInstance}" field="status"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bracketsInstance?.tournament}">
				<li class="fieldcontain">
					<span id="tournament-label" class="property-label"><g:message code="brackets.tournament.label" default="Tournament" /></span>
					
						<span class="property-value" aria-labelledby="tournament-label"><g:link controller="tournament" action="show" id="${bracketsInstance?.tournament?.id}">${bracketsInstance?.tournament?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bracketsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bracketsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
