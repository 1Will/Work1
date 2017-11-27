<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: flowList.ftl 2010-04-06 wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('历史任务查询')}">
	<@ww.form name="'listForm'" action="'searchHistoryTask'" method="'post'">
		<@ww.token name="searchHistoryTaskToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./historyTaskSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
		<#assign itemNo=1/>
        <@list title="${action.getText('历史任务列表')}" excel=false setupTable=false
        	   includeParameters="historyTask.code|historyTask.name|historyTask.personnelFilesName|onlyInvalid|onlyValid" 
        	   fieldMap="like:historyTask.code|historyTask.name|historyTask.personnelFilesName" >
            <@vlh.checkbox property="id" name="historyTaskIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('序号')}">
				<a href="editHistoryTask.html?historyTask.id=#{object.id}">#{itemNo}</a>
				<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('任务编码')}" property="code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('任务名称')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('审批人')}" property="personnelFilesName" sortable="desc">
	            <@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('历史任务')}" boxName="historyTaskIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
    </@ww.form>
</@htmlPage>