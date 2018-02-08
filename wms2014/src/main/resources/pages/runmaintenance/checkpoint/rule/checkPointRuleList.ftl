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

<#--$Id: checkPointRuleList.ftl 11271 2008-03-11 12:52:15Z wzou $ -->
<#include "../../../includes/eam2008.ftl" />
<@framePage title="">
	 <@ww.form name="'checkPointRules'" action="'searchCheckPointRules'" method="'post'">
	 <@ww.token name="searchCheckPointRulesToken"/>
         <#if toolingDev.id?exists>
	       <@ww.hidden name="'toolingDev.id'" value="'#{toolingDev.id}'"/>
	     </#if>
	     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
         <#assign itemNo=1/>
         <@list title="" excel=false setupTable=false includeParameters="toolingDev.id|readOnly|eamAuthentication" fieldMap="like:" >
            <@vlh.checkbox property="id" name="checkPointRuleIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('serialNo')}">
              <#if !(action.isReadOnly())>
                <a href="#" onclick="open_detailDialog(#{toolingDev.id}, #{object.id});return false;">#{itemNo}</a>
              <#else>
                #{itemNo}
              </#if>
            	<@alignCenter />
            </@vcolumn>
            <#assign itemNo = itemNo + 1/>
            <@vcolumn title="${action.getText('checkPointRule.position')}" property="position">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.content')}" property="content">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.ruleDsp')}" property="ruleDsp">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.methodDsp')}" property="methodDsp">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.requestDsp')}" property="requestDsp">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.lastCheckPointTime')}" property="lastCheckPointTime" format="yyyy-MM-dd">
            	<@alignCenter />
            </@vcolumn>
            <@vcolumn title="${action.getText('checkPointRule.cycle')}" property="cycle">
            	<@alignLeft />
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
        <@buttonBar>
        	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{toolingDev.id}, null)"/>
            <#if (toolingDev.checkPointRules.size()>0)>
            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('checkPointRule')}?" />
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"checkPointRuleIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
            </#if>
        </@buttonBar>
        </#if>
        </#if>
      </@ww.form>
      <script>
      	function open_detailDialog(toolingDevId, checkPointRuleId) {
	      		var url = '${req.contextPath}/runmaintenance/toolingDev/checkpoint/editCheckPointRule.html?toolingDev.id='+toolingDevId;	      		
	      		if (checkPointRuleId != null) {
	      			url = url + "&checkPointRule.id=" + checkPointRuleId;
	      		}
	      		popupModalDialog(url,800,600);
	      		self.location.reload();
	      	}
      </script>
</@framePage>