<%@ page import="tournament.manager.Students" %>



<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'docNumber', 'error')} ">
	<label for="docNumber">
		<g:message code="students.docNumber.label" default="Doc Number" />
		
	</label>
	<g:textField name="docNumber" value="${studentsInstance?.docNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'picture', 'error')} ">
	<label for="picture">
		<g:message code="students.picture.label" default="Picture" />
		
	</label>
	<g:textField name="picture" value="${studentsInstance?.picture}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'birthDate', 'error')} required">
	<label for="birthDate">
		<g:message code="students.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="birthDate" precision="day"  value="${studentsInstance?.birthDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="students.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${studentsInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="students.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${studentsInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'school', 'error')} required">
	<label for="school">
		<g:message code="students.school.label" default="School" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="school" name="school.id" from="${tournament.manager.Schools.list()}" optionKey="id" required="" value="${studentsInstance?.school?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentsInstance, field: 'sex', 'error')} required">
	<label for="sex">
		<g:message code="students.sex.label" default="Sex" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sex" from="${tournament.manager.Sex?.values()}" keys="${tournament.manager.Sex.values()*.name()}" required="" value="${studentsInstance?.sex?.name()}" />

</div>

