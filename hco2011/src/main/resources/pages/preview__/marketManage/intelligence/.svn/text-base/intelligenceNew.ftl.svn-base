<#include "../../../includes/macros.ftl" />
<@htmlPage title="市场活动维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'情报编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'相关客户'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'业务员'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'对方联系人'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'对方联系电话'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'对方电子邮件'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
	<@pp.datePicker 
			label="'日期'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
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
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('主题')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('内容')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('竞争者产品动向 ')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('竞争者价格动向')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('竞争者促销动向')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('竞争者广告动向')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('竞争者分销渠道')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('客户意见')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('备注')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
               
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listIntelligence_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>