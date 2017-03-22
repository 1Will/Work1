<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户需求维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
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
		<@ww.textfield label="'机率'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'需求内容'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户预算'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'客户决策人'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户联系人'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户电话'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'客户电子邮件'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'下次联系时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
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
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('解决方案')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('客户反馈')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('联系内容')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listRequirement_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>