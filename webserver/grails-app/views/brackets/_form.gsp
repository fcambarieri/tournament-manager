<%@ page import="tournament.manager.Brackets" %>



<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'combatWeight', 'error')} ">
	<label for="combatWeight">
		<g:message code="brackets.combatWeight.label" default="Combat Weight" />
		
	</label>
	<g:select id="combatWeight" name="combatWeight.id" from="${tournament.manager.CombatWeight.list()}" optionKey="id" value="${bracketsInstance?.combatWeight?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'formCategory', 'error')} ">
	<label for="formCategory">
		<g:message code="brackets.formCategory.label" default="Form Category" />
		
	</label>
	<g:select id="formCategory" name="formCategory.id" from="${tournament.manager.FormCategory.list()}" optionKey="id" value="${bracketsInstance?.formCategory?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'belt', 'error')} required">
	<label for="belt">
		<g:message code="brackets.belt.label" default="Belt" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="belt" name="belt.id" from="${tournament.manager.Belt.list()}" optionKey="id" required="" value="${bracketsInstance?.belt?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="brackets.externalId.label" default="External Id" />
		
	</label>
	<g:textField name="externalId" value="${bracketsInstance?.externalId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="brackets.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${bracketsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="brackets.status.label" default="Status" />
		
	</label>
	<g:textField name="status" value="${bracketsInstance?.status}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="brackets.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${bracketsInstance?.url}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bracketsInstance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="brackets.tournament.label" default="Tournament" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required="" value="${bracketsInstance?.tournament?.id}" class="many-to-one"/>

</div>

