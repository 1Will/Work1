<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场策划维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'业务编号'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'策划时间'" 
			name="'consultationTime'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@ww.textfield label="'负责人'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
	</tr>

	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('策划主题')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"  >关于相亲</textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('策划描述')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"  >crm的功能补充</textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listPlanning_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" class="selectedtab" href="${req.contextPath}/upload_/listUpload_.html" target="frame" >${action.getText('上传附件')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/upload_/listUpload_.html" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
