<%@ page import="tournament.manager.CombatCategory" %>



<div class="fieldcontain ${hasErrors(bean: combatCategoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="combatCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="60" value="${combatCategoryInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: combatCategoryInstance, field: 'minAge', 'error')} required">
	<label for="minAge">
		<g:message code="combatCategory.minAge.label" default="Min Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minAge" type="number" value="${combatCategoryInstance.minAge}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: combatCategoryInstance, field: 'maxAge', 'error')} required">
	<label for="maxAge">
		<g:message code="combatCategory.maxAge.label" default="Max Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxAge" type="number" value="${combatCategoryInstance.maxAge}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: combatCategoryInstance, field: 'sex', 'error')} required">
	<label for="sex">
		<g:message code="combatCategory.sex.label" default="Sex" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sex" from="${tournament.manager.Sex?.values()}" keys="${tournament.manager.Sex.values()*.name()}" required="" value="${combatCategoryInstance?.sex?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: combatCategoryInstance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="combatCategory.tournament.label" default="Tournament" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required="" value="${combatCategoryInstance?.tournament?.id}" class="many-to-one"/>

</div>

