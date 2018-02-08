<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="闲置申请单">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'闲置编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'闲置名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>申请人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'闲置日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr>    
            <tr>
            	<@ww.textarea  label="'闲置原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	<@ww.textarea  label="'备注'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>     
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">闲置:</label></td>
            	<td>
		 			<input type="checkbox" name="unusedAgree" value="0151" onclick="changeSealedFaultState()"/>批准
		 		</td>
		 	</tr>
		 	<tr id="requestPeople" style="display:none">
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>批准人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
            	<@pp.datePicker label="'批准日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr> 
            <tr id="requestIdea" style="display:none">
            	<@ww.textarea  label="'批准意见'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">启用:</label></td>
            	<td>
		 			<input type="checkbox" name="unusedAgreeRenew" value="0151" onclick="changeSealedFaultRenewState()"/>批准
		 		</td>
		 	</tr>
		 	<tr id="requestPeopleRenew" style="display:none">
            	<td align="right" valign="top"><label class="label"><font color="red">*</font>批准人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		       <@pp.datePicker label="'启用日期'" name="'repair.actual.time1'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr> 
            <tr id="cause" style="display:none">
            	<@ww.textarea  label="'启用原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr> 
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/unused/listUnused.html"/>
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
			    document.getElementById("requestPeople").style.display='inline';
			    document.getElementById("requestIdea").style.display='inline';
	     }else{
			    document.getElementById("requestPeople").style.display='none';
			    document.getElementById("requestIdea").style.display='none';
	     }
	  }
	  function changeSealedFaultRenewState() {
    	   if (document.forms[0].elements["unusedAgreeRenew"].checked == true) {
			    document.getElementById("requestPeopleRenew").style.display='inline';
			    document.getElementById("cause").style.display='inline';
	     }else{
			    document.getElementById("requestPeopleRenew").style.display='none';
			    document.getElementById("cause").style.display='none';
	     }
	  }
    </script>
 
</@htmlPage>