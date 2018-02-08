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

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('accessoryDevice.select.list')}">
<base target="_self">

<@ww.form name="'accessoryDevice'" action="'searchAccessoryDeviceSelector'" method="'post'">
	<@ww.token name="deviceSelectorToken"/>
	<#if device?exists>
	  <@ww.hidden name="'device.id'" value="#{device.id}"/>
	</#if>
	<@ww.hidden name="'view'" value="'${req.getParameter('view')?if_exists}'"/>
	
    <#include "./deviceSearcher.ftl" />
  
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        <@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
    </@buttonBar>
   
     <@list title="${action.getText('device.list')}" 
        	includeParameters="device.id|deviceNo|name|cardCreatedTime_start|cardCreatedTime_end|invalid|department.id|category.id" 
        	fieldMap="like:deviceNo|name|installPlace,date:cardCreatedTime_start|cardCreatedTime_end" >
            <@vlh.checkbox property="id" name="deviceIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('device.no')}" property="deviceNo" sortable="desc"/>
            <@vcolumn title="${action.getText('device.assetno')}" property="assetNo" sortable="desc"/>
            <@vcolumn title="${action.getText('device.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('device.model')}" property="model" />
            <@vcolumn title="${action.getText('device.specification')}" property="specification" />
            <@vcolumn title="${action.getText('device.category')}"  property="deviceType.name" /> 
            <@vcolumn title="${action.getText('device.department')}"  property="department.name" />
            <@vcolumn title="${action.getText('device.installPlace')}" property="installPlace"/>
            <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="cardCreatedTime" format="yyyy-MM-dd" sortable="desc"/>
        	<@vcolumn title="${action.getText('state')}" property="job.docState.value"/>
        </@list>
	      <#if !first>
	      <@buttonBar>
	      	<@vsubmit name="'choose'" value="'${action.getText('choose')}'" onclick="'return confirmSelects(\"deviceIds\");'">
	      		<#--<@ww.param name="'onclick'" value="'return confirmSelects(\"deviceIds\");'"/>-->
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
	      	
	      </@buttonBar>
	      </#if>
	      <script language="javascript">	
//	        document.forms[0].elements["docState.code"].disabled=true;
//     		document.forms[0].elements["docState.code"].value = 'DOC_APPROVED';      	
	      	function chooseDevice() {
	      		checkInvalidParms();
	      		document.forms["accessoryDevice"].action="createAccessoryDevice.html"
	      		//document.forms["accessoryDevice"].submit();
	      		return true;
	      	}
	      	
	      	function confirmSelects(boxname) {
	      		if (!hasChecked(boxname)) {
	      			alert("${action.getText('device.noSelect')}");
	      			return false;
	      		}
	      		chooseDevice();
	      		return true;
	      	}
	      </script>
	        	      	      	       
</@ww.form>
</@htmlPage>
