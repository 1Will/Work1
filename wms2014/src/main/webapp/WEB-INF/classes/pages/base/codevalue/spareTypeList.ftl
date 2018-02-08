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
<@htmlPage title="${action.getText('spareTypeList.title')}">
	<@ww.form name="'listForm'" action="'searchSpareTypes'" method="'post'">
        <@ww.token name="searchSpareTypesToken"/>
        <#include "./spareTypeSeracher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')} '" onclick="'return checkInvalidParms();'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/codevalue/editSpareType.html"/>
        </@buttonBar>
        <@list title="${action.getText('spareType.list')}" includeParameters="spareTypeNo|spareTypeName|toolingDevFlag|onlyValid|onlyInvalid" fieldMap="like:spareTypeNo|spareTypeName" >
            <@vlh.checkbox property="id" name="spareTypeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('spareType.No')}" property="code" sortable="asc">
               <a href="editSpareType.html?spareType.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spareType.name')}" property="name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <#assign spareTypeSort = ''/>
            <#if object.toolingDevFlag?exists>
       			<#if '${object.toolingDevFlag}' == 'DEVICE'>
       			<#assign spareTypeSort = "${action.getText('DEVICE')}"/>
       			<#elseif '${object.toolingDevFlag}' == 'TOOLING'>
       			<#assign spareTypeSort = "${action.getText('TOOLING')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('SpareSort')}" sortable="asc">
            	${spareTypeSort}
            	<@alignLeft/>
            </@vcolumn>  
        </@list>
        <#if !first>
        <@buttonBar>
	       <@eam2008_disabledOrEnabled_button message="${action.getText('spareType')}" boxName="spareTypeIds" jsFunctionName="checkInvalidParms()"/>
	   </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>