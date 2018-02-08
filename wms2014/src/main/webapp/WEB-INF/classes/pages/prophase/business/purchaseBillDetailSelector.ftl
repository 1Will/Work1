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

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purBillDtl.title')}">
<@ww.form name="'listForm'" namespace="'/spare'"action="'searchPurchaseBillDetailSelector'" method="'post'">
	<@ww.token name="purchaseBillDetailSearcherToken"/>
	<@ww.hidden name="'spareBillId'" value="'${req.getParameter('spareBillId')?if_exists}'"/>
    <#include "purchaseBillDetailSearcher.ftl" />
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <#assign itemNo=1/>
 	<@list title="" excel=false setupTable=false
		includeParameters="id|spareBillId|billNo|billName|spareNo|spareName|model|specification|department|supplier|buyer|purBillDtlDate_start|purBillDtlDate_end" 
		fieldMap="like:id|billNo|billName|spareNo|spareName|model|specification|supplier|buyer,date:purBillDtlDate_start|purBillDtlDate_end">
            <@vlh.checkbox property="id" name="purchaseBillDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
               <@vcolumn title="${action.getText('itemNo')}">
               ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('purBill.billNo')}" property="purchaseBill.billNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBill.name')}" property="purchaseBill.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('备件编号')}" property="graphNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('备件名称')}" property="name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('purBillDtl.model')}" property="model">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.specification')}" property="specification">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('SGdepartment')}" property="depart.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.supplier')}" property="purchaseBill.supplier.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.buyer')}" property="purchaseBill.buyer.name">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtlDate')}" property="purchaseBill.purchaseDate" format="yyyy-MM-dd">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.amount')}" property="amount">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="到货数量" property="arrivalAmount">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.unitPrice')}" property="taxPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('purBillDtl.totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('purBillDtl.comment')}" property="comment" >
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"purchaseBillDetailIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
	</#if>
</@ww.form>
  <script language="javascript">
        function confirmSelects(boxname) {
	      if (!hasChecked(boxname)) {
	      	return false;
	      }
	      choosePurBillDetail(boxname);
	      return true;
	    }
	    	      	
	   function choosePurBillDetail(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var purBillDetailIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   purBillDetailIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(purBillDetailIds,false);
	   }
	</script>
</@htmlPage>
