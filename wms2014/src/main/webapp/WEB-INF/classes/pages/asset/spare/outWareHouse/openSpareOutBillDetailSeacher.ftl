<@inputTable>
	<#--<@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>-->
	<@ww.hidden name="'selectFlag'" value="'${selectFlag?if_exists}'"/>
	<@ww.hidden name="'inOutFlag'" value="'${inOutFlag?if_exists}'"/>
	<@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>
	<@ww.hidden name="'outWarehouseId'" value="'${req.getParameter('outWarehouseId')?if_exists}'"/>
	 
	<tr>
	  	<@ww.textfield label="'${action.getText('出库单名称')}'" name="'spareOutBillName'" value="'${req.getParameter('spareOutBillName')?if_exists}'" cssClass="'underline'"/>  
	    <@ww.textfield label="'${action.getText('出库单编号')}'" name="'spareOutBillCode'" value="'${req.getParameter('spareOutBillCode')?if_exists}'" cssClass="'underline'"/> 
	    <#--
		<@ww.select label="'${action.getText('部门')}'"
	                    	name="'department.id'"
	                   	 	listKey="id" 
	                    	listValue="name"
	                    	value="'${req.getParameter('department.id')?if_exists}'"
	                    	list="allDepartment" 
	                    	emptyOption="false" 
	                    	disabled="false"  /> 
	     -->
	     <@ww.textfield label="'${action.getText('图号')}'" name="'spareNo'" value="'${req.getParameter('spareNo')?if_exists}'" cssClass="'underline'"/>  
	</tr>
	 
	<tr>
	    <@ww.textfield label="'${action.getText('名称')}'" name="'spareName'" value="'${req.getParameter('spareName')?if_exists}'" cssClass="'underline'"/> 
	    <@ww.textfield label="'${action.getText('型号')}'" name="'spareModelSpecs'" value="'${req.getParameter('spareModelSpecs')?if_exists}'" cssClass="'underline'"/> 
	  	<@ww.select label="'${action.getText('spareOutBillDetail.spareType')}'"
                	name="'spareType.id'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('spareType.id')?if_exists}'"
                	list="spareType" 
                	emptyOption="false" 
                	disabled="false" >  
                	
         </@ww.select> 
	</tr>
	<tr> 
	 <#--
	       
	     <@ww.select label="'${action.getText('spareOutBillDetail.spareType')}'"
                	name="'spareType.id'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('spareType.id')?if_exists}'"
                	list="spareType" 
                	emptyOption="false" 
                	onchange="'spareTypeValueChange(\"spareType.id\",\"spareDetailType.id\")'"
                	disabled="false" >  
                	
         </@ww.select>  
         <@ww.select label="'${action.getText('spareOutBillDetail.spareDetailType')}'"
                     required="false" 
                	name="'spareDetailType.id'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('spareDetailType.id')?if_exists}'"
                	list="spareDetailType" 
                	emptyOption="false" 
                	disabled="false">
         </@ww.select>     
            -->
	     <@ww.select label="'${action.getText('来源仓库')}'"
                	name="'wareHouse.id'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('wareHouse.id')?if_exists}'"
                	list="wareHouses" 
                	emptyOption="false" 
                	disabled="false" >  
                	
         </@ww.select>  
	    	 
	</tr>
	<#--
	<tr>
		 
        
         <@ww.select label="'${action.getText('库位')}'"
                     required="false" 
                	name="'location.id'"
               	 	listKey="id" 
                	listValue="name"
                	value="'${req.getParameter('location.id')?if_exists}'"
                	list="locations" 
                	emptyOption="false" 
                	disabled="false">
         </@ww.select>  
     
	     

     
	</tr>     --> 
 
    <script language="javascript">
             //来源仓库
         var whSelector = document.all("wareHouse.id");
         var whGroups = whSelector.options.length;  
         <#if req.getParameter('warehouse.id')?exists>
         for (var i=0; i< whGroups; i++){
            if (whSelector.options[i].value=="${req.getParameter('warehouse.id')?if_exists}"){
               whSelector.options[i].selected="true";
            }
         }
         </#if>
     window.onload = function () {
    	// toSortDetailTypeBySpareType();	//对备件明细类按备件大类进行排序分类
		// if (-1 == document.forms[0].elements["spareType.id"].value) {
		//   document.getElementById("spareDetailType.id").length=1;
		// }
		 
		 
		 var selector = document.all("spareType.id");
         var groups=selector.options.length;  
         <#if req.getParameter('spareType.id')?exists>
         for (var i=0; i<groups; i++){
            if (selector.options[i].value=="${req.getParameter('spareType.id')?if_exists}"){
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
         if (-1 != document.forms[0].elements["spareType.id"].value) {
		 	spareTypeValueChange("spareType.id","spareDetailType.id");
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
       
        
         //addRowsColor();  
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
           if (document.getElementById("spareType.id").value==-1){
              document.getElementById("spareType.id").value='';
           }
          // if (document.getElementById("spareDetailType.id").value==-1){
         //     document.getElementById("spareDetailType.id").value='';
        //   }
           
          // if (document.getElementById("department.id").value==-1){
          //    document.getElementById("department.id").value='';
        //   }
           if (document.getElementById("wareHouse.id").value==-1){
              document.getElementById("wareHouse.id").value='';
           }
          
           return true;
     }

    </script>
    <#--
    <#include "../../commonSpareType.ftl"/>
    -->
</@inputTable>