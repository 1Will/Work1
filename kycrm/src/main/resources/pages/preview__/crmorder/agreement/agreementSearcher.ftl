<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
  		<@ww.textfield label="'名称'" name="'customerName'" value="''" cssClass="'underline'"/>		
  		<@ww.textfield label="'客户'" name="'customerName'" value="''" cssClass="'underline'"/>		
	</tr>
	<tr>		
		<@pp.datePicker 
			label="'签署时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
  		<@ww.textfield label="'签署产品'" name="'customerName'" value="''" cssClass="'underline'"/>		
	</tr>
</@inputTable>