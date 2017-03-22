<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场调查维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'业务编号'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'调查时间'" 
			name="'consultationTime'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
		<@ww.textfield label="'调查对象'" name="'customerCode'" value="'使用客户'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'负责人'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
	</tr>

	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('调查主题')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" >关于CRM</textarea>
			</td>
	</tr>	
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('调查内容')}:</label>
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
				<textarea name="contactArchives.comment" rows="3" cols="120" >很好</textarea>
			</td>
	</tr>	
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('调查总结')}:</label>
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
		<@redirectButton value="${'返回'}" url="listSurvey_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" class="selectedtab" href="${req.contextPath}/upload_/listUpload_.html" target="frame" >${action.getText('上传附件')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/upload_/listUpload_.html" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
