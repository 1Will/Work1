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

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('applyCar.title')}">
	<@ww.form name="'listFrom'" namespace="'/apply'" action="'searchApplyCar'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchApplyCarToken"/>
		<#include "./applyCarSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/apply/editApplyCar.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
         <#assign returnName='replaceWord'>
        <@list title="${action.getText('applyCar.list.title')}" 
            includeParameters="applyCar.code|applyCar.crateDate_start|applyCar.crateDate_end|applyCar.applyPerson|dept.id|applyCar.endTime_start|applyCar.endTime_end|applyCar.startTime_start|applyCar.startTime_end|applyCar.applyPerson|dept.id|status.id|type.id|flow.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:applyCar.code|applyCar.applyPerson,date:applyCar.crateDate_start|applyCar.crateDate_end|applyCar.endTime_start|applyCar.endTime_end|applyCar.startTime_start|applyCar.startTime_end" >
        	<#if !(action.isReadOnly())>
        	<@vlh.checkbox property="id" name="applyCarIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('applyCar.code')}" property="code" sortable="desc">
                <a href="editApplyCar.html?applyCar.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.crateDate')}" property="createdTime" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/><#-- attributes="width:110;"-->
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.applyPerson')}" property="applyPerson.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.dept')}" property="dept" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('applyCar.contacter')}" property="contacter" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.phone')}" property="phone" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.startTime')}" property="time_start" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.endTime')}" property="time_end" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('流程类型')}" property="flow.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('applyCar.type')}" property="type.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('applyCar.status')}" property="status.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('applyCar.betreffzeile')}" property="reason" sortable="desc">
            <#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.reason?if_exists}'"/>
            	<span title="${object.reason?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>10){
		            	document.write(s.slice(0,10)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
        </@list>
         <#if !first>
         <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('applyCar.info')}" boxName="applyCarIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>