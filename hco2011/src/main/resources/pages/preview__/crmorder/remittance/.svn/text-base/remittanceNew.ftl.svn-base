<#include "../../../includes/macros.ftl" />
<@htmlPage title="汇款维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
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
  		<@ww.textfield label="'经办人员'" name="'customerName'" value="''" cssClass="'underline'"/>
  		<@ww.textfield label="'客户订单'" name="'customerName'" value="''" cssClass="'underline'"/>
 	</tr>
	<tr>
 		<@ww.textfield label="'客户合同'" name="'customerName'" value="''" cssClass="'underline'"/>
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
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listRemittance_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>