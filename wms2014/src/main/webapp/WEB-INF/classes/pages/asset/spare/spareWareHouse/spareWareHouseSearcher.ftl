<@inputTable>
	<tr>
	    <@ww.hidden name="'onlyLessMinStock'" value="true"/>
	    <@ww.hidden name="'onlyEmptyMinStock'" value="true"/>
		<@ww.textfield label="'${action.getText('spareNo')}'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('spareName')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'" />
		<#--
		<@ww.textfield label="'${action.getText('spare.spareEnName')}'" name="'spareEnName'" value="'${req.getParameter('spareEnName')?if_exists}'" cssClass="'underline'"/> 
		-->
		<@ww.textfield label="'${action.getText('spare.modelSpecs')}'" name="'modelSpecs'" value="'${req.getParameter('modelSpecs')?if_exists}'" cssClass="'underline'"/>		 	
	</tr>
	<tr>
         	<@ww.select 
				label="'${action.getText('warehouseCode')}'"
        		name="'warehouse.id'"
       	 		listKey="id" 
        		listValue="name"
        		value="'${req.getParameter('warehouse.id')?if_exists}'"
        		list="allWarehouse" 
        		emptyOption="false" 
        		disabled="false"
        		>
        		</@ww.select>  
        	<@ww.select 
				label="'${action.getText('spare.category')}'"
	        	name="'category.code'"
	       	 	listKey="id" 
	        	listValue="name"
	        	value="'${req.getParameter('category.code')?if_exists}'"
	        	list="spareType" 
	        	emptyOption="false" 
	        	disabled="false" >  
		   </@ww.select>
		   
		   <#--
		           	<@ww.select 
				label="'${action.getText('spare.category')}'"
	        	name="'category.code'"
	       	 	listKey="id" 
	        	listValue="name"
	        	value="'${req.getParameter('category.code')?if_exists}'"
	        	list="spareType" 
	        	emptyOption="false" 
	        	onchange="'spareTypeValueChange(\"category.code\",\"spareDetailType.id\")'"
	        	disabled="false" >  
		   </@ww.select>
    	   <@ww.select 
	    		label="'${action.getText('spare.spareDetailType')}'"
	        	name="'spareDetailType.id'"
	       	 	listKey="id" 
	        	listValue="name"
	        	value="'${req.getParameter('spareDetailType.id')?if_exists}'"
	        	list="spareDetailType" 
	        	emptyOption="false" 
	        	disabled="false">
           </@ww.select>   
           -->   
            <@ww.checkbox label="'${action.getText('onlyLessMinStock')}'" name="'onlyLessMinStockCheckbox'" 
                      value="'true'" fieldValue="'value'" onblur="'onlyLessMinStockOrNotStatus()'"/> 
	</tr>
	<tr>
          <@ww.checkbox label="'${action.getText('onlyEmptyMinStock')}'" name="'onlyEmptyMinStockCheckbox'" 
                      value="'true'" fieldValue="'value'" onblur="'onlyEmptyMinStockOrNotStatus()'"/>   
	</tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">

	  window.onload = function () {
	  <#--
    	 toSortDetailTypeBySpareType();	//对备件明细类按备件大类进行排序分类
		 if (-1 == document.forms[0].elements["category.code"].value) {
		   document.getElementById("spareDetailType.id").length=1;
		 }
		 -->
		 var selector = document.all("category.code");
         var groups=selector.options.length;  
         <#if req.getParameter('category.code')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('category.code')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
          //仓库
         var whSelector = document.all("warehouse.id");
         var whGroups = whSelector.options.length;  
         <#if req.getParameter('warehouse.id')?exists>
         for (var i=0; i< whGroups; i++){
            if (whSelector.options[i].value=="${req.getParameter('warehouse.id')?if_exists}"){
               whSelector.options[i].selected="true";
            }
         }
         </#if>
         <#--
         //下面的方法执行了两次，有点累赘，需要修改
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
     }
     function onlyLessMinStockOrNotStatus(){
      if (document.getElementById("onlyLessMinStockCheckbox").checked) {
        document.getElementById("onlyLessMinStock").value = "true";
      } else {
        document.getElementById("onlyLessMinStock").value = "";
      }
         
    }
   <#if (action.isOnlyLessMinStock())>
     document.getElementById("onlyLessMinStockCheckbox").checked=true;
     onlyLessMinStockOrNotStatus();
   <#else>
     document.getElementById("onlyLessMinStockCheckbox").checked=false;
     onlyLessMinStockOrNotStatus();
   </#if>
   
   function onlyEmptyMinStockOrNotStatus(){
      if (document.getElementById("onlyEmptyMinStockCheckbox").checked) {
        document.getElementById("onlyEmptyMinStock").value = "true";
      } else {
        document.getElementById("onlyEmptyMinStock").value = "";
      }
    }
   <#if (action.isOnlyEmptyMinStock())>
     document.getElementById("onlyEmptyMinStockCheckbox").checked=true;
     onlyEmptyMinStockOrNotStatus();
   <#else>
     document.getElementById("onlyEmptyMinStockCheckbox").checked=false;
     onlyEmptyMinStockOrNotStatus();
   </#if>
</script>