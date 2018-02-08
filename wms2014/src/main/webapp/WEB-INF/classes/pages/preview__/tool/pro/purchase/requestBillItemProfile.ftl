<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="申购单明细">

<script language="JavaScript" type="text/JavaScript"> 
function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',600,300);
	}
function supplier_OpenDialog() {
	popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
}	
</script>
	
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'工装型号'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr> 
            <tr>
	           	<@ww.textfield label="'工装类别'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
                <@ww.textfield label="'数量'" name="'area.code'" value="" cssClass="'underline'" required="true"/>
            </tr>
            <tr>
            	<@ww.textfield label="'单价'" name="'area.code'" value="" cssClass="'underline'" />
                <@ww.textfield label="'总价'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <td align="right" valign="top"><label class="label">${action.getText('供应商')}:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="30" readonly/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="supplier_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>          
            <tr>                
		        <@ww.textfield label="'备注'" name="'area.code'"  value="" cssClass="'underline'" readonly=""/>
            </tr>                       
        </@inputTable>
       
       	<@buttonBar>
       		<@vbutton onclick="javascript:void(0)" value="保存" class="button"/>	
        	<@vbutton onclick="javascript:window.close();" value="关闭" class="button" />	
        </@buttonBar>
    </@ww.form>
    
</@htmlPage>