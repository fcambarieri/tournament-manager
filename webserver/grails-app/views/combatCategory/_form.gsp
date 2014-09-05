<%@ page import="tournament.manager.CombatCategory"%>
<g:hiddenField name="id" value="${instance?.id}" />

<div class="form-group ${hasErrors(bean: instance, field: 'tournament', 'has-error')} required">

	<label for="tournament" class="control-label"> <g:message
			code="combatCategory.tournament.label" default="Tournament" />
	</label>
		<g:select id="tournament" class="form-control" name="tournament.id"
			from="${tournaments}" optionKey="id"
			required="" value="${instance?.tournament?.id}"
			 />
</div>

<div
	class="form-group ${hasErrors(bean: instance, field: 'name', 'has-error')}">
	<label for="name" class="control-label"> <g:message
			code="combatCategory.name.label" default="Name" />
	</label>
		<g:textField class="form-control" name="name" maxlength="60"
			value="${instance?.name}" />
</div>
<div
	class="form-group ${hasErrors(bean: instance, field: 'minAge', 'has-error')} required">
	<label for="minAge" class="col-sm-2 control-label"> <g:message
			code="combatCategory.minAge.label" default="Min Age" />
	</label>
		<g:field name="minAge" class="form-control" type="number"
			value="${instance.minAge}" required="" />
</div>
<div
	class="form-group ${hasErrors(bean: instance, field: 'maxAge', 'has-error')} required">
	<label for="maxAge" class="control-label"> <g:message
			code="combatCategory.maxAge.label" default="Max Age" />
	</label>
		<g:field name="maxAge" class="form-control" type="number"
			value="${instance.maxAge}" required="" />
</div>
<div
	class="form-group ${hasErrors(bean: instance, field: 'sex', 'has-error')} required">
	<label for="sex" class="control-label"> <g:message
			code="combatCategory.sex.label" default="Sex" />
	</label>
		<g:select name="sex" class="form-control"
			from="${tournament.manager.Sex?.values()}"
			keys="${tournament.manager.Sex.values()*.name()}" required=""
			value="${instance?.sex?.name()}" />
</div>


