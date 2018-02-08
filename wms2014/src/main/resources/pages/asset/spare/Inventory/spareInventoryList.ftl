<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInventory.title')}">
	 <@ww.form name="'spares'" namespace="'/spare'" action="'searchSpareInventory'" method="'post'">
	   <@ww.token name="searchSpareInventoryToken"/>
	   <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <#include "spareInventorySeacher.ftl"/>
	     <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        	 <#if !(action.isReadOnly())>
              <@redirectButton value="${action.getText('new')}" 
                 url="${req.contextPath}/spare/editInventoryBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
             </#if>  
        </@buttonBar>
        <@list title="${action.getText('spareInventory.list')}" 
                   includeParameters="spareInventory.No|spareInventory.name|spareInventory.person|storageGrade.id|warehouse.id|inventoryDateTm_start|inventoryDateTm_end|onlyValid|onlyInvalid" 
                   fieldMap="like:spareInventory.No|spareInventory.name|spareInventory.person,date:inventoryDateTm_start|inventoryDateTm_end" >
             <@vlh.checkbox property="id" name="spareInventoryIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <#if (object.disabled)>
             <@vcolumn title="${action.getText('spareInventory.NO')}" property="inventoryNo" sortable="desc">
                ${object.inventoryNo} 
               <@alignLeft />
             </@vcolumn>
            <#else>
              <@vcolumn title="${action.getText('spareInventory.NO')}" property="inventoryNo" sortable="desc">
            	<a href="editInventoryBill.html?spareInventoryBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.inventoryNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('spareInventory.Name')}" property="name" sortable="desc">
              <@alignLeft />
            </@vcolumn>   
            <#-- 仓库级别 -->
            <@vcolumn title="${action.getText('storageGrade')}" property ="storageGrade.value" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>     
            <#-- 仓库 -->
            <@vcolumn title="${action.getText('warehouse')}"  property ="warehouse.name" sortable="desc"> 
				<@alignLeft />
            </@vcolumn>       
            <@vcolumn title="${action.getText('spareInventory.person')}" property="inventoryPeople.name" sortable="desc">
             <@alignLeft />
             </@vcolumn>
            <@vcolumn title="${action.getText('spareInventory.Date')}" property="inventoryDateTm" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter />
            </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
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