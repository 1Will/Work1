<script type='text/javascript' src='${req.contextPath}/dwr/interface/findWareHouseJs.js'></script><@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('sib.spareInBillNo')}'" name="'spareInBillNo'" value="'${req.getParameter('spareInBillNo')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('sib.spareInBillName')}'" name="'spareInBillName'" value="'${req.getParameter('spareInBillName')?if_exists}'" cssClass="'underline'"/> 
	    
	</tr>
	<tr> 
  		   <#-- 库存级别 -->
  		 <@ww.select label="'${action.getText('storageGrade')}'" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false"
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${user.id?if_exists},\"${action.getText('allOptions')}\");'"
                       value="'${req.getParameter('storageGrade.id')?if_exists}'">
                       
         </@ww.select>
         
	      <#-- 仓库 -->
	      <@ww.select 
		            label="'${action.getText('warehouse')}'" 
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
	  </tr>
	  <tr>  	
		<@ww.textfield label="'${action.getText('sib.inPeople')}'" name="'inPeople'" value="'${req.getParameter('inPeople')?if_exists}'" cssClass="'underline'"/> 
	    <@ww.textfield label="'${action.getText('sib.checkPeople')}'" name="'checkPeople'" value="'${req.getParameter('checkPeople')?if_exists}'" cssClass="'underline'"/> 
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('sib.supplierName')}'" name="'supplierName'" value="'${req.getParameter('supplierName')?if_exists}'" cssClass="'underline'"/> 
		<@pp.dateRanger label="'${action.getText('sib.inDate')}'" name="'inDate'" 
		       value="'${req.getParameter('inDate_start')?if_exists}|${req.getParameter('inDate_end')?if_exists}'"
		cssClass="'underline'" dateFormat="date"/>
	</tr>
	<tr>
	  <@ww.select label="'${action.getText('status')}'" required="false" name="'inStatus'" 
		    		value="'${status?if_exists}'" listKey="value" listValue="label"
		            list="inStatus" emptyOption="false" disabled="false">
     </@ww.select>
	  <@eam2008_onlySearchInvalid_checkBox/>
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
    
      <#if req.getParameter('storageGrade.id')?exists>
      	var selector = getObjByName("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
       getObjByName('storageGrade.id').value = "${req.getParameter('storageGrade.id')?if_exists}";
         DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	    wareHouseCascadeDWR("storageGrade.id","warehouse.id",${user.id?if_exists},"${action.getText('allOptions')}")
	  
	    		//重新设置为异步方式
	    DWREngine.setAsync(true);
	 <#else>
	  var selector = getObjByName("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
	  //selector.options[0].selected="true";
        
    </#if>
    
    
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