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

<#--$Id: toolingTypeProfile.ftl 11326 2008-03-15 06:48:54Z smzhang $ -->

<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingType.title')}">
     <@ww.form  name="'toolingType'" action="'saveToolingType'" method="'post'">
       <@ww.token name="saveToolingTypeToken"/>
        <#if toolingType.id?exists>
			<@ww.hidden name="'toolingType.id'" value="#{toolingType.id}"/>
		</#if>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('toolingType.code')}'" name="'toolingType.code'" value="'${toolingType.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('toolingType.value')}'" name="'toolingType.value'" value="'${toolingType.value?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>             
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkInput()'"/>	          
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/codevalue/listToolingTypes.html"/>
         </@buttonBar>
         
         
     </@ww.form>
     <script language="javascript">
	  window.onload=function(){
	     <#if toolingType.id?exists>
		   var url = '${req.contextPath}/base/codevalue/listToolingTypeDetails.html?toolingType.id=#{toolingType.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	       document.all.frame.src = url;
		   document.getElementById("toolingTypeDetails").className = "selectedtab";
	    </#if>
	     }
	    function checkInput() {
	     //验证分类编号
     	if ('' == document.forms["toolingType"].elements["toolingType.code"].value) {       
			alert("${action.getText('toolingType.code.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["toolingType"].elements["toolingType.code"].value,0,50)) {
				 alert("${action.getText('toolingType.code.stringlength')}");
				 return false;
			}
		 }
	    //验证分类名称
     	if ('' == document.forms["toolingType"].elements["toolingType.value"].value) {       
			alert("${action.getText('toolingType.value.requiredstring')}");
			return false;
		} else {
			if (!isValidLengthValue(document.forms["toolingType"].elements["toolingType.value"].value,0,50)) {
				 alert("${action.getText('toolingType.value.stringlength')}");
				 return false;
			}
		 }
		}
     </script>
     <#if toolingType.id?exists>
		<ul id="beautytab">
			<li><a id="toolingTypeDetails" onclick="activeTab(this);"  
				href="listToolingTypeDetails.html?toolingType.id=#{toolingType.id}" target="frame" >${action.getText('toolingTypeDetail')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	 </#if>
</@htmlPage>
