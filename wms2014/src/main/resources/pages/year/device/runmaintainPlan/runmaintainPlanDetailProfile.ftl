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
<@htmlPage title="${action.getText('runmaintainPlanDetailProfile.title')}">
 <base target="_self">
   <@ww.form namespace="'/popup'" name="'runmaintainPlanDetail'" action="'saveRunmaintainPlanDetail'" method="'post'" validate="true">
     <@ww.token name="saveRunmaintainPlanDetailToken"/>
     <#if runmaintainPlan.id?exists>
       <@ww.hidden name="'runmaintainPlan.id'" value="'#{runmaintainPlan.id?if_exists}'"/>
     </#if>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if runmaintainPlanDetail.id?exists>
       <@ww.hidden name="'runmaintainPlanDetail.id'" value="#{runmaintainPlanDetail.id?if_exists}"/>
     </#if>
     <#if runmaintainPlanDetail.device?exists>
       <@ww.hidden name="'device.id'" value="#{runmaintainPlanDetail.device.id?if_exists}"/>
     </#if>
     <@inputTable>
	   <tr>
	     <#assign deviceNo=''/>
	     <#assign deviceName=''/>
	     <#if runmaintainPlanDetail.device?exists>
	       <#assign deviceNo="${runmaintainPlanDetail.device.deviceNo?if_exists}"/>
	       <#assign deviceName="${runmaintainPlanDetail.device.name?if_exists}"/>
	     </#if>
		 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.deviceNo')}'" name="'runmaintainPlanDetail.deviceNo'" value="'${deviceNo}'" cssClass="'underline'" disabled="true"  required="false"/>
		 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.deviceName')}'" name="'runmaintainPlanDetail.deviceName'" value="'${deviceName}'" cssClass="'underline'" disabled="true"  required="flase" />
	   </tr>
       <tr>
       	 <#assign deviceSpecification=''/>
	     <#assign deviceModel=''/>
	     <#if runmaintainPlanDetail.device?exists>
	       <#assign deviceSpecification="${runmaintainPlanDetail.device.specification?if_exists}"/>
	       <#assign deviceModel="${runmaintainPlanDetail.device.model?if_exists}"/>
	     </#if>
         <@ww.textfield label="'${action.getText('runmaintainPlanDetail.deviceSpecification')}'" name="'purchasePlanDetail.model'" value="'${deviceSpecification}'" cssClass="'underline'" disabled="true"/>
    	 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.deviceModel')}'" name="'purchasePlanDetail.unitPrice'" value="'${deviceModel}'" cssClass="'underline'" disabled="true" required="false"/>
	   </tr>
	   <tr>
	     <@ww.textfield label="'${action.getText('runmaintainPlanDetail.dailyRepairFee')}'" name="'runmaintainPlanDetail.dailyRepairFee'" value="'${runmaintainPlanDetail.dailyRepairFee?if_exists}'" cssClass="'underline'" disabled="true" required="false"/>
    	 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.pricisionCheckFee')}'" name="'runmaintainPlanDetail.pricisionCheckFee'" value="'${runmaintainPlanDetail.pricisionCheckFee?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
       </tr>
       <tr>
         <@ww.textfield label="'${action.getText('runmaintainPlanDetail.changeFee')}'" name="'runmaintainPlanDetail.changeFee'" value="'${runmaintainPlanDetail.changeFee?if_exists}'" cssClass="'underline'" disabled="true" required="false"/>
    	 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.pivotalSpareFee')}'" name="'runmaintainPlanDetail.pivotalSpareFee'" value="'${runmaintainPlanDetail.pivotalSpareFee?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
       </tr>
       <tr>
    	 <@ww.textfield label="'${action.getText('runmaintainPlanDetail.allFee')}'" name="'runmaintainPlanDetail.allFee'" value="'${runmaintainPlanDetail.allFee?if_exists}'" cssClass="'underline'" disabled="true" required="false" />
 		 <@ww.textarea label="'${action.getText('runmaintainPlanDetail.comment')}'" 
		              name="'runmaintainPlanDetail.comment'" 
		              value="'${runmaintainPlanDetail.comment?if_exists}'" rows="'3'" cols="'50'"
		              disabled="false" />
       </tr>
     </@inputTable>
     <@buttonBar>
       <#if !(action.isReadOnly())>
       <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
       </#if>
       <input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
     </@buttonBar>
   </@ww.form>
   <script language="javascript">
     window.onload = function () {
	   <#if runmaintainPlanDetail.id?exists>
	      <#-- 初始载入帧页面-->
	      document.all.frame.src = "${req.contextPath}/year/device/runmaintainPlan/listDailyRepairs.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}";
		  getObjByNameRe("dailyRepair").className = "selectedtab";
	   </#if>
     }
     function validate() {
       	//验证备注长度
	   if ('' != document.forms["runmaintainPlanDetail"].elements["runmaintainPlanDetail.comment"].value) {
	     if (!isValidLength(document.forms["runmaintainPlanDetail"],"runmaintainPlanDetail.comment",0,250)) {
	       alert("${action.getText("runmaintainPlanDetail.comment.maxLength")}");
		   return false;
	     }
	   }
     }
   </script>
   <#if runmaintainPlanDetail.id?exists>
     <ul id="beautytab">
       <li><a id="dailyRepair" onclick="activeTab(this);" href="${req.contextPath}/year/device/runmaintainPlan/listDailyRepairs.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}" target="frame">${action.getText('dailyRepair')}</a></li>
       <li><a id="pricisionCheck" onclick="activeTab(this);" href="${req.contextPath}/year/device/runmaintainPlan/listPricisionChecks.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}" target="frame">${action.getText('pricisionCheck')}</a></li>
       <li><a id="deviceChange" onclick="activeTab(this);" href="${req.contextPath}/year/device/runmaintainPlan/listDeviceChanges.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}" target="frame">${action.getText('deviceChange')}</a></li>
       <li><a id="pivotalSpare" onclick="activeTab(this);" href="${req.contextPath}/year/device/runmaintainPlan/listPivotalSpares.html?readOnly=${req.getParameter('readOnly')?if_exists}&runmaintainPlanDetail.id=#{runmaintainPlanDetail.id}" target="frame">${action.getText('pivotalSpare')}</a></li>
     </ul>
     <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   </#if>
</@htmlPage>