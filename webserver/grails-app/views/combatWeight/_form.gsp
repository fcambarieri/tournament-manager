<%@ page import="tournament.manager.CombatWeight" %>

<g:hiddenField name="id" value="${instance?.id}" />
<g:hiddenField name="combatCategoryId" value="${instance?.combatCategory != null ? instance.combatCategory.id : combatCategoryId}" />
<div class="form-group ${hasErrors(bean: instance, field: 'minWeight', 'has-error')} required">
	<label for="minWeight">
		<g:message code="combatWeight.minWeight.label" default="Min Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field class="form-control" name="minWeight" type="number" value="${instance.minWeight}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'maxWeight', 'has-error')} required">
	<label for="maxWeight">
		<g:message code="combatWeight.maxWeight.label" default="Max Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field  class="form-control" name="maxWeight" type="number" value="${instance.maxWeight}" required=""/>

</div>


<%-- <div class="form-group ${hasErrors(bean: instance, field: 'combatCategory', 'has-error')} required">
	<label for="combatCategory">
		<g:message code="combatWeight.combatCategory.label" default="Combat Category" />
	</label>
	<g:select class="form-control" id="combatCategory" name="combatCategory.id" from="${tournament.manager.CombatCategory.list()}" optionKey="id" required="" value="${instance?.combatCategory?.id}" class="many-to-one"/>

</div> --%>

