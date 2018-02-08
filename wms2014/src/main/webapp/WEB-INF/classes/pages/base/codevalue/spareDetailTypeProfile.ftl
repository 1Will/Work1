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
<@htmlPage title="${action.getText('spareTypeDetail.title')}">
     <@ww.form  name="'spareTypeDetail'" namespace="'/base/codevalue'" action="'saveSpareTypeDetail'" method="'post'">
       <@ww.token name="saveSpareTypeDetailToken"/>
       	<#if spareType.id?exists>
			<@ww.hidden name="'spareType.id'" value="#{spareType.id}"/>
		</#if>
		<#if spareDetailType.id?exists>
			<@ww.hidden name="'spareDetailType.id'" value="#{spareDetailType.id}"/>
		</#if>
         <@inputTable>
             <tr>
             <@ww.textfield label="'${action.getText('spareDetailType.code')}'" name="'spareDetailType.code'" value="'${spareDetailType.code?if_exists}'" cssClass="'underline'" required="true"/>
             <@ww.textfield label="'${action.getText('spareDetailType.name')}'" name="'spareDetailType.name'" value="'${spareDetailType.name?if_exists}'" cssClass="'underline'" required="true"/>
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
     	if ('' == document.forms["spareTypeDetail"].elements["spareDetailType.code"].value) {       
			alert("${action.getText('spareDetailType.code.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["spareTypeDetail"].elements["spareDetailType.code"].value,0,50)) {
				 alert("${action.getText('spareDetailType.code.stringlength')}");
				 return false;
			}
		 }
	    //验证分类名称
     	if ('' == document.forms["spareTypeDetail"].elements["spareDetailType.name"].value) {       
			alert("${action.getText('spareDetailType.name.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["spareTypeDetail"].elements["spareDetailType.name"].value,0,50)) {
				 alert("${action.getText('spareDetailType.name.stringlength')}");
				 return false;
			}
		 }
		}
     </script>

</@htmlPage>

