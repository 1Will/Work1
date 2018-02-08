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
<@framePage title="${action.getText('maintenanceDetailList.title')}">
  <link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
  <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
	<@ww.form name="'maintenanceDetail'" action="'savaMaintenanceDetails'" method="'post'">
		<@ww.token name="savaMaintenanceDetailsToken"/>
		<@ww.hidden name="'maintenance.id'" value="'#{maintenance.id?if_exists}'"/>	
		<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
		<@ww.hidden name="'currentRowNum'" value=""/>
     	<@ww.hidden name="'addDeviceIds'" value=""/>
   		<@ww.hidden name="'addDevice'" value=""/>
   		<@ww.hidden name="'allMaintenanceDetailId'" value=""/>
    	<@ww.hidden name="'allDate'" value=""/>
    	<@ww.hidden name="'allPart'" value=""/>
    	<@ww.hidden name="'allMethod'" value=""/>
    	<@ww.hidden name="'allAppeal'" value=""/>
    	<@ww.hidden name="'allDutyPeople'" value=""/>
    	<@ww.hidden name="'allResultType'" value=""/>
    	<@ww.hidden name="'allResult'" value=""/>
    	<@ww.hidden name="'allComment'" value=""/>
    	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
    	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#assign itemNo=1/>
    	<#assign loopNum=0/>
    	<#assign maintenanceDateIdentityName = 'maintenanceDetail.Date' + '${itemNo}'/>
    	<#assign maintenanceDateImgIdentity = 'maintenanceDetail_' + '${maintenanceDateIdentityName}' + '_trigger'/>
    	<#assign maintenanceResultIdentityName = 'maintenanceResult' + '${itemNo}'/>
    	<#assign procResultIdentityName = 'procResult' + '${itemNo}'/>
    	<#assign resultTypeIdentityName = 'resultType' + '${itemNo}'/>
    	
    	<@list title="" excel=false setupTable=false
           includeParameters="maintenance.id|planProcFlag|readOnly" 
           fieldMap="like:maintenance.id" >
           <input type="hidden" name="detailIds" value="#{object.id}"/>
           <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
		      <@vlh.checkbox property="id" name="maintenanceDetailIds">
			    <@vlh.attribute name="width" value="30" />
			  </@vlh.checkbox>
			</#if>
	       </#if>
	       <#if object.device?exists>
	       		<#assign deviceID="#{object.device.id}"/>
	       </#if>
           <@vcolumn title="${action.getText('maintenanceDeatil.itemNo')}">
           		<a href="#" onclick="return maintenanceDetail_openDialog(#{object.id},${deviceID});"/>${itemNo}</a>
		    <@alignCenter/>
	       </@vcolumn>
           <#assign itemNo = itemNo+1/>
           
           <@vcolumn title="${action.getText('maintenanceDeatil.device.No')}" property="device.deviceNo">
		    	<@alignLeft/>
	       </@vcolumn>
	       
	       <@vcolumn title="${action.getText('maintenanceDeatil.device.name')}" property="device.name">
		    	<@alignLeft/>
	       </@vcolumn>
	       
	       <@vcolumn title="${action.getText('maintenanceDeatil.device.model')}" property="device.model">
		    	<@alignLeft/>
	       </@vcolumn>
	       
	       <@vcolumn title="${action.getText('maintenanceDeatil.device.specification')}" property="device.specification">
		    	<@alignLeft/>
	       </@vcolumn>
		  <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
		      <@vcolumn title="${action.getText('maintenanceDeatil.planDate')}">
		  	    <#assign planDate = ''/>
		        <#if object.planDate?exists>
		          <#assign planDate = "${(object.planDate?string('yyyy-MM-dd'))}"/>
		        </#if>
		        <@eam2008_dataPicker inputName="${maintenanceDateIdentityName}" inputId="${maintenanceDateIdentityName}" inputValue="${planDate}" imgId="${maintenanceDateImgIdentity}" formName="maintenanceDetail"/>
			    <#assign maintenanceDateIdentityName = 'maintenanceDetail.Date' + '${itemNo}'/>
    			<#assign maintenanceDateImgIdentity = 'maintenanceDetail_' + '${maintenanceDateIdentityName}' + '_trigger'/>
			    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		      </@vcolumn>
		    <#else>
		      <@vcolumn title="${action.getText('maintenanceDeatil.planDate')}" property="planDate" format="yyyy-MM-dd">
		        <@alignCenter/>
	          </@vcolumn>
		    </#if>
		  </#if>
		  <#if planProcFlag?exists>
	        <#if planProcFlag == 'PROC'>
	          <@vcolumn title="${action.getText('maintenanceDeatil.actualDate')}">
		  	    <#assign actualDate = ''/>
		        <#if object.actualDate?exists>
		          <#assign actualDate = "${(object.actualDate?string('yyyy-MM-dd'))}"/>
		        </#if>
		        <@eam2008_dataPicker inputName="${maintenanceDateIdentityName}" inputId="${maintenanceDateIdentityName}" inputValue="${actualDate}" imgId="${maintenanceDateImgIdentity}" formName="maintenanceDetail"/>
			    <#assign maintenanceDateIdentityName = 'maintenanceDetail.Date' + '${itemNo}'/>
    			<#assign maintenanceDateImgIdentity = 'maintenanceDetail_' + '${maintenanceDateIdentityName}' + '_trigger'/>
			    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		      </@vcolumn>
	        </#if>
	      </#if>
	      <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
		      <@vcolumn title="${action.getText('maintenanceDeatil.dutyPeople')}">
		    	<#assign dutyPeopleName = ''/>
				<#if object.dutyPeople?exists>
				  <#assign dutyPeopleName = "${object.dutyPeople.name}" />
				</#if>
		    	<input type="text" name="dutyPeople.name" 
		    		   class="underline"  value="${dutyPeopleName}"  maxlength="150" size="10" disabled="false"/>
		    	<label id=""></label>
		    	<a onClick="slectDutyPeople(${loopNum});">
		          <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        </a>
		        <#assign dutyPeopleId = ''/>
				<#if object.dutyPeople?exists>
				 	<#assign dutyPeopleId = "${object.dutyPeople.id}" />
				</#if>
				<input type="hidden" name="dutyPeople.id" value="${dutyPeopleId}" /> 
				<@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
			  </@vcolumn>
			<#else>
			  <@vcolumn title="${action.getText('maintenanceDeatil.dutyPeople')}" property="dutyPeople.name">
		        <@alignLeft/>
	          </@vcolumn>
			</#if>
	       </#if>
	       <#assign loopNum = loopNum + 1 />
	       
	       <#--<#assign status=''/>
            <#if object.resultType?exists>
              <#assign status="${object.resultType.value}"/>
            </#if>
           <@vcolumn title="${action.getText('maintenanceDeatil.resultType')}">${status}<@alignLeft /></@vcolumn>-->
           <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
	           <@vcolumn title="${action.getText('maintenanceDeatil.resultType')}">
			      <select name="${resultTypeIdentityName}" valign="center">
				    <@ww.iterator value="resultTypes" id="resultType">
				      <option value="<@ww.property value="id"/>"><@ww.property value="value"/></option>
				    </@ww.iterator>
			      </select>
			      <script language="javascript">
		            <#if object.resultType?exists>
		              document.forms[0].elements["${resultTypeIdentityName}"].value='${object.resultType.id?if_exists}';
		            </#if>
		          </script>
		          <#assign resultTypeIdentityName = 'resultType' + '${itemNo}'/>
			   </@vcolumn>
			<#else>
				<@vcolumn title="${action.getText('maintenanceDeatil.resultType')}" property="resultType.value">
                  <@alignLeft/>
                </@vcolumn>  
	        </#if>
		  </#if>
	       <#if planProcFlag?exists>
	        <#if planProcFlag == 'PLAN'>
		      <@vcolumn title="${action.getText('maintenanceDeatil.comment')}">
		        <input type="text" name="comment" 
		    		   class="underline"  value="${object.comment?if_exists}"  maxlength="250" size="15"/>
		    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		      </@vcolumn>
		    <#else>
		      <@vcolumn title="${action.getText('maintenanceDeatil.comment')}" property="comment">
		        <@alignLeft/>
	          </@vcolumn>
		    </#if>
		  </#if>
		  
		  <#if planProcFlag?exists>
            <#if (planProcFlag=='PROC')>
	            <@vcolumn title="${action.getText('maintenanceDeatil.result')}">
			      <select name="${procResultIdentityName}" valign="center">
				    <@ww.iterator value="procResults" id="procResult">
				      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
				    </@ww.iterator>
			      </select>
			      <script language="javascript">
		            <#if object.result?exists>
		              document.forms[0].elements["${procResultIdentityName}"].value='${object.result?if_exists}';
		            </#if>
		          </script>
		          <#assign procResultIdentityName = 'procResult' + '${itemNo}'/>
			   </@vcolumn>
		     </#if>
	       </#if>
        </@list>
        
        <@buttonBar>
 	    <#if planProcFlag?exists>
        <#if planProcFlag=='PLAN'>
        <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="multi_select_device_openDialog();"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('maintenancePlanDetail')}?" />
        	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
        		<@ww.param name="'onclick'" value="'return confirmDeletes(\"maintenanceDetailIds\", \"${confirmMessage}\");'"/>
        	</@vsubmit>
        	</#if>
        </#if>
         <#if !(action.isReadOnly())>
        	<@vsubmit name="'save'" value="'${action.getText('save')}'" >
        		<@ww.param name="'onclick'" value="'return validate();'"/>
        		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
        	</#if>
        </#if>
        </@buttonBar>
        <script language="javascript">
      window.onload=function(){
   	    <#if req.getParameter('errorFlag')?has_content>
   	       alert("${action.getText('delete.minantence.failure')}");
        </#if>
        }
        	function multi_select_device_openDialog() {
        		var flag='DeviceMaintenance';
        		var url = '${req.contextPath}/popup/deviceSelector.html';
      			eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
        	}
        	function refresh_multi_device_information(reslut) {
		      if (null != result) {
		        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
		        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
		        document.forms[0].elements["addDevice"].value = "addDevices";
		        document.forms[0].submit();
		      }
		    }
		    
		    /*
	       	* 打开设备保养详细页面
	      	*/
	      	function maintenanceDetail_openDialog(maintenanceDetailID,deviceID) {
	       		var url = '${req.contextPath}/runmaintenance/maintenance/editMaintenanceDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&maintenanceDetail.id=' + maintenanceDetailID +'&device.id='+deviceID+'&planProcFlag=${planProcFlag?if_exists}';
			    popupModalDialog(url, 1024,768);
			    var reloadUrl = '${req.contextPath}/runmaintenance/maintenance/listMaintenanceDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&maintenance.id=' + #{maintenance.id} +'&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
			    
			    self.location.href = reloadUrl; 
			    return false;
		  	}
		    
		    /*
		     * 负责人选择
		    */
		    function slectDutyPeople(loopNum) {
		      	document.forms["maintenanceDetail"].elements["currentRowNum"].value = loopNum;
		      	dutyPeople_OpenDialog();
		    }
		    function dutyPeople_OpenDialog() {
		      	var url = "${req.contextPath}/popup/userSelector.html";
			  	popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
		    }
		    function dutyPeopleSelectorHandler(result) {
		      	var allDutyPeopleId = document.getElementsByName("dutyPeople.id");
		      	var allDutyPeopleName = document.getElementsByName("dutyPeople.name");
		      	var currentRowNum = document.forms["maintenanceDetail"].elements["currentRowNum"].value;
		      	if (null != result) {
		        	allDutyPeopleId[currentRowNum].value = result[0];
		        	allDutyPeopleName[currentRowNum].value = result[1];
		      }
		    }
		    
		    function validate() {
		    	if (0 != document.getElementsByName("detailIds").length) { 
		    		if (document.forms["maintenanceDetail"].elements["planProcFlag"].value == 'PLAN'){
		    			if(!retrieveResultTypeText()){
					  		return false;
					  	}
		    		}
		    		if (document.forms["maintenanceDetail"].elements["planProcFlag"].value == 'PLAN') {
		    			retrieveDutyPeopleText();
		    			//retrievePart();
		    			//retrieveMethod();
		    			//retrieveAppeal();
		    			retrieveComment();
		    		}else {
		    			retrieveResultText();
		    		}
		    		retrieveMaintenanceDetailIdText();
		    		retrieveDateText();
		    	}
		      return true;
		    }
		    
		    /*
		     * 获取保养明细ID的所有值
		    */
		    function retrieveMaintenanceDetailIdText() {
			  var allMaintenanceDetailId = document.getElementsByName("detailIds");
			  var ary = new Array();
			  for (var i=0; i<allMaintenanceDetailId.length; i++) {
			    ary.push(allMaintenanceDetailId[i].value);
			  }
			  document.forms["maintenanceDetail"].elements["allMaintenanceDetailId"].value = ary;
		    }
		    
		    /*
		     * 获取计划完成日期或实际完成日期的所有值
		    */
		    function retrieveDateText() {
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         var DateTagName = 'maintenanceDetail.Date';
		         DateTagName = DateTagName + (i+1); 
		         if ('' != document.forms["maintenanceDetail"].elements[DateTagName].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(document.forms["maintenanceDetail"].elements[DateTagName].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allDate"].value = ary;
		     } 
	    
		     /*
		      * 获取负责人的所有值
		     */
		     function retrieveDutyPeopleText() {
		       var allDutyPeople = document.getElementsByName("dutyPeople.id");
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         if ('' != allDutyPeople[i].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(allDutyPeople[i].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allDutyPeople"].value=ary;
		     }
		    
		     /*
		      * 获取计划明细列表中备注的所有值
		     */
		     function retrieveComment() {
		       var allComment = document.getElementsByName("comment");
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         if ('' != allComment[i].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(allComment[i].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allComment"].value=ary;
		     }
		     
		     /*
		      * 获取计划明细列表中部位的所有值
		     */
		     function retrievePart() {
		       var allPart = document.getElementsByName("part");
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         if ('' != allPart[i].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(allPart[i].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allPart"].value=ary;
		     }
		     
		     /*
		      * 获取计划明细列表中方法的所有值
		     */
		     function retrieveMethod() {
		       var allMethod = document.getElementsByName("method");
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         if ('' != allMethod[i].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(allMethod[i].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allMethod"].value=ary;
		     }
		     
		     /*
		      * 获取实施明细保养结果的所有值
		     */
		     function retrieveResultText() {
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         var procResultTagName = 'procResult';
		         procResultTagName = procResultTagName + (i+1); 
		         if(-1 != document.forms["maintenanceDetail"].elements[procResultTagName].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(document.forms["maintenanceDetail"].elements[procResultTagName].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allResult"].value = ary;
		     }
		     
		     /*
		      * 获取计划明细保养类型的所有值
		     */
		     function retrieveResultTypeText() {
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         var planResultTypeTagName = 'resultType';
		         planResultTypeTagName = planResultTypeTagName + (i+1); 
		         if(-1 != document.forms["maintenanceDetail"].elements[planResultTypeTagName].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(document.forms["maintenanceDetail"].elements[planResultTypeTagName].value);
		         }else{
		         	alert('${action.getText('select.maintenance.resultType')}');
					return false; 
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allResultType"].value = ary;
		       return true;
		     }
		     
		     /*
		      * 获取计划明细列表中要求的所有值
		     */
		     function retrieveAppeal() {
		       var allAppeal = document.getElementsByName("appeal");
		       var allMaintenanceDetailId = document.getElementsByName("detailIds");
		       var ary = new Array();
		       for (var i=0; i<allMaintenanceDetailId.length; i++) {
		         if ('' != allAppeal[i].value) {
		           ary.push(allMaintenanceDetailId[i].value);
		           ary.push(allAppeal[i].value);
		         }
		       }
		       document.forms["maintenanceDetail"].elements["allAppeal"].value=ary;
		     }

        </script>
	</@ww.form>
</@framePage>