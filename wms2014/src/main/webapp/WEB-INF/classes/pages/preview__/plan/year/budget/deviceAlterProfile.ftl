<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="设备改造详细维护">



    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textarea  label="'明细内容'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	<@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
        	   
        	</tr>
            <tr>
            	<@ww.textfield label="'明细金额'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>

        </@inputTable>

       	<@buttonBar>

       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	