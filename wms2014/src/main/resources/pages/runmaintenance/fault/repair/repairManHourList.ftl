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
     <@ww.form name="'listPreRepairPlanManHour'" action="'searchRepairManHours'" method="'post'">
     <@ww.token name="searchPreRepairPlanManHoursToken"/>
     <#if faultRepair.id?exists>
       <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     </#if>
     <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     <@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
	 <#assign itemNo=1/>
    <@list title="" excel=false setupTable=false 
           includeParameters="faultRepair.id|planProcFlag|preYearFlag" 
    	   fieldMap="like:" >
     <@ww.hidden name="'manHourIds'" value="'#{object.id}'"/>
     <@vlh.checkbox property="id" name="repairManHourIds">
       <@vlh.attribute name="width" value="30" />
     </@vlh.checkbox>
	 <@vcolumn title="${action.getText('repairManHour.serialNo')}">
	   <a href="#" onclick="repairManHour_openDialog(#{faultRepair.id},#{object.id});return false;">#{itemNo}</a>
       <@alignCenter/>
     </@vcolumn>
     <#assign itemNo = itemNo+1/>
     <@vcolumn title="${action.getText('repairManHour.workType')}" property="workType.name">
       <@alignLeft/>
     </@vcolumn>
     <@vcolumn title="${action.getText('workType.unitPrice')}" property="unitPrice">
       <@alignRight/>
     </@vcolumn>
     <@vcolumn title="${action.getText('repairManHour.procManHourNum')}" property="procManHourNum">
       <@alignRight/>
     </@vcolumn>
     <#assign procAllPrice=''/>
	   <#if object.unitPrice?exists && object.procManHourNum?exists>
	     <#assign procAllPrice=object.procManHourNum * object.unitPrice/>
	   </#if>
     <@vcolumn title="${action.getText('repairManHour.procAllPrice')}">
       ${procAllPrice}
       <@alignRight/>
     </@vcolumn>
     <@vcolumn title="${action.getText('repairManHour.comment')}" property="comment">
       <@alignLeft/>
     </@vcolumn>
    </@list>
    <#if !first>
 	      <@buttonBar>
	     	<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="repairManHour_openDialog(#{faultRepair.id},null);"/>
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('repairManHour')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
              <@ww.param name="'onclick'" value="'return confirmDeletes(\"repairManHourIds\", \"${confirmMessage}\");'"/>
              <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
          </@buttonBar>
    </#if>
     </@ww.form>
     <script language="javascript">
	   function repairManHour_openDialog(DetailId, repairManHourId) {
     	 var url = '${req.contextPath}/runmaintenance/faultRepair/editRepairManHour.html?faultRepair.id=' + DetailId;
	     if (null != repairManHourId) {
	       url = url + '&repairManHour.id=' + repairManHourId;
	     } 
	     url = url + '&preYearFlag=' +
	     			document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,600,300); 
     	 self.location.href='${req.contextPath}/runmaintenance/faultRepair/listRepairManHours.html?faultRepair.id='+DetailId +
     			            '&preYearFlag=' + document.forms[0].elements["preYearFlag"].value; 
	   }
	</script>
</@framePage>