<script type='text/javascript' src='${req.contextPath}/dwr/interface/findWareHouseJs.js'></script><@inputTable>
	<tr>
		<@ww.textfield label="'入库单编码'" name="'spareInBillNo'" value="'${req.getParameter('spareInBillNo')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'入库单名称'" name="'spareInBillName'" value="'${req.getParameter('spareInBillName')?if_exists}'" cssClass="'underline'"/> 
	    
	</tr>
	<tr> 
	      <#-- 仓库 -->
	      <@ww.select 
		            label="'入仓库'" 
					required="true" 
					name="'warehouse.id'" 
					value="'${req.getParameter('warehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouse" 
					emptyOption="false"
					required="false" 
					disabled="false">
		  </@ww.select>    
	  	
		<@ww.textfield label="'入库人'" name="'inPeople'" value="'${req.getParameter('inPeople')?if_exists}'" cssClass="'underline'"/> 
	     </tr>
	  <tr> 
	    <@ww.textfield label="'验收人'" name="'checkPeople'" value="'${req.getParameter('checkPeople')?if_exists}'" cssClass="'underline'"/> 
	
		<@ww.textfield label="'供应商名称'" name="'supplierName'" value="'${req.getParameter('supplierName')?if_exists}'" cssClass="'underline'"/> 
		</tr>
	<tr>
		<@pp.dateRanger label="'入库日期'" name="'inDate'" 
		       value="'${req.getParameter('inDate_start')?if_exists}|${req.getParameter('inDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/>
	
	  <@ww.select label="'状态'" required="false" name="'inStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="inStatus" emptyOption="false" disabled="false">
		            </tr>
	<tr>
     </@ww.select>
	  <@eam2008_onlySearchInvalid_checkBox/>
	
	<input type="hidden" name="oldWhouseCode" value="${req.getParameter('oldWhouseCode')?if_exists}"/>
</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">

    var inStatusSelector = document.all("inStatus");
  	inStatusGroups = inStatusSelector.options.length;
  	for (i=0; i<inStatusGroups; i++) {
    <#if req.getParameter('inStatus')?exists>
    	if (inStatusSelector.options[i].value == "${req.getParameter('inStatus')?if_exists}") {
      	    inStatusSelector.options[i].selected="true";
    	}
    </#if>
    }
    
     
    
    
     //查询后保留下拉列表框的查询条件 hjia 2010-5-19
    var warehouseSelector = document.all("warehouse.id");
  	warehouseGroups = warehouseSelector.options.length;
  	for (i=0; i<warehouseGroups; i++) {
    <#if req.getParameter('warehouse.id')?exists>
    	if (warehouseSelector.options[i].value == "${req.getParameter('warehouse.id')?if_exists}") {
      	    warehouseSelector.options[i].selected="true";
    	}
    </#if>
    }  
  
  function checkInvalidParms(){
  
    <#-- 判断选择的是不是“所有”，如果是，将内容清空 -->
    if(document.forms[0].elements["storageGrade.id"].value == -1){
        	document.forms[0].elements["storageGrade.id"].value = '';
    }
    
    if(document.forms[0].elements["warehouse.id"].value == -1){
        	document.forms[0].elements["warehouse.id"].value = '';
    }

   if(document.forms[0].elements["inDate_start"].value!=""){
          if(!validateTime(document.forms[0].elements["inDate_start"].value)){
               alert("${action.getText('spareInBill.start_EndTimeMistake')}");
               return false;
          }
      }
      if(document.forms[0].elements["inDate_end"].value!=""){
         if(!validateTime(document.forms[0].elements["inDate_end"].value)){
               alert("${action.getText('spareInBill.start_EndTimeMistake')}");
               return false;
          }
      }
    if (getObjByNameRe("inStatus").value == -1) {
	  getObjByNameRe("inStatus").value = '';
	}
      return true;
  }
       
 </script>