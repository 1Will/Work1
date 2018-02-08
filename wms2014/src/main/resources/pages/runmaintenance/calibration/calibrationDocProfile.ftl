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

<@htmlPage title="${action.getText('calibrationDoc.title')}">
 <base target="_self">
	<@ww.form name="'calibrationDocProfile'" action="'saveCalibrationDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveCalibrationDocToken"/>
		<@inputTable>
			<#if detail.id?exists>
	            <@ww.hidden name="'calibrationDetail.id'" value="#{detail.id?if_exists}"/>
	        </#if>
	        <#if doc.id?exists>
	         <@ww.hidden name="'doc.id'" value="#{doc.id?if_exists}"/>
	        </#if>
	         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	       <#if doc.id?exists>
	            <tr>
	            	<@ww.textfield label="'${action.getText('doc.name')}'" size="50" name="'doc.name'" value="'${doc.name?if_exists}'" cssClass="'underline'" required="true"/>
	              </tr>
				<tr>
				 <@ww.textarea label="'${action.getText('doc.comment')}'" 
					         name="'doc.description'" 
					         value="'${doc.description?if_exists}'" rows="'3'" cols="'50'" 
				  required="false"/>
				</tr>
	         <#else>
	            <tr>
	            	<@ww.file label="'${action.getText('doc.upload')}'" size="60" name="'file'" cssClass="'button'" onchange="'return getName();'"required="true"/>
	            </tr>	

	            <tr>
	            	<@ww.textfield label="'${action.getText('doc.name')}'" size="50" name="'doc.name'" value="'${doc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>
				<tr>
				 <@ww.textarea label="'${action.getText('doc.comment')}'" 
					         name="'doc.description'" 
					         value="'${doc.description?if_exists}'" rows="'3'" cols="'50'" 
				  required="false"/>
				</tr>
	           </#if>
		</@inputTable>
		<@buttonBar>
		    <#if doc.id?exists>
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
			  var filename = document.forms["calibrationDocProfile"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
	
			  document.forms["calibrationDocProfile"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
		      var filename = document.forms["calibrationDocProfile"].elements["file"].value;
		      var ary = filename.split("\\");
		      var ary1 = ary[ary.length-1].split("\.");
		      document.forms["calibrationDocProfile"].elements["doc.name"].value=ary1[0];
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
			  document.forms["calibrationDocProfile"].action="saveCalibrationDocModify.html";
		      return true;
			}
			window.onload=function() {
			  if ("true" == document.forms["calibrationDocProfile"].elements["first"].value) {
			    <#if doc.id?exists>
			  	  alert("${action.getText('calibrationDoc')}" + '${doc.name?if_exists}' + "${action.getText('upload.success')}");
			  	  window.close();
			    </#if>
			 }
			}
			
	//验证资料名称是否为空,以及长度
			function validateRepairDocName() {
			<#if doc.id?exists>
			<#else>
			  if ('' == document.forms["calibrationDocProfile"].elements["file"].value) {
			     alert("${action.getText('doc.file.required')}");
			     return false;
			  }
			</#if>
			  if ('' == document.forms["calibrationDocProfile"].elements["doc.name"].value) {
			     alert("${action.getText('doc.name.required')}");
			     return false;
			  } else if (!isValidLength(document.forms["calibrationDocProfile"], "doc.name", null, 150)) {
			    alert("${action.getText('doc.name.length')}");
			    return false;
			  }
			  return true;
			}
			//验证资料描述的长度
			function validateDescription() {
			  if ('' != document.forms["calibrationDocProfile"].elements["doc.description"].value) {
			    if (!isValidLength(document.forms["calibrationDocProfile"], "doc.description", null, 250)) {
			      alert("${action.getText('doc.description.length')}");
			      return false;
			    }
			  }
			  return true;
			}		
			
		</script>
		
	</@ww.form>
</@htmlPage>