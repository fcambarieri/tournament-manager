
<%@ page import="tournament.manager.Participants" %>

<div id="list-participants" class="box-body table-responsive no-padding" role="main">
	<table class="tree table table-hover">
	<thead>
			<tr>
			
				<g:sortableColumn property="name" title="${message(code: 'participants.name.label', default: 'Name')}" />
			
				<g:sortableColumn property="lastName" title="${message(code: 'participants.lastName.label', default: 'Last Name')}" />
			
				<g:sortableColumn property="combatStatus" title="${message(code: 'participants.combatStatus.label', default: 'Combat Status')}" />
			
				<g:sortableColumn property="formStatus" title="${message(code: 'participants.formStatus.label', default: 'Form Status')}" />
			
				<th><g:message code="participants.formCategory.label" default="Form Category" /></th>
			
<%--				<th><g:message code="participants.combatWeight.label" default="Combat Weight" /></th>--%>
				<g:sortableColumn property="combatWeight.combatCategory.name" title="${message(code: 'participants.combatWeight.label', default: 'Combat Weight')}" />
				
				<th><g:message code="defualt.action.label" default="Actions"/></th>
			
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
			
<%--				<td>${fieldValue(bean: participantsInstance, field: "combatWeight")}</td>--%>
				
				<td>${participantsInstance.combatWeight != null ? participantsInstance.combatWeight.combatCategory.toString() + " " +participantsInstance.combatWeight.toString() : null}</td>
				<td>
					<g:link action="edit" class="btn btn-primary" title="${message(code: 'default.button.edit.label', default: 'Edit')}" resource="${participantsInstance}">
						<i class="fa fa-edit"></i>
					</g:link>
					<g:form url="[resource:participantsInstance, action:'delete']" method="DELETE" >
						<button type="submit" class="btn btn-danger" title="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-trash-o"></i></button>
					</g:form>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${participantsInstanceCount ?: 0}" />
	</div>
</div>

