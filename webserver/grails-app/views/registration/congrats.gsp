<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Tournament Manager</title>
</head>
<body>

	<g:render template="/home/templates/nav" />

		<!-- Work -->
			<div class="wrapper style2">
				<article id="work">
					<header>
						
					</header>
					<div class="container">
						<div class="row">
							<div class="4u">
								<section class="box style1">
									<span class="icon featured fa-comments-o"></span>
									<h3>Hello ${userInstance.firstName}</h3>
									<p>${message}</p>
								</section>
							</div>
						</div>
					</div>
				</article>
			</div>


	<g:render template="/home/templates/footer" />

</body>
</html>
