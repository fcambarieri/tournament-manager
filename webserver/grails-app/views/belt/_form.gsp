<%@ page import="tournament.manager.Belt" %>



<div class="fieldcontain ${hasErrors(bean: beltInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="belt.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" maxlength="30" required="" value="${beltInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: beltInstance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="belt.tournament.label" default="Tournament" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required="" value="${beltInstance?.tournament?.id}" class="many-to-one"/>

</div>

