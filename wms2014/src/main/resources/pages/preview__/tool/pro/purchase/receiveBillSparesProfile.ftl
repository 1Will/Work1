<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="备品备件">

	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'"  disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'备件名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'备件型号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'备件规格'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'所属设备'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>                                    
        </@inputTable>
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>