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

<#-- $Id: institutionList.ftl 2009-11-02 11:46:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('institution.title')}">
	<@ww.form name="'listForm'" action="'searchInstitution'" method="'post'">
		<@ww.token name="searchInstitutionToken"/>
		<#include "./institutionSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/baseInfoManager/editInstitution.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('institution.list.title')}" 
            includeParameters="id|code|name|parentInst.id|lader|onlyInvalid|onlyValid" 
        	fieldMap="like:code|name|lader" >
        	
        	<@vlh.checkbox property="id" name="institutionIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if (object.disabled)>
			<@vcolumn title="${action.getText('institution.code')}" property="code" sortable="desc">
                ${object.code}
				<@alignLeft/>
            </@vcolumn>
            <#else>
            <@vcolumn title="${action.getText('institution.code')}" property="code" sortable="desc">
                <a href="editInstitution.html?institution.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('institution.name')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <#if object.parentInst?exists>
               <#assign parentInst="${object.parentInst.name}"/>
             <#else>
               <#assign parentInst="无"/>
             </#if>
             <@vcolumn title="${action.getText('institution.parentInst')}" property="parentInst" sortable="desc">
               ${parentInst}
             </@vcolumn> 
            <@vcolumn title="${action.getText('institution.lader')}" property="lader">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
          <@police_disabledOrEnabled_button message="${action.getText('institution.info')}" boxName="institutionIds" jsFunctionName="checkInvalidParms()"/>
       </@buttonBar>
       </#if>
       </#if>
    </@ww.form>
</@htmlPage>