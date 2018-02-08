<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('feedbackEdit.title')}">
	<@ww.form namespace="'/runmaintenance/feedback'" name="'feedback'" action="'saveFeedback'" method="'post'" validate="true">
		<@ww.token name="saveFeedbackToken"/>
		<#if feedback.id?exists>
          <@ww.hidden name="'feedback.id'" value="#{feedback.id}"/>
        </#if>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('feedbackNo')}'" name="'feedback.feedbackNo'" value="'${feedback.feedbackNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'${action.getText('feedbackName')}'" name="'feedback.feedbackName'" value="'${feedback.feedbackName?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<@eam2008_DeviceSelector/>
		 	<tr>
		 		<#assign deviceModel = ''/>
				<#if feedback.device?exists>
				 	<#assign deviceModel = "${feedback.device.model?if_exists}" />
					</#if>
				<#assign deviceSpecification = ''/>
				<#if feedback.device?exists>
				 	<#assign deviceSpecification = "${feedback.device.specification?if_exists}" />
					</#if>
		 		<@ww.textfield label="'${action.getText('device.model')}'" name="'model'" value="'${deviceModel?if_exists}'" cssClass="'underline'" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('device.specification')}'" name="'specification'" value="'${deviceSpecification?if_exists}'" cssClass="'underline'" disabled="true"/>   
	    	</tr>
	    	<tr>
	    		<#assign deviceMadeTime = ''/>
	            	<#if feedback.device?exists>
						<#if feedback.device.deviceExtInfo?exists>
						 	<#assign deviceMadeTime = "${feedback.device.deviceExtInfo.madeDate?if_exists}" />
						</#if>
					</#if>
	    		<@ww.textfield label="'${action.getText('device.madeTime')}'" name="'device.madeTime'" value="'${deviceMadeTime?if_exists}'" cssClass="'underline'" disabled="true"/>   
		    	<#assign deviceFactory = ''/>
	            	<#if feedback.device?exists>
						<#assign deviceFactory = "${feedback.device.factory?if_exists}" />
					</#if>
				<@ww.textfield label="'${action.getText('device.factory')}'" name="'factory'" value="'${deviceFactory?if_exists}'" cssClass="'underline'"  disabled="true"/>
		   	</tr>
		   	<tr>
		   		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		                list="departments" emptyOption="true" disabled="false" required="true">
		    	</@ww.select>
		   		<#assign managerName = ''/>
					<#if feedback.manager?exists>
					 <#assign managerName = "${feedback.manager.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('feedback.manager')}:</label></td>
	        	<td>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="manager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign managerId = ''/>
				<#if feedback.manager?exists>
				 <#assign managerId = "${feedback.manager.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" />
		   	</tr>
		   	<tr>
		   		<@pp.datePicker label="'${action.getText('feedback.feedbaceDatetime')}'" name="'feedback.feedbaceDatetime'"
	     							value="'${(feedback.feedbaceDatetime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>	
		   	</tr>
		   	<tr>
            	<@ww.textarea  label="'${action.getText('feedback.content')}'" 
	        	         name="'feedback.content'" 
	        	         value="'${feedback.content?if_exists}'"  
	        	         rows="5" cols="60" cssClass="'underline'" required="true"/>
            </tr>
	    </@inputTable>
	    <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/feedback/listFeedbacks.html"/>
	    </@buttonBar>
	    <script language="JavaScript" type="text/JavaScript">
		    window.onload = function () {
			    <#if (device.id)?exists>
		       	<#else>
			       	a = new Date();
					var time=a.format("yyyy-MM-dd");
					document.forms["feedback"].elements["feedback.feedbaceDatetime"].value=time;
				</#if>
				<#if feedback.department?exists>
	     			document.forms["feedback"].elements["department.id"].value=#{feedback.department.id?if_exists};
	     		</#if>
		    }
		  function manager_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, desigerSelectorHandler);
		  }
		  function desigerSelectorHandler(result) {
		    document.forms["feedback"].elements["manager.id"].value = result[0];
		    document.forms["feedback"].elements["manager.name"].value = result[1];
		  }
		  function validate(){
		  	if(!eam2008_device_validate("${action.getText('select.device.no')}")){
					return false;
			}
			if (document.forms["feedback"].elements["department.id"].value == '') {
		      alert("${action.getText('select.department.name')}");
		      return false;
		    }
			if (document.forms["feedback"].elements["manager.name"].value == '') {
		      alert("${action.getText('select.manager.name')}");
		      return false;
		    }
		    if(!validateTime_feedbaceDatetime()){
		  		return false;
		  	}
		  	if(!feedback_content()){
		  		return false;
		  	}
		  }
		  function validateTime_feedbaceDatetime(){
			if(document.forms["feedback"].elements["feedback.feedbaceDatetime"].value ==""){
				alert("${action.getText('select.feedback.feedbaceDatetime')}");
				return false;
			}
			var date=getObjByNameRe("feedback.feedbaceDatetime").value;
			if(!isDate("feedback.feedbaceDatetime")){
				alert("${action.getText('feedback.feedbaceDatetime')}" + "," +"${action.getText('dateFormate.error')}");
				return false;
			}
			if(isDateLessThenCurrent(date)){
				alert("${action.getText('afresh.feedback.feedbaceDatetime')}");
				return false;
			}
			return true;
		  }
		  function feedback_content(){
			var name=document.forms["feedback"].elements["feedback.content"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "feedback.content", null, 250)){
				  		alert("${action.getText('feedback.content.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
	    </script>
	</@ww.form>
</@htmlPage>