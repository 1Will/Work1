<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="工装清查明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	    
		 	  <tr>
		 	  	 <@toolingSelector1/>
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'工装型号'" name="'area.code'" value="" cssClass="'underline'" required="false" disabled="true"/>
		 	  	<@ww.textfield label="'工装规格'" name="'area.code'" value="" cssClass="'underline'" required="false" disabled="true"/>
		 	  </tr>
		 	  <tr>
		 	  	<@ww.textfield label="'使用部门'" name="'area.code'" value="" cssClass="'underline'" required="false" disabled="true"/>
		 	  	<@ww.textfield label="'工装类别'" name="'area.code'" value="" cssClass="'underline'" required="false" disabled="true"/>
		 	  </tr>
		 	  <tr>
		 	  	
	     		 <@ww.select label="'清查结果'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'',		
	                    	'已损坏', 
	                    	'已遗失'
	                    }"
	                 value="selectedDevice"
	      		/>  
	      		<@ww.textarea  label="'处理结果'" 
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