<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户投诉维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="'bm_001'" cssClass="'underline'"/>
   		<@ww.textfield label="'投诉主题'" name="'customerName'" value="'EAM系统太慢'" cssClass="'underline'"/>
   		<@ww.textfield label="'客户'" name="'customerName'" value="'JAC'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'服务人员'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
   		<@ww.textfield label="'联系人'" name="'customerName'" value="'刘伟'" cssClass="'underline'"/>
   		<@ww.textfield label="'电话'" name="'customerName'" value="'12345678901'" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.select label="'投诉类型'"
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
		<@ww.select label="'紧急程度'"
				required="false"
				name="'returnVisit'" 
				value="'一般'" 
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
   			value="'2010-04-20'"
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
	       		<label class="label">${action.getText('处理结果')}:</label>
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
	       		<label class="label">${action.getText('客户反馈')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"></textarea>
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
				<textarea name="contactArchives.comment" rows="4"></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
               
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listComplain_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
