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

<@htmlPage title="${action.getText('流程查询')}">
	<@ww.form name="'listForm'" action="'searchToDoTask'" method="'post'">
		<@ww.token name="searchToDoTaskToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./toDoTaskSearch.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        
		<#assign itemNo=1/>
        <@list title="${action.getText('流程列表')}" excel=false setupTable=false
        	   includeParameters="toDoTask.code|toDoTask.name|onlyInvalid|onlyValid" 
        	   fieldMap="like:toDoTask.name" >
            <@vlh.checkbox property="id" name="toDoTaskIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
			<@vcolumn title="${action.getText('序号')}">
				<a href="editToDoTask.html?toDoTask.id=#{object.id}">#{itemNo}</a>
				<@alignCenter />
			</@vcolumn>
			<#assign itemNo=itemNo + 1/>
            <@vcolumn title="${action.getText('流程编码')}" property="code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('流程名称')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('工作流实例编码')}" property="workflowCase.code" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('工作流实例名称')}" property="workflowCase.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('customerInfo.info')}" boxName="toDoTaskIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
    </@ww.form>
<script language="javascript">
//失去焦点隐藏导航层
function onBlur(op){
	getObjByName(op).style.display="none";
}
</script>
</@htmlPage>