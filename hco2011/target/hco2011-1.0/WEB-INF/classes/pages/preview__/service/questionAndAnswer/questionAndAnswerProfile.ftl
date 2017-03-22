<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户服务维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="'bm_001'" cssClass="'underline'"/>
		<@ww.textfield label="'适用产品'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'版本号'" name="'customerName'" value="'1.0.1'" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.select label="'类型'"
				required="false"
				name="'returnVisit'" 
				value="'系统升级'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'系统升级',
					'漏洞修复'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'状态'"
				required="false"
				name="'returnVisit'" 
				value="'已解决'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'已解决',
					'未解决'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'严重程度'"
				required="false"
				name="'returnVisit'" 
				value="'严重'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'严重',
					'一般'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('问题')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" >系统无法升级</textarea>
			</td>
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
	       		<label class="label">${action.getText('内部提示')}:</label>
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
		<@redirectButton value="${'返回'}" url="listQuestionAndAnswer_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
