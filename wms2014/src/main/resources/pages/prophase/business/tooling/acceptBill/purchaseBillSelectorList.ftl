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
<@htmlPage title="${action.getText('acceptCancont')}">
<base target="_self">
<@ww.form namespace="'/tooling/acceptBillSelector'" name="'listForm'" action="'listPurchaseBillSelector'" method="'post'">
	<@ww.token name="supplierSelectorToken"/>
    <#include "purchaseSelectorSeacher.ftl" />
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
 <@list title="" excel=false setupTable=false
		includeParameters="billNo|name|name1|department.id|model|specification|buyer.name|purchaseDate_start|purchaseDate_end|toolingDevFlag" 
		fieldMap="like:billNo|name|name1|model|specification,date:purchaseDate_start|purchaseDate_end">
           <#assign supplierId=""/>
            <#assign model=""/>
            <#assign specification=""/>
            <#assign amount=""/>
            <#assign purchaseSupplierName=""/>
            <#assign purchaseSupplierNo=""/>
           <#assign purchaseBillNo=""/>
           <#if object.purchaseBill?exists>
             <#if object.purchaseBill.supplier?exists>
               <#assign purchaseSupplierName="${object.purchaseBill.supplier.name}"/>
             </#if>
           </#if>
            <#if  object.purchaseBill?exists>
             <#if object.purchaseBill.supplier?exists>
               <#assign purchaseSupplierNo="${object.purchaseBill.supplier.supplierNo}"/>
             </#if>
           </#if>
           <#if object.amount?exists>
            <#assign amount="${object.amount}"/>
           </#if>
            <#if object.specification?exists>
            <#assign specification="${object.specification}"/>
           </#if>
           <#if object.model?exists>
            <#assign model="${object.model}"/>
           </#if>
           <#if object.purchaseBill?exists>
           <#if object.purchaseBill.supplier?exists>
               <#assign supplierId="${object.purchaseBill.supplier.id}"/>
           </#if>
           </#if>
           <#if object.purchaseBill?exists>
             <#assign purchaseBillNo="${object.purchaseBill.billNo?if_exists}"/>
           </#if>
            <@vcolumn title="${action.getText('purchaseContract.billNo')}" property="billNo">
            	  <a href="javascript: returnDialog(new Array('${object.id}','${object.name}','${model}','${specification}','${amount}','${supplierId}','${purchaseSupplierName}','${purchaseSupplierNo}'));">${purchaseBillNo}</a>
              <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purchaseContract.name')}" property="purchaseBill.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('department')}" property="purchaseBill.department.name" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('purchaseBill.Pepole')}" property="purchaseBill.buyer.name" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('specification')}" property="specification" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingpurchaseDate')}" property="purchaseBill.purchaseDate" format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingsupplier.Name')}" property="purchaseBill.supplier.name" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('amount')}" property="amount" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" >
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('reqDeliveryDate')}" property="reqDeliveryDate" format="yyyy-MM-dd" >
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" >
            	<@alignLeft />
            </@vcolumn>
              <#assign purchasedtlStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'unSPECT'>
               <#assign purchasedtlStatus = "${action.getText('unSPECT')}"/>
              <#else>
              <#assign purchasedtlStatus = "${action.getText('INSPECTED')}"/>
            </#if>
            </#if>
         <@vcolumn title="${action.getText('purchase.state')}">
           ${purchasedtlStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
		<#if !first>
       <@buttonBar>
	      	<#--<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"purchaseBillNo\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>-->
        </@buttonBar>
	</#if>
</@ww.form>
  <script language="javascript">
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	        alert("${action.getText('tooling.noSelect')}");
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
		 var purchaseBillDtlIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   purchaseBillDtlIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(purchaseBillDtlIds,false);
	   }
        </script>
</@htmlPage>