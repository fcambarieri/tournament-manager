
<%@ page import="tournament.manager.FormCategory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formCategory.label', default: 'FormCategory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-formCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-formCategory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'formCategory.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="minAge" title="${message(code: 'formCategory.minAge.label', default: 'Min Age')}" />
					
						<g:sortableColumn property="maxAge" title="${message(code: 'formCategory.maxAge.label', default: 'Max Age')}" />
					
						<th><g:message code="formCategory.tournament.label" default="Tournament" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${formCategoryInstanceList}" status="i" var="formCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${formCategoryInstance.id}">${fieldValue(bean: formCategoryInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "minAge")}</td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "maxAge")}</td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "tournament")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${formCategoryInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
