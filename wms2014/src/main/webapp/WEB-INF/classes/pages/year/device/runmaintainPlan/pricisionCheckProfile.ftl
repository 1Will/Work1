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
<@htmlPage title="${action.getText('pricisionCheckProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'pricisionCheck'" action="'savePricisionCheck'" method="'post'" validate="true">
     <@ww.token name="savePricisionCheckToken"/>
     <#if runmaintainPlanDetail.id?exists>
       <@ww.hidden name="'runmaintainPlanDetail.id'" value="'#{runmaintainPlanDetail.id?if_exists}'"/>
     </#if>
     <#if pricisionCheck.id?exists>
       <@ww.hidden name="'pricisionCheck.id'" value="#{pricisionCheck.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('pricisionCheck.fee')}'" name="'pricisionCheck.fee'" value="'${pricisionCheck.fee?if_exists}'" cssClass="'underline'" disabled="false"  required="true" />
	   </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('pricisionCheck.content')}'" 
					   name="'pricisionCheck.content'" 
					   value="'${pricisionCheck.content?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" required="true" />
 	  	 <@ww.textarea label="'${action.getText('pricisionCheck.comment')}'" 
		              name="'pricisionCheck.comment'" 
		              value="'${pricisionCheck.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
	   </tr>
     </@inputTable>
     <@buttonBar>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     function validate() {
       //验证费用
       if (!validateFee()) {
         return false;
       }
       //验证内容和备注
       if (!validateContentAndComment()) {
         return false;
       }
       return true;
     }

     /*
      * 验证费用是否为空,以及格式是否正确
     */
     function validateFee() {
       //验证费用是否为空,以及格式
       var fee = document.forms["pricisionCheck"].elements["pricisionCheck.fee"].value;
       if ('' == fee) {         //验证是否为空
         alert("${action.getText('pricisionCheck.fee.requried')}");
         return false;
       } else if (!isDoubleNumber("pricisionCheck.fee")){  //验证格式
         alert("${action.getText('pricisionCheck.fee.format.error')}");
         return false;
       } else if (!isDoubleNumberBetweenBoolean(fee, 10000000001, 0)){  //验证范围
         alert("${action.getText('pricisionCheck.fee.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证需求原因和备注的长度
     */
     function validateContentAndComment() {
        //验证内容是否为空,长度
       if ('' == document.forms["pricisionCheck"].elements["pricisionCheck.content"].value) {
         alert("${action.getText('pricisionCheck.content.requried')}");
         return false;
       } else if (!isValidLength(document.forms["pricisionCheck"],"pricisionCheck.content",0,250)) {
           alert("${action.getText("pricisionCheck.content.maxLength")}");
		   return false;
       }
	   //验证备注长度
	   if ('' != document.forms["pricisionCheck"].elements["pricisionCheck.comment"].value) {
	     if (!isValidLength(document.forms["pricisionCheck"],"pricisionCheck.comment",0,250)) {
	       alert("${action.getText("pricisionCheck.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
   </script>
</@htmlPage>