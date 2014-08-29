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
                                    
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="Combat Category" /></g:link></li>
            </ul>
        </div>
        <div id="list-combatCategory" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
            <thead>
                    <tr>
                    
                        <g:sortableColumn property="name" title="${message(code: 'combatCategory.name.label', default: 'Name')}" />
                    
                        <g:sortableColumn property="minAge" title="${message(code: 'combatCategory.minAge.label', default: 'Min Age')}" />
                    
                        <g:sortableColumn property="maxAge" title="${message(code: 'combatCategory.maxAge.label', default: 'Max Age')}" />
                    
                        <g:sortableColumn property="sex" title="${message(code: 'combatCategory.sex.label', default: 'Sex')}" />
                    
                        <th><g:message code="combatCategory.tournament.label" default="Tournament" /></th>
                    
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
                    
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${combatCategoryInstanceCount ?: 0}" />
            </div>
        </div>







                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    
                                </div><!-- /.box-footer-->
                            </div>
                            

                </section><!-- /.content -->
            </aside><!-- /.right-side -->

        </div><!-- ./wrapper -->

		<g:render template="/home/templates/footer"/>
		
    </body>
</html>