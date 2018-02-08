<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备点检计划维护">

<script language="JavaScript" type="text/JavaScript"> 

 	window.onload = function () {
	    		//var url = '../../preview/tool/editToolExtInfo.html';
				document.all.frame.src= '../../tool/checkpoint/listCheckPointPlanItems.html';
				getObjByNameRe("extInfo").className = "selectedtab";
				}
	function supplier_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/supplierSelector.html',600,400);
		}	
	function peopleMainManager_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/maintenanceManagerSelector.html',null, 300);
	}
</script>
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>        
        <@inputTable>       
        	<tr>	        	
	        	<@ww.textfield label="'点检计划编号'" name="'area.code'" value=""  required="false" cssClass="'underline'" readonly="true" disabled="true"/>
	        	<@ww.textfield label="'点检计划名称'" name="'area.code'" value=""  required="true" cssClass="'underline'"/>      
	      	</tr> 	
        	 <tr>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">设备编号:</label></td>
		 		<td>
		 			<input type="text" name="deviceExtInfo.madeIn" class="underline"  value=""  maxlength="150" disabled="true"/>
		 			<a onClick='tool_OpenDialog();'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 				<input type="text" style="border:0px" name="device.name" disabled="true" value=""/>
		 		</td>
	        	<@ww.textfield label="'设备名称'" name="'area.code'" value=""  required="false" cssClass="'underline'" readonly="true" disabled="true"/>
	        	<@ww.textfield label="'部门'" name="'area.code'" value=""  required="false" cssClass="'underline'" readonly="true" disabled="true"/>      
	      	</tr>
	      	<tr>
	      		<td align="right" valign="top"><label class="label"><font color="red">*</font>负责人:</label></td>
	        	<td>
	        		<input type="text" name="shipmentPlan.vehicle.code" 
	        			class="underline"  value=""  maxlength="150" size="20"/>
	        		<label id=""></label>
	        		<input type="hidden" name="shipmentPlan.vehicle.id" value="c" />
		    		<a onClick="peopleMainManager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
           		 <@pp.datePicker label="'计划执行时间'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
	     	</tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/tool/checkpoint/listCheckPointPlans.html"/>	
        </@buttonBar>
    </@ww.form>
    
	<ul id="beautytab">
		<li>
		<a id="extInfo" onclick="activeTab(this);" class="selectedtab" href="listCheckPointPlanItems.html" target="frame" >点检计划明细</a>
		</li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>