<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->
<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form namespace="'/runmaintenance/trusteeship'" name="'trusteeshipDetail'" action="'searchTrusteeshipDetails'" method="'post'">
	      <@ww.token name="searchTrusteeshipDetailToken"/>
	      <#if trusteeship?exists>
	      <@ww.hidden name="'trusteeship.id'" value="'#{trusteeship.id?if_exists}'"/>
	      </#if>
	        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	        <@ww.hidden name="'addDeviceIds'" value=""/>
	        <@ww.hidden name="'addDevice'" value=""/>
	        <input type="hidden" name="currentRowNum" value=""/>
	      <#--  
	        <input type="hidden" name="allDeviceMigratenewManager" value=""/>
	        <input type="hidden" name="migrateDeviceNewPlace" value=""/>
	        <input type="hidden" name="allMigrateDetailId" value=""/>-->
	        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	     <#assign itemNo=1/>
	     <#assign loopNum=0/>
	      <@titleBar title=""/>
	        <#assign itemNo = 1/>
	        <#assign dutyPeopleIdentity = 'ManagerPeople' + '${loopNum}'/>   
	        
        <@list title="" excel=false setupTable=false
        	includeParameters="trusteeship.id|toolingDevFlag" 
        	fieldMap="like:trusteeship.id" >
            <@vlh.checkbox property="id" name="trusteeshipDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
           <#if toolingDevFlag?exists>
            <#if toolingDevFlag='DEVICE'>
              <@vcolumn title="${action.getText('trusteeshipDetail.deviceNo')}" property="asset.deviceNo">
	          <@alignLeft/>
              </@vcolumn>
              <@vcolumn title="${action.getText('trusteeshipDetail.deviceName')}" property="asset.name">
	          <@alignLeft/>
              </@vcolumn>
              <#else>
               <@vcolumn title="${action.getText('trusteeshipDetail.toolingNo')}" property="asset.deviceNo">
	          <@alignLeft/>
              </@vcolumn>
              <@vcolumn title="${action.getText('trusteeshipDetail.toolingName')}" property="asset.name">
               <@alignLeft/>
              </@vcolumn>
            </#if>
            </#if>
         <#assign loopNum=loopNum+1/>
        </@list>
        <#if toolingDevFlag?exists>
       	<#if toolingDevFlag=='DEVICE'>
	         <@buttonBar>
	         <#if !(action.isReadOnly())>
	     	     <@vbutton value="${action.getText('new')}" class="button" onclick="multi_select_device_openDialog()"/>   
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('trusteeshipDetailBill')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"trusteeshipDetailIds\", \"${confirmMessage}\")'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	        </@buttonBar>
	        <#else>
	          <@buttonBar>
	           <#if !(action.isReadOnly())>
	     	     <@vbutton value="${action.getText('new')}" class="button" onclick="multi_select_tooling_openDialog()"/>   
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('toolingTrusteeshipDetailBill')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"trusteeshipDetailIds\", \"${confirmMessage}\")'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            </#if>
	        </@buttonBar>
	        </#if>
	        </#if>
     </@ww.form>
     <script language="javascript">
	 function multi_select_device_openDialog() {
	  var flag='DeviceTrusteeship';
      var url = '${req.contextPath}/popup/deviceSelector.html';
      eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
    }
    function refresh_multi_device_information(reslut) {
      if (null != result) {
        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
        document.forms[0].elements["addDevice"].value = "addDevices";
        document.forms[0].submit();
      }
    }
     function multi_select_tooling_openDialog() {
      var flag='Trusteeship';
      var url = '${req.contextPath}/popup/toolingSelector.html';
      eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
    }
    function refresh_multi_device_information(reslut) {
      if (null != result) {
        var addDeviceIds = result.substring(0, result.lastIndexOf(","));
        document.forms[0].elements["addDeviceIds"].value = addDeviceIds;
        document.forms[0].elements["addDevice"].value = "addDevices";
        document.forms[0].submit();
      }
    }
     function SelectmigrateDetailUser_OpenDialog(loopNum){
        document.forms[0].elements["currentRowNum"].value = loopNum;
        migrateDetailUser_OpenDialog();
     }
     
        function customize_validate(){
           retrieveMigrateDetaildeviceIdText();//获取设备转移明细单的id
         }
          function  retrieveMigrateDetaildeviceIdText(){
            var allMigrateDeviceId=document.getElementsByName("migrateDetailIds");  
            var ary=new Array();
            for (var i=0; i<allMigrateDeviceId.length; i++) {
             ary.push(allMigrateDeviceId[i].value);
            }
          document.forms[0].elements["allMigrateDetailId"].value = ary;
          
       }
	    </script>
</@framePage>