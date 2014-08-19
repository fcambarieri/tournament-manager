
<%@ page import="tournament.manager.Schools" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schools.label', default: 'Schools')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-schools" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-schools" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'schools.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="phoneNumber" title="${message(code: 'schools.phoneNumber.label', default: 'Phone Number')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'schools.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'schools.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="dateUpdated" title="${message(code: 'schools.dateUpdated.label', default: 'Date Updated')}" />
					
						<th><g:message code="schools.owner.label" default="Owner" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${schoolsInstanceList}" status="i" var="schoolsInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${schoolsInstance.id}">${fieldValue(bean: schoolsInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: schoolsInstance, field: "phoneNumber")}</td>
					
						<td>${fieldValue(bean: schoolsInstance, field: "email")}</td>
					
						<td><g:formatDate date="${schoolsInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${schoolsInstance.dateUpdated}" /></td>
					
						<td>${fieldValue(bean: schoolsInstance, field: "owner")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${schoolsInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
