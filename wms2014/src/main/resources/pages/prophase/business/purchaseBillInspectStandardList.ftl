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
<#--$Id: purchaseBillPayDetails.ftl 10914 2008-02-14 01:50:22Z qsun $ -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('payDetails.title')}">	
		
	<@ww.form name="'listForm'" action="'searchInspectStandard'" method="'post'">
		<@ww.token name="searchInspectStandardsToken"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if purchaseBill.id?exists>
		<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false  includeParameters="purchaseBill.id" fieldMap="like:purchaseBill.id">
			<@vlh.checkbox property="id" name="inpDtlIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
               <a href="#" onclick="open_payDtlDialog(#{purchaseBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
           <#assign itemNo = itemNo + 1/>
             <@vcolumn title="${action.getText('inspectContent')}" property="inspectContent">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('inspectStandard')}" property="standard"  >
            	<@alignLeft />
            </@vcolumn>
        </@list>
        <@buttonBar>
        	<#if !first>
        	
          <#if !(action.isReadOnly())>
        	 <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_payDtlDialog(#{purchaseBill.id}, null)"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('inputStands')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"inpDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
           
            <script language="javascript">
            	function open_payDtlDialog(purchaseBillId,inspectDtlId) {
            		var url = '${req.contextPath}/prophase/editInspectStandard.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id=' + purchaseBillId;
            		if (null != inspectDtlId) {
            			url = url+ '&inspectStandard.id=' + inspectDtlId;
        			}
	      			popupModalDialog(url, Config.popW, Config.popH);
	      			self.location.reload();
	      			return false;
            	}
            </script>
        </@buttonBar>
	</@ww.form>
</@framePage>