<#include "../../includes/eam2008.ftl" />

<#if planProcFlag?exists>
	<#if (planProcFlag=='PLAN')>
		<#assign calibrationTitle = "${action.getText('toolingCalibrationPlanDetailEdit.title')}"/>
		<#assign require = 'true'/>
		<#assign disabled = 'false'/>
	<#else>
		<#assign calibrationTitle = "${action.getText('toolingCalibrationProcDetailEdit.title')}"/>
		<#assign require = 'false'/>
		<#assign disabled = 'true'/>
	</#if>
</#if>
<@htmlPage title="${calibrationTitle}">
  <base target="_self">
	<@ww.form namespace="'/runmaintenance/calibration'" name="'toolingCalibrationDetailProfile'" action="'saveCalibrationDetail'" method="'post'" validate="true">
		<@ww.token name="saveCalibrationDetailToken"/>
        <@ww.hidden name="'calibrationDetail.id'" value="#{detail.id?if_exists}"/>
        <@ww.hidden name="'tooling.id'" value="#{detail.tooling.id?if_exists}"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
        <@inputTable>
        	<#assign toolingNo = ''/>
			<#if detail.tooling?exists>
			 	<#assign toolingNo = "${detail.tooling.deviceNo?if_exists}" />
				</#if>
			<#assign toolingName = ''/>
			<#if detail.tooling?exists>
			 	<#assign toolingName = "${detail.tooling.name?if_exists}" />
			</#if>
			<tr>
        		<@ww.textfield label="'${action.getText('tooling.toolingNo')}'" name="'tooling.toolingNo'" value="'${toolingNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('tooling.name')}'" name="'tooling.name'" value="'${toolingName?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
        	</tr>
        	<tr>
		 		<#assign toolingGraphNo = ''/>
				<#if detail.tooling?exists>
				 	<#assign toolingGraphNo = "${detail.tooling.graphNo?if_exists}" />
				</#if>
				<#assign toolingDemarcateCycle = ''/>
				<#if detail.tooling?exists>
				 	<#assign toolingDemarcateCycle = "${detail.tooling.demarcateCycle?if_exists}" />
				</#if>
		 		<@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'graphNo'" value="'${toolingGraphNo?if_exists}'" cssClass="'underline'" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('tooling.demarcateCycle')}'" name="'demarcateCycle'" value="'${toolingDemarcateCycle?if_exists}'" cssClass="'underline'" disabled="true"/>   
	    	</tr>
	    	<tr>
	     		<#assign toolingManagerName = ''/>
				  <#if detail.dutyPeople?exists>
				  	<#assign toolingManagerName = "${detail.dutyPeople.name}" />
				  </#if>
				  <td align="right" valign="top"><label class="label">${action.getText('calibrationDetail.manager')}:</label></td>
	        	  <td>
	        		  <input type="text" name="dutyPeople.name" 
	        			  class="underline"  value="${toolingManagerName}"  maxlength="150" size="10" disabled="${disabled}"/>
	        		  <label id=""></label>
		    		    <a onClick="toolingManager_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		          </td>
		          <#assign toolingManagerId = ''/>
				  <#if detail.dutyPeople?exists>
				    <#assign toolingManagerId = "${detail.dutyPeople.id}" />
				  </#if>
				  <input type="hidden" name="dutyPeople.id" value="${toolingManagerId}" />
	     		
				<@pp.datePicker label="'${action.getText('calibrationDetail.planDate')}'" name="'detail.planDate'"
	     							  value="'${(detail.planDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     							  disabled="${disabled}" required="${require}"/>
	     	</tr>
	    	<tr>	
	     		<#if planProcFlag?exists>
	        		<#if planProcFlag == 'PROC'>
	        			<@pp.datePicker label="'${action.getText('calibrationDetail.actualDate')}'" name="'detail.actualDate'"
			     							  value="'${(detail.actualDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
			     							  disabled="false" required="true"/>
	        		</#if>
		  		</#if>
            </tr>
            <#if planProcFlag?exists>
	        		<#if planProcFlag == 'PROC'>
		            <tr>
		            	<@ww.select label="'${action.getText('calibrationDetail.calibrationResult')}'"
			                       name="'calibrationResult'" value="'${detail.calibrationResult?if_exists}'"
			                   	   listKey="value" listValue="label"
			                       list="calibrationResults" emptyOption="false" 
			                       disabled="false">
			           </@ww.select>
		            	<@ww.select label="'${action.getText('calibrationDetail.result')}'"
			                       name="'result'" value="'${detail.result?if_exists}'"
			                   	   listKey="value" listValue="label"
			                       list="results" emptyOption="false" 
			                       disabled="false">
			           </@ww.select>
			           
		            </tr>
	    		</#if>
		  	</#if>
		  	<tr>
        		<@ww.textarea label="'${action.getText('calibrationDetail.comment')}'" 
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
			   var url = '${req.contextPath}/runmaintenance/calibration/listCalibrationDocs.html?calibrationDetail.id=#{detail.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
		          document.all.frame.src = url;
			      document.getElementById("deviceDoc").className = "selectedtab";
		    </#if>
    	}
    	/*
	   	 * 负责人选择
	  	 */
	  	function toolingManager_OpenDialog() {
	    	var url = "${req.contextPath}/popup/userSelector.html";
	    	popupModalDialog(url, 800, 600, toolingManagerSelectorHandler);
	  	}
	  	function toolingManagerSelectorHandler(result) {
	    	if(null != result) {
	      	document.forms["toolingCalibrationDetailProfile"].elements["dutyPeople.id"].value = result[0];
	      	document.forms["toolingCalibrationDetailProfile"].elements["dutyPeople.name"].value = result[1];
	    	}
	  	}
    	function validate() {
	  		if(isNotEmpty(toolingCalibrationDetailProfile,"detail.planDate")) {
	  			if(!isDate("detail.planDate")){
					alert('${action.getText('calibrationDetail.planDate')}'+'${action.getText('dateFormate.error')}');
					return false; 
				}
			}else {
				alert('${action.getText('select.detail.planDate')}');
				return false;
			}
			<#if planProcFlag?exists>
        		<#if planProcFlag == 'PROC'>
        			if(isNotEmpty(toolingCalibrationDetailProfile,"detail.actualDate")) {
			  			if(!isDate("detail.actualDate")){
							alert('${action.getText('calibrationDetail.actualDate')}'+'${action.getText('dateFormate.error')}');
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
			         alert("${action.getText('calibrationDetail.comment.maxlength')}");
			         return false;
			    }
	       }
			return true;
	  	}
    </script>
    <#if detail.id?exists>
		<ul id="beautytab">
			<li>
				<a id="deviceDoc"   onclick="activeTab(this);" 
				href="${req.contextPath}/runmaintenance/calibration/listCalibrationDocs.html?readOnly=${req.getParameter('readOnly')?if_exists}&calibrationDetail.id=#{detail.id}" target="frame" >${action.getText('technical')}
				</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>
