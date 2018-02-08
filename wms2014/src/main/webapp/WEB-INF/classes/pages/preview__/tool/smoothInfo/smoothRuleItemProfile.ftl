<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="润滑标准明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'润滑部位'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'润滑内容'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'润滑方法'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  		<@ww.textfield label="'润滑标准'" name="'area.code'" value="" cssClass="'underline'" required="true" />
	 	  	</tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" size="40"/>
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
</@htmlPage>