<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="设备点检实施维护">

<script language="JavaScript" type="text/JavaScript"> 

 	window.onload = function () {
	    		//var url = '../../preview/tool/editToolExtInfo.html';
				document.all.frame.src= '../../tool/checkpoint/listCheckPointProcItems.html';
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
	        	<@ww.textfield label="'点检实施编号'" name="'area.code'" value=""  required="false" cssClass="'underline'" readonly="true" disabled="true"/>
	        	<@ww.textfield label="'点检实施名称'" name="'area.code'" value=""  required="false" cssClass="'underline'" disabled="true"/>      
	      	</tr>     	
	      	<tr>
	      		<@ww.select label="'部门'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'',		
	                    	'冲焊一厂', 
	                    	'总装一厂'
	                    }"
	                 value="selectedDevice"
	                    	
	             /> 
	            <@pp.datePicker label="'点检日期'" name="'cardCreatedTime'" 
		                        value="''" cssClass="'underline'" dateFormat="date"/>          		
	     	</tr>
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="${action.getText('save')}" url="#"/>	
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/tool/checkpoint/listCheckPointProcs.html"/>	
        </@buttonBar>
    </@ww.form>
    
	<ul id="beautytab">
		<li>
		<a id="extInfo" onclick="activeTab(this);" class="selectedtab" href="listCheckPointProcItems.html" target="frame" >点检实施明细</a>
		</li>	
	</ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>

 
</@htmlPage>