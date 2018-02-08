<#-- $Id: masterTypeDetailProfile.ftl 6934 2007-09-06 10:15:04Z qsun $ -->
<#include "../../includes/macros.ftl" />
<@htmlPage>
	<base target="_self">
    <@ww.form  namespace="'/popup'" name="'detail'" action="'saveMasterTypeDetail'" method="'post'" validate="true" >
        <@ww.token name="saveMasterDetailToken"/>
        <@ww.hidden name="'master.id'" value="'${req.getParameter('master.id')?if_exists}'"/>
        <@ww.hidden name="'detail.id'" value="'${req.getParameter('detail.id')?if_exists}'"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('masterTypeDetail.idx')}'" name="'masterTypeDetail.idx'" value="" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'${action.getText('masterTypeDetail.name')}'" name="'detail.name'" value="'${detail.name?if_exists}'"  size="32"  cssClass="'underline'" required="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
            <@vbutton class="button" value="${action.getText('close')}" onclick="javascript:window.close()"/>
        </@buttonBar>
    </@ww.form>
</@htmlPage>