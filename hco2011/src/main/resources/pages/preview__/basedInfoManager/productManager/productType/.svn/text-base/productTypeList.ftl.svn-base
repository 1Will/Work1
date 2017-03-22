<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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


<#include "../../../includes/crm.ftl" />

<@htmlPage title="${action.getText('products.productType.manager')}">

	<@ww.form name="'listForm'" namespace="'/productTypeManager'" action="'seacherProductTypes'" method="'post'">
		<@ww.token name="seacherProductTypesToken"/>
		<#include "./productTypeSearcher.ftl" />
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/productTypeManager/editProductType.html"/>
        </@buttonBar>
        
        <@list title="${action.getText('productTypeList')}" includeParameters="id|code|name|productType.id|nonParentPT|onlyInvalid|onlyValid" fieldMap="like:code|name|nonParentPT">
            <@vlh.checkbox property="id" name="ptIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <#if (object.disabled)>
	            <@vlh.column title="${action.getText('productType.code')}"  property="code" sortable="desc">
	             ${object.code}
	             <@alignLeft/>
	            </@vlh.column>
            <#else>
            <@vlh.column title="${action.getText('productType.code')}"  property="code" sortable="desc">
                <a href="editProductType.html?productType.id=#{object.id}">${object.code}</a>
                <@alignLeft/>
            </@vlh.column>
            </#if>
            <@vlh.column title="${action.getText('productType.name')}" property="name" sortable="desc"><@alignLeft/></@vlh.column>
            <#--
            <@vlh.column title="${action.getText('productType.parentType')}" property="parentPT.name" sortable="desc"><@alignLeft/></@vlh.column>
            -->
            <#assign parentPT=""/>
             <#if object.parentPT?exists>
               <#assign parentPT="${object.parentPT.name}"/>
             <#else>
               <#assign parentPT="无"/>
             </#if>
             <@vcolumn title="${action.getText('parentTP.name')}" property="parentPT" sortable="desc">
               ${parentPT}
               <@alignLeft/>
             </@vcolumn>
        </@list>
	     <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
          <@police_disabledOrEnabled_button message="${action.getText('productType')}" boxName="ptIds" jsFunctionName="checkInvalidParms()"/>
        </@buttonBar>
        </#if>
        </#if>
    </@ww.form>
  
</@htmlPage>