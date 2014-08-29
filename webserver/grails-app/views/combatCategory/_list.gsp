
<%@ page import="tournament.manager.CombatCategory" %>
		
		<div id="list-combatCategory" class="box-body table-responsive no-padding" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
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
                                <button type="button" class="btn btn-default"><g:message code="defualt.action.select" default="Select"/></button>
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                    	<g:link action="edit" resource="${combatCategoryInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>	
                                    </li>	
                                    <li>         
                                    	<g:form url="[resource:combatCategoryInstance, action:'delete']" >
	 										<g:link  action="delete" resource="${combatCategoryInstance}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
	 											<g:message code="default.button.delete.label" default="Delete" />
	 										</g:link>                                   	
 										</g:form>
                                    </li>
                                </ul>
                            </div>
						</td>
					</tr>
					<g:if test="${combatCategoryInstance.weights.size()>0}"> 
						<g:each in="${combatCategoryInstance.weights}" status="j" var="weight">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td></td>
					
								<td></td>
					
								<td></td>
					
								<td></td>
					
								<td></td>

								<td>${fieldValue(bean: weight, field: "minWeight")}</td>

								<td>${fieldValue(bean: weight, field: "maxWeight")}</td>

								<td/>
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
