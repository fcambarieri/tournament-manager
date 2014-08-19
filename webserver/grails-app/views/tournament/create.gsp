<%@ page import="tournament.manager.Tournament"%>
<%@page import="tournament.manager.TournamentStatus"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Tournament Manager</title>
</head>
<body>

	<g:render template="/home/templates/nav" />

	<div class="wrapper style1">
		<article id="tournament" class="container small">
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
				<g:hasErrors bean="${tournamentInstance}">
					<ul class="errors" role="alert">
						<g:eachError bean="${tournamentInstance}" var="error">
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
						<g:form name="tournament" id="tournament"
							url="[controller:'tournament',action:'save']">
							<g:render template="/tournament/form" />
							<div class="row double">
								<div class="12u">
									<ul class="actions">
										<li><g:submitButton name="continue" class="save"
												value="${message(code: 'default.button.save.label', default: 'Save')}" /></li>
										<li><g:link controller="tournament" action="index">
												${message(code: 'default.button.cancel.label', default: 'Cancel')}
											</g:link></li>
									</ul>

								</div>
							</div>
						</g:form>
					</div>
				</div>
			</div>
			<div id="formContainer"></div>
		</article>
	</div>
	<g:render template="/home/templates/footer" />


	<script type="text/javascript">
	 $(function(){

         $('#tournament').validate({
             rules: {
            	 name: "required"
             },
             messages: {
                 name: "${message(code: 'error.name.label', default: 'Please enter a tournament name')}",
             },
             errorContainer: $('#errorContainer'),
             errorLabelContainer: $('#errorContainer ul'),
             wrapper: 'li'
         });

     });
     
	</script>
</body>
</html>
