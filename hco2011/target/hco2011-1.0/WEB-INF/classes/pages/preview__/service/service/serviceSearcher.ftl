<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编号'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'服务主题'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'开始日期'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
	<tr>
		<@ww.select label="'服务类型'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'类型一',
					'类型二'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'服务方式'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'上门',
					'到服务站'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
</@inputTable>