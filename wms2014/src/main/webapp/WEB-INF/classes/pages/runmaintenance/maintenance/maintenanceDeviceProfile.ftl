<#include "../../includes/eam2008.ftl" />

<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign maintenanceTitle = "${action.getText('deviceMaintenancePlanDetailEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign maintenanceTitle = "${action.getText('deviceMaintenanceProcDetailEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${maintenanceTitle}">
  <base target="_self">
    <@ww.form namespace="'/runmaintenance/maintenance'" name="'deviceMaintenanceDetailProfile'" action="'saveMaintenanceDetail'" method="'post'" validate="true">
        <@ww.token name="saveMaintenanceDetailToken"/>
         <@ww.hidden name="'maintenanceDetail.id'" value="#{detail.id?if_exists}"/>
         <@ww.hidden name="'device.id'" value="${detail.device.id?if_exists}"/>
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <#--<@ww.hidden name="'resultTypeID'" value="${detail.resultType.id?if_exists}"/>-->
        <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
        <@inputTable>
        	<#assign deviceNo = ''/>
			<#if detail.device?exists>
			 	<#assign deviceNo = "${detail.device.deviceNo?if_exists}" />
				</#if>
			<#assign deviceName = ''/>
			<#if detail.device?exists>
			 	<#assign deviceName = "${detail.device.name?if_exists}" />
			</#if>
        	<tr>
        		<@ww.textfield label="'${action.getText('device.deviceNo')}'" name="'device.deviceNo'" value="'${deviceNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('device.name')}'" name="'device.name'" value="'${deviceName?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
        	</tr>
        	<tr>
		 		<#assign deviceModel = ''/>
				<#if detail.device?exists>
				 	<#assign deviceModel = "${detail.device.model?if_exists}" />
					</#if>
				<#assign deviceSpecification = ''/>
				<#if detail.device?exists>
				 	<#assign deviceSpecification = "${detail.device.specification?if_exists}" />
					</#if>
		 		<@ww.textfield label="'${action.getText('device.model')}'" name="'model'" value="'${deviceModel?if_exists}'" cssClass="'underline'" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('device.specification')}'" name="'specification'" value="'${deviceSpecification?if_exists}'" cssClass="'underline'" disabled="true"/>   
	    	</tr>
        	<tr>
				<@pp.datePicker label="'${action.getText('maintenanceDetail.planDate')}'" name="'detail.planDate'"
	     							  value="'${(detail.planDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  disabled="${disabled}" required="${require}"/>
	     		
	     		<#assign deviceManagerName = ''/>
				  <#if detail.dutyPeople?exists>
				  	<#assign deviceManagerName = "${detail.dutyPeople.name}" />
				  </#if>
				  <td align="right" valign="top"><label class="label">${action.getText('maintenanceDetail.manager')}:</label></td>
	        	  <td>
	        		  <input type="text" name="dutyPeople.name" 
	        			  class="underline"  value="${deviceManagerName}"  maxlength="150" size="10" disabled="${disabled}"/>
	        		  <label id=""></label>
		    		    <a onClick="deviceManager_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		          </td>
		          <#assign deviceManagerId = ''/>
				  <#if detail.dutyPeople?exists>
				    <#assign deviceManagerId = "${detail.dutyPeople.id}" />
				  </#if>
				  <input type="hidden" name="dutyPeople.id" value="${deviceManagerId}" />
	     		
            </tr>
            <#if planProcFlag?exists>
	        		<#if planProcFlag == 'PROC'>
		            <tr>
						<@pp.datePicker label="'${action.getText('maintenanceDetail.actualDate')}'" name="'detail.actualDate'"
			     							  value="'${(detail.actualDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
			     							  disabled="false" required="true"/>
						
			        	<@ww.select label="'${action.getText('maintenanceDetail.result')}'" 
		 	  	               name="'result'" value="'${detail.result?if_exists}'"
			    			   listKey="value" listValue="label"
			                   list="results" emptyOption="false" 
			                   disabled="false">
			      		</@ww.select>
		            </tr>
            	</#if>
		  	</#if>
		  	<tr>
		  		<@ww.select label="'${action.getText('maintenanceDetail.resultType')}'"
	                       name="'resultType.id'" 
	                   	   listKey="id" listValue="value"
	                       list="resultType" emptyOption="true" 
	                       disabled="false" required="true">
	           </@ww.select>
		  	</tr>
        	<tr>
        		<@ww.textarea label="'${action.getText('maintenanceDetail.comment')}'" 
					         name="'detail.comment'" 
					         value="'${detail.comment?if_exists}'" rows="'5'" cols="'60'" 
							 />
			</tr>
		 </@inputTable>
        <@buttonBar>
        <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate()'"/>
	      </#if>
	      <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	    </@buttonBar>
	 </@ww.form> 
	    <script language="JavaScript" type="text/JavaScript">
	       window.onload = function () {
	       		<#if detail.id?exists>
	       		<#if detail.resultType?exists>
	       			document.forms["deviceMaintenanceDetailProfile"].elements["resultType.id"].value=#{detail.resultType.id?if_exists};
	       		</#if>
	       		</#if>
	       		<#if detail.id?exists>
	              	document.all.frame.src = '${req.contextPath}/runmaintenance/maintenance/listDeviceMaintenanceDetails.html?maintenanceDetail.id=#{detail.id}&planProcFlag=${planProcFlag?if_exists}&device.id=${detail.device.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
		            document.getElementById("deviceMaintenanceDetail").className = "selectedtab";
	            </#if>
	       }
	        /*
		   	 * 负责人选择
		  	 */
		  	function deviceManager_OpenDialog() {
		    	var url = "${req.contextPath}/popup/userSelector.html";
		    	popupModalDialog(url, 800, 600, deviceManagerSelectorHandler);
		  	}
		  	function deviceManagerSelectorHandler(result) {
		    	if(null != result) {
		      	document.forms["deviceMaintenanceDetailProfile"].elements["dutyPeople.id"].value = result[0];
		      	document.forms["deviceMaintenanceDetailProfile"].elements["dutyPeople.name"].value = result[1];
		    	}
		  	}
		  	function validate() {
		  		<#if planProcFlag?exists>
	        		<#if planProcFlag == 'PLAN'>
				  		if(isNotEmpty(deviceMaintenanceDetailProfile,"detail.planDate")) {
				  			if(!isDate("detail.planDate")){
								alert('${action.getText('maintenanceDetail.planDate')}'+'${action.getText('dateFormate.error')}');
								return false; 
							}
						}else {
							alert('${action.getText('select.detail.planDate')}');
							return false;
						}
					</#if>
		  		</#if>
				
				if ("" == document.forms["deviceMaintenanceDetailProfile"].elements["resultType.id"].value) {    //验证使用部门是否为空
				    alert("${action.getText('resultType.id.requiredstring')}");
				  	return false;
				  }
				<#if planProcFlag?exists>
	        		<#if planProcFlag == 'PROC'>
	        			if(isNotEmpty(deviceMaintenanceDetailProfile,"detail.actualDate")) {
				  			if(!isDate("detail.actualDate")){
								alert('${action.getText('maintenanceDetail.actualDate')}'+'${action.getText('dateFormate.error')}');
								return false; 
							}
						}else {
							alert('${action.getText('select.detail.actualDate')}');
							return false;
						}
	        		</#if>
		  		</#if>
		  	   /*
			    * 验证保养要求字符长度
			   */
		       if ('' != document.forms[0].elements["detail.comment"].value) {
		       		if (!isValidLength(document.forms[0],"detail.comment",0,250)){
				         alert("${action.getText('maintenanceDetail.comment.maxlength')}");
				         return false;
				    }
		       }
				return true;
		  	}
	     </script> 
	     <#if detail.id?exists>
		  <ul id="beautytab">
		    <li><a id="deviceMaintenanceDetail" onclick="activeTab(this);" href="${req.contextPath}/runmaintenance/maintenance/listDeviceMaintenanceDetails.html?maintenanceDetail.id=#{detail.id}&planProcFlag=${planProcFlag?if_exists}&device.id=${detail.device.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('deviceMaintenanceDetail')}</a></li>
		  </ul>
		  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
		</#if>
	
</@htmlPage>