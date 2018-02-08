<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="盘点单查询">
	 <@ww.form name="'spares'" namespace="'/spare'" action="'searchOldSpareInventory'" method="'post'">
	   <@ww.token name="searchSpareInventoryToken"/>
	   <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <#include "oldSpareInventorySeacher.ftl"/>
	     <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        	 <#if !(action.isReadOnly())>
              <@redirectButton value="${action.getText('new')}" 
                 url="${req.contextPath}/spare/editOldInventoryBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
             </#if>  
        </@buttonBar>
        <@list title="盘点单列表" 
                   includeParameters="spareInventory.No|spareInventory.name|spareInventory.person|storageGrade.id|warehouse.id|inventoryDateTm_start|inventoryDateTm_end|onlyValid|onlyInvalid" 
                   fieldMap="like:spareInventory.No|spareInventory.name|spareInventory.person,date:inventoryDateTm_start|inventoryDateTm_end" >
             <@vlh.checkbox property="id" name="spareInventoryIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
             <@vcolumn title="盘点单编码" property="inventoryNo" sortable="desc">
                ${object.inventoryNo} 
               <@alignLeft />
             </@vcolumn>
            <#else>
              <@vcolumn title="盘点单编码" property="inventoryNo" sortable="desc">
            	<a href="editOldInventoryBill.html?spareInventoryBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.inventoryNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="盘点单名称" property="name" sortable="desc">
              <@alignLeft />
            </@vcolumn>   
            <#-- 仓库级别 -->
            <@vcolumn title="仓库级别" property ="storageGrade.value" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>     
            <#-- 仓库 -->
            <@vcolumn title="仓库"  property ="warehouse.name" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>       
            <@vcolumn title="盘点人" property="inventoryPeople.name" sortable="desc">
             <@alignLeft />
             </@vcolumn>
            <@vcolumn title="盘点日期" property="inventoryDateTm" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter />
            </@vcolumn>
             <@vcolumn title="总金额(元)" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
        </@list>  
          <#if !first>
       <@buttonBar>
         <#if !(action.isReadOnly())>
             <@eam2008_disabledOrEnabled_button message="${action.getText('spareInventory')}" boxName="spareInventoryIds" jsFunctionName="checkInvalidParms()"/>
         </#if>
      </@buttonBar>
      </#if> 
     </@ww.form>
</@htmlPage>