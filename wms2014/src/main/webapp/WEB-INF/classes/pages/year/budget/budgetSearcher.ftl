<@inputTable>
  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
  <tr>
    <@ww.textfield label="'${action.getText('budget.yearBudgetNo')}'" name="'yearBudgetNo'" value="'${req.getParameter('yearBudgetNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('budget.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
	<@pp.datePicker label="'${action.getText('budget.year')}'" name="'year'"
 					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
 					dateFormat="'%Y'" maxlength="4"/>
 	<@ww.textfield label="'${action.getText('budget.planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
  </tr>
  <tr>
    <@pp.dateRanger label="'${action.getText('budget.planCreatedDate')}'" name="'planCreatedDate'" 
                    value="'${req.getParameter('planCreatedDate_start')?if_exists}|${req.getParameter('planCreatedDate_end')?if_exists}'"
                    cssClass="'underline'" maxlength="10"/>
    <#if toolingDevFlag?exists>
      <#if toolingDevFlag == 'DEVICE'>
       <@eam2008_onlySearchInvalid_checkBox/>
      <#else>
      <@ww.hidden name="'onlyValid'" value="'true'"/>
      </#if>
    </#if>
  </tr>
  <tr>
    
  </tr>
</@inputTable> 
<script language="javascript">
  
  function checkInvalidParms() {
	//验证编制日期格式
	planCreatedMsg="${action.getText('budget.planCreatedDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("planCreatedDate_start","planCreatedDate_end",
	    planCreatedMsg,null)){
	  return false;
	}
	return true;
  }
  </script>	