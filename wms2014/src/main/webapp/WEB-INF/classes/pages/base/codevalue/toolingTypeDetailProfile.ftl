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

<#--$Id: toolingTypeDetailProfile.ftl 11326 2008-03-15 06:48:54Z smzhang $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingTypeDetail.title')}">
     <@ww.form  name="'toolingTypeDetail'" action="'saveToolingTypeDetail'" method="'post'" validate="true">
       <@ww.token name="saveToolingTypeDetailToken"/>
       	<#if toolingType.id?exists>
			<@ww.hidden name="'toolingType.id'" value="#{toolingType.id}"/>
		</#if>
		<#if toolingTypeDetail.id?exists>
			<@ww.hidden name="'toolingTypeDetail.id'" value="#{toolingTypeDetail.id}"/>
		</#if>
         <@inputTable>
             <tr>
             <@ww.textfield label="'${action.getText('toolingTypeDetail.code')}'" name="'toolingTypeDetail.code'" value="'${toolingTypeDetail.code?if_exists}'" cssClass="'underline'" required="true"/>
             <@ww.textfield label="'${action.getText('toolingTypeDetail.name')}'" name="'toolingTypeDetail.name'" value="'${toolingTypeDetail.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>             
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkInput()'"/>	          
	         <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
         </@buttonBar>
     </@ww.form>
     <script language="javascript">
		function checkInput() {
	     //验证分类编号
     	if ('' == document.forms["toolingTypeDetail"].elements["toolingTypeDetail.code"].value) {       
			alert("${action.getText('toolingTypeDetail.code.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["toolingTypeDetail"].elements["toolingTypeDetail.code"].value,0,50)) {
				 alert("${action.getText('toolingTypeDetail.code.stringlength')}");
				 return false;
			}
		 }
	    //验证分类名称
     	if ('' == document.forms["toolingTypeDetail"].elements["toolingTypeDetail.name"].value) {       
			alert("${action.getText('toolingTypeDetail.name.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["toolingTypeDetail"].elements["toolingTypeDetail.name"].value,0,50)) {
				 alert("${action.getText('toolingTypeDetail.name.stringlength')}");
				 return false;
			}
		 }
		}
     </script>

</@htmlPage>

