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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('部门目标查询页面')}">
	<@ww.form name="'listForm'" action="'searchDepartmentTargetAction'" method="'post'">
		<@ww.token name="searchCompanyTargetActionToken"/>
		<#include "./departmentTargetSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/departmentTarget/editDepartmentTargetAction.html"/>
        </@buttonBar>
        <@list title="${action.getText('部门目标列表')}" 
            includeParameters="deparmentTarget.planType|deparmentTarget.quarter|deparmentTarget.month|deparmentTarget.year|deparmentTarget.targetName|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	
        	<@vlh.checkbox property="id" name="departmentTargetIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
          <@vcolumn title="${action.getText('编码')}" property="code" sortable="desc" >
                <a href="editDepartmentTargetAction.html?targetManagement.id=#{object.id}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('目标名称')}" property="companyTarget.targetName" sortable="desc">
            ${object.targetName}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText(' 计划类型')}" property="planType" sortable="desc">
	           	<#if object.planType?exists>
	           		<#if object.planType = 1>
	           			年度计划
	           		</#if>
	           		<#if object.planType = 2>
	           			季度计划
	           		</#if>
	           		 <#if object.planType = 3>
	           			月度计划
	           		</#if>
          	 	</#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('订单金额')}" property="companyTarget.orderMoney" sortable="desc">
            	 ${object.orderMoney?if_exists}
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('销售回款')}" property="companyTarget.sellReceivedPayments" sortable="desc">
			 ${object.sellReceivedPayments?if_exists}
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('年份')}" property="year" sortable="desc">
			 #{object.year?if_exists}
     			<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('季度')}" property="quarter" sortable="desc">
			 第#{object.quarter?if_exists}季度
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('月份')}" property="month" sortable="desc">
			 #{object.month?if_exists}月份
     			<@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('部门目标')}" boxName="departmentTargetIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
    </@ww.form>
</@htmlPage>

<#macro crm_disabledOrEnabled_Nodelete_button message="" boxName="" jsFunctionName="" other="test">
  <#if (action.isOnlyInvalid())>
  	<#assign confirmMessage1 = "${action.getText('confirm.valid')}${message?if_exists}?" />
	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmValids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  <#else>
    <#assign confirmMessage1 = "${action.getText('confirm.inValid')}${message?if_exists}?" />
	<@vsubmit name="'disabled'" value="'${action.getText('disabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  </#if>
  <script language="javascript">
	function validateInvalid(delFun, checkFun) {
      if (delFun) {
        checkFun;
        return true;
      }
      return false;
   }
  </script>
</#macro>