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
<@htmlPage title="${action.getText('quarterPlanProfile.title')}">
   <@ww.form namespace="'/year/tooling/quarterPlan'" name="'quarterPlan'" action="'saveQuarterPlan'" method="'post'" validate="true">
     <@ww.token name="saveQuarterPlanToken"/>
     <#if quarterPlan.id?exists>
       <@ww.hidden name="'quarterPlan.id'" value="#{quarterPlan.id?if_exists}"/>
     </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('quarterPlan.planNo')}'" name="'quarterPlan.planNo'" value="'${quarterPlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	 <@ww.textfield label="'${action.getText('quarterPlan.name')}'" name="'quarterPlan.name'" value="'${quarterPlan.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	   </tr>
       <tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			     listKey="id" listValue="name" list="departments" 
    			     emptyOption="true" disabled="false" required="true">
    	 </@ww.select>
    	 <@pp.datePicker label="'${action.getText('quarterPlan.year')}'" name="'quarterPlan.year'"
 						 value="'${(quarterPlan.year)?if_exists}'" cssClass="'underline'" size="15" 
 						 dateFormat="'%Y'" required="true" maxlength="4"/>
	   </tr>
	   <tr>
	      <@ww.select label="'${action.getText('quarterPlan.qarterType')}'" required="false" name="'qarterType.id'" 
    		          value="'${req.getParameter('qarterType.id')?if_exists}'" listKey="value" listValue="label"
                      list="qarterTypes" emptyOption="true" disabled="false" required="true">
          </@ww.select>
	     <#assign planCreatorName = ''/>
		 <#if quarterPlan.planCreator?exists>
		   <#assign planCreatorName = "${quarterPlan.planCreator.name}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorName = "${loginUser.name}" />
		 </#if>
	     <td align="right" valign="top">
	       <span class="required">*</span>
	       <label class="label">${action.getText('quarterPlan.planCreator')}:</label>
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
		 <#if quarterPlan.planCreator?exists>
		  <#assign planCreatorId = "#{quarterPlan.planCreator.id}" />
		 <#elseif loginUser?exists>
		  <#assign planCreatorId = "#{loginUser.id}" /> 
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
       </tr>
       <tr>
         <@pp.datePicker label="'${action.getText('quarterPlan.planCreatedDate')}'" name="'quarterPlan.planCreatedDate'"
     					 value="'${(quarterPlan.planCreatedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     					 required="true" maxlength="10"/>
         <@ww.textfield label="'${action.getText('quarterPlan.planAllFee')}'" name="'quarterPlan.planAllFee'" value="'${(quarterPlan.planAllFee?string('#,###,##0.00'))?if_exists}'" cssClass="'underline'" disabled="true"/>
       </tr>
     </@inputTable>
     <@buttonBar>
      <#if !(action.isReadOnly())>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
    </#if>
       <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/tooling/quarterPlan/listQuarterPlans.html?readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}"/>
        <#if quarterPlan.id?exists>
         <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_quarterPlanPdf('#{quarterPlan.id}')"/>
	     <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_quarterPlanXls('#{quarterPlan.id}')"/>
       </#if>
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
   
     window.onload = function () {
       <#if quarterPlan.department?exists>
	     document.forms["quarterPlan"].elements["department.id"].value=#{quarterPlan.department.id?if_exists};
	   <#elseif loginUser.department?exists>
	     document.forms["quarterPlan"].elements["department.id"].value= #{loginUser.department.id};
	   </#if>
	   <#if quarterPlan.qarterType?exists>
	     document.forms["quarterPlan"].elements["qarterType.id"].value='${quarterPlan.qarterType?if_exists}';
	   </#if>
	   <#if quarterPlan.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/tooling/quarterPlan/listToolingMakeDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN&readOnly=${req.getParameter('readOnly')?if_exists}";
		  getObjByNameRe("toolingMakeDetail").className = "selectedtab";
	   <#else>
	     getCurrentlyDate(document.forms["quarterPlan"], "quarterPlan.planCreatedDate");   //设置编制日期默认值(当前日期)
	     getCurrentYear(document.forms["quarterPlan"], "quarterPlan.year");                   //设置年度的默认值(本年度)
	   	 setDefaultQuarterType();
	   </#if>
     }
     
      /*
       * 打印触发
      */
      function open_quarterPlanXls(id){
         var quarterPlanid=id;
         var url='${req.contextPath}/reports/quarter/quarterDetailPlanReportCollect.xls?quarterPlanid='+quarterPlanid;  
         url = encodeURI(url);
         window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
       }
       function open_quarterPlanPdf(id){
         var quarterPlanid=formatDigital(id);
         var url='${req.contextPath}/reports/quarter/quarterDetailPlanReportCollect.pdf?quarterPlanid='+quarterPlanid;
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
       //验证季度是否输入
       if ('' == document.forms["quarterPlan"].elements["qarterType.id"].value) {
         alert("${action.getText('qarterType.id.requried')}");
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
      * 验证季度计划名称是否为空,以及长度验证
     */
     function validateName() {
       var name = document.forms["quarterPlan"].elements["quarterPlan.name"].value;
       if (name == '') {
         alert("${action.getText('quarterPlan.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["quarterPlan"], "quarterPlan.name", null, 50)) {
         alert("${action.getText('quarterPlan.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证部门是否为空
     */
     function validateDepartment() {
       var dept = document.forms["quarterPlan"].elements["department.id"].value;
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
       var year = document.forms["quarterPlan"].elements["quarterPlan.year"].value;
       if ('' == year) {
         alert("${action.getText('quarterPlan.year.requried')}");
         return false;
       } else if (!isYear(year)){
         alert("${action.getText('quarterPlan.year.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证编制人是否为空
     */
     function validateCreator() {
       if ('' == document.forms["quarterPlan"].elements["planCreator.id"].value) {    //验证编制人
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
	   if(document.forms["quarterPlan"].elements["quarterPlan.planCreatedDate"].value ==""){
		 alert("${action.getText('select.quarterPlan.planCreatedDate')}");
		 return false;
	   }
	   //验证编制日期是否为日期型
	   var date = document.forms["quarterPlan"].elements["quarterPlan.planCreatedDate"].value;
	   if(!isDate("quarterPlan.planCreatedDate")){
	     alert("${action.getText('select.right.quarterPlan.planCreatedDate')}");
		 return false;
	   }
	    <#if quarterPlan.id?exists>
		<#else>
		  if(isDateLessThenCurrent(date)){
		    alert("${action.getText('afresh.quarterPlan.planCreatedDate')}");
		    return false;
		  }
		</#if>
	   return true;
     }
     
     /*
     * 设置季度类型的默认季度
     */
     function setDefaultQuarterType() {
       var currentDate = new Date();
       var currentMonth = currentDate.getMonth()+1;
       if (currentMonth>=1&&currentMonth<=3) {
        document.forms["quarterPlan"].elements["qarterType.id"].value='SECOND_QUARTER';
       }
       if (currentMonth>=4&&currentMonth<=6) {
        document.forms["quarterPlan"].elements["qarterType.id"].value='THIRD_QUARTER';
       }
       if (currentMonth>=7&&currentMonth<=9) {
        document.forms["quarterPlan"].elements["qarterType.id"].value='FOURTH_QUARTER';
       }
       if (currentMonth>=10&&currentMonth<=12) {
        document.forms["quarterPlan"].elements["qarterType.id"].value='FIRST_QUARTER';
       }
     }
   </script>
   <#if quarterPlan.id?exists>
      <table align="center" height="2%">
        <tr id="a">
         <td>
          <font id="warnningMessage" color="red"></font>
         </td>
        </tr>
      </table>
   	  <ul id="beautytab">
	    <li><a id="toolingMakeDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/quarterPlan/listToolingMakeDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.toolingMakeDetail')}</a></li>
	    <li><a id="sparePurchaseDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/quarterPlan/listSparePurchaseDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.sparePurchaseDetail')}</a></li>
	    <li><a id="repairMaintenanceDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/quarterPlan/listRepairMaintenanceDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.repairMaintenanceDetail')}</a></li>
	    <li><a id="techAlterDetail" onclick="activeTab(this);" href="${req.contextPath}/year/tooling/quarterPlan/listTechAlterDetails.html?quarterPlan.id=#{quarterPlan.id}&yearQuarterFlag=QUARTER_PLAN&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('yearPlan.techAlterDetail')}</a></li>
	  </ul>
	  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>