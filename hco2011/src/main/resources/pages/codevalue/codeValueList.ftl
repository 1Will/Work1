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

<#--$Id: codeValueList.ftl 11325 2008-03-15 06:48:17Z wzou $ -->
<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('codevalueList.title')}">
	<@ww.form name="'listForm'" action="'searchCodeValues'" method="'post'">
        <@ww.token name="searchCodeValuesToken"/>
        <#include "./codeValueSearcher.ftl" />
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
            <#-- 编码大类不需要新建，代码屏蔽，预留
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/codevalue/editCodeValue.html"/>
       		-->
        </@buttonBar>
        <@list title="${action.getText('code.list')}" includeParameters="code|name|onlyInvalid|onlyValid" fieldMap="like:code|name" >
	        <#--
	        <@vlh.checkbox property="id" name="codeValueIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	        -->
            <@vcolumn title="${action.getText('codevalue.code')}" property="code" sortable="asc">
                <a href="editCodeValue.html?type.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
                <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('code.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>          
        </@list>
        <#--
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
         <#if (action.isOnlyInvalid())>
			<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'">
			  <@ww.param name="'onclick'" value="'return validateInvalid(confirmValids(\"codeValueIds\", \"${action.getText('confirm.valid')}${action.getText('codevalue')}?\"));'"/>
			  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
			</@vsubmit>
		  <#else>
			<@vsubmit name="'disabled'" value="'${action.getText('disabled')}'">
			  <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"codeValueIds\", \"${action.getText('confirm.inValid')}${action.getText('codevalue')}?\"));'"/>
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
       </@buttonBar>
       </#if>
       </#if>
       -->
    </@ww.form>
</@htmlPage>