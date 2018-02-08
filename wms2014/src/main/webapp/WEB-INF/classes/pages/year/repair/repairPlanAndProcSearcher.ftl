<@inputTable>
		<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
 		<tr>
			<@ww.textfield label="'${action.getText('repairPlanOrProc.repairPlanNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('repairPlanOrProc.repairPlanName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('repairPlanOrProc.department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
			<@pp.datePicker label="'${action.getText('repairPlanOrProc.year')}'" name="'year'"
	     					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
	     					dateFormat="'%Y'" maxlength="4"/>
		</tr>
		<tr>
		    <@ww.textfield label="'${action.getText('repairPlanOrProc.planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
		    <@pp.dateRanger label="'${action.getText('repairPlanOrProc.planCreatedTime')}'" name="'planCreatedTime'" 
		                     value="'${req.getParameter('planCreatedTime_start')?if_exists}|${req.getParameter('planCreatedTime_end')?if_exists}'"
		                     cssClass="'underline'" maxlength="10"/>
		</tr>
		<#if planProcFlag?exists>
	      <#if (planProcFlag=='PROC')>
		    <tr>
			    <@ww.textfield label="'${action.getText('repairPlanOrProc.reportor')}'" name="'reportor.name'" value="'${req.getParameter('reportor.name')?if_exists}'" cssClass="'underline'" />
		    	<@pp.dateRanger label="'${action.getText('repairPlanOrProc.reportDate')}'" name="'reportDate'" 
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
	//验证编制日期格式
	planCreatedMsg="${action.getText('repairPlanOrProc.planCreatedTime')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("planCreatedTime_start","planCreatedTime_end",
	    planCreatedMsg,null)){
	  return false;
	}
	<#if planProcFlag?exists>
	  <#if (planProcFlag=='PROC')>
	    //验证报告日期格式
	    planCreatedMsg="${action.getText('repairPlanOrProc.reportDate')}" + "${action.getText('dateFormate.error')}";
	    if(!queryDate("reportDate_start","reportDate_end",
	       planCreatedMsg,null)){
	       return false;
	    }
	  </#if>
	</#if>
	return true;
  }
  </script>	