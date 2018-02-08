<@inputTable>
	<tr>
		<@ww.textfield label="'${action.getText('spareInventory.NO')}'" name="'spareInventory.No'" value="'${req.getParameter('spareInventory.No')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('spareInventory.Name')}'" name="'spareInventory.name'" value="'${req.getParameter('spareInventory.name')?if_exists}'" cssClass="'underline'"/> 
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
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",#{loginUser.id?if_exists},\"${action.getText('allOptions')}\");'"
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
					list="allWarehouses" 
					emptyOption="false"
					required="false" 
					disabled="false">
		  </@ww.select>      
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('spareInventory.person')}'" name="'spareInventory.person'" value="'${req.getParameter('spareInventory.person')?if_exists}'" cssClass="'underline'"/> 
		<@pp.dateRanger label="'${action.getText('spareInventory.Date')}'" 
		       name="'inventoryDateTm'" 
		       value="'${req.getParameter('inventoryDateTm_start')?if_exists}|${req.getParameter('inventoryDateTm_end')?if_exists}'"
		      cssClass="'underline'" 
		      dateFormat="date"/>
	</tr>
	<tr>
		<@eam2008_onlySearchInvalid_checkBox/>
	</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">

      <#if req.getParameter('storageGrade.id')?exists>
      	var selector = getObjByName("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
       getObjByName('storageGrade.id').value = "${req.getParameter('storageGrade.id')?if_exists}";
         DWREngine.setAsync(false); 
	    		//回调 仓库
	    wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"${action.getText('allOptions')}")
	  
	    		//重新设置为异步方式
	    DWREngine.setAsync(true);
	 <#else>
	  var selector = getObjByName("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
	  
        
    </#if>
    
         //查询后保留下拉列表框的查询条件   2010-12-23
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
   		if(document.forms[0].elements["inventoryDateTm_start"].value!=""){
          if(!validateTime(document.forms[0].elements["inventoryDateTm_start"].value)){
               alert("${action.getText('spareInventory.start_EndTimeMistake')}");
               return false;
          }
      	}
      	if(document.forms[0].elements["inventoryDateTm_end"].value!=""){
         if(!validateTime(document.forms[0].elements["inventoryDateTm_end"].value)){
               alert("${action.getText('spareInventory.start_EndTimeMistake')}");
               return false;
          }
      	}
      	if(-1==getObjByName("storageGrade.id").value){
      	   getObjByName("storageGrade.id").value="";
      	}
      	if(-1==getObjByName("warehouse.id").value){
      	   getObjByName("warehouse.id").value="";
      	}
      return true;
  }     
 </script>
 