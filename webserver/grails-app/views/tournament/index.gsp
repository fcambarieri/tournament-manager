<%@ page import="tournament.manager.Tournament"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Tournament Manager</title>
</head>
<body>
	<g:render template="/home/templates/nav" />
	<!-- Work -->
	<div class="wrapper style1">
		<article id="tournaments">
			<div id="list-tournament" class="ch-box-lite" role="main">
				<h1>
					<g:message code="default.tournament.label" default="Tournaments" />
				</h1>

				<g:if test="${flash.message}">
					<div class="message" role="status">
						${flash.message}
					</div>
				</g:if>
				<table class="ch-datagrid">
					<thead>
						<tr>

							<g:sortableColumn property="name"
								title="${message(code: 'tournament.name.label', default: 'Name')}" />

							<g:sortableColumn property="dateCreated"
								title="${message(code: 'tournament.dateCreated.label', default: 'Creation Date')}" />

							<g:sortableColumn property="lastUpdated"
								title="${message(code: 'tournament.lastUpdated.label', default: 'Last updated')}" />

							<g:sortableColumn property="status"
								title="${message(code: 'tournament.status.label', default: 'Status')}" />

							<th scope="col"><g:message
									code="default.button.actions.label" default="Actions" /></th>

						</tr>
					</thead>
					<tbody>
						<g:each in="${tournaments}" status="i" var="tournamentInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

								<td><g:link action="show" id="${tournamentInstance.id}">
										${fieldValue(bean: tournamentInstance, field: "name")}
									</g:link></td>

								<td><g:formatDate date="${tournamentInstance.dateCreated}" /></td>

								<td><g:formatDate date="${tournamentInstance.lastUpdated}" /></td>

								<%--						<td>${fieldValue(bean: tournamentInstance, field: "owner")}</td>--%>

								<td>
									${fieldValue(bean: tournamentInstance, field: "status")}
								</td>

								<td>
									<ul class="dropdown">
										<li>
										
										<g:link controller="tournament" action="settings" params="[id:${tournamentInstance.id}]">
												<i class="icon-cog"></i>
												${message(code: 'default.link.create.label', default: 'Settings')}
											</g:link></li>
									</ul>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${total?:0}" />
				</div>
			</div>
			<div class="row double">
				<div class="12u">
					<ul class="actions">
						<form method="post" action="create">
							<li><g:submitButton name="continue" class="create"
									value="${message(code: 'default.link.create.label', default: 'Create')}" /></li>
							<%--						 <g:link controller="tournament" action="create">${message(code: 'default.link.create.label', default: 'Create')}</g:link>--%>
							</li>
						</form>
					</ul>

				</div>
			</div>
		</article>
	</div>
	<g:render template="/home/templates/footer" />
	<script type="text/javascript">

	</script>
</body>
</html>
