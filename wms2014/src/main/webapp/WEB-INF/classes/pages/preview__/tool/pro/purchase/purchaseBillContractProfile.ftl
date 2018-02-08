<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@framePage title="合同信息">
	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
                <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
            </tr>
                <@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
            	<@ww.textfield label="'设备数量'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
           	</tr>
           	<tr>
            	<@ww.textfield label="'设备单价'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
                <@ww.textfield label="'设备总价'" name="'area.code'" value="10000" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@pp.datePicker label="'要求交货日期'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
                <@ww.textfield label="'备注'" name="'area.code'" size="20" value="" cssClass="'underline'" readonly="true"/>
            </tr>                              
        </@inputTable>
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@framePage>