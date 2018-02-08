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
     <@ww.form  name="'listRepairTool'" action="'searchPreRepairPlanTools'" method="'post'">
     <@ww.token name="searchPreRepairPlanToolsToken"/>
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
     <input type="hidden" name="allRepairToolProcUseNum" value=""/>
     <input type="hidden" name="allRepairToolId" value=""/>
	 <@ww.hidden name="'pagingPage'" value="'${pagingPage?if_exists}'"/>
	
	 <#assign itemNo=1/>
     <#assign loopNum=0/>
	 <#assign procUseNumIdentify = 'procUseNum' + '${loopNum}'/>
	 <@list title="" excel=false setupTable=false 
	          includeParameters="preRepairPlanDetail.id|repairPlanOrProcDetail.id|readOnly|planProcFlag|preYearFlag" 
        	  fieldMap="like:" >
         <input type="hidden" name="toolIds" value="#{object.id}"/>
		 <input type="hidden" name="repairToolName" value="${object.name?if_exists}"/>
         <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
	         <@vlh.checkbox property="id" name="repairToolIds">
		       <@vlh.attribute name="width" value="30" />
		     </@vlh.checkbox>
		   </#if>
		 </#if>
		 <@vcolumn title="${action.getText('repairTool.serialNo')}">
		   <#if planProcFlag?exists>
	         <#if planProcFlag=='PLAN'>
	           <#if preYearFlag?exists>
			     <#if preYearFlag=='YEAR'>
				   <a href="#" onclick="repairTool_openDialog(#{repairPlanOrProcDetail.id},#{object.id});return false;">#{itemNo}</a>
				 <#else>
		           <a href="#" onclick="repairTool_openDialog(#{preRepairPlanDetail.id},#{object.id});return false;">#{itemNo}</a>
		         </#if>
			   </#if>
		     <#else>
		       #{itemNo}
		     </#if>
		   </#if>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repairTool.name')}" property="name">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairTool.specification')}" property="specification">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairTool.model')}" property="model">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairTool.calcUnit')}" property="calcUnit">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairTool.planUseNum')}" property="planUseNum">
	       <@alignRight/>
         </@vcolumn>
	     <#if planProcFlag?exists>
	       <#if planProcFlag=='PROC'>
	         <@vcolumn title="${action.getText('repairTool.procUseNum')}">
               <input type="text" id="${procUseNumIdentify}" name="procUseNum" 
			          class="underline"  value="${object.procUseNum?if_exists}"  maxlength="50"/>
             </@vcolumn>
             <#assign loopNum=loopNum+1/>
	    	 <#assign procUseNumIdentify = 'procUseNum' + '${loopNum}'/>
           </#if>
         </#if>
         <@vcolumn title="${action.getText('repairTool.comment')}" property="comment">
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
			       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairTool_openDialog(#{repairPlanOrProcDetail.id},null);"/>
			   </#if>
			     <#else>
			      <#if !(action.isReadOnly())>
		           <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairTool_openDialog(#{preRepairPlanDetail.id},null);"/>
		           </#if>
		          </#if>
			    </#if>
			      <#if !(action.isReadOnly())>
		        <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairTool')}?" />
            	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
            		<@ww.param name="'onclick'" value="'return confirmDeletes(\"repairToolIds\", \"${confirmMessage}\");'"/>
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
	   function repairTool_openDialog(DetailId, repairToolId) {
	   	<#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
     			var url = '${req.contextPath}/popup/editRepairTool.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id=' + DetailId;
     		<#else>
	     		var url = '${req.contextPath}/popup/editRepairTool.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id=' + DetailId;
	     	</#if>
     	 </#if>
	     if (null != repairToolId) {
	       url = url + '&repairTool.id=' + repairToolId;
	     } 
	     url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,600,300); 
	     <#if preYearFlag?exists>
     		<#if preYearFlag=='YEAR'>
				self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanTools.html?repairPlanOrProcDetail.id='+DetailId +
				'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;     		
     		<#else>
	     		self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanTools.html?preRepairPlanDetail.id='+DetailId +
	     		'&planProcFlag=' + '${planProcFlag?if_exists}'+ '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	  		</#if>
     	 </#if>
	   }
	   function validate() {
         if (!validateProcUseNumFormate()) {
           return false;
         }
         retrieveRepairToolIdText();
         retrieveRepairToolProcUseNumText();
         return true;
	   }
	       //获取列表的实际使用数量的非空值
	       function retrieveRepairToolProcUseNumText() {
             var allRepairToolProcUseNum = document.getElementsByName("procUseNum");
             var allRepairToolId = document.getElementsByName("toolIds");
             var ary = new Array();
             for (var i=0; i<allRepairToolId.length; i++) {
               if ('' != allRepairToolProcUseNum[i].value) {
                 ary.push(allRepairToolId[i].value);
                 ary.push(formatDigital(allRepairToolProcUseNum[i].value));
               }
             }
             document.forms[0].elements["allRepairToolProcUseNum"].value = ary;
           }
	       /*
	        * 获取列表中维修工具的ID
	       */
	       function retrieveRepairToolIdText() {
		     var allRepairToolId = document.getElementsByName("toolIds");
		     var ary = new Array();
		     for (var i=0; i<allRepairToolId.length; i++) {
		       ary.push(allRepairToolId[i].value);
		     }
		     document.forms[0].elements["allRepairToolId"].value = ary;
	       }
           function validateProcUseNumFormate() {
             var allRepairToolName = document.getElementsByName("repairToolName");
             var allRepairToolProcUseNum = document.getElementsByName("procUseNum");
             var allRepairToolId = document.getElementsByName("toolIds");
             for (var i=0; i<allRepairToolId.length; i++) {
               var procUserNum = 'procUseNum' + i;
               if ('' != allRepairToolProcUseNum[i].value) {
                 if (!isNumberBetweenBoolen(allRepairToolProcUseNum[i].value,1000000001,0)) {
                   alert("${action.getText('select.right.ProcUseNum')}");
                   return false;
                 }
               }
             }
			 return true;             
           }
	</script>
</@framePage>