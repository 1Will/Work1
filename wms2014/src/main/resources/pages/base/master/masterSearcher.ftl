<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('masterType.code')}'" name="'code'" value="'${req.getParameter('masterType.code')?if_exists}'" cssClass="'underline'" />
        <@ww.textfield label="'${action.getText('masterType.name')}'" name="'name'" value="'${req.getParameter('masterType.name')?if_exists}'" cssClass="'underline'"/>
    </tr>
</@inputTable>