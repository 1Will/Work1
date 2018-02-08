<@inputTable>
    <tr>
        <@ww.textfield label="'${action.getText('msg.name')}'" name="'msg.name'" value="'${req.getParameter('msg.name')?if_exists}'"  cssClass="'underline'" />
        <@ww.textfield label="'${action.getText('msg.content')}'" name="'msg.content'" value="'${req.getParameter('msg.content')?if_exists}'"  size="32"  cssClass="'underline'" />
        <@pp.datePicker label="'${action.getText('msg.publishDate')}'" name="'accidentBill.accidentOccurDateTm'"
	     							value="'${(msg.publishDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"/>
               
    </tr>
</@inputTable>