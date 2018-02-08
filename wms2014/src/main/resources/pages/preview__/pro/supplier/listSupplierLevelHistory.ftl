 <#include "../../includes/macros2.ftl" />
 
<@htmlPage title="供应商级别变更">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <@inputTable>
<tr>
<@ww.textfield label="'供应商原等级'" name="'suppliers.code'" value="'一般'" cssClass="'underline'"  readonly="true" disabled="true"/>	
 <@ww.select label="'供应商新等级'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '恶劣',
	                    	       '一般',
	                    	       '良好',
	                    	       '优秀' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
</tr>
<tr>
<@pp.datePicker label="'变更时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"/>
<@ww.textfield label="'变更原因'" name="'suppliers.code'" value="" cssClass="'underline'" required="true" size="40" />
</tr>
</@inputTable>
<@buttonBar>
 <@redirectButton value="保存" url="#"/>
 <@vbutton value="关闭" class="button" onclick="javascript:window.close();" />
</@buttonBar>
 </@ww.form>
</@htmlPage>