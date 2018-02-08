<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<script>
	function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,350);
	}
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html', null, 300);
	}
</script>

<@htmlPage title="设备封存维护">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'" validate="true">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	  	   <tr>
		 	  	<@ww.textfield label="'封存编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" disabled="true"/>
		 	  	<@ww.textfield label="'封存人'" name="'area.code'" value="" cssClass="'underline'" required="true" />
		 	  </tr>
		 	 <@deviceSelector/>
		 	  <tr>		 	  
		 	  	<@pp.datePicker label="'封存时间'" name="'repair.estimate.time0'"
	     				value="" cssClass="'underline'" size="15" required="true"/>
	     		<@ww.textfield label="'封存备注'" name="'area.code'" size="50" value="" cssClass="'underline'" required="true" />
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