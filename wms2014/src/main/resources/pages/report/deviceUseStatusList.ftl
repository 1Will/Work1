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
<#-- $Id: -->
<#include "../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('deviceUseStatus.title')}">
	<@ww.form namespace="'/report'" name="'ListDeviceUseStatus'" action="'searchDeviceUseStatus'" method="'post'">
	 <@ww.token name="searchDeviceUseStatusToken"/>   
	 <@inputTable>
	 <tr>
	 	<@ww.select label="'${action.getText('month')}'" required="false" name="'month'" 
		    		value="'${req.getParameter('month')?if_exists}'" listKey="value" listValue="label"
		            list="months" emptyOption="false" disabled="false">
		</@ww.select>
	 </tr>
	 </@inputTable>
	 <@buttonBar>
	 		<@vsubmit value="'${action.getText('search')}'"/>
	 </@buttonBar>
	 <@list title="${action.getText('deviceUseStatus.list')}"
	 	includeParameters="month" 
        	fieldMap="like:" >
		<#if (object.filaileId)?exists>
		<@vcolumn title="${action.getText('deviceUseStatus.department')}" property="filaileId.value" >
        	<@alignLeft />
        </@vcolumn>
		<#else>
       	<@vcolumn title="${action.getText('deviceUseStatus.department')}" property="department" >
        	<@alignLeft />
        </@vcolumn>
        </#if>
        <@vcolumn title="${action.getText('deviceUseStatus.full')}" property="full" format="#,###,##0.00" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.fullAmount')}" property="fullAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.allAmount')}" property="allAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.using')}" property="using" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.useAmount')}" property="useAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.systemDaisTime')}" property="systemDaisTime" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.JQusing')}" property="JQusing" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.JQuseAmount')}" property="JQuseAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseStatus.JQsystemDaisTime')}" property="JQsystemDaisTime" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('month')}" property="month" >
        	<@alignLeft />
        </@vcolumn>
       </@list>
       <#if !first>
        <@buttonBar>
        	<@vbutton name="pdfPrint"  class="button" value="${action.getText('pdfPrint')}" onclick="open_deviceUseStatusListPdf();" />
        	<@vbutton name="xlsPrint"  class="button" value="${action.getText('xlsPrint')}" onclick="open_deviceUseStatusListXls();" />
        </@buttonBar>
       </#if> 
      </@ww.form>
      <script language="javascript">
      	<#if !first && valueListNoRecords>
         	document.forms[0].elements["pdfPrint"].disabled = true;
         	document.forms[0].elements["xlsPrint"].disabled = true;
       </#if>
      	function open_deviceUseStatusListPdf() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/deviceUseStatusList.pdf?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_deviceUseStatusListXls() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/deviceUseStatusList.xls?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
      </script> 
   </@htmlPage>     