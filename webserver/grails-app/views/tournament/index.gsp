<%@ page import="tournament.manager.Tournament"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Dashboard</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
		<meta name="layout" content="main"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        
       	<g:render template="/home/templates/header"/>
        
        <div class="wrapper row-offcanvas row-offcanvas-left">
           
			<g:render template="/home/templates/left"/>
			
			
			
			<!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        <small>
                        	
                        </small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">tournament</a></li>
                        <li class="active">list</li>
                    </ol>
                </section>
				
                <!-- Main content -->
                <section class="content">
 						    <div id="message"></div>
 							<g:if test="${params.ok}">
                        		<div class="alert alert-success alert-dismissable">
                                        <i class="fa fa-check"></i>
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                        <b>Successfull!</b> tournament created!"
                                    </div>
                        	</g:if>
 						
 						
 							<div class="box box-info">
                                <div class="box-header">
                                    <h3 class="box-title">Tournaments</h3>
                                    <div class="box-tools pull-right">
                                        <div class="label bg-aqua">Label</div>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <g:render template="/tournament/list"></g:render>
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    
                                </div><!-- /.box-footer-->
                            </div>
                            

                </section><!-- /.content -->
            </aside><!-- /.right-side -->

        </div><!-- ./wrapper -->

		<g:render template="/home/templates/footer"/>
		
		<script src="//cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {

			    var tblTournament = $('#tblTournament').DataTable();


				$("#save").click(function(){
					saveTournament();
				});
				
                 $('#compose-modal').on('hidden.bs.modal', function (e) {
                  $("#name").val("");
                })

				function saveTournament() {
					var name = $("#name").val();
					var status = $("#status").val();;
					$.post("${createLink(controller: 'tournament', action:'ajaxSave')}",
							  {
							    "name":name,
							    "status":status
							  },
							  function(data,status){
                                    $('#compose-modal').modal('hide');
                                    tblTournament.row.add( [
                                        data.name,
                                        data.date_created,
                                        data.last_updated,
                                        data.status,
                                        '',
                                    ] ).draw();
                                    showSuccess(data);
							 });
				}
			    
			});
            
            function showSuccess(data) {
                $("#message").html('<div class="alert alert-success alert-dismissable"><i class="fa fa-check"></i><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button><b>Success!</b> </div>');
            }

            function showFailure() {

            }    


		
		</script>
    </body>
</html>