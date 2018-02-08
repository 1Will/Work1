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

<@framePage title="${action.getText('repairFee.title')}">
     <@ww.form name="'fee'" action="'searchPreRepairPlanFees'" method="'post'">
     <@ww.token name="searchPreRepairPlanFeesToken"/>
     
     	<#if preRepairPlan?exists>
         	<@ww.hidden name="'preRepairPlan.id'" value="#{preRepairPlan.id}"/>
         </#if>
         <#if repairPlanOrProc?exists>
         	<@ww.hidden name="'repairPlanOrProc.id'" value="#{repairPlanOrProc.id}"/>
         </#if>
     	<#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			<@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
     		<#else>
     			<@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
     		</#if>
     	</#if>
     	<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     	<@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     	<@ww.hidden name="'allRepairFeeProcFee'" value="''"/>
     	<@ww.hidden name="'allRepairFeeId'" value="''"/>
     	<@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
     	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <#assign itemNo=1/>
	    <#assign loopNum=0/>
	    <#assign procFeeIdentify = 'procFee' + '${loopNum}'/>
	    <#assign sourceType=''/>
	    <#assign planAllFees  = 0/>
	    <#assign procAllFees  = 0/>
	        
	   <@list title="" excel=false setupTable=false 
	          includeParameters="preRepairPlanDetail.id|readOnly|repairPlanOrProcDetail.id|planProcFlag|preYearFlag" 
        	  fieldMap="like:" >
         <input type="hidden" name="repairFeeIds" value="#{object.id}"/>
	     <input type="hidden" name="feeItems" value="${object.feeItem?if_exists}"/>
	     <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
	         <@vlh.checkbox property="id" name="feeIds">
		       <@vlh.attribute name="width" value="30" />
		     </@vlh.checkbox>
		   </#if>
		 </#if>
		 <@vcolumn title="${action.getText('repairFee.serialNo')}">
		   <#if planProcFlag?exists>
	         <#if planProcFlag=='PLAN'>
	           <#if preYearFlag?exists>
			     <#if preYearFlag=='YEAR'>
				   <a href="#" onclick="fee_openDialog(#{repairPlanOrProcDetail.id},#{repairPlanOrProc.id},#{object.id});return false">#{itemNo}</a>
				 <#else>
			       <a href="#" onclick="fee_openDialog(#{preRepairPlanDetail.id},#{preRepairPlan.id},#{object.id});return false">#{itemNo}</a>
			     </#if>
			   </#if>
			 <#else>
			   #{itemNo}
			 </#if>
			</#if>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repairFee.feeItem')}" property="feeItem">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairFee.planFee')}" property="planFee">
	       <@alignRight/>
         </@vcolumn>
         <#if object.planFee?exists>
	       <#assign planAllFees  = planAllFees + object.planFee/>
		 </#if>
		 <#if planProcFlag?exists>
	       <#if planProcFlag=='PROC'>
	         <@vcolumn title="${action.getText('repairFee.procFee')}" property="planFee">
	           <input type="text" id="${procFeeIdentify}" name="proFee" 
			          class="underline"  value="${object.procFee?if_exists}"  maxlength="50"/>
             </@vcolumn>
             <#assign loopNum = loopNum+1/>
	    	 <#assign procFeeIdentify = 'procFee' + '${loopNum}'/>
	         <#if object.procFee?exists>
			   <#assign procAllFees  = procAllFees + object.procFee/>
			 </#if>
	       </#if>
	     </#if>
	     <@vcolumn title="${action.getText('repairFee.comment')}" property="comment">
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
			       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="fee_openDialog(#{repairPlanOrProcDetail.id},#{repairPlanOrProc.id},null);"/>
			    </#if>
			     <#else>
			     <#if !(action.isReadOnly())>
		           <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="fee_openDialog(#{preRepairPlanDetail.id},#{preRepairPlan.id},null);"/>
		        </#if>
		         </#if>
			   </#if>
			   <#if !(action.isReadOnly())>
		       <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('fee')}?" />
               <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                 <@ww.param name="'onclick'" value="'return confirmDeletes(\"feeIds\", \"${confirmMessage}\");'"/>
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
          window.onload = function() {
           <#if preRepairPlanDetail?exists>
              if (document.forms[0].elements["planProcFlag"].value == 'PLAN') {
                parent.document.getElementById("preRepairPlanDetail.allFee").value = '${preRepairPlanDetail.planAllFee?if_exists}';
              } else {
                parent.document.getElementById("PreRepairProcDetail.allFee").value = '${preRepairPlanDetail.procAllFee?if_exists}';
              }
            </#if>
            <#if repairPlanOrProcDetail?exists>
              if (document.forms[0].elements["planProcFlag"].value == 'PLAN') {
                parent.document.getElementById("repairPlanOrProcDetail.planDetailAllFee").value = '${repairPlanOrProcDetail.planDetailAllFee?if_exists}';
              } else {
                parent.document.getElementById("repairPlanOrProcDetail.procDetailAllFee").value = '${repairPlanOrProcDetail.procDetailAllFee?if_exists}';
              }
            </#if>
          }
	      function fee_openDialog(DetailId,PlanId,repairFeeId) {
	      	<#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     				var url = '${req.contextPath}/popup/editRepairFee.html?repairPlanOrProcDetail.id='+DetailId+'&repairPlanOrProc.id='+PlanId;
	     		<#else>
		        	var url = '${req.contextPath}/popup/editRepairFee.html?preRepairPlanDetail.id='+DetailId+'&preRepairPlan.id='+PlanId;
		        </#if>
	     	 </#if>
	        if (null !=repairFeeId) {
	        	url = url + '&repairFee.id=' +repairFeeId;
	        }
	        url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	        popupModalDialog(url,650,300); 
	        //self.location.reload();
	        <#if preYearFlag?exists>
	     		<#if preYearFlag=='YEAR'>
	     			self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanFees.html?repairPlanOrProcDetail.id='+DetailId+ 
	     			'&planProcFlag=' + '${planProcFlag?if_exists}'+'&repairPlanOrProc.id=' + PlanId+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;  
	     		<#else>
		        	self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanFees.html?preRepairPlanDetail.id='+DetailId+ 
		        	'&planProcFlag=' + '${planProcFlag?if_exists}'+'&preRepairPlan.id=' + PlanId+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;  
	       		</#if>
     	 </#if>
	       }
	       function validate() {
	         if (!validateProFeeFormate()) {
	           return false;
	         }
	         retrieveRepairFeeIdText();
	         retrieveRepairFeeProcFeeText();
	         return true;
	       }
	       function retrieveRepairFeeProcFeeText() {
             var allRepairFeeProcFee = document.getElementsByName("proFee");
             var allRepairFeeId = document.getElementsByName("repairFeeIds");
             var ary = new Array();
             for (var i=0; i<allRepairFeeId.length; i++) {
               if ('' != allRepairFeeProcFee[i].value) {
                 ary.push(allRepairFeeId[i].value);
                 ary.push(formatDigital(allRepairFeeProcFee[i].value));
               }
             }
             document.forms[0].elements["allRepairFeeProcFee"].value = ary;
           }
	       /*
	        * 获取列表中维修费用的ID
	       */
	       function retrieveRepairFeeIdText() {
		     var allRepairFeeId = document.getElementsByName("repairFeeIds");
		     var ary = new Array();
		     for (var i=0; i<allRepairFeeId.length; i++) {
		       ary.push(allRepairFeeId[i].value);
		     }
		     document.forms[0].elements["allRepairFeeId"].value = ary;
	       }
           function validateProFeeFormate() {
             var allRepairFeeItems = document.getElementsByName("feeItems");
             var allRepairFeeProcFee = document.getElementsByName("proFee");
             var allRepairFeeId = document.getElementsByName("repairFeeIds");
             for (var i=0; i<allRepairFeeId.length; i++) {
               var procFee = 'procFee' + i;
               if ('' != allRepairFeeProcFee[i].value) {
                 if (!isDoubleNumberBetweenBoolean(allRepairFeeProcFee[i].value,10000000001,0)) {
                 //  alert("${action.getText('repairFee.feeItem')} " + allRepairFeeItems[i].value + " ${action.getText('repairFee.procFee.formate')}")
                   alert("${action.getText('select.right.procFee')}");
                   return false;
                 }
               }
             }
			 return true;             
           }
	    </script>
</@framePage>