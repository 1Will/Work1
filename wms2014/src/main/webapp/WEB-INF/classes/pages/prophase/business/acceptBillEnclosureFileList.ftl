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
	<@ww.form name="'listForm'" action="'searchAcceptEnclosure'" method="'post'">
		<@ww.token name="searchPurchaseBillPayDetailsToken"/>
		<#if acceptBill.id?exists>
			<@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
		</#if>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false  includeParameters="acceptBill.id|readOnly" fieldMap="like:acceptBill.id">
			<@vlh.checkbox property="id" name="acceptBillEnclosureIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
              <a href="#" onclick="open_uploadDialog(#{acceptBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('EnclosureName')}" property="name"  >
               <a  title="${action.getText('download')}" href="downloadAcceptEnclosureDoc.html?doc.id=#{object.id}" >${object.name?if_exists}</a>
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('creator')}" property="creator">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('createdTime')}" property="createdTime"  format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
             <@vcolumn title="${action.getText('description')}" property="description"  >
            	<@alignLeft/>
            </@vcolumn>
        </@list>
        <@buttonBar>
        	<#if !first>
        	 <@buttonBar>
           <#if !(action.isReadOnly())>
	         	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('acceptBillDoc')}?" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{acceptBill.id}, null);"/>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"acceptBillEnclosureIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	         </@buttonBar>
            </#if>
        </@buttonBar>
	</@ww.form>
	 <script language="javascript">
	
	    	function open_uploadDialog(acceptBillId, acceptBillDocId) {
	    		var url;
	    		if (null == acceptBillDocId) {
	    			 url = '${req.contextPath}/accept/editAcceptBillEnclosureDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&acceptBill.id='+acceptBillId;
	    		} else {
	    			 url = '${req.contextPath}/accept/editAcceptBillEnclosureDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&acceptBill.id='+acceptBillId+ '&acceptEnclosureDoc.id=' + acceptBillDocId;
	    		    alert(url);
	    		}
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>