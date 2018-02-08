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
<#-- $Id: supplierSelector.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('supplier.title')}">
<base target="_self">

<@ww.form name="'listForm'" action="'searchSupplierSelector'" method="'post'">
	<@ww.token name="supplierSelectorToken"/>
    <#include "supplierSearch.ftl" />
    <@buttonBar>
        <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
    </@buttonBar>

    <@list title="${action.getText('supplier.list')}" includeParameters="id|supplierNo|name|country|zone|toolingDevFlag" fieldMap="like:id|supplierNo|name|country|zone" >
       <#assign purchaseSupplierName=""/>
     <#assign purchaseSupplierTelPhone=""/>
     <#if object.supplierExtInfo?exists>
     <#if object.supplierExtInfo.contact0?exists>
          <#assign purchaseSupplierName="${object.supplierExtInfo.contact0}"/>
     </#if>
     <#if object.supplierExtInfo.tel?exists>
          <#assign purchaseSupplierTelPhone="${object.supplierExtInfo.tel}"/>
     </#if> 
     </#if>
            <@vcolumn title="${action.getText('supplier.supplier_no')}" property="supplierNo" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}','${object.supplierNo}','${purchaseSupplierName}','${purchaseSupplierTelPhone}'));">${object.supplierNo}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.name')}" property="name" sortable="desc"/>
            <@vcolumn title="${action.getText('supplier.en_name')}" property="enName" />
            <@vcolumn title="${action.getText('supplier.supplierType')}" property="supplierType.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.level')}" property="level.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.country')}" property="country.name" />
            <@vcolumn title="${action.getText('supplier.zone')}" property="zone" />
            <@vcolumn title="${action.getText('supplier.company_property')}" property="companyProperty" />
             <@vcolumn title="${action.getText('supplier.register_funds')}" property="registeredFunds" />
     </@list>
     <#if !first>
     <@buttonBar> 
     	<@vbutton name="'close'" class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
	 </@buttonBar>
	</#if>
</@ww.form>
</@htmlPage>