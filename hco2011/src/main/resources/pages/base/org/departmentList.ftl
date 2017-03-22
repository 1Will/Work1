<#-- $Id: departmentList.ftl 9491 2007-12-19 07:06:13Z mwei $-->
<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('department.title')}">
    <@ww.form name="'listForm'" action="'searchDepartments'" method="'post'">
        <@ww.token name="searchDepartmentsToken"/>
        <#include "departmentSearcher.ftl"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@buttonBar> 
	 		 <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
	 		 <#if !(action.isReadOnly())>   
	 		 <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/base/department/editDepartment.html"/>
	 		 </#if>    
        </@buttonBar>
        <@list title="${action.getText('department.list')}" includeParameters="id|name|code|leader|onlyInvalid|onlyValid" fieldMap="like:id|name|code|leader">
             <@vlh.checkbox property="id" name="departmentIds">
                 <@vlh.attribute name="width" value="30"/>
             </@vlh.checkbox>
             <@vcolumn title="${action.getText('deparment.code')}" property="code" sortable="desc">  
                 <a href="editDepartment.html?department.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
             </@vcolumn> 
             <@vcolumn title="${action.getText('department.name')}" property="name" sortable="desc" />
             <#assign parentDept=""/>
             <#if object.parentDept?exists>
               <#assign parentDept="${object.parentDept.name}"/>
             <#else>
               <#assign parentDept="无"/>
             </#if>
             <@vcolumn title="${action.getText('parent.department')}" property="parentDept" sortable="desc">
               ${parentDept}
             </@vcolumn>  
             <@vcolumn title="${action.getText('department.leader')}" property="leader" sortable="desc"/>  
              <@vcolumn title="${action.getText('department.inst')}" property="inst.name" sortable="desc"/>  
        </@list>
       <#if !first>
       <#if !(action.isReadOnly())>
        <@buttonBar>
          <@police_disabledOrEnabled_button message="${action.getText('department')}" boxName="departmentIds" jsFunctionName="checkInvalidParms()"/>
       </@buttonBar>
       </#if>
       </#if>
    </@ww.form>
</@htmlPage>