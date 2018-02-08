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
<@htmlPage title="${action.getText('yearPlanProfile.title')}">
   <@ww.form namespace="'/year/tooling/yearPlan'" name="'yearPlan'" action="'saveYearPlan'" method="'post'" validate="true">
     <@ww.token name="saveYearPlanToken"/>
     <#if yearPlan.id?exists>
       <@ww.hidden name="'yearPlan.id'" value="#{yearPlan.id?if_exists}"/>
     </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('yearPlan.planNo')}'" name="'yearPlan.planNo'" value="'${yearPlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	 <@ww.textfield label="'${action.getText('yearPlan.name')}'" name="'yearPlan.name'" value="'${yearPlan.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	   </tr>
       <tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			     listKey="id" listValue="name" list="departments" 
    			     emptyOption="true" disabled="false" required="true">
    	 </@ww.select>
    	 <@pp.datePicker label="'${action.getText('yearPlan.year')}'" name="'yearPlan.year'"
 						 value="'${(yearPlan.year)?if_exists}'" cssClass="'underline'" size="15" 
 						 dateFormat="'%Y'" required="true" maxlength="4"/>
	   </tr>
	   <tr>
	     <#assign planCreatorName = ''/>
		 <#if yearPlan.planCreator?exists>
		   <#assign planCreatorName = "${yearPlan.planCreator.name}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorName = "${loginUser.name}" />
		 </#if>
	     <td align="right" valign="top">
	       <span class="required">*</span>
	       <label class="label">${action.getText('yearPlan.planCreator')}:</label>
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
		 <#if yearPlan.planCreator?exists>
		  <#assign planCreatorId = "#{yearPlan.planCreator.id}" />
		 <#elseif loginUser?exists>
		  <#assign planCreatorId = "#{loginUser.id}" /> 
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
    	 <@pp.datePicker label="'${action.getText('yearPlan.planCreatedDate')}'" name="'yearPlan.planCreatedDate'"
     					 value="'${(yearPlan.planCreatedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     					 required="true" maxlength="10"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('yearPlan.planAllFee')}'" name="'yearPlan.planAllFee'" value="'${yearPlan.planAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
       </tr>
     </@inputTable>
     <@buttonBar>
      <#if !(action.isReadOnly())>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
          <@ww.param name="'disabled'" value="${yearPlan.lockedFlag?string}"/>
       </@vsubmit>
       </#if>
       <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/tooling/yearPlan/listYearPlans.html?readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${eamAuthentication?if_exists}"/>
       <#if yearPlan.id?exists>
         <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_yearPlanPdf('#{yearPlan.id}')"/>
	     <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_yearPlanXls('#{yearPlan.id}')"/>
       </#if>
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
       <#if yearPlan.department?exists>
	     document.forms["yearPlan"].elements["department.id"].value=#{yearPlan.department.id?if_exists};
	   <#elseif loginUser.department?exists>
	     document.forms["yearPlan"].elements["department.id"].value = #{loginUser.department.id?if_exists};
	   </#if>
	   <#if yearPlan.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/tooling/yearPlan/listToolingMakeDetails.html?yearPlan.id=#{yearPlan.id}&detailType=TOOLING_MAKE&readOnly=${req.getParameter('readOnly')?if_exists}";
		  getObjByNameRe("toolingMakeDetail").className = "selectedtab";
	   <#else>
	     getCurrentlyDate(document.forms["yearPlan"], "yearPlan.planCreatedDate");   //设置编制日期默认值(当前日期)
	     getNextYear(document.forms["yearPlan"], "yearPlan.year");                   //设置年度的默认值(下一年)
	   </#if>
     }
     
      /*
       * 打印触发
      */
      function open_yearPlanXls(id){
         var yearPlanid=id;
         var url='${req.contextPath}/reports/yearPlan/yearPlanReportCollect.xls?yearPlanid='+yearPlanid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_yearPlanPdf(id){
         var yearPlanid=formatDigital(id);
         var url='${req.contextPath}/reports/yearPlan/yearPlanReportCollect.pdf?yearPlanid='+yearPlanid;
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
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
       //验证年度计划名称
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
       return true;
     }
     /*
      * 验证年度计划名称是否为空,以及长度验证
     */
     function validateName() {
       var name = document.forms["yearPlan"].elements["yearPlan.name"].value;
       if (name == '') {
         alert("${action.getText('yearPlan.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["yearPlan"], "yearPlan.name", null, 50)) {
         alert("${action.getText('yearPlan.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证部门是否为空
     */
     function validateDepartment() {
       var dept = document.forms["yearPlan"].elements["department.id"].value;
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
       var year = document.forms["yearPlan"].elements["yearPlan.year"].value;
       if ('' == year) {
         alert("${action.getText('yearPlan.year.requried')}");
         return false;
       } else if (!isYear(year)){
         alert("${action.getText('yearPlan.year.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证编制人是否为空
     */
     function validateCreator() {
       if ('' == document.forms["yearPlan"].elements["planCreator.id"].value) {    //验证编制人
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
	   if(document.forms["yearPlan"].elements["yearPlan.planCreatedDate"].value ==""){
		 alert("${action.getText('select.yearPlan.planCreatedDate')}");
		 return false;
	   }
	   //验证编制日期是否为日期型
	   var date=getObjByNameRe("yearPlan.planCreatedDate").value;
	   if(!isDate("yearPlan.planCreatedDate")){
	     alert("${action.getText('select.right.yearPlan.planCreatedDate')}");
		 return false;
	   }
	   <#if yearPlan.id?exists>
	   <#else>
	     if(isDateLessThenCurrent(date)){
		   alert("${action.getText('afresh.yearPlan.planCreatedDate')}");
		   return false;
		 }
	   </#if>
	   return true;
     }
   </script>
   <#if yearPlan.id?exists>
   	  <ul id="beautytab">
	    <li><a id="toolingMakeDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listToolingMakeDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN&detailType=TOOLING_MAKE&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.toolingMakeDetail')}</a></li>
	    <li><a id="sparePurchaseDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listSparePurchaseDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN&detailType=TOOLING_MAKE&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.sparePurchaseDetail')}</a></li>
	    <li><a id="repairMaintenanceDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listRepairMaintenanceDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN&detailType=TOOLING_MAKE&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.repairMaintenanceDetail')}</a></li>
	    <li><a id="techAlterDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/yearPlan/listTechAlterDetails.html?yearPlan.id=#{yearPlan.id}&yearQuarterFlag=YEAR_PLAN&detailType=TOOLING_MAKE&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.techAlterDetail')}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>