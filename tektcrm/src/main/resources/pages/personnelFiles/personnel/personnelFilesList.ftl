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
<@htmlPage title="${action.getText('personnelFilesManager')}">
	<@ww.form name="'listFrom'" namespace="'/personnelFile'" action="'searchPersonnelFile'" method="'post'" >
		<@ww.token name="searchPersonnelFileToken"/>
		<@ww.hidden name="'notOpen'" value="'${notOpen?if_exists}'"/>
		<#include "./personnelFilesSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if popWindowFlag?exists>
			<@ww.hidden name="'popWindowFlag'" value="'${req.getParameter('popWindowFlag')?if_exists}'"/>
        </#if>
        <@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<#if popWindowFlag?exists&&popWindowFlag==popWindowFlag>
			<#else>
				<#if !(action.isReadOnly())>
					<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/personnelFile/editPersonnelFile.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	       		</#if>
       		</#if>
			</#if>
			
			<@redirectButton value="${action.getText('importPersonnel.button')}" url="${req.contextPath}/personnelFile/toImportPersonnel.html"/>
			
        </@buttonBar>
	<@list title="${action.getText('personnelFilesList')}" includeParameters="code|name|fileNo|inst.id|dept.id|duty.id|notOpen|readOnly|onlyInvalid|onlyValid|popWindowFlag|" fieldMap="like:code|name|fileNo" >
		<#if popWindowFlag?exists && popWindowFlag==popWindowFlag>
		<#else>
			<#if !(action.isReadOnly())>
				<@vlh.checkbox property="id" name="personnelFileIds">
		            <@vlh.attribute name="width" value="30" />
		        </@vlh.checkbox>
	        </#if>
	    </#if>
	    <@vcolumn title="${action.getText('personnel.code')}" property="code" sortable="asc">
		 <#if !object.disabled>
		     <#if popWindowFlag?exists && popWindowFlag==popWindowFlag>
	            <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}'));">${object.code}</a>
			 <#else>
		        <a href="${req.contextPath}/personnelFile/editPersonnelFile.html?personnelFile.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
		     </#if>
		 <#else>
	         ${object.code}
         </#if>
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.name')}" property="name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.fileNo')}" property="fileNo" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.inst')}" property="inst.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.dept')}" property="dept.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.duty')}" property="duty.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.sex')}" property="sex" sortable="asc">
			<#if !object.sex>
					${action.getText('personnel.sex.man')}
				<#else>
					${action.getText('personnel.sex.women')}
			 </#if>
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.mobile')}" property="mobile" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
        <@vcolumn title="${action.getText('personnel.telphone')}" property="telphone" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('personnel.superiorLeader')}" property="superiorLeader.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
          <@vcolumn title="${action.getText('businessType.name')}" property="businessType.name" sortable="asc">
            <@alignLeft/>
        </@vcolumn>
      
	</@list>
	<#if popWindowFlag?exists&&popWindowFlag==popWindowFlag>
	<#else>
		<#if !first>
		  <#if !(action.isReadOnly())>
	        <@buttonBar>
			  <@crm_disabledOrEnabled_Nodelete_button message="${action.getText('personnelFilesList')}" boxName="personnelFileIds" jsFunctionName="checkInvalidParms()"/>
	       </@buttonBar>
	       </#if>
		</#if>
	</#if>
</@ww.form>
</@htmlPage>
