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
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('deviceTypeSelector.title')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'searchDeviceTypeSelector'" method="'post'">
	 	<@ww.token name="searchDeviceTypesToken"/>
	 	<#include "./deviceTypeSearcher.ftl"/>
	 	<@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'"/>
        </@buttonBar>
        <@list title="${action.getText('deviceType.list')}" 
        	includeParameters="id|code|name" 
        	fieldMap="like:code|name" >
            <@vcolumn title="${action.getText('deviceType.code')}" property="code" sortable="desc">
            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.code}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('deviceType.name')}" property="name" sortable="desc"/>         
        </@list> 
     <#if !first>
     <@buttonBar> 
     	<@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	 </@buttonBar>
	</#if>        
     </@ww.form>
</@htmlPage>