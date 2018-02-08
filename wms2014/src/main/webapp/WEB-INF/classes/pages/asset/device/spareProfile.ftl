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

<@htmlPage title="${action.getText('spare.title1')}">
 <base target="_self">
	<@ww.form name="'newSpare'" action="'saveSpare'" method="'post'" validate="true">
		<@ww.token name="saveSpareToken"/>
		<@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	 	<@ww.hidden name="'spare.id'" value="'${spare.id?if_exists}'"/>
		<@inputTable>
			<tr>
			    <@ww.textfield readonly="false" label="'${action.getText('spare.spareNo')}'" name="'spare.spareNo'" value="'${spare.spareNo?if_exists}'"  cssClass="'underline'" required="true"/>
			    <@ww.textfield readonly="false" label="'${action.getText('spare.name')}'" name="'spare.name'" value="'${spare.name?if_exists}'" cssClass="'underline'" required="true"/>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" />
		    <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
	</@ww.form>
</@htmlPage>