<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<script type='text/javascript' src='${req.contextPath}/dwr/interface/SpareDetailSelectJs.js'></script>
<@framePage title="">
	 <@ww.form name="'spares'" action="'searchListSpareFrame'" method="'post'">
        <@list title="${action.getText('备件匹配列表')}" 
                   includeParameters="id|name|model|spareNo|factory.name|category.id|spareDetailType.id|spareEnName|toolingDevFlag|disabledSpare|selectFlag|onlyValid|onlyInvalid|spare.custos" 
                   fieldMap="like:model|spareNo|name|factory.name" >          
			<#assign unitId=''/>
			<#assign orderNo=''/>
			<#assign factoryId=''/>
			<#assign factoryName=''>
			<#assign spareId=''>
			<#assign modelSpecs=''/>
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
             <@vcolumn title="${action.getText('code')}" property="spareNo" sortable="desc">
                <a href="#" onclick="javascript: returnSpareValue('${object.spareNo}','${object.name}','${modelSpecs?replace('\n','')}','#{object.category.id}','${unitId}','${orderNo}','${factoryId}','${factoryName}','#{object.id}');">${object.spareNo}</a>
            	<@alignLeft/>
             </@vcolumn>
              <@vcolumn title="${action.getText('name')}" property="name" sortable="desc">
              	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="modelSpecs" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
              <@vcolumn title="${action.getText('orderNo')}"  property="orderNo" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('factory')}"  property="factory.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('category')}" property="category.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('detailType')}" property="spareDetailType.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('unit')}" property="unit.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('库位')}" property="locationCode" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('盘点前数量')}" property="actualNumber" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('盘点前数量')}" property="actualNumber" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('盘点前金额')}" property="actualTotalPrice" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
           
           
        </@list>
     </@ww.form>
     <script language="javascript">	
         function returnSpareValue(spareNo,spareName,model,categoryId,
                                   calUnitId,orderNo,
                                   factoryId,factoryName,spareId){
                      //设置图号
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.graphNo"].value=spareNo;
                      //设置品名
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.name"].value=spareName;
                      //设置规格型号
                      parent.frames["topFrame"].document.forms[0].elements["subscribeDtl.model"].value=model;
                      //设置种类
                      if(categoryId != '' && categoryId != '-1'){
                      	parent.frames["topFrame"].document.forms[0].elements["category.id"].value=categoryId;
                      	//DWREngine.setAsync(false); 
			    	    //回调种类的值后触发DWR 级联明细种类  
			    	   //spareTypeCascadeDWR(parent.frames["topFrame"].document.forms[0].elements["category.id"],parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"],"${action.getText('')}")
			    	    //重新设置为异步方式
			    	  // DWREngine.setAsync(true);
                      }
			    		
                      //设置明细种类
                     // if(detailCategoryId != "" && detailCategoryId != '-1'){
                     // 	parent.frames["topFrame"].document.forms[0].elements["detailCategory.id"].value=detailCategoryId
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
                                   
                 }
     </script>
</@framePage>