<#include "../../../includes/macros.ftl" />
<@htmlPage title="竞争对手维护">
<@ww.form name="'listForm'" action="''" method="'post'">
	<@ww.token name="saveCEInfo_Token"/>
	<@inputTable>
	<tr>
		<@ww.textfield label="'编码'" name="'customerCode'" value="'bh_001'" cssClass="'underline'"/>
   		<@ww.textfield label="'竞争公司名称'" name="'customerName'" value="'江淮汽车'" cssClass="'underline'"/>
   		<@ww.textfield label="'企业法人'" name="'customerName'" value="''" cssClass="'underline'"/>
   		
	</tr>
	<tr>
		<@ww.textfield label="'联系电话'" name="'customerCode'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'传真'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'网站'" name="'customerCode'" value="'www.jac.cn'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'企业规模 '" name="'customerName'" value="'500人以上'" cssClass="'underline'"/>
   		<@ww.select label="'行业'"
				required="false"
				name="'制造'" 
				value="selectedType" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'零售',
					'制造'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
		<@ww.select label="'性质'"
				required="false"
				name="'returnVisit'" 
				value="'国企'" 
				headerKey="1"
				headerValue="selectedType"
			    list="{
			    	'',
					'私营',
					'国企'
					}"
		    	emptyOption="false" 
		    	disabled="false"/>   
 	</tr>
	<tr>		
   		<@ww.textfield label="'主要经营 '" name="'customerName'" value="'汽车'" cssClass="'underline'"/>
   		<@ww.textfield label="'经营策略'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'竞争客户名称'" name="'customerCode'" value="'AAAA'" cssClass="'underline'"/>
	</tr>
	<tr>
   		<@ww.textfield label="'竞争产品'" name="'customerName'" value="'CRM'" cssClass="'underline'"/>
   		<@ww.textfield label="'价格'" name="'customerName'" value="''" cssClass="'underline'"/>
		<@ww.textfield label="'竞争能力'" name="'customerCode'" value="'弱'" cssClass="'underline'"/>
	</tr>              
	<tr>
   		<@ww.textfield label="'目标市场'" name="'customerName'" value="''" cssClass="'underline'"/>
   		<@ww.textfield label="'发展趋势'" name="'customerName'" value="''" cssClass="'underline'"/>
	</tr>
	<tr>
		<td align="right" valign="top">
       		<!--<span class="required">*</span>-->
       		<label class="label">${action.getText('地址')}:</label>
     	</td>
		<td colspan="10">
			<input type="text" name="customerInfo.address" class="underline"  value="" maxlength="140" size="120" />
		</td>
	</tr>
	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('资源情况')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('我们的优势')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('我们的劣势')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('应对策略')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
 	<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('备注')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4" cols="150" ></textarea>
			</td>
	</tr>	
</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="listCompetitor_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>