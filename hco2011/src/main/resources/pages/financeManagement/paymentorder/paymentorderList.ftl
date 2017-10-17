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

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('paymentorder.search')}">
	<@ww.form name="'listForm'" action="'searchPaymentorderAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchPaymentorderActionToken"/>
		<#include "./paymentorderSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms()'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/paymentorder/editPaymentorderAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        <@list title="${action.getText('paymentorder.list')}" 
            includeParameters="paymentorder.code|paymentorder.supplier.name|paymentorder.produceType.id|readOnly|onlyInvalid|onlyValid" 
        	fieldMap="like:paymentorder.code|paymentorder.supplier.name" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="paymentorderIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
          	<@vcolumn title="${action.getText('paymentorder.code')}" property="code" sortable="desc" >
                <a href="editPaymentorderAction.html?paymentorder.id=#{object.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code?if_exists}</a>
				<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('paymentorder.supplier')}" property="customerInfo.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('合同名称')}"  >
              <#if object.contractManagement?exists>
              <a href="javascript:contractManagement_OpenDialog(#{object.contractManagement.id?if_exists})">
             ${object.contractManagement.contractName}
             </a>
             </#if>
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('项目名称')}" >
             <#if object.projectInfo?exists>
             <a href="javascript:projectInfo_OpenDialog(#{object.projectInfo.id?if_exists})">
             ${object.projectInfo.name}
             </a>
             </#if>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('paymentorder.produceType')}" property="produceType.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            
             <@vcolumn title="${action.getText('paymentorder.totalMoney')}" property="totalMoney" sortable="desc">
	            	#{object.totalMoney?if_exists}            	
     			<@alignRight/>
            </@vcolumn>
        </@list>
        <#if !first>
        <#if !(action.isReadOnly())>
	        <@buttonBar>
	          <@crm_disabledOrEnabled_button message="${action.getText('paymentorder.message')}" boxName="paymentorderIds" jsFunctionName="checkInvalidParms()"/>
			</@buttonBar>
		</#if>
		</#if>
    </@ww.form>
</@htmlPage>
<script>
	  //弹出项目信息窗体
	function projectInfo_OpenDialog(id){
	   var url = "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag";
	   openNewWindow(url);
	 }
	   //弹出合同信息窗体
	function contractManagement_OpenDialog(id){
	   var url = "${req.contextPath}/contractManagement/editContractManagementAction.html?contractManagement.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag";
	   openNewWindow(url);
	 }
	 
</script>
