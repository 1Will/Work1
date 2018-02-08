<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: subscribeDetails.ftl 11311 2008-03-13 13:19:59Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />
<@framePage title="${action.getText('repairPurchaseMaintenanceDetail.title')}">
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   	<script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script> 
	<@ww.form namespace="'/tooling/prophase/business'" name="'listForm'" action="'searchPurchaseRepairMaintenanceDetails'" method="'post'">
		<@ww.token name="searchPurchaserepairPurchaseMaintenanceDetailToken"/>
		<#--
		<@ww.hidden name="'detailType'" value="'${req.getParameter('detailType')?if_exists}'"/>
		-->
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'addQuarterPlanDetailIds'" value=""/>
		<@ww.hidden name="'quarterPlanDetailSelector'" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailIds" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailUnitPrice" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailAmount" value=""/>
		<input type="hidden" name="allPurToolingMakeDetailRequestDate" value=""/>
		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
		<#if subscribe.id?exists>
			<@ww.hidden name="'subscribe.id'" value="'#{subscribe.id}'"/>
		</#if>
		<#assign itemNo=1/>
		<#--
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
		-->
		<@list title="" excel=false setupTable=false includeParameters="subscribe.id" fieldMap="">
		<@vlh.checkbox property="id" name="purToolingPurchaseMakeDtlIds">
             <@vlh.attribute name="width" value="30" />
        </@vlh.checkbox>
	    <@vcolumn title="${action.getText('itemNo')}">
            <a href="#" onclick="return open_toolingMakeDtlDialog(#{subscribe.id}, #{object.id})">${itemNo}</a>
            <@alignCenter />
        </@vcolumn>
        <#assign itemNo = itemNo + 1/>
        <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('categoryName')}" property="categoryName">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('detailCategoryName')}" property="detailCategoryName">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('model')}" property="model">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('specification')}" property="specification">
        	<@alignLeft />
        </@vcolumn>
         <@vcolumn title="${action.getText('unit')}" property="calUnit.value">
        <@alignLeft />
      </@vcolumn>       
        <#assign unitPrice = ''/>
        <#assign amount = ''/>
        <#if '${object.unitPrice}'=='0'>
            <#assign unitPrice = ''/>
        <#else>
            <#assign unitPrice = '${object.unitPrice?if_exists}'/>
        </#if>
        <#if '${object.amount}'=='0'>
            <#assign amount = ''/>
        <#else>
            <#assign amount = '${object.amount?if_exists}'/>
        </#if>
        <@vcolumn title="${action.getText('unitPrice')}">
        	${unitPrice}
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('amount')}">
        	${amount}
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
        	<@alignRight />
        </@vcolumn>
		<@vcolumn title="${action.getText('reqDate')}" property="requireDate" format="yyyy-MM-dd" attributes="width='80'">
			<@alignCenter />
       </@vcolumn>
       <@vcolumn title="${action.getText('reqReason')}" property="reqReason" attributes="width='100'">
        	<@alignLeft />
       </@vcolumn>
       <@vcolumn title="${action.getText('comment')}" property="comment" attributes="width='100'">
        	<@alignLeft />
       </@vcolumn>
       <#assign surscribeToPurchaseStatus = ''/>
       <#if '${object.status}' == 'PURCHASEED'>
           <#assign surscribeToPurchaseStatus = "${action.getText('PURCHASEED')}"/>
          <#else>
              <#assign surscribeToPurchaseStatus = "${action.getText('UNRCHASEED')}"/>
       </#if>
         <@vcolumn title="${action.getText('status')}" attributes="width='50'">
         ${surscribeToPurchaseStatus}
             <@alignLeft/>
       </@vcolumn>
       </@list>
         <#if !first>
    	 <#if !(action.isReadOnly())>
       <@buttonBar>
    	 	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_toolingMakeDtlDialog(#{subscribe.id}, null)"/>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('detelePurchaseDetail')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"purToolingPurchaseMakeDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vbutton name="'new'"  class="button" value="${action.getText('fromQuanterPlanSelect')}" onclick="open_quarterPlanToolingMakeDtlDialog()"/>
       </@buttonBar>
        </#if>
        </#if>
	</@ww.form>
</@framePage>
<script language="javascript">
	window.onload = function(){
		<#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.PurchaseMainten.failure')}");
        </#if>
   		//更新当前采购单状态
   		<#if subscribe.status?exists>
   		    <#assign subscribeStatus = ''/>
   			<#if '${subscribe.status}' == 'NEWPURCHASE'>
   			<#assign subscribeStatus = "${action.getText('NEWPURCHASE')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASING'>
   			<#assign subscribeStatus = "${action.getText('PURCHASING')}"/>
   			<#elseif '${subscribe.status}' == 'PURCHASEED'>
   			<#assign subscribeStatus = "${action.getText('PURCHASEED')}"/>
   			</#if>
   			parent.document.forms["subscribe"].elements["subscribe.status"].value = '${subscribeStatus}';
   		</#if>
   		<#if subscribe.id?exists>
             parent.getObjByNameRe("subscribe.totalPrice").value = '${(subscribe.totalPrice?string('#,###,##0.00'))?if_exists}';
        </#if>
	}
	//新建、更新
	function open_toolingMakeDtlDialog(subscribeId,toolingMakeDtlId){
		 var url = '${req.contextPath}/tooling/prophase/business/editPurchaseRepairMaintenanceDetail.html?subscribe.id='+subscribeId+'&readOnly=${req.getParameter('readOnly')?if_exists}';	
		 if(toolingMakeDtlId != null){
		 	url = url + "&subscribeDtl.id=" + toolingMakeDtlId
		 }
		 popupModalDialog(url,800,600);
		 var reloadUrl = "${req.contextPath}/tooling/prophase/business/listPurchaseRepairMaintenanceDetails.html?subscribe.id=#{subscribe.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
		//如果是点击项目号进入维护页面，保存当前页，已使在维护页面点击关闭，会回到当前页
        if (null != toolingMakeDtlId) {
        	reloadUrl = reloadUrl + '&pagingPage=${pagingPage?if_exists}'
        }
		self.location.href = reloadUrl;
	}
	//从季度计划选择
	function open_quarterPlanToolingMakeDtlDialog(){
		var url = '${req.contextPath}/tooling/prophase/business/listPurchaseRepairMaintenanceDetailFromQuarterPlanDetail.html?detailType=REPAIR_MAINTENANCE&subscribeId='+#{subscribe.id};
		popupModalDialog(url, gw, gh, refresh_purchaseToolingMakeDtl_information);
	}
	function  refresh_purchaseToolingMakeDtl_information(result){
	  if(null!=result){
	     var purBillToolingMakeDtlIds = result.substring(0, result.lastIndexOf(","));
	     document.forms[0].elements["addQuarterPlanDetailIds"].value = purBillToolingMakeDtlIds;
         document.forms[0].elements["quarterPlanDetailSelector"].value = "quartePlanDetail";
         document.forms[0].submit();
	  }
	}
</script>