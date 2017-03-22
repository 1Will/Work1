<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'日期'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@ww.select label="'类型'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'普通汇款',
					'快件汇款',
					'电报汇款'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
 	</tr>
	<tr>
 		<@ww.textfield label="'汇款客户'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.select label="'是否开票'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'是',
					'否'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
	</tr>

</@inputTable>