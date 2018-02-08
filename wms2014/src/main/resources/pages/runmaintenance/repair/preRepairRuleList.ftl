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

<@framePage title="${action.getText('PreRepairRuleList.title')}">
     <@ww.form name="'PreRepairRule'" action="'searchPreRepairRules'" method="'post'">
     <@ww.token name="searchPreRepairRulesToken"/>
     <#if asset?exists>
	     <@ww.hidden name="'device.id'" value="'#{asset.id?if_exists}'"/>
	 </#if>
	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
     <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	 <#assign itemNo=1/>
	 <@list title="" excel=false setupTable=false includeParameters="device.id|readOnly|eamAuthentication" fieldMap="like:" >
            <@vlh.checkbox property="id" name="preRepairRuleIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('serialNo')}">
              <#if !(action.isReadOnly())>
                <a href="#" onclick="open_preRepairRuleDialog(#{asset.id}, #{object.id});return false;">#{itemNo}</a>
              <#else>
                #{itemNo}
              </#if>
            	<@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('PreRepairRule.position')}" property="position">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairRule.content')}" property="content">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairRule.lastRepairDate')}" property="lastRepairDate" format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('PreRepairRule.maxRunHour')}" property="maxRunHour">
            	<@alignRight />
            </@vcolumn>
            <@vcolumn title="${action.getText('device.preRepairTime')}" property="preRepairTime">
            	<@alignRight />
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
         <@buttonBar>
         	 <input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_preRepairRuleDialog(#{asset.id}, null)"/>
            <#if (asset.preRepairRules.size()>0)>
            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('preRepairRule')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"preRepairRuleIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
            </#if>
         </@buttonBar>
       </#if>
       </#if>
        </@ww.form>
	     <script language="javascript">
			 <@eam2008_LockPageIfNeed/>
			 /*
			  *当该设备失效时,锁定页面所有控件
			 */
			<#if asset.enabled?exists>
	          <#if !(asset.enabled)>
	             __disableAllElements__(document.forms[0], new Array(""));
	          </#if>
	        </#if>
	         function open_preRepairRuleDialog(deviceId, preRepairRuleId) {
	      		var url = '${req.contextPath}/popup/editPreRepairRule.html?device.id=' + deviceId;	 
	      		if (preRepairRuleId != null) {
	      			url = url + "&preRepairRule.id=" + preRepairRuleId;
	      		}
	      		
	      		popupModalDialog(url, 650,300);
	      		//opener.location.reload();
	      	    //alert(0000);
	      	  //window.location.href=''
	      		self.location.reload();
	      	 }
	    </script>
	 
</@framePage>