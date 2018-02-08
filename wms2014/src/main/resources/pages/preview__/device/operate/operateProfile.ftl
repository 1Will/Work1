<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备操作证维护">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'操作证编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'操作证名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备型号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备规格'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
             <tr>
             	<@ww.textfield label="'人员代码'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'人员名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<@pp.datePicker label="'测试日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>主考人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            </tr>    
            <tr>
            	<@ww.textfield label="'成绩'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
	        	<@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>     
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/operate/listOperate.html"/>
       		<@vbutton value="打印" class="button" />
        </@buttonBar>
    </@ww.form>
    <script>
    function peopleMainManager_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
	 }	
	 
	function userUnSealedSelectorHandler(result) {
	  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
	  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
	}
    function changeSealedFaultState() {
    	   if (document.forms[0].elements["unusedAgree"].checked == true) {
			    getObjByNameRe("requestPeople").style.display='inline';
			    getObjByNameRe("requestIdea").style.display='inline';
	     }else{
			    getObjByNameRe("requestPeople").style.display='none';
			    getObjByNameRe("requestIdea").style.display='none';
	     }
	  }
	  function changeSealedFaultRenewState() {
    	   if (document.forms[0].elements["unusedAgreeRenew"].checked == true) {
			    getObjByNameRe("requestPeopleRenew").style.display='inline';
			    getObjByNameRe("cause").style.display='inline';
	     }else{
			    getObjByNameRe("requestPeopleRenew").style.display='none';
			    getObjByNameRe("cause").style.display='none';
	     }
	  }
    </script>
 
</@htmlPage>