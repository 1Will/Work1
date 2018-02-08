<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>
<@framePage title="">
	 <@ww.form name="'spares'" action="'searchListSpareFrame'" method="'post'">
        <@list title="${action.getText('备件匹配列表')}" 
                   includeParameters="id|modelSpecs|category.code|putPostion|spareDetailType.id|department.id|checkbox|spareNo|spareName|spareEnName|factory.name|toolingDevFlag|disabledSpare|selectFlag|onlyValid|onlyInvalid|spare.custos" 
                   fieldMap="like:id|modelSpecs|spareNo|spareName|putPostion|toolingDevFlag|spareEnName|factory.name|selectFlag" >          
			<#assign unitId=''/>
			<#assign orderNo=''/>
			<#assign factoryId=''/>
			<#assign factoryName=''>
			<#assign spareId=''>
			<#assign modelSpecs=''/>
			<#assign unitPrice=''/>
			<#if object.unit?exists>
            	<#assign unitId="#{object.unit.id}"/>
            </#if>
            <#if object.orderNo?exists>
            	<#assign orderNo="${object.orderNo}"/>
            </#if>
            <#if object.factory?exists>
            	<#assign factoryId="#{object.factory.id}"/>
            	<#assign factoryName="${object.factory.name}">
            </#if>
            <#if object.modelSpecs?exists>
            	<#assign modelSpecs="${object.modelSpecs}"/>
            </#if>
            <#if object.unitPrice?exists>
            	<#assign unitPrice="${object.unitPrice}"/>
            </#if>
             <@vcolumn title="${action.getText('spare.code')}" property="spareNo" sortable="desc">
                <a href="#" onclick="javascript: returnSpareValue('${object.spareNo}','${object.name}','${modelSpecs?replace('\n','')}','#{object.category.id}','${unitId}','${orderNo}','${factoryId}','${factoryName}','#{object.id}','#{object.unitPrice}');">${object.spareNo}</a>
            	<@alignLeft/>
             </@vcolumn>
              <@vcolumn title="${action.getText('spare.spareName')}" property="name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.modelSpecs')}" property="modelSpecs" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.orderNo')}"  property="orderNo">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.factory')}"  property="factory.name">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.category')}" property="category.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('所属设备')}" property="ownedEquipment"/>
            <@vcolumn title="${action.getText('设备厂家')}" property="equFactory.name"/>
            <#--
            <@vcolumn title="${action.getText('spare.spareDetailType')}" property="spareDetailType.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('spare.unit')}" property="unit.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('总库存')}" property="stocks">
                <a href="#" onclick="javascript:open_selectDialog('${object.spareNo}')">${object.stocks?if_exists}</a>
            	<@alignCenter/>
             </@vcolumn>
             <@vcolumn title="${action.getText('单价')}" property="unitPrice">
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.stocks')}" property="stocks">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.detailStocks')}"  property="">
             	<a href="#" onclick="spareHistory_openDialog(#{object.id}, '${toolingDevFlag?if_exists}', 'IN');">${action.getText('spare.inOutStock')}</a>
            	<@alignLeft/>
            </@vcolumn>
            -->

            <#--
            <@vcolumn title="${action.getText('spare.supplier')}"  property="supplier.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            -->
        </@list>
     </@ww.form>
     <script language="javascript">	
     window.onload=function(){
 //parent.frames["topFrame"].document.forms[0].elements["taoNo"].value="22222";
}
     	function open_selectDialog(spareNo) {
	 		var url = '${req.contextPath}/asset/spare/listSpareDetailSearcherCommon.html?readOnly=true&spareNo='+spareNo;
	 		popupModalDialog(url,1024,900,callback);
	 	} 
	 	function callback(){}
         function returnSpareValue(spareNo,spareName,model,categoryId,
                                   calUnitId,orderNo,
                                   factoryId,factoryName,spareId,unitPrice){
                      //设置图号
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.graphNo"].value=spareNo;
                       parent.frames["topFrame"].document.forms[0].elements["graphNoTemp"].value=spareNo;
                      //设置品名
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.name"].value=spareName;
                      //设置规格型号
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.model"].value=model;
                      //设置种类
                      if(categoryId != '' && categoryId != '-1'){
                      	parent.frames["topFrame"].document.forms[0].elements["category.id"].value=categoryId;
                      //	DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	 //  spareTypeCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["category.id"],parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"],"${action.getText('')}")
			    	    //重新设置为异步方式
			    	  // DWREngine.setAsync(true);
                      }
			    		
                      //设置明细种类
                    //  if(detailCategoryId != "" && detailCategoryId != '-1'){
                      //	parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"].value=detailCategoryId
                    //  }
                      //设置单位
                      parent.frames["topFrame"].document.forms[0].elements["calUnit.id"].value=calUnitId;
                      //设置订货号
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.orderNo"].value=orderNo;
                      //设置生产厂家
					  parent.frames["topFrame"].document.forms[0].elements["factory.id"].value=factoryId;
					  //设置生产厂家(冗余字段)
					  parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.factoryStr"].value=factoryName;
                      //设置申购关联备件
                      parent.frames["topFrame"].document.forms[0].elements["spare.id"].value=spareId; 
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.unitPrice"].value= unitPrice;        
                                   
                 }
     </script>
</@framePage>