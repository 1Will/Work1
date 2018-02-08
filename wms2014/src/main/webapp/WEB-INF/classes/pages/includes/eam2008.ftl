<#--Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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
<#include "macros.ftl" />

<#-- $Id: eam2008.ftl 11220 2008-03-07 10:03:29Z mwei $ -->
<#macro framePage title="${action.getText('title')}">
    <html>
        <head>
        	<meta http-equiv="Pragma" content="no-cache">
        	<meta http-equiv="Expires" content="-1">
            <title>${action.getText('name.application')}-${title}</title>
            <link rel="stylesheet" href="${req.contextPath}/stylesheets/valuelist.css" type="text/css"/>
	        <link rel="stylesheet" href="${req.contextPath}/stylesheets/default.css" type="text/css"/>
	        <link rel="stylesheet" type="text/css" href="${req.contextPath}/stylesheets/dtree.css" />
	        
	        <script type='text/javascript' src='${req.contextPath}/dwr/engine.js'></script>  
			<script type='text/javascript' src='${req.contextPath}/dwr/util.js'></script>
			
	        <script language="javascript" src="${req.contextPath}/javascripts/global.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/lang/global.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/jsrsClient.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/modalDialog.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/eam2008.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/cookies.js"></script>
	        <script language="javascript" src="${req.contextPath}/javascripts/valueList.js"></script>
        	<script language="javascript" src="${req.contextPath}/javascripts/prototype-1.6.0.2.js"></script>
        	<script language="javascript" src="${req.contextPath}/javascripts/dtree.js"></script>
			
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/FilialeSelectDeptJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/WareCascadeRegionalJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/findWareHouseJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/findSpareLocationStockJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/updateParameterFromPageJs.js'></script>
			<script type='text/javascript' src='${req.contextPath}/dwr/interface/findLocationByRegionalJs.js'></script>
        </head>
        <body>
            <#nested>
        </body>
    </html>
</#macro>

<#--
	设备选择
-->
<#macro eam2008_DeviceSelector display="true" flag="null" required="true" select="false">
 <tr>
   <td align="right" valign="top">
   <#if required?has_content && required=="true">
     <span class="required">*</span>
   </#if>
   <label class="label">${action.getText('device.no')}:</label>
   </td>
   <td>
     <#if device?exists>
       <input type="text" name="device.deviceNo" class="underline"  value="${device.deviceNo?if_exists}"  maxlength="150" disabled/>
	 <#else>
	 	<input type="text" name="device.deviceNo" class="underline"  value=""  maxlength="150" disabled/>
	 </#if>
	 <#if display?has_content && display=="true">
	   <a onClick='eam2008_device_OpenDialog("${req.contextPath}/popup/deviceSelector.html","${flag?if_exists}")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 </#if>
	 <#if device?exists>
	   <#assign deviceName ="${device.name?if_exists}" />
	 <#else>
	   <#assign deviceName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="device.name" disabled value="${deviceName}"/>
 	</td>
 	<#if device?exists && device.department?exists>
 	  <@ww.textfield label="'${action.getText('department')}'" name="'device.department.name'"   value="'${device.department.name?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	<#else>
	  <#if select=="true">
	  	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
	    		   listKey="id" listValue="name"
	               list="departments" emptyOption="true" disabled="false"   required="true">
	    </@ww.select>
	  <#else>
	  	<@ww.textfield label="'${action.getText('department')}'" name="'device.department.name'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
	  </#if>
	</#if>
  </tr>
  <#if device?exists>
 	<@ww.hidden name="'device.id'" value="'#{device.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'device.id'" value=""/>
  </#if>
</#macro>

<#--
	设备选择
-->
<#macro eam2008_DeviceSelector_Borrow display="false" flag="null" required="true" select="false" oldDeviceIds="null">
 <tr>
   <td align="right" valign="top">
   <#if required?has_content && required=="true">
     <span class="required">*</span>
   </#if>
    	<label class="label">${action.getText('device.no')}:</label>
   		</td>
  		<td>
	     <#if tooling?exists>
	       <input type="text" name="device.deviceNo" class="underline"  value="${tooling.deviceNo?if_exists}"  maxlength="150" disabled/>
		 <#else>
		   <input type="text" name="device.deviceNo" class="underline"  value=""  maxlength="150" disabled/>
		   </label>
		 </#if>
		 <#if display?has_content && display=="false">
		   <a onClick='eam2008_deviceBorrow_OpenDialog("${req.contextPath}/popup/deviceSelector.html","${flag?if_exists}","${oldDeviceIds?if_exists}")'>
		     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		   </a>
		 </#if>
		 <#if tooling?exists>
	   		<#assign deviceName ="${toolingBorrowBill.tooling.name?if_exists}" />
	 	<#else>
	   		<#assign deviceName ="" />
	 	</#if>
	 	<input type="text" style="border:0px" name="device.name" disabled value="${deviceName}"/>
 	</td>
  </tr>
  <#if device?exists>
 	<@ww.hidden name="'device.id'" value="'#{device.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'device.id'" value=""/>
  </#if>
</#macro>


<#--
   工装选择
-->
<#macro eam2008_ToolingSelectorMore toolingStatus="false" flag="null" required="true">
 <tr>
   <td align="right" valign="top">
   <#if required?has_content && required=="true">
     <span class="required">*</span>
   </#if>
   <label class="label">${action.getText('tooling.no')}:</label>
   </td>
   <td>
     <#if tooling?exists>
       <input type="text" name="tooling.deviceNo" class="underline"  value="${tooling.deviceNo?if_exists}"  maxlength="150" disabled="true"/>
	 <#else>
	   <input type="text" name="tooling.deviceNo" class="underline"  value=""  maxlength="150" disabled="true"/>
	   </label>
	 </#if>
	 <#--
	 <#if tooling?exists>
	 <#else>
	   <a onClick='eam2008_tooling_OpenDialog("${req.contextPath}/popup/toolingSelector.html")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 </#if>
	 -->
	   <a onClick='eam2008_tooling_OpenDialog("${req.contextPath}/popup/toolingSelector.html","${flag?if_exists}")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 <#if tooling?exists>
	   <#assign toolingName ="${tooling.name?if_exists}" />
	 <#else>
	   <#assign toolingName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="tooling.name" disabled value="${toolingName}"/>
 	</td>
 	<#if tooling?exists>
 	  <@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'tooling.graphNo'"   value="'${tooling.graphNo?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	<#else>
	  <@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'tooling.graphNo'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
	</#if>
  </tr>
  <#if tooling?exists>
 	<@ww.hidden name="'tooling.id'" value="'#{tooling.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'tooling.id'" value=""/>
  </#if>
</#macro>

<#--
  工装选择
-->
<#macro eam2008_ToolingSelector toolingStatus="false" flag="null" required="true">
 <tr>
   <td align="right" valign="top">
   <#if required?has_content && required=="true">
     <span class="required">*</span>
   </#if>
	   <label class="label">${action.getText('tooling.no')}:</label>
	   </td>
	   <td>
	     <#if tooling?exists>
	       <input type="text" name="tooling.deviceNo" class="underline"  value="${tooling.deviceNo?if_exists}"  maxlength="150" disabled="true"/>
		 <#else>
		   <input type="text" name="tooling.deviceNo" class="underline"  value=""  maxlength="150" disabled="true"/>
		   </label>
		 </#if>
		 <#--
		 <#if tooling?exists>
		 <#else>
		   <a onClick='eam2008_tooling_OpenDialog("${req.contextPath}/popup/toolingSelector.html")'>
		     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		   </a>
		 </#if>
		 -->
		<#if toolingStatus?has_content && toolingStatus=="false">
		   <a onClick='eam2008_tooling_OpenDialog("${req.contextPath}/popup/toolingSelector.html","${flag?if_exists}")'>
		     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		   </a>
		 </#if>
	 	 <#if tooling?exists>
	   		<#assign toolingName ="${tooling.name?if_exists}" />
		 <#else>
	  		<#assign toolingName ="" />
	 	 </#if>
		 <input type="text" style="border:0px" name="tooling.name" disabled value="${toolingName}"/>

 	</td>
		<#if tooling?exists>
	 	  <@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'tooling.graphNo'"   value="'${tooling.graphNo?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
		<#else>
		  <@ww.textfield label="'${action.getText('tooling.graphNo')}'" name="'tooling.graphNo'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
		</#if>
  </tr>
	  <#if tooling?exists>
	 	<@ww.hidden name="'tooling.id'" value="'#{tooling.id?if_exists}'"/>
	  <#else>
		<@ww.hidden name="'tooling.id'" value=""/>
	  </#if>
</#macro>

<#--
    润滑油选择
-->
<#macro eam2008_LubricationOilSelector>
 <tr>
   <td align="right" valign="top">
     <span class="required"></span><label class="label">${action.getText('lubricatingOil.material')}:</label>
   </td>
   <td>
     <#if lubricationOil?exists>
       <input type="text" name="lubricationOil.code" class="underline"  value="${lubricationOil.code?if_exists}"  maxlength="150" disabled="true"/>
	 <#else>
	   <input type="text" name="lubricationOil.code" class="underline"  value=""  maxlength="150" disabled="true"/>
	   </label>
	 </#if>
	 <a onClick='eam2008_lubricationOil_OpenDialog("${req.contextPath}/popup/lubricationOilSelector.html")'>
	    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 </a>
	 <#if lubricationOil?exists>
	   <#assign lubricationOilName ="${lubricationOil.name?if_exists}" />
	 <#else>
	   <#assign lubricationOilName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="lubricationOil.name" disabled value="${lubricationOilName}"/>
 	</td>
 	<#if lubricationOil?exists && lubricationOil.measureUnit?exists>
 	  <@ww.textfield label="'${action.getText('lubricationOil.measureUnit')}'" name="'lubricationOil.measureUnit'"   value="'${lubricationOil.measureUnit.value?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	<#else>
	  <@ww.textfield label="'${action.getText('lubricationOil.measureUnit')}'" name="'lubricationOil.measureUnit'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
	</#if>
  </tr>
  <#if lubricationOil?exists>
 	<@ww.hidden name="'lubricationOil.id'" value="'#{lubricationOil.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'lubricationOil.id'" value=""/>
  </#if>
</#macro>

<#--
    工种选择
-->
<#macro eam2008_WorkTypeSelector>
 <tr>
   <td align="right" valign="top">
     <span class="required">*</span><label class="label">${action.getText('workType.code')}:</label>
   </td>
   <td>
     <#if workType?exists>
       <input type="text" name="workType.code" class="underline"  value="${workType.code?if_exists}"  maxlength="150" disabled="true"/>
	 <#else>
	   <input type="text" name="workType.code" class="underline"  value=""  maxlength="150" disabled="true"/>
	   </label>
	 </#if>
	 <a onClick='eam2008_workType_OpenDialog("${req.contextPath}/popup/workTypeSelector.html")'>
	    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 </a>
	 <#if workType?exists>
	   <#assign workTypeName ="${workType.name?if_exists}" />
	 <#else>
	   <#assign workTypeName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="workType.name" disabled value="${workTypeName}"/>
 	</td>
 	<#if workType?exists>
 	  <#assign workTypeUnitPrice = ''/>
 	  <#if workType.unitPrice?exists>
 	    <#assign workTypeUnitPrice = "${workType.unitPrice?if_exists}"/>
 	  </#if>
 	  <@ww.textfield label="'${action.getText('workType.unitPrice')}'" name="'workType.unitPrice'"   value="'${workTypeUnitPrice}'" cssClass="'underline'"  disabled="true" readonly="true"/>
	<#else>
	  <@ww.textfield label="'${action.getText('workType.unitPrice')}'" name="'workType.unitPrice'"   value="" cssClass="'underline'"  disabled="true" readonly="true"/>
	</#if>
  </tr>
  <#if workType?exists>
 	<@ww.hidden name="'workType.id'" value="'#{workType.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'workType.id'" value=""/>
  </#if>
</#macro>
<#--
	显示记录操作信息
	
	creator:			创建人
	createdTime:		创建时间
	lastOperator:		最后修改人
	lastModifiedTime:	最后修改时间
-->
<#macro eam2008_RecordLog creator="" createdTime="" lastOperator="" lastModifiedTime="">
  <tr>
    <#assign _createdTime = ""/>
    <#assign _lastModifiedTime = ""/>
	<#if creator?has_content>
	  <#assign _createdTime = "(${createdTime})"/>
	</#if>
	<#if lastOperator?has_content>
	  <#assign _lastModifiedTime = "(${lastModifiedTime})"/>
	</#if>
 	<td align="right" valign="top">
 	  <label class="label">${action.getText('record.creator')}:</label>
 	</td>
 	<td>
 	  <input type="text" size="25" class="underline"  value="${creator}${_createdTime}"   disabled/>
 	</td>
 	<td align="right" valign="top">
 	  <label class="label">${action.getText('record.lastOperator')}:</label>
 	</td>
 	<td>
 	  <input type="text" size="25" class="underline"  value="${lastOperator}${_lastModifiedTime}"   disabled/>
 	</td>
 </tr>
</#macro>

<#--
	锁定页面上所有可触发的控件
	
    disabled:	=1 时，锁定所有
    view:       =1 时，隐藏除了"close"的所有按钮控件
-->
<#macro eam2008_LockPageIfNeed>
  var disabled = '${req.getParameter('disabled')?if_exists}';
  var view = '${req.getParameter('view')?if_exists}';
  if (disabled==1) {
    __disableAllElements__(document.forms[0], new Array(""));
  }
  if (view==1) {
  	eam2008_hiddenButtonElements(document.forms[0],new Array("close"));
  }
</#macro>

<#--
	供应商选择
-->
<#macro eam2008_SupplierSelector toolingDevFlag="DEVICE">
 <tr>
   <td align="right" valign="top">
     <span class="required">*</span><label class="label">${action.getText('supplier.no')}:</label>
   </td>
   <td>
     <#if supplier?exists>
       <input type="text" name="supplier.supplierNo" class="underline"  value="${supplier.supplierNo?if_exists}"  maxlength="150" readonly="true" disabled="true" />
	 <#else>
	   <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" />
	 </#if>
	   <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/supplierSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 <#if supplier?exists>
	   <#assign supplierName ="${supplier.name?if_exists}" />
	 <#else>
	   <#assign supplierName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="supplier.name" disabled value="${supplierName}"/>
 	</td>
  </tr>
  <#if supplier?exists>
 	<@ww.hidden name="'supplier.id'" value="'${supplier.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'supplier.id'" value=""/>
  </#if>
</#macro> 

<#macro eam2008_SupplierSelectorLessTr toolingDevFlag="DEVICE">
   <td align="right" valign="top">
     <span class="required">*</span><label class="label">${action.getText('supplier.no')}:</label>
   </td>
   <td>
     <#if supplier?exists>
       <input type="text" name="supplier.supplierNo" class="underline"  value="${supplier.supplierNo?if_exists}"  maxlength="150" readonly="true" disabled="true" />
	 <#else>
	   <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" disabled="true"/>
	 </#if>
	   <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/supplierSelector.html?toolingDevFlag=${toolingDevFlag?if_exists}")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 <#if supplier?exists>
	   <#assign supplierName ="${supplier.name?if_exists}" />
	 <#else>
	   <#assign supplierName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="supplier.name" disabled value="${supplierName}"/>
 	</td>
  <#if supplier?exists>
    <#if supplier.id?exists>
 	  <@ww.hidden name="'supplier.id'" value="'#{supplier.id}'"/>
 	</#if>
  <#else>
	<@ww.hidden name="'supplier.id'" value=""/>
  </#if>
</#macro> 
<#--
    在列表中显示能够选择时间的控件
   inputName ：input 类型控件name属性的值
   inputId : input 类型控件id属性的值
   inputValue : input类型控件value属性的值
   imgId : img 类型控件id属性的值
-->
<#macro eam2008_dataPicker inputName="" inputId="" inputValue="" inputMethod="" imgId="" formName="" dateFormate="%Y-%m-%d" disable="">
	<#assign disableTiger = ''/>
	<#if "${disable}"='false'>
	<#assign disableTiger = ''/>	
	<#else>
	<#assign disableTiger = 'disabled'/>
	</#if>
  <input type="text"
         name="${inputName?if_exists}"
         size="10"                    
         value="${inputValue?if_exists}"                     
         id="${inputId?if_exists}"
         onchange="${inputMethod?if_exists}"                       
         class="underline"     ${disableTiger?if_exists}  />                    
  <img id="${imgId?if_exists}" src="${req.contextPath}/images/calendar/calendar.gif" align=absMiddle border=0 />
  <script language="javascript">
    Calendar.setup({
      formName       :    "${formName?if_exists}",
      inputField     :    "${inputName?if_exists}",
      button         :    "${imgId?if_exists}",
      ifFormat       :    "${dateFormate?if_exists}",
      showsTime      :    false,
      timeFormat     :    "24",
      showOthers     :    true
    });
  </script>
</#macro>

<#--
    用于显示"仅查询失效"的checkbox
-->
<#macro eam2008_onlySearchInvalid_checkBox>
  <@ww.hidden name="'onlyValid'" value="true"/>
  <@ww.hidden name="'onlyInvalid'" value=""/>
  <@ww.checkbox label="'${action.getText('includeDisabled')}'" name="'onlyDisabled'" value="'false'" fieldValue="'value'" onblur="'changValidOrInvalidStatus()'"/>      
  <script language="javascript">
    function changValidOrInvalidStatus(){
      if ($('onlyDisabled').checked) {
        $('onlyInvalid').value = "true";
        $('onlyValid').value = "";
      } else {
        $('onlyInvalid').value = "";
        $('onlyValid').value = "true";
      }
    }
   <#if (action.isOnlyInvalid())>
     $('onlyDisabled').checked=true;
     changValidOrInvalidStatus();
   </#if>
  </script>
</#macro>

<#--
    显示失效或有效按钮
    message  :  要失效的纪录的消息
    boxName  :  要失效纪录的checkbox的Id
    jsFunctionName  :  js函数名
    isDispalyDelete : 是否显示”删除"按钮  yes[表示显示]|no[表示不显示]
-->
<#macro eam2008_disabledOrEnabled_button message="" boxName="" jsFunctionName="" isDispalyDelete="yes">
  <#if (action.isOnlyInvalid())>
  	<#assign confirmMessage1 = "${action.getText('confirm.valid')}${message?if_exists}?" />
	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmValids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
	<#if isDispalyDelete?has_content && isDispalyDelete=="yes">
	    <#assign confirmMessage = "${action.getText('confirm.delete')}${message?if_exists}?" />
		<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		  <@ww.param name="'onclick'" value="'return validateInvalid(confirmDeletes(\"${boxName?if_exists}\", \"${confirmMessage}\"),${jsFunctionName});'"/>
		  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
		</@vsubmit>
	<#else>
	</#if>
  <#else>
    <#assign confirmMessage1 = "${action.getText('confirm.inValid')}${message?if_exists}?" />
	<@vsubmit name="'disabled'" value="'${action.getText('disabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(confirmInvalids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  </#if>
  <script language="javascript">
	function validateInvalid(delFun, checkFun) {
      if (delFun) {
        checkFun;
        return true;
      }
      return false;
   }
  </script>
</#macro>

<#--
    带锁定/解锁功能的失效和有效
-->
<#macro eam2008_disabledOrEnabled_button_have_locked_unLocked message="" boxName="" jsFunctionName="" jsFunctionName1="null">
  <#if (action.isOnlyInvalid())>
  	<#assign confirmMessage1 = "${action.getText('confirm.valid')}${message?if_exists}?" />
	<@vsubmit name="'enabled'" value="'${action.getText('enabled')}'">
	  <@ww.param name="'onclick'" value="'return validateValid(\"null\",confirmValids(\"${boxName?if_exists}\", \"${confirmMessage1}\"),${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  <#else>
    <#assign confirmMessage1 = "${action.getText('confirm.inValid')}${message?if_exists}?" />
	<@vsubmit name="'disabled'" value="'${action.getText('disabled')}'">
	  <@ww.param name="'onclick'" value="'return validateInvalid(${jsFunctionName1},\"${boxName?if_exists}\", \"${confirmMessage1}\",${jsFunctionName});'"/>
	  <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
  </#if>
  <script language="javascript">
	function validateInvalid(fun,boxName,message, checkFun) {
	  if ("null" != fun) {
	    if (!fun) {
	      return false;
	    }
	  }
      if (confirmInvalids(boxName, message)) {
        checkFun;
        return true;
      }
      return false;
   }
   function validateValid(fun,delFun, checkFun) {
	  if ("null" != fun) {
	    if (!fun) {
	      return false;
	    }
	  }
      if (delFun) {
        checkFun;
        return true;
      }
      return false;
   } 
   <#if (action.isOnlyInvalid())>
     document.getElementById("onlyDisabled").checked=true;
     changValidOrInvalidStatus();
   </#if>
  </script>
</#macro>

<#--
    预算明细查询选择
-->
<#macro eam2008_FeeSourceTypeSelector toolingDevFlag="TOOLING" planProcFlag="PLAN" disabled="false" require="true">
 
   <@ww.select label="'${action.getText('sourceType')}'"  name="'sourceType'" 
	    	   listKey="realCode" listValue="value" value="'${sourceType?if_exists}'"
	           list="feeSourceTypes" onchange="'changeBudgeNo()'" 
	           emptyOption="true" disabled="${disabled}"  required="${require}">
   </@ww.select>
   <td id="budgetNoTitle" align="right" valign="top" style="display:none">
   <#if '${planProcFlag}'=='PLAN'>
     <span class="required"></span>
   </#if>
   <label class="label">${action.getText('budgetNo.select')}:</label></td>
      <td id="budgetNoContent" style="display:none">
        <#if budgetDetail?exists>
          <input type="text" name="budgetDetail.budgetNo" 
    			 class="underline"  value="${budgetDetail.budgetNo?if_exists}"  maxlength="150" size="20" disabled/>
        <#else>
          <input type="text" name="budgetDetail.budgetNo" class="underline"  value=""  maxlength="150" disabled/>
        </#if>
        <#if '${planProcFlag}'=='PLAN'>
          <a onClick='eam2008_budgetDetail_OpenDialog("${req.contextPath}/popup/budgetDetailSelector.html?toolingDevFlag=${toolingDevFlag}")'>
	        <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	      </a>
	    </#if>
	    <#if budgetDetail?exists>
	     <#assign budgetDetailName ="${budgetDetail.budgetName?if_exists}" />
	   <#else>
	     <#assign budgetDetailName ="" />
	   </#if>
       <input type="text" style="border:0px" name="budgetDetail.name" disabled value="${budgetDetailName}"/>
       <#if budgetDetail?exists>
 	     <@ww.hidden name="'budgetDetail.id'" value="'#{budgetDetail.id?if_exists}'"/>
       <#else>
	     <@ww.hidden name="'budgetDetail.id'" value=""/>
       </#if>
    </td>
 
 <script language="javascript">
   /*
	* 如果费用来源是预算内,则显示预算编号字段
   */
   if($('sourceType').value=='IN_BUDGET'){
     $('budgetNoTitle').style.display='inline';
	 $('budgetNoContent').style.display='inline';
   }
   /*
    * 如果是预算内，则显示预算编号字段
    * 如果是预算外，则不显示预算编号字段
   */
   function changeBudgeNo() {
     if($('sourceType').value=='IN_BUDGET'){
 	   $('budgetNoTitle').style.display='inline';
 	   $('budgetNoContent').style.display='inline';
     }
     if($('sourceType').value=='OUT_BUDGET'){
 	   $('budgetNoTitle').style.display='none';
 	   $('budgetNoContent').style.display='none';
 	   //document.forms[0].elements["budgetDetail.budgetNo"].value='';
 	   $('budgetDetail.id').value='';
     }
     return true;
  }
 </script>
</#macro>

<#--供应商选择-->
<#macro eam2008_SupplierSelectorSpare>
   <td align="right" valign="top">
     <span class="required">*</span><label class="label">${action.getText('supplier.no')}:</label>
   </td>
   <td>
     <#if spareInBill.supplier?exists>
       <input type="text" name="supplier.supplierNo" class="underline"  value="${spareInBill.supplier.supplierNo?if_exists}"  maxlength="150" readonly="true" disabled="true" />
	 <#else>
	   <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" disabled="true"/>
	 </#if>
	   <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/supplierSelector.html")'>
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 <#if spareInBill.supplier?exists>
	   <#assign supplierName ="${spareInBill.supplier.name?if_exists}" />
	 <#else>
	   <#assign supplierName ="" />
	 </#if>
	 <input type="text" style="border:0px" name="supplier.name" disabled value="${supplierName}"/>
 	</td>
  <#if spareInBill.supplier?exists>
 	<@ww.hidden name="'supplier.id'" value="'${spareInBill.supplier.id?if_exists}'"/>
  <#else>
	<@ww.hidden name="'supplier.id'" value=""/>
  </#if>
</#macro> 

 

<#--
    在列表中能选择和输入库位的控件
   code ：库位号值
   loopNum : 当前列表循环数
   inputName : input类型控件name属性的前部分值 [name属性格式: inputName + loopNum]
-->
<#macro eam2008_remoteGetLocation id="" code=""  loopNum="" inputName=""  disabled="false" >
 	<#if "${disabled}"='false'>
	 	<input type="text" name="${inputName}${loopNum}" 
		class="underline"  value="${code}"  maxlength="150" size="10" onblur="getLocationCode(${loopNum})" /><#--(zzb 删除 readonly="true")-->
		<#--<input type="hidden" name="warehouse${loopNum}"   value=""  />-->
		<#--<input type="hidden" name="regional${loopNum}"    value="" />-->
    <label id=""></label>
	<a onClick="selectLocation(${loopNum});">
		<img id="img1" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
	</a>
	<#else>
	 	<input type="text" name="${inputName}${loopNum}" 
		class="underline"  value="${code}"  maxlength="150" size="10" onblur="getLocationCode(${loopNum})" disabled/>
    <label id=""></label>
	<a>
		<img id="img1" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline" disabled/>
	</a>
	</#if>
	<input type="hidden" name="hidden.${inputName}${loopNum}" value="true" />
	<input type="hidden" name="moveId.${inputName}${loopNum}" value="${id}" />
	<script language="javascript">
	  var LOOP_NUM;
	  function getLocationCode(loopNum) {
	    LOOP_NUM = loopNum;
	    if(document.getElementById("${inputName}" + loopNum).value!=""){
	      try{
	        remoteGetLocationCode(document.getElementById("${inputName}" + loopNum).value);
	      }catch(x){}
	    }
	  }
	  
	  function remoteGetLocationCode(code) {
        if(code.length>0){
          var val=new Array(5);
          val[0]="locationCodeSelectorAjax";
          val[1]=code;
          val[2]="id";
          val[3]="";
          val[4]="code";
          jsrsExecute("${req.contextPath}/remoteSelector.html", locationCode_SetPickedResult, val);
        }
	  }
	  
	  function locationCode_SetPickedResult(msg){
	  
        if (msg.length == 0) {
                var value = document.getElementById("${inputName}" + LOOP_NUM).value;
                document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "false";
                alert(value + "${action.getText('location_notFound')}");
            } else {
                document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "true";
                var result = jsrsArrayFromString(msg);
            }
      }
      
      function selectLocation(loopNum) {
        LOOP_NUM = loopNum;
        location_OpenDialog();
      }
      
      function location_OpenDialog(){
      	var url="${req.contextPath}/newLocation/locationSearcherCommon.html";
        <#if spareInBill?exists>
        	<#if spareInBill.warehouse.id?exists>
       		url = url+"?warehouse=#{spareInBill.warehouse.id}&fromSpareInBillDetail=true";
       		</#if>
	 	<#elseif movePostionBill?exists>
	 		<#if movePostionBill.warehouse.id?exists>
	   		url = url+"?warehouse=#{movePostionBill.warehouse.id}";
	   		</#if>
	 	</#if>
		popupModalDialog(url, 800, 600, locationSelectorHandler);
      }
      
      function locationSelectorHandler(result) {
        
        if (null != result) {
          var objLocationCode = document.getElementById("${inputName}" + LOOP_NUM);
		  <#--var objWarehouseName = document.getElementById("warehouse"+ LOOP_NUM);-->
		  <#--var objRegionalName = document.getElementById("regional"+ LOOP_NUM);-->
		  
          objLocationCode.value = result[1];
          <#--zzb document.getElementById("afterMoveWarehouse"+ LOOP_NUM).value=result[3];-->
          <#--objWarehouseName.value=result[2];-->
          <#-- zzb document.getElementById("afterMoveRegional"+ LOOP_NUM).value=result[5];-->
          
          <#--objRegionalName.value=result[4];-->
		  document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "true"; 
          document.getElementById("moveId." + "${inputName}" + LOOP_NUM).value = result[0];
        }
      }
	</script> 
</#macro>
<#--
    在列表中能选择库位的控件
   inputName ：input 类型控件name属性的值
   inputId : input 类型控件id属性的值
   inputValue : input类型控件value属性的值
   imgId : img 类型控件id属性的值       
-->
<#macro eam2008_list_locationSelector formName="" inputName="" inputHiddenId="" objectValue="" objectId="" loopNum="" >
  <input type="text"
         name="${inputName?if_exists}"
         size="10"     
         value="${objectValue?if_exists}"   
         id="${objectId?if_exists}" 
         class="underline"     disabled/>    
  <a onClick="selectLoction(${loopNum?if_exists});">
    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
  </a>
  <#if '${objectId?if_exists}'==''>
    <input type="hidden" name="${inputHiddenId?if_exists}" value="" />
  <#else>
    <input type="hidden" name="${inputHiddenId?if_exists}" value="${(objectId?number)?if_exists}" />
  </#if>
  <#if '${loopNum}' == '0'>
    <input type="hidden" name="currentRowNum" value="" />
  </#if>
    <script language="javascript">
      //选择库位
      function selectLoction(loopNum) {
		Select.setup({
            formName       :    "${formName?if_exists}",		  
		    inputFieldName :    "${inputName?if_exists}",
		    inputHiddenName :   "${inputHiddenId?if_exists}",
		    currentRowNumHiddenName : "currentRowNum",
		    currentRowNum   :   loopNum,
		    url             : "${req.contextPath}/location/locationSearcherCommon.html"
		  }
		)
      }
        
  </script>
</#macro>
<#-- 0000000000000000000-->
<#--采购单 弹出页面选择 申购单明细 选择 生产厂家-->
<#macro factorySelectorInList size = "">
     <#if object.factory?exists>
       <input type="text" name="${factoryIdentityName}" class="underline"  value="${object.factory.name?if_exists}"  maxlength="150" size = "${size}" disabled/>
	   <input type="hidden" name="${factoryIdentityId}" value="#{object.factory.id?if_exists}"/>
	 <#else>
	   <input type="text" name="${factoryIdentityName}" class="underline"  value=""  maxlength="150" size = "12"  disabled/>
	    <input type="hidden" name="${factoryIdentityId}" value=""/>
	 </#if>
	  <a onClick="eam2008_factory_OpenDialogzzb('${req.contextPath}/popup/factorySelector.html','${itemNo}')" >
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 />
	   </a>
	 <#--
	 <#if !object.graphNo?exists || '${object.graphNo?if_exists}' == ''>
	    <a onClick="eam2008_factory_OpenDialogzzb('${req.contextPath}/popup/factorySelector.html','${itemNo}')" >
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 />
	   </a>
	 <#else>
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 disabled />
	   </a>
	 </#if>
	 -->
	   
   
      
   <#assign factoryIdentityName = 'factory' + '${itemNo}'/>
   <#assign factoryIdentityId = 'factoryId' + '${itemNo}'/>	
   <#assign imgIdentityName = 'img' + '${itemNo}'/>	
 <script language="javascript">
    var itemNo='';
    function eam2008_factory_OpenDialogzzb(url,num) {
        itemNo=num;
	    popupModalDialog(url, gw, gh, refresh_factory_informationzzb);
    }
    function refresh_factory_informationzzb (result) {
        if (null != result) {
          document.forms[0].elements['factory'+(itemNo-1)].value = result[1]; 
          document.forms[0].elements['factoryId'+(itemNo-1)].value = result[0]; 
          //document.forms[0].elements["factory.id"].value = result[0]; 
       }
    }
   </script>
   
</#macro>


<#-- 0000000000000000000-->
<#--出库单明细列表显示出库人-->
<#macro peopleSelectorInList >
     <#if object.outPeople?exists>
       <input type="text" name="${peopleIdentityName}" class="underline"  size ="12" value="${object.outPeople.name?if_exists}"  maxlength="20" disabled/>
	   <input type="hidden" name="${peopleIdentityId}" value="${object.outPeople.id?if_exists}"/>
	 <#else>
	   <input type="text" name="${peopleIdentityName}" class="underline"  value=""  size ="12" maxlength="20" disabled/>
	    <input type="hidden" name="${peopleIdentityId}" value=""/>
	 </#if>
	  <a onClick="eam2008_factory_OpenDialogzzb('${req.contextPath}/popup/userSelector.html','${itemNo}')" >
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 />
	   </a>
	 <#--
	 <#if !object.graphNo?exists || '${object.graphNo?if_exists}' == ''>
	    <a onClick="eam2008_factory_OpenDialogzzb('${req.contextPath}/popup/factorySelector.html','${itemNo}')" >
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 />
	   </a>
	 <#else>
	     <img name="${imgIdentityName}" src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0 disabled />
	   </a>
	 </#if>
	 -->
	   
   
      
   <#assign factoryIdentityName = 'factory' + '${itemNo}'/>
   <#assign factoryIdentityId = 'factoryId' + '${itemNo}'/>	
   <#assign imgIdentityName = 'img' + '${itemNo}'/>	
 <script language="javascript">
    var itemNo='';
    function eam2008_factory_OpenDialogzzb(url,num) {
        itemNo=num;
	    popupModalDialog(url, gw, gh, refresh_factory_informationzzb);
    }
    function refresh_factory_informationzzb (result) {
        if (null != result) {
          document.forms[0].elements['outPeopleName'+(itemNo-1)].value = result[1]; 
          document.forms[0].elements['outPeopleId'+(itemNo-1)].value = result[0]; 
          //document.forms[0].elements["factory.id"].value = result[0]; 
       }
    }
   </script>
   
</#macro>



<#--
   在输入框下方显示下拉框的控件
   required ：是否是必填属性(true:必填 false:非必填)
   validateJs: 控件所在维护页面的表单提交验证方法名
   在validateJs验证方法中加入:
		if($('show').style.display == "block"){
			$('show').style.display="none";
			var flag = event.srcElement.id.substring(6,7)-1;
			autoCopyValue(flag);
			return false;
		}
-->
<#macro factorySelector required="true" validateJs="null">
   <td align="right" valign="top">
   <#if required="true">
        <span class="required">*</span>
   </#if>
   <label class="label">生产厂家:</label>
   </td>
   <td>
     <#if factory?exists>
       <input type="text" id="factory.name" name="factory.name" class="underline"  value="${factory.name?if_exists}"  maxlength="150" disabled/>
	 <#else>
	   <input type="text" id="factory.name" name="factory.name" class="underline"  value=""  maxlength="150" onkeyup="showFactory()" autocomplete="off" onmouseout="checkFactory()"/>
	   <div id="show" style="background-color:#FFFFFF;border:1px solid; overflow-x:hidden; overflow-y:scroll; display: none;z-index:2;position:absolute;"></div>
	   </label>
	 </#if>
	   <a onClick='eam2008_factory_OpenDialog("${req.contextPath}/popup/factorySelector.html")'>
	     <img src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0/>
	   </a>
	 <#if factory?exists>
	   <#assign factoryCode ="${factory.supplierNo?if_exists}" />
	 <#else>
	   <#assign factoryCode ="" />
	 </#if>
	 <input type="text" style="border:0px" name="factory.supplierNo" disabled value="${factoryCode}"/>
	<script type='text/javascript' src='${req.contextPath}/dwr/interface/loadSuppliersByNameJs.js'></script>
 	</td>

  <#if factory?exists>
 	<@ww.hidden name="'factory.id'" value="#{factory.id?if_exists}"/>
  <#else>
	<@ww.hidden name="'factory.id'" value=""/>
  </#if>
  <script language="javascript">
	var selectflag = 0;
	var resultData;
	function showFactory(){
		var left = 0;					//设置弹出层的位置
		var top = 0;
		var e = $('factory.name');
		while(e.offsetParent){
			left += e.offsetLeft;
			top += e.offsetTop;
			e = e.offsetParent;
		}
		top += 20;
		$('show').style.left=left+"px";
		$('show').style.top=top+"px";
		
		var obj = $('factory.name').value;
		if(obj != "" && obj != null){
			loadSuppliersByNameJs.loadSuppliersByName(obj,callback);
		}
	}
	
	function checkFactory(){
		var factoryName = $('factory.name').value;
		if(factoryName != "" && factoryName != null){
			loadSuppliersByNameJs.loadSupplierByName(factoryName,callbackCheck);
		}
	}
	
	function callbackCheck(data){
		if(null == data){
			if($('factory.supplierNo').value != "" || $('factory.name').value != "" && $('show').style.display == "none"){
				clean();
			}
		}
	}
	
	function callback(data){
				if(data.length > 0){
					selectflag = data.length;
					resultData = data;
					$('show').innerHTML = "";
					for(var i = 0;i<data.length;i++){
						var tag = document.createElement("input");
						tag.setAttribute("type","text");
						var id = "option" + (i+1);
						tag.setAttribute("id",id);
						if(data[i]["name"].length>8){
							    tag.setAttribute("value",data[i]["name"].substring(0,8)+"...");
							  }else{
							    tag.setAttribute("value",data[i]["name"]);
							  }
							  tag.setAttribute("title",data[i]["name"]);
						tag.setAttribute("size","20");
						tag.setAttribute("readOnly","readOnly");
						tag.style.border = "0px";
						tag.onmouseover=function(){
								mouseON(this);
							};
						tag.onmouseout=function(){
								mouseOUT(this);
							};
						tag.onkeypress=function(){
									DWRUtil.onReturn(event, ${validateJs?if_exists});
									if(event.keyCode==13){				//选择下拉框的值后阻止直接提交表单
										return false;
									}
								};
						tag.onclick=function(){
									$('factory.name').value = this.value;
									$('show').style.display = "none";
									var flag = this.id.substring(6,7)-1;
									autoCopyValue(flag);				//给控件赋值
								};		
						$('show').appendChild(tag);
						$('show').appendChild(document.createElement('br'));
					}
					$('show').style.display = "block";
				}else{
					$('show').style.display = "none";
					clean();
					$('factory.name').focus();
				}	
					
				}
		
		 /**
		  * 鼠标移上焦点时触发事件
		  * 将焦点内容选中
		  */
		function mouseON(op){
			op.value = op.title;
			DWRUtil.selectRange(op.id, 0, op.size);
		}
		/**
		  * 鼠标移出焦点时触发事件
		  * 将焦点内容还原
		  */
		function mouseOUT(op){
			if(op.value.length>8){
				op.value = op.value.substring(0,8)+"...";
			}
		}
		/**
		 * 将控件自动赋值
		 */
		function autoCopyValue(flag){
			$('factory.id').value=resultData[flag]["id"];
			$('factory.name').value=resultData[flag]["name"];
			$('factory.supplierNo').value=resultData[flag]["supplierNo"];
			$('factory.name').focus();	
		}
		/**
		 * 将控件值清空
		 */
		 function clean(){
		 	$('factory.id').value="";
			$('factory.name').value="";
			$('factory.supplierNo').value="";
		 }
		 /**
	  	 * 页面点击事件源
	  	 */
	  	document.onclick = function(event){
	   		checkdiv(event);
		}
		/**
		 * 源默认触发事件
		 */
		function checkdiv(event){
		    var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    if(obj){
		    	//alert("源默认触发事件");
				$('show').style.display = "none";
				
		    }
		}
		/**
		 * 键盘上下键事件
		 */
		function myMethod(){
			//alert(event.altKey);
			//alert(event.keyCode);
			
			if(event.keyCode==38){
				if($('show').style.display == "block"){
					for(var i=selectflag; i>0; i--){
						var logo = 'option' + i;
						if(document.activeElement.id==logo){
							if($(logo).value.length>8){
								$(logo).value = $(logo).value.substring(0,8)+"...";
							}
							var nextLogo = 'option' + (i-1);
							if($(nextLogo) != null){
								$(nextLogo).value = $(nextLogo).title;
								DWRUtil.selectRange(nextLogo, 0, 20);
							}
							return;
						}
					}
					DWRUtil.selectRange('option1', 0, 10);
				}
			}else if(event.keyCode==40){
				if($('show').style.display == "block"){
					for(var i=1; i<selectflag+1; i++){
						var logo = 'option' + i;
						if(document.activeElement.id==logo){
							if($(logo).value.length>8){
								$(logo).value = $(logo).value.substring(0,8)+"...";
							}
							var nextLogo = 'option' + (i+1);
							if($(nextLogo) != null){
								$(nextLogo).value = $(nextLogo).title;
								DWRUtil.selectRange(nextLogo, 0, 20);
							}
							return;
						}
					}
					DWRUtil.selectRange('option1', 0, 10);
				}
			}else if(event.keyCode==13){
				if(document.selection.createRange().text != ""){
					$('factory.name').value = document.selection.createRange().text;
				}
				$('show').style.display = "none";
			}else if(event.keyCode==8 || event.keyCode==46 && $('show').style.display == "none"){
				showFactory();
			}
		}
		document.onkeyup = myMethod;
	</script>
</#macro>

<#macro equFactorySelector required="true">
   <td align="right" valign="top">
   <#if required="true">
        <span class="required">*</span>
   </#if>
   <label class="label">设备厂家:</label>
   </td>
   <td>
     <#if equFactory?exists>
       <input type="text" name="equFactory.name" class="underline"  value="${equFactory.name?if_exists}"  maxlength="150" disabled/>
	 <#else>
	   <input type="text" name="equFactory.name" class="underline"  value=""  maxlength="150" disabled/>
	   </label>
	 </#if>
	   <a onClick='eam2008_equfactory_OpenDialog("${req.contextPath}/popup/factorySelector.html")'>
	     <img src="${req.contextPath}/images/icon/files.gif" style="cursor: hand" align=absMiddle border=0/>
	   </a>
	 <#if equFactory?exists>
	   <#assign equFactoryCode ="${equFactory.supplierNo?if_exists}" />
	 <#else>
	   <#assign equFactoryCode ="" />
	 </#if>
	 <input type="text" style="border:0px" name="equFactory.supplierNo" disabled value="${equFactoryCode}"/>
 	</td>

  <#if equFactory?exists>
 	<@ww.hidden name="'equFactory.id'" value="${equFactory.id?if_exists}"/>
  <#else>
	<@ww.hidden name="'equFactory.id'" value=""/>
  </#if>
</#macro>



<#--
    在列表中能选择和输入采购人的控件
   code ：采购人编码
   loopNum : 当前列表循环数
   inputName : input类型控件name属性的前部分值 [name属性格式: inputName + loopNum]
-->
<#macro eam2008_getBuyer id="" code=""  name="" loopNum="" inputName="" inputId="" inputN="" disabled="false" >
 	<#if "${disabled}"='false'>
	 	<input type="text" name="${inputName}${loopNum}" id="${inputName}${loopNum}"
		class="underline"  value="${code}"  maxlength="150" size="6" onblur="getByName(this,${loopNum})" />
		<label id="${inputN}${loopNum}">${name}</label>
		<input type="hidden" name="name${loopNum}" value="${code}"/>
		<input type="hidden" name="${inputId}${loopNum}" id="${inputId}${loopNum}" value="${id}"/>
    <label id=""></label>
	<a onClick="selectBuyer(${loopNum});">
		<img id="img1" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline"/>
	</a>
	<#else>
	 	<input type="text" name="${inputName}${loopNum}" 
		class="underline"  value="${name}"  maxlength="150" size="6" onblur="getBuyerCode(${loopNum})" disabled/>		
		<label id="${inputN}${loopNum}">${code}</label>
		<input type="hidden" name="${inputId}${loopNum}" id="${inputId}${loopNum}" value="${id}"/>
    <label id=""></label>
	<a>
		<img id="img1" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0 style="display:inline" disabled/>
	</a>
	</#if>
	<input type="hidden" name="hidden.${inputName}${loopNum}" value="true" />
	<script type='text/javascript' src='${req.contextPath}/dwr/interface/getUserByNameJs.js'></script>
	<script language="javascript">
	  var LOOP_NUM;
	  function getBuyerCode(loopNum) {	  
	    LOOP_NUM = loopNum;
	    if(document.getElementById("${inputName}" + loopNum).value!=""){
	      try{	      
	        remoteGetBuyerCode(document.getElementById("${inputName}" + loopNum).value);	       
	      }catch(x){}
	    }
	  }
	  
	  function remoteGetBuyerCode(code) {	
        if(code.length>0){		
          var val=new Array(5);
          val[0]="userSelectorAjax";
          val[1]=code;
          val[2]="id";
          val[3]="";
          val[4]="code";
          jsrsExecute("${req.contextPath}/popup/userSelector.html", buyerCode_SetPickedResult, val);       	 
        }
	  }
	  
	  function getByName(name,loopNum){
	  	var name = name.value.replace(/(^\s*)|(\s*$)/g,"");
	  	LOOP_NUM = loopNum;
	  	if(name != ""){
	  		if(name != document.getElementById("NAME"+LOOP_NUM).value){
	  			getUserByNameJs.getUserByLoginName(name,getByNameCallBack)
	  		}
	  	}else{
	  		document.getElementById("${inputName}"+ LOOP_NUM).value='';
            document.getElementById("hidden." + "${inputName}"+ LOOP_NUM).value = "false";
            document.getElementById("${inputN}"+ LOOP_NUM).innerHTML=''; 
	  	}
	  	
	  }
	  
	  function getByNameCallBack(data){
	  	if(null != data){
	  		document.getElementById("${inputId}"+ LOOP_NUM).value=data.id;          
          	document.getElementById("${inputName}"+ LOOP_NUM).value=data.loginName;
          	document.getElementById("${inputN}"+ LOOP_NUM).innerHTML=data.name; 
	  	}else{
	  		var value = document.getElementById("${inputName}"+ LOOP_NUM).value;
	  		if(document.getElementById("${inputN}"+ LOOP_NUM).innerHTML==""){
	  			document.getElementById("${inputName}"+ LOOP_NUM).value='';
            	document.getElementById("hidden." + "${inputName}"+ LOOP_NUM).value = "false";
            	document.getElementById("${inputN}"+ LOOP_NUM).innerHTML=''; 
	  		}else{
	  			document.getElementById("${inputName}"+ LOOP_NUM).value=document.getElementById("NAME"+LOOP_NUM).value;
	  		}
            alert(value + "${action.getText(' 采购人不存在')}");
	  		
	  	}
	  }

	  function buyerCode_SetPickedResult(msg){	  	 	  
        if (msg.length == 0) {
                var value = document.getElementById("${inputName}" + LOOP_NUM).value;
                document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "false";
                alert(value + "${action.getText('采购人不存在')}");
            } else {         	  
                document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "true";           
                var result = jsrsArrayFromString(msg);
            }
      }
      
      function selectBuyer(loopNum) {
        LOOP_NUM = loopNum;
        location_OpenDialog();
      }
      
      function location_OpenDialog(){
      	var url;      
        url = "${req.contextPath}/popup/userSelector.html";
        popupModalDialog(url, 800, 600, buyerSelectorHandler);
     
      }
      
      function buyerSelectorHandler(result) {
        if (null != result) {
          var objLocationCode = document.getElementById("${inputName}" + LOOP_NUM);		  
          objLocationCode.value = result[2];
          document.getElementById("${inputId}" + LOOP_NUM).value=result[0];          
          document.getElementById("${inputN}" + LOOP_NUM).innerHTML=result[1];  
          //document.getElementById("afterMoveWarehouse"+ LOOP_NUM).value=result[3];
          //document.getElementById("afterMoveRegional"+ LOOP_NUM).value=result[5];
          document.getElementById("hidden." + "${inputName}" + LOOP_NUM).value = "true";
        }
      }
	</script> 
</#macro>


