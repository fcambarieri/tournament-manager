
<%@ page import="tournament.manager.Belt"%>


<div class="box-body table-responsive">
      <div id="example2_wrapper" class="dataTables_wrapper form-inline" role="grid"><div class="row"><div class="col-xs-6"></div><div class="col-xs-6"></div></div>
      <table id="tblTournament"  name="tblTournament" class="table table-bordered table-hover dataTable" aria-describedby="example2_info">
          <thead>
              <tr role="row">
<%--              	<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">Rendering engine</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">Browser</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Platform(s)</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending">Engine version</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending">CSS grade</th>--%>
              	<g:sortableColumn property="name"
								title="${message(code: 'tournament.name.label', default: 'Name')}" />

					<g:sortableColumn property="dateCreated"
						title="${message(code: 'tournament.dateCreated.label', default: 'Creation Date')}" />

					<g:sortableColumn property="lastUpdated"
						title="${message(code: 'tournament.lastUpdated.label', default: 'Last updated')}" />

					<g:sortableColumn property="status"
						title="${message(code: 'tournament.status.label', default: 'Status')}" />

					<th scope="col"><g:message
							code="default.button.actions.label" default="Actions" /></th>

              </tr>
          </thead>
          
          <tfoot>
              <tr>
              </tr>
          </tfoot>
     	 <tbody role="alert" aria-live="polite" aria-relevant="all">
      
	      		<g:each in="${tournaments}" status="i" var="tournamentInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link action="show" id="${tournamentInstance.id}">
										${fieldValue(bean: tournamentInstance, field: "name")}
									</g:link></td>

								<td><g:formatDate date="${tournamentInstance.dateCreated}" /></td>

								<td><g:formatDate date="${tournamentInstance.lastUpdated}" /></td>

								<%--						<td>${fieldValue(bean: tournamentInstance, field: "owner")}</td>--%>

								<td>
									${fieldValue(bean: tournamentInstance, field: "status")}
								</td>

								<td>
									<ul class="dropdown">
										<li>
										
<%--										 <g:link controller="tournament" action="settings" params="[id:${tournamentInstance.id}]">--%>
<%--												<i class="icon-cog">${message(code: 'default.link.create.label', default: 'Settings')}</i>--%>
<%--												--%>
<%--											</g:link>--%>
										</li>
									</ul>
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
     					<li>
     						<a class="btn btn-block btn-primary" data-toggle="modal" data-target="#compose-modal"><i class="fa fa-pencil"></i>+</a>
     					</li>
     					<li><g:paginate total="${total?:0}" /></li>
     				</ul>
     			</div>
     		</div>
  		</div>
  	</div>
  </div>
	

 <!-- COMPOSE MESSAGE MODAL -->
        <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title"><i class="fa fa-envelope-o"></i>New Tournament</h4>
                    </div>
                    <g:form name="tournament" id="tournament"
							url="[controller:'tournament',action:'save']">
                        <div class="modal-body">
                            	<g:render template="/tournament/form"></g:render>
                        </div>
                        <div class="modal-footer clearfix">

                            <button id="discard" type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-times"></i>Discard</button>

                            <button id="save" type="button" class="btn btn-primary pull-left"><i class="fa fa-check"></i>Save</button>
                        </div>
                    </g:form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
