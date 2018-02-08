<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备领用维护">
    <@ww.form namespace="'/base/area'" name="'deviceBorrowBill'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'领用单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'领用单名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
            </tr>
            <tr>
                <@ww.textfield label="'资产编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
                <@ww.textfield label="'部门'" name="'area.code'" value="" cssClass="'underline'" readonly="true" required="false"/>
            </tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>领用人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'领用日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">领用状态:</label></td>
            	<td>
		 			<input type="checkbox" name="unusedAgree" value="0151" onclick="changeReturnState()"/>已经归还
		 		</td>
		 		
		 	<tr id="requestState1" style="display:none">
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>验收人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'验收日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="false" />
            </tr> 
            <tr id="requestState2" style="display:none">
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>保管员:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'入库日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="false" />
            </tr> 
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/unused/listUnused.html"/>
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
			    document.getElementById("requestPeople").style.display='inline';
			    document.getElementById("requestIdea").style.display='inline';
	     }else{
			    document.getElementById("requestPeople").style.display='none';
			    document.getElementById("requestIdea").style.display='none';
	     }
	  }
	 function changeReturnState() {
		    if (document.forms["deviceBorrowBill"].elements["unusedAgree"].checked == true) {
		      document.getElementById("requestState1").style.display='inline';
		      document.getElementById("requestState2").style.display='inline';
			}else {
		      document.getElementById("requestState1").style.display='none';
		      document.getElementById("requestState2").style.display='none';
		      }
	}
    </script>
 
</@htmlPage>