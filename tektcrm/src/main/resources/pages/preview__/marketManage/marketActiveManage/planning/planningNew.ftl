<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场策划维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'业务编号'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'策划时间'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@ww.textfield label="'负责人'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>

	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('策划主题')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('策划描述')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listPlanning_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>