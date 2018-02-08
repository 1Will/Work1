<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: payDetailProfile.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('acceptBillDetail.title')}">
<base target="_self">
	
	<@ww.form namespace="'/tooling/acceptBillSelector'" name="'acceptBillDetail'" action="'saveAcceptBillDetail'" method="'post'" validate="true">
		<@ww.token name="savePayDetailToken"/>
		<#if acceptBill.id?exists>
			<@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
		</#if>
		<#if acceptBillDetail.id?exists>
			<@ww.hidden name="'acceptBillDetail.id'" value="#{acceptBillDetail.id}"/>
		</#if>
		<@inputTable>
			<tr>
			 <@ww.textfield label="'${action.getText('accept.Project')}'" name="'acceptBillDetail.acceptProject'" value="'${acceptBillDetail.acceptProject?if_exists}'"  cssClass="'underline'" required="true" />
			 <@ww.textarea label="'${action.getText('accept.situation')}'"  name="'acceptBillDetail.acceptSituation'" value="'${acceptBillDetail.acceptSituation?if_exists}'" rows="'3'" cols="'50'" />
			</tr>
			<tr>
			 <@ww.textarea label="'${action.getText('handleopinion')}'" name="'acceptBillDetail.handleOpinion'" value="'${acceptBillDetail.handleOpinion?if_exists}'" rows="'3'" cols="'50'" cssClass="'underline'"/>
			 <@ww.textarea label="'${action.getText('memo')}'"  name="'acceptBillDetail.memo'" value="'${acceptBillDetail.memo?if_exists}'" rows="'3'" cols="'50'" />
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveVlidate();'"/>
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
	 <script language="javascript">	
	   function saveVlidate(){
       if(document.getElementById("acceptBillDetail.acceptProject").value=='' ){
         alert('${action.getText('acceptBillDetail.acceptProject.not.null')}');
         return false;
       }else{
		   if(!isValidLength(document.forms[0], "acceptBillDetail.acceptProject", null, 50)){
				alert("${action.getText('acceptBillDetail.acceptProject.acceptBilName.length')}");
				return  false;
			   }
       }
      
    if(document.getElementById("acceptBillDetail.acceptSituation").value!=''){
		   if(!isValidLength(document.forms[0], "acceptBillDetail.acceptSituation", null, 250)){
				alert("${action.getText('acceptBillDetail.acceptSituation')}");
				return  false;
			   }
			}
     if(document.getElementById("acceptBillDetail.handleOpinion").value!=''){
		   if(!isValidLength(document.forms[0], "acceptBillDetail.handleOpinion", null, 250)){
				alert("${action.getText('acceptBillDetail.handleOpinion')}");
				return  false;
			   }
			}
     if(document.getElementById("acceptBillDetail.memo").value!=''){
		   if(!isValidLength(document.forms[0], "acceptBillDetail.memo", null, 250)){
				alert("${action.getText('acceptBillDetail.memo')}");
				return  false;
			   }
			}
    
       return true;
      
   }
   
   </script>
</@htmlPage>