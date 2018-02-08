<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="工装点检标准明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  <tr>
	 	  	<@ww.textfield label="'点检部位'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	 	  	<@ww.textfield label="'点检内容'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	 	  </tr>
	 	  <tr>
	 	  	<@ww.textfield label="'点检方法'" name="'area.code'" value=""  required="true" cssClass="'underline'" />
	 	  	<@ww.textfield label="'点检标准'" name="'area.code'" value=""  required="false" cssClass="'underline'" />
	 	  </tr>	 	 
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	 </@ww.form>
</@htmlPage>