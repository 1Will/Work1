<@inputTable>
  <tr>
    <@ww.textfield label="'${action.getText('quarterPlan.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('quarterPlan.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		     value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                 list="departments" emptyOption="false" disabled="false">
    </@ww.select>
	<@pp.datePicker label="'${action.getText('quarterPlan.year')}'" name="'year'"
 					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
 					dateFormat="'%Y'"/>
  </tr>
  <tr>
  	<@ww.select label="'${action.getText('quarterPlan.qarterType')}'" required="false" name="'qarterType.id'" 
    		     value="'${req.getParameter('qarterType.id')?if_exists}'" listKey="value" listValue="label"
                 list="qarterTypes" emptyOption="false" disabled="false">
    </@ww.select>
    <@ww.textfield label="'${action.getText('quarterPlan.planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
  </tr>
  <tr>
    <@pp.dateRanger label="'${action.getText('quarterPlan.planCreatedDate')}'" name="'planCreatedDate'" 
                    value="'${req.getParameter('planCreatedDate_start')?if_exists}|${req.getParameter('planCreatedDate_end')?if_exists}'"
                    cssClass="'underline'" dateFormat="date"/>
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
  var selector = document.all("qarterType.id");
  groups = selector.options.length;
  for (i=0; i<groups; i++) {
    <#if req.getParameter('qarterType.id')?exists>
    if (selector.options[i].value == "${req.getParameter('qarterType.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
  }
  
  function checkInvalidParms() {
    if (document.getElementById("department.id").value == -1) {
  		document.getElementById("department.id").value = '';
	}
	if (document.getElementById("qarterType.id").value == -1) {
  		document.getElementById("qarterType.id").value = '';
	}
	var year = document.forms[0].elements["year"].value;
	if(year != ''){
		if(!isYear(year)){
			alert("${action.getText('年份格式错误,正确格式为yyyy')}");
			return false;
		}
	}
	//验证编制日期格式
	planCreatedMsg="${action.getText('quarterPlan.planCreatedDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("planCreatedDate_start","planCreatedDate_end",
	    planCreatedMsg,null)){
	  return false;
	}
	return true;
  }
  </script>	