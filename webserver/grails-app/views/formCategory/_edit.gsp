<%@ page import="tournament.manager.FormCategory" %>

<div id="edit-formCategory" class="content scaffold-edit" role="main">
	<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
	<div class="message" role="status">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${formCategoryInstance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${formCategoryInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
	</g:hasErrors>
	<g:form url="[resource:formCategoryInstance, action:'update']" method="PUT" >
		<g:hiddenField name="version" value="${formCategoryInstance?.version}" />
		<fieldset class="form">
			<g:render template="form"/>
		</fieldset>
		<fieldset class="buttons">
			<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" class="btn btn-primary"/>
		</fieldset>
	</g:form>
</div>
