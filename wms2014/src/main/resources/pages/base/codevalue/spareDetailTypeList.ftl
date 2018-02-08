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

<#--$Id: toolingTypeDetailList.ftl 11326 2008-03-15 06:48:54Z smzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@framePage title="">
	<@ww.form name="'listForm'" action="'searchSpareTypeDetails'" method="'post'">
        <@ww.token name="searchSpareTypeDetailsToken"/>
       	<#if spareType.id?exists>
			<@ww.hidden name="'spareType.id'" value="'#{spareType.id}'"/>
		</#if>
        <@list title="" excel=false setupTable=false includeParameters="spareType.id" fieldMap="" >
            <@vlh.checkbox property="id" name="spareTypeDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('spareTypeDetail.code')}" property="code" >
                <a href="#" onclick="return open_spareTypeDtlDialog(#{spareType.id}, #{object.id})">
                <#if object.code?exists>
                ${object.code}
                </#if>
                </a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spareTypeDetail.name')}" property="name">
            	<@alignLeft/>
            </@vcolumn>  
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareTypeDetails')}?" />
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_spareTypeDtlDialog(#{spareType.id}, null)"/>
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"spareTypeDetailIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
    
    <script language="javascript">
    		function open_spareTypeDtlDialog(spareTypeId, spareTypeDtlId) {
        	var url = '${req.contextPath}/base/codevalue/editSpareDetailTypes.html?spareType.id=' + spareTypeId ;
        		if (null != spareTypeDtlId) {
        			url = url+ '&spareDetailType.id=' + spareTypeDtlId;
        		}
	      		popupModalDialog(url, Config.popW, Config.popH);
	      		var reloadUrl = '${req.contextPath}/base/codevalue/listSpareDetailTypes.html?spareType.id=' + spareTypeId;
	            self.location.href = reloadUrl;
	      		return false;
        	}
    </script>
   
</@framePage>