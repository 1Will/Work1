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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('personnelCasting.search')}">
	<@ww.form name="'listForm'" action="'listPersonnelCastingAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="listPersonnelCastingActionToken"/>
		<#include "./personnelCastingSearcher.ftl" />
        <@buttonBar>  
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
		<#--	<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/personnelCasting/editStorageBillAction.html"/>
       		</#if>
       		-->
        </@buttonBar>
        <@list title="${action.getText('personnelCasting.list')}" 
            includeParameters="personnelCasting.code|personnelCasting.name|personnelCasting.fileNo|personnelCasting.inst|personnelCasting.dept|personnelCasting.duty|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:personnelCasting.code|personnelCasting.name|personnelCasting.fileNo|personnelCasting.inst|personnelCasting.dept|personnelCasting.duty" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="personnelCastingIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            
          	<@vcolumn title="${action.getText('personnelCasting.code')}" property="code" sortable="desc" >
              <#--  <a href="editStorageBillAction.html?personnelCasting.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code?if_exists}</a>
				-->
				<@alignLeft/>
            </@vcolumn>
            
           <@vcolumn title="${action.getText('personnelCasting.name')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('personnelCasting.fileNo')}" property="fileNo" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('personnelCasting.inst')}" property="inst" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('personnelCasting.dept')}" property="dept" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('personnelCasting.duty')}" property="duty" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('personnelCasting.contractCode')}" property="contractCode" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('personnelCasting.regularizedDate')}" property="regularizedDate" format="yyyy-HH-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('personnelCasting.message')}" boxName="personnelCastingIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
    
</@htmlPage>
