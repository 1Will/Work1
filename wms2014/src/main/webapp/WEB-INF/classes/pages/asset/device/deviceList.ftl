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
<@htmlPage title="${action.getText('device.title')}">
	 <@ww.form name="'listForm'" action="'searchDevices'" method="'post'">
	 	<@ww.token name="searchDevicesToken"/>
	 	<#include "deviceSearcher.ftl"/>
	 	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	<@buttonBar> 
	 		<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>   
	 		<#--
	 		<#if !(action.isReadOnly())>
	 		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/asset/device/editDevice.html"/>    
        	</#if>
        	-->
        </@buttonBar>
        <@list title="${action.getText('device.list')}" 
        	includeParameters="id|readOnly|deivceStatus.code|filiale.code|deviceNo|name|department.id|category.id|invalid|manager|cardCreatedTime_start|cardCreatedTime_end|yesFull|noFull|onlyValid|onlyInvalid" 
        	fieldMap="like:id|deviceNo|name|installPlace,date:cardCreatedTime_start|cardCreatedTime_end" >
        	<#if (object.enabled)>
            	<@vlh.checkbox property="id" name="deviceIds">
                	<@vlh.attribute name="width" value="30" />
            	</@vlh.checkbox>
            <#else>
                <@vlh.checkbox property="id" name="disabledDevice" attributes="id=disabledDeviceIds onclick=\"notSelectedInvalid('disabledDevice','deviceIds');\"">
            		<@vlh.attribute name="width" value="30" />
            		<@vlh.attribute name="disabled" value="true" />
            	</@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('device.no')}" property="deviceNo" sortable="desc">
              <#if !(action.isOnlyInvalid())>
                <a href="editDevice.html?device.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.deviceNo}</a>
              </#if>
              <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.assetno')}" property="assetNo" sortable="desc"/>
            <@vcolumn title="${action.getText('device.name')}" property="name" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.model')}" property="model" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.specification')}" property="specification" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.category')}"  property="deviceType.name" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.filiale')}"  property="filialeString" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}"  property="department.name" sortable="desc" attributes="width='120'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('managerPep')}" property="manager.name">
            <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('device.installPlace')}" property="installPlace" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            
            <#assign level=''/>
            <#if object.managementLevel?exists>
              <#if (object.managementLevel)=='MAJOR_DEVICES'>
                <#assign level="${action.getText('device.majorDevice')}"/>
              <#elseif (object.managementLevel)=='GENERAL_DEVICES'>
                <#assign level="${action.getText('device.generalDevice')}"/>
              <#elseif (object.managementLevel)=='IMPORTANT_DEVICES'>
                <#assign level="${action.getText('device.importantDevice')}"/>
              </#if>
            </#if>
            <@vcolumn title="${action.getText('device.managementLevel')}">
            	${level}
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.useCategory')}" property="useCategoryString" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.specificationProp')}" property="specificationPropString" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            <#--
              老的精大稀字段，现不用
            <#assign excellentBigSparse=''/>
            <#if object.excellentBigSparse?exists>
              <#assign excellentBigSparse="${object.excellentBigSparse.value}"/>
            </#if>
            <@vcolumn title="${action.getText('device.excellentBigSparse')}">${excellentBigSparse}<@alignLeft /></@vcolumn>-->
            
            <#if (object.full?string)=='true'>
	              <#assign full="${action.getText('YES')}"/>
	            <#else>
	              <#assign full="${action.getText('NO')}"/>
	            </#if>
	            <@vcolumn title="${action.getText('device.full')}">
	            ${full}
	             <@alignLeft/>
	            </@vcolumn>
	            
	        <#if (object.emphasis?string)=='true'>
	              <#assign emphasis="${action.getText('YES')}"/>
	            <#else>
	              <#assign emphasis="${action.getText('NO')}"/>
	            </#if>
	            <@vcolumn title="${action.getText('device.emphasis')}">
	            ${emphasis}
	             <@alignLeft/>
	            </@vcolumn>
	            
	        <#if (object.standard?string)=='true'>
	              <#assign standard="${action.getText('YES')}"/>
	            <#else>
	              <#assign standard="${action.getText('NO')}"/>
	            </#if>
	            <@vcolumn title="${action.getText('device.standard')}">
	            ${standard}
	             <@alignLeft/>
	            </@vcolumn>    
            
            <@vcolumn title="${action.getText('device.cardCreatedTime')}" property="cardCreatedTime" format="${action.getText('dateFormat')}" sortable="desc"/>
            <#assign status=''/>
            <#if object.deviceStatus?exists>
              <#assign status="${object.deviceStatus.value}"/>
            </#if>
            <@vcolumn title="${action.getText('device.status')}">${status}<@alignLeft /></@vcolumn>
            <@vcolumn title="${action.getText('device.occursDetails')}">
            	<a href="#" onclick="searchOccursHistory(#{object.id});">${action.getText('device.searchOccursHistory')}</a>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
        	<@eam2008_disabledOrEnabled_button message="${action.getText('device')}" boxName="deviceIds" jsFunctionName="checkInvalidParms()"/>
        </@buttonBar>
        </#if>
        </#if>
     </@ww.form>
     <script language="javascript">
       <#if !first && valueListNoRecords>
       <#--<#if !(action.isReadOnly())>
         document.forms[0].elements["print"].disabled = true;
         </#if>-->
       </#if>
       window.onload = function() {
       filialeSelectDeptDWR();
       <#--
       		<#if first>
			    <#if loginUser.department?exists>
			      document.getElementById("department.id").value = #{loginUser.department.id};
			    </#if>
			  </#if>
		-->
       }
       function open_deviceListPdf() {
         var ary = new Array();
         if ('' == document.forms[0].elements["deviceNo"].value) {
           ary.push("deviceNo");
           ary.push("null");
         } else {
           ary.push("deviceNo");
           ary.push(document.forms[0].elements["deviceNo"].value);
         }
         if ('' == document.forms[0].elements["name"].value) {
           ary.push("name");
           ary.push("null");
         } else {
           ary.push("name");
           ary.push(document.forms[0].elements["name"].value);
         }
         if ('-1' == document.forms[0].elements["category.id"].value) {
           ary.push("categoryId");
           ary.push("null");
         } else {
           ary.push("categoryId");
           ary.push(document.forms[0].elements["category.id"].value);
         }
         if ('-1' == document.forms[0].elements["department.id"].value) {
           ary.push("departmentId");
           ary.push("null");
         } else {
           ary.push("departmentId");
           ary.push(document.forms[0].elements["department.id"].value);
         }
         /*
         if ('' == document.forms[0].elements["installPlace"].value) {
           ary.push("installPlace");
           ary.push("null");
         } else {
           ary.push("installPlace");
           ary.push(document.forms[0].elements["installPlace"].value);
         }
         */
         if ('' == document.forms[0].elements["cardCreatedTime_start"].value) {
           ary.push("cardCreatedTime_start");
           ary.push("null");
         } else {
           ary.push("cardCreatedTime_start");
           ary.push(document.forms[0].elements["cardCreatedTime_start"].value);
         }
         if ('' == document.forms[0].elements["cardCreatedTime_end"].value) {
           ary.push("cardCreatedTime_end");
           ary.push("null");
         } else {
           ary.push("cardCreatedTime_end");
           ary.push(document.forms[0].elements["cardCreatedTime_end"].value);
         }
                                                           
         var url = '${req.contextPath}/reports/device/deviceList.pdf?searchOption=' + ary;
         url = encodeURI(url);
         window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       <!-- 查看发生历史 -->
       function searchOccursHistory(deviceId){
			var url = "${req.contextPath}/asset/device/searchOccursHistory.html?deviceId="+deviceId;
	   		window.open(url,"_new","toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=150,top=100");

       }
     </script> 
</@htmlPage>