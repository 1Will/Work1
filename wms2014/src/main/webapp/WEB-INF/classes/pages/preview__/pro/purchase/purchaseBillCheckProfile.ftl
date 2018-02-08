<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="验收单标准">
<script language="JavaScript" type="text/JavaScript"> 
	function supplier_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,350);
	}
</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
			<tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" readonly="true"/>
            </tr>	
            <tr>
            	<@ww.textfield label="'验收内容'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            	<@ww.textfield label="'标准'" size="30" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr>

        </@inputTable>
       
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>