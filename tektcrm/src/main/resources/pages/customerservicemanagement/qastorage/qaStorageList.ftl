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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('QA库查询页面')}">
	<@ww.form name="'listForm'" action="'searchQaStorageAction'" method="'post'">
		<@ww.token name="searchQaStorageActionToken"/>
		<#include "./qaStorageSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/qaStorage/editQaStorageAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('QA库列表')}" 
            includeParameters="qaStorage.code|qaStorage.applyProduc|qaStorage.type.id|qaStorage.state.id|qaStorage.severityDegree.id|qaStorage.question|qaStorage.resolveProject|qaStorage.innerPrompt|qaStorage.remark|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	
        	<@vlh.checkbox property="id" name="qaStorageIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
          	<@vcolumn title="${action.getText('编号')}" property="code" sortable="desc" >
                <a href="editQaStorageAction.html?qaStorage.id=#{object.id?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('版本号')}" property="versionNumber" sortable="desc">
            ${object.versionNumber?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('类型')}" property="type.id" sortable="desc">
            	<#if object.type?exists>
	            	 ${object.type.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            
			<@vcolumn title="${action.getText('状态')}" property="state.id" sortable="desc">
				<#if object.state?exists>
	            	 ${object.state.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('严重程度')}" property="severityDegree.id" sortable="desc">
            	<#if object.severityDegree?exists>
	            	 ${object.severityDegree.name?if_exists}            	
            	</#if>
     			<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('问题')}" property="question" sortable="desc">
             ${object.question?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('QA信息')}" boxName="qaStorageIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
        </#if>
		</#if>
    </@ww.form>
</@htmlPage>
