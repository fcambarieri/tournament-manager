<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>${message(code: 'default.title.update.label', default: 'Update')} ${title}</title>  
</head>
<body>
  <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                 <h4 class="modal-title">${title}</h4>
            </div>      <!-- /modal-header -->
            <div class="modal-body">
              <g:render template="${templateName}"/>
            </div>      <!-- /modal-body -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">${message(code: 'default.button.close.label', default: 'Close')}</button>
                <button id="update" type="button" class="btn btn-primary" onclikc="saveBelt();">${message(code: 'default.button.update.label', default: 'Update')}</button>
            </div>      <!-- /modal-footer -->
        </div>         <!-- /modal-content -->
    </div>     <!-- /modal-dialog -->
</body>
</html>