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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('leaveBill.title')}">
	<@ww.form name="'listFrom'" namespace="'/leaveBill'" action="'searchLeaveBill'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchLeaveBillToken"/>
		<#include "./leaveBillSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/leaveBill/editLeaveBill.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('leaveBill.list.title')}" 
            includeParameters="leaveBill.code|leaveBill.crateDate_start|leaveBill.crateDate_end|leaveBill.applyPerson|dept.id|leaveBill.endTime_start|leaveBill.endTime_end|leaveBill.startTime_start|leaveBill.startTime_end|leaveBill.applyPerson|dept.id|status.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:leaveBill.code|leaveBill.applyPerson,date:leaveBill.crateDate_start|leaveBill.crateDate_end|leaveBill.endTime_start|leaveBill.endTime_end|leaveBill.startTime_start|leaveBill.startTime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="leaveBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('leaveBill.code')}" property="code" sortable="desc">
                <a href="editLeaveBill.html?leaveBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.crateDate')}" property="createDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.applyPerson')}" property="applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.dept')}" property="dept.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.startTime')}" property="startTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.endTime')}" property="endTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.manHour')}" property="manHour" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('leaveBill.type')}" property="type.name" sortable="desc">
            	<@alignCenter/>
            </@vcolumn>
             <@vcolumn title="${action.getText('leaveBill.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('leaveBill.betreffzeile')}" property="betreffzeile" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('leaveBill.info')}" boxName="leaveBillIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>