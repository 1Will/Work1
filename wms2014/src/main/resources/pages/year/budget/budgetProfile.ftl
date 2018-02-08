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
<#assign budgetProfileTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign budgetProfileTitle = "${action.getText('deviceBudgetSeacherProfile.title')}"/>
  <#else>
    <#assign budgetProfileTitle = "${action.getText('toolingBudgetSeacherProfile.title')}"/>
  </#if>
</#if>
<@htmlPage title="${budgetProfileTitle}">
   <@ww.form namespace="'/year/budget'" name="'yearBudget'" action="'saveYearBudget'" method="'post'" validate="true">
     <@ww.token name="saveYearBudgetToken"/>
     <#if budget.id?exists>
       <@ww.hidden name="'budget.id'" value="#{budget.id?if_exists}"/>
     </#if>
     <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('budget.yearBudgetNo')}'" name="'budget.yearBudgetNo'" value="'${budget.yearBudgetNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	 <@ww.textfield label="'${action.getText('budget.name')}'" name="'budget.name'" value="'${budget.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	   </tr>
       <tr>
    	 <@pp.datePicker label="'${action.getText('budget.year')}'" name="'budget.year'"
 						 value="'${(budget.year)?if_exists}'" cssClass="'underline'" size="15" 
 						 dateFormat="'%Y'" required="true" maxlength="4"/>
 	     <@ww.textfield label="'${action.getText('budget.allFee')}'" name="'budget.allFee'" value="'${budget.allFee?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
	   </tr>
	   <tr>
	     <#assign planCreatorName = ''/>
		 <#if budget.planCreator?exists>
		   <#assign planCreatorName = "${budget.planCreator.name}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorName = "${loginUser.name}" />
		 </#if>
	     <td align="right" valign="top">
	       <span class="required">*</span>
	       <label class="label">${action.getText('budget.planCreator')}:</label>
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
		 <#if budget.planCreator?exists>
		  <#assign planCreatorId = "#{budget.planCreator.id}" />
		 <#elseif loginUser?exists>
		  <#assign planCreatorId = "#{loginUser.id}" /> 
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
    	 <@pp.datePicker label="'${action.getText('budget.planCreatedDate')}'" name="'budget.planCreatedDate'"
     					 value="'${(budget.planCreatedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     					 required="true" maxlength="10"/>
       </tr>
     </@inputTable>
     <@buttonBar>
       <#if !(action.isReadOnly())>
         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
       </#if>
       <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/budget/listYearBudgets.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
	   <#if budget.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/budget/listYearBudgetDetails.html?budget.id=#{budget.id}&toolingDevFlag=${toolingDevFlag}&readOnly=${req.getParameter('readOnly')?if_exists}";
		  getObjByNameRe("budgetDetail").className = "selectedtab";
	   <#else>
	    getCurrentlyDate(document.forms["yearBudget"], "budget.planCreatedDate");   //设置编制日期默认值(当前日期)
	    getNextYear(document.forms["yearBudget"], "budget.year");                   //设置年度的默认值(下一年)
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
       //验证年度预算名称
       if (!validateName()) {
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
       return true;
     }
     /*
      * 验证年度预算名称是否为空,以及长度验证
     */
     function validateName() {
       var name = document.forms["yearBudget"].elements["budget.name"].value;
       if (name == '') {
         alert("${action.getText('budget.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["yearBudget"], "budget.name", null, 50)) {
         alert("${action.getText('budget.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证年度是否为空,格式是否正确
     */
     function validateYear() {
       var year = document.forms["yearBudget"].elements["budget.year"].value;
       if ('' == year) {
         alert("${action.getText('budget.year.requried')}");
         return false;
       } else if (!isYear(year)){
         alert("${action.getText('budget.year.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证编制人是否为空
     */
     function validateCreator() {
       if ('' == document.forms["yearBudget"].elements["planCreator.id"].value) {    //验证编制人
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
	   if(document.forms["yearBudget"].elements["budget.planCreatedDate"].value == ""){
		 alert("${action.getText('select.budget.planCreatedDate')}");
		 return false;
	   }
	   //验证编制日期是否为日期型
	   var date = document.forms["yearBudget"].elements["budget.planCreatedDate"].value;
	   if(!isDate("budget.planCreatedDate")){
	     alert("${action.getText('select.right.budget.planCreatedDate')}");
		 return false;
	   }
	   <#if budget.id?exists>
	   <#else>
		  if(isDateLessThenCurrent(date)){
		    alert("${action.getText('afresh.budget.planCreatedDate')}");
		    return false;
		  }
		</#if>
	   return true;
     }
   </script>
   <#if budget.id?exists>
   	  <ul id="beautytab">
	    <li><a id="budgetDetail" onclick="activeTab(this);" href="${req.contextPath}/year/budget/listYearBudgetDetails.html?budget.id=#{budget.id}&toolingDevFlag=${toolingDevFlag}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearBudgetDetail')}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>