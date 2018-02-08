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
<@htmlPage title="${action.getText('runmaintanPlanProfile.title')}">
   <@ww.form namespace="'/year/device/runmaintainPlan'" name="'runmaintainPlan'" action="'saveRunmaintainPlan'" method="'post'" validate="true">
     <@ww.token name="saveRunmaintainPlanToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if runmaintainPlan.id?exists>
       <@ww.hidden name="'runmaintainPlan.id'" value="#{runmaintainPlan.id?if_exists}"/>
     </#if>
     
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('runmaintainPlan.planNo')}'" name="'runmaintainPlan.planNo'" value="'${runmaintainPlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	 <@ww.textfield label="'${action.getText('runmaintainPlan.name')}'" name="'runmaintainPlan.name'" value="'${runmaintainPlan.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	   </tr>
       <tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			     listKey="id" listValue="name" list="departments" 
    			     emptyOption="true" disabled="false" required="true">
    	 </@ww.select>
    	 <@pp.datePicker label="'${action.getText('runmaintainPlan.year')}'" name="'runmaintainPlan.year'"
 						 value="'${(runmaintainPlan.year)?if_exists}'" cssClass="'underline'" size="15" 
 						 dateFormat="'%Y'" required="true" maxlength="4"/>
	   </tr>
	   <tr>
	     <#assign planCreatorName = ''/>
		 <#if runmaintainPlan.planCreator?exists>
		   <#assign planCreatorName = "${runmaintainPlan.planCreator.name}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorName = "${loginUser.name}" />
		 </#if>
	     <td align="right" valign="top">
	       <span class="required">*</span>
	       <label class="label">${action.getText('runmaintainPlan.planCreator')}:</label>
	     </td>
	     <td>
		   <input type="text" name="planCreator.name" 
			      class="underline"  value="${planCreatorName}"  maxlength="140" size="20" disabled="true"/>
	       <label id=""></label>
	       <a onClick="planCreator_OpenDialog();">
    	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
    	   </a>
		 </td>
         <#assign planCreatorId = ''/>
		 <#if runmaintainPlan.planCreator?exists>
		  <#assign planCreatorId = "#{runmaintainPlan.planCreator.id}" />
		 <#elseif loginUser?exists>
		  <#assign planCreatorId = "#{loginUser.id}" /> 
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
    	 <@pp.datePicker label="'${action.getText('runmaintainPlan.planCreatedDate')}'" name="'runmaintainPlan.planCreatedDate'"
     					 value="'${(runmaintainPlan.planCreatedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     					 required="true" maxlength="10"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('runmaintainPlan.planAllFee')}'" name="'runmaintainPlan.planAllFee'" value="'${runmaintainPlan.planAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
         <@ww.textarea label="'${action.getText('runmaintainPlan.comment')}'" 
		              name="'runmaintainPlan.comment'" 
		              value="'${runmaintainPlan.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
       </tr>
     </@inputTable>
     <@buttonBar>
     <#if !(action.isReadOnly())>
         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
     </#if>
          
       <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/device/runmaintainPlan/listRunmaintainPlans.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
	   <#if runmaintainPlan.department?exists>
	      document.forms["runmaintainPlan"].elements["department.id"].value=#{runmaintainPlan.department.id?if_exists};
	   <#elseif loginUser.department?exists>
	      document.forms["runmaintainPlan"].elements["department.id"].value=#{loginUser.department.id};
	   </#if>
	   <#if runmaintainPlan.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/device/runmaintainPlan/listRunmaintainPlanDetails.html?runmaintainPlan.id=#{runmaintainPlan.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
		  document.getElementById("runmaintainPlanDetail").className = "selectedtab";
	   <#else>
	     getCurrentlyDate(document.forms["runmaintainPlan"], "runmaintainPlan.planCreatedDate");   //设置编制日期默认值(当前日期)
	     getNextYear(document.forms["runmaintainPlan"], "runmaintainPlan.year");                   //设置年度的默认值(下一年)
	   </#if>
     }
    /*
	 * 编制人选择
	*/
	 function planCreator_OpenDialog () {
	   var url = "${req.contextPath}/popup/userSelector.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	 }
	 function creatorSelectorHandler(result) {
	   if (null != result) {
	     document.forms[0].elements["planCreator.id"].value = result[0];
		 document.forms[0].elements["planCreator.name"].value = result[1];
	   }
	 }
     function validate() {
       //验证设备年度运维计划名称
       if (!validateName()) {
         return false;
       }
       //验证部门
       if (!validateDepartment()) {
         return false;
       }
       //验证年度
       if (!validateYear()) {
         return false;
       }
       //验证编制人
       if (!validateCreator()) {
         return false;
       }
       //验证编制日期
       if (!validateCreateDate()) {
         return false;
       }
       //验证备注
       if (!validateComment()) {
         return false;
       }
       return true;
     }
     /*
      * 验证验证设备年度采购计划名称是否为空,以及长度验证
     */
     function validateName() {
       var name = document.forms["runmaintainPlan"].elements["runmaintainPlan.name"].value;
       if (name == '') {
         alert("${action.getText('runmaintainPlan.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["runmaintainPlan"], "runmaintainPlan.name", null, 50)) {
         alert("${action.getText('runmaintainPlan.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证部门是否为空
     */
     function validateDepartment() {
       var dept = document.forms["runmaintainPlan"].elements["department.id"].value;
       if (dept =='' || dept == '-1') {
         alert("${action.getText('department.id.requried')}");
         return false;
       }
       return true;
     }
     /*
      * 验证年度是否为空,格式是否正确
     */
     function validateYear() {
       var year = document.forms["runmaintainPlan"].elements["runmaintainPlan.year"].value;
       if ('' == year) {
         alert("${action.getText('runmaintainPlan.year.requried')}");
         return false;
       } else if (!isYear(year)){
         alert("${action.getText('runmaintainPlan.year.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证编制人是否为空
     */
     function validateCreator() {
       if ('' == document.forms["runmaintainPlan"].elements["planCreator.id"].value) {    //验证编制人
	     alert("${action.getText('planCreator.id.required')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证编制日期是否为空,以及格式是否正确
     */
     function validateCreateDate() {
       //验证编制日期是否为空
	   if(document.forms["runmaintainPlan"].elements["runmaintainPlan.planCreatedDate"].value ==""){
		 alert("${action.getText('select.runmaintainPlan.planCreatedDate')}");
		 return false;
	   }
	   //验证编制日期是否为日期型
	   var date = document.forms["runmaintainPlan"].elements["runmaintainPlan.planCreatedDate"].value;
	   if(!isDate("runmaintainPlan.planCreatedDate")){
	     alert("${action.getText('select.right.runmaintainPlan.planCreatedDate')}");
		 return false;
	   }
	   <#if runmaintainPlan.id?exists>
	   <#else>
		  if(isDateLessThenCurrent(date)){
		    alert("${action.getText('afresh.runmaintainPlan.planCreatedDate')}");
		    return false;
		  }
		</#if>
	   return true;
     }
     /*
      * 验证备注的长度
     */
     function validateComment() {
	   //验证备注长度
	   if ('' != document.forms["runmaintainPlan"].elements["runmaintainPlan.comment"].value) {
	     if (!isValidLength(document.forms["runmaintainPlan"],"runmaintainPlan.comment",0,250)) {
	       alert("${action.getText("runmaintainPlan.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
   </script>
   <#if runmaintainPlan.id?exists>
     <ul id="beautytab">
       <li><a id="runmaintainPlanDetail" onclick="activeTab(this);" href="${req.contextPath}/year/device/runmaintainPlan/listRunmaintainPlanDetails.html?runmaintainPlan.id=#{runmaintainPlan.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('runmaintainPlanDetail')}</a></li>
     </ul>
     <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
   <#--
   <#if purchasePlan.id?exists>listPurchasePlanDetails
   	  <ul id="beautytab">
	    <li><a id="toolingMakeDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listToolingMakeDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN" target="frame">${action.getText('yearPlan.toolingMakeDetail')}</a></li>
	    <li><a id="sparePurchaseDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listSparePurchaseDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN" target="frame">${action.getText('yearPlan.sparePurchaseDetail')}</a></li>
	    <li><a id="repairMaintenanceDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listRepairMaintenanceDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN" target="frame">${action.getText('yearPlan.repairMaintenanceDetail')}</a></li>
	    <li><a id="techAlterDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listTechAlterDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN" target="frame">${action.getText('yearPlan.techAlterDetail')}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
   -->
</@htmlPage>