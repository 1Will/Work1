<@inputTable>	
	<tr>
		<@ww.textfield label="'${action.getText('accept.billNo')}'" name="'acceptBillNo'" value="'${req.getParameter('acceptBillNo')?if_exists}'" cssClass="'underline'" />	 
		<@ww.textfield label="'${action.getText('accept.name')}'" name="'acceptBilName'" value="'${req.getParameter('acceptBilName')?if_exists}'" cssClass="'underline'" />	
	</tr>
	<tr>
	    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		</@ww.select> 
		<@ww.textfield label="'${action.getText('accept.Person')}'" name="'acceptPeople.name'" value="'${req.getParameter('acceptPeople.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	    <@ww.textfield label="'${action.getText('accept.supplier')}'" name="'supplier.name'" value="'${req.getParameter('supplier.name')?if_exists}'" cssClass="'underline'" />
	    <@ww.textfield label="'${action.getText('accept.merchandiseName')}'" name="'merchandiseName'" value="'${req.getParameter('merchandiseName')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	   <@ww.textfield label="'${action.getText('accept.specification')}'" name="'specification'" value="'${req.getParameter('specification')?if_exists}'" cssClass="'underline'" />
	   <@ww.textfield label="'${action.getText('accept.model')}'" name="'model'" value="'${req.getParameter('model')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	    <@pp.dateRanger label="'${action.getText('acceptDate')}'" name="'acceptDate'" 
		       value="'${req.getParameter('acceptDate_start')?if_exists}|${req.getParameter('acceptDate_end')?if_exists}'"
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
      document.getElementById("department.id").value = #{loginUser.department.id};
    </#if>
  </#if>
  } 
   //当部门的id为-1时,把当前部门的id至为空
  function checkInvalidParms(){   
	    if (document.getElementById("department.id").value == -1) {
	      document.getElementById("department.id").value = '';
	    } 
	    beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	   if(!queryDate("acceptDate_start","acceptDate_end",
	      beginDateMsg,null)){
	     return false;
	}
	    return true; 
	}    
 </script>