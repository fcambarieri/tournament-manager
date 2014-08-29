<%@ page import="tournament.manager.auth.User"%>




<%--	<g:if test="${flash.message}">--%>
<%--		<div class="message">--%>
<%--			${flash.message}--%>
<%--		</div>--%>
<%--	</g:if>--%>
<g:hasErrors bean="${userInstance}">
	<div class="alert alert-danger alert-dismissable">
		<i class="fa fa-ban"></i>
		<button type="button" class="close" data-dismiss="alert"
			aria-hidden="true">×</button>
		<ul class="has-error" role="alert">
			<g:eachError bean="${userInstance}" var="error">
				<li
					<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
						error="${error}" /></li>
			</g:eachError>
		</ul>
	</div>
</g:hasErrors>



<div class="form-box" id="login-box">
	<div class="header">Register New Membership</div>
	<g:form id="reg" name="reg"
		url="[controller:'registration',action:'save']">
		<div class="body bg-gray">
			<div class="form-group has-error">
				<g:textField id="firstName" class="form-control" name="firstName"
					value="${userInstance?.firstName}" placeholder="First Name" />
			</div>
			<div class="form-group">
				<g:textField id="lastName" name="lastName" class="form-control"
					value="${userInstance?.lastName}" placeholder="Last Name" />
			</div>
			<div class="form-group">
				<g:textField id="username" name="username" class="form-control"
					value="${userInstance?.username}" placeholder="Username" />
			</div>
			<div class="form-group">
				<g:textField id="email" name="email" class="form-control"
					value="${userInstance?.email}" placeholder="Email" />
			</div>
			<div class="form-group">
				<g:passwordField name="password" class="form-control"
					value="${userInstance?.password}" placeholder="Password" />
			</div>
			<div class="form-group">
				<g:passwordField name="confirmPassword" class="form-control"
					value="${userInstance?.confirmPassword}"
					placeholder="Confirm password" />
			</div>
		</div>
		<div class="footer">

			<g:submitButton name="continue" class="btn bg-olive btn-block"
				value="${message(code: 'default.button.continue.label', default: 'Continue')}" />

			<g:link controller="login" action="auth" class="text-center"
				action="index">
				${message(code: 'default.alreadyMembership.label', default: 'I already have a membership')}
			</g:link>


		</div>
	</g:form>

	<div class="margin text-center">
		<span>Register using social networks</span> <br />
		<button class="btn bg-light-blue btn-circle">
			<i class="fa fa-facebook"></i>
		</button>
		<button class="btn bg-aqua btn-circle">
			<i class="fa fa-twitter"></i>
		</button>
		<button class="btn bg-red btn-circle">
			<i class="fa fa-google-plus"></i>
		</button>

	</div>
</div>


