
<%@ page import="tournament.manager.FormCategory" %>
		
		<div id="list-formCategory" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="ch-datagrid">
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
