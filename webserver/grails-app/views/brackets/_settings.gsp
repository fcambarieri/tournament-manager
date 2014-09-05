<%@ page import="tournament.manager.Brackets" %>
<%@ page import="tournament.manager.BracketKey" %>
BracketKey

<div class="form-group">
	<label for="tournament">
		<g:message code="participants.tournament.label" default="Tournament" />
	</label>
	<g:select id="tournament" class="form-control" name="tournament.id" from="${tournaments}" optionKey="id" required="" value="${instance?.tournament?.id}" noSelection="['null': '--select tournament--']" />
</div>

 <div class="box box-info">
    <div class="box-header">
        <h3 class="box-title"><g:message code="participants.tournament.label" default="Tournament" /></h3>
        <div class="box-tools pull-right">
            
        </div>
    </div>
    <div class="box-body">
        
        <div id="list-combat-brackets" class="content scaffold-list" role="main">
				<table>
					<thead>
							<tr>
							
								<th><g:message code="brackets.belt.label" default="Belt" /></th>
							
								<th><g:message code="brackets.combatWeight.label" default="Combat Weight" /></th>
							
								<th><g:message code="default.entity.schools.title" default="School" /></th>
								
								<g:sortableColumn property="name" title="${message(code: 'participants.name.label', default: 'Name')}" />
			
								<g:sortableColumn property="lastName" title="${message(code: 'participants.lastName.label', default: 'Last Name')}" />	
								
								<th><g:message code="default.count" default="Count"/></th>
								
								<th><g:message code="defualt.action.label" default="Actions"/></th>
								
<%--								<g:sortableColumn property="externalId" title="${message(code: 'brackets.externalId.label', default: 'External Id')}" />--%>
<%--							--%>
<%--								<g:sortableColumn property="name" title="${message(code: 'brackets.name.label', default: 'Name')}" />--%>
<%--							--%>
<%--								<g:sortableColumn property="status" title="${message(code: 'brackets.status.label', default: 'Status')}" />--%>
							
							</tr>
						</thead>
						<tbody>
						<g:each in="${combatParticipants}" status="i" var="bracketMap">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td>${bracketMap.key.belt}</td>
							
								<td>${bracketMap.key.competition}</td>
							
								
								<td></td>
							
								<td></td>
							
								<td></td>
							
								<td></td>
								
								<td></td>
							
							</tr>
							<g:each in="${bracketMap.value}" status="j" var="participant">
								<tr></td>
							
								<td></td>
							
								
								<td>${participant.name}</td>
							
								<td>${participant.lastName}</td>
							
								<td>${participant.school.name}</td>
							
								<td>${bracketMap.value.size()}</td>
								
								<td>NADA</td>
								</tr>
							</g:each>	
						</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${bracketsInstanceCount ?: 0}" />
					</div>
				</div>
        
    </div><!-- /.box-body -->
    <div class="box-footer">
    	<div>
    	
        </div>
    </div><!-- /.box-footer-->
</div>