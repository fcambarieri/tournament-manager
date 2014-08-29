<%@ page import="tournament.manager.CombatCategory"%>

<div
	class="form-group ${hasErrors(bean: combatCategoryInstance, field: 'name', 'has-error')}">
	<label for="name" class="col-sm-2 control-label"> <g:message
			code="combatCategory.name.label" default="Name" />
	</label>
	<div class="col-sm-10">
		<g:textField class="form-group" name="name" maxlength="60"
			value="${combatCategoryInstance?.name}" />
	</div>
</div>
<div
	class="form-group ${hasErrors(bean: combatCategoryInstance, field: 'minAge', 'has-error')} required">
	<label for="minAge" class="col-sm-2 control-label"> <g:message
			code="combatCategory.minAge.label" default="Min Age" />
	</label>
	<div class="col-sm-10">
		<g:field name="minAge" class="form-group" type="number"
			value="${combatCategoryInstance.minAge}" required="" />
	</div>
</div>
<div
	class="form-group ${hasErrors(bean: combatCategoryInstance, field: 'maxAge', 'has-error')} required">
	<label for="maxAge" class="col-sm-2 control-label"> <g:message
			code="combatCategory.maxAge.label" default="Max Age" />
	</label>
	<div class="col-sm-10">
		<g:field name="maxAge" class="form-group" type="number"
			value="${combatCategoryInstance.maxAge}" required="" />
	</div>
</div>
<div
	class="form-group ${hasErrors(bean: combatCategoryInstance, field: 'sex', 'has-error')} required">
	<label for="sex" class="col-sm-2 control-label"> <g:message
			code="combatCategory.sex.label" default="Sex" />
	</label>
	<div class="col-sm-4">
		<g:select name="sex" class="form-control"
			from="${tournament.manager.Sex?.values()}"
			keys="${tournament.manager.Sex.values()*.name()}" required=""
			value="${combatCategoryInstance?.sex?.name()}" />
	</div>
</div>
<div class="form-group ${hasErrors(bean: combatCategoryInstance, field: 'tournament', 'has-error')} required">

	<label for="tournament" class="col-sm-2 control-label"> <g:message
			code="combatCategory.tournament.label" default="Tournament" />
	</label>
	<div class="col-sm-4">
		<g:select id="tournament" class="form-control" name="tournament.id"
			from="${tournament.manager.Tournament.list()}" optionKey="id"
			required="" value="${combatCategoryInstance?.tournament?.id}"
			 />
	</div>
</div>

