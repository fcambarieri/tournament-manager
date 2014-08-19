<%@ page import="tournament.manager.FormCategory" %>



<div class="fieldcontain ${hasErrors(bean: formCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="formCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="60" value="${formCategoryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: formCategoryInstance, field: 'minAge', 'error')} required">
	<label for="minAge">
		<g:message code="formCategory.minAge.label" default="Min Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minAge" type="number" value="${formCategoryInstance.minAge}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: formCategoryInstance, field: 'maxAge', 'error')} required">
	<label for="maxAge">
		<g:message code="formCategory.maxAge.label" default="Max Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxAge" type="number" value="${formCategoryInstance.maxAge}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: formCategoryInstance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="formCategory.tournament.label" default="Tournament" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required="" value="${formCategoryInstance?.tournament?.id}" class="many-to-one"/>

</div>

