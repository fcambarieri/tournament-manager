 <!-- COMPOSE MESSAGE MODAL -->
  <div class="modal fade" id="compose-modal" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title"><i class="fa fa-envelope-o"></i>${title}</h4>
              </div>
              <g:form name="${formName}" id="${formName}"
	url="[controller:'${controller}',action:'${action}']">
                  <div class="modal-body">
                      	<g:render template="${template}"></g:render>
                  </div>
                  <div class="modal-footer clearfix">

                      <button type="button" class="btn btn-danger" data-dismiss="modal"><i class="fa fa-times"></i> Discard</button>

                      <button type="submit" class="btn btn-primary pull-left"><i class="fa fa-check"></i>Save</button>
                  </div>
              </g:form>
          </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->