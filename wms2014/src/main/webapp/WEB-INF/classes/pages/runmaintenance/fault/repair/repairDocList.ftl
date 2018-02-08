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

<@framePage title="${action.getText('maintechDoc.title')}">
     <@ww.form name="'listForm'" action="'searchRepairDocs'" method="'post'">
     <@ww.token name="searchRepairDocsToken"/>
	 <#if faultRepair.id?exists>
       <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	   <#assign itemNo=1/>
	   <@list title="" excel=false setupTable=false 
	          includeParameters="faultRepair.id|preYearFlag" 
        	  fieldMap="like:" >   
         <@vlh.checkbox property="id" name="repairDocIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
		 <@vcolumn title="${action.getText('serialNo')}">
		   <a href="#" onclick="open_uploadDialog(#{faultRepair.id}, #{object.id});return false;">${itemNo}</a>
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
         <@buttonBar>
	        <@vbutton name="'upload'"  class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{faultRepair.id},null);"/>
         	<#assign confirmMessage = "${action.getText('repairDoc.deleted')}" />
	        <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	          <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairDocIds\", \"${confirmMessage}\");'"/>
	          <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	        </@vsubmit>
         </@buttonBar>
       </#if>    
     </@ww.form>
     <script language="javascript">
	   function open_uploadDialog(detailId, repairDocId) {
     	 var url = '${req.contextPath}/runmaintenance/faultRepair/editRepairDoc.html?faultRepair.id='+detailId;
     	 if (null != repairDocId) {
     	   url = url + '&doc.id=' + repairDocId;
     	 }
     	 url = url + '&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url, 650, 300);
         self.location.href='${req.contextPath}/runmaintenance/faultRepair/listRepairDocs.html?faultRepair.id='+detailId 
                                 + '&preYearFlag=' + '${preYearFlag?if_exists}';  
	  }
	 </script>
</@framePage>