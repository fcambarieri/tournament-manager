
<%@ page import="tournament.manager.Students" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'students.label', default: 'Students')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-students" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-students" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list students">
			
				<g:if test="${studentsInstance?.docNumber}">
				<li class="fieldcontain">
					<span id="docNumber-label" class="property-label"><g:message code="students.docNumber.label" default="Doc Number" /></span>
					
						<span class="property-value" aria-labelledby="docNumber-label"><g:fieldValue bean="${studentsInstance}" field="docNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.picture}">
				<li class="fieldcontain">
					<span id="picture-label" class="property-label"><g:message code="students.picture.label" default="Picture" /></span>
					
						<span class="property-value" aria-labelledby="picture-label"><g:fieldValue bean="${studentsInstance}" field="picture"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.birthDate}">
				<li class="fieldcontain">
					<span id="birthDate-label" class="property-label"><g:message code="students.birthDate.label" default="Birth Date" /></span>
					
						<span class="property-value" aria-labelledby="birthDate-label"><g:formatDate date="${studentsInstance?.birthDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="students.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${studentsInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="students.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${studentsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.school}">
				<li class="fieldcontain">
					<span id="school-label" class="property-label"><g:message code="students.school.label" default="School" /></span>
					
						<span class="property-value" aria-labelledby="school-label"><g:link controller="schools" action="show" id="${studentsInstance?.school?.id}">${studentsInstance?.school?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentsInstance?.sex}">
				<li class="fieldcontain">
					<span id="sex-label" class="property-label"><g:message code="students.sex.label" default="Sex" /></span>
					
						<span class="property-value" aria-labelledby="sex-label"><g:fieldValue bean="${studentsInstance}" field="sex"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:studentsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${studentsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
