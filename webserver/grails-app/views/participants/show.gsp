
<%@ page import="tournament.manager.Participants" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'participants.label', default: 'Participants')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-participants" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-participants" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list participants">
			
				<g:if test="${participantsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="participants.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${participantsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="participants.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${participantsInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.combatStatus}">
				<li class="fieldcontain">
					<span id="combatStatus-label" class="property-label"><g:message code="participants.combatStatus.label" default="Combat Status" /></span>
					
						<span class="property-value" aria-labelledby="combatStatus-label"><g:fieldValue bean="${participantsInstance}" field="combatStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.formStatus}">
				<li class="fieldcontain">
					<span id="formStatus-label" class="property-label"><g:message code="participants.formStatus.label" default="Form Status" /></span>
					
						<span class="property-value" aria-labelledby="formStatus-label"><g:fieldValue bean="${participantsInstance}" field="formStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.formCategory}">
				<li class="fieldcontain">
					<span id="formCategory-label" class="property-label"><g:message code="participants.formCategory.label" default="Form Category" /></span>
					
						<span class="property-value" aria-labelledby="formCategory-label"><g:link controller="formCategory" action="show" id="${participantsInstance?.formCategory?.id}">${participantsInstance?.formCategory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.combatWeight}">
				<li class="fieldcontain">
					<span id="combatWeight-label" class="property-label"><g:message code="participants.combatWeight.label" default="Combat Weight" /></span>
					
						<span class="property-value" aria-labelledby="combatWeight-label"><g:link controller="combatWeight" action="show" id="${participantsInstance?.combatWeight?.id}">${participantsInstance?.combatWeight?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.belt}">
				<li class="fieldcontain">
					<span id="belt-label" class="property-label"><g:message code="participants.belt.label" default="Belt" /></span>
					
						<span class="property-value" aria-labelledby="belt-label"><g:link controller="belt" action="show" id="${participantsInstance?.belt?.id}">${participantsInstance?.belt?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="participants.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${participantsInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="participants.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${participantsInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.school}">
				<li class="fieldcontain">
					<span id="school-label" class="property-label"><g:message code="participants.school.label" default="School" /></span>
					
						<span class="property-value" aria-labelledby="school-label"><g:link controller="schools" action="show" id="${participantsInstance?.school?.id}">${participantsInstance?.school?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${participantsInstance?.tournament}">
				<li class="fieldcontain">
					<span id="tournament-label" class="property-label"><g:message code="participants.tournament.label" default="Tournament" /></span>
					
						<span class="property-value" aria-labelledby="tournament-label"><g:link controller="tournament" action="show" id="${participantsInstance?.tournament?.id}">${participantsInstance?.tournament?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:participantsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${participantsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
