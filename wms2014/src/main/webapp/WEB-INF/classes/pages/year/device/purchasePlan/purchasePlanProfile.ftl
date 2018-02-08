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
<@htmlPage title="${action.getText('purchasePlanProfile.title')}">
   <@ww.form namespace="'/year/device/purchasePlan'" name="'purchasePlan'" action="'savePurchasePlan'" method="'post'" validate="true">
     <@ww.token name="savePurchasePlanToken"/>
     <#if purchasePlan.id?exists>
       <@ww.hidden name="'purchasePlan.id'" value="#{purchasePlan.id?if_exists}"/>
     </#if>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
     <@inputTable>
	   <tr>
		 <@ww.textfield label="'${action.getText('purchasePlan.planNo')}'" name="'purchasePlan.planNo'" value="'${purchasePlan.planNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
 	  	 <@ww.textfield label="'${action.getText('purchasePlan.name')}'" name="'purchasePlan.name'" value="'${purchasePlan.name?if_exists}'" cssClass="'underline'" required="true" disabled="false"/>
	   </tr>
       <tr>
		 <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
    			     listKey="id" listValue="name" list="departments" 
    			     emptyOption="true" disabled="false" required="true">
    	 </@ww.select>
    	 <@pp.datePicker label="'${action.getText('purchasePlan.year')}'" name="'purchasePlan.year'"
 						 value="'${(purchasePlan.year)?if_exists}'" cssClass="'underline'" size="15" 
 						 dateFormat="'%Y'" required="true" maxlength="4"/>
	   </tr>
	   <tr>
	     <#assign planCreatorName = ''/>
		 <#if purchasePlan.planCreator?exists>
		   <#assign planCreatorName = "${purchasePlan.planCreator.name}" />
		 <#elseif loginUser?exists>
		   <#assign planCreatorName = "${loginUser.name}" />
		 </#if>
	     <td align="right" valign="top">
	       <span class="required">*</span>
	       <label class="label">${action.getText('purchasePlan.planCreator')}:</label>
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
		 <#if purchasePlan.planCreator?exists>
		  <#assign planCreatorId = "#{purchasePlan.planCreator.id}" />
		 <#elseif loginUser?exists>
		  <#assign planCreatorId = "#{loginUser.id}" /> 
		 </#if>
		 <input type="hidden" name="planCreator.id" value="${planCreatorId}" />
    	 <@pp.datePicker label="'${action.getText('purchasePlan.planCreatedDate')}'" name="'purchasePlan.planCreatedDate'"
     					 value="'${(purchasePlan.planCreatedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
     					 required="true" maxlength="10"/>
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('purchasePlan.planAllFee')}'" name="'purchasePlan.planAllFee'" value="'${purchasePlan.planAllFee?if_exists}'" cssClass="'underline'" disabled="true"/>
         <@ww.textarea label="'${action.getText('purchasePlan.comment')}'" 
		              name="'purchasePlan.comment'" 
		              value="'${purchasePlan.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
       </tr>
     </@inputTable>
     <@buttonBar>
     <#if !(action.isReadOnly())>
     
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
       </#if>
       <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/year/device/purchasePlan/listPurchasePlans.html?eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
       <#if purchasePlan.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_purchasePlanDetailPdf('#{purchasePlan.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_purchasePlanDetailXls('#{purchasePlan.id}')"/>
	   </#if> 
     </@buttonBar>
   </@ww.form>
   <script language="JavaScript" type="text/JavaScript">
     window.onload = function () {
       <#--设备默认部门-->
	   <#if purchasePlan.department?exists>
	      document.forms["purchasePlan"].elements["department.id"].value=#{purchasePlan.department.id?if_exists};
	   <#elseif loginUser.department?exists>
	      <#--默认为当前登录用户部门-->
	      document.forms["purchasePlan"].elements["department.id"].value=#{loginUser.department.id};
	   </#if>
	   <#if purchasePlan.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/device/purchasePlan/listPurchasePlanDetails.html?purchasePlan.id=#{purchasePlan.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
		  document.getElementById("purchasePlanDetail").className = "selectedtab";
	   <#else>
	     getCurrentlyDate(document.forms["purchasePlan"], "purchasePlan.planCreatedDate");   //设置编制日期默认值(当前日期)
	     getNextYear(document.forms["purchasePlan"], "purchasePlan.year");                   //设置年度的默认值(下一年)
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
       //验证设备年度采购计划名称
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
       var name = document.forms["purchasePlan"].elements["purchasePlan.name"].value;
       if (name == '') {
         alert("${action.getText('purchasePlan.name.requried')}");
         return false;
       } else if(!isValidLength(document.forms["purchasePlan"], "purchasePlan.name", null, 50)) {
         alert("${action.getText('purchasePlan.name.maxLength')}");
         return false;
       }
       return true;
     }
     /*
      * 验证部门是否为空
     */
     function validateDepartment() {
       var dept = document.forms["purchasePlan"].elements["department.id"].value;
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
       var year = document.forms["purchasePlan"].elements["purchasePlan.year"].value;
       if ('' == year) {
         alert("${action.getText('purchasePlan.year.requried')}");
         return false;
       } else if (!isYear(year)){
         alert("${action.getText('purchasePlan.year.format.error')}");
         return false;
       }
       return true;
     }
     /*
      * 验证编制人是否为空
     */
     function validateCreator() {
       if ('' == document.forms["purchasePlan"].elements["planCreator.id"].value) {    //验证编制人
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
	   if(document.forms["purchasePlan"].elements["purchasePlan.planCreatedDate"].value ==""){
		 alert("${action.getText('select.purchasePlan.planCreatedDate')}");
		 return false;
	   }
	   //验证编制日期是否为日期型
	   if(!isDate("purchasePlan.planCreatedDate")){
	     alert("${action.getText('select.right.purchasePlan.planCreatedDate')}");
		 return false;
	   }
	   return true;
     }
     /*
      * 验证备注的长度
     */
     function validateComment() {
	   //验证备注长度
	   if ('' != document.forms["purchasePlan"].elements["purchasePlan.comment"].value) {
	     if (!isValidLength(document.forms["purchasePlan"],"purchasePlan.comment",0,250)) {
	       alert("${action.getText("purchasePlan.comment.maxLength")}");
		   return false;
	     }
	   }
	   return true;
     }
     function open_purchasePlanDetailXls(id){
       var purchasePlanid=id;
       var url='${req.contextPath}/reports/device/purchasePlanDetaillist.xls?purchasePlanid='+purchasePlanid;  
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
       }
      function open_purchasePlanDetailPdf(id){
       var purchasePlanid=id;
       var url='${req.contextPath}/reports/device/purchasePlanDetaillist.pdf?purchasePlanid='+purchasePlanid;  
       url = encodeURI(url);
         	window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
    }
   </script>
   <#if purchasePlan.id?exists>
     <ul id="beautytab">
       <li><a id="purchasePlanDetail" onclick="activeTab(this);" href="${req.contextPath}/year/device/purchasePlan/listPurchasePlanDetails.html?purchasePlan.id=#{purchasePlan.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('purchasePlanDetail')}</a></li>
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