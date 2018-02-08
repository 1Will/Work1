<@inputTable>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<@ww.hidden name="'department.id'" value="'${req.getParameter('department.id')?if_exists}'"/>
		<@ww.hidden name="'preRepairPlan.id'" value="'${req.getParameter('preRepairPlan.id')?if_exists}'"/>
		<tr>
	    	<@pp.dateRanger label="'${action.getText('planCreatedTime')}'" name="'planCreatedTime'" 
		                    value="'${req.getParameter('planCreatedTime_start')?if_exists}|${req.getParameter('planCreatedTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/>
		    <@ww.select label="'${action.getText('procStatus')}'" required="false" name="'procStatus'" 
		    		value="'${req.getParameter('procStatus')?if_exists}'" listKey="value" listValue="label"
		            list="procStatus" emptyOption="false" disabled="false">
		    </@ww.select> 
		</tr>
</@inputTable> 
<script language="javascript">
  var selector = document.all("procStatus");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('procStatus')?exists>
    if (selector.options[i].value == "${req.getParameter('procStatus')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  function checkInvalidParms() {
  if (getObjByNameRe("procStatus").value == -1) {
  		getObjByNameRe("procStatus").value = '';
	}
	strStartMsg="${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('planCreatedTime.order.error')}";
	if(!queryDate("planCreatedTime_start","planCreatedTime_end",
	    strStartMsg,strEndMsg)){
	    	return false;
	    }

	return true;
  }
  </script>	