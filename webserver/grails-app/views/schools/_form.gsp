<%@ page import="tournament.manager.Schools" %>



<div class="fieldcontain ${hasErrors(bean: schoolsInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="schools.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="30" required="" value="${schoolsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: schoolsInstance, field: 'phoneNumber', 'error')} ">
	<label for="phoneNumber">
		<g:message code="schools.phoneNumber.label" default="Phone Number" />
		
	</label>
	<g:textField name="phoneNumber" value="${schoolsInstance?.phoneNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: schoolsInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="schools.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${schoolsInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: schoolsInstance, field: 'dateUpdated', 'error')} required">
	<label for="dateUpdated">
		<g:message code="schools.dateUpdated.label" default="Date Updated" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateUpdated" precision="day"  value="${schoolsInstance?.dateUpdated}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: schoolsInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="schools.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${tournament.manager.auth.User.list()}" optionKey="id" required="" value="${schoolsInstance?.owner?.id}" class="many-to-one"/>

</div>

