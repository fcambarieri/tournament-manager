<%@ page import="tournament.manager.CombatWeight" %>



<div class="fieldcontain ${hasErrors(bean: combatWeightInstance, field: 'minWeight', 'error')} required">
	<label for="minWeight">
		<g:message code="combatWeight.minWeight.label" default="Min Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minWeight" type="number" value="${combatWeightInstance.minWeight}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: combatWeightInstance, field: 'maxWeight', 'error')} required">
	<label for="maxWeight">
		<g:message code="combatWeight.maxWeight.label" default="Max Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxWeight" type="number" value="${combatWeightInstance.maxWeight}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: combatWeightInstance, field: 'combatCategory', 'error')} required">
	<label for="combatCategory">
		<g:message code="combatWeight.combatCategory.label" default="Combat Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="combatCategory" name="combatCategory.id" from="${tournament.manager.CombatCategory.list()}" optionKey="id" required="" value="${combatWeightInstance?.combatCategory?.id}" class="many-to-one"/>

</div>

