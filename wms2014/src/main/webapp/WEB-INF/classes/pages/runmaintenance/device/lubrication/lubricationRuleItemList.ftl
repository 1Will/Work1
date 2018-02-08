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

<#include "../../../includes/eam2008.ftl" />
<#--$Id: lubricationRuleItemList.ftl 11251 2008-03-10 13:53:55Z zbzhang $ -->


<@framePage title="${action.getText('lubricationRules.title')}">
	 <@ww.form name="'lubricationRules'" action="'searchLubricationRules'" method="'post'">
	   <@ww.token name="searchLubricationRulesToken"/>
	     <#if device.id?exists>
	       <@ww.hidden name="'device.id'" value="'#{device.id}'"/>
	     </#if>
	 	  <#assign itemNo=1/>
     	    <@list title="" excel=false setupTable=false 
     	           includeParameters="device.id"  fieldMap="like:" >
     	      <@vlh.checkbox property="id" name="lubricationRuleIds">
                <@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
              <@vcolumn title="${action.getText('serialNo')}">
                <#if !(action.isReadOnly())>
                  <a href="#" onclick="open_detailDialog(#{device.id}, #{object.id});return false;">#{itemNo}</a>
                <#else>
                  #{itemNo}
                </#if>
                <@alignCenter />
              </@vcolumn>
              <#assign itemNo = itemNo + 1/>
              <@vcolumn title="${action.getText('lubricationRules.position')}" property="position">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.ruleDsp')}" property="ruleDsp">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.methodDsp')}" property="methodDsp">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.cycle')}" property="cycle">
                <@alignRight />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.lastLubricationDate')}" property="lastLubricationDate" format="yyyy-MM-dd">
                <@alignCenter />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRulesDetail.lubricatingOil')}" property="lubricatingOil.name">
                <@alignLeft />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.lubricatingOilQty')}" property="lubricatingOilQty">
                <@alignRight />
              </@vcolumn>
              <@vcolumn title="${action.getText('lubricationRules.dutyPeople')}" property="dutyPeople">
                <@alignRight />
              </@vcolumn>
     	    </@list>
     	    <#if !first>
     	    <#if !(action.isReadOnly())>
	     	    <@buttonBar>
		         	<input type="button" class="button" name="new" value="${action.getText('new')}" onclick="open_detailDialog(#{device.id}, null)"/>
		            <#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('lubricationRule')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"lubricationRuleIds\", \"${confirmMessage}\");'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
	           </@buttonBar>
	       </#if>
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
     function open_detailDialog(deviceId, lubricationRuleId) {
	      		var url = '${req.contextPath}/runmaintenance/lubrication/editLubricationRules.html?device.id='+deviceId;	      		
	      		if (lubricationRuleId != null) {
	      			url = url + "&lubricationRule.id=" + lubricationRuleId;
	      		}
	      		popupModalDialog(url,800,600);
	      		self.location.reload();
	      	}
     
     </script>
     
</@framePage>