<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: organizationList.ftl 2010-01-26 wliu $ -->

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('organization.title')}">
	<@ww.form name="'listForm'" action="'searchOrg'" namespace="'/organizationOperate'" method="'post'">
        <@ww.token name="searchOrgToken"/>
        <#include "./organizationSearcher.ftl" />
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" />
            <#if !(action.isReadOnly())>
            	<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/organizationOperate/editOrg.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
            </#if>
        </@buttonBar>
        <@list title="${action.getText('organization.list')}" includeParameters="code|name|onlyInvalid|onlyValid" fieldMap="like:code|name" >
	        <@vlh.checkbox property="id" name="orgIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	        <#if !object.disabled>
				<@vcolumn title="${action.getText('organization.code')}" property="code" sortable="asc">
					<a href="editOrg.html?org.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
					<@alignLeft/>
				</@vcolumn>
			<#else>
				<@vcolumn title="${action.getText('organization.code')}" property="code" sortable="asc">
					${object.code}
					<@alignLeft/>
				</@vcolumn>
			</#if>
            <@vcolumn title="${action.getText('organization.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn> 
         
			<@vcolumn title="${action.getText('organization.leader')}" property="leader" sortable="desc">
				<@alignLeft/>
			</@vcolumn>  
			
         	<@vcolumn title="${action.getText('organization.tel')}" property="tel" sortable="desc">
				<@alignLeft/>
         	</@vcolumn> 
         	
         	<#assign indu=""/>
            <#if object.industry?exists>
            	<#assign indu="${object.industry.name}">
            </#if>
         	<@vcolumn title="${action.getText('organization.industry')}" property="industry" sortable="desc">
				${indu?if_exists}
				<@alignLeft/>
         	</@vcolumn>  
         	
         	<#assign natu=""/>
            <#if object.nature?exists>
            	<#assign natu="${object.nature.name}">
            </#if>
         	<@vcolumn title="${action.getText('organization.nature')}" property="nature" sortable="desc">
         		${natu?if_exists}
				<@alignLeft/>
         	</@vcolumn>  
		</@list>
        
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@crm_disabledOrEnabled_button message="${action.getText('organization.info')}" boxName="orgIds" jsFunctionName="checkInvalidParms()"/>
		  <script language="javascript">
			function validateInvalid(delFun, checkFun) {
		      if (delFun) {
		        checkFun;
		        return true;
		      }
		      return false;
		   }
		  </script>
       </@buttonBar>
       </#if>
       </#if>
    </@ww.form>
</@htmlPage>