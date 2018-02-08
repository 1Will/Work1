
<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('department.title1')}">
     <@ww.form  name="'department'" action="'saveDepartment'" method="'post'" validate="true">
       <@ww.token name="saveDepartmentProfileToken"/>
         <@inputTable>
            <#if department.id?exists>
                <@ww.hidden name="'department.id'" value="#{department.id}"/>
            </#if>
            <@ww.hidden name="'department.version'" value="#{department.version?if_exists}"/>
             <tr>
                 <@ww.textfield label="'${action.getText('department.name')}'" name="'department.name'" value="'${department.name?if_exists}'" cssClass="'underline'" required="true"/>
                 <@ww.textfield label="'${action.getText('department.leader')}'" name="'department.leader'" value="'${department.leader?if_exists}'" cssClass="'underline'"/>
             </tr>
             <tr>
                  <@ww.textfield label="'${action.getText('department.tel')}'" name="'department.tel'" value="'${department.tel?if_exists}'" cssClass="'underline'" required="true"/>
             </tr>
         </@inputTable>
         
         <@buttonBar>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" />
	          
	         <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('department.name')}[${department.name?if_exists}]?" />
	         <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	             <#if department.id?exists>
	                 <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	             </#if>
	             <@ww.param name="'disabled'" value="${(!department.id?exists)?string}"/>
	         </@vsubmit>
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/department/listDepartments.html"/>
         </@buttonBar>
     </@ww.form>
</@htmlPage>
