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
			<div id="list-tournament" class="content scaffold-list" role="main">
				<h1>
					<g:message code="default.tournament.label" default="Tournaments" />
				</h1>
				<g:if test="${flash.message}">
					<div class="message" role="status">
						${flash.message}
					</div>
				</g:if>
				<table>
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
								
							<th><g:message code="default.button.actions.label" default="Actions" /></th>

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
									<select>
										<option value="volvo">Editar</option>
										<option value="volvo">Eliminar</option>
									</select>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${total}" />
				</div>
			</div>
			<div class="row double">
				<div class="12u">
					<ul class="actions">
						<g:link controller="tournament" action="create">${message(code: 'default.link.create.label', default: 'Create')}</g:link>
					</ul>

				</div>
			</div>
		</article>
	</div>
	<g:render template="/home/templates/footer" />
</body>
</html>
