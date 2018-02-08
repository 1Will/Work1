<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->
<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form namespace="'/runmaintenance/inventory'" name="'listToolingChange'" action="'searchInventoryDetail'" method="'post'">
	      <@ww.token name="searchInventoryDetailToken"/>
	     <@ww.hidden name="'inventoryBill.id'" value="'${inventoryBill.id?if_exists}'"/>
	     <#assign itemNo=1/>
	     
	      <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	      <@titleBar title=""/>
	      <#assign itemNo = 1/>
	      <#if toolingDevFlag?exists>
        	   <#if toolingDevFlag=='DEVICE'>
          <@list title="" excel=false setupTable=false
        	includeParameters="inventoryBill.id|toolingDevFlag" 
        	fieldMap="like:inventoryBill.id" >
            <@vlh.checkbox property="id" name="inventoryBillDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
               <a href="#" onclick="open_inventoryDetailDialog(#{inventoryBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('device.number')}" property="asset.deviceNo">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('assets_no')}" property="asset.assetNo" >
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('equipment_name')}" property="asset.name">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('equipment_model')}"  property="asset.model">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('equipment_specification')}"  property="asset.specification">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('equipment_category')}"  property="asset.deviceType.name">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('ablitly.department')}"  property="asset.department.name">
               <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('check_result')}"  property="inventoryResult">
               <@alignLeft />
              </@vcolumn> 
              <@vcolumn title="${action.getText('process_result')}"  property="handleResult">
               <@alignLeft />
              </@vcolumn> 
           </@list>
           <#else>
               <@list title="" excel=false setupTable=false
        	includeParameters="inventoryBill.id|toolingDevFlag" 
        	fieldMap="like:inventoryBill.id" >
            <@vlh.checkbox property="id" name="inventoryBillDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
               <a href="#" onclick="open_inventoryDetailDialog(#{inventoryBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('tooling.number')}" property="asset.deviceNo">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('tooling.group')}" property="asset.graphNo" >
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('tooling.model')}"  property="asset.model">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('tooling.specification')}"  property="asset.specification">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('tooling.category')}"  property="asset.toolingType.value">
              <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('check_result')}"  property="inventoryResult">
               <@alignLeft />
              </@vcolumn> 
              <@vcolumn title="${action.getText('process_result')}"  property="handleResult">
               <@alignLeft />
              </@vcolumn> 
           </@list>
           </#if>
           </#if>
           <#if toolingDevFlag?exists>
             <#if toolingDevFlag=='DEVICE'>
	         <@buttonBar>
	         <#if !(action.isReadOnly())>
	     	 <@vbutton value="${action.getText('new')}" class="button" onclick="open_inventoryDetailDialog('#{inventoryBill.id}',null)"/>   
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('DetailBill')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"inventoryBillDetailIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
	        </@buttonBar>
	        <#else>
	        <@buttonBar>
	        <#if !(action.isReadOnly())>
	     	     <@vbutton value="${action.getText('new')}" class="button" onclick="open_inventoryDetailDialog('#{inventoryBill.id}',null)"/>   
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('DetailBill')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"inventoryBillDetailIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
	        </@buttonBar>
	        </#if>
	        </#if>
     </@ww.form>
     <script language="javascript">
	         function open_inventoryDetailDialog(inventoryId, inventoryDetailId) {
	      		var url = '${req.contextPath}/runmaintenance/inventory/editinventoryDetail.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&inventoryBill.id=' + inventoryId;	 
	     
	      		if (inventoryDetailId != null) {
	      			url = url + "&inventoryBillDetail.id=" + inventoryDetailId;
	      		}
	      		popupModalDialog(url, 800,600);
	      		self.location.reload();
	      	 }
	    </script>
</@framePage>