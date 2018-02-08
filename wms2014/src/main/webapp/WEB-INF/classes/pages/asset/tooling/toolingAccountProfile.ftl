<#include "../../includes/eam2008.ftl" />
<#include "../commonDepartmentProduction.ftl" />

<@htmlPage title="${action.getText('toolingAccountEdit.title')}">
<@ww.form namespace="'/asset/tooling'" name="'tooling'" action="'saveToolingAccount'" method="'post'" validate="true">
    <@ww.token name="saveToolingAccountToken"/>
    <#if tooling.id?exists>
      <@ww.hidden name="'tooling.id'" value="#{tooling.id}"/>
    </#if>
    <#if acceptBill?exists>
     <@ww.hidden name="'acceptBill.id'" value="#{acceptBill.id}"/>
     </#if>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
    <@inputTable>
        <tr>
        	<@ww.textfield label="'${action.getText('toolingNo')}'" name="'tooling.deviceNo'" value="'${tooling.deviceNo?if_exists}'"  required="true" cssClass="'underline'" />
        	<@ww.textfield label="'${action.getText('toolingDrawNo')}'" name="'tooling.graphNo'" value="'${tooling.graphNo?if_exists}'"  required="false" cssClass="'underline'" />
        	<@ww.textfield label="'${action.getText('toolingName')}'" name="'tooling.name'" value="'${tooling.name?if_exists}'" required="true" cssClass="'underline'"/>
        </tr>
        <tr>
        	<@ww.select label="'${action.getText('toolingCategory')}'" required="false" name="'toolingType.id'" 
	    			    value="'${req.getParameter('toolingType.id')?if_exists}'" listKey="id" listValue="value"
	                    list="toolingTypes" emptyOption="true" disabled="false" onchange="'toolTypeValueChange()'">
	        </@ww.select>
	        <@ww.select label="'${action.getText('category')}'" required="false" name="'toolingTypeDetail.id'" 
	    			value="'${req.getParameter('toolingTypeDetail.id')?if_exists}'" listKey="id" listValue="name"
	                list="toolingTypeDetails" emptyOption="true" disabled="false">
	        </@ww.select>
	        <@pp.datePicker label="'${action.getText('usedDate')}'" name="'tooling.usedStartedTime'"
	        	            value="'${(tooling.usedStartedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="false"/>
	    </tr>
	    <tr>       
	        <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		    			 onclick="'departmentValueChange(\"department.id\",\"productionLine.id\")'"
		                list="departments" emptyOption="true" disabled="false">
		    </@ww.select>
		    
		    <@ww.select label="'${action.getText('device.line')}'" 
      						required="false" 
      						name="'productionLine.id'" 
        					listKey="id" 
        					listValue="name"
        					list="productionLines" 
        					emptyOption="false" 
        					disabled="false">
					        <#if tooling.productionLine?exists>
					          <@ww.param name="'value'"  value="'${tooling.productionLine.id?if_exists}'" />
					        <#else>
                              <@ww.param name="'value'"  value="" />
					        </#if>
					      </@ww.select>
		    <@ww.select label="'${action.getText('tooling.calcUnit')}'" required="false" name="'calUnit.id'" 
	    			    value="'${req.getParameter('calUnit.id')?if_exists}'" listKey="id" listValue="value"
	                    list="calcUnits" emptyOption="true" disabled="false">
	        </@ww.select>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('model')}'" name="'tooling.model'" value="'${tooling.model?if_exists}'" required="false" cssClass="'underline'" />
        	<@ww.textfield label="'${action.getText('specification')}'" name="'tooling.specification'" value="'${tooling.specification?if_exists}'" required="false" cssClass="'underline'"/>
            <@ww.textfield label="'${action.getText('demarcateCycle')}'" name="'tooling.demarcateCycle'" value="'${tooling.demarcateCycle?if_exists}'" required="false" cssClass="'underline'" /> 	
        </tr>
        <tr>
            <@ww.select label="'${action.getText('managementLevel')}'" 
                        required="false" name="'tooling.managementLevel'" 
                        listKey="value" listValue="label"
                        list="managementLevels" 
                        emptyOption="true" 
                        disabled="false">
            </@ww.select>
             <#assign manager = ''/>
			  <#if tooling.manager?exists>
			    <#assign manager = "${tooling.manager.name}" />
			  </#if>
			  <td align="right" valign="top"><label class="label">${action.getText('tooling.manager.name')}:</label></td>
			  <td>
			    <input type="text" name="manager.name" class="underline"  value="${manager}"  maxlength="150" disabled="true"/>
				<a onClick='user_OpenDialog()'>
			 	  <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			    </a>
			  </td>
			    <#assign managerId = ''/>
			    <#if tooling.toolingManager?exists>
			      <#assign managerId = "${tooling.toolingManager.id}" />
			    </#if>
			    <@ww.hidden name="'manager.id'" value="'${managerId}'"/>
			    <#--
			 <#assign productName = ''/>
			<#if tooling.product?exists>
			 <#assign productName = "${tooling.product.name}" />
			</#if>
       		<td align="right" valign="top"><label class="label">${action.getText('productName')}:</label></td>
	 		<td>
	 			<input type="text" name="product.name" class="underline"  value="${productName}"  maxlength="150" disabled="true"/>
	 			<a onClick='product_OpenDialog();'>
 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
 				</a>
 			</td>
 			<#assign productId = ''/>
            <#if tooling.product?exists>
               <#assign productId = "${tooling.product.id}" />
            </#if>
            <@ww.hidden name="'product.id'" value="'${productId}'"/>	
            -->
            <@ww.select label="'${action.getText('productName')}'" required="false" name="'product.id'" 
	    			    value="'${req.getParameter('product.id')?if_exists}'" listKey="id" listValue="name"
	                    list="productModels" emptyOption="true" disabled="false">
	        </@ww.select>
        </tr>
        <tr> 
        	<@ww.textfield label="'${action.getText('qualityEnsure')}'" name="'tooling.qualityEnsure'" value="'${tooling.qualityEnsure?if_exists}'" required="false" cssClass="'underline'" /> 	
        	<@ww.textfield label="'${action.getText('processNo')}'" name="'tooling.processNo'" required="false" value="'${tooling.processNo?if_exists}'" cssClass="'underline'"/>
       		<@ww.textfield label="'${action.getText('usedQuota')}'" name="'tooling.usedQuota'" required="false" value="'${tooling.usedQuota?if_exists}'" cssClass="'underline'"/>
        	
        </tr>
	        <tr>
	        	<@ww.textfield label="'${action.getText('usingDevice')}'" name="'tooling.usingDevice'" required="false" value="'${tooling.usingDevice?if_exists}'" cssClass="'underline'"/>
	        	<@ww.textfield label="'${action.getText('number')}'" name="'tooling.number'" value="'${tooling.number?if_exists}'" required="false" cssClass="'underline'" />
	        	<@ww.textfield readonly="true" label="'${action.getText('outputTotal')}'" name="'tooling.totalOutput'" value="'${tooling.totalOutput?if_exists}'"  cssClass="'underline'" disabled="true"/> 	        	
	        </tr>
	        
	        <tr>
	        	<@ww.textfield label="'${action.getText('trusteeshipSupplierName')}'" name="'tooling.trusteeshipSupplierName'" value="'${tooling.trusteeshipSupplierName?if_exists}'" cssClass="'underline'" disabled="true"/>
	        	<#if tooling.id?exists>
	        	 <#assign toolingStatus = ''/>
				 <#if tooling.toolingStatus?exists>
				   <#assign toolingStatus = "${tooling.toolingStatus.value}" />
				 </#if>	     		
	     		 <@ww.textfield readonly="false" label="'${action.getText('toolingState')}'" name="'tooling.toolingStatus.value'" value="'${toolingStatus}'"  cssClass="'underline'" disabled="true"/> 					
	       		 <#assign toolingStatusId = ''/>
				 <#if tooling.toolingStatus?exists>
				   <#assign toolingStatusId = "${tooling.toolingStatus.id}" />
				 </#if>	
				 <input type="hidden" name="toolingStatus.id" value="${toolingStatusId}" />
				 </#if>
	        	<@pp.datePicker label="'${action.getText('tooling.cardCreatedTime')}'" name="'tooling.cardCreatedTime'"
	        		value="'${(tooling.cardCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="10" required="true"/>
	        </tr>
        
  		</@inputTable>
	<@buttonBar>
	  <#if !(action.isReadOnly())>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
      </#if>
      <#--
      <#if tooling.enabled?exists>
        <#if !(tooling.enabled)>
          <@vsubmit name="'enabled'" value="'${action.getText('enabled')}'"/>
        </#if>
      </#if>
      -->
      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/asset/tooling/listToolingAccounts.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
      <input type="button" name="backHistory" value="${action.getText('back.history.page')}" class="button" onclick="javascript:history.go(-1);">
      <#if tooling.id?exists>
          <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_toolingAccountPdf('#{tooling.id}')"/>
          <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_toolingAccountXls('#{tooling.id}')"/>
     </#if>
    </@buttonBar>
</@ww.form>
	<script language="JavaScript" type="text/JavaScript"> 
	  /*
	   *当该工装失效时，该页面所有控件，除有效、返回按钮，其他都失效
	  */
	　<#if tooling.enabled?exists>
	 　　<#if !(tooling.enabled)>
	      __disableAllElements__(document.forms[0], new Array("enabled", "back"));
	      disableCalendars(document.forms[0],new Array("tooling.usedStartedTime"),true);
	    </#if>
	 </#if>
	  var detailTypeNumberForString = document.getElementById("toolingTypeDetail.id").length;
	  var detailTypeNumberForInteger = new Number(detailTypeNumberForString);    
	  var detailTypeForSameToolType =new Array(detailTypeNumberForInteger.valueOf());    //用来放生产线值的数组
	  var detailTypeForSortToolType = new Array();      //lineForSortDept变量放lineForSameDept数组的元素
	  for(var i=0; i<detailTypeForSameToolType.length; i++) {
		    detailTypeForSameToolType[i] = new Array();   
	  }
	  function product_OpenDialog() {
	  	var url = "${req.contextPath}/popup/productSelector.html";
		popupModalDialog(url,800,600,productSelectorHandler);
	  }
	  function productSelectorHandler(result) {
	    document.forms["tooling"].elements["product.id"].value = result[0];
		document.forms["tooling"].elements["product.name"].value = result[1];
	  }
  
	  function toSortDetailTypeByToolType() {      //把同一个部门的生产线放在一个数组里
	    var arrayCount = 0;      //对lineForSameDept数组计数
	    <#if (toolingTypes.size()>0)>
	    <#list toolingTypes as toolType>
	      var count = 0;          
	  	  var flag = 0;
	  	<#if (toolingTypeDetails.size()>0)>
	  	<#list toolingTypeDetails as detailType>
	  	  <#if detailType.toolingType?exists>
	  	    if ('#{toolType.id}' == '#{detailType.toolingType.id}' && arrayCount<detailTypeForSameToolType.length) {
	  		  if (count == 0) {	  								
	  		    detailTypeForSameToolType[arrayCount].push(['#{toolType.id}', '']);
	  			detailTypeForSameToolType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			count++;
	  			flag = 1;
	  		  } else {
	  			  detailTypeForSameToolType[arrayCount].push(['#{detailType.id}', '${detailType.name}']);
	  			  flag = 1;
	  			}
	  		} 
	  	  </#if>
	  	</#list>
	  	</#if>
	  	if (flag == 1) {
	  	  detailTypeForSortToolType.push(detailTypeForSameToolType);
	  	  arrayCount++;
	  	}
	 </#list>
	 </#if>
	}
	
	function toolTypeValueChange() {      //处理部门选择框的事件
	  var selectedToolTypeId = document.getElementById("toolingType.id").value;
	  for (var i = 0; i < detailTypeForSortToolType.length; i ++) {
	    var toolType = detailTypeForSortToolType[i];
	    deleteAllDetailTypes();
	    for (var j = 0; j < toolType.length; j ++) {
	  	  var detailType = toolType[j];
	  	  for (var k=0; k<toolType.length; k++) {
		    if (selectedToolTypeId == detailType[0][0] ) {
		  	  createDetailTypeSelect(detailType);
		  	  return ;
		  	} 
	  	  }
	  	}
	  }
	}
	
	function createDetailTypeSelect(ary) {     //给生产线的选择框赋值
	  var obj = document.getElementById("toolingTypeDetail.id");
	  for (var j=1; j < ary.length; j ++) {
	    var opt = new Option(ary[j][1], ary[j][0]);
		eval("obj.options[obj.options.length]=opt");
	  }
	}
	
   function deleteAllDetailTypes() {     //删除生产线的选择框的值
      document.getElementById("toolingTypeDetail.id").value='';
	  var obj = document.getElementById("toolingTypeDetail.id");
	  for (var i=obj.options.length; i>0; i--) {
    	obj.options[i] = null;
  		}
	}
	
	function user_OpenDialog() {
	  var url = "${req.contextPath}/popup/userSelector.html";
	  popupModalDialog(url, 800, 600, userSelectorHandler);
	}

	function userSelectorHandler(result) {
	  if (null != result) {
	    document.forms[0].elements["manager.id"].value = result[0];
	    document.forms[0].elements["manager.name"].value = result[1];
	  } 
	}
	
	function validate(){
		if(isNotEmpty(tooling,"tooling.deviceNo")) {
			if (!isValidLength(document.forms[0],"tooling.deviceNo",0,50)){
				alert("${action.getText('tooling.deviceNo.maxLength')}");
				return false;
		    }
		} else {
			alert("${action.getText('tooling.deviceNo.requiredstring')}");
			return false;
		}
		
		if(isNotEmpty(tooling,"tooling.graphNo")) {
			if (!isValidLength(document.forms[0],"tooling.graphNo",0,50)){
				alert("${action.getText('tooling.graphNo.maxLength')}");
				return false;
		    }
		}
		<#-- 
		else {
			alert("${action.getText('tooling.graphNo.requiredstring')}");
			return false;
		}
		-->
		if(isNotEmpty(tooling,"tooling.name")) {
			if (!isValidLength(document.forms[0],"tooling.name",0,50)){
				alert("${action.getText('tooling.name.maxLength')}");
				return false;
		    }
		} else {
			alert("${action.getText('tooling.name.requiredstring')}");
			return false;
		}
		
		if(isNotEmpty(tooling,"tooling.usedStartedTime")) {
			if(!isDate("tooling.usedStartedTime")){
				alert('${action.getText('usedDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		
		if (isNotEmpty(document.forms[0], "tooling.model")) {
	　　　　if (!isValidLength(document.forms[0], new Array("tooling.model"), 0, 50)) {
		  　　alert("${action.getText("tooling.model.length.overflow")}");
		     return false;
		   }
		}
	　　if (isNotEmpty(document.forms[0], "tooling.specification")) {
	　　　　if (!isValidLength(document.forms[0], new Array("tooling.specification"), 0, 50)) {
		  　　alert("${action.getText("tooling.specification.length.overflow")}");
		     return false;
		   }
		}
		
		if(isNotEmpty(tooling,"tooling.demarcateCycle")) {
		      if (!isNumber("tooling.demarcateCycle")) {
				alert("${action.getText('tooling.demarcateCycle.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["tooling.demarcateCycle"].value, 10001, 0)){	//最大范围要加1，例：价格最大为1000000，在此要设为1000001。以保证取得最大值。
				alert("${action.getText('tooling.demarcateCycle.error')}");
				return false;
		      }
		}
		
		if (-1 == document.forms["tooling"].elements["productionLine.id"].value) {
	    	document.forms["tooling"].elements["productionLine.id"].value='';
	  	}
		
		if(isNotEmpty(tooling,"tooling.qualityEnsure")) {
		      if (!isNumber("tooling.qualityEnsure")) {
				alert("${action.getText('tooling.qualityEnsure.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["tooling.qualityEnsure"].value, 10001, 0)){	//最大范围要加1，例：价格最大为1000000，在此要设为1000001。以保证取得最大值。
				alert("${action.getText('tooling.qualityEnsure.error')}");
				return false;
		      }
		}
		
		if (isNotEmpty(document.forms[0], "tooling.processNo")) {
	　　　　if (!isValidLength(document.forms[0], new Array("tooling.processNo"), 0, 50)) {
		  　　alert("${action.getText("tooling.processNo.maxlength")}");
		     return false;
		   }
		}
		
		if(isNotEmpty(tooling,"tooling.usedQuota")) {
			if (!isDoubleNumber("tooling.usedQuota")){
				alert("${action.getText('tooling.usedQuota.isNotNumber')}");
				return false;
			} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["tooling.usedQuota"].value, 10000000001, 0)){  //验证范围
				alert("${action.getText('tooling.usedQuota.maxLength')}");
				return false;
			}
		}
		
		if (isNotEmpty(document.forms[0], "tooling.usingDevice")) {
	　　　　if (!isValidLength(document.forms[0], new Array("tooling.usingDevice"), 0, 50)) {
		  　　alert("${action.getText("tooling.usingDevice.maxlength")}");
		     return false;
		   }
		}
		
		if(isNotEmpty(tooling,"tooling.number")) {
		      if (!isNumber("tooling.number")) {
				alert("${action.getText('tooling.number.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["tooling.number"].value, 100000001, 0)){	
				alert("${action.getText('tooling.number.error')}");
				return false;
		      }
		}
	  /*
       *验证建卡时间是否为空，格式是否正确，是否在当前日期之后
      */
	  if ('' == document.forms["tooling"].elements["tooling.cardCreatedTime"].value) {
	    alert("${action.getText('tooling.cardCreatedTime.required')}");
	    return false;
	  } else {
	    if (!isDate("tooling.cardCreatedTime")) {
	      alert("${action.getText('tooling.cardCreatedTime')}" + "," + "${action.getText('dateFormate.error')}");
	      return false;
	    }
	    if (isDateLessThenCurrent(document.forms["tooling"].elements["tooling.cardCreatedTime"].value)) {
	  	  alert("${action.getText('tooling.cardCreatedTime.late.error')}");
	  	  return false;
	    }
	  }	
		return true;
	}
	</script>
	<#if tooling.id?exists>
	<script language="JavaScript" type="text/JavaScript"> 
		var defNavItem=4;
		var navItem = new Array();
		navItem[1] = '<a  id="toolingExtInfo" onclick="activeTab(this);getCurrentNavItem(1)" href="${req.contextPath}/asset/tooling/editToolingAccountExtInfo.html?tooling.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame">${action.getText('tooling.ExtInfo')}</a>';
		navItem[2] = '<a  id="toolingFinanceInfo" onclick="activeTab(this);getCurrentNavItem(2)" href="${req.contextPath}/asset/device/editToolingFinanceInfo.html?device.id=#{tooling.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('tooling.FinanceInfo')}</a>';
		navItem[3] = '<a  id="toolingSpare" onclick="activeTab(this);getCurrentNavItem(3)" href="${req.contextPath}/asset/device/listToolingDevSpares.html?toolingDev.id=#{tooling.id}&toolingDevFlag=TOOLING&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('tooling.spare')}</a>';
		navItem[4] = '<a  id="preRepairRules" onclick="activeTab(this);getCurrentNavItem(4)"  href="${req.contextPath}/repair/preRepair/rule/listPreRepairRules.html?device.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame">${action.getText('tooling.preRepairRule')}</a>';
		<#--navItem[5] = '<a  id="toolingCheckPoint" onclick="activeTab(this);" href="${req.contextPath}/preview/tool/checkpoint/listCheckPointRuleItems.html" target="frame" >点检标准</a>';-->
		navItem[5] = '<a  id="toolingCheckPoint" onclick="activeTab(this);getCurrentNavItem(5)" href="${req.contextPath}/runmaintenance/toolingDev/checkpoint/listCheckPointRule.html?toolingDev.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('tooling.checkPointRule')}</a>';
		navItem[6] = '<a  id="deviceDoc"  onclick="activeTab(this);getCurrentNavItem(6)" href="${req.contextPath}/asset/device/listDeviceDocs.html?device.id=#{tooling.id}&disabled=${req.getParameter('view')?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('device.doc')}</a>';
		navItem[7] = '<a  id="borrowHistory" onclick="activeTab(this);getCurrentNavItem(7)" href="${req.contextPath}/runmaintenance/tooling/record/listToolingBorrowHistory.html?tooling.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('borrowHistory')}</a>';
		navItem[8] = '<a  id="toolingFaultBill"  onclick="activeTab(this);getCurrentNavItem(8)" href="${req.contextPath}/asset/tooling/listToolingFaultBill.html?tooling.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('tooling.FaultBill')}</a>';
		navItem[9] = '<a  id="toolingChangeBill" onclick="activeTab(this);getCurrentNavItem(9)" href="${req.contextPath}/asset/tooling/listToolingChangeBill.html?tooling.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}" target="frame" >${action.getText('tooling.changeBillRecord')}</a>';
		
		var navItemId = new Array();
		navItemId[1] = "toolingExtInfo";
		navItemId[2] = "toolingFinanceInfo";
		navItemId[3] = "toolingSpare";
		navItemId[4] = "preRepairRules";
		navItemId[5] = "toolingCheckPoint";
		navItemId[6] = "deviceDoc";
		navItemId[7] = "borrowHistory";
		navItemId[8] = "toolingFaultBill";
		navItemId[9] = "toolingChangeBill";
		
		function getCurrentNavItem(item) {
		  curPos = item;
		}
		function initNav(num) {
			for (var i = 1; i <= num; i ++) {
				document.getElementById(i).innerHTML = navItem[i];
			}
		}
		
		var curPos=1;
		function scrollNav(pos) {
			var c = 1;	
			 for(var id = pos;id<=13;id++, c++) {
			   if(c<=4){
				  if (c==1){
				  	if (pos>=5){
				  	  temp = id-3;
					  document.getElementById(c).innerHTML = navItem[id-3];
					}else {
					  document.getElementById(c).innerHTML = navItem[c];	
					}
				  }else{
				  	if (pos>=5){
				  	  temp = temp+1;
					  document.getElementById(c).innerHTML = navItem[temp];
					}else {
					  document.getElementById(c).innerHTML = navItem[c];	
					}
				  }
			  }
			  <#--
			  if(c<=4){
			  document.getElementById(c).innerHTML = navItem[id];
			  <#if tooling.id?exists>
			  	if (c == 1) {
		     		document.all.frame.src = document.getElementById(navItemId[id]).href;
			 		document.getElementById(navItemId[id]).className = "selectedtab";
			 	}
		   	  </#if>
			  }
			  -->
			 }  
			 <#if tooling.id?exists>
		       document.all.frame.src = document.getElementById(navItemId[pos]).href;
			   document.getElementById(navItemId[pos]).className = "selectedtab";
		   	  </#if>
			 if (pos<=1){
			 pos=1; 
			 try{document.getElementById("navl").style.visibility="hidden"}catch(e){};
			 try{document.getElementById("navr").style.visibility="visible"}catch(e){};
			 }else if(pos>=9){
		//	 pos=9;
			 try{document.getElementById("navl").style.visibility="visible"}catch(e){};
			 try{document.getElementById("navr").style.visibility="hidden"}catch(e){};
			 }else{
			 try{document.getElementById("navl").style.visibility="visible"}catch(e){};
			 try{document.getElementById("navr").style.visibility="visible"}catch(e){};
			 }
		 
		 	curPos = pos;
			}
			
		</script>
	  </#if>
	<script language="javascript" type="text/JavaScript">
	 	window.onload = function () {
	 	
	 	
	   <#if tooling.id?exists>
	     initNav(4);
	     document.all.frame.src = '${req.contextPath}/asset/tooling/editToolingAccountExtInfo.html?tooling.id=#{tooling.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}';
		 document.getElementById("toolingExtInfo").className = "selectedtab";
		 try{document.getElementById("navl").style.visibility="hidden"}catch(e){};
	   <#else>
		   a = new Date();
		   var time=a.format("yyyy-MM-dd");   
		   document.forms[0].elements["tooling.cardCreatedTime"].value=time;
	   </#if>
	   <#if tooling.toolingType?exists>
	     document.forms["tooling"].elements["toolingType.id"].value=#{tooling.toolingType.id?if_exists};
	   </#if>
	   <#if tooling.department?exists>
	       document.forms["tooling"].elements["department.id"].value=#{tooling.department.id?if_exists};
	   <#elseif loginUser.department?exists>
		   document.getElementById("department.id").value = #{loginUser.department.id};
		 </#if>
	   <#if tooling.calUnit?exists>
	     document.forms["tooling"].elements["calUnit.id"].value=#{tooling.calUnit.id?if_exists};
	   </#if>
	   <#if tooling.product?exists>
	     document.forms["tooling"].elements["product.id"].value=#{tooling.product.id?if_exists};
	   </#if>
	   <#if tooling.id?exists>
	     var usedQuota = '${tooling.usedQuota?if_exists}';
	     if (usedQuota == 0 || usedQuota == '') {
	       document.forms["tooling"].elements["tooling.usedQuota"].value = '';
	     } else {
	       document.forms["tooling"].elements["tooling.usedQuota"].value = usedQuota;
	     }
	   </#if>
	   toSortProductionLineByDepartment();
	   if (-1 == document.forms["tooling"].elements["department.id"].value) {
	   document.forms["tooling"].elements["productionLine.id"].length=1;
	 } else {
	     departmentValueChange("department.id", "productionLine.id");
	     <#if tooling.productionLine?exists>
	       document.forms["tooling"].elements["productionLine.id"].value = #{tooling.productionLine.id?if_exists};
	     </#if>
	 }
	   toSortDetailTypeByToolType();	
	   if ('' == document.getElementById("toolingType.id").value) {
	     document.getElementById("toolingTypeDetail.id").length=1;
	   } else {
	     toolTypeValueChange();
	     <#if tooling.toolingTypeDetail?exists>
	       document.forms["tooling"].elements["toolingTypeDetail.id"].value=#{tooling.toolingTypeDetail.id?if_exists};
	     </#if>
	   }
	  }
	  
	  function open_toolingAccountXls(id) {
			var toolingId=id;
			var url='${req.contextPath}/reports/tooling/toolingAccount.xls?toolingId='+toolingId
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
      	}
        function open_toolingAccountPdf(id) {
			var toolingId=id;
			var url='${req.contextPath}/reports/tooling/toolingAccount.pdf?toolingId='+toolingId
      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
      	}
	</script>

<#if tooling.id?exists>
  <ul id="beautytab">
  	<li id="navl"><a href="javascript:scrollNav(curPos-1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_left.gif" alt="${action.getText('lastPage')}" border="0" /></a></li>
    <li id="1"/>
    <li id="2"/>
    <li id="3"/>
    <li id="4"/>
    <li id="navr"><a href="javascript:scrollNav(curPos+1);" target="_self"><img src="${req.contextPath}/stylesheets/images/return_right.gif" alt="${action.getText('nextPage')}" border="0" /></a></li>
  </ul>
  <iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
</#if>

</@htmlPage>