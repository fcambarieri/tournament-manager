<%@ page import="tournament.manager.Brackets" %>
<%@ page import="tournament.manager.BracketKey" %>
	

<div class="form-group">
	<label for="tournament">
		<g:message code="default.entity.tournament.title" default="Tournament" />
	</label>
	<g:select id="tournament" class="form-control" name="tournament.id" from="${tournaments}" optionKey="id" required="" value="${tournamentSelected?.id}" noSelection="['null': '--select tournament--']" />
</div>

 <div class="box box-info">
    <div class="box-header">
        <h3 class="box-title"><g:message code="default.entity.bracketCombat" default="Combat brackets" /></h3>
        <div class="box-tools pull-right">
            
        </div>
    </div>
    <div class="box-body">
        
        <div id="list-combat-brackets" class="box-body table-responsive no-padding" role="main">
        		<a onclick="jQuery('#participants').treetable('expandAll'); return false;" href="#">Expand all</a>
				<a onclick="jQuery('#participants').treetable('collapseAll'); return false;" href="#">Collapse all</a>
				<table class="table table-bordered table-hover dataTable" id="participants">
					<thead>
							<tr>
							
								<th><g:message code="brackets.belt.label" default="Belt" /></th>
							
<%--								<th><g:message code="brackets.combatCategory.label" default="Combat Category" /></th>--%>
								<g:sortableColumn property="category" title="${message(code: 'brackets.combatCategory.label', default: 'Combat Category')}" params="[tournamentId:tournamentSelected?.id]"/>
							
								<th><g:message code="brackets.combatWeight.label" default="Combat Weight" /></th>
							
								<th><g:message code="default.entity.schools.title" default="School" /></th>
								
								<g:sortableColumn property="name" title="${message(code: 'participants.name.label', default: 'Name')}" />
			
								<g:sortableColumn property="lastName" title="${message(code: 'participants.lastName.label', default: 'Last Name')}" />	
								
								<g:sortableColumn property="count" title="${message(code: 'participants.count.label', default: 'Count')}" params="[tournamentId:tournamentSelected?.id]"/>
								
<%--								<th><g:message code="default.count" default="Count"/></th>--%>
								
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
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}" data-tt-id="${i}">
							
								<td>${bracketMap.key.belt}</td>
							
								<td>${bracketMap.key.competition.combatCategory}</td>
								
								<td>${bracketMap.key.competition}</td>
								
								<td></td>
							
								<td></td>
							
								<td></td>
							
								<td>${bracketMap.value.size()}</td>
								
								<td>
									<g:if test="${bracketMap.key.bracket == null}">
										<g:form url="[action:'createBracket', controller:'brackets']"  method="post" >
											<g:hiddenField name="beltId" value="${bracketMap.key.belt.id}" />
											<g:hiddenField name="categoryId" value="${bracketMap.key.competition.id}" />
											<g:hiddenField name="type" value="combat" />
											<button type="submit" title="${message(code: 'default.button.create.label', default: 'Create')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-table"></i></button>
		 								</g:form>
		 							</g:if>
		 							<g:else>
		 								<a href="${bracketMap.key.bracket.url.replaceAll("http//","http://")}" target="_blank"><i class="fa fa-check"></i></a>
		 							</g:else>
								</td>
							
							</tr>
							<g:each in="${bracketMap.value}" status="j" var="participant">
								<tr data-tt-id="${j}" data-tt-parent-id="${i}">
									<td></td>
								
									<td></td>
									
									<td></td>
								
									<td>${participant.school.name}</td>
									
									<td>
									<g:link action="edit" title="${message(code: 'default.button.edit.label', default: 'Edit')}" resource="${participant}">
										${participant.name}
									</g:link>
									</td>
								
									<td>${participant.lastName}</td>
								
									<td></td>
								
									<td></td>
									
								</tr>
							</g:each>	
						</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${countParticipant ?: 0}" />
					</div>
				</div>
        
    </div><!-- /.box-body -->
    <div class="box-footer">
    	<div>
    	
        </div>
    </div><!-- /.box-footer-->
</div>

<div class="box box-info">
    <div class="box-header">
        <h3 class="box-title"><g:message code="default.entity.bracketPoomse" default="Poomse brackets" /></h3>
        <div class="box-tools pull-right">
            
        </div>
    </div>
    <div class="box-body">
        
        <div id="list-poomse-brackets" class="box-body table-responsive no-padding" role="main">
        		<a onclick="jQuery('#participants').treetable('expandAll'); return false;" href="#">Expand all</a>
				<a onclick="jQuery('#participants').treetable('collapseAll'); return false;" href="#">Collapse all</a>
				<table class="table table-bordered table-hover dataTable" id="poomseParticipants">
					<thead>
							<tr>
							
								<th><g:message code="brackets.belt.label" default="Belt" /></th>
							
								<th><g:message code="brackets.poomse.label" default="Poomse Category" /></th>
							
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
						<g:each in="${poomseParticipants}" status="i" var="bracketMap">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td>${bracketMap.key.belt}</td>
							
								<td>${bracketMap.key.competition}</td>
							
								
								<td></td>
							
								<td></td>
							
								<td></td>
							
								<td>${bracketMap.value.size()}</td>
								
								<td>NADA</td>
							
							</tr>
							<g:each in="${bracketMap.value}" status="j" var="participant">
								<tr>
									<td></td>
								
									<td></td>
								
									<td>${participant.school.name}</td>
									
									<td>
									<g:link action="edit" title="${message(code: 'default.button.edit.label', default: 'Edit')}" resource="${participant}">
										${participant.name}
									</g:link>
									</td>
								
									<td>${participant.lastName}</td>
								
									<td></td>
								
									<td></td>
									
								</tr>
							</g:each>	
						</g:each>
						</tbody>
					</table>
					<div class="pagination">
						<g:paginate total="${poomseParticipants.size()}" />
					</div>
				</div>
        
    </div><!-- /.box-body -->
    <div class="box-footer">
    	<div>
    		<a href="#" class="btn btn-default" onClick="window.print();return false"><i class="fa fa-print"></i>Print</a>
        </div>
    </div><!-- /.box-footer-->
</div>