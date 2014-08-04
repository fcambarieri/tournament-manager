<!DOCTYPE html>
<html>
<body>
	<div>
		<g:form name="loginForm" url="[action:'doLogin',controller:'login']"
			method="post">
			<div>
				<label for='email'>Email:</label>
				<g:textField name="email" value="${email}" />
				<label for='password'>Password:</label>
				<g:passwordField name="password" value="${password}" />
				<input type="submit" />
			</div>
		</g:form>
	</div>
</body>
</html>