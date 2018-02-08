<#include "../../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'业务编号'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'调查主题'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'调查时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
	<tr>
		<@ww.textfield label="'调查对象'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'负责人'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
</@inputTable>