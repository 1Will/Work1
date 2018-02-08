<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('checkPointRuleDetail.title')}">
 <base target="_self">
	 <@ww.form name="'detail'" action="'saveCheckPointRuleDetail'" method="'post'" validate="true" >
	 	  <@ww.token name="saveCheckPointRuleDetailToken"/>
	 	  <@ww.hidden name="'rule.id'" value="'${req.getParameter('rule.id')?if_exists}'"/>
	 	  <@ww.hidden name="'detail.id'" value="'${req.getParameter('detail.id')?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.part')}'" name="'detail.part'" value="'${detail.part?if_exists}'" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.content')}'" name="'detail.content'" value="'${detail.content?if_exists}'" cssClass="'underline'" required="true" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.method')}'" name="'detail.method'" value="'${detail.method?if_exists}'" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.fee')}'" name="'detail.fee'" value="'${detail.fee?if_exists}'" cssClass="'underline'" required="true"/>
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.rule')}'" name="'detail.rule'" value="'${detail.rule?if_exists}'" cssClass="'underline'" />
	 	  		<@ww.textfield label="'${action.getText('ruleDetail.tool')}'" name="'detail.tool'" value="'${detail.tool?if_exists}'" cssClass="'underline'"  />	
	 	  	</tr>
	 	  	<tr>
				 <@oneLine>
					<@ww.textarea label="'${action.getText('ruleDetail.comment')}'" 
					         name="'detail.comment'" 
					         value="'${detail.comment?if_exists}'" rows="'4'" cols="'80'" 
							 required="false"/>
				</@oneLine>
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	  		<input type="submit" class="button" name="save" value="${action.getText('save')}" />
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>

	 </@ww.form>
</@htmlPage>