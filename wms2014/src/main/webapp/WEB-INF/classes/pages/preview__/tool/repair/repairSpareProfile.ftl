<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="维修计划备件信息维护">

<script language="JavaScript" type="text/JavaScript"> 

	function spare_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/spareSelector.html',750,400);
	}

</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'项目号'" name="'area.code'" value="123" cssClass="'underline'" readonly="true" disabled="true"/>	
        		<td align="right" valign="top"><label class="label"><font color="red">*</font>备件编号</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="spare_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
        	</tr>
        	<tr>
                
            	 <@ww.textfield label="'备件名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true" />
            	<@ww.textfield label="'备件型号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true" />
            </tr>
            <tr>
            	<@ww.textfield label="'备件规格'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true" />
            	<@ww.textfield label="'备件类别'" name="'area.code'" value="" cssClass="'underline'" readonly="true" />
            </tr>    
            <tr>     
            	<@ww.textfield label="'备件单价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly=""  disabled="true"/> 
      			<@ww.textfield label="'使用数量(件)'" name="'area.code'" value="" cssClass="'underline'" readonly=""  required="true"/>
				
            </tr> 
            <tr>
            	<@ww.textfield label="'备件总价(元)'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true"/>
            </tr>
             
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>		
        	<input type="button" value="关闭" class="button" onclick="window.close();"
        </@buttonBar>
        
    </@ww.form>
</@htmlPage>
            	