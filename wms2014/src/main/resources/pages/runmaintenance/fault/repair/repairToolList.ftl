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

<@framePage title="${action.getText('item.title')}">
     <@ww.form  name="'listRepairTool'" action="'searchRepairTools'" method="'post'">
     <@ww.token name="searchPreRepairPlanToolsToken"/>
     <#if faultRepair.id?exists>
       <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>

	 <#assign itemNo=1/>
	 <@list title="" excel=false setupTable=false 
	          includeParameters="faultRepair.id|preYearFlag" 
        	  fieldMap="like:" >
         <input type="hidden" name="toolIds" value="#{object.id}"/>
		 <input type="hidden" name="repairToolName" value="${object.name?if_exists}"/>
         <@vlh.checkbox property="id" name="repairToolIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
		 <@vcolumn title="${action.getText('repairTool.serialNo')}">
		   <a href="#" onclick="repairTool_openDialog(#{faultRepair.id},#{object.id});return false;">#{itemNo}</a>
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
         <@vcolumn title="${action.getText('repairTool.procUseNum')}" property="procUseNum">
	       <@alignRight/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairTool.comment')}" property="comment">
	       <@alignLeft/>
         </@vcolumn>
       </@list>
       <#if !first>
         <@buttonBar>
	       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairTool_openDialog(#{faultRepair.id},null);"/>
	       <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairTool')}?" />
           <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
             <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairToolIds\", \"${confirmMessage}\");'"/>
        	 <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
           </@vsubmit>
         </@buttonBar>
       </#if>
     </@ww.form>
     <script language="javascript">
	   function repairTool_openDialog(DetailId, repairToolId) {
	     var url = '${req.contextPath}/runmaintenance/faultRepair/editRepairTool.html?faultRepair.id=' + DetailId;
	     if (null != repairToolId) {
	       url = url + '&repairTool.id=' + repairToolId;
	     } 
	     url = url + '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,600,300); 
		 self.location.href='${req.contextPath}/runmaintenance/faultRepair/listRepairTools.html?faultRepair.id='+DetailId +
				'&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;     		
	   }
	</script>
</@framePage>