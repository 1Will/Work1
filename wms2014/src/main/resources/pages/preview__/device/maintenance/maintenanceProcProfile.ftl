<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="设备保养实施维护">
<script language="JavaScript" type="text/JavaScript"> 
	window.onload = function () {
		//		document.forms["area"].elements["ext"].click();
 		document.all.frame.src='../../device/maintenance/maintenanceProcDeviceList.html';
 		getObjByNameRe("fees").className = "selectedtab";
 	}
 	
	function activeTab(obj) {
	    var sfEls = getObjByNameRe("beautytab").getElementsByTagName("li");
	    for (var i=0; i<sfEls.length; i++) {
	    	//alert(sfEls[i].getElementsByTagName("a")[0].id == obj.id);
	      if (sfEls[i].getElementsByTagName("a")[0].id == obj.id) {
	        sfEls[i].getElementsByTagName("a")[0].className = "selectedtab";
	      } else {
	        sfEls[i].getElementsByTagName("a")[0].className = '';
	      }
	    }
	}
	
		function device_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/deviceSelector.html',600,400);
	}
	
	
	
	    function peopleMaintenance_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenancePeopleSelector.html');
	}
	
	
	function peopleMainManager_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenanceManagerSelector.html',null, 300);
	}
	
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',null, 300);
	}
</script>

    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'保养计划编号'" name="'area.code'" value="" cssClass="'underline'"  required="true" readonly="true"/>
        		<@ww.textfield label="'保养计划名称'" name="'area.code'" value="" cssClass="'underline'"  required="true" readonly="true"/>
        	</tr>
        	<tr>
        	<tr>
            	 <@ww.select label="'部门'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '机动部',
	                    	       '品管部',
	                    	       '技术部' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	 required="true"
	                    	/>
            	  <@ww.select label="'类别'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '一保',
	                    	       '二保' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	 required="true"
	                    	/>
	     </tr>
	     <tr>
		          <td align="right" valign="top"><label class="label"><font color="red">*</font>计划编制人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20" readonly="true"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@pp.datePicker label="'编制日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" readonly="true"/>
	     	</tr>
	     	<tr>
	     		 <td align="right" valign="top"><label class="label"><font color="red">*</font>执行人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value="" maxlength="150" size="20" readonly="true"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <@pp.datePicker label="'执行日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" readonly="true"/>
	     		 <@ww.select label="'状态'"
	                    	name="level"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    	       '已完成',
	                    	       '未完成' 
	                    	  	  }"
	                    	value="selectedSuppliers"
	                    	 disabled="true"
	                    	/>
	     </tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/device/maintenance/listMaintenanceProcs.html"/>	
        	<@redirectButton value="${action.getText('print')}" url="#"/>
        </@buttonBar>
    </@ww.form>
    
    
    
	<ul id="beautytab">
		<li>
		<a id="maintenanceDeviceList" onclick="activeTab(this);" class="selectedtab" href="../../device/maintenance/maintenanceProcDeviceList.html" target="frame" >保养实施明细</a>
		</li>	
		<#--<li><a id="maintenanceDevicefees" onclick="activeTab(this);" href="../../device/maintenance/listMaintenancePlanFees.html" target="frame">保养费用明细</a></li>
		<li><a id="manHours" onclick="activeTab(this);" href="../../device/maintenance/listMaintenancePlanManHours.html" target="frame" >保养工时明细</a></li>	 
		  
		<li><a id="manPlanVerify" onclick="activeTab(this);" href="../../device/maintenance/maintenancePlanVerify.html" target="frame" >保养计划变更明细</a></li>	  
		--> 	  
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>