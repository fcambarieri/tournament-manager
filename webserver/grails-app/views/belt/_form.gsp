<%@ page import="tournament.manager.Belt" %>



 <div class="form-group">
     <div class="input-group">
         <label for="description">
			<g:message code="belt.description.label" default="Description" />
			<span class="required-indicator">*</span>
		</label>
          <g:textField id="description" class="form-control" name="description" maxlength="30" required="" value="${beltInstance?.description}"/>
          <g:hiddenField name="beltId" value="${beltInstance?.id}" />
          <g:hiddenField name="beltVersion" value="${beltInstance?.version}" />
     </div>
 </div>


