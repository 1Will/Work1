<@inputTable>
		<tr>
	 		<@ww.textfield label="'${action.getText('demarcate.planNo')}'" name="'toolingNo'" value="'${req.getParameter('toolingNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('demarcate.planName')}'" name="'toolingName'" value="'${req.getParameter('toolingName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>	
	 	<tr>
    	    <@pp.dateRanger label="'${action.getText('demarcate.planStartTime')}'" name="'planStartTime'" 
		                    value="'${req.getParameter('planStartTime_start')?if_exists}|${req.getParameter('planStartTime_end')?if_exists}'"
		                    cssClass="'underline'" dateFormat="date"/> 
	 	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select> 	
	 	</tr>		
		<tr>
	          <@ww.select label="'${action.getText('demarcate.status')}'" required="false" name="'demarcate.status'" 
    			     value="'${req.getParameter('demarcate.status')?if_exists}'" listKey="value" listValue="label"
                     list="demarcateStatus" emptyOption="false" disabled="false">
             </@ww.select>	
		</tr>		
</@inputTable> 
<script language="javascript">
  selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  } 
  
  selector = document.all("demarcate.status");
  var groups=selector.options.length;  
  for (i=0; i<groups; i++){
    <#if req.getParameter('demarcate.status')?exists>
      if (selector.options[i].value=="${req.getParameter('demarcate.status')?if_exists}"){
        selector.options[i].selected="true";
      }
    </#if>
  } 
  
  function checkInvalidParms() {
    if (-1 == document.getElementById("department.id").value) {
      document.getElementById("department.id").value = '';
    }
    if (-1 == document.getElementById("demarcate.status").value) {
      document.getElementById("demarcate.status").value = '';
    }
    return true;
  }
</script> 