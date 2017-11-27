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


<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('salarySet.title.list')}">
	<@ww.form name="'listFrom'" namespace="'/salarySet'" action="'searchSalarySet'" method="'post'" >
		<@ww.token name="searchSalarySetToken"/>
		<#include "./salarySetSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/salarySet/editSalarySet.html"/>
       		</#if>
        </@buttonBar>
	<@list title="${action.getText('salarySetList')}" includeParameters="emplyee|standard|status.id|salarySet.createDate_start|salarySet.createDate_end|readOnly|onlyInvalid|onlyValid"
	 fieldMap="like:emplyee|standard|,date:salarySet.createDate_start|salarySet.createDate_end" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="salarySetIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	    <@vcolumn title="${action.getText('salarySet.emplyee')}" property="emplyee.name" sortable="asc">
	     <#if !object.disabled>
            <a href="${req.contextPath}/salarySet/editSalarySet.html?salarySet.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.emplyee.name}</a>
            <#else>
            ${object.emplyee.name}
         </#if>
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salarySet.standard')}" property="standard" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('salarySet.createDate')}" property="createDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
        <@vcolumn title="${action.getText('salarySet.status')}" property="status.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salarySet.reason')}" property="reason" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('salarySet.remark')}" property="remark" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
	</@list>
	<#if !first>
	  <#if !(action.isReadOnly())>
       <@buttonBar>
		   <@crm_disabledOrEnabled_button message="${action.getText('salarySet.info')}" boxName="salarySetIds" jsFunctionName="checkInvalidParms()"/>
	   </@buttonBar>
       </#if>
	</#if>
</@ww.form>
</@htmlPage>
