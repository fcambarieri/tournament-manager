<%@ page import="tournament.manager.Participants" %>



<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'combatStatus', 'error')} ">
	<label for="combatStatus">
		<g:message code="participants.combatStatus.label" default="Combat Status" />
		
	</label>
	<g:textField name="combatStatus" value="${participantsInstance?.combatStatus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'formStatus', 'error')} ">
	<label for="formStatus">
		<g:message code="participants.formStatus.label" default="Form Status" />
		
	</label>
	<g:textField name="formStatus" value="${participantsInstance?.formStatus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'formCategory', 'error')} ">
	<label for="formCategory">
		<g:message code="participants.formCategory.label" default="Form Category" />
		
	</label>
	<g:select id="formCategory" name="formCategory.id" from="${tournament.manager.FormCategory.list()}" optionKey="id" value="${participantsInstance?.formCategory?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'combatCategory', 'error')} ">
	<label for="combatCategory">
		<g:message code="participants.combatCategory.label" default="Combat Category" />
		
	</label>
	<g:select id="combatCategory" name="combatCategory.id" from="${tournament.manager.CombatCategory.list()}" optionKey="id" value="${participantsInstance?.combatCategory?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'combatWeight', 'error')} ">
	<label for="combatWeight">
		<g:message code="participants.combatWeight.label" default="Combat Weight" />
		
	</label>
	<g:select id="combatWeight" name="combatWeight.id" from="${tournament.manager.CombatWeight.list()}" optionKey="id" value="${participantsInstance?.combatWeight?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'belt', 'error')} required">
	<label for="belt">
		<g:message code="participants.belt.label" default="Belt" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="belt" name="belt.id" from="${tournament.manager.Belt.list()}" optionKey="id" required="" value="${participantsInstance?.belt?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'student', 'error')} required">
	<label for="student">
		<g:message code="participants.student.label" default="Student" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="student" name="student.id" from="${tournament.manager.Students.list()}" optionKey="id" required="" value="${participantsInstance?.student?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: participantsInstance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="participants.tournament.label" default="Tournament" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tournament" name="tournament.id" from="${tournament.manager.Tournament.list()}" optionKey="id" required="" value="${participantsInstance?.tournament?.id}" class="many-to-one"/>

</div>

