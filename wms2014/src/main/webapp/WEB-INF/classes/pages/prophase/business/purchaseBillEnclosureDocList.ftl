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
<#--$Id: purchaseBillPayDetails.ftl 11279 2008-03-12 01:12:13Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('payDetails.title')}">	
		
	<@ww.form name="'listForm'" action="'searchPurchaseEnclosure'" method="'post'">
		<@ww.token name="searchPurchaseBillPayDetailsToken"/>
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false  includeParameters="purchaseBill.id" fieldMap="like:purchaseBill.id">
			<@vlh.checkbox property="id" name="purchaseEnclosureIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
              <a href="#" onclick="open_uploadDialog(#{purchaseBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('EnclosureName')}" property="fileName"  >
               <a  title="${action.getText('download')}" href="downloadPurchaseEnclosureDoc.html?doc.id=#{object.id}" >${object.name?if_exists}</a>
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('createdTime')}" property="createdTime"  format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
             <@vcolumn title="${action.getText('creator')}" property="creator"  >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('description')}" property="description"  >
            	<@alignLeft/>
            </@vcolumn>
        </@list>
        <@buttonBar>
        	<#if !first>
        	 <@buttonBar>
        	 <#if !(action.isReadOnly())>
	         	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('purchaseBillchangeDoc')}?" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{purchaseBill.id}, null);"/>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchaseEnclosureIds\", \"${confirmMessage}\");'"/>
	                 <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	         </@buttonBar>
            </#if>
        </@buttonBar>
	</@ww.form>
	 <script language="javascript">
	    
	    	function open_uploadDialog(purchaseBillId, purchaseBillDocId) {
	    		var url;
	    		if (null == purchaseBillDocId) {
	    			 url = '${req.contextPath}/prophase/editPurchaseEnclosureDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id='+purchaseBillId;
	    		} else {
	    			 url = '${req.contextPath}/prophase/editPurchaseEnclosureDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&purchaseBill.id='+purchaseBillId+ '&purchaseEnclosureDoc.id=' + purchaseBillDocId;
	    		}
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>