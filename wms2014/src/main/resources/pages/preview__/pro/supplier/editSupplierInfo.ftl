<#include "../../includes/macros2.ftl" />

<@framePage title="添加供应商信息">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <@inputTable>

	 	  <tr>
       		<@ww.select label="'人员规模'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'10以下',
	                    	       '10~50人',
	                    	      '50~100人',
	                    	      '100人以上'
	                    	  	  }"
	                    	value="selectedSuppliers"
	        	/>
		   <@ww.textfield label="'主要联系人'" name="'manufuture.name'" value="王某某" cssClass="'underline'" />	
		   </tr>
		   <tr> 
	 	   <@ww.textfield label="'联系电话'" name="'manufuture.address'" value="0551-2915888" cssClass="'underline'" />	
		   <@ww.textfield label="'传真号码'" name="'manufuture.name'" value="101-565465656" cssClass="'underline'" />	 
		   </tr>
		   <tr>
	 	   <@ww.textfield label="'网址'" name="'manufuture.address'" value="www.jhcar.com" cssClass="'underline'" />	
	       <@ww.textfield label="'企业生产经营品种'" name="'suppliers.jypz'" value="" cssClass="'underline'" />
	       </tr>
	   <tr>
	       <@ww.textfield label="'营业执照内容'" name="'suppliers.yyzzlr'" value="" cssClass="'underline'"/>	       				
           <@ww.textfield label="'供应商的售后服务'" name="'suppliers.yyzzlr'" value="" cssClass="'underline'" />	
      </tr>
      <tr>
       <@ww.textfield label="'供应商的服务质量'" name="'suppliers.fwzl'" value="" cssClass="'underline'" />	    
      </tr>
 </@inputTable>

 
  <@buttonBar>
     	<@vbutton class="button" value="保存" onclick="javascript:void(0);"/>
     	<@vbutton class="button" value="关闭" onclick="javascript:window.close();"/>
     </@buttonBar>
 </@ww.form>
</@framePage>