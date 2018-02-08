<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../includes/macros2.ftl" />
<@htmlPage title="大项修计划明细">


<script language="JavaScript" type="text/JavaScript"> 

	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
            	<@ww.textfield label="'项目号'" name="'area.code'" value="JAC56" cssClass="'underline'" readonly="" disabled="true" />
            </tr>
            <tr>
                <@ww.textfield label="'项目名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
                <td align="right" valign="top"><label class="label"><font color="red">*</font>设备编号</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="device_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>
            <tr>
            	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="" disabled="true" />
                <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="" disabled="true" />
            </tr>
            <tr>
                <@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="" disabled="true" />
                <@ww.textfield label="'设备类别'" name="'area.code'" value="" size="" cssClass="'underline'" readonly="" disabled="true" />
            </tr>
            <tr>
            	<@ww.textfield label="'维修部位'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            	<@ww.textfield label="'维修工时'" name="'area.code'" value="" cssClass="'underline'" readonly="" required="true"/>
            </tr>
           <tr>
            	 <@pp.datePicker label="'预计开工时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" />
	     		<@pp.datePicker label="'预计完工时间'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15" />
	     	</tr>
            <tr>
                <@ww.textfield label="'预计维修费用'" name="'area.code'" value="" cssClass="'underline'" readonly="" />
                <@ww.textfield label="'预计工具费用'" name="'area.code'" value="" cssClass="'underline'" readonly="" />
            </tr>

			<tr>
				<@ww.textfield label="'维修内容摘要'" name="'area.code'" value="" size="40" cssClass="'underline'" readonly=""/>
			</tr>
            <tr>
            	
            	<@ww.textfield label="'备注'" name="'area.code'" value="" size="40" cssClass="'underline'" readonly=""/>
            </tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>		
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
    </@ww.form>
    
 </@htmlPage>