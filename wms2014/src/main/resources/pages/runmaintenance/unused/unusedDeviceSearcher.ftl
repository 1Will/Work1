<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('unusedBill_no')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('unusedBill_Name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<#if toolingDevFlag?exists>
		  <#if toolingDevFlag=='DEVICE'>
		     <tr>
	        <@ww.textfield label="'${action.getText('device.no')}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('device.Name')}'" name="'asset.name1'" value="'${req.getParameter('asset.name')?if_exists}'" cssClass="'underline'"/>
	        </tr>
	      <#else>
	        <@ww.textfield label="'${action.getText('tooling.no')}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'asset.graphNo'" value="'${req.getParameter('asset.graphNo')?if_exists}'" cssClass="'underline'"/>
		  </#if>
		  </#if>
         <tr>
         <@ww.select label="'${action.getText('unused.departments')}'" 
                             required="false" name="'department.id'" 
                             value="'${req.getParameter('department.id')?if_exists}'"
                             listKey="id" listValue="name"
                             list="departments" 
                              emptyOption="false" 
                              disabled="false">
          </@ww.select>
         <@ww.textfield label="'${action.getText('unused.people')}'" name="'unused.people'" value="'${req.getParameter('unused.people')?if_exists}'" cssClass="'underline'"/>
        
		</tr>
		<tr>
		  <@pp.dateRanger label="'${action.getText('unusedOccurDateTime')}'" name="'unUseDate'" 
		       value="'${req.getParameter('unUseDate_start')?if_exists}|${req.getParameter('unUseDate_end')?if_exists}'"
		       cssClass="'underline'" maxlength="10"/> 
			 <@pp.dateRanger label="'${action.getText('unusedisUsedOccurDateTime')}'" name="'usedAprDate'" 
		       value="'${req.getParameter('usedAprDate_start')?if_exists}|${req.getParameter('usedAprDate_end')?if_exists}'"
		       cssClass="'underline'" maxlength="10"/> 
          </tr>
          <tr>
          <@ww.select label="'${action.getText('unused_state')}'" 
                             required="false" name="'status'" 
                             value="'${status?if_exists}'"
                             listKey="value" listValue="label"
                             list="unusedStatus" 
                              emptyOption="false" 
                              disabled="false">
          </@ww.select>
           	<@eam2008_onlySearchInvalid_checkBox/>
        </tr>
           <#--
           <td align="right" valign="top"><label  for="searchFaultBills_toolingStatus.code" class="label">${action.getText('faultBillstatus')}:</label></td>
           <td>
             <select name="tooling.faultBillstatus" id="searchToolingBorrowBills_toolingStatus.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('faultBill.solution')}</option>       
               <option value="false">${action.getText('faultBill.noSolution')}</option>              
            </select>
           </td> 
	    </tr>  	-->
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
  var selector = document.all("status");
  groups = selector.options.length;

  for (i=0; i<groups; i++) {
    <#if req.getParameter('status')?exists>
    if (selector.options[i].value == "${req.getParameter('status')?if_exists}") {
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
        if(getObjByNameRe("department.id").value == -1) {
	    getObjByNameRe("department.id").value = '';
	     }
	    if (getObjByNameRe("status").value == -1) {
          getObjByNameRe("status").value = '';
        }
            var startUsedDate = getObjByNameRe("usedAprDate_start").value;
            var endUsedDate = getObjByNameRe("usedAprDate_end").value;
            var strStartUsedDate = new String(startUsedDate);
        	var strEndUsedDate = new String(endUsedDate);
            	
            
        	if (strStartUsedDate!='') {
	        	if (!isDate("usedAprDate_start")) {
	        	  alert("${action.getText('UseDate.start')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if (strEndUsedDate!='') {
	            if (!isDate("usedAprDate_end")) {
	        	  alert("${action.getText('UseDate.start')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if ((strStartUsedDate!='' && strEndUsedDate!='')) {
	        	if (strStartUsedDate > strEndUsedDate) {
	        		alert("${action.getText('usedTime.order.error')}");
	        		return false;
	        	}
	        }		
	        var startDate = getObjByNameRe("unUseDate_start").value;
        	var endDate = getObjByNameRe("unUseDate_end").value;
        	var strStartDate = new String(startDate);
        	var strEndDate = new String(endDate);
        	if (strStartDate!='') {
	        	if (!isDate("unUseDate_start")) {
	        	  alert("${action.getText('unUseDate.start')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if (strEndDate!='') {
	            if (!isDate("unUseDate_end")) {
	        	  alert("${action.getText('unUseDate.start')}"  + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if ((strStartDate!='' && strEndDate!='')) {
	        	if (strStartDate > strEndDate) {
	        		alert("${action.getText('unusedTime.order.error')}");
	        		return false;
	        	}
	        }
     }
</script>
	