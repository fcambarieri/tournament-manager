
<%@ page import="tournament.manager.Belt"%>


<div id="list-combatCategory"
	class="box-body table-responsive no-padding" role="main">
	<table id="beltTable"
		class="table table-bordered table-hover dataTable"
		aria-describedby="example2_info">
		<thead>
			<tr role="row">
				<g:sortableColumn property="description" rowspan="1" colspan="1" title="${message(code: 'belt.description.label', default: 'Description')}" />
				<th><g:message code="defualt.action.label" default="Actions"/></th>
			</tr>
		</thead>
		<tfoot>
			<tr>
			</tr>
		</tfoot>
		<tbody role="alert" aria-live="polite" aria-relevant="all">

			<g:each in="${beltInstanceList}" status="i" var="beltInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td id="belt-row-${beltInstance.id}"><a
						uri="${createLink(uri: '/belt/edit')}/${beltInstance.id}"
						data="${beltInstance.id}" onclick="showForm(this);">
							${fieldValue(bean: beltInstance, field: "description")}
					</a></td>
					<td>
						<g:link action="edit" class="btn btn-primary" resource="${beltInstance}">
							<i class="fa fa-edit"></i>
						</g:link>
						<g:form url="[resource:combatCategoryInstance, action:'delete']" method="DELETE" >
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
		<g:paginate total="${beltInstanceCount ?: 0}" />
	</div>

</div>
