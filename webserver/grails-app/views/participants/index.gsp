
<%@ page import="tournament.manager.Participants" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'participants.label', default: 'Participants')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-participants" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-participants" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'participants.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'participants.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="combatStatus" title="${message(code: 'participants.combatStatus.label', default: 'Combat Status')}" />
					
						<g:sortableColumn property="formStatus" title="${message(code: 'participants.formStatus.label', default: 'Form Status')}" />
					
						<th><g:message code="participants.formCategory.label" default="Form Category" /></th>
					
						<th><g:message code="participants.combatWeight.label" default="Combat Weight" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${participantsInstanceList}" status="i" var="participantsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${participantsInstance.id}">${fieldValue(bean: participantsInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: participantsInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: participantsInstance, field: "combatStatus")}</td>
					
						<td>${fieldValue(bean: participantsInstance, field: "formStatus")}</td>
					
						<td>${fieldValue(bean: participantsInstance, field: "formCategory")}</td>
					
						<td>${fieldValue(bean: participantsInstance, field: "combatWeight")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${participantsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
