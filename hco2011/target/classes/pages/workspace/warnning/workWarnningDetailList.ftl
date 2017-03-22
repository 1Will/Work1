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

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('workwarndetail.search')}">
	<@ww.form name="'listForm'" namespace="'/workspace/warnning/myWarnning'" action="'searchWorkWarnningDetail'" method="'post'">
		<@ww.token name="searchworkWarnningDetailToken"/>
		<@ww.hidden name="'workWarnningId'" value="${req.getParameter('workWarnningId')?if_exists}"/>
		<@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
                <@ww.textfield label="'${action.getText('remaindays')}'" name="'remaindays'" value="'${req.getParameter('remaindays')?if_exists}'" cssClass="'underline'"/>
            </tr>
        </@inputTable>
        <@buttonBar>
			<@vsubmit name="'search'" value="'${action.getText('search')}'" />
			<@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
        </@buttonBar>
        <@list title="${action.getText('workwarndetail.list')}" includeParameters="workWarnningId|name|remaindays|onlyInvalid|onlyValid" fieldMap="like:name|remaindays" >
    	<@vcolumn title="${action.getText('name')}" property="name" sortable="asc" >
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('warnDate')}" property="warnDate" format="yyyy-MM-dd" sortable="asc">
            <@alignCenter/>
        </@vcolumn>
        <@vcolumn title="${action.getText('remaindays')}" property="remaindays" sortable="asc">
            <#if (object.remaindays > 0)>
            	<#if (type = "回访提醒")>
                <font color="red"> #{object.remaindays} 小时</font>
                <#else>
                <font color="red"> #{object.remaindays} 天</font>
                </#if>
            <#else>
               <font color="gray">已过期</font>
            </#if>
            <@alignRight/>
        </@vcolumn>
    </@list>
    </@ww.form>
</@htmlPage>