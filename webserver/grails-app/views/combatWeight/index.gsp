
<%@ page import="tournament.manager.CombatWeight" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'combatWeight.label', default: 'CombatWeight')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-combatWeight" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-combatWeight" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="minWeight" title="${message(code: 'combatWeight.minWeight.label', default: 'Min Weight')}" />
					
						<g:sortableColumn property="maxWeight" title="${message(code: 'combatWeight.maxWeight.label', default: 'Max Weight')}" />
					
						<th><g:message code="combatWeight.combatCategory.label" default="Combat Category" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${combatWeightInstanceList}" status="i" var="combatWeightInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${combatWeightInstance.id}">${fieldValue(bean: combatWeightInstance, field: "minWeight")}</g:link></td>
					
						<td>${fieldValue(bean: combatWeightInstance, field: "maxWeight")}</td>
					
						<td>${fieldValue(bean: combatWeightInstance, field: "combatCategory")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${combatWeightInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
