<@inputTable>
 		<tr>
 			<@ww.textfield label="'${action.getText('regional.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'" required="false"/>
 			<@ww.textfield label="'${action.getText('regional.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" required="false"/>
 		</tr>
 		</tr>
 		   <#-- 库存级别 -->
  		 <@ww.select label="'${action.getText('regional.storageGrade')}'" 
	                   name="'storageGrade.id'" 
    			       listKey="id" 
    			       listValue="value"
                       list="allStorageGrade" 
                       emptyOption="true" 
                       disabled="false"
                       onchange="'wareHouseCascadeDWR(\"storageGrade.id\",\"warehouse.id\",${loginUser.id?if_exists},\"${action.getText('allOptions')}\");'"
                       value="'${req.getParameter('storageGrade.id')?if_exists}'">
                       
         </@ww.select>
         
	      <#-- 仓库 -->
	      <@ww.select 
		            label="'${action.getText('regional.warehouse')}'" 
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
			<@eam2008_onlySearchInvalid_checkBox/>
		</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">

    <#if req.getParameter('storageGrade.id')?exists>
       getObjByName('storageGrade.id').value = "${req.getParameter('storageGrade.id')?if_exists}";
         DWREngine.setAsync(false); 
	    		//回调种类的值后触发DWR 级联明细种类  
	    wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"${action.getText('allOptions')}")
	  
	    		//重新设置为异步方式
	    DWREngine.setAsync(true);
	 <#else>
	  var selector = getObjByName("storageGrade.id");
	    selector.options[0].value=-1;
	    selector.options[0].text="所有";
	  //selector.options[0].selected="true";
        
    </#if>
    <#--查询后保留查询条件 张忠斌 2011-01-24-->
    <#if req.getParameter('warehouse.id')?exists>
      getObjByName("warehouse.id").value="${req.getParameter('warehouse.id')?if_exists}";
    </#if>
     
  function checkInvalidParms(){
   if(getObjByName("storageGrade.id").value == -1) {
	  getObjByName("storageGrade.id").value = '';
	}
    if(getObjByName("warehouse.id").value == -1) {
	  getObjByName("warehouse.id").value = '';
	}
      return true;
  }   
</script>