
<%@ page import="tournament.manager.FormCategory" %>
		
		<div id="list-formCategory" class="box-body table-responsive no-padding" role="main">

			<table class="table table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'formCategory.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="minAge" title="${message(code: 'formCategory.minAge.label', default: 'Min Age')}" />
					
						<g:sortableColumn property="maxAge" title="${message(code: 'formCategory.maxAge.label', default: 'Max Age')}" />
					
						<th><g:message code="formCategory.tournament.label" default="Tournament" /></th>

						<th><g:message code="defualt.action.label" default="Actions"/></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${formCategoryInstanceList}" status="i" var="formCategoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${formCategoryInstance.id}">${fieldValue(bean: formCategoryInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "minAge")}</td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "maxAge")}</td>
					
						<td>${fieldValue(bean: formCategoryInstance, field: "tournament")}</td>

						<td>
							<g:link action="edit" resource="${formCategoryInstance}" title="${message(code: 'default.button.edit.label', default: 'Edit')}">
								<i class="fa fa-edit"></i>
							</g:link>
							<g:form url="[resource:formCategoryInstance, action:'delete']" >
								<button type="submit" class="btn btn-danger" title="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-trash-o"></i></button>                                 	
							</g:form>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${formCategoryInstanceCount ?: 0}" />
			</div>
		</div>
