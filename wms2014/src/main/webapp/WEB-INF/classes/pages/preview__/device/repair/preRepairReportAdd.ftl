<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="闲置申请单">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'维修报告编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'维修报告名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
            </tr>           
            <tr>
                <@RepairGrade/>
                <@dept0/>
            </tr>
           <tr>
                <td align="right" valign="top"><label class="label"><font color="red">*</font>责任人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td> 
		          <@dutydept/>            
            </tr>
             <tr>
                <@pp.datePicker label="'执行时间'" name="'repair.actual.time1'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
                <@ww.textfield label="'执行结果'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
            </tr>
            <tr>
                <@budgetSelector/>               
            </tr>
                        
           
            <tr>
                 <@ww.textfield label="'维修费用'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
                <td align="right" valign="top"><span class="required"></span><label class="label">是否外协:</label></td>
            	<td>
		 			<input type="checkbox" name="outHelp" value="0151"/>
		 		</td>
               
            </tr>             
            <tr>
            	<@ww.textarea  label="'维修说明'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	<@ww.textarea  label="'外协原因'" 
	        	         name="''" 
	        	         value="''"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>              
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/repair/listPreRepairPlanTOOLING.html"/>
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
	     }else{
			    document.getElementById("requestPeopleRenew").style.display='none';
	     }
	  }
    </script>
 
</@htmlPage>