<#include "../../../includes/macros.ftl" />
<@htmlPage title="市场活动维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'情报编码'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
   		<@ww.textfield label="'相关客户'" name="'customerName'" value="'aaa'" cssClass="'underline'"/>
   		<@ww.textfield label="'业务员'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
   		
	</tr>
	<tr>
		<@ww.textfield label="'对方联系人'" name="'customerCode'" value="'刘伟'" cssClass="'underline'"/>
   		<@ww.textfield label="'对方联系电话'" name="'customerName'" value="'12345678901'" cssClass="'underline'"/>
   		<@ww.textfield label="'对方电子邮件'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
	<@pp.datePicker 
			label="'日期'" 
			name="'consultationTime'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
	<@ww.select label="'情报重要性'"
		required="false"
		name="'returnVisit'" 
		value="'重要'" 
		headerKey="1"
		headerValue="selectedType"
	    list="{
	    	'',
			'重要',
			'不重要'
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
			'重要',
			'不重要'
			}"
    	emptyOption="false" 
    	disabled="false"/>
	</tr>            
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('主题')}:</label>
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
	       		<label class="label">${action.getText('内容')}:</label>
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
	       		<label class="label">${action.getText('竞争者产品动向 ')}:</label>
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
	       		<label class="label">${action.getText('竞争者价格动向')}:</label>
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
	       		<label class="label">${action.getText('竞争者促销动向')}:</label>
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
	       		<label class="label">${action.getText('竞争者广告动向')}:</label>
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
	       		<label class="label">${action.getText('竞争者分销渠道')}:</label>
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
	       		<label class="label">${action.getText('客户意见')}:</label>
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
		<@redirectButton value="${'返回'}" url="listIntelligence_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>