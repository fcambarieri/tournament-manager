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
                        <small>
                        	
                        </small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">${controller}</a></li>
                        <li class="active">${action}</li>
                    </ol>
                </section>
				
                <!-- Main content -->
                <section class="content">
<%--                	<div class="row">--%>
 							<div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">${title}</h3>
<%--                                    <div class="box-tools pull-right">--%>
<%--                                        <div class="label bg-aqua">Label</div>--%>
<%--                                    </div>--%>
                                </div>
                                <div id="message"  role="alert"></div>
                       			<g:if test="${flash.errorMessage}">
									<div class="alert alert-danger" role="alert">${flash.errorMessage}</div>
								</g:if> 
								
								<g:if test="${flash.message}">
									<div class="alert alert-success" role="alert">${flash.message}</div>
								</g:if>
								
                               	<g:render template="${templateName}"/>    
	
                            </div>
<%--					</div>--%>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->

        </div><!-- ./wrapper -->

		<g:render template="/home/templates/footer"/>
		
		<g:if test="${scriptName}"> 
			<g:render template="${scriptName}"/>
		</g:if>
		
    </body>
</html>