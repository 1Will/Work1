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
<@framePage title="${action.getText('deviceMaintenanceDetailList.title')}">
  <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
	<@ww.form name="'deviceMaintenanceDetail'" action="'savaDeviceMaintenanceDetails'" method="'post'">
		<@ww.token name="savaDeviceMaintenanceDetailsToken"/>
		<@ww.hidden name="'device.id'" value="#{device.id?if_exists}"/>
		<@ww.hidden name="'maintenanceDetail.id'" value="'#{detail.id?if_exists}'"/>
		<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>	
    	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
    	<#assign itemNo=1/>
    	<#assign loopNum=0/>
    	<@list title="" excel=false setupTable=false
           includeParameters="maintenanceDetail.id|readOnly" 
           fieldMap="like:maintenanceDetail.id" >
           <input type="hidden" name="deviceMaintenanceDetailIds" value="#{object.id}"/>
           <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
		      <@vlh.checkbox property="id" name="deviceMaintenanceDetailIds">
			    <@vlh.attribute name="width" value="30" />
			  </@vlh.checkbox>
			  </#if>
		   </#if>
           <@vcolumn title="${action.getText('deviceMaintenanceDetail.itemNo')}">
           		<a href="#" onclick="deviceMaintenanceDetail_openDialog(#{detail.id},#{object.id});return false">${itemNo}</a>
		    <@alignCenter/>
	       </@vcolumn>
           <#assign itemNo = itemNo+1/>
           

	      <@vcolumn title="${action.getText('maintenanceDeatil.part')}" property="part">
	        <@alignLeft/>
          </@vcolumn>

	      <@vcolumn title="${action.getText('maintenanceDeatil.method')}" property="method">
	        <@alignLeft/>
          </@vcolumn>
		  
	      <@vcolumn title="${action.getText('maintenanceDeatil.appeal')}" property="appeal">
	        <@alignLeft/>
          </@vcolumn>
           
        </@list>
        
        <@buttonBar>
 	    <#if planProcFlag?exists>
        <#if planProcFlag=='PLAN'>
           <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="deviceMaintenanceDetail_openDialog(#{detail.id},null)"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('deviceMaintenanceDetail')}?" />
        	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
        		<@ww.param name="'onclick'" value="'return confirmDeletes(\"deviceMaintenanceDetailIds\", \"${confirmMessage}\");'"/>
        	    <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
        </#if>
        </#if>	
        </#if>
        </@buttonBar>
        <script language="javascript">
		    function deviceMaintenanceDetail_openDialog(maintenanceDetailID,deviceMaintenanceDetailID) {
	       		var url = '${req.contextPath}/runmaintenance/maintenance/editDeviceMaintenanceDetail.html?detail.id=' + maintenanceDetailID +'&planProcFlag=${planProcFlag?if_exists}&device.id=#{device.id}';                     
			    if (null != deviceMaintenanceDetailID){
			    	url = url +'&deviceMaintenanceDetail.id='+deviceMaintenanceDetailID;
			    }
			    popupModalDialog(url,800,600);
			    var reloadUrl = '${req.contextPath}/runmaintenance/maintenance/listDeviceMaintenanceDetails.html?maintenanceDetail.id=#{detail.id}&planProcFlag=${planProcFlag?if_exists}&device.id=#{device.id}';
			    self.location.href = reloadUrl; 
		  	}

        </script>
	</@ww.form>
</@framePage>