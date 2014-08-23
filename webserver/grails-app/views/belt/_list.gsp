
<%@ page import="tournament.manager.Belt"%>


<div class="box-body table-responsive">
      <div id="example2_wrapper" class="dataTables_wrapper form-inline" role="grid"><div class="row"><div class="col-xs-6"></div><div class="col-xs-6"></div></div><table id="example2" class="table table-bordered table-hover dataTable" aria-describedby="example2_info">
          <thead>
              <tr role="row">
<%--              	<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">Rendering engine</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">Browser</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Platform(s)</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending">Engine version</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending">CSS grade</th>--%>
              	<g:sortableColumn property="description" rowspan="1" colspan="1"
					title="${message(code: 'belt.description.label', default: 'Description')}" />
				<th rowspan="1" colspan="1"><g:message code="belt.tournament.label"
						default="Tournament" /></th>
              </tr>
          </thead>
          
          <tfoot>
              <tr>
              </tr>
          </tfoot>
      <tbody role="alert" aria-live="polite" aria-relevant="all">
      
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
      		<div class="row">
      			<div class="col-xs-6">
      				<div class="dataTables_info" id="example2_info">
      					
      				</div>
     			</div>
     		<div class="col-xs-6">
     			<div class="dataTables_paginate paging_bootstrap">
     				<ul class="pagination">
     					<li><button class="btn btn-primary">+</button></li>
     					<li><g:paginate total="${beltInstanceCount ?: 0}" /></li>
     				</ul>
     			</div>
     		</div>
  </div>
