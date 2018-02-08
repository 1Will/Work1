<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="实施维修费用维护">



    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		 <@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" readonly="true" disabled="true"/>
        		<@ww.textfield label="'实施费用项目'" name="'area.code'" value="材料费" cssClass="'underline'" readonly="true" disabled="true"/>
        	</tr>


            <tr>
            	<@ww.textfield label="'计划费用额(元)'" name="'area.code'" value="200" cssClass="'underline'" readonly="true"/>
           		<@ww.textfield label="'实施费用额(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
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
            	