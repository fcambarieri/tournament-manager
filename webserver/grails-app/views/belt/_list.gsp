
<%@ page import="tournament.manager.Belt"%>


<div class="box-body table-responsive">
      <div id="example2_wrapper" class="dataTables_wrapper form-inline" role="grid"><div class="row"><div class="col-xs-6"></div><div class="col-xs-6"></div></div>
      <table id="beltTable" class="table table-bordered table-hover dataTable" aria-describedby="example2_info">
          <thead>
              <tr role="row">
<%--              	<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">Rendering engine</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending">Browser</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Platform(s)</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending">Engine version</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending">CSS grade</th>--%>
              	<g:sortableColumn property="description" rowspan="1" colspan="1"
					title="${message(code: 'belt.description.label', default: 'Description')}" />
              </tr>
          </thead>
          
          <tfoot>
              <tr>
              </tr>
          </tfoot>
      <tbody role="alert" aria-live="polite" aria-relevant="all">
      
      		<g:each in="${beltInstanceList}" status="i" var="beltInstance">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					  <td id="belt-row-${beltInstance.id}">

              <%--<a href="${createLink(uri: '/belt/edit')}/${beltInstance.id}" data-toggle="modal" data-target="#myModal">${fieldValue(bean: beltInstance, field: "description")}</a>--%>

               <a uri="${createLink(uri: '/belt/edit')}/${beltInstance.id}" data="${beltInstance.id}" onclick="showForm(this);">${fieldValue(bean: beltInstance, field: "description")}</a> 

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
                  <a class="btn btn-block btn-primary" data-toggle="modal" data-target="#beltModal"><i class="fa fa-pencil"></i>+</a>
              </li>
     					<li><g:paginate total="${beltInstanceCount ?: 0}" /></li>
     				</ul>
     			</div>
     		</div>
  </div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

  <mat:modal template="/belt/form" title="${message(code: 'belt.label', default: 'Belt')}" buttonName="beltSave" formName="beltForm" modalName="beltModal"/>


  
