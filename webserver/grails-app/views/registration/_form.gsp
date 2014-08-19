<%@ page import="tournament.manager.auth.User"%>


<style type="text/css">
#errorContainer {
	display: none;
	overflow: auto;
	background-color: #FFDDDD;
	border: 1px solid #FF2323;
	padding-top: 1;
}

#errorContainer label {
	float: none;
	width: auto;
}

input.error {
	border: 1px solid #FF2323;
}
</style>

<div class="wrapper style2">
	<article id="registration" class="container small">
		<header>
			<div id="errorContainer" class="errors" role="alert">
				<p>&nbsp;Please correct the following errors and try again:</p>
				<ul />
			</div>
			<g:if test="${flash.message}">
				<div class="message">
					${flash.message}
				</div>
			</g:if>
			<g:hasErrors bean="${userInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${userInstance}" var="error">
						<li
							<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}" /></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
		</header>
		<div>
			<div class="row">
				<div class="12u">
					<g:form id="reg" name="reg" url="[controller:'registration',action:'save']">
						<fieldset class="form">
							<div>
								<div class="row">
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
										<g:textField id="firstName" name="firstName"
											value="${userInstance?.firstName}" placeholder="First Name" />
									</div>
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
										<g:textField id="lastName" name="lastName"
											value="${userInstance?.lastName}" placeholder="Last Name" />
									</div>
								</div>
								<div class="row">
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
										<g:textField name="username" value="${userInstance?.username}"
											placeholder="Username" />
									</div>
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
										<g:textField id="email" name="email"
											value="${userInstance?.email}" placeholder="Email" />
									</div>
								</div>
								<div class="row">
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
										<g:passwordField name="password"
											value="${userInstance?.password}" placeholder="Password" />
									</div>
									<div
										class="6u fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
										<g:passwordField name="confirmPassword"
											value="${userInstance?.confirmPassword}"
											placeholder="Confirm password" />
									</div>
								</div>
								<div class="row double">
									<div class="12u">
										<ul class="actions">
											<li><g:submitButton name="continue" class="save"
													value="${message(code: 'default.button.continue.label', default: 'Continue')}" /></li>
											<li><g:link controller="home" action="index">
													${message(code: 'default.button.cancel.label', default: 'Cancel')}
												</g:link></li>
										</ul>

									</div>
								</div>
							</div>
						</fieldset>
					</g:form>
				</div>
			</div>
		</div>
	</article>
</div>