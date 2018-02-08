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

<#--$Id:$ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('financeTypeList.title')}">
	<@ww.form name="'listForm'" action="'searchFinanceTypes'" method="'post'">
        <@ww.token name="searchFinanceTypesToken"/>
        <#include "./financeTypeSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/financeType/editFinanceType.html"/>
        </@buttonBar>
        <@list title="${action.getText('financeType.list')}" includeParameters="code|name|onlyValid|onlyInvalid" fieldMap="like:code|name" >
            <@vlh.checkbox property="id" name="financeTypeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('financeType.code')}" property="code" sortable="asc">
                <a href="editFinanceType.html?financeType.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('financeType.name')}" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>  
            <@vcolumn title="${action.getText('financeType.yearDeprecitionScale')}" property="yearDeprecitionScale" >
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financeType.deprecitionYearLimit')}" property="deprecitionYearLimit" >
            	<@alignRight/>
            </@vcolumn>         
            <@vcolumn title="${action.getText('financeType.netSalvageScale')}" property="netSalvageScale" >
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('financeType.comment')}" property="comment" >
            	<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
           <@buttonBar>
	         <@eam2008_disabledOrEnabled_button message="${action.getText('financeType')}" boxName="financeTypeIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>