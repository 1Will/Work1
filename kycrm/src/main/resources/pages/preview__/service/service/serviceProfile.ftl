<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户服务维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="'bm_001'" cssClass="'underline'"/>
   		<@ww.textfield label="'服务主题'" name="'customerName'" value="'EAM系统升级'" cssClass="'underline'"/>
   		<@ww.textfield label="'客户'" name="'customerName'" value="'JAC'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'服务人员'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
   		<@ww.textfield label="'联系人'" name="'customerName'" value="'刘伟'" cssClass="'underline'"/>
   		<@ww.textfield label="'电话'" name="'customerName'" value="'12345678901'" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.select label="'服务类型'"
				required="false"
				name="'returnVisit'" 
				value="'类型一'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'类型一',
					'类型二'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'服务方式'"
				required="false"
				name="'returnVisit'" 
				value="'上门'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'上门',
					'到服务站'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
   		<@ww.textfield label="'花费时间'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'费用'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'开始日期'" 
			name="'consultationTime'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@ww.select label="'状态'"
				required="false"
				name="'returnVisit'" 
				value="''" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'服务期间',
					'服务过期'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('服务内容')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" ></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('客户反馈')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"  ></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('对应QA')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"  ></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('备注')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" ></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
               
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listService_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
