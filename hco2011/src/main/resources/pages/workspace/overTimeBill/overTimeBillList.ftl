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
<@htmlPage title="${action.getText('overTimeBill.title')}">
	<@ww.form name="'listFrom'" namespace="'/overTimeBill'" action="'searchOverTimeBill'" method="'post'">
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchOverTimeBillToken"/>
		<#include "./overTimeBillSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" cssClass="'button'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/overTimeBill/editOverTimeBill.html"/>
			</#if>
        </@buttonBar>
        <@list title="${action.getText('overTimeBill.list.title')}" 
            includeParameters="overTimeBill.code|overTimeBill.crateDate_start|overTimeBill.crateDate_end|overTimeBill.endTime_start|overTimeBill.endTime_end|overTimeBill.startTime_start|overTimeBill.startTime_end|overTimeBill.applyPerson|dept.id|status.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:overTimeBill.code|overTimeBill.applyPerson,date:overTimeBill.crateDate_start|overTimeBill.crateDate_end|overTimeBill.endTime_start|overTimeBill.endTime_end|overTimeBill.startTime_start|overTimeBill.startTime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="overTimeBillIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             </#if>
            <@vcolumn title="${action.getText('overTimeBill.code')}" property="code" sortable="desc">
                <a href="editOverTimeBill.html?overTimeBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('overTimeBill.crateDate')}" property="createDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('overTimeBill.applyPerson')}" property="applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('overTimeBill.dept')}" property="dept.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('overTimeBill.startTime')}" property="startTime" format="yyyy-MM-dd hh:mm" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('overTimeBill.endTime')}" property="endTime" format="yyyy-MM-dd hh:mm" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('overTimeBill.manHour')}" property="manHour" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
             <@vcolumn title="${action.getText('overTimeBill.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('overTimeBill.betreffzeile')}" property="betreffzeile" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('overTimeBill.info')}" boxName="overTimeBillIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>