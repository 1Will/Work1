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
<#include "../../../../includes/eam2008.ftl" />
<base target="_self">
<@htmlPage title="${action.getText('SparePurchaseDetail')}">
  <@ww.form  name="'listQuarterPlanChoose'" action="'searchPurchaseContractDtlOfSparePurchase'" method="'post'">
    <@ww.token name="searchQuarterPlansToken"/>   
      <@ww.hidden name="'detailType'" value="'${req.getParameter('detailType')?if_exists}'"/>
    <@ww.hidden name="'acceptId'" value="'${req.getParameter('acceptId')?if_exists}'"/>
	<#include "purchaseContractSelectorSeacher.ftl"/>
 	<@buttonBar>
      <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
 	<@list title="" excel=false setupTable=false
           includeParameters="id|acceptId|detailType" 
           fieldMap="like:" >
	  <@vlh.checkbox property="id" name="quarterPlanIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('purchaseContractBillNo')}" property="purchaseBill.billNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('purchaseContractName')}" property="purchaseBill.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('department')}" property="purchaseBill.department.name">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('categoryName')}" property="categoryName">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('graphNo')}" property="graphNo">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('ProductName')}" property="name">
        <@alignLeft/>
      </@vcolumn>
        <@vcolumn title="${action.getText('model')}" property="model">
        <@alignLeft/>
      </@vcolumn>
      <@vcolumn title="${action.getText('specification')}" property="specification">
        <@alignCenter/>
      </@vcolumn>
     <#-- <@vcolumn title="${action.getText('modelr')}" property="">
	    <@alignLeft/>
      </@vcolumn>
	  <@vcolumn title="${action.getText('unitPrice')}" property="">
	    <@alignCenter/>
      </@vcolumn>
      <@vcolumn title="${action.getText('number')}" property="">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('allPrice')}" property="">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('requestDate')}" property="" format="yyyy-MM-dd">
	    <@alignRight/>
      </@vcolumn>
      <@vcolumn title="${action.getText('requestReason')}" property="">
	    <@alignRight/>
      </@vcolumn>
   -->
      <@vcolumn title="${action.getText('comment')}" property="comment">
	    <@alignRight/>
      </@vcolumn>
    </@list>
    <#if !first>
      <@buttonBar>
	   <@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"quarterPlanIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
	  </@buttonBar>
    </#if>
  </@ww.form>
</@htmlPage>

 <script language="javascript">
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
		 var quarterPlanIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   quarterPlanIds += selector[i].value + ",";
			 }
		   }
		 }
		 returnDialog(quarterPlanIds,false);
	   }
        </script>
