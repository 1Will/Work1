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

<@htmlPage title="${action.getText('工作流查询')}">
	<@ww.form name="'listForm'" action="'searchWorkflow'" method="'post'">
		<@ww.token name="searchWorkflowToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./workflowSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        	<#if !(action.isReadOnly())>
        	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/workflow/editWorkflow.html"/>
        	</#if>
        </@buttonBar>
        
		<#assign itemNo=1/>
        <@list title="${action.getText('工作流列表')}" excel=false setupTable=false
        	   includeParameters="workflow.code|workflow.name|workflow.openOrNot|affectObject|onlyInvalid|onlyValid" 
        	   fieldMap="like:workflow.name" >
        	   <#if !(action.isReadOnly())>
            <@vlh.checkbox property="id" name="workflowIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
			<@vcolumn title="${action.getText('序号')}">
				<a href="editWorkflow.html?workflow.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">#{itemNo}</a>
				<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('工作流编码')}" property="code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('工作流名称')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('适用类型')}" property="affectObject.name" sortable="desc">
	            <@alignLeft/>
            </@vcolumn>
            <#-- 定义是否启动变量 -->
            <#if object.openOrNot == 0>
				<#assign openOrNot="${action.getText('是')}"/>
			<#else>
				<#assign openOrNot="${action.getText('否')}"/>
			</#if>
			
            <@vcolumn title="${action.getText('是否启用')}" property="openOrNot" sortable="desc">
	            ${openOrNot}
	            <@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('customerInfo.info')}" boxName="workflowIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		</#if>
    </@ww.form>
<script language="javascript">
//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
</script>
</@htmlPage>