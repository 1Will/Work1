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
<@htmlPage title="${action.getText('spareType.title')}">
     <@ww.form  name="'spareType'" action="'saveSpareType'" method="'post'">
       <@ww.token name="saveSpareTypeToken"/>
        <#if spareType.id?exists>
			<@ww.hidden name="'spareType.id'" value="#{spareType.id}"/>
		</#if>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('spareType.code')}'" name="'spareType.code'" value="'${spareType.code?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('spareType.name')}'" name="'spareType.name'" value="'${spareType.name?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>
             <tr>
             	 <@ww.select label="'${action.getText('SpareSort')}'"  name="'toolingDevFlag'" 
	    			     listKey="value" listValue="label" value="'${spareType.toolingDevFlag?if_exists}'"
	                     list="spareSort"
	                     required="false" emptyOption="true" disabled="false" required="true">
	    	     </@ww.select>
             </tr>             
         </@inputTable>
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkInput()'"/>	          
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/codevalue/listSpareTypes.html"/>
         </@buttonBar>
       
	</@ww.form>
     <script language="javascript">
	  window.onload=function(){
	     <#if spareType.id?exists>
		   var url = '${req.contextPath}/base/codevalue/listSpareDetailTypes.html?spareType.id=#{spareType.id}';
	       document.all.frame.src = url;
		   getObjByNameRe("spareTypeDetails").className = "selectedtab";
	    </#if>
	     }
	    function checkInput() {
		    //验证分类编号
	     	if ('' == document.forms["spareType"].elements["spareType.code"].value) {       
				alert("${action.getText('spareType.code.requiredstring')}");
				return false;
			} else {
				if (!isValidLengthValue(document.forms["spareType"].elements["spareType.code"].value,0,50)) {
					 alert("${action.getText('spareType.code.stringlength')}");
					 return false;
				}
			 }
		    //验证分类名称
	     	if ('' == document.forms["spareType"].elements["spareType.name"].value) {       
				alert("${action.getText('spareType.name.requiredstring')}");
				return false;
			} else {
				if (!isValidLengthValue(document.forms["spareType"].elements["spareType.name"].value,0,50)) {
					 alert("${action.getText('spareType.name.stringlength')}");
					 return false;
				}
			 }
			//验证分类编号
	     	if ('' == document.forms["spareType"].elements["toolingDevFlag"].value) {       
				alert("${action.getText('spareType.toolingDevFlag.requiredstring')}");
				return false;
			}		     	     
	     }
     </script>
     <#if spareType.id?exists>
		<ul id="beautytab">
			<li><a id="spareTypeDetails" onclick="activeTab(this);"  
				href="listSpareDetailTypes.html?spareType.id=#{spareType.id}" target="frame" >${action.getText('spareTypeDetail')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	 </#if>
</@htmlPage>
