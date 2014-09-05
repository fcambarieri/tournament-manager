<!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
<%--            <div class="collapse navbar-collapse navbar-ex1-collapse">--%>
			<div class="navbar navbar-static-top" role="navigation">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>

                    <sec:ifNotLoggedIn>
                        <li><g:link controller="registration" action="index">${message(code: 'default.link.signup.label', default: 'Sign up')}</g:link></li>
                    
                    	<g:if test="${loginLink}">
                    		<li><g:link controller="login" action="auth">${message(code: 'default.link.sigin.label', default: 'Sign in')}</g:link></li>
                    	</g:if>    
                        
                    </sec:ifNotLoggedIn>

                    <sec:ifLoggedIn>
                        <li>
                            <g:link controller="tournament" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.admin.label', default: 'Admin')}</g:link>
                        </li>

<%--                        <li>--%>
                        
                        <div class="navbar-right">
		                    <ul class="nav navbar-nav">
		                        <!-- User Account: style can be found in dropdown.less -->
		                        <li class="dropdown user user-menu">
		                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                                <i class="glyphicon glyphicon-user"></i>
		                                <span>
		                                	 <sec:username/> <i class="caret"></i>
		                                </span>
		                            </a>
		                            <ul class="dropdown-menu">
		                                <!-- User image -->
		                                <li class="user-header bg-light-blue">
		                                    <img src="../../img/avatar3.png" class="img-circle" alt="User Image">
		                                    <p>
		                                        <sec:username/> 
		
		                                    </p>
		                                </li>
		                                <!-- Menu Body -->
		                                
		                                <!-- Menu Footer-->
		                                <li class="user-footer">
		                                    <div class="pull-left">
		                                        <a href="#" class="btn btn-default btn-flat">Profile</a>
		                                    </div>
		                                    <div class="pull-right">
		                                    	<mat:logout class="page-scroll"/>
<%--		                                        <form name="logoutForm" method="POST" action="/tournament-manager/j_spring_security_logout"><input type="hidden" name="" value=""><a href="javascript:document.logoutForm.submit()" class="btn btn-default btn-flat">Logout</a></form> --%>
		                                    </div>
		                                </li>
		                            </ul>
		                        </li>
		                    </ul>
		                </div>
                        
<%--                            <i class="glyphicon glyphicon-user"></i>--%>
<%--                            <span><sec:username/> </span>--%>
<%--                            <mat:logout class="page-scroll"/>--%>
                            
                            
                            
                            
                            
                            
<%--                        </li>--%>

                    </sec:ifLoggedIn>


                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>