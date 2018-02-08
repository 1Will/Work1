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

<@htmlPage title="${action.getText('attachTool.profile')}">
 <base target="_self">
	<@ww.form name="'newAttachTool'" action="'saveAttachTool'" method="'post'" validate="true">
		<@ww.token name="saveAttachToolToken"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@ww.hidden name="'attachTool.id'" value="'${req.getParameter('attachTool.id')?if_exists}'"/>
		<@inputTable>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('attachTool.name')}'" name="'attachTool.name'" value="'${attachTool.name?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('attachTool.piece')}'" name="'attachTool.piece'" value="'${attachTool.piece?if_exists}'" cssClass="'underline'"/>
			</tr>
			<tr>
				<@oneLine>
				 <@ww.textarea label="'${action.getText('comment')}'" name="'attachTool.comment'" value="'${attachTool.comment?if_exists}'" rows="'4'" cols="'80'" required="false"/>
				</@oneLine>
			</tr>							
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<script language="javascript">
			function customize_validate() {
				if(isNotEmpty(newAttachTool,"attachTool.name")) {
					if (!isValidLength(document.forms[0],"attachTool.name",0,50)){
						alert("${action.getText('attachTool.name.maxLength')}");
						return false;
				    }
				} else {
					alert("${action.getText('attachTool.name.requiredstring')}");
					return false;
				}
				
				if(isNotEmpty(newAttachTool,"attachTool.piece")) {
				      if (!isNumber("attachTool.piece")) {
						alert("${action.getText('attachTool.piece.isNotNumber')}");
						return false;
				      } else if (!isNumberBetweenBoolen(document.forms[0].elements["attachTool.piece"].value, 1000000001, 0)){
						alert("${action.getText('attachTool.piece.maxLength')}");
						return false;
				      }
				}else{
				   alert("${action.getText('attachTool.piece.required')}");
				   return false;
				}
				
				if(isNotEmpty(newAttachTool,"attachTool.comment")) {
					if (!isValidLength(document.forms[0],"attachTool.comment",0,250)){
						alert("${action.getText('attachTool.comment.maxLength')}");
						return false;
				    }
				}
			}
		</script>
	</@ww.form>
</@htmlPage>