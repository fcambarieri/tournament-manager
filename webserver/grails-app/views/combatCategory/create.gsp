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
                        <li><a href="#">${message(code: 'combatCategory.title.label', default: 'Combat Category')}</a></li>
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
 						
 						
 							 <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">${message(code: 'combatCategory.title.label', default: 'Combat Category')}</h3>
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                
                                <g:form url="[resource:combatCategoryInstance, action:'save']" class="form-horizontal" role="form" >
                                    <div class="box-body">
                                    	
                                       <g:if test="${flash.message}">
                                            <div class="message" role="status">${flash.message}</div>
                                        </g:if>
                                        <g:hasErrors bean="${combatCategoryInstance}">
                                        <div class="alert alert-danger" role="alert">
	                                        <ul class="errors" role="alert">
	                                            <g:eachError bean="${combatCategoryInstance}" var="error">
	                                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	                                            </g:eachError>
	                                        </ul>
                                        </div>
                                        </g:hasErrors>
                                        
                                                <g:render template="form"/>
                                      
                                        
                                    </div><!-- /.box-body -->

                                   	 <div class="box-footer">
                                   	 
	                                   	  <div class="form-group">
	    										<div class="col-sm-offset-2 col-sm-10">
	      										<g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.create.label', default: 'Create')}" />
	      										<g:link name="list" class="btn btn-default">${message(code: 'default.button.cancel.label', default: 'Cancel')}</g:link>
	    										</div>
	  									</div>
                                    </div>
                        		</g:form>        	
                            </div>
        				


                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                    
                                </div><!-- /.box-footer-->
                           

		<g:render template="/home/templates/footer"/>
		
    </body>
</html>