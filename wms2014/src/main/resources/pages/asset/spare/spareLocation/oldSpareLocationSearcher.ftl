
<@inputTable>
	  <@ww.hidden name="'onlyHasStocks'" value="true"/>
	  <@ww.hidden name="'inOutFlag'" value="'${inOutFlag?if_exists}'"/>
	   <@ww.hidden name="'fromSpareInBillDetail'" value="'${req.getParameter('fromSpareInBillDetail')?if_exists}'"/>
	   <@ww.hidden name="'deptid'" value="'${req.getParameter('deptid')?if_exists}'"/>
	<tr>
		<@ww.textfield label="'备件编码'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'备件名称'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'型号'" name="'modelSpecs'" value="'${req.getParameter('modelSpecs')?if_exists}'" cssClass="'underline'"/>		 	
	</tr>
	<tr>
		
		<#if req.getParameter('fromSpareInBillDetail')?exists&&req.getParameter('fromSpareInBillDetail')=="true">
 		    
 		   <@ww.select label="'仓库'"
		        	name="'warehouse.id'"
		       	 	listKey="id" 
		        	listValue="name"
		        	value="'${req.getParameter('warehouse.id')?if_exists}'"
		        	list="allWarehouse" 
		        	emptyOption="false" 
		        	disabled="false">
		        	</@ww.select>  
		        	                                  
        	 
         <#else>
         
         	<@ww.select 
				label="'仓库'"
        		name="'warehouse.id'"
       	 		listKey="id" 
        		listValue="name"
        		value="'${req.getParameter('warehouse.id')?if_exists}'"
        		list="allWarehouse" 
        		emptyOption="false" 
        		disabled="false"
        		>
        		</@ww.select>        
         </#if>
		
       </tr>
	<tr>
		 <@ww.select 
        	label="'部门'" 
        	required="false" 
        	name="'department.id'" 
    		value="'${req.getParameter('department.id')?if_exists}'" 
    		listKey="id" 
    		listValue="name"
            list="departments" 
            emptyOption="false" 
            disabled="false">
		</@ww.select>
		<@ww.select 
			label="'种类'"
        	name="'category.code'"
       	 	listKey="id" 
        	listValue="name"
        	value="'${req.getParameter('category.code')?if_exists}'"
        	list="spareType" 
        	emptyOption="false" 
        	disabled="false" >  
		</@ww.select>
		
	</tr>
	<tr>

        <@ww.checkbox label="'仅显示库存不为零'" name="'onlyHasStocksCheckbox'" 
                      value="'true'" fieldValue="'value'" onblur="'changHasStocksOrNotStatus()'"/>  				
	</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
	  window.onload = function () {
    	// toSortDetailTypeBySpareType();	//对备件明细类按备件大类进行排序分类
		// if (-1 == document.forms[0].elements["category.code"].value) {
		 //  getObjByNameRe("spareDetailType.id").length=1;
		// }
		 var selector = document.all("category.code");
         var groups=selector.options.length;  
         <#if req.getParameter('category.code')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('category.code')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
 
       	<#if req.getParameter('deptid')?exists && req.getParameter('deptid')!=''>     	
	        document.forms[0].elements["department.id"].value=${req.getParameter('deptid')};                			        	
		    document.forms[0].elements["department.id"].selected="true"; 
     	</#if>  
       	
         //部门
	   	 var selector = document.all("department.id");
	        var groups=selector.options.length;  
	       <#if req.getParameter('department.id')?exists>
	       for (var i=0; i<groups; i++){
	           if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
	              selector.options[i].selected="true";
	          }
	         }
         </#if>
         //仓库
         var whSelector = document.all("warehouse.id");
         var whGroups = whSelector.options.length;  
         
         <#if req.getParameter('roleWarehouseId')?exists && req.getParameter('roleWarehouseId')!=''>
                document.forms[0].elements["warehouse.id"].value=${req.getParameter('roleWarehouseId')?if_exists}; 
                document.forms[0].elements["warehouse.id"].selected="true";     
         </#if>
       
         <#if req.getParameter('warehouse.id')?exists >
        	<#-- 
        	document.forms[0].elements["warehouse.id"].value=${req.getParameter('roleWarehouseId')?if_exists};                			        	
		    document.forms[0].elements["warehouse.id"].selected="true"; 
             -->
	         for (var i=0; i< whGroups; i++){
	            if (whSelector.options[i].value=="${req.getParameter('warehouse.id')?if_exists}"){
	               whSelector.options[i].selected="true";
	            }
	         }
         <#elseif req.getParameter('roleWarehouseId')?exists>
         for (var i=0; i< whGroups; i++){
            if (whSelector.options[i].value=="${req.getParameter('roleWarehouseId')?if_exists}"){
               whSelector.options[i].selected="true";
            }
         }
         </#if>
         //库区
         var regSelector = document.all("regional.id");
         var regGroups = regSelector.options.length;  
         <#if req.getParameter('regional.id')?exists>
         for (var i=0; i< regGroups; i++){
            if (regSelector.options[i].value=="${req.getParameter('regional.id')?if_exists}"){
               regSelector.options[i].selected="true";
            }
         }
         </#if>
         //下面的方法执行了两次，有点累赘，需要修改
         <#--
         var selector = document.all("spareDetailType.id");
         var groups=selector.options.length;  
         <#if req.getParameter('spareDetailType.id')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('spareDetailType.id')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         if (-1 != document.forms[0].elements["category.code"].value) {
		 	spareTypeValueChange("category.code","spareDetailType.id");
		 }
         var selector = document.all("spareDetailType.id");
         var groups=selector.options.length;  
         <#if req.getParameter('spareDetailType.id')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('spareDetailType.id')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         -->
         //页面刷新时保持仓库联动库区
		if(-1 != getObjByName('warehouse.id').value){
			//设置同步
		    DWREngine.setAsync(false); 
		    //获得仓库的值后触发DWR 级联库区 
		    WareCascadeRegionalDWR("warehouse.id","regional.id","${action.getText('select.option.all')}","search")
		    //重新设置为异步方式
		    DWREngine.setAsync(true);  
		    if('' == "${req.getParameter('regional.id')?if_exists}"){
		    	getObjByName('regional.id').value = -1;
		    }else{
				getObjByName('regional.id').value = "${req.getParameter('regional.id')?if_exists}"; 
			}
		}
     }
     
     
    function changHasStocksOrNotStatus(){
      if (getObjByNameRe("onlyHasStocksCheckbox").checked) {
        getObjByNameRe("onlyHasStocks").value = "true";
      } else {
        getObjByNameRe("onlyHasStocks").value = "";
      }
    }
   <#if (action.isOnlyHasStocks())>
     getObjByNameRe("onlyHasStocksCheckbox").checked=true;
     changHasStocksOrNotStatus();
   <#else>
     getObjByNameRe("onlyHasStocksCheckbox").checked=false;
     changHasStocksOrNotStatus();
   </#if>
</script>
		