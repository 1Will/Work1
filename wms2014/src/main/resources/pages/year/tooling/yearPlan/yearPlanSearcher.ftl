<@inputTable>
  <tr>
    <@ww.textfield label="'${action.getText('yearPlan.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('yearPlan.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
  </tr>
  <tr>
	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    		     value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
                 list="departments" emptyOption="false" disabled="false">
    </@ww.select>
	<@pp.datePicker label="'${action.getText('yearPlan.year')}'" name="'year'"
 					value="'${req.getParameter('year')?if_exists}'" cssClass="'underline'" size="15" 
 					dateFormat="'%Y'"/>
  </tr>
  <tr>
    <@ww.textfield label="'${action.getText('yearPlan.planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
    <@pp.dateRanger label="'${action.getText('yearPlan.planCreatedDate')}'" name="'planCreatedDate'" 
                    value="'${req.getParameter('planCreatedDate_start')?if_exists}|${req.getParameter('planCreatedDate_end')?if_exists}'"
                    cssClass="'underline'" dateFormat="date"/>
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
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  function checkInvalidParms() {
  if (getObjByNameRe("department.id").value == -1) {
  		getObjByNameRe("department.id").value = '';
	}
	var year = document.forms[0].elements["year"].value;
	if(year != ''){
		if(!isYear(year)){
			alert("${action.getText('年份格式错误,正确格式为yyyy')}");
			return false;
		}
	}
	strStartMsg="${action.getText('yearPlan.planCreatedDate')}" + "${action.getText('dateFormate.error')}";
	strEndMsg="${action.getText('planCreatedTime.order.error')}";
	if(!queryDate("planCreatedDate_start","planCreatedDate_end",
	    strStartMsg,strEndMsg)){
	    	return false;
	    }
	return true;
  }
  </script>	