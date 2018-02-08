<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户订单维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
  		<@ww.textfield label="'客户'" name="'customerName'" value="'JAC'" cssClass="'underline'"/>
  		<@ww.select label="'订单类型'"
				required="false"
				name="'returnVisit'" 
				value="'紧急'" 
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
		<@ww.textfield label="'订购产品'" name="'customerName'" value="'轮胎'" cssClass="'underline'"/>
  		<@pp.datePicker 
			label="'下单时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
  		<@pp.datePicker 
			label="'送货时间'" 
			name="'consultationTime'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
	<tr>
		<@ww.select label="'送货方式'"
				required="false"
				name="'returnVisit'" 
				value="'快递'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'快递',
					'平邮'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
   		<@ww.textfield label="'联系人 '" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
		<@ww.textfield label="'联系电话'" name="'customerCode'" value="'12345678901'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'业务员'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<td align="right" valign="top">
       		<!--<span class="required">*</span>-->
       		<label class="label">${action.getText('联系地址 ')}:</label>
     	</td>
		<td colspan="10">
			<input type="text" name="customerInfo.address" class="underline"  value="" maxlength="140" size="120" />
		</td>
	</tr>
	 <tr>
		<td align="right" valign="top">
       		<!--<span class="required">*</span>-->
       		<label class="label">${action.getText('备注')}:</label>
     	</td>
		<td colspan=10">
			<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
		</td>
	</tr>
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listOrder_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>