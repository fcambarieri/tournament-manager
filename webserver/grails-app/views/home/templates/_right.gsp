			<!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       
                        <small>it all starts here</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Examples</a></li>
                        <li class="active">Blank page</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
 						${request.getSession().getServletContext().getRealPath("/")}
                        ${request.getSession().getServletContext().getContextPath()}
						<button id="t-btn-show" type="button">Top</button>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
            