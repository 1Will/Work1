<#include "../../../includes/macros.ftl" />
<@htmlPage title="${action.getText('checkPointPlanDetail.title')}">
 <base target="_self">
	 <@ww.form name="'detail'" action="'saveCheckPointPlanDetail'" method="'post'" validate="true">
	 	  <@ww.token name="saveCheckPointPlanDetailToken"/>
	 	  <@ww.hidden name="'plan.id'" value="'${req.getParameter('plan.id')?if_exists}'"/>
	 	  <@ww.hidden name="'detail.id'" value="'${req.getParameter('detail.id')?if_exists}'"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('planDetail.part')}'" name="'detail.part'" value="'${detail.part?if_exists}'" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'${action.getText('planDetail.content')}'" name="'detail.content'" value="'${detail.content?if_exists}'" cssClass="'underline'" required="true" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('planDetail.rule')}'" name="'detail.rule'" value="'${detail.rule?if_exists}'" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'${action.getText('planDetail.method')}'" name="'detail.method'" value="'${detail.method?if_exists}'" cssClass="'underline'" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'${action.getText('planDetail.fee')}'" name="'detail.fee'" value="'${detail.fee?if_exists}'" cssClass="'underline'" required="true"/>
	 	  		<@ww.textfield label="'${action.getText('planDetail.tool')}'" name="'detail.tool'" value="'${detail.tool?if_exists}'" cssClass="'underline'"  />	
	 	  	</tr>
	 	  	<tr>	
	 	  		<@ww.textarea label="'${action.getText('planDetail.comment')}'" 
					         name="'detail.comment'" 
					         value="'${detail.comment?if_exists}'" rows="'3'" cols="'50'" 
							 />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	 <@vsubmit name="'save'" value="'${action.getText('save')}'" />
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">

	     </@buttonBar>
		<script language="javascript">
			
		</script>
	 </@ww.form>
</@htmlPage>