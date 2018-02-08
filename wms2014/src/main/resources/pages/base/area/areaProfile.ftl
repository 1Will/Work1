<#--$Id: areaProfile.ftl 6888 2007-09-05 13:24:56Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <#if area.id?exists>
                <@ww.hidden name="'area.id'" value="#{area.id}"/>
            </#if>
            <@ww.hidden name="'area.version'" value="#{area.version?if_exists}"/>
            <tr>
                <@ww.textfield label="'${action.getText('area.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('area.name')}'" name="'area.name'" value="'${area.name?if_exists}'"  size="32"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
                <#if area.id?exists>
                    <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('area')}?" />
                    <@vsubmit name="'delete'" value="'${action.getText('invalidation')}'">
                    <#if area.id?exists>
                        <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
                    </#if>
                <@ww.param name="'disabled'" value="${(!area.id?exists)?string}"/>
            </@vsubmit>
               </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/area/listAreas.html"/>
        </@buttonBar>
    </@ww.form>
</@htmlPage>