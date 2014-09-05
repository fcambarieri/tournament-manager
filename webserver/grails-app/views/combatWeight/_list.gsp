
<%@ page import="tournament.manager.CombatWeight" %>

<div id="list-combatWeight" class="box-body table-responsive no-padding" role="main">
	
	<table class="table table-bordered">
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
