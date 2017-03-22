<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
  		<@ww.textfield label="'客户'" name="'customerName'" value="''" cssClass="'underline'"/>		
  		<@ww.select label="'订单类型'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'紧急',
					'一般'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>  
	</tr>
	<tr>
		<@pp.datePicker 
			label="'送货时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
  		<@ww.textfield label="'订购产品'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.select label="'送货方式'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'快递',
					'平邮'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
   		<@ww.textfield label="'联系人 '" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'联系电话'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
</@inputTable>