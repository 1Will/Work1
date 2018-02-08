<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="保养详细维护">

<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='../../device/maintenance/listMaintenanceItemDetails.html';
 		document.getElementById("preRepairItemDetails").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = document.getElementById("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	      function peopleMainManager_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
	 }	
	 
	function userUnSealedSelectorHandler(result) {
	  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
	  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
	}
		function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null,300);
		}
		function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',750,400);
		}
	

</script>

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="false"/>
            </tr>           
           <tr>
                <td align="right" valign="top"><label class="label"><font color="red">*</font>计划执行人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td> 
		       <@pp.datePicker label="'计划完成日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true"/>
		                    
            </tr>
			<@ww.select label="'状态'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '完成',
	                    	       '未完成' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	 required="true"
	                    	/>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/repair/listPreRepairPlanTOOLING.html"/>
       	</@buttonBar>	 	
	 </@ww.form>
	 
	 <ul id="beautytab">	 
	 		<li><a id="preRepairItemDetails" onclick="activeTab(this)" href="../../device/maintenance/listMaintenanceItemDetails.html" target="frame">维修明细</a></li>
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
   
   
</@htmlPage>