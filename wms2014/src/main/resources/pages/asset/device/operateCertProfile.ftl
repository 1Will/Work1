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

<@htmlPage title="${action.getText('operateCert.title')}">
 <base target="_self">
	<@ww.form name="'operateCert'" action="'saveOperateCerts'" method="'post'" validate="true">
		<@ww.token name="saveDeviceArgsToken"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@ww.hidden name="'operateCert.id'" value="'${req.getParameter('operateCert.id')?if_exists}'"/>
		<@inputTable>
            <tr>
				<@ww.textfield readonly="false" label="'${action.getText('operateCert.code')}'" name="'operateCert.code'" value="'${operateCert.code?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('operateCert.name')}'" name="'operateCert.name'" value="'${operateCert.name?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
				<@ww.textfield readonly="false" label="'${action.getText('operateCert.userCode')}'" name="'operateCert.userCode'" value="'${operateCert.userCode?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('operateCert.userName')}'" name="'operateCert.userName'" value="'${operateCert.userName?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>
				<@pp.datePicker label="'${action.getText('operateCert.examDate')}'" name="'operateCert.examDate'"
	     							value="'${(operateCert.examDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />	
				<@ww.textfield readonly="false" label="'${action.getText('operateCert.examScore')}'" name="'operateCert.examScore'" value="'${operateCert.examScore?if_exists}'"  cssClass="'underline'" />
			</tr>
			<tr>	
				<@ww.textfield readonly="false" label="'${action.getText('operateCert.examiner')}'" name="'operateCert.examiner'" value="'${operateCert.examiner?if_exists}'"  cssClass="'underline'" />
				<@ww.textarea label="'${action.getText('operateCert.comment')}'" 
				         name="'operateCert.comment'" 
				         value="'${operateCert.comment?if_exists}'" rows="'4'" cols="'50'" 
						 required="false"/>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'"/>
		    <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	</@ww.form>
</@htmlPage>