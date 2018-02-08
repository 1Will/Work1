<#include "../../includes/eam2008.ftl" />

<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign maintenanceTitle = "${action.getText('maintenancePlanEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign maintenanceTitle = "${action.getText('maintenanceProcEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${maintenanceTitle}">
    <@ww.form namespace="'/runmaintenance/maintenance'" name="'maintenance'" action="'saveMaintenance'" method="'post'" validate="true">
        <@ww.token name="saveMaintenanceToken"/>
         <@ww.hidden name="'maintenance.id'" value="${maintenance.id?if_exists}"/>
         <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('maintenance.planNo')}'" name="'maintenance.planNo'" value="'${maintenance.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('maintenance.planName')}'" name="'maintenance.planName'" value="'${maintenance.planName?if_exists}'" cssClass="'underline'" required="false"readonly="true" disabled="true"/>
        	</tr>
        	<tr>
        		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name" 
		                list="departments" emptyOption="true" required="false" disabled="true">
		    	</@ww.select>
				<#--<@pp.datePicker label="'${action.getText('maintenance.scheduleDate')}'" name="'maintenance.scheduleDate'"
	     							  value="'${(maintenance.scheduleDate?string('yyyy-MM'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  disabled="true" required="false"/>-->
	    		<@ww.textfield label="'${action.getText('maintenance.scheduleDate')}'" name="'maintenance.month'" 
	    				value="'${(maintenance.month)?if_exists}'" cssClass="'underline'" size="15"
	    				disabled="true"/>
            </tr>
        	<tr>
	         	<#assign planCreatorName = ''/>
					<#if maintenance.writer?exists>
					  <#assign planCreatorName = "${maintenance.writer.name}" />
					<#elseif loginUser?exists>
					  <#assign planCreatorName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top">
	        	<#if planProcFlag?exists>
			      <#if (planProcFlag=='PLAN')>
	        	    <span class="required">*</span>
	        	  </#if>
	        	</#if>
	        	<label class="label">${action.getText('maintenance.writer')}:</label></td>
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
				<#if maintenance.writer?exists>
				 <#assign planCreatorId = "${maintenance.writer.id}" />
				<#elseif loginUser?exists>
				 <#assign planCreatorId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="writer.id" value="${planCreatorId}" />
				
    		    <#--<@pp.datePicker label="'${action.getText('maintenance.makeDate')}'" name="'maintenance.makeDate'"
     							  value="'${(maintenance.makeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     							  required="${require}"/>-->
 	  	  	    <@ww.textfield label="'${action.getText('maintenance.makeDate')}'" name="'maintenance.makeDate'" value="'${(maintenance.makeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" disabled="true"/>
			</tr>
		     <#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
		     <tr>
	        	<#assign reporterName = ''/>
				<#if maintenance.reporter?exists>
				  <#assign reporterName = "${maintenance.reporter.name}" />
				<#elseif loginUser?exists>
				 <#assign reporterName = "${loginUser.name}" />
				</#if>
	        	<td align="right" valign="top">
        	      <span class="required">*</span>
        	      <label class="label">${action.getText('maintenance.reporter')}:</label>
        	    </td>
        	    <td>
        		  <input type="text" name="reporter.name" 
        			     class="underline"  value="${reporterName}"  maxlength="140" size="20" disabled="true"/>
        	        <a name="reporterSeector" onClick="reporter_OpenDialog();">
	        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        	    </a>
	            </td>
			    <#if maintenance.reporter?exists>
			      <#assign reporterId = "${maintenance.reporter.id}" />
			    <#elseif loginUser?exists>
				 <#assign reporterId = "${loginUser.id}" />
			    </#if>
			    <input type="hidden" name="reporter.id" value="${reporterId}" />
			    
	        	<@pp.datePicker label="'${action.getText('maintenance.reportDate')}'" name="'maintenance.reportDate'"
     							  value="'${(maintenance.reportDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     							  required="true"/>
		     </tr>
		     </#if>
		     </#if>
		 </@inputTable>
        <@buttonBar>
          <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/maintenance/listMaintenances.html?planProcFlag=${planProcFlag?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if maintenance.id?exists>
	      	  <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_maintenancePlanPdf('#{maintenance.id}')"/>
	      	  <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_maintenancePlanXls('#{maintenance.id}')"/>
	      </#if>
	    </@buttonBar>
	</@ww.form>
		 <script language="JavaScript" type="text/JavaScript">
	       window.onload = function () {
	       		<#if maintenance.id?exists>
	              	document.all.frame.src = '${req.contextPath}/runmaintenance/maintenance/listMaintenanceDetails.html?maintenance.id=' + #{maintenance.id} +'&planProcFlag=' + '${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
		            getObjByNameRe("maintenanceDetail").className = "selectedtab";
	            </#if>
	            <#if maintenance.department?exists>
	     			document.forms["maintenance"].elements["department.id"].value=#{maintenance.department.id?if_exists};
	     		</#if>
	       }
	       <#if (maintenance.id)?exists>
	       	<#else>
		       	a = new Date();
				var time=a.format("yyyy-MM-dd");
				document.forms["maintenance"].elements["maintenance.makeDate"].value=time;
			</#if>
			<#if planProcFlag?exists>
		     <#if (planProcFlag=='PROC')>
				<#if (maintenance.reportDate)?exists>
		       	<#else>
			       	a = new Date();
					var time=a.format("yyyy-MM-dd");
					document.forms["maintenance"].elements["maintenance.reportDate"].value=time;
				</#if>			
	       	</#if>
		   </#if>
	       function planCreator_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Auditor);
	       }
	       function desigerSelectorHandler_Auditor(result) {
		    	document.forms["maintenance"].elements["writer.id"].value = result[0];
		    	document.forms["maintenance"].elements["writer.name"].value = result[1];
		   }
		    function open_maintenancePlanPdf(id){
                var maintenanceid=id;
                <#if (planProcFlag=='PLAN')>
                   var url='${req.contextPath}/reports/device/maintenancePlanReport.pdf?maintenanceid='+maintenanceid+'&flag=PLAN';  
                <#else>
                   var url='${req.contextPath}/reports/device/maintenanceProcReport.pdf?maintenanceid='+maintenanceid+'&flag=PROC';
                </#if>
         	    window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
           }
           function open_maintenancePlanXls(id){
                var maintenanceid=id;
                <#if (planProcFlag=='PLAN')>
                   var url='${req.contextPath}/reports/device/maintenancePlanReport.xls?maintenanceid='+maintenanceid+'&flag=PLAN';  
                 <#else>
                   var url='${req.contextPath}/reports/device/maintenanceProcReport.xls?maintenanceid='+maintenanceid+'&flag=PROC';
                 </#if>
         	     window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
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
		      document.forms["maintenance"].elements["verifyPeople.id"].value = result[0];
		      document.forms["maintenance"].elements["verifyPeople.name"].value = result[1];
		    }
		  }
		   
		   function reporter_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_reportor);
	       }
	       function desigerSelectorHandler_reportor(result) {
		    	document.forms["maintenance"].elements["reporter.id"].value = result[0];
		    	document.forms["maintenance"].elements["reporter.name"].value = result[1];
		  }
		  
		  function validate() {
		  		<#if planProcFlag?exists>
	        		<#if planProcFlag == 'PROC'>
	        			if(isNotEmpty(maintenance,"maintenance.reportDate")) {
				  			if(!isDate("maintenance.reportDate")){
								alert('${action.getText('maintenance.reportDate')}'+'${action.getText('dateFormate.error')}');
								return false; 
							}
						}else {
							alert('${action.getText('select.maintenance.reportDate')}');
							return false;
						}
	        		</#if>
		  		</#if>
		  		return true;
		  }
	     </script> 
	     <#if maintenance.id?exists>
		 <#assign iframeTitle =''/>
		 <#if planProcFlag?exists>
		   <#if planProcFlag=='PLAN'>
		     <#assign iframeTitle ="${action.getText('maintenancePlanDetail')}"/>
		   <#else>
		     <#assign iframeTitle ="${action.getText('maintenanceProcDetail')}"/>
		   </#if>
		 </#if>
		  <ul id="beautytab">
		    <li><a id="maintenanceDetail" onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/maintenance/listMaintenanceDetails.html?maintenance.id=#{maintenance.id}&planProcFlag=${planProcFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${iframeTitle}</a></li>
		  </ul>
		  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
		</#if>
</@htmlPage>