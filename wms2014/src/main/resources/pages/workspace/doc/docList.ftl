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
<#-- $Id: docList.ftl 7921 2007-10-22 02:36:23Z qsun $ -->
<#include "../../includes/macros.ftl" />

<@htmlPage title="${action.getText('listDoc.title')}">
	 <@ww.form name="'listForm'" action="'searchDoc'" method="'post'">
	 	<@ww.token name="searchDocToken"/>
	 	<@ww.hidden name="'currentUser.id'" value="#{user.id}"/>
		<#include "docSearcher.ftl"/>
		<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>   
        </@buttonBar>
        <@list title="${action.getText('docList.title')}" includeParameters="currentUser.id|docNo|name|docName|department.id|docType.id|docState.id|createdTime_start|createdTime_end|createdTime" 
        	fieldMap="like:docNo|name,date:createdTime_start|createdTime_end">
             <@vlh.checkbox property="id" name="departmentIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox> 
             <@vcolumn title="${action.getText('doc.no')}" property="docNo" sortable="desc">
             	<a href="editDoc.html?doc.id=#{object.id}&approver.id=#{object.approveId}">${object.docNo?if_exists}</a>
             </@vcolumn>
             <@vcolumn title="${action.getText('doc.type')}" property="docType.name" sortable="desc"/>
             <@vcolumn title="${action.getText('doc.name')}" property="docName"/>
             <@vcolumn title="${action.getText('doc.comment')}" property="comment"/>
             <#if object.finalApr?exists>
             	<#if object.finalApr==true>
             		<#assign finalApprover="${action.getText('yes')}"/>
             	<#else>
             		<#assign finalApprover="${action.getText('no')}"/>	
             	</#if>
             </#if>
             <@vcolumn title="${action.getText('doc.finalapprover')}">${finalApprover}</@vcolumn>
             <@vcolumn title="${action.getText('doc.submittor')}" property="submittor"/>
             <@vcolumn title="${action.getText('doc.submittedTime')}" format="yyyy-MM-dd HH:mm:ss" property="submittedTime"/>
             <@vcolumn title="${action.getText('state')}" property="docState.value"/>
             <@vcolumn title="${action.getText('doc.state.mine')}" property="myDocState.value"/>
             <@vcolumn title="${action.getText('doc.approtedTime')}" format="yyyy-MM-dd HH:mm:ss" property="lastModifiedTime"/>
        </@list>
	 </@ww.form>
</@htmlPage>