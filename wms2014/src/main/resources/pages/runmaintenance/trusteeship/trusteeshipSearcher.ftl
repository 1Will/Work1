<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('trusteeshipBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('trusteeshipBillName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select>
		<@ww.textfield label="'${action.getText('trusteeshiprequestUserName')}'" name="'trusteeshipUser'" value="'${req.getParameter('trusteeshipUser')?if_exists}'" cssClass="'underline'" /> 
		</tr>
		<tr> 
    	    <@pp.dateRanger label="'${action.getText('trusteeshipRequestDateTime')}'" name="'trusteeshipRequestDate'" 
		       value="'${req.getParameter('trusteeshipRequestDate_start')?if_exists}|${req.getParameter('trusteeshipRequestDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
          	<@eam2008_onlySearchInvalid_checkBox/>
		</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
    var selector = document.all("department.id");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {    //循环的指定部门所查询的条件
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
 function checkInvalidParms() {
       beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";//查询页面的日期验证格式
	   if(!queryDate("trusteeshipRequestDate_start","trusteeshipRequestDate_end",
	      beginDateMsg,null)){
	     return false;
	 }
	 if (getObjByNameRe("department.id").value == -1) {
	  getObjByNameRe("department.id").value = '';
	} 
	  return true;
 }
 </script>           