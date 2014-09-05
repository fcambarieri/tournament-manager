
<%@ page import="tournament.manager.Brackets" %>

<div id="list-brackets" class="content scaffold-list" role="main">
<table>
	<thead>
			<tr>
			
				<th><g:message code="brackets.combatWeight.label" default="Combat Weight" /></th>
			
				<th><g:message code="brackets.formCategory.label" default="Form Category" /></th>
			
				<th><g:message code="brackets.belt.label" default="Belt" /></th>
			
				<g:sortableColumn property="externalId" title="${message(code: 'brackets.externalId.label', default: 'External Id')}" />
			
				<g:sortableColumn property="name" title="${message(code: 'brackets.name.label', default: 'Name')}" />
			
				<g:sortableColumn property="status" title="${message(code: 'brackets.status.label', default: 'Status')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${bracketsInstanceList}" status="i" var="bracketsInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			
				<td><g:link action="show" id="${bracketsInstance.id}">${fieldValue(bean: bracketsInstance, field: "combatWeight")}</g:link></td>
			
				<td>${fieldValue(bean: bracketsInstance, field: "formCategory")}</td>
			
				<td>${fieldValue(bean: bracketsInstance, field: "belt")}</td>
			
				<td>${fieldValue(bean: bracketsInstance, field: "externalId")}</td>
			
				<td>${fieldValue(bean: bracketsInstance, field: "name")}</td>
			
				<td>${fieldValue(bean: bracketsInstance, field: "status")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${bracketsInstanceCount ?: 0}" />
	</div>
</div>
