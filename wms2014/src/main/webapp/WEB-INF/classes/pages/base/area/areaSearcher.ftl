<#--$Id: areaSearcher.ftl 6888 2007-09-05 13:24:56Z qsun $ -->
<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('area.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('area.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
        <@ww.checkbox label="'${action.getText('invalid')}'"  name="'invalid'"  fieldValue="'true'" />        
    </tr>
</@inputTable>