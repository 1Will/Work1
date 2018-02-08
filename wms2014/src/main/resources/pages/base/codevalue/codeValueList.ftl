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

<#--$Id: codeValueList.ftl 11325 2008-03-15 06:48:17Z wzou $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('codevalueList.title')}">
	<@ww.form name="'listForm'" action="'searchCodeValues'" method="'post'">
        <@ww.token name="searchCodeValuesToken"/>
        <#include "./codeValueSearcher.ftl" />
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <#-- 编码大类不需要新建，代码屏蔽，预留
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/codevalue/editCodeValue.html"/>
       		-->
        </@buttonBar>
        <@list title="${action.getText('code.list')}" includeParameters="code|value" fieldMap="like:code|value" >
	        <@vlh.checkbox property="id" name="codeValueIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
            <@vcolumn title="${action.getText('code.type')}" property="code" sortable="asc">
                <a href="editCodeValue.html?type.id=#{object.id}">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('code.name')}" property="value" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>          
        </@list>
        
        <#if !first>
        <#--<@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('area')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('invalidation')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"areaIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>-->
        
        <@buttonBar>
          <@eam2008_disabledOrEnabled_button message="${action.getText('codevalue')}" boxName="codeValueIds" jsFunctionName="checkInvalidParms()"/>
       </@buttonBar>
       </#if>
    </@ww.form>
</@htmlPage>