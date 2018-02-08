<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@framePage title="合同信息">
	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
            	<@ww.textfield label="'已付款额'" name="'area.code'" value="" cssClass="'underline'" />
            	<@ww.textfield label="'合同金额'" name="'area.code'" value="" cssClass="'underline'" />
            </tr>
             <tr>
            	 <@ww.textarea  label="'采购主要条款'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	         
	        	 <@ww.textarea  label="'付款方式'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />            	
            </tr>
            <tr>
            	<@pp.datePicker label="'要求交货日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
                <@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />      
            </tr>                              
        </@inputTable>
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@framePage>