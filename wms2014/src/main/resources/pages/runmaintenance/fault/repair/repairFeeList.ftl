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

<@framePage title="${action.getText('repairFee.title')}">
     <@ww.form name="'fee'" action="'searchRepairFees'" method="'post'">
     <@ww.token name="searchRepairFeesToken"/>
     <#if faultRepair.id?exists>
     	<@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
     </#if>
     	<@ww.hidden name="'planProcFlag'" value="'${planProcFlag?if_exists}'"/>
     	<@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
     	<@ww.hidden name="'allRepairFeeProcFee'" value="''"/>
     	<@ww.hidden name="'allRepairFeeId'" value="''"/>
	    <#assign itemNo=1/>
	    <#assign loopNum=0/>
	    <#assign procFeeIdentify = 'procFee' + '${loopNum}'/>
	    <#assign sourceType=''/>
	    <#assign planAllFees  = 0/>
	    <#assign procAllFees  = 0/>
	        
	   <@list title="" excel=false setupTable=false 
	          includeParameters="preRepairPlanDetail.id|repairPlanOrProcDetail.id|planProcFlag|preYearFlag" 
        	  fieldMap="like:" >
         <input type="hidden" name="repairFeeIds" value="#{object.id}"/>
	     <input type="hidden" name="feeItems" value="${object.feeItem?if_exists}"/>
         <@vlh.checkbox property="id" name="feeIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
		 <@vcolumn title="${action.getText('repairFee.serialNo')}">
		   <a href="#" onclick="fee_openDialog(#{faultRepair.id},null,#{object.id});return false">#{itemNo}</a>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repairFee.feeItem')}" property="feeItem">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairFee.procFee')}" property="procFee" format="#,###,##0.00">
	       <@alignRight/>
         </@vcolumn>
         <#if object.planFee?exists>
	       <#assign planAllFees  = planAllFees + object.planFee/>
		 </#if>
	     <@vcolumn title="${action.getText('repairFee.comment')}" property="comment">
	       <@alignLeft/>
         </@vcolumn>
       </@list>
       <#if !first>
	     <@buttonBar>
		   <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="fee_openDialog(#{faultRepair.id},null,null);"/>
	       <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('fee')}?" />
	       <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	         <@ww.param name="'onclick'" value="'return confirmDeletes(\"feeIds\", \"${confirmMessage}\");'"/>
	         <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	       </@vsubmit>
	     </@buttonBar>
       </#if>
     </@ww.form>
     <script language="javascript">
          window.onload = function() {
            <#if faultRepair?exists>
              parent.getObjByNameRe("faultRepair.allFee").value = '${faultRepair.allFee?if_exists}';
            </#if>
          }
	      function fee_openDialog(DetailId,PlanId,repairFeeId) {
     		var url = '${req.contextPath}/runmaintenance/faultRepair/editRepairFee.html?faultRepair.id=' + DetailId;
	        if (null !=repairFeeId) {
	        	url = url + '&repairFee.id=' +repairFeeId;
	        }
	        url = url + '&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;
	        popupModalDialog(url,650,300); 
	     	self.location.href='${req.contextPath}/runmaintenance/faultRepair/listRepairFees.html?faultRepair.id='+DetailId+ 
	     			'&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;  
	       }
	    </script>
</@framePage>