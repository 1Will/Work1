<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="定期检查标准维护">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" />
        	</tr>
        	<tr>
        		<@ww.textfield label="'检查设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'检查设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
        	<tr>
        		<@ww.textfield label="'检查设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'检查设备类别'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
        	<tr>
        		<@ww.textfield label="'检查设备数量(件)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'备注'" name="'area.code'" value="" cssClass="'underline'" />
        	</tr>
        </@inputTable>
        <@buttonBar>
       		<@vbutton class="button" value="${action.getText('save')}" onclick="javascript:void(0);"/>
       		<@vbutton class="button" value="${action.getText('submit')}" onclick="javascript:void(0);"/>
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
    </@ww.form>
</@htmlPage>