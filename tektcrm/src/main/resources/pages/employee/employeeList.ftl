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
<@htmlPage title="${action.getText('employeeList.title')}">
	<@ww.form name="'listForm'" action="'searchEmployee'" namespace="'/employees'" method="'post'">
        <@ww.token name="searchEmployeeToken"/>
        <#include "./employeeSearcher.ftl" />
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'" />
            <#if !(action.isReadOnly())>
            <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/employees/editEmployee.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
            </#if>
        </@buttonBar>
        <@list title="${action.getText('employee.list')}" includeParameters="employeeNo|name|inst.id|dept.id|duty|onlyInvalid|onlyValid" fieldMap="like:employeeNo|name" >
	        <@vlh.checkbox property="id" name="employeeIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	        <#if !object.disabled>
		            <@vcolumn title="${action.getText('employee.no')}" property="employeeNo" sortable="asc">
		                <a href="editEmployee.html?employee.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.employeeNo}</a>
		                <@alignLeft/>
		            </@vcolumn>
	            <#else>
	            	<@vcolumn title="${action.getText('employee.no')}" property="employeeNo" sortable="asc">
		                ${object.employeeNo}
		                <@alignLeft/>
		            </@vcolumn>
	         </#if>
            <@vcolumn title="${action.getText('employee.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn> 
         <#assign sex="" />
         <#if object.sex>
         	<#assign sex="${action.getText('employee.sex.man')}" />
         	<#else>
         	<#assign sex="${action.getText('employee.sex.woman')}" />
         </#if>    
         <@vcolumn title="${action.getText('employee.sex')}" property="sex" sortable="desc">
         		${sex}
            	<@alignLeft/>
         </@vcolumn>
         <@vcolumn title="${action.getText('employee.inst')}" property="inst.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>  
         <@vcolumn title="${action.getText('employee.dept')}" property="dept.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>  
         <@vcolumn title="${action.getText('employee.duty')}" property="duty.name" sortable="desc">
            	<@alignLeft/>
         </@vcolumn>  
        </@list>
        
        <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@crm_disabledOrEnabled_button message="${action.getText('employee')}" boxName="employeeIds" jsFunctionName="checkInvalidParms()"/>
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
    </@ww.form>
</@htmlPage>