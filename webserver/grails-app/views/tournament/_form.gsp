
 <div class="form-group">
     <div class="input-group">
         <span class="input-group-addon"><g:message code="tournament.name.label"
						default="Name" /></span>
          	<g:textField id="name" class="form-control" name="name" value="${tournamentInstance?.name}" placeholder="Name" />
     </div>
 </div>
<div class="form-group">
     <div class="input-group">
         <span class="input-group-addon"><g:message code="tournament.status.label" default="Status" /></span>
          <g:select name="status" class="form-control" from="${tournament.manager.TournamentStatus?.values()}"
					keys="${tournament.manager.TournamentStatus.values()*.name()}" required=""
					value="${tournamentInstance?.status?.name()}" />
     </div>
 </div>