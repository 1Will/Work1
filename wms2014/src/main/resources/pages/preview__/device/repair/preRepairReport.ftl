<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="闲置申请单">
    <@ww.form namespace="'/base/area'" name="'area'" action="'saveArea'" method="'post'" validate="true">
        <@ww.token name="saveAreaToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr>
            <tr>
                <@dept0/><input type="checkbox" name="unusedAgree" value="0151" />是否外协
                <a onClick="planAuditor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        </a>
            </tr>
            <tr>
                <@ww.select label="'维修等级'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'A',		
	                    	'B', 
	                    	'C'
	                    }"
	                 value="selectedDevice"
	      		/>
	      		<a onClick="planAuditor_OpenDialog();">
		        		  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        </a>  	
            </tr> 
            <tr>
              <@ww.textfield label="'计划费用'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
              <@pp.datePicker label="'计划完成日期'" name="'repair.actual.time0'"
	     			value="" cssClass="'underline'" size="15"  required="true" />
            </tr>   
            <tr>
            	<@ww.select label="'维修等级'"
	                name="device"
	                headerKey="1" 
	                headerValue="Select Month"
	                list="{
	                    	'计划类',		
	                    	'计划外'
	                    }"
	                 value="selectedDevice"
	      		/>
	      		<@ww.textfield label="'计划费用'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
            </tr> 
            <tr>
              <@ww.textfield label="'工作内容'" name="'area.code'" value="" cssClass="'underline'"  size="50"/>
              <@ww.textfield label="'目标要求'" name="'area.code'" value="" cssClass="'underline'"  size="50"/>
            </tr>    
            
        </@inputTable>
       	<@buttonBar>
       		<@redirectButton value="保存" url="#"/>
       		<@redirectButton value="返回" url="${req.contextPath}/preview/device/unused/listUnused.html"/>
        </@buttonBar>
    </@ww.form>
    <script>
    function planAuditor_OpenDialog () {
	       		var url = "${req.contextPath}/popup/userSelector.html?multiple='F'";
		    	popupModalDialog(url, 800, 600, desigerSelectorHandler_Auditor);
	       }     
    
    </script>
 
</@htmlPage>