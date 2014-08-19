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
	<script type="text/javascript">

	 $(function(){

        $('#reg').validate({
            rules: {
                firstName: "required",
                lastName: "required",
                email: {
                    	email: true
               	 	required: true
                    }
                username: "required",
                password: {
                    required: true,
                	minlength: 5
                },
                confirPassword: "required"
            },
            messages: {
                firstName: "Please enter your first name.",
                lastName: "Please enter your last name.",
                username: "Please enter your username.",
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                email: "Please enter a valid email address",
                confirmPassword: "Please enter confirmPassword.",
                
            },
            errorContainer: $('#errorContainer'),
            errorLabelContainer: $('#errorContainer ul'),
            wrapper: 'li'
        });

    });
	</script>
</body>
</html>
