<@inputTable>
    <@ww.hidden name="'includeValid'" value="true"/>
    <tr>
        <@ww.textfield label="'${action.getText('workType.code')}'" name="'code'" value="'${req.getParameter('code')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('workType.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    </tr>
</@inputTable>