<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场活动维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'主题'" name="'customerCode'" value="'世博会'" cssClass="'underline'"/>
   		<@ww.textfield label="'对象客户'" name="'customerName'" value="'游客'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'活动目的'" name="'customerCode'" value="'介绍本公司产品'" cssClass="'underline'"/>
   		<@pp.datePicker 
			label="'开始时间'" 
			name="'consultationTime1'" 
   			value="'2010-04-20'"
			cssClass="'underline'" 
			dateFormat="'%Y-%m-%d'"/>
   		<@pp.datePicker 
			label="'结束时间'" 
			name="'consultationTime2'" 
   			value="'2010-04-21'"
			cssClass="'underline'"
			dateFormat="'%Y-%m-%d'"/>
	</tr>
	<tr>
		<@ww.textfield label="'地点'" name="'customerCode'" value="'上海'" cssClass="'underline'"/>
		<@ww.select label="'活动类别'"
				required="false"
				name="'returnVisit'" 
				value="'重要活动'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'重要活动',
					'不重要活动'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
   		<@ww.select label="'状态'"
				required="false"
				name="'returnVisit'" 
				value="'计划中'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'计划中',
					'进行中',
					'结束'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
	</tr>
	<tr>
		<@ww.select label="'进展情况(阶段)'"
				required="false"
				name="'returnVisit'" 
				value="'准备阶段'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'筹划阶段',
					'准备阶段',
					'实行阶段'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
		<@ww.select label="'优先级别'"
				required="false"
				name="'returnVisit'" 
				value="'A级别'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'A级别',
					'B级别',
					'C级别'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
   		<@ww.textfield label="'参与人员'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'费用'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('活动内容')}:</label>
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
	       		<label class="label">${action.getText('活动总结')}:</label>
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
	       		<label class="label">${action.getText('效果评估')}:</label>
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
				<textarea name="contactArchives.comment" rows="4"  ></textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	</tr>	
               
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listActivity_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>
<ul id="beautytab">
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" class="selectedtab" href="${req.contextPath}/upload_/listUpload_.html" target="frame" >${action.getText('上传附件')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/upload_/listUpload_.html" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
