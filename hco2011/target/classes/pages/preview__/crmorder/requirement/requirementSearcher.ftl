<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'联系日期'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
  		<@ww.textfield label="'标题'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
  		<@ww.textfield label="'客户'" name="'customerName'" value="''" cssClass="'underline'"/>
  		<@ww.textfield label="'提供者'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.select label="'重要性'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'重要',
					'一般'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
	</tr>
	<tr>
		<@ww.select label="'类型'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'私营',
					'国企'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>  
   		<@ww.textfield label="'业务员 '" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'负责人'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.select label="'状态'"
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
</@inputTable>