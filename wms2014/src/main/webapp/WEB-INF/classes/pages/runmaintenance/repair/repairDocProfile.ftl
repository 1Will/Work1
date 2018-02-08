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

<@htmlPage title="${action.getText('repair.doc.title1')}">
 <base target="_self">
	<@ww.form name="'repairDoc'" action="'saveRepairDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveRepairDocToken"/>
		<@inputTable>
	      <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	  </#if>
     	  <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 	  <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	 	  <#if repairDoc.id?exists>
	 	    <@ww.hidden name="'repairDoc.id'" value="#{repairDoc.id?if_exists}"/>
	 	  </#if>
	 	  <#if repairDoc.id?exists>
	 	  <#else>
	 	    <tr>
	          <@ww.file label="'${action.getText('repair.doc.upload')}'" size="60" name="'file'" cssClass="'button'" required="true" onchange="'getName();'"/>
	        </tr>
	 	  </#if>
	            <tr>
	            	<@ww.textfield label="'${action.getText('repair.doc.name')}'" size="50" name="'repairDoc.name'" value="'${repairDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>		
				<tr>
					 <@oneLine>
					 <@ww.textarea label="'${action.getText('repair.doc.description')}'" 
					         name="'repairDoc.description'" 
					         value="'${repairDoc.description?if_exists}'" rows="'4'" cols="'80'" 
							 required="false"/>
					</@oneLine>
				</tr>
		</@inputTable>
		<@buttonBar>
		  <#if repairDoc.id?exists>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return chageAction();'"/>
		  <#else>
		    <@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
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
			  var filename = document.forms["repairDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
			  document.forms["repairDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
			  getFileNameByFile(document.forms["repairDoc"],"repairDoc.name");
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
			  document.forms["repairDoc"].action="saveModifyRepairDoc.html";
		      return true;
			}
			window.onload=function() {
			  if ("true" == document.forms["repairDoc"].elements["first"].value) {
			    <#if repairDoc.id?exists>
			  	  alert("${action.getText('repair.doc')}" + '${repairDoc.name?if_exists}' + "${action.getText('upload.success')}");
			  	  window.close();
			    </#if>
			  }
			}
			//验证资料名称是否为空,以及长度
			function validateRepairDocName() {
			  if ('' == document.forms["repairDoc"].elements["repairDoc.name"].value) {
			     alert("${action.getText('repairDoc.name.required')}");
			     return false;
			  } else if (!isValidLength(document.forms[0], "repairDoc.name", null, 150)) {
			    alert("${action.getText('repairDoc.name.length')}");
			    return false;
			  }
			  return true;
			}
			//验证资料描述的长度
			function validateDescription() {
			  if ('' != document.forms["repairDoc"].elements["repairDoc.description"].value) {
			    if (!isValidLength(document.forms[0], "repairDoc.description", null, 250)) {
			      alert("${action.getText('repairDoc.description.length')}");
			      return false;
			    }
			  }
			  return true;
			}
		</script>
		
	</@ww.form>
</@htmlPage>