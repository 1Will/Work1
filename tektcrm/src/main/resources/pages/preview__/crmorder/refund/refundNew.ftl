<#include "../../../includes/macros.ftl" />
<@htmlPage title="退货维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'分销商'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'客户'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
 	<tr>
		<@ww.textfield label="'销售订单'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'业务员'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'退货产品'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
 	<tr>
		<@ww.textfield label="'退货金额'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@pp.datePicker 
			label="'退货时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	</tr>
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('退货原因')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
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
		<@redirectButton value="${'返回'}" url="listRefund_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>