<#include "../../includes/macros.ftl" />
<@framePage>
	<@ww.form namespace="''" name="'manual'" action="'saveManual'" enctype="'multipart/form-data'" method="'post'" validate="true">
	  	<@ww.token name="saveManualToken"/>
		<@inputTable>
		  <tr>
		    <@ww.file label="'${action.getText('上传附件')}'" size="60" name="'file'" onchange="'getName();'"/>
		  </tr>
		</@inputTable>
	</@ww.form>
</@framePage>