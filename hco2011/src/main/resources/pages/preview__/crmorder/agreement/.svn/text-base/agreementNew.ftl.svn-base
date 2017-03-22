<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户合同维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
  		<@ww.textfield label="'名称'" name="'customerName'" value="''" cssClass="'underline'"/>
  		<@pp.datePicker 
			label="'签署时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>	
	</tr>
	<tr>
  		<@ww.textfield label="'客户'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户签约人'" name="'customerName'" value="''" cssClass="'underline'"/>
 		<@ww.textfield label="'我方签约人'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
 		<@ww.textfield label="'对方联系电话'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="' 对方电子邮件'" name="'customerName'" value="''" cssClass="'underline'"/>
 		<@ww.textfield label="'签署产品'" name="'customerName'" value="''" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.textfield label="' 签署地点'" name="'customerName'" value="''" cssClass="'underline'"/>
 		<@ww.textfield label="'金额'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.select label="'付款方式'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'信用卡',
				'现金'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
 	</tr>
	<tr>
		<@ww.select label="'合同类型'"
			required="false"
			name="'returnVisit'" 
			value="selectedType" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
		    	'',
				'买卖合同',
				'技术合同'
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
				'签订',
				'未签订'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
  	</tr>
	<tr>
 		<@ww.textfield label="'客户订单'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'销售地区'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
 	<tr>
		<td align="right" valign="top">
       		<!--<span class="required">*</span>-->
       		<label class="label">${action.getText('合同内容')}:</label>
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
		<@redirectButton value="${'返回'}" url="listAgreement_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>