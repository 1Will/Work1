<#include "../../../../includes/macros.ftl" />
<@htmlPage title="个人目标维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编号'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
   		<@ww.textfield label="'业务员名称'" name="'customerName'" value="'陈鲍秀'" cssClass="'underline'"/>
   		<@ww.textfield label="'目标名称'" name="'customerName'" value="'卖出2套'" cssClass="'underline'"/>
 	</tr>
	<tr>
		<@ww.select label="'所属部门'"
				required="false"
				name="'returnVisit'" 
				value="'销售部'"
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'销售部',
					'研发部',
					'管理部'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>
  		<@ww.textfield label="' 联系客户数量'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'访问次数'" name="'customerCode'" value="''" cssClass="'underline'"/>
	<tr>
	</tr>
		<@ww.textfield label="'新客户开发数量'" name="'customerCode'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'产品'" name="'customerCode'" value="'crm'" cssClass="'underline'"/>
		<@ww.select label="'月份'"
				required="false"
				name="'returnVisit'" 
				value="'2'" 
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
		<@redirectButton value="${'返回'}" url="listPersonnalTarget_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>