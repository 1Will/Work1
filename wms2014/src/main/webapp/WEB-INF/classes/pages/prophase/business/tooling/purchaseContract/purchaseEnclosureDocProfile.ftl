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
<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseDocCon.title1')}">
 <base target="_self">
	<@ww.form name="'PurchaseEnclosureDoc'" action="'savePurchaseEnclosureDoc'" method="'post'" enctype="'multipart/form-data'" validate="true">
		<@ww.token name="savePurchaseEnclosureDocToken"/>
		<@inputTable>
			<#if purchaseBill.id?exists>
	            <@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id?if_exists}"/>
	        </#if>
	        <#if purchaseEnclosureDoc.id?exists>
	         <@ww.hidden name="'purchaseEnclosureDoc.id'" value="#{purchaseEnclosureDoc.id?if_exists}"/>
	        </#if>
	       <#if purchaseEnclosureDoc.id?exists>
	            <tr>
	            	<@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="50" name="'purchaseEnclosureDoc.name'" value="'${purchaseEnclosureDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	              </tr>
				<tr>
				 <@ww.textarea label="'${action.getText('purchaseEnclosureDoc.comment')}'" 
					         name="'purchaseEnclosureDoc.description'" 
					         value="'${purchaseEnclosureDoc.description?if_exists}'" rows="'3'" cols="'50'" 
				  required="false"/>
				</tr>
	         <#else>
	            <tr>
	            	<@ww.file label="'${action.getText('toolingChangeDoc.upload')}'" size="60" name="'file'" cssClass="'button'" onchange="'return getName();'"required="true"/>
	            </tr>	

	            <tr>
	            	<@ww.textfield label="'${action.getText('toolingChangeDoc.name')}'" size="50" name="'purchaseEnclosureDoc.name'" value="'${purchaseEnclosureDoc.name?if_exists}'" cssClass="'underline'" required="true"/>
	            </tr>
				<tr>
				 <@ww.textarea label="'${action.getText('purchaseEnclosureDoc.comment')}'" 
					         name="'purchaseEnclosureDoc.description'" 
					         value="'${purchaseEnclosureDoc.description?if_exists}'" rows="'3'" cols="'50'" 
				  required="false"/>
				</tr>
	           </#if>
		</@inputTable>
		<@buttonBar>
		    <#if purchaseEnclosureDoc.id?exists>
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
			  var filename = document.forms["PurchaseEnclosureDoc"].elements["file"].value;
			  ary = filename.split("\\");
			  if (filename == '' || ary.length<=0) { 
			    alert("${action.getText('please.choose.file')}");
				return false;
			  }
			  document.forms["PurchaseEnclosureDoc"].elements["origFileName"].value=ary[ary.length-1];
			  return true;
			}
			function getName() {
		      var filename = document.forms["PurchaseEnclosureDoc"].elements["file"].value;
		      var ary = filename.split("\\");
		      var ary1 = ary[ary.length-1].split("\.");
		      document.forms["PurchaseEnclosureDoc"].elements["purchaseEnclosureDoc.name"].value=ary1[0];
		      return true;
		    }
			function chageAction() {
			  document.forms["PurchaseEnclosureDoc"].action="saveModifyPurchaseEnclosureDoc.html";
		      return true;
			}
			window.onload=function() {
			  if ("true" == document.forms["PurchaseEnclosureDoc"].elements["first"].value) {
			    <#if purchaseEnclosureDoc.id?exists>
			   
			  	  alert("${action.getText('purchaseBillchangeDocCon')}" + '${PurchaseEnclosureDoc.name?if_exists}' + "${action.getText('upload.success')}");
			  	  window.close();
			    </#if>
			 }
			}
		</script>
		
	</@ww.form>
</@htmlPage>