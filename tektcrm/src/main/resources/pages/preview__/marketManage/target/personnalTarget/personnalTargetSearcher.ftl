<#include "../../../../includes/macros.ftl" />
<@inputTable>
	<tr>
	<@ww.select label="'年度目标'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'2009',
				'2010',
				'2011',
				'2012'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	<@ww.select label="'季度目标'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'第一季度',
				'第二季度',
				'第三季度',
				'第四季度'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	<@ww.select label="'月度目标'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'1',
				'2',
				'3',
				'4',
				'5'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	</tr>
</@inputTable>