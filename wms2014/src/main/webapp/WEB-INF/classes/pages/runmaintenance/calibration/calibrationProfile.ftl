<#include "../../includes/eam2008.ftl" />
<#assign preRepairTitle = ''/>
<#assign require = ''/>
<#assign disabled = ''/>
<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign calibrationTitle = "${action.getText('calibrationPlanEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign calibrationTitle = "${action.getText('calibrationProcEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${calibrationTitle}">
    <@ww.form namespace="'/runmaintenance/calibration'" name="'calibration'" action="'saveCalibration'" method="'post'" validate="true">
        <@ww.token name="saveCalibrationToken"/>
         <@ww.hidden name="'calibration.id'" value="${calibration.id?if_exists}"/>
        <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('calibration.calibrationNo')}'" name="'calibration.planNo'" value="'${calibration.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('calibration.calibrationName')}'" name="'calibration.planName'" value="'${calibration.planName?if_exists}'" cssClass="'underline'" required="${require}" disabled="${disabled}"/>
        	</tr>
        	<tr>
				<@ww.select label="'${action.getText('department')}'" name="'department.id'" 
		    			    listKey="id" listValue="name" 
		                    list="departments" emptyOption="false"  required="true" disabled="${disabled}">
		    	</@ww.select>
		    	<#--<@ww.textfield label="'${action.getText('month')}'" name="'calibration.month'" 
 							value="'${calibration.month?if_exists}'" cssClass="'underline'" 
 							/>-->
 			   <#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
 				    <@pp.datePicker label="'${action.getText('month')}'" name="'calibration.month'" 
		       		                value="'${(calibration.month)?if_exists}'" cssClass="'underline'" size="15" 
	     			                dateFormat="'%Y-%m'" required="true"/>
	     	      <#else>
	     	        <@ww.textfield label="'${action.getText('month')}'" name="'calibration.month'" 
 							        value="'${calibration.month?if_exists}'" cssClass="'underline'" disabled="true"/>
                  </#if>
               </#if>
	     	      
            </tr>
	        <tr>
	         	<#assign planCreatorName = ''/>
					<#if calibration.writer?exists>
					  <#assign planCreatorName = "${calibration.writer.name}" />
					<#elseif loginUser?exists>
					  <#assign planCreatorName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
	        	<label class="label">${action.getText('calibration.writer')}:</label></td>
	        	<td>
	        		<input type="text" name="writer.name" 
	        			class="underline"  value="${planCreatorName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
	        	    <#if planProcFlag?exists>
			          <#if (planProcFlag=='PLAN')>
	        	        <a onClick="planCreator_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
	        	      </#if>
	        	    </#if>
		        </td>
		        <#assign planCreatorId = ''/>
				<#if calibration.writer?exists>
				 <#assign planCreatorId = "${calibration.writer.id}" />
				<#elseif loginUser?exists>
				 <#assign planCreatorId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="writer.id" value="${planCreatorId}" />
				
				<#if planProcFlag?exists>
	              <#if (planProcFlag=='PLAN')>
        		     <@pp.datePicker label="'${action.getText('calibration.makeDate')}'" name="'calibration.makeDate'"
	     							  value="'${(calibration.makeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  required="${require}"/>
	 	  	  	  <#else>
	 	  	  	    <@ww.textfield label="'${action.getText('calibration.makeDate')}'" name="'calibration.makeDate'" value="'${(calibration.makeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true"/>
	 	  	  	  </#if>
	 	  	  	</#if>
			</tr>
		        <tr>
	                <#assign verifyPeopleName = ''/>
					<#if calibration.verifyPeople?exists>
					  <#assign verifyPeopleName = "${calibration.verifyPeople.name}" />
					</#if>
		        	<td align="right" valign="top">
	        	      <label class="label">${action.getText('calibration.verifyPeople')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="verifyPeople.name" 
	        			     class="underline"  value="${verifyPeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<#if planProcFlag?exists>
			          <#if (planProcFlag=='PLAN')>
	        	        <a name="verifyPeopleSeector" onClick="verifyPeople_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
	        	      </#if>
	        	    </#if>
	        		<#assign verifyPeopleId = ''/>	     
				    <#if calibration.verifyPeople?exists>
				      <#assign verifyPeopleId = "${calibration.verifyPeople.id}" />
				    </#if>
				    <input type="hidden" name="verifyPeople.id" value="${verifyPeopleId}" />
				    
				    <#assign approverName = ''/>
					<#if calibration.approver?exists>
					  <#assign approverName = "${calibration.approver.name}" />
					</#if>
		        	<td align="right" valign="top">
	        	      <label class="label">${action.getText('calibration.approver')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="approver.name" 
	        			     class="underline"  value="${approverName}"  maxlength="140" size="20" disabled="true"/>
	        	        <#if planProcFlag?exists>
				          <#if (planProcFlag=='PLAN')>
		        	        <a name="verifyPeopleSeector" onClick="approver_OpenDialog();">
			        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	    </a>
		        	      </#if>
		        	    </#if>
		            </td>
		            <#assign approverId = ''/>
				    <#if calibration.approver?exists>
				      <#assign approverId = "${calibration.approver.id}" />
				    </#if>
				    <input type="hidden" name="approver.id" value="${approverId}" />
		        </tr>
		     <#if planProcFlag?exists>
		     	<#if (planProcFlag=='PROC')>
		        <tr>
		        	<#assign reporterName = ''/>
					<#if calibration.reporter?exists>
					  <#assign reporterName = "${calibration.reporter.name}" />
					<#elseif loginUser?exists>
					  <#assign reporterName = "${loginUser.name}" />
					</#if>  
		        	<td align="right" valign="top">
	        	      <span class="required">*</span>
	        	      <label class="label">${action.getText('calibration.reporter')}:</label>
	        	    </td>
	        	    <td>
	        		  <input type="text" name="reporter.name" 
	        			     class="underline"  value="${reporterName}"  maxlength="140" size="20" disabled="true"/>
	        	        <a name="reporterSeector" onClick="reporter_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		            </td>
		            <#assign reporterId = ''/>
				    <#if calibration.reporter?exists>
				      <#assign reporterId = "${calibration.reporter.id}" />
				    <#elseif loginUser?exists>
					  <#assign reporterId = "${loginUser.id}" />
					</#if>  
				    <input type="hidden" name="reporter.id" value="${reporterId}" />
		        	<@pp.datePicker label="'${action.getText('calibration.reportDate')}'" name="'calibration.reportDate'"
	     							  value="'${(calibration.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  required="true"/>
		        </tr>
		     </#if>
		   </#if>
		   
        </@inputTable>
        <@buttonBar>
         <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/calibration/listCalibrations.html?planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if calibration.id?exists>
		      <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_calibrationDetailPdf('#{calibration.id}')"/>
	  	      <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_calibrationDetailXls('#{calibration.id}')"/>
	      </#if>
	    </@buttonBar>
	</@ww.form>
	    <script language="JavaScript" type="text/JavaScript">
	       window.onload = function () {
	            <#if calibration.id?exists>
	              	document.all.frame.src = '${req.contextPath}/runmaintenance/calibration/listCalibrationDetails.html?calibration.id=' + #{calibration.id} +'&planProcFlag=' + '${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		            document.getElementById("calibrationDetail").className = "selectedtab";
	            </#if>
	       		<#if calibration.department?exists>
	     			document.forms["calibration"].elements["department.id"].value=#{calibration.department.id?if_exists};
	     		<#elseif loginUser.department?exists>
				   document.getElementById("department.id").value = #{loginUser.department.id};
				 </#if>
	     		<#if (calibration.id)?exists>
	     		  <#if planProcFlag?exists>
			        <#if (planProcFlag=='PROC')>
			          a = new Date();
					  var time=a.format("yyyy-MM-dd");
					  document.forms["calibration"].elements["calibration.reportDate"].value=time;
			        </#if>
			      </#if>
		       	<#else>
		       	  <#if planProcFlag?exists>
			        <#if (planProcFlag=='PLAN')>
			       	  a = new Date();
					  var time=a.format("yyyy-MM-dd");
					  document.forms["calibration"].elements["calibration.makeDate"].value=time;
					</#if>
				  </#if> 
				</#if>
	       }
	       
	       function planCreator_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Auditor);
	       }
	       function desigerSelectorHandler_Auditor(result) {
		    	document.forms["calibration"].elements["writer.id"].value = result[0];
		    	document.forms["calibration"].elements["writer.name"].value = result[1];
		   }
		  
		  function reporter_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_reportor);
	       }
	       function desigerSelectorHandler_reportor(result) {
		    	document.forms["calibration"].elements["reporter.id"].value = result[0];
		    	document.forms["calibration"].elements["reporter.name"].value = result[1];
		  }
		  
		  function approver_OpenDialog() {
	       		var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_approver);
	       }
	       function desigerSelectorHandler_approver(result) {
		    	document.forms["calibration"].elements["approver.id"].value = result[0];
		    	document.forms["calibration"].elements["approver.name"].value = result[1];
		  }
	  	  
		  /*
		   * 审核人选择
		  */
		  function verifyPeople_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, verifyPeopleSelectorHandler);
		  }
		  function verifyPeopleSelectorHandler(result) {
		    if (null != result) {
		      document.forms["calibration"].elements["verifyPeople.id"].value = result[0];
		      document.forms["calibration"].elements["verifyPeople.name"].value = result[1];
		    }
		  }
		  function open_calibrationDetailXls(id){
			   var calibrationid=id;
			   <#if (planProcFlag=='PLAN')>
			        var url='${req.contextPath}/reports/calibration/calibrationDetailReportList.xls?calibrationid='+calibrationid+'&flag=PLAN';    
			   <#else>
			   		var url='${req.contextPath}/reports/calibration/calibrationDetailProcReportList.xls?calibrationid='+calibrationid+'&flag=PROC';   
			   </#if>
		        url = encodeURI(url);
	         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	       }
		  function open_calibrationDetailPdf(id){
		       var calibrationid=id;
		       <#if (planProcFlag=='PLAN')>
		       		var url='${req.contextPath}/reports/calibration/calibrationDetailReportList.pdf?calibrationid='+calibrationid+'&flag=PLAN';  
		       <#else>
		       		var url='${req.contextPath}/reports/calibration/calibrationDetailProcReportList.pdf?calibrationid='+calibrationid+'&flag=PROC';  
		       </#if>
	       		url = encodeURI(url);
	         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	       }
		  function validate(){
		    if ('PROC' == document.forms[0].elements["planProcFlag"].value) {
		    	if(isNotEmpty(calibration,"calibration.reportDate")) {
					if(!isDate("calibration.reportDate")){
						alert('${action.getText('calibration.reportDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}else {
						if(isDateLessThenCurrent(document.forms[0].elements["calibration.reportDate"].value)){
							alert("${action.getText('afresh.calibration.reportDate')}");
							return false;
						}
					}
				} else {
					alert("${action.getText('calibration.reportDate.requiredstring')}");
					return false;
				}
		    } else {
		  	    if(isNotEmpty(calibration,"calibration.planName")) {
					if (!isValidLength(document.forms[0],"calibration.planName",0,50)){
						alert("${action.getText('calibration.planName.maxlength')}");
						return false;
				    }
				} else {
					alert("${action.getText('calibration.planName.requiredstring')}");
					return false;
				}
				if(isNotEmpty(calibration,"calibration.month")) {
					if(!isDateOfNotDay("calibration.month")){
						alert('${action.getText('calibration.month')}'+'${action.getText('dateFormate.error1')}');
						return false; 
					}
				} else {
				  alert('${action.getText('please.input.calibration.month')}');
				  return false;
				}
				if(isNotEmpty(calibration,"calibration.makeDate")) {
					if(!isDate("calibration.makeDate")){
						alert('${action.getText('calibration.makeDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}else {
						if(isDateLessThenCurrent(document.forms[0].elements["calibration.makeDate"].value)){
							alert("${action.getText('afresh.calibration.makeDate')}");
							return false;
						}
					}
				} else {
					alert("${action.getText('calibration.makeDate.requiredstring')}");
					return false;
				}
		  	  
		  	  if (document.forms["calibration"].elements["department.id"].value == -1) {
		        alert("${action.getText('select.department.name')}");
		        return false;
		      }
		    }
		    return true;
		  
		  }
	 </script>
	 <#if calibration.id?exists>
	 <#assign iframeTitle =''/>
	 <#if planProcFlag?exists>
	   <#if planProcFlag=='PLAN'>
	     <#assign iframeTitle ="${action.getText('calibrationPlanDetail')}"/>
	   <#else>
	     <#assign iframeTitle ="${action.getText('calibrationProcDetail')}"/>
	   </#if>
	 </#if>
	  <ul id="beautytab">
	    <li><a id="calibrationDetail" onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/calibration/listCalibrationDetails.html?calibration.id=#{calibration.id}&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${iframeTitle}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>