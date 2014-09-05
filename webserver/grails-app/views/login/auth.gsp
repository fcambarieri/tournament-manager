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

        <g:render template="/home/templates/nav"/>
<%--			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">--%>
<%--		        <div class="container">--%>
<%--		            <div class="navbar-header page-scroll">--%>
<%--		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">--%>
<%--		                    <span class="sr-only">Toggle navigation</span>--%>
<%--		                    <span class="icon-bar"></span>--%>
<%--		                    <span class="icon-bar"></span>--%>
<%--		                    <span class="icon-bar"></span>--%>
<%--		                </button>--%>
<%--		            </div>--%>
<%--	            </div>--%>
<%--	            <!-- Collect the nav links, forms, and other content for toggling -->--%>
<%--            <div class="collapse navbar-collapse navbar-ex1-collapse">--%>
<%--                <ul class="nav navbar-nav">--%>
<%--                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->--%>
<%--                    <li class="hidden">--%>
<%--                        <a class="page-scroll" href="#page-top"></a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a class="page-scroll" href="#about">Home</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a class="page-scroll" href="#services">Services</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a class="page-scroll" href="#contact">Contact</a>--%>
<%--                    </li>--%>
<%----%>
<%--                        <li><g:link controller="registration" action="index">${message(code: 'default.link.signup.label', default: 'Sign up')}</g:link></li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--            <!-- /.navbar-collapse -->--%>
<%--            </nav>--%>
        
       	  <div class="form-box" id="login-box">
            <div class="header">Sign In</div>
             <!-- <g:message code="springSecurity.login.header"/> -->
            <g:if test='${flash.message}'>
                <div class="alert alert-danger alert-dismissable">
                    ${flash.message}
                </div>
           </g:if>
            <form method="POST" action="${resource(file: 'j_spring_security_check')}">
                <div class="body bg-gray">
                    <div class="form-group">
                    	<g:textField name="j_username" placeholder="Username" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <input type="password" name="j_password" class="form-control" placeholder="Password"/>
                    </div>          
                    <div class="form-group">
                        <input type="checkbox" name="_spring_security_remember_me"/> Remember me
                    </div>
                </div>
                <div class="footer">                                                               
                    <button type="submit" class="btn bg-blue btn-block">Sign me in</button>  
                    
                    <p><a href="#">I forgot my password</a></p>
                    
                    <g:link controller="registration" action="index" class="text-center">${message(code: 'default.link.signup.label', default: 'Sign up')}</g:link>
<%--                    <a href="register.html" class="text-center">Register a new membership</a>--%>
                </div>
            </form>

            <div class="margin text-center">
                <span>Sign in using social networks</span>
                <br/>
                <button class="btn bg-light-blue btn-circle"><i class="fa fa-facebook"></i></button>
                <button class="btn bg-aqua btn-circle"><i class="fa fa-twitter"></i></button>
                <button class="btn bg-red btn-circle"><i class="fa fa-google-plus"></i></button>

            </div>
        </div>

    </body>
</html>