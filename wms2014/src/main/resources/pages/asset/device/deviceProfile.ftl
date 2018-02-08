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
<#-- $Id: deviceProfile.ftl 11436 2008-03-18 14:16:50Z wzou $ -->
<#include "../../includes/eam2008.ftl" />
<#include "../commonDepartmentProduction.ftl" />

<@htmlPage title="${action.getText('device.title1')}">

  <@ww.form namespace="'/asset/device'" name="'device'" action="'saveDevice'" method="'post'" validate="true">
  <@ww.token name="saveDeviceToken"/>
    <@inputTable>
    <#if device.id?exists>
      <@ww.hidden name="'device.id'" value="#{device.id}"/>
    </#if>
      <@ww.hidden name="'device.version'" value="#{device.version?if_exists}"/>
      <@ww.hidden name="'docview'" value="'${req.getParameter('view')?if_exists}'"/>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <tr>
	  <@ww.textfield label="'${action.getText('device.no')}'" name="'device.deviceNo'" value="'${device.deviceNo?if_exists}'" cssClass="'underline'" required="flase" readonly="true" disabled="true"/>
	  <@ww.textfield label="'${action.getText('device.assetno')}'" name="'device.assetNo'" value="'${device.assetNo?if_exists}'" cssClass="'underline'" required="false" />
	  <@ww.textfield label="'${action.getText('device.name')}'" name="'device.name'" value="'${device.name?if_exists}'" cssClass="'underline'" required="true"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('device.graphNo')}'" name="'device.graphNo'" value="'${device.graphNo?if_exists}'" cssClass="'underline'"/>
		<@ww.select label="'${action.getText('device.useCategory')}'" required="false"
   				name="'useCategory.id'" listKey="id" listValue="value"
    			list="useCategory" emptyOption="true" disabled="false">
   		</@ww.select>
		<@ww.select label="'${action.getText('device.sepecDevicePro')}'" 
                   required="false" name="'specificationProp.id'" 
                   listKey="id" listValue="value"
                   list="deviceSpecifictionProps" 
                   emptyOption="true" 
                   disabled="false">
      </@ww.select>	
	</tr>
	<tr>
	    <@ww.select label="'${action.getText('device.category')}'" 
                    required="false" name="'deviceType.id'" 
                    listKey="id" listValue="name"
                    list="deviceTypes" 
                    emptyOption="true" 
                    disabled="false" required="true">
        </@ww.select>	
		<@ww.textfield label="'${action.getText('device.model')}'" name="'device.model'" value="'${device.model?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('device.specification')}'" name="'device.specification'" value="'${device.specification?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	  <@ww.select label="'${action.getText('device.filiale')}'"
	                       name="'filiale.id'"
	                   	   listKey="id" 
	                       listValue="name"
	                       list="filiales" 
	                       emptyOption="true" 
	                       disabled="false"
	                       onchange="'filialeSelectDeptDWR()'">
	                       <#if device.filiale?exists>
                              <@ww.param name="'value'"  value="'#{device.filiale.id?if_exists}'" />
                             <#else>
                              <@ww.param name="'value'"  value="" />
                          </#if>
	           </@ww.select>
	  	
	  <@ww.select label="'${action.getText('department')}'" 
	  						required="true" 
	  						name="'department.id'" 
	    					listKey="id" 
	    					listValue="name" 
	    					onchange="'departmentValueChange(\"department.id\",\"productionLine.id\")'"
        					list="departments" 
        					emptyOption="false" 
        					disabled="false" >
        <#if device.department?exists>
          <@ww.param name="'value'"  value="'${device.department.id?if_exists}'" />
        </#if>
      </@ww.select>
      <@ww.select label="'${action.getText('device.line')}'" 
      						required="false" 
      						name="'productionLine.id'" 
        					listKey="id" 
        					listValue="name"
        					list="productionLines" 
        					emptyOption="false" 
        					disabled="false">
					        <#if device.productionLine?exists>
					          <@ww.param name="'value'"  value="'${device.productionLine.id?if_exists}'" />
					        <#else>
                              <@ww.param name="'value'"  value="" />
					        </#if>
					      </@ww.select>
	</tr>
	<tr>
		<td align="right" valign="top"><label class="label">${action.getText('device.emphasis')}:</label></td>	
		  	<td>
		  		<select name="device.emphasis">
	 			<option value="N">${action.getText('no')}</option>
	 			<option value="Y">${action.getText('yes')}</option>
	      	</select>
	      	</td>
	      	<script language="javascript">
	        	<#if (device.emphasis)>
	     	  	document.forms[0].elements["device.emphasis"].value = 'Y';
	     		<#else>
	     	  	document.forms[0].elements["device.emphasis"].value = 'N';
	     		</#if>
      	</script>
	  <@ww.select label="'${action.getText('device.managementLevel')}'" 
                   required="false" name="'device.managementLevel'" 
                   listKey="value" listValue="label"
                   list="managementLevels" 
                   emptyOption="true" 
                   disabled="false">
      </@ww.select>
      
      <#assign manager = ''/>
	  <#if device.manager?exists>
	    <#assign manager = "${device.manager.name}" />
	  </#if>
	  <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('device.manager.name')}:</label></td>
	  <td>
	    <input type="text" name="manager.name" class="underline"  value="${manager}"  maxlength="150" disabled="true"/>
		<a onClick='user_OpenDialog()'>
	 	  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	    </a>
	  </td>
	    <#assign managerId = ''/>
	    <#if device.manager?exists>
	      <#assign managerId = "${device.manager.id}" />
	    </#if>
	    <@ww.hidden name="'manager.id'" value="'${managerId}'"/>	 		
    </tr>
	<tr>
	  <td align="right" valign="top"><label class="label">${action.getText('device.supplier')}:</label></td>
	  <td>
	  <#assign supplierName = ''/>
	    <#if device.supplier?exists>
		  <#assign supplierName = "${device.supplier.name?if_exists}"/>
	    </#if>
		<input type="text" name="supplier.name" class="underline"  value="${supplierName}"  maxlength="150" disabled="true"/>
		<a onClick='supplier_OpenDialog()'>
	 	  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 	</a>
	  </td>
	  <#assign supplierId = ''/>
	    <#if device.supplier?exists>
		  <#assign supplierId = "${device.supplier.id?if_exists}"/>
		</#if>
		  <@ww.hidden name="'supplier.id'" value="'${supplierId}'"/>
		  <@ww.textfield label="'${action.getText('device.factory')}'" name="'device.factory'" value="'${device.factory?if_exists}'" cssClass="'underline'" required="true"/>
		  <@ww.textfield label="'${action.getText('device.installPlace')}'" name="'device.installPlace'" value="'${device.installPlace?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<td align="right" valign="top"><label class="label">${action.getText('device.accuracy')}:</label></td>	
		  	<td>
		  		<select name="device.accuracy">
	     			<option value="N">${action.getText('no')}</option>
	     			<option value="Y">${action.getText('yes')}</option>
	      		</select>
	      	</td>
          	<script language="javascript">
            	<#if (device.accuracy)>
         	  	document.forms[0].elements["device.accuracy"].value = 'Y';
         		<#else>
         	  	document.forms[0].elements["device.accuracy"].value = 'N';
         		</#if>
          	</script>
        
        <td align="right" valign="top"><label class="label">${action.getText('device.large')}:</label></td>	
		  	<td>
		  		<select name="device.large">
     			<option value="N">${action.getText('no')}</option>
     			<option value="Y">${action.getText('yes')}</option>
	      	</select>
	      	</td>
          	<script language="javascript">
            	<#if (device.large)>
         	  	document.forms[0].elements["device.large"].value = 'Y';
         		<#else>
         	  	document.forms[0].elements["device.large"].value = 'N';
         		</#if>
          	</script>
        
        <td align="right" valign="top"><label class="label">${action.getText('device.rare')}:</label></td>	
		  	<td>
		  		<select name="device.rare">
     			<option value="N">${action.getText('no')}</option>
     			<option value="Y">${action.getText('yes')}</option>
	      	</select>
	      	</td>
          	<script language="javascript">
            	<#if (device.rare)>
         	  	document.forms[0].elements["device.rare"].value = 'Y';
         		<#else>
         	  	document.forms[0].elements["device.rare"].value = 'N';
         		</#if>
          	</script>
	</tr>
    <tr>
   		
   		<td align="right" valign="top"><label class="label">${action.getText('device.full')}:</label></td>	
		  	<td>
		  		<select name="device.full">
     			<option value="N">${action.getText('no')}</option>
     			<option value="Y">${action.getText('yes')}</option>
	      	</select>
	      	</td>
          	<script language="javascript">
            	<#if (device.full)>
         	  	document.forms[0].elements["device.full"].value = 'Y';
         		<#else>
         	  	document.forms[0].elements["device.full"].value = 'N';
         		</#if>
          	</script>
          	
      	<td align="right" valign="top"><label class="label">${action.getText('device.standard')}:</label></td>	
	  	<td>
	  		<select name="device.standard">
 			<option value="N">${action.getText('no')}</option>
 			<option value="Y">${action.getText('yes')}</option>
      	</select>
      	</td>
      	<script language="javascript">
        	<#if (device.standard)>
     	  	document.forms[0].elements["device.standard"].value = 'Y';
     		<#else>
     	  	document.forms[0].elements["device.standard"].value = 'N';
     		</#if>
      	</script>
      	
      	<#--<@ww.select label="'${action.getText('device.excellentBigSparse')}'"
               name="'excellentBigSparse.id'"
           	   listKey="id" 
               listValue="value"
               list="excellentBigSparseType" 
               emptyOption="false" 
               disabled="false">
               <#if device.excellentBigSparse?exists>
                  <@ww.param name="'value'"  value="'#{device.excellentBigSparse.id?if_exists}'" />
                 <#else>
                  <@ww.param name="'value'"  value="" />
              </#if>
           </@ww.select>-->
           
           <@ww.select label="'${action.getText('device.calUnit')}'"
	                       name="'calUnit.id'"
	                   	   listKey="id" 
	                       listValue="value"
	                       list="UnitType" 
	                       emptyOption="true" 
	                       disabled="false">
	                       <#if device.calUnit?exists>
                              <@ww.param name="'value'"  value="'#{device.calUnit.id?if_exists}'" />
                             <#else>
                              <@ww.param name="'value'"  value="" />
                          </#if>
	           </@ww.select>
    </tr>
    <tr>
      
      	
      <@ww.select label="'${action.getText('device.status')}'" required="false" name="'device.status.id'" 
                  listKey="id" listValue="value"
                  list="deviceStatus" emptyOption="true" disabled="true">
      </@ww.select>
      
      <@ww.textfield label="'${action.getText('device.trusteeshipSupplierName')}'" name="'device.trusteeshipSupplierName'" value="'${device.trusteeshipSupplierName?if_exists}'" cssClass="'underline'" disabled="true"/>
      
	  <@pp.datePicker label="'${action.getText('device.cardCreatedTime')}'" name="'device.cardCreatedTime'"
	        value="'${(device.cardCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>	
	</tr>
	<tr>
		<@ww.textfield readonly="true" label="'${action.getText('device.stateAlterTime')}'" name="'device.stateAlterTime'" 
                     value="'${device.stateAlterTime?if_exists}'"  cssClass="'underline'" disabled="true"/>
	</tr>
    </@inputTable>
	<@buttonBar>
      <input type="button" name="close" value="${action.getText('close')}" class="button" onclick="window.close()" style="display:none">
      <#if !(action.isReadOnly())>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate()'"/>
      </#if>
      <#if device.enabled?exists>
	    <#if !(device.enabled)>
	      <@vsubmit name="'enabled'" value="'${action.getText('enabled')}'"/>
	    </#if>
	  </#if>
      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/asset/device/listDevices.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
      <input type="button" name="backHistory" value="${action.getText('back.history.page')}" class="button" onclick="javascript:history.go(-1);">
    </@buttonBar>
  </@ww.form>
  <script language="javascript" type="text/JavaScript">
   	<#if device.enabled?exists>
	  <#if !(device.enabled)>
	    disableAllFormElements()
	  </#if>
	</#if>
	/*
	 * 除了有效和返回按钮,其他所有控件都disabled
	*/
	function disableAllFormElements() {
	  __disableAllElements__(document.forms[0], new Array("enabled", "back"));
	  var x = document.forms[0].name + "_" + "device.cardCreatedTime" + "_trigger";
	  document.forms[0].elements[x].disabled="true";
	}
	function supplier_OpenDialog() {
	  var url = "${req.contextPath}/popup/supplierSelector.html?toolingDevFlag=DEVICE";
	  popupModalDialog(url, 800, 600, supplierSelectorHandler);
	}
	
	function supplierSelectorHandler(result) {
	  if(null != result) {
	    document.forms["device"].elements["supplier.id"].value = result[0];
	    document.forms["device"].elements["supplier.name"].value = result[1];
	  }
	}
	
	function deviceType_OpenDialog() {
	  var url = "${req.contextPath}/popup/deviceTypeSelector.html";
	  popupModalDialog(url, 800, 600, deviceTypeSelectorHandler);
	}
	
	function deviceTypeSelectorHandler(result) {
	  document.forms["device"].elements["deviceType.id"].value      = result[0];
	  document.forms["device"].elements["deviceType.name"].value = result[1];
	}
	
	function user_OpenDialog() {
	  var url = "${req.contextPath}/popup/userSelector.html";
	  popupModalDialog(url, 800, 600, userSelectorHandler);
	}
	
	function userSelectorHandler(result) {
	  document.forms["device"].elements["manager.id"].value = result[0];
	  document.forms["device"].elements["manager.name"].value = result[1];
	}
	
	function customize_validate() {
	  if ('' != document.forms["device"].elements["device.assetNo"].value) {     //验证资产编号的长度
	    if (!isValidLengthValue(document.forms["device"].elements["device.assetNo"].value,0,50)) {
	      alert("${action.getText('device.assetNo.maxLength')}");
	      return false;
	    }
	  }
	  /*
	   *验证设备名称是否为空，以及长度是否溢出
	  */
	  if ('' == document.forms["device"].elements["device.name"].value) {       
	    alert("${action.getText('device.name.requiredstring')}");
	    return false;
	  } else {
	    if (!isValidLengthValue(document.forms["device"].elements["device.name"].value,0,50)) {
	      alert("${action.getText('device.name.maxLength')}");
	      return false;
	    }
	  }
	  if ('' != document.forms["device"].elements["device.graphNo"].value) { //验证设备图号长度是否溢出
	    if (!isValidLengthValue(document.forms["device"].elements["device.graphNo"].value,0,50)) {
	      alert("${action.getText('device.graphNo.maxLength')}");
	  	  return false;
	    }
	  }	
	  if ('' == document.forms["device"].elements["deviceType.id"].value) { //验证设备类别是否为空
	    alert("${action.getText('deviceType.id.requiredstring')}");
	  	return false;
	  }
	  if ('' != document.forms["device"].elements["device.model"].value) { //验证设备型号长度是否溢出
	    if (!isValidLengthValue(document.forms["device"].elements["device.model"].value,0,50)) {
	      alert("${action.getText('device.model.maxLength')}");
	  	  return false;
	    }
	  }
	  if ('' != document.forms["device"].elements["device.specification"].value) { //验证设备规格长度是否溢出
	    if (!isValidLengthValue(document.forms["device"].elements["device.specification"].value,0,50)) {
	      alert("${action.getText('device.specification.maxLength')}");
	  	  return false;
	    }
      }
	  if (-1 == document.forms["device"].elements["department.id"].value) {    //验证使用部门是否为空
	    alert("${action.getText('department.id.requiredstring')}");
	  	return false;
	  }
	  if ('' != document.forms["device"].elements["device.installPlace"].value) { //验证安装地点长度是否溢出
	    if (!isValidLengthValue(document.forms["device"].elements["device.installPlace"].value,0,50)) {
	      alert("${action.getText('device.installPlace.maxLength')}");
	  	  return false;
	    }
      }
      if ('' == document.forms["device"].elements["manager.id"].value) {    //验证负责人是否为空
	    alert("${action.getText('manager.id.required')}");
	  	return false;
	  }
	  if (-1 == document.forms["device"].elements["productionLine.id"].value) {
	    document.forms["device"].elements["productionLine.id"].value='';
	  }
	  if ('' == document.forms["device"].elements["device.factory"].value) {       
	    alert("${action.getText('device.factory.required')}");
	    return false;
	  }else { //验证安装地点长度是否溢出
	    if (!isValidLengthValue(document.forms["device"].elements["device.factory"].value,0,50)) {
	      alert("${action.getText('device.factory.maxLength')}");
	  	  return false;
	    }
      }
      /*
       *验证建卡时间是否为空，格式是否正确，是否在当前日期之后
      */
	  if ('' == document.forms["device"].elements["device.cardCreatedTime"].value) {
	    alert("${action.getText('device.cardCreatedTime.required')}");
	    return false;
	  } else {
	    if (!isDate("device.cardCreatedTime")) {
	      alert("${action.getText('device.cardCreatedTime')}" + "," + "${action.getText('dateFormate.error')}");
	      return false;
	    }
	    if (isDateLessThenCurrent(document.forms["device"].elements["device.cardCreatedTime"].value)) {
	  	  alert("${action.getText('device.cardCreatedTime.late.error')}");
	  	  return false;
	    }
	  }

	  
	  return true;
	}
  </script>
  <#if device.id?exists>
  <script language="javascript">
  	var defNavItem=4;
	var navItem = new Array();
	navItem[1] = '<a id="extInfo" onclick="activeTab(this);getDate(1)"  href="editDeviceExtInfo.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('editDeviceExtInfo')}</a>';
	navItem[2] = '<a id="financeInfo" onclick="activeTab(this);getDate(2)"  href="editDeviceFinanceInfo.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('deviceFinanceInfo')}</a>';
	navItem[3] = '<a id="deviceDoc"   onclick="activeTab(this);getDate(3)" href="listDeviceDocs.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('device.doc')}</a>';
	navItem[4] = '<a id="accessoryDevice" onclick="activeTab(this);getDate(4)"  href="listAccessoryDevices.html?device.id=#{device.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('accessoryDevice')}</a>';	 
	navItem[5] = '<a id="attachTool" onclick="activeTab(this);getDate(5)"  href="listAttachTools.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('attachTool')}</a>';
	navItem[6] = '<a id="spare" onclick="activeTab(this);getDate(6)"  href="listToolingDevSpares.html?toolingDev.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&toolingDevFlag=DEVICE&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('spare')}</a>';	 
	navItem[7] = '<a id="args" onclick="activeTab(this);getDate(7)"  href="listDeviceArgs.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('deviceArgs')}</a>';
    navItem[8] = '<a id="preRepairRules" onclick="activeTab(this);getDate(8)"  href="${req.contextPath}/repair/preRepair/rule/listPreRepairRules.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('device.preRepairRule')}</a>';
    navItem[9] = '<a id="checkPointRule" onclick="activeTab(this);getDate(9)"  href="${req.contextPath}/runmaintenance/toolingDev/checkpoint/listCheckPointRule.html?toolingDev.id=#{device.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('device.checkPointRule')}</a>';
    navItem[10] = '<a id="lubricationRules" onclick="activeTab(this);getDate(10)"  href="${req.contextPath}/runmaintenance/lubrication/listLubricationRules.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('device.lubrication')}</a>';
	navItem[11] = '<a id="maintenanceDeviceList" onclick="activeTab(this);getDate(11)" href="${req.contextPath}/runmaintenance/maintenance/listMaintenanceRules.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">保养标准</a>';
	navItem[12] = '<a id="failureHistory" onclick="activeTab(this);getDate(12)" href="${req.contextPath}/deviceFailureHistory/listDeviceFailureHistory.html?device.id=#{device.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame">${action.getText('failureHistory')}</a>';
	
	var navItemId = new Array();
	navItemId[1] = "extInfo";
	navItemId[2] = "financeInfo";
	navItemId[3] = "deviceDoc";
	navItemId[4] = "accessoryDevice";
	navItemId[5] = "attachTool";
	navItemId[6] = "spare";
	navItemId[7] = "args";
	navItemId[8] = "preRepairRules";
	navItemId[9] = "checkPointRule";
	navItemId[10] = "lubricationRules";
	navItemId[11] = "maintenanceDeviceList";
	navItemId[12] = "failureHistory";
	
	var noCount = new Array();
	noCount[0] = new Array(1,2,3,4);
	noCount[1] = new Array(5,6,7,8);
	noCount[2] = new Array(9,10,11,12);
	var c = 0;
	
	function getDate(item){
		date = item;
		curPos = date;
	}
	
	function initNav(num) {
		for (var i = 1; i <= noCount[num].length; i ++) {
			var temp = noCount[num][i-1];
			getObjByNameRe(i).innerHTML = navItem[temp];
		}
		c=num;
	}
	
	var curPos=1;
	function scrollNav(pos,pageCount) {
		if(pos <=1){
			pos=1;
		}else if(pos >=12){
			pos=9;
		}
		var x = c + pageCount;
		if(x < 0){
			x = 0;
		}else if(x > 2){
			x = 2;
		}
		for (var i = 0,j = 1; i < noCount[x].length; i ++,j ++) {
			if(j<=4){
				var t = noCount[x][i];
				getObjByNameRe(j).innerHTML = navItem[t];
			} 
		}
		 <#if device.id?exists>
     		document.all.frame.src = getObjByNameRe(navItemId[pos]).href;
	 		getObjByNameRe(navItemId[pos]).className = "selectedtab";
	   	  </#if>
		 if (pos<=1){
			 pos=1; 
			 try{getObjByNameRe("navl").style.visibility="hidden"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="visible"}catch(e){};
		 }else if(pos>=9){
			 try{getObjByNameRe("navl").style.visibility="visible"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="hidden"}catch(e){};
		 }else{
			 try{getObjByNameRe("navl").style.visibility="visible"}catch(e){};
			 try{getObjByNameRe("navr").style.visibility="visible"}catch(e){};
		 }
	 	c=x;
	 	curPos = pos;
	}
  </script>
  </#if>
  <script language="javascript" type="text/JavaScript">
    window.onload = function () {
      	<#if device.useCategory?exists>
			document.forms["device"].elements["useCategory.id"].value=#{device.useCategory.id?if_exists};
		</#if>
		<#if device.filiale?exists>
			document.forms["device"].elements["filiale.id"].value=#{device.filiale.id?if_exists};
		</#if>
	  	<#if device.department?exists>
		   document.forms["device"].elements["department.id"].value = #{device.department.id?if_exists};
		 <#elseif loginUser.department?exists>
		   getObjByNameRe("department.id").value = #{loginUser.department.id};
		 </#if>
		<#--<#if device.excellentBigSparse?exists>
	         selector = document.all("excellentBigSparse.id");
	         groups=selector.options.length;
	         for (var i=0; i<groups; i++){
	            if (selector.options[i].value=="${device.excellentBigSparse.id?if_exists}"){
	               selector.options[i].selected="true";
	            }
         	} 
         </#if>-->
	  <#if device.id?exists>
	    initNav(0);
	    var deviceId = formatDigital("${device.id}");
	    var url = '${req.contextPath}/asset/device/editDeviceExtInfo.html?device.id=' + deviceId + '&readOnly=${req.getParameter('readOnly')?if_exists}';
		if (document.forms['device'].elements['docview'].value != '') {
		  url = url + '&disabled=' + document.forms['device'].elements['docview'].value; 
		}
		document.all.frame.src= url;
		getObjByNameRe("extInfo").className = "selectedtab";
		try{getObjByNameRe("navl").style.visibility="hidden"}catch(e){};
		/*
		var ck = getCookie("activeTabId");
		if (null == ck) {
		  setCookie("activeTabId", url);
		}
		document.all.frame.src=  getCookie("activeTabId");
		if (getCookie("activeFrameId") != null) {
	      getObjByNameRe(getCookie("activeFrameId")).className = "selectedtab";
		} else {
			 		
		}
		deleteCookie("activeTabId");
		deleteCookie("activeFrameId");
		*/
	 <#else>
	   a = new Date();
	   var time=a.format("yyyy-MM-dd");   
	   document.forms[0].elements["device.cardCreatedTime"].value=time;
	 </#if>
	 <#if device.specificationProp?exists>
	   document.forms["device"].elements["specificationProp.id"].value = #{device.specificationProp.id?if_exists};
	 </#if>
	 <#if device.deviceStatus?exists>
	   document.forms["device"].elements["device.status.id"].value = #{device.deviceStatus.id?if_exists};
	 </#if>
	 <#if device.deviceType?exists>
	   document.forms["device"].elements["deviceType.id"].value = #{device.deviceType.id?if_exists};
	 </#if>
	 toSortProductionLineByDepartment();	
	 if (-1 == document.forms["device"].elements["department.id"].value) {
	   document.forms["device"].elements["productionLine.id"].length=1;
	 } else {
	     departmentValueChange("department.id", "productionLine.id");
	     <#if device.productionLine?exists>
	       document.forms["device"].elements["productionLine.id"].value = #{device.productionLine.id?if_exists};
	     </#if>
	 }
	 <#if device.calUnit?exists>
	         selector = document.all("calUnit.id");
	         groups=selector.options.length;
	         for (var i=0; i<groups; i++){
	            if (selector.options[i].value=="${device.calUnit.id?if_exists}"){
	               selector.options[i].selected="true";
	            }
         	} 
         </#if>
	}
  </script>
  <#if device.id?exists>
    <ul id="beautytab">
  	<li id="navl"><a href="javascript:scrollNav(curPos-4,-1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_left.gif" alt="${action.getText('lastPage')}" border="0" /></a></li>
    <li id="1"/>
    <li id="2"/>
    <li id="3"/>
    <li id="4"/>
    <li id="navr"><a href="javascript:scrollNav(curPos+4,1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_right.gif" alt="${action.getText('nextPage')}" border="0" /></a></li>
  </ul>
	<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
  </#if>
  
</@htmlPage>