<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('inventoryBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('inventoryBillName')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
	 	<tr> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		   </@ww.select>
           <@ww.textfield label="'${action.getText('inventory.people')}'" name="'inventoryPeople'" value="'${req.getParameter('inventoryPeople')?if_exists}'" cssClass="'underline'"/>
    	    
		</tr>
		<tr>
		    <@pp.dateRanger label="'${action.getText('inventoryBillDate')}'" name="'inventoryDate'" 
		       value="'${req.getParameter('inventoryDate_start')?if_exists}|${req.getParameter('inventoryDate_end')?if_exists}'"
		       cssClass="'underline'" maxlength="10"/> 
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
  }
  
  function checkInvalidParms() {
    if (document.getElementById("department.id").value == -1) {
	  document.getElementById("department.id").value = '';
	}

	//验证清查日期格式
	inventoryDateMsg="${action.getText('inventoryBillDate')}" + "${action.getText('dateFormate.error')}";
	if(!queryDate("inventoryDate_start","inventoryDate_end",inventoryDateMsg,null)){
	  return false;
	}

	return true;
	
  }
</script>
	