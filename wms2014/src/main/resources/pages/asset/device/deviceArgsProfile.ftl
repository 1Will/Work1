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

<@htmlPage title="${action.getText('attachTool.title1')}">
 <base target="_self">
	<@ww.form name="'newDeviceArgs'" action="'saveDeviceArgs'" method="'post'" validate="true">
		<@ww.token name="saveDeviceArgsToken"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@ww.hidden name="'deviceArgs.id'" value="'${req.getParameter('deviceArgs.id')?if_exists}'"/>
		<@inputTable>
            <tr>
				<@ww.textfield readonly="false" label="'${action.getText('deviceArgs.name')}'" name="'deviceArgs.name'" value="'${deviceArgs.name?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('deviceArgs.value')}'" name="'deviceArgs.value'" value="'${deviceArgs.value?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
				<@oneLine>
					<@ww.textarea label="'${action.getText('comment')}'" 
					         name="'deviceArgs.comment'" 
					         value="'${deviceArgs.comment?if_exists}'" rows="'4'" cols="'80'" 
							 required="false"/>
				</@oneLine>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<script language="javascript">
			function customize_validate() {
				if ('' == document.forms["newDeviceArgs"].elements["deviceArgs.name"].value) {       
				    alert("${action.getText('deviceArgs.name.requiredstring')}");
				    return false;
				  } else {
				    if (!isValidLengthValue(document.forms["newDeviceArgs"].elements["deviceArgs.name"].value,0,150)) {
				      alert("${action.getText('deviceArgs.name.maxLength')}");
				      return false;
				    }
				  }
				  
				if ('' == document.forms["newDeviceArgs"].elements["deviceArgs.value"].value) {       
				    alert("${action.getText('deviceArgs.value.requiredstring')}");
				    return false;
				  } else {
				    if (!isValidLengthValue(document.forms["newDeviceArgs"].elements["deviceArgs.value"].value,0,150)) {
				      alert("${action.getText('deviceArgs.value.maxLength')}");
				      return false;
				    }
				  }
				
				if ('' != document.forms["newDeviceArgs"].elements["deviceArgs.comment"].value) { 
					    if (!isValidLengthValue(document.forms["newDeviceArgs"].elements["deviceArgs.comment"].value,0,250)) {
					      alert("${action.getText('deviceArgs.comment.maxLength')}");
					  	  return false;
					    }
				  }
				 return true;
			 }
		</script>
	</@ww.form>
</@htmlPage>