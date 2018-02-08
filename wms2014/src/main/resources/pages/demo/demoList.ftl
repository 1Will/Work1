<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: demoList.ftl 6888 2007-09-05 13:24:56Z qsun $ -->

<#include "../includes/macros.ftl" />

<@htmlPage>
	
	<@ww.form name="'listForm'" action="'searchDemo'" method="'post'">
	<@ww.token name="searchDemoToken"/>
	
	<#include "./demoSearch.ftl"/>
	
	<@buttonBar>
		 <@vsubmit value="'${action.getText('search')}'" />
		<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/pages/demo/listDemo.html"/>
	</@buttonBar>
	<@list title="${action.getText('area.list')}" includeParameters="id|name|password" fieldMap="like:id" >
            <@vlh.checkbox property="id" name="userIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('area.code')}">
                <a href="listDemo.html?area.id=#{object.id}">${object.id}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('area.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('area.name')}" property="password" sortable="desc"/>
     </@list>
     <#if !first>
        <@buttonBar>
            <#assign confirmMessage = "${action.getText('confirm.invalid')}${action.getText('area')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('invalidation')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"areaIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
        </@buttonBar>
        </#if>
     </@ww.form>
</@htmlPage>