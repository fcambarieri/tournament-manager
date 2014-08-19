
<%@ page import="tournament.manager.CombatCategory" %>
		
		<div id="list-combatCategory" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="ch-datagrid">
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'combatCategory.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="minAge" title="${message(code: 'combatCategory.minAge.label', default: 'Min Age')}" />
					
						<g:sortableColumn property="maxAge" title="${message(code: 'combatCategory.maxAge.label', default: 'Max Age')}" />
					
						<g:sortableColumn property="sex" title="${message(code: 'combatCategory.sex.label', default: 'Sex')}" />
					
						<th><g:message code="combatCategory.tournament.label" default="Tournament" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${combatCategoryInstanceList}" status="i" var="combatCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${combatCategoryInstance.id}">${fieldValue(bean: combatCategoryInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: combatCategoryInstance, field: "minAge")}</td>
					
						<td>${fieldValue(bean: combatCategoryInstance, field: "maxAge")}</td>
					
						<td>${fieldValue(bean: combatCategoryInstance, field: "sex")}</td>
					
						<td>${fieldValue(bean: combatCategoryInstance, field: "tournament")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${combatCategoryInstanceCount ?: 0}" />
			</div>
		</div>
