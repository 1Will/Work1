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

<@framePage title="${action.getText('calibrationDetailList.title')}">
	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
    <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
    <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
     <@ww.form name="'calibrationDetail'" action="'savaCalibrationDetails'" method="'post'">
        <@ww.token name="savaCalibrationDetailsToken"/>
     	<@ww.hidden name="'calibration.id'" value="'#{calibration.id?if_exists}'"/>
     	<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     	<@ww.hidden name="'currentRowNum'" value=""/>
     	<@ww.hidden name="'addToolingIds'" value=""/>
   		<@ww.hidden name="'addTooling'" value=""/>
   		<@ww.hidden name="'allCalibrationDetailId'" value=""/>
   		<@ww.hidden name="'allDutyPeople'" value=""/>
   		<@ww.hidden name="'allResult'" value=""/>
   		<@ww.hidden name="'allCalibrationResult'" value=""/>
   		<@ww.hidden name="'allDate'" value=""/> 
   		<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
   		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
   		<#assign itemNo=1/>
		<#assign loopNum=0/>
		<#assign calibrationDateIdentityName = 'calibrationDetail.Date' + '${itemNo}'/>
		<#assign calibrationDateImgIdentity = 'calibrationDetail_' + '${calibrationDateIdentityName}' + '_trigger'/>
		<#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
		<#assign calibrationResultIdentityName = 'calibrationResult' + '${itemNo}'/>
     	    <@list title="" excel=false setupTable=false
        		includeParameters="calibration.id|planProcFlag|readOnly" 
        		fieldMap="like:calibration.id" >
        		<input type="hidden" name="detailIds" value="#{object.id}"/>
        		
        		<#if planProcFlag?exists>
	                <#if (planProcFlag=='PLAN')>
		        		<@vlh.checkbox property="id" name="calibrationDetailIds">
			            	<@vlh.attribute name="width" value="30" />
			            </@vlh.checkbox>
			         </#if>
		         </#if>
		         <#if object.tooling?exists>
		       		<#assign toolingID="#{object.tooling.id}"/>
		       	</#if>
		        <@vcolumn title="${action.getText('calibrationDetail.itemNo')}">
		        	<a href="#" onclick="return calibrationDetail_openDialog(#{object.id},${toolingID});"/>${itemNo}</a>
			    <@alignCenter/>
		       </@vcolumn>
	           <#assign itemNo = itemNo+1/> 
	            <@vcolumn title="${action.getText('calibrationDetail.tooling.deviceNo')}" property="tooling.deviceNo">
	              <@alignCenter/>
	            </@vcolumn>
	            <@vcolumn title="${action.getText('calibrationDetail.tooling.name')}" property="tooling.name">
	            	<@alignLeft/>
                </@vcolumn>
	            <@vcolumn title="${action.getText('calibrationDetail.tooling.graphNo')}" property="tooling.graphNo">
	            	<@alignLeft/>
                </@vcolumn>
                <@vcolumn title="${action.getText('calibrationDetail.tooling.demarcateCycle')}" property="tooling.demarcateCycle">
	            	<@alignRight/>
                </@vcolumn>
                <#if planProcFlag?exists>
	        		<#if planProcFlag == 'PLAN'>
		                <@vcolumn title="${action.getText('calibrationDetail.dutyPeople')}">
			                <#assign dutyPeopleIdentity = 'dutyPeople' + '${loopNum}'/>
				        	<#assign dutyPeopleName = ''/>
							<#if object.dutyPeople?exists>
							 <#assign dutyPeopleName = "${object.dutyPeople.name}" />
							</#if>
				        		<input type="text" name="dutyPeople.name" 
				        			class="underline"  value="${dutyPeopleName}"  maxlength="150" size="10" disabled="false"/>
				        		<label id=""></label>
					    		<a onClick="selectDutyPeople(${loopNum});">
					        		<img id="${dutyPeopleIdentity}"src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
					        	</a>
					        <#assign dutyPeopleId = ''/>
							<#if object.dutyPeople?exists>
							 	<#assign dutyPeopleId = "${object.dutyPeople.id}" />
							</#if>
							<input type="hidden" name="dutyPeople.id" value="${dutyPeopleId}" /> 
			             </@vcolumn>
                	<#else>
                		<@vcolumn title="${action.getText('calibrationDetail.dutyPeople')}" property="dutyPeople.name">
					        <@alignLeft/>
				          </@vcolumn>
                	</#if>
	       		</#if>
                
                <@vcolumn title="${action.getText('department')}" property="tooling.department.name">
	            	<@alignLeft/>
                </@vcolumn>
                
                <#if planProcFlag?exists>
	                <#if (planProcFlag=='PLAN')>
		                <@vcolumn title="${action.getText('calibrationDetail.planDate')}" >
			            	<#assign planDate = ''/>
			            	<#if object.planDate?exists>
					          <#assign planDate = "${(object.planDate?string('yyyy-MM-dd'))}"/>
					        </#if>
					        <@eam2008_dataPicker inputName="${calibrationDateIdentityName}" inputId="${calibrationDateIdentityName}" inputValue="${planDate}" imgId="${calibrationDateImgIdentity}" formName="calibrationDetail"/>
						    <#assign calibrationDateIdentityName = 'calibrationDetail.Date' + '${itemNo}'/>
							<#assign calibrationDateImgIdentity = 'calibrationDetail_' + '${calibrationDateIdentityName}' + '_trigger'/>
						    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		                </@vcolumn>
		            <#else>
		            	<@vcolumn title="${action.getText('calibrationDetail.planDate')}" property="planDate" format="yyyy-MM-dd">
			            	<@alignCenter/>
		                </@vcolumn>
		             </#if>
		         </#if>
                <#if planProcFlag?exists>
	                <#if (planProcFlag=='PROC')>
		                <@vcolumn title="${action.getText('calibrationDetail.actualDate')}">
			            	<#assign actualDate = ''/>
			            	<#if object.actualDate?exists>
					          <#assign actualDate = "${(object.actualDate?string('yyyy-MM-dd'))}"/>
					        </#if>
					        <@eam2008_dataPicker inputName="${calibrationDateIdentityName}" inputId="${calibrationDateIdentityName}" inputValue="${actualDate}" imgId="${calibrationDateImgIdentity}" formName="calibrationDetail"/>
		                	<#assign calibrationDateIdentityName = 'calibrationDetail.Date' + '${itemNo}'/>
							<#assign calibrationDateImgIdentity = 'calibrationDetail_' + '${calibrationDateIdentityName}' + '_trigger'/>
						    <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
		                </@vcolumn>
		            </#if>
		         </#if>
                <#assign loopNum = loopNum + 1 />
                <#if planProcFlag?exists>
	                <#if (planProcFlag=='PROC')>
			            <@vcolumn title="${action.getText('calibrationDetail.calibrationResult')}">
					      <select name="${calibrationResultIdentityName}" valign="center">
						    <@ww.iterator value="passResults" id="passResult">
						      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
						    </@ww.iterator>
					      </select>
					      <script language="javascript">
				            <#if object.calibrationResult?exists>
				              document.forms[0].elements["${calibrationResultIdentityName}"].value='${object.calibrationResult?if_exists}';
				            </#if>
				          </script>
				          <#assign calibrationResultIdentityName = 'calibrationResult' + '${itemNo}'/>
					   </@vcolumn>
				   </#if>
	          </#if>
                <#if planProcFlag?exists>
	                <#if (planProcFlag=='PROC')>
			            <@vcolumn title="${action.getText('calibrationDetail.result')}">
					      <select name="${execResultIdentityName}" valign="center">
						    <@ww.iterator value="procResults" id="procResult">
						      <option value="<@ww.property value="value"/>"><@ww.property value="label"/></option>
						    </@ww.iterator>
					      </select>
					      <script language="javascript">
				            <#if object.result?exists>
				              document.forms[0].elements["${execResultIdentityName}"].value='${object.result?if_exists}';
				            </#if>
				          </script>
				          <#assign execResultIdentityName = 'execResult' + '${itemNo}'/>
					   </@vcolumn>
				   </#if>
	          </#if>
	          
		  </@list>
     	    
 	    <@buttonBar>
 	    <#if planProcFlag?exists>
        <#if planProcFlag=='PLAN'>
          <#if !(action.isReadOnly())>
            <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="select_tooling_openDialog();"/>
        	<@vsubmit name="'save'" value="'${action.getText('save')}'" >
        		<@ww.param name="'onclick'" value="'return validate();'"/>
        		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
        	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('calibrationPlan')}?" />
        	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
        		<@ww.param name="'onclick'" value="'return confirmDeletes(\"calibrationDetailIds\", \"${confirmMessage}\");'"/>
        		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
          </#if>
        <#else>
          <#if !(action.isReadOnly())>
        	<@vsubmit name="'save'" value="'${action.getText('save')}'" >
        		<@ww.param name="'onclick'" value="'return validate();'"/>
        		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
        	</@vsubmit>
          </#if>
        </#if>
        </#if>
        </@buttonBar>

     </@ww.form>
     <script language="javascript">
    <#if req.getParameter('errorFlag')?has_content>
      alert("${action.getText('delete.CalibrationDetail.failure')}");
    </#if>
     	 function select_tooling_openDialog() {
     	 	var flag='ToolingCalibration';
	      	var url = '${req.contextPath}/popup/toolingSelector.html';
	      	url = url + '?hiddenCheckBox=true&multi=multiSelect&flag='+flag; 
	      	popupModalDialog(url, 800, 600, refresh_tooling_information);
	     } 
	     
	     function refresh_tooling_information(reslut) {
	      if (null != result) {
	        var addToolingIds = result.substring(0, result.lastIndexOf(","));
	        document.forms[0].elements["addToolingIds"].value = addToolingIds;
	        document.forms[0].elements["addTooling"].value = "addToolings";
	        document.forms[0].submit();
	      }
	    }
     	 /*
	      * 责任人选择
	     */
	      function selectDutyPeople(loopNum) {
	         document.forms["calibrationDetail"].elements["currentRowNum"].value = loopNum;
		     dutyPeople_OpenDialog();
	      }
	      function dutyPeople_OpenDialog() {
	        var url = "${req.contextPath}/popup/userSelector.html";
			popupModalDialog(url, 800, 600, dutyPeopleSelectorHandler);
	      }
	      function dutyPeopleSelectorHandler(result) {
	        var allDutyPeopleId = document.getElementsByName("dutyPeople.id");
	        var allDutyPeopleName = document.getElementsByName("dutyPeople.name");
	        var currentRowNum = document.forms["calibrationDetail"].elements["currentRowNum"].value;
	        if (null != result) {
	          allDutyPeopleId[currentRowNum].value = result[0];
	          allDutyPeopleName[currentRowNum].value = result[1];
	        }
	      }
	    
	    function validate() {
	    	if (0 != document.getElementsByName("detailIds").length) { 
	    		if (document.forms["calibrationDetail"].elements["planProcFlag"].value == 'PLAN') {
	    			retrieveDutyPeopleText();
	    			//retrieveComment();
	    		}else {
	    			retrieveResultText();
	    			retrieveCalibrationResultText();
	    		}
	    		retrieveCalibrationDetailIdText();
	    		retrieveDateText();
	    		
	    	}
	      return true;
	    }
	     
	    /*
	     * 获取标定明细ID的所有值
	    */
	    function retrieveCalibrationDetailIdText() {
		  var allCalibrationDetailId = document.getElementsByName("detailIds");
		  var ary = new Array();
		  for (var i=0; i<allCalibrationDetailId.length; i++) {
		    ary.push(allCalibrationDetailId[i].value);
		  }
		  document.forms["calibrationDetail"].elements["allCalibrationDetailId"].value = ary;
	    }
	     
	     /*
	     * 获取计划完成日期或实际完成日期的所有值
	    */
	    function retrieveDateText() {
	       var allCalibrationDetailId = document.getElementsByName("detailIds");
	       var ary = new Array();
	       for (var i=0; i<allCalibrationDetailId.length; i++) {
	         var DateTagName = 'calibrationDetail.Date';
	         DateTagName = DateTagName + (i+1); 
	         if ('' != document.forms["calibrationDetail"].elements[DateTagName].value) {
	           ary.push(allCalibrationDetailId[i].value);
	           ary.push(document.forms["calibrationDetail"].elements[DateTagName].value);
	         }
	       }
	       document.forms["calibrationDetail"].elements["allDate"].value = ary;
	     } 
	     
	     /*
	      * 获取负责人的所有值
	     */
	     function retrieveDutyPeopleText() {
	       var allDutyPeople = document.getElementsByName("dutyPeople.id");
	       var allCalibrationDetailId = document.getElementsByName("detailIds");
	       var ary = new Array();
	       for (var i=0; i<allCalibrationDetailId.length; i++) {
	         if ('' != allDutyPeople[i].value) {
	           ary.push(allCalibrationDetailId[i].value);
	           ary.push(allDutyPeople[i].value);
	         }
	       }
	       document.forms["calibrationDetail"].elements["allDutyPeople"].value=ary;
	     } 
	     
	      /*
	       * 获取实施明细列表中的标定实施结果的所有值（完成，未完成）
	   	  */
	     function retrieveResultText() {
	       var allCalibrationDetailId = document.getElementsByName("detailIds");
	       var ary = new Array();
	       for (var i=0; i<allCalibrationDetailId.length; i++) {
	         var execResultTagName = 'execResult';
	         execResultTagName = execResultTagName + (i+1); 
	         ary.push(allCalibrationDetailId[i].value);
	         ary.push(document.forms["calibrationDetail"].elements[execResultTagName].value);
	       }
	       document.forms["calibrationDetail"].elements["allResult"].value = ary; 
	     }
	      /*
	       * 获取实施明细列表中的标定结果的所有值（合格，不合格）
	   	  */
	     function retrieveCalibrationResultText() {
	       var allCalibrationDetailId = document.getElementsByName("detailIds");
	       var ary = new Array();
	       for (var i=0; i<allCalibrationDetailId.length; i++) {
	         var calibrationResultTagName = 'calibrationResult';
	         calibrationResultTagName = calibrationResultTagName + (i+1); 
	         ary.push(allCalibrationDetailId[i].value);
	         ary.push(document.forms["calibrationDetail"].elements[calibrationResultTagName].value);
	       }
	       document.forms["calibrationDetail"].elements["allCalibrationResult"].value = ary; 
	     }
	     /*
       	  * 打开工装标定详细页面
      	 */
      	function calibrationDetail_openDialog(calibrationDetailID,toolingID) {
       		var url = '${req.contextPath}/runmaintenance/calibration/editCalibrationDetail.html?readOnly=${req.getParameter('readOnly')?if_exists}&calibrationDetail.id=' + calibrationDetailID +'&tooling.id='+toolingID+'&planProcFlag=${planProcFlag?if_exists}';
		    popupModalDialog(url, 1024,768);
		    var reloadUrl = '${req.contextPath}/runmaintenance/calibration/listCalibrationDetails.html?readOnly=${req.getParameter('readOnly')?if_exists}&calibration.id=' + #{calibration.id} +'&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		    
		    self.location.href = reloadUrl; 
		    return false;
	  	}
	    </script>
</@framePage>