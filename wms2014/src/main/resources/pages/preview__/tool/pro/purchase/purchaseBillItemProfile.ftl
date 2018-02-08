<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="采购单明细">
<script>
function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
}
</script>
	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" disabled="true" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
                <@ww.textfield label="'工装型号'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
            </tr>
                <@ww.textfield label="'工装类别'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
            	<@ww.textfield label="'数量'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
           	</tr>
           	<tr>
            	<@ww.textfield label="'单价'" name="'area.code'" value="" cssClass="'underline'"  required="true"/>
                <@ww.textfield label="'总价'" name="'area.code'" value="10000" cssClass="'underline'" disabled="true" readonly="true"/>
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
    
</@htmlPage>