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
     <@ww.form name="'repairItem'" action="'searchRepairItems'" method="'post'">
     <@ww.token name="searchRepairItemsToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	   <#if faultRepair.id?exists>
	     <@ww.hidden name="'faultRepair.id'" value="'#{faultRepair.id?if_exists}'"/>
	   </#if>
       <@ww.hidden name="'preYearFlag'" value="'${preYearFlag?if_exists}'"/>
	   <#assign itemNo=1/>
	   <@list title="" excel=false setupTable=false 
	          includeParameters="preYearFlag|faultRepair.id|readOnly" 
        	  fieldMap="like:" >
         <@ww.hidden name="'repairItemIds'" value="'#{object.id}'"/>
         <@vlh.checkbox property="id" name="itemIds">
	       <@vlh.attribute name="width" value="30" />
	     </@vlh.checkbox>
		 <@vcolumn title="${action.getText('repairItem.serialNo')}">
		   <a href="#" onclick="item_openDialog(#{faultRepair.id},#{object.id});return false">${itemNo}</a>
	       <@alignCenter/>
	     </@vcolumn>
	     <#assign itemNo = itemNo+1/>
	     <@vcolumn title="${action.getText('repairItem.position')}" property="position">
	       <@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('repairItem.content')}" property="content">
	       <@alignLeft/>
         </@vcolumn>
         <#--
         <@vcolumn title="${action.getText('repairItem.aimRequire')}" property="aimRequire">
	       <@alignLeft/>
         </@vcolumn>
         -->
         <@vcolumn title="${action.getText('repairItem.execPeople')}" property="execPeople.name">
	       <@alignLeft/>
         </@vcolumn>
         <#--
         <@vcolumn title="${action.getText('repairItem.planCompleteDate')}" property="planCompleteDate" format="yyyy-MM-dd">
	       <@alignCenter/>
         </@vcolumn>
         -->
       </@list>
       <#if !first>
         <@buttonBar>
         
         <#if !(action.isReadOnly())>
	       <@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="item_openDialog(#{faultRepair.id},null);"/>
		   <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('item')}?" />
	       <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	         <@ww.param name="'onclick'" value="'return confirmDeletes(\"itemIds\", \"${confirmMessage}\");'"/>
	         <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	       </@vsubmit>
	       </#if>
	     </@buttonBar>
       </#if>
     </@ww.form>
     <script language="javascript">
	   function item_openDialog(DetailId,repairItemId) {
     	 var url = '${req.contextPath}/runmaintenance/faultRepair/editRepairItem.html?readOnly=${req.getParameter('readOnly')?if_exists}&faultRepair.id='+DetailId;
	     if (null !=repairItemId) {
	       url = url + '&repairItem.id=' +repairItemId;
	     }
	     url = url + '&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;
	     popupModalDialog(url,800,600);
     	 self.location.href='${req.contextPath}/runmaintenance/faultRepair/listRepairItems.html?readOnly=${req.getParameter('readOnly')?if_exists}&faultRepair.id='+DetailId + 
     			 '&preYearFlag=' + document.forms[0].elements["preYearFlag"].value;
	   }
	 </script>
</@framePage>