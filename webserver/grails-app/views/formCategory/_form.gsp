<%@ page import="tournament.manager.FormCategory" %>

<g:hiddenField name="id" value="${instance?.id}" />

<div class="form-group ${hasErrors(bean: instance, field: 'name', 'has-error')} ">
	<label for="name">
		<g:message code="formCategory.name.label" default="Name" />
		
	</label>
	<g:textField name="name"  class="form-control"maxlength="60" value="${instance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'minAge', 'has-error')} required">
	<label for="minAge">
		<g:message code="formCategory.minAge.label" default="Min Age" />
	</label>
	<g:field name="minAge"  class="form-control" type="number" value="${instance.minAge}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'maxAge', 'has-error')} required">
	<label for="maxAge">
		<g:message code="formCategory.maxAge.label" default="Max Age" />
	</label>
	<g:field name="maxAge"  class="form-control" type="number" value="${instance.maxAge}" required=""/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'tournament', 'has-error')} required">
	<label for="tournament">
		<g:message code="formCategory.tournament.label" default="Tournament" />
	</label>
	<g:select id="tournament"  class="form-control" name="tournament.id" from="${tournaments}" optionKey="id" required="" value="${instance?.tournament?.id}" />

</div>

