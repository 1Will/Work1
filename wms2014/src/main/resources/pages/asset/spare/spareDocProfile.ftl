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

<@htmlPage title="${action.getText('spare.doc.title1')}">
 <base target="_self">
	<@ww.form name="'spareDoc'" action="'saveSpareDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveSpareDocToken"/>
		<@inputTable>
			<#if spare?exists>
	            <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
	         </#if>
	         
	            <tr>
	            	<@ww.file label="'${action.getText('spare.doc.upload')}'" size="60" name="'file'" cssClass="'button'" required="true" onchange="'getName();'"/>
	            </tr>	
	            <tr>
	            	<@ww.textfield label="'${action.getText('spare.doc.name')}'" size="50" name="'spareDoc.name'" value="'${spareDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>
				<tr>
					 <@oneLine>
					 <@ww.textarea label="'${action.getText('spare.doc.description')}'" 
					         name="'spareDoc.description'" 
					         value="'${spareDoc.description?if_exists}'" rows="'4'" cols="'80'" 
							 required="false"/>
					</@oneLine>
				</tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit name="'upload'" value="'${action.getText('upload')}'" onclick="'return getFileName();'"/>
			<@vbutton name="'close'"class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		<@ww.hidden name="'origFileName'" value=""/>
		<script language="javascript">
			function getFileName() {
			  var filename = document.forms["spareDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
			  document.forms["spareDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
			  getFileNameByFile(document.forms["spareDoc"],"spareDoc.name");
			}
			window.onload=function() {
			  <#if spareDoc.id?exists>
			  	alert("${action.getText('spare.doc')}" + '${spareDoc.name?if_exists}' + "${action.getText('upload.success')}");
			  	window.close();
			  </#if>
			}
		</script>
		
	</@ww.form>
</@htmlPage>