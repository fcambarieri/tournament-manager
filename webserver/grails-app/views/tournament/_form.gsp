
<fieldset class="form">
	<div>
		<div class="row">
			<div
				class="6u fieldcontain ${hasErrors(bean: tournamentInstance, field: 'name', 'error')} required">
				<label for="name"> <g:message code="tournament.name.label"
						default="Name" /> <span class="required-indicator">*</span>
				</label>
				<g:textField id="name" name="name"
					value="${tournamentInstance?.name}" placeholder="Name" />
			</div>
			<div
				class="6u fieldcontain fieldcontain ${hasErrors(bean: tournamentInstance, field: 'status', 'error')} required">
				<label for="status"> <g:message
						code="tournament.status.label" default="Status" /> <span
					class="required-indicator">*</span>
				</label>
				<g:select name="status" from="${tournament.manager.TournamentStatus?.values()}"
					keys="${tournament.manager.TournamentStatus.values()*.name()}" required=""
					value="${tournamentInstance?.status?.name()}" />
			</div>
		</div>
	</div>
</fieldset>
