<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('spareBorrowBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('spareBorrowBillName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 		<tr>
	 		<@ww.textfield label="'${action.getText('spare.code')}'" name="'spareCode'" value="'${req.getParameter('spareCode')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('sparename')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'"/>
		</tr>
	 		<tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select>
		<@pp.dateRanger label="'${action.getText('borrowDateTime')}'" name="'borrowDate'" 
		       value="'${req.getParameter('borrowDate_start')?if_exists}|${req.getParameter('borrowDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
		</tr>
	 
		<tr>
	 		<@ww.textfield label="'${action.getText('spare.model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('spare.specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr> 
    	    <@ww.textfield label="'${action.getText('borrow.Person')}'" name="'borrowName'" value="'${req.getParameter('borrowName')?if_exists}'" cssClass="'underline'" /> 
		</tr>
	<#--	<tr>
		<@ww.select label="'${action.getText('borrowStatus')}'" required="false" name="'borrowStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="borrowStatus" emptyOption="false" disabled="false">
		</@ww.select>
			<@eam2008_onlySearchInvalid_checkBox/>
		</tr>-->
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
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
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
	return true;
    }
</script>