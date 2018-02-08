<@inputTable>
  <tr>
    <@ww.textfield label="'${action.getText('purchasePlan.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('purchasePlan.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		     value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                 list="departments" emptyOption="false" disabled="false">
    </@ww.select>
	<@pp.datePicker label="'${action.getText('purchasePlan.year')}'" name="'year'"
 					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
 					dateFormat="'%Y'" maxlength="4"/>
  </tr>
  <tr>
    <@ww.textfield label="'${action.getText('purchasePlan.planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
    <@pp.dateRanger label="'${action.getText('purchasePlan.planCreatedDate')}'" name="'planCreatedDate'" 
                    value="'${req.getParameter('planCreatedDate_start')?if_exists}|${req.getParameter('planCreatedDate_end')?if_exists}'"
                    cssClass="'underline'" maxlength="10"/>
  </tr>
  <tr>
    <@eam2008_onlySearchInvalid_checkBox/>
  </tr>
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
	planCreatedMsg="${action.getText('purchasePlan.planCreatedDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("planCreatedDate_start","planCreatedDate_end",
	    planCreatedMsg,null)){
	  return false;
	}
	return true;
  }
  </script>	