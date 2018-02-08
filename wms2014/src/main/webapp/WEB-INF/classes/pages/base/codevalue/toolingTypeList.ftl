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

<#--$Id: toolingTypeList.ftl 11326 2008-03-15 06:48:54Z smzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingTypeList.title')}">
	<@ww.form name="'listForm'" action="'searchToolingTypes'" method="'post'">
        <@ww.token name="searchToolingTypesToken"/>
        <#include "./toolingTypeSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/codevalue/editToolingType.html"/>
        </@buttonBar>
        <@list title="${action.getText('toolingType.list')}" includeParameters="code|value|onlyValid|onlyInvalid" fieldMap="like:code|value" >
            <@vlh.checkbox property="id" name="toolingTypeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('toolingType.code')}" property="code" sortable="asc">
                <a href="editToolingType.html?toolingType.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('toolingType.value')}" property="value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>  
        </@list>
        <#if !first>
        <@buttonBar>
	       <@eam2008_disabledOrEnabled_button message="${action.getText('toolingType')}" boxName="toolingTypeIds" jsFunctionName="checkInvalidParms()"/>
	   </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>