<#-- $Id: departmentList.ftl 9491 2007-12-19 07:06:13Z mwei $-->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('department.title')}">
    <@ww.form name="'listForm'" action="'searchDepartments'" method="'post'">
        <@ww.token name="searchDepartmentsToken"/>
        <#include "departmentSearcher.ftl"/>
        <@buttonBar> 
	 		 <@vsubmit value="'${action.getText('search')}'"  onclick="'return checkInvalidParms();'"/>   
	 		 <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/department/editDepartment.html"/>    
        </@buttonBar>
        <@list title="${action.getText('department.list')}" includeParameters="id|name|code|filiale.id|leader|onlyInvalid|onlyValid" fieldMap="like:id|name|code|leader">
             <@vlh.checkbox property="id" name="departmentIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox>
             <@vcolumn title="${action.getText('deparment.code')}" property="code" sortable="desc">  
                 <a href="editDepartment.html?department.id=#{object.id}">${object.code}</a>
             </@vcolumn> 
             <@vcolumn title="${action.getText('department.name')}" property="name" sortable="desc" />
             <@vcolumn title="${action.getText('filiale')}" property="filiale.name" sortable="desc"/>  
             <@vcolumn title="${action.getText('department.leader')}" property="leader" sortable="desc"/>  
             <@vcolumn title="排序号" property="sortIdx" sortable="desc"/>  
        </@list>
       <#if !first>
	        <@buttonBar>
              <#-- <@vsubmit name="'disable'" value="'${action.getText('disable')}'">
                <@ww.param name="'onclick'" value="'return validateDelete(confirmInvalids(\"departmentIds\", \"${action.getText('confirm.disable')}\"), checkInvalidParms());'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>    -->
             <@eam2008_disabledOrEnabled_button message="${action.getText('department')}" boxName="departmentIds" jsFunctionName="checkInvalidParms()"/>
	        </@buttonBar>
        </#if>
    </@ww.form>
     
</@htmlPage>