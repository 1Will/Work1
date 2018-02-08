<@inputTable>
	<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	<@ww.hidden name="'selectFlag'" value="'${selectFlag?if_exists}'"/>
	<@ww.hidden name="'inOutFlag'" value="'${inOutFlag?if_exists}'"/>
	<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
	<tr>
		<@ww.textfield label="'${action.getText('spare.spareNo')}'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('spare.spareName')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'"/> 
	   <#--
	    <@ww.textfield label="'${action.getText('spare.spareEnName')}'" name="'spareEnName'" value="'${req.getParameter('spareEnName')?if_exists}'" cssClass="'underline'"/> 
	   -->
	</tr>
	<tr> 
	    <@ww.textfield label="'${action.getText('spare.modelSpecs')}'" name="'modelSpecs'" value="'${req.getParameter('modelSpecs')?if_exists}'" cssClass="'underline'"/> 	
		<#--
		<@ww.textfield label="'${action.getText('spare.custos')}'" name="'spare.custos'" value="'${req.getParameter('spare.custos')?if_exists}'" cssClass="'underline'"/> 
		<#if toolingDevFlag =='TOOLINGDEVICE'>
		<#else>
			<@ww.textfield label="'${action.getText('spare.putPostion')}'" name="'putPostion'" value="'${req.getParameter('putPostion')?if_exists}'" cssClass="'underline'"/> 	
		</#if>
		-->
		<#--
   <#if toolingDevFlag =='TOOLINGDEVICE'> 
    <#else>
    </tr>
	<tr>
    </#if>
    -->
    <#--
	    <@ww.select label="'${action.getText('spare.category')}'"
                	name="'category.code'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('category.code')?if_exists}'"
                	list="spareType" 
                	emptyOption="false" 
                	onchange="'spareTypeValueChange(\"category.code\",\"spareDetailType.id\")'"
                	disabled="false"  />  
      -->
      	    <@ww.select label="'${action.getText('spare.category')}'"
                	name="'category.code'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('category.code')?if_exists}'"
                	list="spareType" 
                	emptyOption="false" 
                	disabled="false"  />  
       </tr>
  
 	
		<#--<@ww.checkbox label="'${action.getText('spare.selectLowStock')}'"  name="'checkbox'"  fieldValue="'false'" />
		<@ww.checkbox label="'${action.getText('spare.disabledSpare')}'"  name="'disabledSpare'" value="true"  fieldValue="'true'"/>
     <#if toolingDevFlag =='TOOLINGDEVICE'> 
    </tr>
	<tr>
    <#else>
    </#if>	  
    --> 
    <tr>
    <#--
        <@ww.select label="'${action.getText('spare.spareDetailType')}'"
	                    	name="'spareDetailType.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	value="'${req.getParameter('spareDetailType.id')?if_exists}'"
	                    	list="spareDetailType" 
	                    	emptyOption="false" 
	                    	disabled="false">
                </@ww.select>
      -->
		<@eam2008_onlySearchInvalid_checkBox/>
	
	</tr>      
	<#--  
	<tr>    	    
	    <td align="right" valign="top"/>
	    <td><input type="checkbox" name="checkbox" value="false"/><label  class="label">${action.getText('spare.selectLowStock')}</label></td>
	    <td><input type="checkbox" name="disabledSpare" value="false" checked/><label  class="label">${action.getText('spare.disabledSpare')}</label></td>        
     </tr>
     -->
    <script language="javascript">
    window.onload = function () {
    	// toSortDetailTypeBySpareType();	//对备件明细类按备件大类进行排序分类
		 if (-1 == document.forms[0].elements["category.code"].value) {
		   //document.getElementById("spareDetailType.id").length=1;
		 }
		 var checkboxVal="${req.getParameter('checkbox')?if_exists}";
         var disableCheckboxVal="${req.getParameter('disabledSpare')?if_exists}";
		 
		 var selector = document.all("category.code");
         var groups=selector.options.length;  
         <#if req.getParameter('category.code')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('category.code')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         <#--
    	 var selector = document.all("department.id");
         var groups=selector.options.length;  
         <#if req.getParameter('department.id')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}"){
               selector.options[i].selected="true";
            }
         }
         </#if>
         -->
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
         -->
         if (-1 != document.forms[0].elements["category.code"].value) {
		 	//spareTypeValueChange("category.code","spareDetailType.id");
		 }
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
         -->
         <#--<#if first>
             <#if loginUser.department?exists>
                 document.getElementById("department.id").value = #{loginUser.department.id};
             </#if>
        </#if>-->
         if(checkboxVal){
             document.getElementById("checkbox").checked=true;
         }
         <#--<#if selectFlag?exists>
         if(!disableCheckboxVal){
             document.getElementById("onlyDisabled").checked=false;
         }
         </#if>-->
         addRowsColor();  
     }
     
     
     function addRowsColor(){
           var sumStocks=document.getElementsByName("spare.hiddenStocks");
           var safeStock=document.getElementsByName("spare.safe_stock");
           
           var table = document.getElementById("vltable");
           if (typeof(table) == undefined || null == table) {
           	return ;
           }
           var objRows = table.getElementsByTagName("tr")
           for (var i = 1; i < objRows.length; i ++) {
           	if( parseInt(sumStocks[i-1].value) <= parseInt(safeStock[i-1].value) ){
           		objRows[i].className = "displayRed";
            }
           }
     }

     function checkInvalidParms(){
           if (document.getElementById("category.code").value==-1){
              document.getElementById("category.code").value='';
           }
           document.getElementById("selectFlag").value="T";
           document.getElementById("pagingPage").value="";
           return true;
     }

    </script>
</@inputTable>