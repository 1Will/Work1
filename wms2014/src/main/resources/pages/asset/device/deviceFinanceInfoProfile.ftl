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

<@framePage title="${action.getText('deviceFinanceInfo.title')}">
	<@ww.form name="'newDeviceFinanceInfo'" action="'saveDeviceFinanceInfo'" method="'post'" validate="true">
	    <@ww.token name="saveDeviceFinanceInfoToken"/>
	    <@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	     <#if deviceFinanceInfo.id?exists>
	    	<@ww.hidden name="'deviceFinanceInfo.id'" value="'#{deviceFinanceInfo.id?if_exists}'"/>
	    </#if>
	    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
	    <@inputTable>
	        <tr>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.unitPrice')}'" name="'deviceFinanceInfo.unitPrice'" value="'${deviceFinanceInfo.unitPrice?if_exists}'"  cssClass="'underline'"/>
	            <@ww.select label="'${action.getText('deviceFinanceInfo.foreignCurrencyName')}'" required="false" name="'foreignCurrencyName.id'" 
	    			    value="'${req.getParameter('foreignCurrencyName.id')?if_exists}'" listKey="id" listValue="value"
	                    list="foreignCurrencyName" emptyOption="true" disabled="false">
	        	</@ww.select>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.foreignCurrencyPrice')}'" name="'deviceFinanceInfo.foreignCurrencyPrice'" value="'${deviceFinanceInfo.foreignCurrencyPrice?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>     
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.miscExpenses')}'" name="'deviceFinanceInfo.miscExpenses'" value="'${deviceFinanceInfo.miscExpenses?if_exists}'"  cssClass="'underline'"/>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.origPrice')}'" name="'deviceFinanceInfo.origPrice'" value="'${deviceFinanceInfo.origPrice?if_exists}'"  cssClass="'underline'"/> 
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.netValue')}'" name="'deviceFinanceInfo.netValue'" value="'${deviceFinanceInfo.netValue?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>
	         <#if device.toolingDevFlag=="TOOLING">
	         <#else>
	            <@pp.datePicker label="'${action.getText('deviceFinanceInfo.usedDate')}'" name="'deviceFinanceInfo.usedDate'"
	     							value="'${(deviceFinanceInfo.usedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	         </#if>
	     		<@pp.datePicker label="'${action.getText('deviceFinanceInfo.initDeprecitionDate')}'" name="'deviceFinanceInfo.initDeprecitionDate'"
	     							value="'${(deviceFinanceInfo.initDeprecitionDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.initDeprecitionSum')}'" name="'deviceFinanceInfo.initDeprecitionSum'" value="'${deviceFinanceInfo.initDeprecitionSum?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.yearLimit')}'" name="'deviceFinanceInfo.yearLimit'" value="'${deviceFinanceInfo.yearLimit?if_exists}'"  cssClass="'underline'"/>
	            <td align="right" valign="top"><label class="label">${action.getText('deviceFinanceInfo.financeType')}:</label></td>
		 		<td>
		 			<#assign financeTypeName = ''/>
		 			<#if deviceFinanceInfo.financeType?exists>
		 				<#assign financeTypeName = "${deviceFinanceInfo.financeType.name?if_exists}"/>
		 			</#if>
		 			<input type="text" name="financeType.name" class="underline"  value="${financeTypeName}"  maxlength="150" disabled/>
		 			<a onClick='financeType_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<#assign financeTypeId = ''/>
		 			<#if deviceFinanceInfo.financeType?exists>
		 				<#assign deviceTypeId = "${deviceFinanceInfo.financeType.id?if_exists}"/>
		 		</#if>
		 		<@ww.hidden name="'financeType.id'" value="'${financeTypeId}'"/>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.netSalvageScale')}'" name="'deviceFinanceInfo.netSalvageScale'" value="'${deviceFinanceInfo.netSalvageScale?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>
                <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceFinanceInfo.yearDeprecitionScale')}'" name="'deviceFinanceInfo.yearDeprecitionScale'" value="'${deviceFinanceInfo.yearDeprecitionScale?if_exists}'"  cssClass="'underline'"/> 
	            <@ww.textfield readonly="true" label="'${action.getText('deviceFinanceInfo.curDeprecitionDate')}'" name="'deviceFinanceInfo.curDeprecitionDate'" value="'${deviceFinanceInfo.curDeprecitionDate?if_exists}'"  cssClass="'underline'"/>
	            <@ww.textfield readonly="true" label="'${action.getText('deviceFinanceInfo.curDeprecitionSum')}'" name="'deviceFinanceInfo.curDeprecitionSum'" value="'${deviceFinanceInfo.curDeprecitionSum?if_exists}'"  cssClass="'underline'"/> 
	        	<td align="left">
	        	<input type="image" src="${req.contextPath}/images/icon/look.jpg" align="left" title="${action.getText('deviceFinanceInfo.searchDeviceDepreciationDetail')}" width="15" height="15" name="look" onclick="return lookDetail_onclick();"/>
	        	</td>
	        </tr>
	    </@inputTable>
	    <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
	      <@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
		  </@buttonBar>
		</#if>
		<script language="javascript">
			window.onload = function () {
				<#if deviceFinanceInfo.foreignCurrencyName?exists>
			     	document.forms["newDeviceFinanceInfo"].elements["foreignCurrencyName.id"].value=#{deviceFinanceInfo.foreignCurrencyName.id?if_exists};
			   </#if>
		   }
			<@eam2008_LockPageIfNeed/>
			/*
			 *瑜版捁顕氱拋鎯ь樃婢惰鲸鏅ラ弮锟�,闁夸礁鐣炬い鐢告桨閹碉拷閺堝甯舵禒锟�
			*/
			<#if device.enabled?exists>
	          <#if !(device.enabled)>
	             __disableAllElements__(document.forms[0], new Array(""));
	             disableCalendars(document.forms[0],new Array("deviceFinanceInfo.usedDate","deviceFinanceInfo.usedDate","deviceFinanceInfo.initDeprecitionDate"),true);
	          </#if>
	        </#if>
			function financeType_OpenDialog() {
	  			var url = "${req.contextPath}/popup/financeTypeSelector.html";
	  			popupModalDialog(url, 800, 600, deviceTypeSelectorHandler);
	  		}
	  		function deviceTypeSelectorHandler(result) {
	  			document.forms["newDeviceFinanceInfo"].elements["financeType.id"].value = result[0];
	  			document.forms["newDeviceFinanceInfo"].elements["financeType.name"].value = result[1];
	  			document.forms["newDeviceFinanceInfo"].elements["deviceFinanceInfo.yearDeprecitionScale"].value = result[2];
	  			document.forms["newDeviceFinanceInfo"].elements["deviceFinanceInfo.netSalvageScale"].value = result[3];
	  		}
	  		function customize_validate() {
	  			if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.unitPrice")) {
				      if (!isDoubleNumber("deviceFinanceInfo.unitPrice")) {
						alert("${action.getText('deviceFinanceInfo.unitPrice.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.unitPrice"].value, 1000000001, 0)){
						alert("${action.getText('deviceFinanceInfo.unitPrice.maxLength')}");
						return false;
				      }
				}
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.foreignCurrencyPrice")) {
				      if (!isDoubleNumber("deviceFinanceInfo.foreignCurrencyPrice")) {
						alert("${action.getText('deviceFinanceInfo.foreignCurrencyPrice.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.foreignCurrencyPrice"].value, 1000000001, 0)){
						alert("${action.getText('deviceFinanceInfo.foreignCurrencyPrice.maxLength')}");
						return false;
				      }
				}
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.miscExpenses")) {
				      if (!isDoubleNumber("deviceFinanceInfo.miscExpenses")) {
						alert("${action.getText('deviceFinanceInfo.miscExpenses.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.miscExpenses"].value, 1000000001, 0)){
						alert("${action.getText('deviceFinanceInfo.miscExpenses.maxLength')}");
						return false;
				      }
				}
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.origPrice")) {
				      if (!isDoubleNumber("deviceFinanceInfo.origPrice")) {
						alert("${action.getText('deviceFinanceInfo.origPrice.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.origPrice"].value, 1000000001, 0)){
						alert("${action.getText('deviceFinanceInfo.origPrice.maxLength')}");
						return false;
				      }
				}
				
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.netValue")) {
				      if (!isDoubleNumber("deviceFinanceInfo.netValue")) {
						alert("${action.getText('deviceFinanceInfo.netValue.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.netValue"].value, 1000000001, 0)){
						alert("${action.getText('deviceFinanceInfo.netValue.maxLength')}");
						return false;
				      }
				}
				<#if device.toolingDevFlag=="DEVICE">
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.usedDate")) {
					if(!isDate("deviceFinanceInfo.usedDate")){
						alert('${action.getText('deviceFinanceInfo.usedDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}  	
				}
				</#if>
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.initDeprecitionDate")) {
					if(!isDate("deviceFinanceInfo.initDeprecitionDate")){
						alert('${action.getText('deviceFinanceInfo.initDeprecitionDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}  	
				}
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.initDeprecitionSum")) {
					if (!isDoubleNumber("deviceFinanceInfo.initDeprecitionSum")){
						alert("${action.getText('deviceFinanceInfo.initDeprecitionSum.isNotNumber')}");
						return false;
					} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.initDeprecitionSum"].value, 1.001, 0.000)){  //妤犲矁鐦夐懠鍐ㄦ纯
						alert("${action.getText('deviceFinanceInfo.initDeprecitionSum.maxLength')}");
						return false;
					}
				}
				
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.yearLimit")) {
				      if (!isNumber("deviceFinanceInfo.yearLimit")) {
						alert("${action.getText('deviceFinanceInfo.yearLimit.isNotNumber')}");
						return false;
				      } else if (!isNumberBetweenBoolen(document.forms[0].elements["deviceFinanceInfo.yearLimit"].value, 1000001, 0)){
						alert("${action.getText('deviceFinanceInfo.yearLimit.maxLength')}");
						return false;
				      }
				}
				
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.netSalvageScale")) {
					if (!isDoubleNumber("deviceFinanceInfo.netSalvageScale")){
						alert("${action.getText('deviceFinanceInfo.netSalvageScale.isNotNumber')}");
						return false;
					} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.netSalvageScale"].value, 1.001, 0.000)){  //妤犲矁鐦夐懠鍐ㄦ纯
						alert("${action.getText('deviceFinanceInfo.netSalvageScale.maxLength')}");
						return false;
					}
				}
				
				if(isNotEmpty(newDeviceFinanceInfo,"deviceFinanceInfo.yearDeprecitionScale")) {
					if (!isDoubleNumber("deviceFinanceInfo.yearDeprecitionScale")){
						alert("${action.getText('deviceFinanceInfo.yearDeprecitionScale.isNotNumber')}");
						return false;
					} else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["deviceFinanceInfo.yearDeprecitionScale"].value, 1.001, 0.000)){  //妤犲矁鐦夐懠鍐ㄦ纯
						alert("${action.getText('deviceFinanceInfo.yearDeprecitionScale.maxLength')}");
						return false;
					}
				}

	  			return true;
	  		}
	  	  
	  	  	/**
	  	  	 * 瀵懓鍤Ο鈥崇础缁愭褰涢敍灞剧叀鐠囥垼顔曟径鍥ㄥ閺冄勬缂侊拷
	  	  	 */
	  		function lookDetail_onclick(){
	  			var url = "${req.contextPath}/deviceDepreciationDetailSelector/searchDeviceDepreciationDetail.html?deviceCard.id="+#{device.id};
	   			popupModalDialog(url, 800, 600);
	   			return false;
	  		}
		</script>
		

	</@ww.form>
</@framePage>