<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>
<@framePage title="">
	 <@ww.form name="'spare'" action="'searchOldSpareLocationToFrames'" method="'post'">
	 
	   <#assign itemNo=0/>
	   <#assign spareNameTest='spareName' + '${itemNo}'/>
	   <#assign spareModelTest='spareModel'+'${itemNo}'/>
	   
        <@list title="备件匹配列表" 
                   includeParameters="id|name|model|orderNo|factory.name|category.id|spareDetailType.id|spareEnName|toolingDevFlag|disabledSpare|selectFlag|onlyValid|onlyInvalid|spare.custos|warehouse.id" 
                   fieldMap="like:model|orderNo|name|factory.name" >          
			<#assign itemNo = itemNo+1 />
			<#assign spareModelTest='spareModel'+'${itemNo}'/>
			<#assign spareNameTest='spareName' + '${itemNo}'/>
			
			<#assign ary=''/>
			<#assign spareNo='${object.spare.spareNo}'/>
			<#assign spareName=''/>
			<#assign modelSpecs=''/>
			<#assign categoryId='#{object.spare.category.id}'/>
			<#assign unitId=''/>
			<#assign factoryId=''/>
			<#assign factoryName=''>
			<#assign orderNo=''/>
		    <#assign objectId='#{object.id}'/>
		    
		    <#assign price='0.00'/>
		    <#assign equipmentName=''/>
		    <#assign equFactoryName=''/>
		 
			<#assign storageGradeId=''/>
			<#assign warehouseId=''/>
			<#assign regionalId=''/>
			<#assign locationId=''/>
			<#assign locationCode=''/>
			
			<#assign beforeNum='0'/>
			<#assign beforeTotalPrice='0.00'/>
			
			
			
			<#if object.spare.unit?exists>
            	<#assign unitId="#{object.spare.unit.id}"/>
            </#if>

            <#if object.spare.orderNo?exists>
            	<#assign orderNo="${object.spare.orderNo}"/>
            </#if>
            
            <#if object.spare.factory?exists>
            	<#assign factoryId="#{object.spare.factory.id}"/>
            	<#assign factoryName="${object.spare.factory.name}">
            </#if>
            
            <#if object.spare?exists>
                <#--
            	<#assign modelSpecs="${object.spare.modelSpecs?replace('\n','')}"/>-->
            </#if>
            
             <#if object.unitPrice?exists>
            	<#assign price="${object.unitPrice}"/>
            </#if>
            
            <#if object.warehouse?exists>
            	<#assign warehouseId='#{object.warehouse.id}'/>
            	<#assign storageGradeId='#{object.warehouse.storageGrade.id}'/>
            </#if>
            
             <#if object.regional?exists>
            	<#assign regionalId='#{object.regional.id}'/>
            </#if>
            
            <#if object.location?exists>
            	<#assign locationId='#{object.location.id}'/>
            	<#assign locationCode='${object.location.code}'/>
            </#if>
             
            <#if object.stocks?exists>
            	<#assign beforeNum='#{object.stocks}'/>
            </#if> 
           <#--
            <input type="hidden" name="${spareNameTest}" id="" value="${object.spare.name}"/>-->
            
            <#assign ary = ary +'${spareNo}'+'$$'+'${spareName}'+'$$'+'${modelSpecs}'+'$$'+'${categoryId}'+'$$'+'$$'+'${unitId}'+'$$'/>
            <#assign ary = ary+'${factoryId}'+'$$'+'${factoryName}'+'$$'+'${orderNo}'+'$$'+'${objectId}'+'$$'+'${storageGradeId}'+'$$'+'${warehouseId}'+'$$'/>
            <#assign ary = ary+'${regionalId}'+'$$'+'${locationId}'+'$$'+'${locationCode}'+'$$'+'${price}'+'$$'+'${equipmentName}'+'$$'+'${equFactoryName}'+'$$'/>
             <#assign ary = ary+'${beforeNum}'/>
           <@vcolumn title="${action.getText('code')}" sortable="desc">
            <#--  <a href="#" 
                onclick="javascript: returnSpareValue('${object.spare.spareNo}','${object.spare.name}','${object.spare.modelSpecs?replace('\n','')}','#{object.spare.category.id}','#{object.spare.spareDetailType.id}','${unitId}','${factoryId}','${factoryName}','${object.spare.orderNo}','#{object.id}','#{object.warehouse.storageGrade.id}','#{object.warehouse.id}','#{object.regional.id}','#{object.location.id}','${object.location.code}','${price}');">
                   ${object.spare.spareNo}</a> -->
              <a href="#"  onclick="javascript: returnSpareValueTest('${ary}',${itemNo});">
                   ${object.spare.spareNo}
              </a> 
            	<@alignLeft/>
             </@vcolumn>
             
              <@vcolumn title="${action.getText('name')}" property="spare.name" sortable="desc">
                 <@vlh.attribute name="id" value="${spareNameTest}" />
              	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('model')}" property="spare.modelSpecs" sortable="desc">
               <@vlh.attribute name="id" value="${spareModelTest}" />
            	<@alignLeft/>
            </@vcolumn>
            
              <@vcolumn title="${action.getText('orderNo')}"  property="spare.orderNo" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('factory')}"  property="spare.factory.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('category')}" property="spare.category.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('明细种类')}" property="spare.spareDetailType.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('unit')}" property="spare.unit.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('盘点前数量')}" property="stocks" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('盘点前金额')}" property="money" sortable="desc">
              #{object.stocks*object.unitPrice}
            	<@alignLeft/>
            </@vcolumn>
           
       </@list>
     </@ww.form>
     <script language="javascript">
         
             <#--自定义一个trim函数--> 
             String.prototype.Trim = function() {
	             return this.replace(/(^\s*)|(\s*$)/g, "");
	         }
         
         
           function returnSpareValueTest(ary,item){
               var nameTag = "spareName";
               var modelTag ="spareModel";
               
               var arry = ary.split("$$");
              //设置图号
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.code"].value=arry[0];
              //设置品名
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.name"].value=getObjByName(nameTag+parseInt(item)).innerText.Trim();
              //设置规格型号
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.model"].value=getObjByName(modelTag+parseInt(item)).innerText.Trim();
              //设置种类
              if(arry[3] != '' && arry[3] != '-1'){
              	parent.frames["topFrame"].document.forms[0].elements["category.id"].value=arry[3];
              	//DWREngine.setAsync(false); 
	    	    //回调种类的值后触发DWR 级联明细种类  
	    	  // spareTypeCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["category.id"],
	    	          //parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"],"")
	    	    //重新设置为异步方式
	    	   //DWREngine.setAsync(true);
              }
	    		
              //设置明细种类
             // if(arry[4] != "" && arry[4] != '-1'){
              //	parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"].value=arry[4]
            //  }
              //设置单位
              parent.frames["topFrame"].document.forms[0].elements["calUnit.id"].value=arry[5];
              //设置订货号
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.orderNo"].value=arry[8];
              //设置生产厂家
			  parent.frames["topFrame"].document.forms[0].elements["factory.id"].value=arry[6];
			  parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.factoryName"].value=arry[7];
			  
              //设置申购关联备件
              parent.frames["topFrame"].document.forms[0].elements["spareLocation.id"].value=arry[9]; 
               <#--仓库级别级联仓库
              parent.frames["topFrame"].document.forms[0].elements["storageGrade.id"].value=arry[10]; 
             // alert(arry[10]);
              	DWREngine.setAsync(false); 
	    	    //回调种类的值后触发DWR 级联明细种类  
	    	   wareHouseCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["storageGrade.id"],
	    	       parent.frames["topFrame"].document.forms[0].elements["warehouse.id"],#{loginUser.id?if_exists},"");
		    	    //重新设置为异步方式
		    	   DWREngine.setAsync(true);
		    	   -->
 	             <#--仓库级联库区
              parent.frames["topFrame"].document.forms[0].elements["warehouse.id"].value=arry[11];  
             //  alert(arry[11]);
              
              	DWREngine.setAsync(false); 
	    	    //回调种类的值后触发DWR 级联明细种类  
	    	   WareCascadeRegionalDWR(parent.frames["topFrame"].document.forms[0].elements["warehouse.id"],
	    	          parent.frames["topFrame"].document.forms[0].elements["regional.id"],"","edit")
	    	    //重新设置为异步方式
	    	   DWREngine.setAsync(true);
	    	   -->
     
               <#--库区级联库位
               	DWREngine.setAsync(false); 
	    	    //回调种类的值后触发DWR 级联明细种类  
	    	   
	    	    //重新设置为异步方式
	    	   DWREngine.setAsync(true);
	    	   --> 
             
              
              
               //设置单价
               if("" == arry[15]){
                  arry[15]="0.00";
               }
               
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.unitPrice"].value=arry[15];  
              //盘点前数量
              if(""==arry[18]){
                 arry[18]="0";
              }
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.actualNumber"].value=arry[18]; 
              //盘点前金额
              parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.actualTotalPrice"].value=(parseInt(arry[18])*parseFloat(arry[15])); 
                   
                                   
        }
         
         
         
         
         
         
         function returnSpareValue(spareNo,spareName,model,categoryId,
                                   detailCategoryId,calUnitId,factoryId,
                                   factoryName,orderNo,spareLocationId,
                                   storageGradeId,warehouseId,regionalId,
                                   locationId,locationCode,price){
                      //设置图号
                      parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.code"].value=spareNo;
                      //设置品名
                      parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.name"].value=spareName;
                      //设置规格型号
                      parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.model"].value=model;
                      //设置种类
                      if(categoryId != '' && categoryId != '-1'){
                      	parent.frames["topFrame"].document.forms[0].elements["category.id"].value=categoryId;
                      	//DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	   //spareTypeCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["category.id"],
			    	          //parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"],"")
			    	    //重新设置为异步方式
			    	   //DWREngine.setAsync(true);
                      }
			    		
                      //设置明细种类
                    //  if(detailCategoryId != "" && detailCategoryId != '-1'){
                      //	parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"].value=detailCategoryId
                    //  }
                      //设置单位
                      parent.frames["topFrame"].document.forms[0].elements["calUnit.id"].value=calUnitId;
                      //设置订货号
                      parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.orderNo"].value=orderNo;
                      //设置生产厂家
					  parent.frames["topFrame"].document.forms[0].elements["factory.id"].value=factoryId;
					  parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.factoryName"].value=factoryName;
					  
                      //设置申购关联备件
                      parent.frames["topFrame"].document.forms[0].elements["spareLocation.id"].value=spareLocationId; 
                       <#--仓库级别级联仓库-->
                      parent.frames["topFrame"].document.forms[0].elements["storageGrade.id"].value=storageGradeId; 
                      	DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	   wareHouseCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["storageGrade.id"],
			    	       parent.frames["topFrame"].document.forms[0].elements["warehouse.id"],#{loginUser.id?if_exists},"");
			    	    //重新设置为异步方式
			    	   DWREngine.setAsync(true);
			    	   
	 	             <#--仓库级联库区-->
                      parent.frames["topFrame"].document.forms[0].elements["warehouse.id"].value=warehouseId;  
                      
                      	DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	   WareCascadeRegionalDWR(parent.frames["topFrame"].document.forms[0].elements["warehouse.id"],
			    	          parent.frames["topFrame"].document.forms[0].elements["regional.id"],"","edit")
			    	    //重新设置为异步方式
			    	   DWREngine.setAsync(true);
			    	   
			    	   
                      parent.frames["topFrame"].document.forms[0].elements["regional.id"].value=regionalId; 
                       <#--库区级联库位--> 
                       	DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	   WareCascadeLocationDWR(parent.frames["topFrame"].document.forms[0].elements["regional.id"],
			    	        parent.frames["topFrame"].document.forms[0].elements["location.id"],"")
			    	    //重新设置为异步方式
			    	   DWREngine.setAsync(true);
			    	   
                      parent.frames["topFrame"].document.forms[0].elements["location.id"].value=locationId;    
                       //设置单价
                      parent.frames["topFrame"].document.forms[0].elements["spareInventoryDetail.unitPrice"].value=price;        
                                   
                 }
     </script>
</@framePage>