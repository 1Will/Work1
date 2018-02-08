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

<#include "../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11192 2008-03-04 01:46:57Z zbzhang $ -->


<@framePage title="${action.getText('repairMaintenanceDetail.title')}">
  <@ww.form name="'listPurchasePlanDetails'" action="'searchPurchasePlanDetails'" method="'post'">
    <@ww.token name="searchPurchasePlanDetailsToken"/>
    <#if purchasePlan.id?exists>
      <@ww.hidden name="'purchasePlan.id'" value="'#{purchasePlan.id?if_exists}'"/>
    </#if>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
     	   includeParameters="purchasePlan.id|readOnly"  fieldMap="like:" >
 	  <@vlh.checkbox property="id" name="purchasePlanDetailIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('purchasePlanDetail.serialNo')}">
        <a href="#" onclick="open_detailDialog(#{purchasePlan.id}, #{object.id});return false;">#{itemNo}</a>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo = itemNo + 1/>
      <@vcolumn title="${action.getText('purchasePlanDetail.name')}" property="name">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.specification')}" property="specification">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.model')}" property="model">
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.unitPrice')}" property="unitPrice">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.number')}" property="number">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.allPrice')}" property="allPrice">
        <@alignRight />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.planPurchaseDate')}" property="planPurchaseDate" format="yyyy-MM-dd">
        <@alignCenter />
      </@vcolumn>
      <@vcolumn title="${action.getText('purchasePlanDetail.comment')}" property="comment">
        <@alignLeft />
      </@vcolumn>
    </@list>
    <#if !first>
 	  <@buttonBar>
 	  
         <#if !(action.isReadOnly())>
        <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{purchasePlan.id}, null)"/>
        <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('purchasePlanDetail')}?" />
        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
          <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchasePlanDetailIds\", \"${confirmMessage}\");'"/>
          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        </@vsubmit>
        </#if>
      </@buttonBar>
    </#if>
    </@ww.form>
    <script language="javascript">
      window.onload = function () {
	    parent.document.forms["purchasePlan"].elements["purchasePlan.planAllFee"].value='${purchasePlan.planAllFee?if_exists}';
	  }
      function open_detailDialog(planId, purchasePlanDetailId) {
	    var url = '${req.contextPath}/popup/editPurchasePlanDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchasePlan.id='+planId;	      		 		
	    if (purchasePlanDetailId != null) {
	      url = url + "&purchasePlanDetail.id=" + purchasePlanDetailId;
	    }
	    popupModalDialog(url,800,600);
	   	var reloadUrl = "${req.contextPath}/year/device/purchasePlan/listPurchasePlanDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchasePlan.id=#{purchasePlan.id}";
	    self.location.href = reloadUrl;
	  }
    </script>
     
</@framePage>