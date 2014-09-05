<%@ page import="tournament.manager.Schools" %>

<div class="form-group ${hasErrors(bean: instance, field: 'name', 'has-error')} required">
	<label for="name">
		<g:message code="schools.name.label" default="Name" />
	</label>
	<g:textField class="form-control" name="name" maxlength="30" required="" value="${instance?.name}"/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'email', 'has-error')} ">
	<label for="email">
		<g:message code="schools.email.label" default="Email" />
		
	</label>
	<g:textField class="form-control" name="email" value="${instance?.email}"/>

</div>

<div class="form-group ${hasErrors(bean: instance, field: 'phoneNumber', 'has-error')} ">
	<label for="phoneNumber">
		<g:message code="schools.phoneNumber.label" default="Phone Number" />
		
	</label>
	<g:textField class="form-control" name="phoneNumber" value="${instance?.phoneNumber}"/>
</div>


