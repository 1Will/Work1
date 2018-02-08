<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('spareBorrowBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('spareBorrowBillName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select>
		<@pp.dateRanger label="'${action.getText('borrowDateTime')}'" name="'borrowDate'" 
		       value="'${req.getParameter('borrowDate_start')?if_exists}|${req.getParameter('borrowDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		
		<tr> 
    	    <@ww.textfield label="'${action.getText('borrow.Person')}'" name="'borrowName'" value="'${req.getParameter('borrowName')?if_exists}'" cssClass="'underline'" /> 
		    <@ww.textfield label="'${action.getText('appor.Person')}'" name="'appName'" value="'${req.getParameter('appName')?if_exists}'" cssClass="'underline'" />    
          
		</tr>
		<tr>
		<@ww.select label="'${action.getText('borrowStatus')}'" required="false" name="'borrowStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="borrowStatus" emptyOption="false" disabled="false">
		</@ww.select>
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
    <#else>
	    <#if loginUser.department?exists>
	      document.getElementById("department.id").value = #{loginUser.department.id};
	    </#if>
	</#if>
 } 
  	var statusSelector = document.all("borrowStatus");
  	statusGroups = statusSelector.options.length;
  	for (i=0; i<statusGroups; i++) {
    <#if req.getParameter('borrowStatus')?exists>
    	if (statusSelector.options[i].value == "${req.getParameter('borrowStatus')?if_exists}") {
      	statusSelector.options[i].selected="true";
    	}
    </#if>
    }
//查询页面的日期验证格式
  function checkInvalidParms() {
       beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	   if(!queryDate("borrowDate_start","borrowDate_end",
	      beginDateMsg,null)){
	     return false;
	}
	 if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	} 
	if (document.getElementById("borrowStatus").value == -1) {
	  document.getElementById("borrowStatus").value = '';
	} 
	return true;
    }
</script>