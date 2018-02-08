<@inputTable>
	<tr>
		<@ww.textfield label="'报废单编码'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'报废单名称'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/> 
	</tr>
	<tr> 
 	  	<@ww.select label="'领料部门'" 
                   name="'spareOutBill.department'" 
			       value="'${req.getParameter('spareOutBill.department')?if_exists}'" 
			       listKey="id" 
			       listValue="name"
                   list="departments" 
                   emptyOption="false" 
                   disabled="false">
        </@ww.select>
	    <@ww.textfield label="'领料人'" name="'borrower'" value="'${req.getParameter('borrower')?if_exists}'" cssClass="'underline'"/> 	
		
	</tr>
	<tr> 
		<@ww.textfield label="'出库人'" name="'outPeople'" value="'${req.getParameter('outPeople')?if_exists}'" cssClass="'underline'"/> 	
	    <@pp.dateRanger label="'出库日期'" name="'outDate'" 
		       value="'${req.getParameter('outDate_start')?if_exists}|${req.getParameter('outDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	</tr>
	<tr>
	
    	 <@ww.select 
		     label="'出仓库'"
		     name="'warehouse.id'"
		     listKey="id" 
		     listValue="name"
		     value="'${req.getParameter('warehouse.id')?if_exists}'"
		     list="allWarehouse"
	 	     required="false" 
	 	     emptyOption="false"
		     disabled="false">
		</@ww.select>	

	<@ww.select label="'状态'" required="false" name="'outStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="outStatus" emptyOption="false" disabled="false">
	</@ww.select>
	</tr>	
	<tr>
	<@eam2008_onlySearchInvalid_checkBox/>
	</tr>
<script language="javascript">

    
var selector = document.all("spareOutBill.department");
    groups = selector.options.length;
    for (i=0; i<groups; i++) {
    <#if req.getParameter('spareOutBill.department')?exists>
    if (selector.options[i].value == "${req.getParameter('spareOutBill.department')?if_exists}") {
      selector.options[i].selected="true";
    }
    </#if>
    
   <#if first>
    <#if loginUser.department?exists>
      getObjByNameRe("spareOutBill.department").value = #{loginUser.department.id};
    </#if>
  </#if> 
 } 
  
  var warehouseSelector = document.all("warehouse.id");
  	warehouseGroups = warehouseSelector.options.length;
  	for (i=0; i<warehouseGroups; i++) {
    <#if req.getParameter('warehouse.id')?exists>
    	if (warehouseSelector.options[i].value == "${req.getParameter('warehouse.id')?if_exists}") {
      	    warehouseSelector.options[i].selected="true";
    	}
    </#if>
    } 
    
   var storageGradeSelector = document.all("storageGrade.id");
  	storageGradeGroups = storageGradeSelector.options.length;
  	for (i=0; i<storageGradeGroups; i++) {
    <#if req.getParameter('storageGrade.id')?exists>
    	if (storageGradeSelector.options[i].value == "${req.getParameter('storageGrade.id')?if_exists}") {
      	    storageGradeSelector.options[i].selected="true";
    	}
    </#if>
    } 
  
  var outStatusSelector = document.all("outStatus");
  	outStatusGroups = outStatusSelector.options.length;
  	for (i=0; i<outStatusGroups; i++) {
    <#if req.getParameter('outStatus')?exists>
    	if (outStatusSelector.options[i].value == "${req.getParameter('outStatus')?if_exists}") {
      	    outStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
     function checkInvalidParms(){
        <#-- 判断选择的是不是“所有”，如果是，将内容清空 -->
     	if(document.forms[0].elements["warehouse.id"].value == -1){
        	document.forms[0].elements["warehouse.id"].value = '';
     	}
     		
     	
     	if(document.forms[0].elements["storageGrade.id"].value == -1){
        	document.forms[0].elements["storageGrade.id"].value = '';
     	}
       if(document.forms[0].elements["outDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["outDate_start"].value)){
               alert("${action.getText('spareOutWareHouse.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["outDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["outDate_end"].value)){
               alert("${action.getText('spareOutWareHouse.start_EndTimeMistake')}");
               return false;
          }
      }
      if (getObjByNameRe("spareOutBill.department").value == -1) {
  	    getObjByNameRe("spareOutBill.department").value = '';
      } 
      if (getObjByNameRe("outStatus").value == -1) {
	  getObjByNameRe("outStatus").value = '';
	} 
      return true;
     }
</script>
</@inputTable>   