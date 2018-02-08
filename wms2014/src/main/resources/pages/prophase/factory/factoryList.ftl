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
<@htmlPage title="${action.getText('factory.title')}">
	
	<@ww.form name="'listForm'" action="'searchFactory'" method="'post'">
	<@ww.token name="searchFactoryToken"/>
	
	<#include "./factorySearch.ftl"/>
	
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@buttonBar>
		<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
		<#if !(action.isReadOnly())>
		  <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/prophase/factory/editFactory.html"/>
	    </#if>
	</@buttonBar>
	
	<@list title="${action.getText('factory.list')}" 
	includeParameters="id|readOnly|supplierNo|name|country|zone|cartory.name|onlyValid|onlyInvalid" 
	fieldMap="like:id|supplierNo|name|country|zone|cartory.name" >
            <#if (object.disabled)>
            <@vlh.checkbox property="id" name="factoryIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('factory.cartory')}" property="supplierType.value" >
             ${object.supplierNo}
            <@alignLeft />
            </@vcolumn>
            <#else>
            <@vlh.checkbox property="id" name="factoryIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('factory.supplierNo')}" property="supplierNo" sortable="desc">
                <a href="editFactory.html?factory.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.supplierNo}</a>
                <@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('factory.name')}" property="name" sortable="desc" >
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.supplierType')}" property="supplierType.value" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.country')}" property="country.name" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.zone')}" property="zone" sortable="desc">
            <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('factory.register_funds')}" property="registeredFunds">
            <@alignRight />
            </@vcolumn>
     </@list>
     <#if !first>
     <#if !(action.isReadOnly())>
        <@buttonBar>
          <@eam2008_disabledOrEnabled_button message="${action.getText('supplierDevice')}" boxName="factoryIds" jsFunctionName="checkInvalidParms()"/>
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