<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="设备启封维护">

<script>
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,350);
	}
	function disabledDev_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/disabledDevSelector.html',600,350);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}
</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	   <tr>
		 	  	<@ww.textfield label="'启封编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true"/>
		 	  	<@ww.textfield label="'启封人'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  
		 	  <tr>
		 	  
		 	  <td align="right" valign="top"><label class="label"><font color="red">*</font>封存编号:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="disabledDev_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		          <@ww.textfield label="'封存人'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
               	  <@pp.datePicker label="'封存时间'" name="'repair.estimate.time2'"
	     				value="" cssClass="'underline'" size="15" readonly="true"/>
		 	  </tr>
		 	  <@deviceSelector/>
		 	  <tr>		 	  
		 	  	<@pp.datePicker label="'启封时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
	     		<@ww.textfield label="'启封备注'" name="'area.code'" size="50" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <@audit2/>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@redirectButton value="${action.getText('save')}" url="#"/>
	        	<input type="button" value="提交" class="button" onclick="javascript:window.close();"/>
	        	<input type="button" value="返回" class="button" onclick="javascript:history.back(-1);"/>
	     </@buttonBar>
	</@ww.form>
</@htmlPage>