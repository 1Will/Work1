<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'竞争公司名称'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.select label="'行业'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'零售',
					'制造'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
	</tr>
	<tr>
		<@ww.select label="'性质'"
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
   		<@ww.textfield label="'主要经营 '" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'竞争能力'" name="'customerCode'" value="''" cssClass="'underline'"/>
 	</tr>
	<tr>
   		<@ww.textfield label="'目标市场'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'竞争客户名称'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'竞争产品'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
</@inputTable>