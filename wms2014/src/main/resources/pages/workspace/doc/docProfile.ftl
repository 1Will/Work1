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
<#-- $Id: docProfile.ftl 7921 2007-10-22 02:36:23Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('doc.title')}">
    <@ww.form namespace="'/workspace'" name="'doc'" action="'saveDoc'" method="'post'" validate="true">
        <@ww.token name="saveDocToken"/>
        <@ww.hidden name="'approved.result'" value="APPROVED"/>
        <@ww.hidden name="'doc.id'" value="${job.id?if_exists}"/>
        <@ww.hidden name="'approver.id'" value="'${req.getParameter('approver.id')?if_exists}'"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('doc.no')}'"  name="'doc.no'" value="'${job.docNo}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	  	<@ww.textfield label="'${action.getText('doc.type')}'"  name="'doc.type'" value="'${job.docType.name}'" cssClass="'underline'"  disabled="true" readonly="true"/> 		
	 	  	  	<#--
	 	  	  	<@ww.textfield label="'${action.getText('doc.name')}'"  name="'doc.name'" value="'${job.name}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	 	-->
	 	  	  </tr>
	 	  	  <tr>
	 	  	  <#--
	 			<@ww.textfield label="'${action.getText('doc.department')}'"  name="'doc.department'" value="''" cssClass="'underline'"  disabled="true" readonly="true"/> 		
	 	  	  	-->
	 	  	  	<@ww.textfield label="'${action.getText('doc.submittor')}'"  name="'doc.creator'" value="'${job.creator}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	  	<@ww.textfield label="'${action.getText('doc.submittedTime')}'"  name="'doc.createdTime'" value="'${job.createdTime}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	  </tr>
	 	  	  <tr>
	 	  	  	<@ww.textfield label="'${action.getText('doc.auditComment')}'"  name="'job.comment'" value="'${job.comment}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	  	<@ww.textfield label="'${action.getText('doc.docStatus')}'"  name="'job.docState.value'" value="'${job.docState.value}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	 	  	  </tr>
	 	  	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	 	  	  <tr>
	 	  	  	<@ww.textfield label="'${action.getText('doc.docStatus')}'"  name="'job.docState.value'" value="'${job.docState.value}'" cssClass="'underline'"  disabled="true" readonly="true"/>
				 <td align="right" valign="top"><label class="label">${action.getText('doc.options')}:</label>
					 <td valign="top">
					 	<input type="radio" name="approved" value="DOC_APPROVED" checked="true">${action.getText('doc.approved')}
					 	<input type="radio" name="approved" value="DOC_REJECTED">${action.getText('doc.reject')}
					 	<input type="radio" name="approved" value="DOC_WAITTING">${action.getText('doc.waitting')}
					 </td>
				 </td>
	 	  	  </tr>
	 	  	  <tr>
	 	  	  	<@ww.textarea label="'${action.getText('doc.docText')}'" 
              				   name="'approver.docText'" 
                               value="'${approver.docText?if_exists}'" rows="'5'" cols="'50'" 
				               required="false"/>
	 	  	  </tr>
		</@inputTable>
		<@buttonBar>
			<@vsubmit value="'${action.getText('submit')}'" onclick="'return getApprovedResult();'"/>  
	        <@vbutton class="button" value="${action.getText('doc.viewDetail')}" onclick="open_viewDetailDialog(#{job.docType.id});"/>
	        <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/workspace/listDocs.html"/>	
	     </@buttonBar>
	     
	     <script language="javascript">
	     	window.onload = function () {
				var url = '${req.contextPath}/workspace/listSignatureLogs.html?job.id=' + ${job.id};
		 		document.all.frame.src= url;
		 		getObjByNameRe("signatureLogs").className = "selectedtab";		 		
		 		document.all.frame.src=  url;
		 		
		 		<#if job.docState?exists>
		 			var approvedValue = "${job.docState.code}";
		 			
		 			var ary = document.forms[0].approved;
			 		for (var i = 0; i < ary.length; i ++) {
			 			if (approvedValue == ary[i].value) {
			 				ary[i].checked=true;
			 				break;
			 			}
			 		}
		 		</#if>
		 	}
	     	function getApprovedResult() {
	     		var approved = document.forms[0].elements["approved"];
	     		for (var i=0; i<approved.length; i++) {
	     			if (approved[i].checked==true) {
	     			document.forms[0].elements["approved.result"].value = approved[i].value;
	     			<#--
	     				if (approved[i].value==4) {
	     					document.forms[0].elements["approved.result"].value = "APPROVED";
	     				} else {
	     					document.forms[0].elements["approved.result"].value = "REJECT";
	     				}
	     				-->
	     				return true;
	     			}
	     		}
	     		return true;
	     	}
	     	function open_viewDetailDialog(docTypeId) {
	     		var url;
	     		if (docTypeId == 1) {
	     			url = '${req.contextPath}/runmaintenance/checkPoint/' + 'editCheckPointRule.html?rule.id=' + #{job.docIdentify} + '&view=1';
	     		} else if (docTypeId == 2) {
	     			url = '${req.contextPath}/asset/device/' + 'editDevice.html?device.id=' + #{job.docIdentify} + '&view=1';
	     		} else if (docTypeId == 3) {
	     			url = '${req.contextPath}/runmaintenance/checkPoint/' + 'editCheckPointPlan.html?plan.id=' + #{job.docIdentify} + '&view=1';
	     		} else if (docTypeId == 4) {
	     			url ='${req.contextPath}/runmaintenance/checkPoint/' + 'editCheckPointProcs.html?proc.id=' + #{job.docIdentify} + '&view=1';
	     		}
	     		popupModalDialog(url, 1000,600);
	     		
	     	}
	     	//editDeviceCardView
	     </script>
    </@ww.form>
    
    <#if job.id?exists>
		<ul id="beautytab">
			<li><a id="signatureLogs" onclick="activeTab(this);"  href="listSignatureLogs.html?job.id=#{job.id}" target="frame" >${action.getText('signatureLogs.title')}</a></li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>