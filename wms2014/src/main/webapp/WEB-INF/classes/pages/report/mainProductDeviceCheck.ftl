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
<@htmlPage title="${action.getText('mainProductDevice.title')}">
	<@ww.form namespace="'/report'" name="'listMainProductDeviceCheck'" action="'searchMainProductDeviceCheck'" method="'post'">
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
	 <@list title="${action.getText('mainProductDeviceCheck.list')}"
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
        <@vcolumn title="${action.getText('device.total')}" property="deviceTotal">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('device.fullAmount')}" property="fullDeviceAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('unFullDeviceAmount')}" property="unFullDeviceAmount" >
        	<@alignRight />
        </@vcolumn>
         <@vcolumn title="${action.getText('stopUseAmount')}" property="stopUseAmount" >
        	<@alignRight />
        </@vcolumn>
         <@vcolumn title="${action.getText('deviceFullModulus')}" property="deviceFullModulus" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('useAmount')}" property="useAmount" >
        	<@alignRight />
        </@vcolumn>
         <@vcolumn title="${action.getText('keepAmount')}" property="keepAmount" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('useDaisTime')}" property="useDaisTime" >
        	<@alignRight />
        </@vcolumn>
         <@vcolumn title="${action.getText('systemDaisTime')}" property="systemDaisTime" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('maintainDate')}" property="maintainDate" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('faultStopDate')}" property="faultStopDate" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceUseModulus')}" property="deviceUseModulus" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceEffectModulus')}" property="deviceEffectModulus" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('faultStopModulus')}" property="faultStopModulus" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('JQusing')}" property="JQusing" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('planMaintainAmount')}" property="planMaintainAmount">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('practiceMaintainAmount')}" property="practiceMaintainAmount">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('month')}" property="month" >
        	<@alignLeft />
        </@vcolumn>
       </@list>
       <#if !first>
        <@buttonBar>
        	<@vbutton name="pdfPrint"  class="button" value="${action.getText('pdfPrint')}" onclick="open_deviceProductCheckListPdf();" />
        	<@vbutton name="xlsPrint"  class="button" value="${action.getText('xlsPrint')}" onclick="open_deviceProductCheckListXls();" />
        </@buttonBar>
       </#if> 
      </@ww.form>
      <script language="javascript">
       <#if !first && valueListNoRecords>
         document.forms[0].elements["pdfPrint"].disabled = true;
         document.forms[0].elements["xlsPrint"].disabled = true;
       </#if>
      	function open_deviceProductCheckListPdf() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/mainDeviceProductCheckList.pdf?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_deviceProductCheckListXls() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/mainDeviceProductCheckList.xls?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
      </script>
   </@htmlPage>     