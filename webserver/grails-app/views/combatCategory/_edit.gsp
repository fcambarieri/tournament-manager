<%@ page import="tournament.manager.CombatCategory" %>
<div id="edit-combatCategory" class="content scaffold-edit" role="main">
	<g:hasErrors bean="${combatCategoryInstance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${combatCategoryInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
	</g:hasErrors>
	<g:form url="[resource:combatCategoryInstance, action:'update']" method="PUT" >
		<g:hiddenField name="version" value="${combatCategoryInstance?.version}" />
		<fieldset class="form">
			<g:render template="form"/>
		</fieldset>
		<fieldset class="buttons">
			<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" class="btn btn-primary"/>
		</fieldset>
	</g:form>
</div>