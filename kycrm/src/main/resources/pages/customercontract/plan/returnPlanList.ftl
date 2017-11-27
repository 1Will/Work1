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

<#-- $Id: contactArchivesList.ftl 2009-12-08 13:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('returnPlan.title.check')}">
	<@ww.form name="'listFrom'" namespace="'/contractManagement'" action="'searchReturnPlan'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchReturnPlanToken"/>
		<#include "./returnPlanSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/contractManagement/editReturnPlan.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
        </@buttonBar>
		<#assign returnName='replaceWord'>
        <@list title="${action.getText('returnPlan.list.title')}" 
            includeParameters="contractManagement.code|returnPlan.contractManagement|returnPlan.paytime_start|returnPlan.paytime_end
            |returnPlan.planDate_start|returnPlan.planDate_end|customerInfo.code|customerInfo.name|mold.id|isOrNot|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:contractManagement.code|returnPlan.contractManagement|customerInfo.code|customerInfo.name|
        	,date:returnPlan.paytime_start|returnPlan.paytime_end|returnPlan.planDate_start|returnPlan.planDate_end" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="returnPlanIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('returnPlan.batch')}" property="batch.name" sortable="desc">
            	<a href="editReturnPlan.html?returnPlan.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.batch.name}</a>
            	<@alignCenter/>
            </@vcolumn>
            
            <#--
            <@vcolumn title="${action.getText('contractManagement.code')}" property="contractManagement.code" sortable="desc">
                <a href="editReturnPlan.html?returnPlan.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.contractManagement.code}</a>
				<@alignLeft attributes="width:120;"/>
            </@vcolumn>
            -->
             <@vcolumn title="${action.getText('类型')}" property="mold.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('returnPlan.contractManagement')}" property="contractManagement.contractName" sortable="desc">
            	<@alignLeft attributes="width:220;"/>
            </@vcolumn>
            
            <#--
            <@vcolumn title="${action.getText('customerInfo.code')}" property="customerInfo.code" sortable="desc">
     			<@alignLeft attributes="width:80;"/>
            </@vcolumn>
            -->
            <@vcolumn title="${action.getText('customerInfo.name')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.contactArchives')}" property="contactArchives.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('returnPlan.phone')}" property="contactArchives.mobilePhone" sortable="desc">
             	<@alignLeft attributes="width:100;"/>
     			<@alignRight/>
            </@vcolumn>
            <@vcolumn title="${action.getText('returnPlan.planDate')}" property="planDate" format="yyyy-MM-dd" sortable="desc">
            	<@vlh.attribute name="width" value="90" />
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            
			<@vcolumn title="${action.getText('房间编码')}" property="house" sortable="desc">
			<#assign returnName=returnName +'replaceWord'>
				<@ww.hidden name="'${returnName}'" value="'${object.house?if_exists}'"/>
	            <span title="${object.house?if_exists}">
		            <script>
		            	var s = getObjByName('${returnName}').value;
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>18){
		            	document.write(s.slice(0,18)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            	getObjByName('${returnName}').disabled = "disabled";
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('returnPlan.sum')}" format="0.00" property="sum" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>

             <#assign isOrNot=""/>
            <#if (object.isOrNot)=='0'>
            	<#assign isOrNot="${action.getText('是')}">
            <#else>
           	 	<#assign isOrNot="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.isOrNot')}"sortable="desc">
            	${isOrNot?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            <#--
             <#assign notOrIs=""/>
            <#if (object.notOrIs)=='0'>
            	<#assign notOrIs="${action.getText('是')}">
            <#else>
           	 	<#assign notOrIs="${action.getText('否')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.notOrIs')}" sortable="desc">
            	${notOrIs?if_exists}
            	<@alignLeft/>
            </@vcolumn>
            -->
            
            <@vcolumn title="${action.getText('returnPlan.paytime')}" property="paytime" format="yyyy-MM-dd" sortable="desc">
            	<@alignCenter/><#-- attributes="width:110;"-->
            </@vcolumn>
            
             <@vcolumn title="${action.getText('returnPlan.factSum')}" property="factSum" format="0.00" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
            
            <#assign billingOrNot=""/>
            <#if (object.billingOrNot)=='0'>
            	<#assign billingOrNot="${action.getText('未开发票')}">
		    </#if>
            <#if (object.billingOrNot)=='1'>
           	 	<#assign billingOrNot="${action.getText('部分已开')}">
		    </#if>
            <#if (object.billingOrNot)=='2'>
           	 	<#assign billingOrNot="${action.getText('发票全开')}">
		    </#if>
            <@vcolumn title="${action.getText('returnPlan.billingOrNot')}" property="billingOrNot" sortable="desc">
            	${billingOrNot?if_exists}
            	<@vlh.attribute name="width" value="60" />
            	<@alignLeft/>
            </@vcolumn>
            			
             <@vcolumn title="${action.getText('开票日期')}" property="billDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
			<@vcolumn title="${action.getText('开票金额')}" property="billMoney" format="0.00" sortable="desc">
     			<@alignRight/>
			</@vcolumn>
            
             <@vcolumn title="${action.getText('详细')}" >
				<#if object.mold?exists && object.mold.code=='23701' >
					<a href="javascript:open_detail('${object.house?if_exists}',#{object.contractManagement.id?if_exists})">详细</a>
				</#if>
				
				<#if object.mold?exists && object.mold.code=='23702' >
					<a href="javascript:open_waterDetail('#{object.customerInfo.id?if_exists}','${(object.planDate?string('yyyy-MM-dd'))?if_exists}')">详细</a>
				</#if>
     			<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('收款')}">
            <#if object.isOrNot?exists && object.isOrNot=='1' >
            	<@vbutton value="${action.getText('收款')}" class="button" onclick ="newFinancialManagement_OpenDialog(#{object.id?if_exists})"/>
            <#else>
            	完成
            </#if>
     			<@alignCenter/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('开票')}">
            <#if object.billingOrNot?exists && object.billingOrNot !='2' >
            	<@vbutton value="${action.getText('开票')}" class="button" onclick ="newBillingRecord_OpenDialog(#{object.id?if_exists})"/>
            <#else>
            	完成
            </#if>
     			<@alignCenter/>
            </@vcolumn>
            
        </@list>
         <#if !first>
         	<#if !(action.isReadOnly())>
		        <@buttonBar>
		          <@crm_disabledOrEnabled_button message="${action.getText('returnPlan.info')}" boxName="returnPlanIds" jsFunctionName="checkInvalidParms()"/>
				</@buttonBar>
			</#if>
		 </#if>
    </@ww.form>
</@htmlPage>