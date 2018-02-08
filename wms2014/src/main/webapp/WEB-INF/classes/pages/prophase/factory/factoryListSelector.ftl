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

<#-- $Id: userSelector.ftl 11122 2008-02-26 12:54:35Z zbzhang $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('factory.title')}">
<base target="_self">
<@ww.form name="'listForm'" action="'factorySelector'" method="'post'">
    <@ww.token name="factorySelectorToken"/>
    <#include "./factorySearch.ftl" />
    <@buttonBar>
        <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        <@hrefButton value="${action.getText('new')}" url="${req.contextPath}/prophase/factory/editFactory.html?requestSourceType=PopupWin"/>
    </@buttonBar>
    <@list title="${action.getText('factory.list')}" 
	includeParameters="id|readOnly|supplierNo|name|country|zone|cartory.name|onlyValid|onlyInvalid" 
	fieldMap="like:id|supplierNo|name|country|zone|cartory.name" >
		    <@vcolumn title="${action.getText('factory.supplierNo')}">
		    	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}','${object.supplierNo}'));">
			       ${object.name}
		        </a>
		     </@vcolumn>
            <@vcolumn title="${action.getText('factory.supplierType')}" property="supplierType.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.country')}" property="country.name" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.zone')}" property="zone" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.register_funds')}" property="registeredFunds">
            <@alignRight />
            </@vcolumn>
      </@list>
      <#if !first>
     	<@buttonBar> 
	     	<@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	  </#if>
</@ww.form>
</@htmlPage>
