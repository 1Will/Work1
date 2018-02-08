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
<@htmlPage title="${action.getText('deviceFullUsing.title')}">
	<@ww.form namespace="'/report'" name="'ListDeviceUseStatus'" action="'searchDeviceFullUsing'" method="'post'">
	 <@ww.token name="searchDeviceFullUsingToken"/>   
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
	 <@list title="${action.getText('deviceFullUsing.list')}"
	 	includeParameters="month" 
        	fieldMap="like:" >
       	<@vcolumn title="${action.getText('deviceFullUsing.description')}" property="description">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.unitIndex')}" property="unitIndex" format="#,###,##0.00" >
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.unitChildItem')}" property="unitChildItem" >
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.unitMontherItem')}" property="unitMontherItem" >
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.factIndex')}" property="factIndex" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.factChildItem')}" property="factChildItem" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.factMontherItem')}" property="factMontherItem" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.yearIndex')}" property="yearIndex" format="#,###,##0.00">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.yearChildItem')}" property="yearChildItem" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('deviceFullUsing.yearMontherItem')}" property="yearMontherItem" >
        	<@alignRight />
        </@vcolumn>
       </@list>
       <#if !first>
        <@buttonBar>
        	<@vbutton name="pdfPrint"  class="button" value="${action.getText('pdfPrint')}" onclick="open_deviceFullUsingListPdf();" />
        	<@vbutton name="xlsPrint"  class="button" value="${action.getText('xlsPrint')}" onclick="open_deviceFullUsingListXls();" />
        </@buttonBar>
       </#if> 
      </@ww.form>
      <script language="javascript">
       <#if !first && valueListNoRecords>
         document.forms[0].elements["pdfPrint"].disabled = true;
         document.forms[0].elements["xlsPrint"].disabled = true;
       </#if>
      	function open_deviceFullUsingListPdf() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/deviceFullUsingList.pdf?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_deviceFullUsingListXls() {
      		var month = document.forms[0].elements["month"].value;
      		var url='${req.contextPath}/reports/device/deviceFullUsingList.xls?month='+month;
      		url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
      </script> 
   </@htmlPage>     