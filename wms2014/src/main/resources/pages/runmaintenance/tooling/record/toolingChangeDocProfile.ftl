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
<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('changeDoc.title1')}">
 <base target="_self">
	<@ww.form name="'ToolingChangeDoc'" action="'saveToolingChangeDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveToolingChangeDocToken"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@inputTable>
			<#if toolingChangeBill.id?exists>
	            <@ww.hidden name="'toolingChangeBill.id'" value="#{toolingChangeBill.id?if_exists}"/>
	        </#if>
	        <#if toolingChangeDoc.id?exists>
	         <@ww.hidden name="'toolingChangeDoc.id'" value="#{toolingChangeDoc.id?if_exists}"/>
	        </#if>
	         <#if toolingChangeDoc.id?exists>
	         <#else>
	            <tr>
	            	<@ww.file label="'${action.getText('toolingChangeDoc.upload')}'" size="60" name="'file'" cssClass="'button'" onchange="'return getName();'"required="true"/>
	            </tr>	
	          </#if>
	            <tr>
	            	<@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="50" name="'toolingChangeDoc.name'" value="'${toolingChangeDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>
	            <tr>
	            	<@ww.textfield label="'${action.getText('toolingChangeDoc.no')}'" size="50" name="'toolingChangeDoc.fileNo'" value="'${toolingChangeDoc.fileNo?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>		
				<tr>
					 <@ww.textarea label="'${action.getText('toolingChangeDoc.comment')}'" 
					         name="'toolingChangeDoc.description'" 
					         value="'${toolingChangeDoc.description?if_exists}'" rows="'3'" cols="'50'" 
							 required="false"/>
				</tr>
		</@inputTable>
		<@buttonBar>
		    <#if toolingChangeDoc.id?exists>
		    
          <#if !(action.isReadOnly())>
		      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return chageAction();'"/>
		   </#if>
		    <#else>
		     <#if !(action.isReadOnly())>
		      <@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
		     </#if>
		    </#if>
			<@vbutton name="'close'"class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<@ww.hidden name="'origFileName'" value=""/>
		<@ww.hidden name="'first'" value="${first?if_exists}"/>
		<script language="javascript">
			function getFileName() {
			  //验证资料名称是否为空,以及长度
			  if (!validateRepairDocName()) {
			    return false;
			  }
			  //验证资料描述的长度
			  if (!validateDescription()) {
			    return false;
			  }
			  var filename = document.forms["ToolingChangeDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
			  document.forms["ToolingChangeDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
		      var filename = document.forms["ToolingChangeDoc"].elements["file"].value;
		      var ary = filename.split("\\");
		      var ary1 = ary[ary.length-1].split("\.");
		      document.forms["ToolingChangeDoc"].elements["toolingChangeDoc.name"].value=ary1[0];
		      return true;
		    }
			function chageAction() {
			  //验证资料名称是否为空,以及长度
			  if (!validateRepairDocName()) {
			    return false;
			  }
			  //验证资料描述的长度
			  if (!validateDescription()) {
			    return false;
			  }
			  document.forms["ToolingChangeDoc"].action="saveModifyToolingChangeDoc.html";
		      return true;
			}
			window.onload=function() {
			  if ("true" == document.forms["ToolingChangeDoc"].elements["first"].value) {
			    <#if toolingChangeDoc.id?exists>
			  	  alert("${action.getText('toolingChangeDoc')}" + '${toolingChangeDoc.name?if_exists}' + "${action.getText('upload.success')}");
			  	  window.close();
			    </#if>
			 }
			}
			//验证资料名称是否为空,以及长度
			function validateRepairDocName() {
			  if ('' == document.forms["ToolingChangeDoc"].elements["toolingChangeDoc.name"].value) {
			     alert("${action.getText('toolingChangeDoc.name.required')}");
			     return false;
			  } else if (!isValidLength(document.forms[0], "toolingChangeDoc.name", null, 150)) {
			    alert("${action.getText('toolingChangeDoc.name.length')}");
			    return false;
			  }
			  return true;
			}
			//验证资料描述的长度
			function validateDescription() {
			  if ('' != document.forms["ToolingChangeDoc"].elements["toolingChangeDoc.description"].value) {
			    if (!isValidLength(document.forms[0], "toolingChangeDoc.description", null, 250)) {
			      alert("${action.getText('toolingChangeDoc.description.length')}");
			      return false;
			    }
			  }
			  return true;
			}
		</script>
		
	</@ww.form>
</@htmlPage>