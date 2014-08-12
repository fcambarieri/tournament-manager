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
				<h2>
					${message(code: 'default.title.registration.label', default: 'Sign up')}
				</h2>
			</header>
			<g:render template="/registration/form" />
		</article>
	</div>
	<g:render template="/home/templates/footer" />
</body>
</html>
