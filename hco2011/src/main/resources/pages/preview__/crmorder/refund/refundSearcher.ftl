<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'退货时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
 	</tr>
	<tr>
 		<@ww.textfield label="'退货产品'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
</@inputTable>