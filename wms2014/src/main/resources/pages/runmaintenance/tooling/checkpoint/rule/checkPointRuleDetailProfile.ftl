<#include "../../../../includes/macros.ftl" />
<@htmlPage title="点检标准明细维护">
 <base target="_self">
	 <@ww.form name="'detail'" action="''" method="'post'" validate="true" >
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'点检项目'" name="'detail.part'" value="" cssClass="'underline'" required="true" />
	 	    </tr>
	 	    <tr>
	 	  		<@ww.textarea label="'点检内容'" 
					         name="'detail.comment'" 
					         value="" rows="'4'" cols="'80'" 
							 required="false"/>
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	  		<input type="submit" class="button" name="save" value="${action.getText('save')}" />
	        	<input type="button" value="${action.getText('close')}" class="button" onclick="window.close()">
	     </@buttonBar>

	 </@ww.form>
</@htmlPage>