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
<#--$Id: purchaseBillPayDetails.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('payDetails.title')}">	
		
	<@ww.form name="'listForm'" action="'searchPayDetails'" method="'post'">
		<@ww.token name="searchPurchaseBillPayDetailsToken"/>
		<#if purchaseBill.id?exists>
		<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false  includeParameters="purchaseBill.id" fieldMap="like:purchaseBill.id">
			<@vlh.checkbox property="id" name="payDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
               <a href="editPayDetail.html" onclick="open_payDtlDialog(#{purchaseBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('payMoney')}" property="payMoney"  >
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('payDate')}" property="payDate" format="yyyy-MM-dd" >
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment"  >
            	<@alignLeft />
            </@vcolumn>
            <#-- <@vcolumn title="${action.getText('content')}" property="comment"  >
            	<@alignLeft />
            </@vcolumn>-->
        </@list>
        <@buttonBar>
         <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_payDtlDialog(#{purchaseBill.id}, null)"/>
           <#if !first>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('payDetails')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"payDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
            
        </@buttonBar>
        <script language="javascript">
        window.onload=function(){
         <#if purchaseBill.id?exists>
                parent.getObjByNameRe("alreadyPayOut").value = '${purchaseBill.alreadyPayOut?if_exists}';
           </#if>
        }
        
            	function open_payDtlDialog(purchaseBillId,payDtlId) {
            		var url = '${req.contextPath}/toooling/purchaseContract/editPayDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=' + purchaseBillId;
            		if (null != payDtlId) {
            			url = url+ '&payDetail.id=' + payDtlId;
        			}
	      			popupModalDialog(url, Config.popW, Config.popH);
	      			self.location.reload();
	      			return false;
            	}
            </script>
	</@ww.form>
</@framePage>