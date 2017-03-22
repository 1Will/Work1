<#include "../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'编号'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'问题'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'版本号'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.select label="'类型'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'系统升级',
					'漏洞修复'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'状态'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'已解决',
					'未解决'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'严重程度'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'严重',
					'一般'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
</@inputTable>