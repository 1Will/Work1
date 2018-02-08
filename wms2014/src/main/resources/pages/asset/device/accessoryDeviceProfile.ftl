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

<@htmlPage title="${action.getText('accessoryDevice.title')}">
 <base target="_self">
	<@ww.form name="'accessoryDevice'" action="'saveAccessoryDevice'" method="'post'" validate="true">
		<@ww.token name="saveAccessoryDeviceToken"/>
		<@ww.hidden name="'accessoryDevice.id'" value="'${req.getParameter('accessoryDevice.id')?if_exists}'"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@inputTable>
	 	    <tr>

			 	<@ww.textfield label="'${action.getText('accessoryDevice.name')}'" name="'accessoryDevice.name'" value="'${accessoryDevice.name?if_exists}'" cssClass="'underline'" required="true"/>
			</tr>
	 	</@inputTable>
	 	<@buttonBar>
	        	<@vsubmit value="'${action.getText('save')}'" onclick="'return storeValidate()'" />
	        	<@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	     </@buttonBar>
	 	<script language="javascript">
	    	function storeValidate() {
	    		if(isNotEmpty(accessoryDevice,"accessoryDevice.name")) {
					if (!isValidLength(document.forms[0],"accessoryDevice.name",0,50)){
						alert("${action.getText('accessoryDevice.name.maxlength')}");
						return false;
				    }
				} else {
					alert("${action.getText('accessoryDevice.name.requiredstring')}");
					return false;
				}
				return true;
	    	}
	    </script>
	</@ww.form>
</@htmlPage>