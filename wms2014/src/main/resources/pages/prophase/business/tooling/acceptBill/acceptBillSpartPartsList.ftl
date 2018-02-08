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
<#--$Id: purchaseBillDetails.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('purchaseBillDetails.title')}">	
	<@ww.form  namespace="'/tooling/acceptBillSelector'" name="'listForm'" action="'sercherSparePart'" method="'post'">
		<@ww.token name="searchSparePartToken"/>
		<#if acceptBill.id?exists>
		<@ww.hidden name="'acceptBill.id'" value="'#{acceptBill.id}'"/>
		</#if>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		 <#assign itemNo=1/>
		<@list title="" excel=false setupTable=false includeParameters="acceptBill.id" fieldMap="like:">
			<@vlh.checkbox property="id" name="sparePartIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
               <a href="editSparePart.html" onclick="open_sparePartDialog(#{acceptBill.id},#{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
            </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('spareName')}" property="spareName" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spareModel')}" property="spareModel">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('spareSpecification')}" property="spareSpecification" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('spareamount')}" property="amount" >
            	<@alignRight />
            </@vcolumn>
           <#assign spareStatus = ''/>
           <#if object.damage ==false>
           <#assign spareStatus = "${action.getText('N')}"/>
           <#else> 
              <#assign spareStatus = "${action.getText('Y')}"/>
           </#if>
             <@vcolumn title="${action.getText('sparedamage')}" property="damage" >
                ${spareStatus}
            	<@alignLeft />
            </@vcolumn>
          
            <@vcolumn title="${action.getText('installPosition')}" property="installPosition" >
            	<@alignLeft />
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('memo')}" property="memo" >
            	<@alignLeft />
            </@vcolumn>
            -->
		</@list>
        <@buttonBar>
        	<#if !first>
        	   <#if !(action.isReadOnly())>
        	<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_sparePartDialog(#{acceptBill.id}, null)"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spartBillDtl')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"sparePartIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
            </#if>
        </@buttonBar>
         <script language="javascript">
	      function open_sparePartDialog(acceptId,sparePartId) {
        		var url = '${req.contextPath}/accept/editSparePart.html?acceptBill.id=' + acceptId;;
        		if (null != sparePartId) {
        			url = url+ '&sparePart.id=' + sparePartId;
        		}
	      		popupModalDialog(url, Config.popW+100, Config.popH);
	      		self.location.reload();
	      		return false;
        	}
        </script>
	</@ww.form>
	 
</@framePage>