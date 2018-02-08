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
<#-- $Id: supplierSelector.ftl 11157 2008-02-29 03:39:18Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<#assign purchaseBillTitle=''/>

<#if "${req.getParameter('detailType')?if_exists}"=='TOOLING_MAKE'>
	<#assign purchaseBillTitle="${action.getText('ToolingMakeDetailPurchaseConcat')}"+"${action.getText('seacher')}"/>
</#if>
<#if "${req.getParameter('detailType')?if_exists}"=='SPARE_PURCHASE'>
	<#assign purchaseBillTitle="${action.getText('sparePurchaseConcat')}"+"${action.getText('seacher')}"/>
</#if>
<#if "${req.getParameter('detailType')?if_exists}"=='REPAIR_MAINTENANCE'>
	<#assign purchaseBillTitle="${action.getText('repairMaintenancePurchaseConcat')}"+"${action.getText('seacher')}"/>
</#if>
<#if "${req.getParameter('detailType')?if_exists}"=='TECH_ALTER'>
	<#assign purchaseBillTitle="${action.getText('techAlterDetailsPurchaseConcat')}"+"${action.getText('seacher')}"/>
</#if>

<@htmlPage title="${purchaseBillTitle}">
<base target="_self">
<@ww.form name="'listForm'" namespace="'/toooling/purchaseContract'"action="'searchPurchaseBill'" method="'post'">
	<@ww.token name="searchPurchaseBillToken"/>
    <#include "subscribeDetailToPurchaseDetailSearcher.ftl" />
    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <@ww.hidden name="'detailType'" value="'${req.getParameter('detailType')?if_exists}'"/>
    <@ww.hidden name="'purchaseType'" value="'${req.getParameter('purchaseType')?if_exists}'"/>
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <#assign itemNo=1/>
 <@list title="" excel=false setupTable=false
		includeParameters="billNo|subscribeDetail.name|subscribe.name|department.id|graphNo|model|specification|detailType|category.id|requireDate_start|requireDate_end|category.id" 
		fieldMap="like:billNo|subscribeDetail.name|subscribe.name|graphNo|model|specification|,date:subscribeDate_start|subscribeDate_end">
            <@vlh.checkbox property="id" name="subscribeDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
               <@vcolumn title="${action.getText('itemNo')}">
               ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="subscribe.billNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribe.name')}" property="subscribe.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('department')}" property="subscribe.department.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('categoryName')}" property="categoryName">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name">
            	${object.name}
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('DeliveryDate')}" property="requireDate" format="yyyy-MM-dd">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('amount')}" property="amount">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice">
            	<@alignRight />
            </@vcolumn>
            <#assign surscribeToPurchaseStatus = ''/>
             <#if '${object.status}' == 'PURCHASEED'>
               <#assign surscribeToPurchaseStatus = "${action.getText('PURCHASEED')}"/>
              <#else>
                  <#assign surscribeToPurchaseStatus = "${action.getText('UNRCHASEED')}"/>
            </#if>
             <@vcolumn title="${action.getText('status')}">
             ${surscribeToPurchaseStatus}
                 <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" >
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"subscribeDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
	</#if>
</@ww.form>
  <script language="javascript">
  //alert(getObjByNameRe("purchaseType").value);
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	      	return false;
	      }
	      chooseSubscribes(boxname);
	      return true;
	    }
	    	      	
	   function chooseSubscribes(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var subscribeDtlIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   subscribeDtlIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(subscribeDtlIds,false);
	   }
        </script>
</@htmlPage>