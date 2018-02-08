<#--$Id: syncProfile.ftl 8874 2007-12-02 02:47:00Z qsun $ -->
<#include "../../includes/eam2008.ftl" />
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
        	<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/dataimport/mrpsync/listSyncHistories.html"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/dataimport/mrpsync/listSyncHistories.html"/>	
        </@buttonBar>
    </@ww.form>
</@htmlPage>