
			<!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
<%--                            <img src="${request.getSession().getServletContext().getContextPath()}/img/avatar3.png" class="img-circle" alt="User Image" />--%>
 							<mat:imgAvatar class="img-circle"/>
                        </div>
                        <div class="pull-left info">
<%--                            <p>Hello, <sec:username/></p>--%>
							<p><mat:welcome/></p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
<%--                    <form action="#" method="get" class="sidebar-form">--%>
<%--                        <div class="input-group">--%>
<%--                            <input type="text" name="q" class="form-control" placeholder="Search..."/>--%>
<%--                            <span class="input-group-btn">--%>
<%--                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>--%>
<%--                            </span>--%>
<%--                        </div>--%>
<%--                    </form>--%>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li>
                            <a href="../../index.html">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
<%--                        <li>--%>
<%--                            <a href="../widgets.html">--%>
<%--                                <i class="fa fa-th"></i> <span>Widgets</span> <small class="badge pull-right bg-green">new</small>--%>
<%--                            </a>--%>
<%--                        </li>--%>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i>
                                <span>${message(code: 'default.link.tournament.label', default: 'Tournament')}</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><g:link controller="tournament" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.tournament.label', default: 'Tournament')}</g:link></li>
                                <li><g:link controller="tournament" action="settings"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.settings.label', default: 'Settings')}</g:link></li>
<%--                                <li><g:link controller="participants" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.participants.label', default: 'Participants')}</g:link></li>--%>
<%--                                <li><g:link controller="tournament" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.brackets.label', default: 'Brackets')}</g:link></li>--%>
                            </ul>
                        </li>
                        <li><g:link controller="participants" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.participants.label', default: 'Participants')}</g:link></li>
                        <li><g:link controller="brackets" action="potencialBrackets"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.brackets.label', default: 'Brackets')}</g:link></li>
                        <li><g:link controller="schools" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.school.label', default: 'Schools')}</g:link></li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-laptop"></i>
                                <span>${message(code: 'default.link.schools.label', default: 'Schools')}</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><g:link controller="schools" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.school.label', default: 'Schools')}</g:link></li>
                                <li><g:link controller="participants" action="index"><i class="fa fa-angle-double-right"></i> ${message(code: 'default.link.participants.label', default: 'Participants')}</g:link></li>
                            </ul>
                        </li>
<%--                        <li class="treeview">--%>
<%--                            <a href="#">--%>
<%--                                <i class="fa fa-edit"></i> <span>Forms</span>--%>
<%--                                <i class="fa fa-angle-left pull-right"></i>--%>
<%--                            </a>--%>
<%--                            <ul class="treeview-menu">--%>
<%--                                <li><a href="../forms/general.html"><i class="fa fa-angle-double-right"></i> General Elements</a></li>--%>
<%--                                <li><a href="../forms/advanced.html"><i class="fa fa-angle-double-right"></i> Advanced Elements</a></li>--%>
<%--                                <li><a href="../forms/editors.html"><i class="fa fa-angle-double-right"></i> Editors</a></li>--%>
<%--                            </ul>--%>
<%--                        </li>--%>
<%--                        <li class="treeview">--%>
<%--                            <a href="#">--%>
<%--                                <i class="fa fa-table"></i> <span>Tables</span>--%>
<%--                                <i class="fa fa-angle-left pull-right"></i>--%>
<%--                            </a>--%>
<%--                            <ul class="treeview-menu">--%>
<%--                                <li><a href="../tables/simple.html"><i class="fa fa-angle-double-right"></i> Simple tables</a></li>--%>
<%--                                <li><a href="../tables/data.html"><i class="fa fa-angle-double-right"></i> Data tables</a></li>--%>
<%--                            </ul>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="../calendar.html">--%>
<%--                                <i class="fa fa-calendar"></i> <span>Calendar</span>--%>
<%--                                <small class="badge pull-right bg-red">3</small>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li>--%>
<%--                            <a href="../mailbox.html">--%>
<%--                                <i class="fa fa-envelope"></i> <span>Mailbox</span>--%>
<%--                                <small class="badge pull-right bg-yellow">12</small>--%>
<%--                            </a>--%>
<%--                        </li>--%>
<%--                        <li class="treeview active">--%>
<%--                            <a href="#">--%>
<%--                                <i class="fa fa-folder"></i> <span>Examples</span>--%>
<%--                                <i class="fa fa-angle-left pull-right"></i>--%>
<%--                            </a>--%>
<%--                            <ul class="treeview-menu">--%>
<%--                                <li><a href="invoice.html"><i class="fa fa-angle-double-right"></i> Invoice</a></li>--%>
<%--                                <li><a href="login.html"><i class="fa fa-angle-double-right"></i> Login</a></li>--%>
<%--                                <li><a href="register.html"><i class="fa fa-angle-double-right"></i> Register</a></li>--%>
<%--                                <li><a href="lockscreen.html"><i class="fa fa-angle-double-right"></i> Lockscreen</a></li>--%>
<%--                                <li><a href="404.html"><i class="fa fa-angle-double-right"></i> 404 Error</a></li>--%>
<%--                                <li><a href="500.html"><i class="fa fa-angle-double-right"></i> 500 Error</a></li>--%>
<%--                                <li class="active"><a href="blank.html"><i class="fa fa-angle-double-right"></i> Blank Page</a></li>--%>
<%--                            </ul>--%>
<%--                        </li>--%>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>