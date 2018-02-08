<@inputTable>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
 		<tr>
			<@ww.textfield label="'${action.getText('preRepairPlanNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('preRepairPlanName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
			<@pp.dateRanger label="'${action.getText('beginDateTime')}'" name="'beginDateTime'" 
		                    value="'${req.getParameter('beginDateTime_start')?if_exists}|${req.getParameter('beginDateTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/>
		</tr>
		<tr>
		    <@ww.textfield label="'${action.getText('planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
	    	<@pp.dateRanger label="'${action.getText('planCreatedTime')}'" name="'planCreatedTime'" 
		                    value="'${req.getParameter('planCreatedTime_start')?if_exists}|${req.getParameter('planCreatedTime_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/>
	    </tr>
	    <#if planProcFlag?exists>
	      <#if (planProcFlag=='PROC')>
		    <tr>
			    <@ww.textfield label="'${action.getText('preRepairProc.reportor')}'" name="'reportor.name'" value="'${req.getParameter('reportor.name')?if_exists}'" cssClass="'underline'" />
		    	<@pp.dateRanger label="'${action.getText('preRepairProc.reportDate')}'" name="'reportDate'" 
			                    value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'"
			                    cssClass="'underline'" maxlength="10"/>
		    </tr>
	      </#if>
	    </#if>
</@inputTable> 
<script language="javascript">
  var selector = document.all("department.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  <#if first>
    <#if loginUser.department?exists>
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  function checkInvalidParms() {
    if (document.getElementById("department.id").value == -1) {
      document.getElementById("department.id").value = '';
    }
    //验证计划开始日期格式
    beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("beginDateTime_start","beginDateTime_end",
	    beginDateMsg,null)){
	  return false;
	}
	//验证编制日期格式
	planCreatedMsg="${action.getText('planCreatedTime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("planCreatedTime_start","planCreatedTime_end",
	    planCreatedMsg,null)){
	  return false;
	}
    <#if planProcFlag?exists>
	  <#if (planProcFlag=='PROC')>
	  	//验证报告日期格式
	    reportDateMsg="${action.getText('preRepairProc.reportDate')}" + "${action.getText('dateFormate.error')}";
	    if(!queryDate("reportDate_start","reportDate_end",
	      reportDateMsg,null)){
	      return false;
	    }
	  </#if>
	</#if>
	return true;
  }
  </script>	