<#include "../../../../includes/macros.ftl" />
<@htmlPage title="公司目标维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编号'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'目标名称'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'订单金额'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'销售回款'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'丢单比率'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'人均访问客户次数'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'业务员行动计划完成率'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'新客户开发数量'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'地区'" name="'customerCode'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'产品'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.select label="'月份'"
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
		<@redirectButton value="${'返回'}" url="listCompanyTarget_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>