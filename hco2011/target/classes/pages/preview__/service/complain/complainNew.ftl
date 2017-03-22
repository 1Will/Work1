<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户投诉维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'投诉主题'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'客户'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'服务人员'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'联系人'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'电话'" name="'customerName'" value="''" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.select label="'投诉类型'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'类型一',
					'类型二'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'紧急程度'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'紧急',
					'一般'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
  		<@ww.textfield label="'花费时间'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@pp.datePicker 
			label="'开始日期'" 
			name="'consultationTime'" 
   			value="''"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@ww.select label="'处理状态'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'处理中',
					'已解决'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
   		<@ww.select label="'处理方式'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'方式一',
					'方式二'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
   		<@ww.select label="'回访确认'"
				required="false"
				name="'returnVisit'" 
				value="''" 
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
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('投诉内容')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('处理结果')}:</label>
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
	       		<label class="label">${action.getText('备注')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" ></textarea>
			</td>
	</tr>	
               
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listComplain_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
