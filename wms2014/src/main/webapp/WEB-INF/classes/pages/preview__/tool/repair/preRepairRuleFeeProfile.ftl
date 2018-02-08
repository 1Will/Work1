<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="维修费用维护">



    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		 <@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" readonly="true" disabled="true"/>
        		<@ww.textfield label="'费用项目'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>


            <tr>
            	<@ww.textfield label="'费用额(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr> 
			<tr>
            	<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" readonly="" size="40"/>
            </tr> 

        </@inputTable>

       	<@buttonBar>

       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	