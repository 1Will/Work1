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

<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form name="'listForm'" action="'searchPreRepairPlanDocs'" method="'post'">
     <@ww.token name="searchPreRepairPlanDocsToken"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <#if preYearFlag?exists>
       <#if preYearFlag=='YEAR'>
         <@ww.hidden name="'repairPlanOrProcDetail.id'" value="'#{repairPlanOrProcDetail.id?if_exists}'"/>
       <#else>
     	 <@ww.hidden name="'preRepairPlanDetail.id'" value="'#{preRepairPlanDetail.id?if_exists}'"/>
       </#if>
     </#if>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	   <#assign itemNo=1/>
	   <@list title="" excel=false setupTable=false 
	          includeParameters="preRepairPlanDetail.id|readOnly|repairPlanOrProcDetail.id|planProcFlag|preYearFlag" 
        	  fieldMap="like:" >   
         <#if planProcFlag?exists>
           <#if (planProcFlag=='PLAN')>
	         <@vlh.checkbox property="id" name="repairDocIds">
		       <@vlh.attribute name="width" value="30" />
		     </@vlh.checkbox>
		   </#if>
		 </#if>
		 <@vcolumn title="${action.getText('serialNo')}">
 		   <#if planProcFlag?exists>
             <#if (planProcFlag=='PLAN')>
               <#if preYearFlag?exists>
			     <#if preYearFlag=='YEAR'>
			       <a href="#" onclick="open_uploadDialog(#{repairPlanOrProcDetail.id},#{object.id});return false">${itemNo}</a>
				 <#else>
				   <a href="#" onclick="open_uploadDialog(#{preRepairPlanDetail.id}, #{object.id});return false;">${itemNo}</a>
			     </#if>
			   </#if>
             <#else>
               ${itemNo}  
             </#if>
           </#if>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repair.doc.name')}">
	       <a  title="${action.getText('download')}" href="downloadRepairDoc.html?doc.id=#{object.id}" >${object.name?if_exists}</a>
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repair.doc.description')}" property="description">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repair.doc.createdTime')}" property="createdTime" format="yyyy-MM-dd">
	       <@alignCenter/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repair.doc.operator')}" property="creator">
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
			           <@vbutton name="'upload'"  class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{repairPlanOrProcDetail.id},null);"/>
			     </#if> 
			        <#else>
			         <#if !(action.isReadOnly())>
			          <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{preRepairPlanDetail.id}, null);"/>
			        </#if>
			        </#if>
			       </#if>
			         <#if !(action.isReadOnly())>
		         	<#assign confirmMessage = "${action.getText('repairDoc.deleted')}" />
			        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			          <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairDocIds\", \"${confirmMessage}\");'"/>
			          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
			        </@vsubmit>
			        </#if>
		         </@buttonBar>
	         </#if>
           </#if>
       </#if>    
     </@ww.form>
     <script language="javascript">
	   <@eam2008_LockPageIfNeed/>
	   function open_uploadDialog(detailId, repairDocId) {
	     <#if preYearFlag?exists>
     	   <#if preYearFlag=='YEAR'>
     	     var url = '${req.contextPath}/popup/editRepairDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&repairPlanOrProcDetail.id='+detailId;
     	   <#else>
     		 var url = '${req.contextPath}/popup/editRepairDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&preRepairPlanDetail.id='+detailId;
     	   </#if>
     	 </#if>
     	 if (null != repairDocId) {
     	   url = url + '&doc.id=' + repairDocId;
     	 }
     	 url = url + '&planProcFlag=' + document.forms[0].elements["planProcFlag"].value + '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url, 650, 300);
	    		//self.location.reload();
	     
	  	 <#if preYearFlag?exists>
     	   <#if preYearFlag=='YEAR'>
             self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDocs.html?repairPlanOrProcDetail.id='+detailId+ '&planProcFlag=' + '${planProcFlag?if_exists}' 
                                 + '&preYearFlag=' + '${preYearFlag?if_exists}';  
     	   <#else>
	     	self.location.href='${req.contextPath}/runmaintenance/repair/plan/listPreRepairPlanDocs.html?preRepairPlanDetail.id='+detailId+ '&planProcFlag=' + '${planProcFlag?if_exists}' 
                                 + '&preYearFlag=' + '${preYearFlag?if_exists}';  
     		</#if>
     	 </#if>
	  }
	 </script>
</@framePage>