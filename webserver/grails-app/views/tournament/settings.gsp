<%@ page import="tournament.manager.Tournament"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tournament Manager</title>
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
                       
                        <small><g:message code="combatCategory.tournament.label" default="Tournament" />
                        <span class="required-indicator">*</span></small>
                        <select id="tournament" name="tournament.id" optionKey="id" required=""  class="form-control">

                            <g:each var="tournament" in="${tournaments}">
                               
                                <option value="${tournament.id}" ${tournament.id.toString().equals(tournamentId?.toString()) ? 'selected' : ''}>${tournament.name}</option>
                            </g:each>

                        </select>

<%--						<a class="btn btn-primary" title="${message(code: 'default.button.participants.label', default: 'Participants')}" href="${createLink(controller: 'participants', action:'index')}">--%>
<%--							<i class="fa fa-plus">${message(code: 'default.button.participants.label', default: 'Participants')}</i>--%>
<%--						</a>--%>

                        <%-- <g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required=""  class="form-control"/>
                           --%> 

                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">tournament</a></li>
                        <li class="active">setting</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
 						     <div id="message"></div>
 							<div class="box box-info">
                                <div class="box-header">
                                    <h3 class="box-title">Belts</h3>
                                    <div class="box-tools pull-right">
                                        
                                    </div>
                                </div>
                                <div class="box-body">
                                    <g:render template="/belt/list"></g:render>
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    <div>
                                    	<a class="btn btn-primary" href="${createLink(controller: 'belt', action:'create')}"><i class="fa fa-plus"></i></a>
                                    </div>
                                </div><!-- /.box-footer-->
                            </div>
                            <div class="box box-info">
                                <div class="box-header">
                                    <h3 class="box-title">Combat Categories</h3>
                                    <div class="box-tools pull-right">
                                        
                                    </div>
                                </div>
                                <div class="box-body">
                                    <g:render template="/combatCategory/list"></g:render>
                                    
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                	<div>
                                    	<a class="btn btn-primary" href="${createLink(controller: 'combatCategory', action:'create')}"><i class="fa fa-plus"></i></a>
                                    </div>
                                </div><!-- /.box-footer-->
                            </div>
 						     <div class="box box-info">
                                <div class="box-header">
                                    <h3 class="box-title">Poomse Categories</h3>
                                    <div class="box-tools pull-right">
                                    </div>
                                </div>
                                <div class="box-body">
                                    <g:render template="/formCategory/list"></g:render>
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    <div>
                                    	<a class="btn btn-primary" href="${createLink(controller: 'formCategory', action:'create')}"><i class="fa fa-plus"></i></a>
                                    </div>
                                </div><!-- /.box-footer-->
                            </div>

                </section><!-- /.content -->
            </aside><!-- /.right-side -->
            
			
			

        </div><!-- ./wrapper -->

		<g:render template="/home/templates/footer"/>

		
        <script src="//cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
        
        
        <r:require modules="tree" />
        
        <script type="text/javascript">
        
<%--            function showForm(obj) {--%>
<%--                    --%>
<%--                    var $row = $(obj);--%>
<%--                    var uri =  $row.attr("uri");--%>
<%--                    console.log(uri);--%>
<%--                    //var options = {"remote":obj.data};--%>
<%--                    $('#myModal').modal({--%>
<%--                         'show':true,--%>
<%--                         'remote': uri  --%>
<%--                    });--%>
<%--            }--%>
            $(document).ready(function() {
<%--                var url = "${reloadUri}";--%>
<%----%>
<%--                $('#treeCategory').treegrid();--%>
<%----%>
                $('#tournament').on('change', function() {
                    //alert( this.value ); // or $(this).val()
                    goTo=url+"?tournamentId="+$(this).val();
                    window.location.href = goTo;
                });
<%----%>
<%--                var beltTable = $('#beltTable').DataTable();--%>
<%----%>
<%----%>
<%--                $("#beltSave").click(function(){--%>
<%--                    saveBelt("post");--%>
<%--                });--%>
<%----%>
<%--              --%>
<%----%>
<%--                --%>
<%--                --%>
<%--                $('#beltModal').on('hidden.bs.modal', function (e) {--%>
<%--                  $("#name").val("");--%>
<%--                });--%>
<%----%>
<%--                $('#myModal').on('shown.bs.modal', function (e) {--%>
<%--                    $("#update").click(function(){--%>
<%--                        saveBelt("put");--%>
<%--                    });  --%>
<%--                })--%>
<%----%>
<%--                function saveBelt(method) {--%>
<%--                    var id = $("#beltId").val();--%>
<%--                    var description = $("#description").val();--%>
<%--                    var tournamentId = $("#tournament").val();;--%>
<%--                    $.post("${createLink(controller: 'belt', action:'ajaxSave')}",--%>
<%--                              {--%>
<%--                                "id":id,--%>
<%--                                "description":description,--%>
<%--                                "tournamentId":tournamentId--%>
<%--                              },--%>
<%--                              function(data,status){--%>
<%--                                console.log ("status:"+status+"data:"+ JSON.stringify(data));--%>
<%--                                    if (data.status == 201) {--%>
<%--                                        $('#beltModal').modal('hide');--%>
<%--                                        $('#myModal').modal('hide');--%>
<%--                                        --%>
<%--                                        if (method == "post") {--%>
<%--                                            beltTable.row.add( [--%>
<%--                                                data.entity.description--%>
<%--                                            ] ).draw();    --%>
<%--                                        } else {--%>
<%----%>
<%--                                        }--%>
<%--                                        --%>
<%--                                        --%>
<%--                                        showSuccess(data);    --%>
<%--                                    } else {--%>
<%--                                        alert("status:"+status+"data:"+ JSON.stringify(data));    --%>
<%--                                    }--%>
<%--                                    --%>
<%--                             });--%>
<%--                }--%>
<%----%>
<%--                function showSuccess(data) {--%>
<%--                    $("#message").html('<div class="alert alert-success alert-dismissable"><i class="fa fa-check"></i><button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button><b>Success!</b> </div>');--%>
<%--                }--%>
            });
        </script>


    </body>
</html>