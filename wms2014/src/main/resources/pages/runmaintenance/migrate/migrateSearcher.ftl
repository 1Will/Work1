<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('migrateBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('migrateBillName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select>
		<@ww.textfield label="'${action.getText('requester.Person')}'" name="'requester.name'" value="'${req.getParameter('requester.name')?if_exists}'" cssClass="'underline'" /> 
		<tr> 
    	    <@pp.dateRanger label="'${action.getText('migrateOccurDateTime')}'" name="'requestDate'" 
		       value="'${req.getParameter('requestDate_start')?if_exists}|${req.getParameter('requestDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		       
          	<@eam2008_onlySearchInvalid_checkBox/>
		</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('department.id')?exists>
    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
   <#if first>
    <#if loginUser.department?exists>
      getObjByNameRe("department.id").value = #{loginUser.department.id};
    </#if>
  </#if> 
 } 
//查询页面的日期验证格式
  function checkInvalidParms() {
       beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	   if(!queryDate("requestDate_start","requestDate_end",
	      beginDateMsg,null)){
	     return false;
	}
	 if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	} 
	return true;
    }
</script>