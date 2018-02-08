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
	<@ww.form name="'listForm'" action="'searchToolingTypeDetails'" method="'post'">
        <@ww.token name="searchToolingTypeDetailsToken"/>
       	<#if toolingType.id?exists>
			<@ww.hidden name="'toolingType.id'" value="#{toolingType.id}"/>
		</#if>
        <@list title="" excel=false setupTable=false includeParameters="toolingType.id" fieldMap="" >
            <@vlh.checkbox property="id" name="toolingTypeDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('toolingTypeDetail.code')}" property="code" sortable="asc">
                <a href="#" onclick="return open_toolingTypeDtlDialog(#{toolingType.id}, #{object.id})">
                <#if object.code?exists>
                ${object.code}
                </#if>
                </a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingTypeDetail.name')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>  
        </@list>
        <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('toolingTypeDetails')}?" />
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_toolingTypeDtlDialog(#{toolingType.id}, null)"/>
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"toolingTypeDetailIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
    </@ww.form>
    <script language="javascript">
    	function open_toolingTypeDtlDialog(toolingTypeId, toolingTypeDtlId) {
        		var url = '${req.contextPath}/base/codevalue/editToolingTypeDetail.html?toolingType.id=' + toolingTypeId + '&toolingDevFlag=${toolingDevFlag?if_exists}';
        		
        		if (null != toolingTypeDtlId) {
        			url = url+ '&toolingTypeDetail.id=' + toolingTypeDtlId;
        		}
	      		popupModalDialog(url, Config.popW, Config.popH);
	      		var reloadUrl = '${req.contextPath}/base/codevalue/listToolingTypeDetails.html?toolingType.id=' + toolingTypeId+'&toolingDevFlag=${toolingDevFlag?if_exists}';
	            self.location.href = reloadUrl;
	      		return false;
        	}
    </script>
</@framePage>