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

<@htmlPage title="${action.getText('PreRepairRule.profile')}">
 <base target="_self">
	<@ww.form name="'newPreRepairRule'" action="'savePreRepairRule'" method="'post'" validate="true">
		<@ww.token name="savePreRepairRuleToken"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@ww.hidden name="'preRepairRule.id'" value="'${req.getParameter('preRepairRule.id')?if_exists}'"/>
		<@inputTable>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('PreRepairRule.position')}'" name="'preRepairRule.position'" value="'${preRepairRule.position?if_exists}'"  cssClass="'underline'" required="true"/>
                <@ww.textfield readonly="false" label="'${action.getText('PreRepairRule.maxRunHour')}'" name="'preRepairRule.maxRunHour'" value="'${preRepairRule.maxRunHour?if_exists}'" cssClass="'underline'" required="false"/>
			</tr>
			<tr>
			    <@pp.datePicker label="'${action.getText('PreRepairRule.lastRepairDate')}'" name="'preRepairRule.lastRepairDate'"
	     					     value="'${(preRepairRule.lastRepairDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     					     required="false"/>
			    <@ww.textarea label="'${action.getText('PreRepairRule.content')}'" 
					          name="'preRepairRule.content'" 
					          value="'${preRepairRule.content?if_exists}'" rows="'3'" cols="'50'" 
							  required="false"/>
			</tr>							
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	</@ww.form>
	<script language="javascript" type="text/JavaScript">
	  function customize_validate() {
	  	if ('' == document.forms["newPreRepairRule"].elements["preRepairRule.position"].value) {       
		    alert("${action.getText('preRepairRule.position.requiredstring')}");
		    return false;
		  } else {
		    if (!isValidLengthValue(document.forms["newPreRepairRule"].elements["preRepairRule.position"].value,0,50)) {
		      alert("${action.getText('preRepairRule.position.maxLength')}");
		      return false;
		    }
		  }
		  
		if(isNotEmpty(newPreRepairRule,"preRepairRule.content")) {
			if (!isValidLength(document.forms[0],"preRepairRule.content",0,50)){
				alert("${action.getText('preRepairRule.content.maxLength')}");
				return false;
		    }
		}
		
		if(isNotEmpty(newPreRepairRule,"preRepairRule.lastRepairDate")) {
			if(!isDate("preRepairRule.lastRepairDate")){
				alert('${action.getText('PreRepairRule.lastRepairDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
			if (isDateLessThenCurrent(document.forms[0].elements["preRepairRule.lastRepairDate"].value)) {
				alert("${action.getText('lastRepairDate.later.error')}");
				return false;
			}
		}
				
	  	if(isNotEmpty(newPreRepairRule,"preRepairRule.maxRunHour")) {
		      if (!isNumber("preRepairRule.maxRunHour")) {
				alert("${action.getText('preRepairRule.maxRunHour.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["preRepairRule.maxRunHour"].value, 1000000001, 0)){
				alert("${action.getText('preRepairRule.maxRunHour.maxLength')}");
				return false;
		      }
		}
		
	    return true;
	  }
	</script>
</@htmlPage>