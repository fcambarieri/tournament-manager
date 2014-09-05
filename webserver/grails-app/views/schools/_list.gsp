
<%@ page import="tournament.manager.Schools" %>

<div id="list-schools" class="box-body table-responsive no-padding" role="main">
	<table class="table table-bordered">
	<thead>
			<tr>
			
				<g:sortableColumn property="name" title="${message(code: 'schools.name.label', default: 'Name')}" />
			
				<g:sortableColumn property="phoneNumber" title="${message(code: 'schools.phoneNumber.label', default: 'Phone Number')}" />
			
				<g:sortableColumn property="email" title="${message(code: 'schools.email.label', default: 'Email')}" />
			
				<g:sortableColumn property="dateCreated" title="${message(code: 'schools.dateCreated.label', default: 'Date Created')}" />
			
				<g:sortableColumn property="dateUpdated" title="${message(code: 'schools.dateUpdated.label', default: 'Date Updated')}" />
			
<%--				<th><g:message code="schools.owner.label" default="Owner" /></th>--%>
				<th><g:message code="defualt.action.label" default="Actions"/></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${schoolsInstanceList}" status="i" var="schoolsInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			
				<td><g:link action="show" id="${schoolsInstance.id}">${fieldValue(bean: schoolsInstance, field: "name")}</g:link></td>
			
				<td>${fieldValue(bean: schoolsInstance, field: "phoneNumber")}</td>
			
				<td>${fieldValue(bean: schoolsInstance, field: "email")}</td>
			
				<td><g:formatDate date="${schoolsInstance.dateCreated}" /></td>
			
				<td><g:formatDate date="${schoolsInstance.lastUpdated}" /></td>
			
<%--				<td>${fieldValue(bean: schoolsInstance, field: "owner")}</td>--%>
				
				<td>
					<g:link action="edit" class="btn btn-primary" resource="${schoolsInstance}">
						<i class="fa fa-edit"></i>
					</g:link>
					<g:form url="[resource:schoolsInstance, action:'delete']" method="DELETE" >
						<button type="submit" class="btn btn-danger" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
							<i class="fa fa-times"></i>
						</button>					                                   	
					</g:form>
				</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${schoolsInstanceCount ?: 0}" />
	</div>
</div>

