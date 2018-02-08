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
<@htmlPage title="${action.getText('dailyRepairProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'dailyRepair'" action="'saveDailyRepair'" method="'post'" validate="true">
     <@ww.token name="saveDailyRepairToken"/>
     <#if runmaintainPlanDetail.id?exists>
       <@ww.hidden name="'runmaintainPlanDetail.id'" value="'#{runmaintainPlanDetail.id?if_exists}'"/>
     </#if>
     <#if dailyRepair.id?exists>
       <@ww.hidden name="'dailyRepair.id'" value="#{dailyRepair.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('dailyRepair.fee')}'" name="'dailyRepair.fee'" value="'${dailyRepair.fee?if_exists}'" cssClass="'underline'" disabled="false"  required="true" />
	   </tr>
	   <tr>
	     <@ww.textarea label="'${action.getText('dailyRepair.content')}'" 
					   name="'dailyRepair.content'" 
					   value="'${dailyRepair.content?if_exists}'" rows="'3'" cols="'50'"
					   disabled="false" required="true" />
 	  	 <@ww.textarea label="'${action.getText('dailyRepair.comment')}'" 
		              name="'dailyRepair.comment'" 
		              value="'${dailyRepair.comment?if_exists}'" rows="'3'" cols="'50'"
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
       var fee = document.forms["dailyRepair"].elements["dailyRepair.fee"].value;
       if ('' == fee) {         //验证是否为空
         alert("${action.getText('dailyRepair.fee.requried')}");
         return false;
       } else if (!isDoubleNumber("dailyRepair.fee")){  //验证格式
         alert("${action.getText('dailyRepair.fee.format.error')}");
         return false;
       } else if (!isDoubleNumberBetweenBoolean(fee, 10000000001, 0)){  //验证范围
         alert("${action.getText('dailyRepair.fee.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证需求原因和备注的长度
     */
     function validateContentAndComment() {
        //验证内容是否为空,长度
       if ('' == document.forms["dailyRepair"].elements["dailyRepair.content"].value) {
         alert("${action.getText('dailyRepair.content.requried')}");
         return false;
       } else if (!isValidLength(document.forms["dailyRepair"],"dailyRepair.content",0,250)) {
           alert("${action.getText("dailyRepair.content.maxLength")}");
		   return false;
       }
	   //验证备注长度
	   if ('' != document.forms["dailyRepair"].elements["dailyRepair.comment"].value) {
	     if (!isValidLength(document.forms["dailyRepair"],"dailyRepair.comment",0,250)) {
	       alert("${action.getText("dailyRepair.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
   </script>
</@htmlPage>