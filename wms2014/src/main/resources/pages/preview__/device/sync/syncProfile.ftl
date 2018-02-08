<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="${action.getText('sync.maintain')}">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('sync.time')}'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'${action.getText('sync.mrp.host')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('sync.mrp.port')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'${action.getText('sync.mrp.db')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('sync.mrp.table')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
            </tr>
                <@ww.textfield label="'${action.getText('sync.mrp.user')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('sync.mrp.password')}'" name="'area.localeName'" value=""  size="32"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/preview/device/sync/listSyncHistories.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/device/sync/listSyncHistories.html"/>	
        </@buttonBar>
    </@ww.form>
</@htmlPage>