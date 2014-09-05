<%@ page import="tournament.manager.Belt" %>
<g:hiddenField name="id" value="${instance?.id}" />
<div
	class="form-group ${hasErrors(bean: instance, field: 'name', 'has-error')}">
     
         <label for="description">
			<g:message code="belt.description.label" default="Description" />
		</label>
          <g:textField id="description" class="form-control" name="description" maxlength="30" required="" value="${beltInstance?.description}"/>
 </div>

<div class="form-group ${hasErrors(bean: instance, field: 'tournament', 'has-error')} required">

	<label for="tournament"> <g:message
			code="combatCategory.tournament.label" default="Tournament" />
	</label>
	
		<g:select id="tournament" class="form-control" name="tournament.id"
			from="${tournaments}" optionKey="id"
			required="" value="${instance?.tournament != null ? instance.tournament.id : tournamentSelected?.id}"
			 />
</div>

