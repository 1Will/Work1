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
<#--$Id: subscribeSelector.ftl 11220 2008-03-07 10:03:29Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('subscribeList.title')}">
<base target="_self">
	<@ww.form name="'listForm'" action="'searchSubscribesSelector'" method="'post'">
		<@ww.token name="searchSubscribesSelectorToken"/>
		<#include "subscribeSearcher.ftl"/>
		
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" />
		</@buttonBar>
	
		<@list title="${action.getText('subscribeList')}" includeParameters="billNo|name|subscribeDate_start|subscribeDate_end" fieldMap="like:id" >
			<@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="billNo" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribe.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('subscriber')}" property="subscriber" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('totalAmounts')}" property="totalAmounts" sortable="asc">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('reason')}" property="reason" >
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('comment')}" property="comment" >
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
        <@buttonBar>
        	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
        		<@ww.param name="'onclick'" value="'return confirmSelects(\"subscribeIds\");'"/>
        		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	       </@vsubmit>
	   </@buttonBar>
        </#if>
	</@ww.form>
</@htmlPage>