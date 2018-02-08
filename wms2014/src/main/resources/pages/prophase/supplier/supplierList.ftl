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
<#--$Id: supplierList.ftl 11220 2008-03-07 10:03:29Z mwei $ -->

<#include "../../includes/eam2008.ftl" />
 <#assign toolingDevName=""/>
 <#assign toolingListName=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign toolingDevName="${action.getText('supplierDeviceBillSearcher.title')}"/>
   <#assign toolingListName="${action.getText('deviceSupplierList.title')}"/>
   <#else>
    <#assign toolingDevName="${action.getText('supplierToolingBillSearcher.title')}"/>
    <#assign toolingListName="${action.getText('toolingSupplierList.title')}"/>
   </#if>
</#if>
<@htmlPage title="${toolingDevName}">
	
	<@ww.form name="'listForm'" action="'searchSupplier'" method="'post'">
	<@ww.token name="searchSupplierToken"/>
	
	<#include "./supplierSearch.ftl"/>
	
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@buttonBar>
		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
		<#if !(action.isReadOnly())>
		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/prophase/supplier/editSupplier.html?toolingDevFlag=${toolingDevFlag?if_exists}"/>
	    </#if>
	</@buttonBar>
	
	<@list title="${toolingListName}" 
	includeParameters="id|readOnly|supplierNo|name|country|zone|cartory.name|level.id||docState.code|onlyValid|onlyInvalid|toolingDevFlag" 
	fieldMap="like:id|supplierNo|name|country|zone|cartory.name" >
            <#if (object.disabled)>
            <@vlh.checkbox property="id" name="supplierIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('supplier.cartory')}" property="supplierType.value" >
             ${object.supplierNo}
            <@alignLeft />
            </@vcolumn>
            <#else>
            <@vlh.checkbox property="id" name="supplierIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('supplier.supplier_no')}" property="supplierNo" sortable="desc">
                <a href="editSupplier.html?toolingDevFlag=${toolingDevFlag}&supplier.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.supplierNo}</a>
                <@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('supplier.name')}" property="name" sortable="desc" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.en_name')}" property="enName" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.supplierType')}" property="supplierType.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.level')}" property="level.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.country')}" property="country.name" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.zone')}" property="zone" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier.register_funds')}" property="registeredFunds">
            <@alignRight />
            </@vcolumn>
     </@list>
     <#if !first>
     <#if !(action.isReadOnly())>
        <@buttonBar>
          <@eam2008_disabledOrEnabled_button message="${action.getText('supplierDevice')}" boxName="supplierIds" jsFunctionName="checkInvalidParms()"/>
       </@buttonBar>
     </#if>
     </#if>
     </@ww.form>
</@htmlPage>
 <script language="JavaScript" type="text/JavaScript"> 
      window.onload = function () {
      
	    if ('${onlyInvalid?string}' == 'true') {
	      getObjByNameRe("onlyDisabled").checked=true;
	    }
	  }
	  function validateInvalid(delFun, checkFun) {
         if (delFun) {
         	checkFun;
         	return true;
         }
         return false;
       } 	 
    </script>