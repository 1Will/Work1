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
<#--$Id:$ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('manualList.title')}">
<@ww.form name="'listHelpManual'" action="'searchManuals'" method="'post'">
    <@ww.token name="searchManualsToken"/>
    <#assign itemNo=1/>
    <@inputTable>
      <tr>
        <@ww.textfield label="'${action.getText('helpManual.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('helpManual.vesion')}'" name="'manualVersion'" value="'${req.getParameter('manualVersion')?if_exists}'" cssClass="'underline'"/>
      </tr>
    </@inputTable>
    <@buttonBar>        
      <@vsubmit value="'${action.getText('search')}'"/>
	<#if !(action.isReadOnly())>
		<input type="button" value="${action.getText('upload')}" class="button" onclick="location='${req.contextPath}/base/manual/editManual.html?readOnly=${req.getParameter('readOnly')?if_exists}';" <#if mostNumberForTheManualDoc?exists>disabled="disabled"</#if>/>
	</#if>
    </@buttonBar>
    <@list title="${action.getText('helpManual.list')}" 
        	includeParameters="id|name|manualVersion" 
        	fieldMap="like:id|name|manualVersion" >
      <@vlh.checkbox property="id" name="helpManualIds">
        <@vlh.attribute name="width" value="30" />
      </@vlh.checkbox>
      <@vcolumn title="${action.getText('itemNo')}">
        <a href="editManual.html?manual.id=#{object.id}">#{itemNo}</a>
        <@alignCenter />
      </@vcolumn>
      <#assign itemNo=itemNo + 1/>
      <@vcolumn title="${action.getText('helpManual.name')}"  property="name" sortable="desc">
        <a href="downloadManual.html?manual.id=#{object.id}" title="${action.getText('download')}">${object.name}</a>
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('helpManual.vesion')}" property="manualVersion" >
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('helpManual.description')}" property="description" >
        <@alignLeft />
      </@vcolumn>
      <@vcolumn title="${action.getText('helpManual.createdTime')}"  property="createdTime" format="yyyy-MM-dd" sortable="desc">
        <@alignCenter />
      </@vcolumn>
    </@list>
	<#if !first>
		<@buttonBar>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('helpManual')}?" />
			<@vsubmit name="'delete'"  
						 value="'${action.getText('delete')}'">
	          <@ww.param name="'onclick'" value="'return confirmDeletes(\"helpManualIds\", \"${confirmMessage}\");'"/>
			  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>	             		 			 
	        </@vsubmit>
		</@buttonBar>
    </#if>
</@ww.form>
</@htmlPage>