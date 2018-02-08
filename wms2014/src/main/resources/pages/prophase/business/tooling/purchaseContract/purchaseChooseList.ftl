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
<@htmlPage title="${action.getText('purchasechooseBillDtl.title')}">
<base target="_self">
<@ww.form name="'listForm'" action="'searchSubscribeBill'" method="'post'">
	<@ww.token name="supplierSelectorToken"/>
    <#include "purchaseDetailToPurchaseDetailSearcher.ftl" />
    <@ww.hidden name="'purchaseContractType'" value="'${req.getParameter('purchaseContractType')?if_exists}'"/>
    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>
    <#assign itemNo=1/>
 <@list title="" excel=false setupTable=false
		includeParameters="billNo|name|toolingDevFlag|graphNo|department.id|dtlName|model|specification|requireDate_start|requireDate_end|purchaseContractType" 
		fieldMap="like:billNo|name|dtlName|model|graphNo|specification,date:subscribeDate_start|subscribeDate_end">
            <@vlh.checkbox property="id" name="subscribeDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
               <@vcolumn title="${action.getText('itemNo')}">
               ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('pchoose.billNo')}" property="subscribe.billNo">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('pchoose.name')}" property="subscribe.name">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('department')}" property="subscribe.department.name">
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
             <@vcolumn title="${action.getText('comment')}" property="comment" >
            	<@alignLeft />
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
		</@list>
		<#if !first>
       <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"subscribeDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            <@vsubmit name="'choose'" value="'${action.getText('选择并关闭')}'" onclick="'return confirmSelects(\"subscribeDtlIds\");'">
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
	</#if>
</@ww.form>
  <script language="javascript">
  //alert(getObjByNameRe("purchaseContractType").value);
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