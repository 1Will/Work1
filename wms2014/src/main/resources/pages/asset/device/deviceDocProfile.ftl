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

<@htmlPage title="${action.getText('device.doc.title1')}">
 <base target="_self">
	<@ww.form name="'deviceDoc'" action="'saveDeviceDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="saveDeviceDocToken"/>
		<@inputTable>
			<#if device?exists>
	            <@ww.hidden name="'device.id'" value="#{device.id?if_exists}"/>
	         </#if>
	            <tr>
	            	<@ww.file label="'${action.getText('device.doc.upload')}'" size="60" name="'file'" cssClass="'button'" required="true" onchange="'getName();'"/>
	            </tr>	
	            <tr>
	            	<@ww.textfield label="'${action.getText('device.doc.name')}'" size="50" name="'deviceDoc.name'" value="'${deviceDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>		
				<tr>
					 <@oneLine>
					 <@ww.textarea label="'${action.getText('device.doc.description')}'" 
					         name="'deviceDoc.description'" 
					         value="'${deviceDoc.description?if_exists}'" rows="'4'" cols="'80'" 
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
			  var filename = document.forms["deviceDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
			  document.forms["deviceDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
			  getFileNameByFile(document.forms["deviceDoc"],"deviceDoc.name");
			}
			window.onload=function() {
			  <#if deviceDoc.id?exists>
			  	alert("${action.getText('device.doc')}" + '${deviceDoc.name?if_exists}' + "${action.getText('upload.success')}");
			  	window.close();
			  </#if>
			}
		</script>
		
	</@ww.form>
</@htmlPage>