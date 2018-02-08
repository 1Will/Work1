<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: purchaseBillList.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseContractList.title')}">
	<@ww.form namespace="'/toooling/purchaseContract'" action="'searchPurchaseBills'" method="'post'">
		<@ww.token name="searchPurchasesToken"/>
		<#include "purchaseContractSearcher.ftl"/>
		  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		  <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			 <#if !(action.isReadOnly())>
			 <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/toooling/purchaseContract/editPurchaseBill.html?toolingDevFlag=${toolingDevFlag?if_exists}"/>
		    </#if>
		</@buttonBar>
		<@list title="${action.getText('purchaseContract.List')}" 
		includeParameters="billNo|readOnly|name|department.id|buyer.name|supplier.name|status|purchaseDate_start|purchaseDate_end|toolingDevFlag|onlyValid|onlyInvalid" 
		fieldMap="like:billNo|name|buyer.name|supplier.name" >
			 <#if (object.disabled)>
			<@vlh.checkbox property="id" name="purchaseBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
             <@vcolumn title="${action.getText('purchaseContract.billNo')}" property="billNo" sortable="desc">
            	${object.billNo}
            	<@alignLeft />
            </@vcolumn>
            <#else>
               <@vlh.checkbox property="id" name="purchaseBillIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
              <@vcolumn title="${action.getText('purchaseContract.billNo')}" property="billNo" sortable="desc">
            	<a href="editPurchaseBill.html?purchaseBill.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.billNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('purchaseContract.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('buyer')}" property="buyer.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('purchaseBill.purchaseDate')}" property="purchaseDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier')}" property="supplier.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('contractMoney')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight/>
            </@vcolumn>
            <#assign purchaseStatus = ''/>
            <#if '${object.status}' == 'NEWSTATUS'>
              <#assign purchaseStatus = "${action.getText('NEWSTATUS')}"/>
            <#elseif '${object.status}' == 'INSPECTING'>
              <#assign purchaseStatus = "${action.getText('INSPECTING')}"/>
            <#else>
              <#assign purchaseStatus = "${action.getText('INSPECTED')}"/>
            </#if>
         <@vcolumn title="${action.getText('purchase.state')}">
           ${purchaseStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
		<#if !first>
		 <#if !(action.isReadOnly())>
        <@buttonBar>
             <@eam2008_disabledOrEnabled_button message="${action.getText('chasescontract')}" boxName="purchaseBillIds" jsFunctionName="checkInvalidParms()"/>
        </@buttonBar>
        </#if>
        </#if>
	</@ww.form>
</@htmlPage>