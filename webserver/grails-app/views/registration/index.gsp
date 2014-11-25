<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main"/>
<title>Tournament Manager</title>
</head>
 <body class="skin-blue">

	<g:render template="/home/templates/nav"/>
	
	<g:render template="/registration/form" />
	
<%--	<g:render template="/home/templates/footer" />--%>
	
<%--	<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>--%>
	
	<g:render template="/home/templates/footer"/>
	
	<script type="text/javascript">

	 $(function(){

        $('#reg').validate({
            rules: {
                firstName: "required",
                lastName: "required",
                email: {
                    	email: true,
               	 		required: true
                    },
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
