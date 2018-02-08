<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="保养标准明细维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	<tr>
	 	  		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
	 	  	</tr>	 	  	
	 	  	<tr>
	 	  		  <@ww.select label="'保养分类'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '一保',
	                    	       '二保' ,
	                    	       '日常'
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	required="true"
	        	/>
	        	<@ww.textfield label="'保养台时阀值(小时)'" name="'area.code'" required="true" value="" cssClass="'underline'"/>
	       </tr>
	       <tr>
	       	<@ww.textfield label="'保养文档名称'" name="'area.code'" value="" cssClass="'underline'"/>
	       	<@ww.textfield label="'保养文档'" name="'area.code'" value="" cssClass="'underline'" size="40" />
	       </tr>
	 	  	<tr>
	 	  		<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" size="40" />
	 	  	</tr>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="关闭" class="button" onclick="javascript:window.close();"
	    </@buttonBar>
	 </@ww.form>
</@htmlPage>