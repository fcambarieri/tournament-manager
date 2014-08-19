
<%@ page import="tournament.manager.CombatWeight" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'combatWeight.label', default: 'CombatWeight')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-combatWeight" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-combatWeight" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list combatWeight">
			
				<g:if test="${combatWeightInstance?.minWeight}">
				<li class="fieldcontain">
					<span id="minWeight-label" class="property-label"><g:message code="combatWeight.minWeight.label" default="Min Weight" /></span>
					
						<span class="property-value" aria-labelledby="minWeight-label"><g:fieldValue bean="${combatWeightInstance}" field="minWeight"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${combatWeightInstance?.maxWeight}">
				<li class="fieldcontain">
					<span id="maxWeight-label" class="property-label"><g:message code="combatWeight.maxWeight.label" default="Max Weight" /></span>
					
						<span class="property-value" aria-labelledby="maxWeight-label"><g:fieldValue bean="${combatWeightInstance}" field="maxWeight"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${combatWeightInstance?.combatCategory}">
				<li class="fieldcontain">
					<span id="combatCategory-label" class="property-label"><g:message code="combatWeight.combatCategory.label" default="Combat Category" /></span>
					
						<span class="property-value" aria-labelledby="combatCategory-label"><g:link controller="combatCategory" action="show" id="${combatWeightInstance?.combatCategory?.id}">${combatWeightInstance?.combatCategory?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:combatWeightInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${combatWeightInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
