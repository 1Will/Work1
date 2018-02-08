<@inputTable>
	<tr>
	<!--计划模板编码-->
	  <@ww.textfield label="'${action.getText('code')}'" name="'proPlanTemplet.code'" value="'${req.getParameter('proPlanTemplet.code')?if_exists}'" cssClass="'underline'"  />
	  <!--计划名称-->
	  <@ww.textfield label="'${action.getText('name')}'" name="'proPlanTemplet.name'" value="'${req.getParameter('proPlanTemplet.name')?if_exists}'" cssClass="'underline'"/>
	   <!--模板名称-->
	 <@ww.textfield label="'${action.getText('proPlanTemplet.proplanName')}'" name="'proPlanTemplet.proplanName'" value="'${req.getParameter('proPlanTemplet.proplanName')?if_exists}'" cssClass="'underline'"/>
	  </tr>
	 
</@inputTable>