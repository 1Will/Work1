<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="采购计划明细维护">


<script language="JavaScript" type="text/JavaScript"> 

	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html');
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="" cssClass="'underline'" readonly="" disabled="true"/>
        		<@pp.datePicker label="'预计采购发生时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" />
        	</tr>
        	<tr>
        		<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
        	<tr>
        		<@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'设备类别'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        	</tr>
        	<tr>
        		<@ww.textfield label="'设备数量(件)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'设备单价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
        		<@ww.textfield label="'设备总价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="true" />
        	</tr>
        </@inputTable>
        <@buttonBar>
       		<@vbutton class="button" value="${action.getText('save')}" onclick="javascript:void(0);"/>
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
    </@ww.form>
</@htmlPage>