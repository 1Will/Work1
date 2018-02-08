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
     <@ww.form namespace="'/runmaintenance/migrate'" name="'migrateDetail'" action="'searchMigrateDetails'" method="'post'">
	      <@ww.token name="searchMigrateDetailToken"/>
	      <#if migrate?exists>
	      <@ww.hidden name="'migrate.id'" value="'#{migrate.id?if_exists}'"/>
	     </#if>
	        <@ww.hidden name="'addDeviceIds'" value=""/>
	        <@ww.hidden name="'addDevice'" value=""/>
	        <input type="hidden" name="currentRowNum" value=""/>
	        <input type="hidden" name="allDeviceMigratenewManager" value=""/>
	        <input type="hidden" name="migrateDeviceNewPlace" value=""/>
	        <input type="hidden" name="allMigrateDetailId" value=""/>
	        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
            <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <#assign itemNo=1/>
	     <#assign loopNum=0/>
	      <@titleBar title=""/>
	        <#assign itemNo = 1/>
	        <#assign dutyPeopleIdentity = 'ManagerPeople' + '${loopNum}'/>   
        <@list title="" excel=false setupTable=false
        	includeParameters="migrate.id|toolingDevFlag" 
        	fieldMap="like:migrate.id" >
            <@vlh.checkbox property="id" name="migrateDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
              <input type="hidden" name="hiddenDeviceIds" value="#{object.asset.id}"/>
            <@vcolumn title="${action.getText('itemNo')}">
                ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
            <#if toolingDevFlag?exists>
           <#if toolingDevFlag='DEVICE'>
           <@vcolumn title="${action.getText('migrateDetail.deviceNo')}" property="asset.deviceNo">
	       <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('migrateDetail.deviceName')}" property="asset.name">
	       <@alignLeft/>
           </@vcolumn>
           <@vcolumn title="${action.getText('migrateDetail.installPlace')}" property="oldInstallPlace">
	       <@alignLeft/>
           </@vcolumn>
          
           <@vcolumn title="${action.getText('migrateDetail.newinstallPlace')}">
	        <input type="text" name="newInstallPlace" 
	    		   class="underline"  value="${object.newInstallPlace?if_exists}"  maxlength="50" size="15"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
	      </@vcolumn>
	      <@vcolumn title="${action.getText('orginal.responsible')}" property="oldManager">
	      <@alignLeft/>
          </@vcolumn>
          <@vcolumn title="${action.getText('new.responsible')}">
	        <#assign migrateDetailManagerName = ''/>
	          <#assign dutyPeopleIdentity = 'ManagerPeople' + '${loopNum}'/>  
					<#if object.newManager?exists>
					 <#assign migrateDetailManagerName= "${object.newManager.name}" />
			         <#elseif loginUser?exists>
			          <#assign migraterequestUserName = "${loginUser.name}" />
					</#if>
	        		<input type="text" name="newManager.name" 
	        			class="underline"  value="${migrateDetailManagerName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="SelectmigrateDetailUser_OpenDialog(${loopNum});">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        <#assign migrateDetailManagerId = ''/>
				<#if object.newManager?exists>
				 <#assign migrateDetailManagerId = "${object.newManager.id}" />
				  <#elseif loginUser?exists>
				  <#assign migraterequestUserId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="newManager.id" value="${migrateDetailManagerId}" />
             </@vcolumn>
               <#else>
              <@vcolumn title="${action.getText('migrateDetail.toolingNo')}" property="asset.deviceNo">
	          <@alignLeft/>
              </@vcolumn>
              <@vcolumn title="${action.getText('migrateDetail.toolingName')}" property="asset.name">
	          <@alignLeft/>
              </@vcolumn>
              <@vcolumn title="${action.getText('toolingDetail.group')}" property="asset.graphNo">
	          <@alignLeft/>
              </@vcolumn>
              <@vcolumn title="${action.getText('orginal.responsible')}" property="oldManager">
	      <@alignLeft/>
          </@vcolumn>
          <@vcolumn title="${action.getText('new.responsible')}">
	        <#assign migrateDetailManagerName = ''/>
	          <#assign dutyPeopleIdentity = 'ManagerPeople' + '${loopNum}'/>  
					<#if object.newManager?exists>
					 <#assign migrateDetailManagerName= "${object.newManager.name}" />
			         <#elseif loginUser?exists>
			          <#assign migraterequestUserName = "${loginUser.name}" />
					</#if>
	        		<input type="text" name="newManager.name" 
	        			class="underline"  value="${migrateDetailManagerName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		    <a onClick="SelectmigrateDetailUser_OpenDialog(${loopNum});">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        <#assign migrateDetailManagerId = ''/>
				<#if object.newManager?exists>
				 <#assign migrateDetailManagerId = "${object.newManager.id}" />
				  <#elseif loginUser?exists>
				  <#assign migraterequestUserId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="newManager.id" value="${migrateDetailManagerId}" />
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
	            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('migrateDetailBill')}?" />
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"migrateDetailIds\", \"${confirmMessage}\")'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	            <#if !(action.isReadOnly())>
     	       <@vsubmit name="'save'" value="'${action.getText('save')}'">
	              <@ww.param name="'onclick'" value="'return customize_validate();'"/>
	               <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	            </#if>
	        </@buttonBar>
	        <#else>
	          <@buttonBar>
	          <#if !(action.isReadOnly())>
	     	     <@vbutton value="${action.getText('new')}" class="button" onclick="multi_select_tooling_openDialog()"/>   
	     	     <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return  customize_validate();'">
		             <#-- <@ww.param name="'onclick'" value="'return customize_validate();'"/>-->
		              <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		         </@vsubmit>
		            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('migrateDetailBill')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"migrateDetailIds\", \"${confirmMessage}\")'"/>
		                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		            </@vsubmit>
		            
		         </#if>
	        </@buttonBar>
	        </#if>
	        </#if>
     </@ww.form>
     <script language="javascript">
	 function multi_select_device_openDialog() {
	   var flag='Migrate';	 
       var url = '${req.contextPath}/popup/deviceSelector.html';
        <#if !valueListNoRecords>
	   var oldDeviceIds = document.getElementsByName("hiddenDeviceIds");
	   var ary = new Array();
	   for (var i=0; i<oldDeviceIds.length; i++) {
	     ary.push(oldDeviceIds[i].value);
	   }
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,ary,flag);
	 <#else>
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
	 </#if>
    <#-- eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information);-->
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
      var flag='Migrate';
      var url = '${req.contextPath}/popup/toolingSelector.html';
      <#if !valueListNoRecords>
	   var oldDeviceIds = document.getElementsByName("hiddenDeviceIds");
	   var ary = new Array();
	   for (var i=0; i<oldDeviceIds.length; i++) {
	     ary.push(oldDeviceIds[i].value);
	   }
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,ary,flag);
	 <#else>
	   eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information,null,flag);
	 </#if>
     <#-- eam2008_multi_select_device_OpenDialog(url,refresh_multi_device_information);-->
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
     function migrateDetailUser_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
    	popupModalDialog(url, 800, 600, migrateSelectorHandler);
      }
        function migrateSelectorHandler(result) {
        var allManagerPeopleId = document.getElementsByName("newManager.id");
        var allManagerPeopleName = document.getElementsByName("newManager.name");
        var currentRowNum = document.forms[0].elements["currentRowNum"].value;
           if (null != result) {
		     allManagerPeopleId[currentRowNum].value = result[0];
             allManagerPeopleName[currentRowNum].value = result[1];
		    }      
      }
        function customize_validate(){
           retrieveMigrateDetaildeviceIdText();//获取设备转移明细单的id
           retrieveMigrateDetailPeopleText(); //获取设备转移明细单中的所有新负责人
          <#if toolingDevFlag='DEVICE'>  
           retrieveMigrateDetaildeviceNewPlaceText();//获取设备转移明细单中的所有设备的新地点
           </#if>
          
         }
          function  retrieveMigrateDetaildeviceIdText(){
            var allMigrateDeviceId=document.getElementsByName("migrateDetailIds");  
            var ary=new Array();
            for (var i=0; i<allMigrateDeviceId.length; i++) {
             ary.push(allMigrateDeviceId[i].value);
            }
          document.forms[0].elements["allMigrateDetailId"].value = ary;
            }
            //获得每一个新负责人的id
        function retrieveMigrateDetailPeopleText(){
           var allnewManager = document.getElementsByName("newManager.id");
           var allMigrateDetailId = document.getElementsByName("migrateDetailIds");
           var ary = new Array();
           for (var i=0; i<allMigrateDetailId.length; i++) {
             if ('' != allnewManager[i].value) {
               ary.push(allMigrateDetailId[i].value);
               ary.push(allnewManager[i].value);
             }
           }
           document.forms[0].elements["allDeviceMigratenewManager"].value=ary;
         }
     <#if toolingDevFlag='DEVICE'>  
       function  retrieveMigrateDetaildeviceNewPlaceText(){  //获得每一个设备的新安装地点
         var allMigrateDetailId = document.getElementsByName("migrateDetailIds");
         var allDeviceNewPlace=document.getElementsByName("newInstallPlace");
         var ary = new Array();
         for(var i=0; i<allMigrateDetailId.length; i++) {
             if ('' != allDeviceNewPlace[i].value) {
             ary.push(allMigrateDetailId[i].value);
             ary.push(allDeviceNewPlace[i].value);
         }
       }
      document.forms[0].elements["migrateDeviceNewPlace"].value=ary;
   }  
   </#if>
 <#--  function   customize_validate(){//验证新安装地点的长度
     if(document.getElementById("newInstallPlace").value!=''){
     if(!isValidLength(document.forms[0], "newInstallPlace", null, 50)){
			alert("${action.getText('newInstallPlace.length')}");
			return  false;
		 }
     }
     return true;
   }-->
 </script>
</@framePage>