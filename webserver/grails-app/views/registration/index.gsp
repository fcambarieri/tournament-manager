<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Registration</title>
<style type="text/css" media="screen">
#status {
	background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	width: 12em;
	float: left;
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
}

.ie6 #status {
	display: inline;
	/* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
	font-size: 0.9em;
	list-style-type: none;
	margin-bottom: 0.6em;
	padding: 0;
}

#status li {
	line-height: 1.3;
}

#status h1 {
	text-transform: uppercase;
	font-size: 1.1em;
	margin: 0 0 0.3em;
}

#page-body {
	margin: 2em 1em 1.25em 18em;
}

h2 {
	margin-top: 1em;
	margin-bottom: 0.3em;
	font-size: 1em;
}

p {
	line-height: 1.5;
	margin: 0.25em 0;
}

#controller-list ul {
	list-style-position: inside;
}

#controller-list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

@media screen and (max-width: 480px) {
	#status {
		display: none;
	}
	#page-body {
		margin: 0 1em 1em;
	}
	#page-body h1 {
		margin-top: 0;
	}
}
</style>
</head>
<body>

	<g:form action="save">
		<fieldset class="form">
			<div
				class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
				<label for="email"> <g:message code="user.email.label"
						default="Email" />

				</label>
				<g:textField name="email" value="${userInstance?.email}" />
			</div>

			<div
				class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
				<label for="firstName"> <g:message
						code="user.firstName.label" default="firstName" />

				</label>
				<g:textField name="firstName" value="${userInstance?.firstName}" />
			</div>
			<div
				class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
				<label for="lastName"> <g:message code="user.lastName.label"
						default="lastName" />

				</label>
				<g:textField name="lastName" value="${userInstance?.lastName}" />
			</div>
			<div
				class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
				<label for="password"> <g:message code="user.psw.label"
						default="Password" />

				</label>
				<g:textField name="password" value="${userInstance?.password}" />
			</div>
			<div
				class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} required">
				<label for="confirmPassword"> <g:message code="user.confirmPassword.label"
						default="Confirm password" />

				</label>
				<g:textField name="confirmPassword" value="${userInstance?.confirmPassword}" />
			</div>
		</fieldset>
		<fieldset class="buttons">
				<g:submitButton name="createAccount" class="save" value="${message(code: 'default.button.createAccount.label', default: 'Create Account')}" />
		</fieldset>
	</g:form>


</body>
</html>
