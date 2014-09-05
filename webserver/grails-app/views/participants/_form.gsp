<%@ page import="tournament.manager.Participants" %>

<g:hiddenField name="id" value="${instance?.id}" />
<%--<% def tournamentSelected = instance?.tournament != null ? instance.tournament.id : tournamentId} %>--%>
<div class="form-group ${hasErrors(bean: instance, field: 'tournament', 'error')} required">
	<label for="tournament">
		<g:message code="participants.tournament.label" default="Tournament" />
	</label>
	
	<g:select id="tournament" class="form-control" name="tournament.id" from="${tournaments}" optionKey="id" required="" value="${instance?.tournament?.id}" noSelection="['null': '--select item--']" />
<%--	<select id="tournament" class="form-control" name="tournament.id" required="">--%>
<%--		<option value="none" >"select tournament"</option>--%>
<%--		<g:each in="${tournaments}" status="i" var="tournamentInstance">--%>
<%--			<option value="${tournamentInstance.id}">${tournamentInstance}</option>--%>
<%--		</g:each>--%>
<%--	</select>--%>
</div>

<div class="form-group ${hasErrors(bean: instance, field: 'school', 'error')} required">
	<label for="school">
		<g:message code="participants.school.label" default="School" />
	</label>
	<g:select id="school" class="form-control" name="school.id" from="${schools}" optionKey="id" required="" value="${instance?.school?.id}" noSelection="['null': '--select item--']"/>
</div>

<div class="form-group ${hasErrors(bean: instance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="participants.name.label" default="Name" />
	</label>
	<g:textField class="form-control" name="name" maxlength="30" required="" value="${instance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="participants.lastName.label" default="Last Name" />
	</label>
	<g:textField class="form-control" name="lastName" maxlength="30" required="" value="${instance?.lastName}"/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'belt', 'error')} required">
	<label for="belt">
		<g:message code="participants.belt.label" default="Belt" />
		<span class="required-indicator">*</span>
	</label>
	<g:select class="form-control" id="belt" name="belt.id" from="${belts}" optionKey="id" required="" value="${instance?.belt?.id}" noSelection="['null': '--select item--']"/>

</div>

<%--<div class="form-group ${hasErrors(bean: instance, field: 'combatStatus', 'error')} ">--%>
<%--	<label for="combatStatus">--%>
<%--		<g:message code="participants.combatStatus.label" default="Combat Status" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<g:textField class="form-group" name="combatStatus" value="${instance?.combatStatus}"/>--%>
<%----%>
<%--</div>--%>
<%----%>
<%--<div class="form-group ${hasErrors(bean: instance, field: 'formStatus', 'error')} ">--%>
<%--	<label for="formStatus">--%>
<%--		<g:message code="participants.formStatus.label" default="Form Status" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<g:textField class="form-group" name="formStatus" value="${instance?.formStatus}"/>--%>
<%----%>
<%--</div>--%>

<div class="form-group ${hasErrors(bean: instance, field: 'formCategory', 'error')} ">
	<label for="formCategory">
		<g:message code="participants.formCategory.label" default="Form Category" />
	</label>
	<g:select class="form-control" id="formCategory" name="formCategory.id" from="${formCategories}" optionKey="id" value="${instance?.formCategory?.id}"  noSelection="['null': '--select item--']"/>

</div>

<div class="form-group">
	<label for="combatCategory">
		<g:message code="combatCategory.title" default="Combat categories" />
	</label>
	<g:select class="form-control" id="combatCategory" name="combatCategoryId" from="${combatCategories}" optionKey="id" value="${instance?.combatWeight?.combatCategory?.id}" noSelection="['':'--select item--']"/>
</div>

<div class="form-group ${hasErrors(bean: instance, field: 'combatWeight', 'error')} ">
	<label for="combatWeight">
		<g:message code="participants.combatWeight.label" default="Combat Weight" />
	</label>
	<g:select class="form-control" id="combatWeight" name="combatWeight.id" from="${combatWeights}" optionKey="id" value="${instance?.combatWeight?.id}"  noSelection="['null': '--select item--']"/>
</div>







