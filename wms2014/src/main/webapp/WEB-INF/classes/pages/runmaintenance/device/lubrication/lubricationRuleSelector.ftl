<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('lubricationRule.title')}">
<base target="_self">
    <@ww.form name="'listForm'" action="'lubricationRuleSelector'" method="'post'">
        <@ww.token name="searchLubricationRuleToken"/>
<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('lubricationRule.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('lubricationRule.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/> 
    </tr>
</@inputTable>
        <@buttonBar>
            <@vsubmit value="'${action.getText('search')}'" />
        </@buttonBar>
        <@list title="${action.getText('country.list')}" includeParameters="code|name" fieldMap="like:code|name" >
            <@vcolumn title="${action.getText('lubricationRule.code')}" property="code" sortable="desc">
                <a href="javascript: returnDialog(new Array(#{object.id}, '${object.value}', '${object.code}'));">${object.code}</a>
            </@vcolumn>
            <@vcolumn title="${action.getText('lubricationRule.name')}" property="value" sortable="desc"/>
        </@list>
    </@ww.form>
</@htmlPage>
<script language="JavaScript" type="text/JavaScript">
	function checkInvalidParms(){
		return true;
	}
</script>