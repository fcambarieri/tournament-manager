
<%@ page import="tournament.manager.CombatCategory" %>
		
		<div id="list-combatCategory" class="box-body table-responsive no-padding" role="main">
			<table id="treeCategory" class="tree table table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'combatCategory.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="minAge" title="${message(code: 'combatCategory.minAge.label', default: 'Min Age')}" />
					
						<g:sortableColumn property="maxAge" title="${message(code: 'combatCategory.maxAge.label', default: 'Max Age')}" />
					
						<g:sortableColumn property="sex" title="${message(code: 'combatCategory.sex.label', default: 'Sex')}" />
					
						<th><g:message code="combatCategory.tournament.label" default="Tournament" /></th>

						<th><g:message code="combatCategory.minWeight.label" default="Min Weigth" /></th>

						<th><g:message code="combatCategory.maxWeight.label" default="Max Weight" /></th>
					
						<th><g:message code="defualt.action.label" default="Actions"/></th>
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

						<td/>

						<td/>

						<td>

							<div class="btn-group">
							
								<g:link action="edit" class="btn btn-primary" title="${message(code: 'default.button.edit.label', default: 'Edit')}" resource="${combatCategoryInstance}">
									<i class="fa fa-edit"></i>
								</g:link>
								<a class="btn btn-primary" title="${message(code: 'default.button.addWeight.label', default: 'Add weight')}" href="${createLink(controller: 'combatWeight', action:'create', params:[combatCategoryId:combatCategoryInstance.id])}">
									<i class="fa fa-plus"></i>
								</a>
								<g:form url="[resource:combatCategoryInstance, action:'delete']" method="DELETE" >
									<button type="submit" class="btn btn-danger" title="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-trash-o"></i></button>
	 							</g:form>
                            </div>
						</td>
					</tr>
					<g:if test="${combatCategoryInstance.weights.size()>0}"> 
						<g:each in="${combatCategoryInstance.weights.sort{a,b-> a.minWeight<=>b.minWeight}}" status="j" var="weight">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td></td>
					
								<td></td>
					
								<td></td>
					
								<td></td>
					
								<td></td>

								<td>${fieldValue(bean: weight, field: "minWeight")}</td>

								<td>${fieldValue(bean: weight, field: "maxWeight")}</td>

								<td>
									<a class="btn btn-primary" title="${message(code: 'default.button.edit.label', default: 'Edit')}" href="${createLink(controller: 'combatWeight', action:'edit', params:[id:weight.id,combatCategoryId:combatCategoryInstance.id])}">
										<i class="fa fa-edit"></i>
									</a>
									<g:form url="[resource:weight, action:'delete']" method="DELETE" >
										<button type="submit" class="btn btn-danger" title="${message(code: 'default.button.delete.label', default: 'Delete')}"  onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-trash-o"></i></button>
	 								</g:form>
								</td>
						<tr>
						</g:each>
					</g:if>

				</g:each>
				
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${combatCategoryInstanceCount ?: 0}" />
			</div>
		</div>
