<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'主题'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
				label="'日期'" 
				name="'consultationTime'" 
	   			value="''"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"/>
   		<@ww.textfield label="'相关客户'" name="'customerName'" value="''" cssClass="'underline'"/>
   		
	</tr>
	<tr>
   		<@ww.textfield label="'业务员'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.select label="'情报重要性'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'重要',
				'不重要'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	   <@ww.select label="'状态'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'重要',
				'不重要'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
</@inputTable>