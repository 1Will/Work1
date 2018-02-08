<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="设备鉴定明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	    
		 	  <tr>
		 	  	 <@deviceSelector2/>
		 	  	<@ww.textfield label="'资产编号'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" required="false" />
		 	  	<@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" required="false" />
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'使用部门'" name="'area.code'" value="" cssClass="'underline'" required="false" />
		 	  	<@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'" required="false" />
		 	  </tr>
		 	  <tr>
		 	  	
	     		 <@ww.select label="'鉴定结果'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'',		
	                    	'完好', 
	                    	'不完好'
	                    }"
	                 value="selectedDevice"
	      		/>  
	      		<@ww.textarea  label="'详细描述'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 	  </tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	</@ww.form>
</@htmlPage>