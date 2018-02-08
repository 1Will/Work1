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

<@framePage title="${action.getText('item.title')}">
     <@ww.form name="'listPreRepairPlanManHour'" action="'searchPreRepairPlanManHours'" method="'post'">
     <@ww.token name="searchPreRepairPlanManHoursToken"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
			     <@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
			<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     </#if>
     <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     <@ww.hidden name="'allRepairProcManHourNum'" value="''"/>
     <@ww.hidden name="'allRepairManHourId'" value="''"/>
     <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     
	 <#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
           includeParameters="preRepairPlanDetail.id|readOnly|repairPlanOrProcDetail.id|planProcFlag|preYearFlag" 
    	   fieldMap="like:" >
     <@ww.hidden name="'manHourIds'" value="'#{object.id}'"/>
     <#if planProcFlag?exists>
       <#if (planProcFlag=='PLAN')>
         <@vlh.checkbox property="id" name="repairManHourIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
	   </#if>
	 </#if>
	 <@vcolumn title="${action.getText('repairManHour.serialNo')}">
	   <#if planProcFlag?exists>
         <#if planProcFlag=='PLAN'>
           <#if preYearFlag?exists>
		     <#if preYearFlag=='YEAR'>
			   <a href="#" onclick="repairManHour_openDialog(#{repairPlanOrProcDetail.id},#{object.id});return false;">#{itemNo}</a>
			 <#else>
		       <a href="#" onclick="repairManHour_openDialog(#{preRepairPlanDetail.id},#{object.id});return false;">#{itemNo}</a>
		     </#if>
		   </#if>
		 <#else>
		   #{itemNo}
		 </#if>
		</#if>
       <@alignCenter/>
     </@vcolumn>
     <#assign itemNo = itemNo+1/>
     <@vcolumn title="${action.getText('repairManHour.workType')}" property="workType.name">
       <@alignLeft/>
     </@vcolumn>
     <@vcolumn title="${action.getText('workType.unitPrice')}" property="unitPrice" format="#,###,##0.##">
       <@alignRight/>
     </@vcolumn>
     <@vcolumn title="${action.getText('repairManHour.manHourNum')}" property="manHourNum" format="#,###,##0.##">
       <@alignRight/>
     </@vcolumn>
     <#assign planAllPrice=''/>
	   <#if object.unitPrice?exists && object.manHourNum?exists>
	     <#assign planAllPrice=object.manHourNum * object.unitPrice/>
	   </#if>
     <@vcolumn title="${action.getText('repairManHour.allPrice')}">
       ${planAllPrice?string('#,###,##0.##')}
       <@alignRight/>
     </@vcolumn>
     <#if planProcFlag?exists>
       <#if planProcFlag=='PROC'>
         <@vcolumn title="${action.getText('repairManHour.procManHourNum')}">
           <input type="text" name="procManHourNum" 
		          class="underline"  value="${(object.procManHourNum?string('#,###,##0.##'))?if_exists}"  maxlength="50"/>
         </@vcolumn>
         <#assign procAllPrice=''/>
		   <#if object.unitPrice?exists && object.procManHourNum?exists>
		     <#assign procAllPrice=object.procManHourNum * object.unitPrice/>
		   </#if>
         <@vcolumn title="${action.getText('repairManHour.procAllPrice')}">
           ${procAllPrice?string('#,###,##0.##')}
           <@alignRight/>
         </@vcolumn>
       </#if>
     </#if>
     <@vcolumn title="${action.getText('repairManHour.comment')}" property="comment">
       <@alignLeft/>
     </@vcolumn>
    </@list>
    <#if !first>
      <#if planProcFlag?exists>
        <#if planProcFlag=='PLAN'>
 	      <@buttonBar>
 	    	<#if preYearFlag?exists>
	     		<#if preYearFlag=='YEAR'>
	     		<#if !(action.isReadOnly())>
	     			<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairManHour_openDialog(#{repairPlanOrProcDetail.id},null);"/>
	     		</#if>
	     		<#else>
	     		<#if !(action.isReadOnly())>
	     			<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairManHour_openDialog(#{preRepairPlanDetail.id},null);"/>
	     		</#if>
	     		</#if>
	     	</#if>
	     	<#if !(action.isReadOnly())>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairManHour')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
              <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairManHourIds\", \"${confirmMessage}\");'"/>
              <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            </#if>
          </@buttonBar>
        <#else>
          <@buttonBar>
          <#if !(action.isReadOnly())>
      	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'">
      	      <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
      	    </@vsubmit>
      	    </#if>
      	  </@buttonBar>
        </#if>
      </#if>
    </#if>
     </@ww.form>
     <script language="javascript">
	   function repairManHour_openDialog(DetailId, repairManHourId) {
	   	 	<#if preYearFlag=='YEAR'>
     			 var url = '${req.contextPath}/popup/editRepairManHour.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=' + DetailId;
     		<#else>
     			 var url = '${req.contextPath}/popup/editRepairManHour.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=' + DetailId;
     		</#if>
	     if (null != repairManHourId) {
	       url = url + '&repairManHour.id=' + repairManHourId;
	     } 
	     url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,600,300); 
	     <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanManHours.html?repairPlanOrProcDetail.id='+DetailId +
     			'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value; 
     		<#else>
     			self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanManHours.html?preRepairPlanDetail.id='+DetailId +
     			'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value; 
     		</#if>
     	 </#if>
	   }
		
	   //获取列表中实际工时数量的值
       function retrieveRepairProcManHourNumText() {
         var allRepairProcManHourNum = document.getElementsByName("procManHourNum");
         var allRepairProcId = document.getElementsByName("manHourIds");
         var ary = new Array();
         for (var i=0; i<allRepairProcId.length; i++) {
           if ('' != allRepairProcManHourNum[i].value) {
             if (!validateProcManHourNum(allRepairProcManHourNum[i].value)) {
               return false;
             }
             ary.push(allRepairProcId[i].value);
             ary.push(formatDigital(allRepairProcManHourNum[i].value));
           }
         }
         document.forms[0].elements["allRepairProcManHourNum"].value = ary;
         return true;
       }
       //获取列表中详细的ID
       function retrieveRepairManHourIdText() {
	     var allRepairManHourId = document.getElementsByName("manHourIds");
		 var ary = new Array();
		 for (var i=0; i<allRepairManHourId.length; i++) {
		   ary.push(allRepairManHourId[i].value);
		 }
		 document.forms[0].elements["allRepairManHourId"].value = ary;
	   }
	   
	   function validate() {
	     retrieveRepairManHourIdText();      //获取列表中详细的ID          
	     if (!retrieveRepairProcManHourNumText()) {       //获取列表中实际工时数量的值    
	       return false;
	     }
	     return true;
	   }
	   //验证实际工时数量格式
   	   function validateProcManHourNum(usedNumValue){
	     usedNumValue = formatDigital(usedNumValue);
	     var control = isNumberBetween(usedNumValue,1000000001,0);
		 if(control!=1){
           alert("${action.getText('select.right.procManHourNum')}");
           return false;
         }
		 return true;
	   }
	</script>
</@framePage>