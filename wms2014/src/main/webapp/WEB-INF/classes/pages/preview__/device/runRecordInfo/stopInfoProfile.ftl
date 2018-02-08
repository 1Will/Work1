<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />

<@htmlPage title="${action.getText('runRecordInfo.maintain')}">
	 <@ww.form name="'listForm'" action="" method="'post'">
		<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('device.code')}'" name="'device.code'" value="" cssClass="'underline'" required="true"/>
	 			<@ww.textfield label="'${action.getText('device.name')}'" name="'device.name'" value="" cssClass="'underline'" required="true"/>
	         	<@ww.select label="'${action.getText('runRecordInfo.kind')}'"
	                    	name="device"
	                   		headerKey="1" 
	                   	 	headerValue="Select Month"
	                   	 	list="{'-----------', 
	                    			'${action.getText('runRecordInfo.stop')}', 
	                    			'${action.getText('runRecordInfo.fault')}', 
	                    			'${action.getText('runRecordInfo.accident')}'
	                    	 	 }"
	                   		 value="selectedDevice"
	                  		required="true"
	        	/>
	        </tr>
	        <tr>
	        	<@pp.datePicker label="'${action.getText('stop.time0')}'" name="'stop.time0'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     		<@pp.datePicker label="'${action.getText('stop.time1')}'" name="'stop.time1'"
	     			value="" cssClass="'underline'" size="15" required="true"/>
	     		<@ww.textfield label="'${action.getText('stop.operator')}'" name="'stop.operator'" value="" cssClass="'underline'" required="true"/>
	        </tr>
        </@inputTable>  
         <@buttonBar>        
        	<@redirectButton value="${action.getText('save')}" url="${req.contextPath}/preview/device/runRecordInfo/listRunRecordInfo.html"/>
        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/preview/device/runRecordInfo/listRunRecordInfo.html"/>	
        </@buttonBar>
       
     </@ww.form>
</@htmlPage>