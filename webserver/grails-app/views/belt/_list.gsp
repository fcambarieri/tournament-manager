
<%@ page import="tournament.manager.Belt"%>

<div id="list-belt" class="content scaffold-list" role="main">
	<g:if test="${flash.message}">
		<div class="message" role="status">
			${flash.message}
		</div>
	</g:if>
	<table class="ch-datagrid">
		<thead>
			<tr>
				<g:sortableColumn property="description"
					title="${message(code: 'belt.description.label', default: 'Description')}" />
				<th><g:message code="belt.tournament.label"
						default="Tournament" /></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${beltInstanceList}" status="i" var="beltInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td><g:link action="show" id="${beltInstance.id}">
							${fieldValue(bean: beltInstance, field: "description")}
						</g:link></td>
					<td>
						${fieldValue(bean: beltInstance, field: "tournament")}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${beltInstanceCount ?: 0}" />
	</div>
	<ul>
		<li><button id="addBelt" ><g:message
					code="default.add.label" default="add"/></button></li>
	</ul>
</div>
