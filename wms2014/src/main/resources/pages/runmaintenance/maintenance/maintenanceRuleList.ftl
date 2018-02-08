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

<#include "../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11090 2008-02-26 01:42:28Z zbzhang $ -->


<@framePage title="${action.getText('maintenanceRules.title')}">
	 <@ww.form name="'maintenanceRules'" action="'searchMaintenanceRules'" method="'post'">
	   <@ww.token name="searchMaintenanceRulesToken"/>
	     <#if device.id?exists>
	       <@ww.hidden name="'device.id'" value="'#{device.id}'"/>
	     </#if>
	 	  <#assign itemNo=1/>
     	    <@list title="" excel=false setupTable=false 
     	           includeParameters="device.id"  fieldMap="like:" >
     	      <@vlh.checkbox property="id" name="maintenanceRuleIds">
                <@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
              <@vcolumn title="${action.getText('serialNo')}">
                <a href="#" onclick="open_maintenanceRuleDialog(#{device.id}, #{object.id});return false;">#{itemNo}</a>
                <@alignCenter />
              </@vcolumn>
              <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('maintenanceRules.position')}" property="part">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('maintenanceRules.method')}" property="method">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('maintenanceRules.appeal')}" property="appeal">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('maintenanceRules.maintenanceType')}" property="maintenanceType.value">
                <@alignLeft />
              </@vcolumn>
     	    </@list>
     	    <#if !(action.isReadOnly())>
     	    <@buttonBar>
	         	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_maintenanceRuleDialog(#{device.id}, null)"/>
	         	<#if (device.maintenanceRules.size()>0)>
	            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('maintenanceRule')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"maintenanceRuleIds\", \"${confirmMessage}\");'"/>
	            </@vsubmit>
	            </#if>
           </@buttonBar>
           </#if>
     </@ww.form>
     
     <script>
     /*
	  *当该设备失效时,锁定页面所有控件
	 */
	<#if device.enabled?exists>
	  <#if !(device.enabled)>
	    __disableAllElements__(document.forms[0], new Array(""));
	  </#if>
	</#if>
     function open_maintenanceRuleDialog(deviceId, maintenanceRuleId) {
	      		var url = '${req.contextPath}/runmaintenance/maintenance/editMaintenanceRules.html?device.id='+deviceId;	      		
	      		if (maintenanceRuleId != null) {
	      			url = url + "&maintenanceRule.id=" + maintenanceRuleId;
	      		}
	      		popupModalDialog(url,800,600);
	      		self.location.reload();
	      	}
     
     </script>
     
</@framePage>