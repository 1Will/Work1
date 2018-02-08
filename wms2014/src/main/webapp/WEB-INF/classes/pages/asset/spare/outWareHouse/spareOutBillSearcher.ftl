<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('spareOutBill.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('spareOutBill.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/> 
	</tr>
	<tr> 
 	  	<@ww.select label="'${action.getText('spareOutBill.department')}'" 
                   name="'spareOutBill.department'" 
			       value="'${req.getParameter('spareOutBill.department')?if_exists}'" 
			       listKey="id" 
			       listValue="name"
                   list="departments" 
                   emptyOption="false" 
                   disabled="false">
        </@ww.select>
	    <@ww.textfield label="'${action.getText('spareOutBill.borrower')}'" name="'borrower'" value="'${req.getParameter('borrower')?if_exists}'" cssClass="'underline'"/> 	
		
	</tr>
	<tr> 
		<@ww.textfield label="'${action.getText('spareOutBill.outPeople')}'" name="'outPeople'" value="'${req.getParameter('outPeople')?if_exists}'" cssClass="'underline'"/> 	
	    <@pp.dateRanger label="'${action.getText('spareOutBill.outDate')}'" name="'outDate'" 
		       value="'${req.getParameter('outDate_start')?if_exists}|${req.getParameter('outDate_end')?if_exists}'"
		       cssClass="'underline'" dateFormat="date"/> 
	</tr>
	<tr>
	<#-- 仓库 -->
  		 <@ww.select label="'${action.getText('storageGrade')}'" 	                   
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
    			       value="'${req.getParameter('storageGrade.id')?if_exists}'"    			     
                       list="allStorageGrade" 
                       emptyOption="false" 
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('select.option.all')}\");'"
                       disabled="false">
         </@ww.select>
	
    	 <@ww.select 
		     label="'${action.getText('warehouse')}'"
		     name="'warehouse.id'"
		     listKey="id" 
		     listValue="name"
		     value="'${req.getParameter('warehouse.id')?if_exists}'"
		     list="allWarehouse"
	 	     required="false" 
	 	     emptyOption="false"
		     disabled="false">
		</@ww.select>	
	 </tr>
	<tr>
		 <@ww.select 
		     label="'${action.getText('inWarehouse')}'"
		     name="'inWarehouse.id'"
		     listKey="id" 
		     listValue="name"
		     value="'${req.getParameter('inWarehouse.id')?if_exists}'"
		     list="allInWarehouse"
	 	     required="false" 
	 	     emptyOption="false"
		     disabled="false">
		</@ww.select>


	<@ww.select label="'${action.getText('status')}'" required="false" name="'outStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="outStatus" emptyOption="false" disabled="false">
	</@ww.select>
		</tr>
	<tr>
	<@eam2008_onlySearchInvalid_checkBox/>
	
<script language="javascript">
 <#if req.getParameter('storageGrade.id')?exists>
 		var selector = $("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
       $('storageGrade.id').value = "${req.getParameter('storageGrade.id')?if_exists}";
         DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	    wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"${action.getText('所有')}")
	  
	    		//重新设置为异步方式
	    DWREngine.setAsync(true);
       <#else>
       	var selector = $("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
    </#if>
    
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
      document.getElementById("spareOutBill.department").value = #{loginUser.department.id};
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
    
     var inWarehouseSelector = document.all("inWarehouse.id");
  	inWwarehouseGroups = inWarehouseSelector.options.length;
  	for (i=0; i<inWwarehouseGroups; i++) {
    <#if req.getParameter('inWarehouse.id')?exists>
    	if (inWarehouseSelector.options[i].value == "${req.getParameter('inWarehouse.id')?if_exists}") {
      	    inWarehouseSelector.options[i].selected="true";
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
     		if(document.forms[0].elements["inWarehouse.id"].value == -1){
        	document.forms[0].elements["inWarehouse.id"].value = '';
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
      if (document.getElementById("spareOutBill.department").value == -1) {
  	    document.getElementById("spareOutBill.department").value = '';
      } 
      if (document.getElementById("outStatus").value == -1) {
	  document.getElementById("outStatus").value = '';
	} 
      return true;
     }
</script>
</@inputTable>   