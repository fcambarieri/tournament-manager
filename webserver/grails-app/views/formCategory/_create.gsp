
<div id="create-formCategory" class="content scaffold-create" role="main">
	<h1><g:message code="default.create.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="alert alert-${hasErrors(bean: formCategoryInstance, field: 'name', 'danger')}" role="alert">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${formCategoryInstance}">
		<div class="alert alert-danger">
			<ul class="errors" role="alert">
				<g:eachError bean="${formCategoryInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</div>
	</g:hasErrors>
	
	<g:form url="[resource:formCategoryInstance, action:'save']" >
		<fieldset class="form">
			<g:render template="form"/>
		</fieldset>
		<fieldset class="buttons">
			<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
		</fieldset>
	</g:form>
</div>
