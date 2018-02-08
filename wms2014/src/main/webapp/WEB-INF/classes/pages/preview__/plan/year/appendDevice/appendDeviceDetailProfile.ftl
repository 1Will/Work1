<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="新增设备计划明细维护">



    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	    <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
            <tr>
            	<@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            	<@ww.textfield label="'单价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'数量'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            	<@ww.textfield label="'总价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true"/>			
            </tr> 

        </@inputTable>

       	<@buttonBar>

       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<input type="button" value="关闭" class="button" onclick="window.close();"/>
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	