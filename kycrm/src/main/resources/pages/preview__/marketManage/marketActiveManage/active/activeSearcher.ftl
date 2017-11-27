<#include "../../../../includes/macros.ftl" />
<@inputTable>
	<tr>
		<@ww.textfield label="'主题'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'对象客户'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'开始时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
	<tr>
   		<@pp.datePicker 
			label="'结束时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'"
			dateFormat="'%Y-%m-%d'"/>
		<@ww.select label="'活动类别'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'重要活动',
				'不重要活动'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
		<@ww.select label="'进展情况(阶段)'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'筹划阶段',
					'准备阶段',
					'实行阶段'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select label="'优先级别'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'A级别',
					'B级别',
					'C级别'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.textfield label="'负责人'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.select label="'状态'"
				required="false"
				name="'returnVisit'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'计划中',
					'进行中',
					'结束'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
</@inputTable>