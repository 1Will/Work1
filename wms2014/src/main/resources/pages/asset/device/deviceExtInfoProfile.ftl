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

<@framePage title="${action.getText('deviceExtInfo.title')}">
	<@ww.form name="'deviceExtInfo'" action="'saveDeviceExtInfo'" method="'post'" validate="false">
	    <@ww.token name="saveDeviceExtInfoToken"/>
	    <@ww.hidden name="'device.id'" value="'${req.getParameter('device.id')?if_exists}'"/>
	    <@inputTable>
	        <tr>
	        	<td align="right" valign="top"><label class="label">${action.getText('deviceExtInfo.madeIn')}:</label></td>
		 		<td>
		 			<#assign madeIn = ''/>
		 			<#if device?exists>
		 				<#assign madeIn = "${device.madeIn?if_exists}"/>
		 			</#if>
		 			<input type="text" name="device.madeIn" class="underline"  value="${madeIn}"  maxlength="150" disabled="true"/>
		 			<a onClick='country_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
	 				<@ww.hidden name="'country.name'" value=""/>
		 		</td>
	            <@ww.textfield readonly="false" label="'${action.getText('deviceExtInfo.zone')}'" name="'device.zone'" value="'${device.zone?if_exists}'"  cssClass="'underline'"/>
	        	<@ww.textfield readonly="false" label="'${action.getText('deviceExtInfo.material')}'" name="'device.material'" value="'${device.material?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>
	            <@ww.textfield readonly="false" label="'${action.getText('deviceExtInfo.productNo')}'" name="'device.productNo'" value="'${device.productNo?if_exists}'"  cssClass="'underline'"/>
	            <@pp.datePicker label="'${action.getText('deviceExtInfo.madeDate')}'" name="'device.madeDate'"
	     							value="'${(device.madeDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	        	<@ww.textfield readonly="false" required="false" label="'${action.getText('device.systemDaisTime')}'" name="'device.systemDaisTime'" value="'${device.systemDaisTime?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	        <tr>
	            <@pp.datePicker label="'${action.getText('deviceExtInfo.inFactoryTime')}'" name="'device.inFactoryTime'"
	     							value="'${(device.inFactoryTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	            <@pp.datePicker label="'${action.getText('deviceExtInfo.installedDate')}'" name="'device.installedDate'"
	     							value="'${(device.installedDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	        	<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.equippedCapacitor')}'" name="'device.equippedCapacitor'" value="'${device.equippedCapacitor?if_exists}'"  cssClass="'underline'"/>
	        </tr>
	        <tr>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.identifiers')}'" name="'device.identifiers'" value="'${device.identifiers?if_exists}'"  cssClass="'underline'"/>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.centerCode')}'" name="'device.centerCode'" value="'${device.centerCode?if_exists}'"  cssClass="'underline'"/>
	         	<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.opCondition')}'" name="'device.opCondition'" value="'${device.opCondition?if_exists}'"  cssClass="'underline'"/>
	        </tr>
	        <tr>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.power')}'" name="'device.power'" value="'${device.power?if_exists}'"  cssClass="'underline'"/>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.weight')}'" name="'device.weight'" value="'${device.weight?if_exists}'"  cssClass="'underline'"/>
	            <@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.size')}'" name="'device.size'" value="'${device.size?if_exists}'"  cssClass="'underline'"/> 
	        </tr>
	         <tr>
	         	<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.machMaintenancePeople')}'" name="'device.machMaintenancePeople'" value="'${device.machMaintenancePeople?if_exists}'"  cssClass="'underline'"/>	
              	<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.statusDescription')}'" name="'device.statusDescription'" value="'${device.statusDescription?if_exists}'"  cssClass="'underline'"/>
	        	<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.elecMaintenancePeople')}'" name="'device.elecMaintenancePeople'" value="'${device.elecMaintenancePeople?if_exists}'"  cssClass="'underline'"/>
	        </tr>
	        <tr>
	        	<td align="right" valign="top">
			     <label class="label">${action.getText('deviceExtInfo.oneMaintenanceTime')}:</label>
			    </td>
	        	<td>
	        		<input type="text" name="device.oneMaintenanceTime" class="underline"  value="${device.oneMaintenanceTime?if_exists}"  maxlength="12" size="12" />
	        		${action.getText('（')}<input type="text" style="border:0px" name="total.twoMaintenanceHour" size="4" disabled value="${device.oneMaintenanceHour?if_exists}"/>${action.getText('）')}
	        	</td>
	        	<#--<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.oneMaintenanceTime')}'" name="'device.oneMaintenanceTime'" value="'${device.oneMaintenanceTime?if_exists}'" size="12" cssClass="'underline'"/>-->
	        	<@pp.datePicker label="'${action.getText('deviceExtInfo.lastOneMaintenanceTime')}'" name="'device.lastOneMaintenanceTime'"
	     							value="'${(device.lastOneMaintenanceTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	        </tr>
	        <tr>
	        	<td align="right" valign="top">
			     <label class="label">${action.getText('deviceExtInfo.twoMaintenanceTime')}:</label>
			    </td>
	        	<td>
	        		<input type="text" name="device.twoMaintenanceTime" class="underline"  value="${device.twoMaintenanceTime?if_exists}"  maxlength="12" size="12" />
	        		${action.getText('（')}<input type="text" style="border:0px" name="total.twoMaintenanceHour" size="4" disabled value="${device.twoMaintenanceHour?if_exists}"/>${action.getText('）')}
	        	</td>
	        	
	        	<#--<@ww.textfield readonly="false" required="false" label="'${action.getText('deviceExtInfo.twoMaintenanceTime')}'" name="'device.twoMaintenanceTime'" value="'${device.twoMaintenanceTime?if_exists}'" size="12" cssClass="'underline'"/>-->
	        	<@pp.datePicker label="'${action.getText('deviceExtInfo.lastTwoMaintenanceTime')}'" name="'device.lastTwoMaintenanceTime'"
	     							value="'${(device.lastTwoMaintenanceTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	        </tr>
	        <tr>
	        	<td align="right" valign="top" rowspan="3">
                  <label  class="label">${action.getText('comment')}:</label>
            	</td>
            	<td rowspan="3" colspan="3">
                	<textarea name="device.comment" rows="3" cols="40">${device.comment?if_exists}</textarea>
            	</td>     	
			</tr>
	    </@inputTable>
	    <#if !(action.isReadOnly())>
	      <@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
		  </@buttonBar>
		</#if>
		
		<script language="javascript">
			<@eam2008_LockPageIfNeed/>
			/*
			 *当该设备失效时,锁定页面所有控件
			*/
			<#if device.enabled?exists>
	          <#if !(device.enabled)>
	             __disableAllElements__(document.forms[0], new Array(""));
	             disableCalendars(document.forms[0],new Array("device.madeDate","device.inFactoryTime","device.installedDate"),true);
	          </#if>
	        </#if>
	        
			function country_OpenDialog() {
				var url = "${req.contextPath}/popup/countrySelector.html";
	  			popupModalDialog(url, 800, 600, countrySelectorHandler);
			}
			
			function countrySelectorHandler(result) {
				document.forms["deviceExtInfo"].elements["country.name"].value = result[1];
	  			document.forms["deviceExtInfo"].elements["device.madeIn"].value = result[1];
	  		
			}
			
			function customize_validate() {
				if ('' != document.forms["deviceExtInfo"].elements["device.zone"].value) { //验证地理区域名称是否溢出
				    if (!isValidLengthValue(document.forms["deviceExtInfo"].elements["device.zone"].value,0,50)) {
				      alert("${action.getText('device.zone.maxLength')}");
				  	  return false;
				    }
				  }
				if ('' != document.forms["deviceExtInfo"].elements["device.material"].value) { //验证材质是否溢出
				    if (!isValidLengthValue(document.forms["deviceExtInfo"].elements["device.material"].value,0,50)) {
				      alert("${action.getText('device.material.maxLength')}");
				  	  return false;
				    }
				  }
				if ('' != document.forms["deviceExtInfo"].elements["device.productNo"].value) { //验证出厂编号是否溢出
				    if (!isValidLengthValue(document.forms["deviceExtInfo"].elements["device.productNo"].value,0,50)) {
				      alert("${action.getText('device.productNo.maxLength')}");
				  	  return false;
				    }
				  }
				if(isNotEmpty(deviceExtInfo,"device.madeDate")) {
					if(!isDate("device.madeDate")){
						alert('${action.getText('deviceExtInfo.madeDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}
				}
				if(isNotEmpty(deviceExtInfo,"device.systemDaisTime")) {
				      if (!isDoubleNumber("device.systemDaisTime")) {
						alert("${action.getText('device.systemDaisTime.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["device.systemDaisTime"].value, 1001, 0)){
						alert("${action.getText('device.systemDaisTime.maxLength')}");
						return false;
				      }
				}
				
				
				if(isNotEmpty(deviceExtInfo,"device.inFactoryTime")) {
					if(!isDate("device.inFactoryTime")){
						alert('${action.getText('deviceExtInfo.inFactoryTime')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}
				}
				if(isNotEmpty(deviceExtInfo,"device.installedDate")) {
					if(!isDate("device.installedDate")){
						alert('${action.getText('deviceExtInfo.installedDate')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}  	
				}
				
				if(isNotEmpty(deviceExtInfo,"device.equippedCapacitor")) {
				      if (!isNumber("device.equippedCapacitor")) {
						alert("${action.getText('device.equippedCapacitor.isNotNumber')}");
						return false;
				      } else if (!isNumberBetweenBoolen(document.forms[0].elements["device.equippedCapacitor"].value, 1000000001, 0)){
						alert("${action.getText('device.equippedCapacitor.maxLength')}");
						return false;
				      }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.identifiers")) {
					if (!isValidLength(document.forms[0],"device.identifiers",0,50)){
						alert("${action.getText('device.identifiers.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.centerCode")) {
					if (!isValidLength(document.forms[0],"device.centerCode",0,50)){
						alert("${action.getText('device.centerCode.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.opCondition")) {
					if (!isValidLength(document.forms[0],"device.opCondition",0,250)){
						alert("${action.getText('device.opCondition.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.power")) {
				      if (!isDoubleNumber("device.power")) {
						alert("${action.getText('device.power.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["device.power"].value, 1000000001, 0)){
						alert("${action.getText('device.power.maxLength')}");
						return false;
				      }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.weight")) {
				      if (!isDoubleNumber("device.weight")) {
						alert("${action.getText('device.weight.isNotNumber')}");
						return false;
				      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["device.weight"].value, 1000000001, 0)){
						alert("${action.getText('device.weight.maxLength')}");
						return false;
				      }
				}
				if(isNotEmpty(deviceExtInfo,"device.size")) {
					if (!isValidLength(document.forms[0],"device.size",0,50)){
						alert("${action.getText('device.size.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.machMaintenancePeople")) {
					if (!isValidLength(document.forms[0],"device.machMaintenancePeople",0,50)){
						alert("${action.getText('device.machMaintenancePeople.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.statusDescription")) {
					if (!isValidLength(document.forms[0],"device.statusDescription",0,250)){
						alert("${action.getText('device.statusDescription.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.elecMaintenancePeople")) {
					if (!isValidLength(document.forms[0],"device.elecMaintenancePeople",0,50)){
						alert("${action.getText('device.elecMaintenancePeople.maxlength')}");
						return false;
				    }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.oneMaintenanceTime")) {
				      if (!isNumber("device.oneMaintenanceTime")) {
						alert("${action.getText('device.oneMaintenanceTime.isNotNumber')}");
						return false;
				      } else if (!isNumberBetweenBoolen(document.forms[0].elements["device.oneMaintenanceTime"].value, 1000000001, 0)){
						alert("${action.getText('device.oneMaintenanceTime.maxLength')}");
						return false;
				      }
				}
				
				if(isNotEmpty(deviceExtInfo,"device.twoMaintenanceTime")) {
				      if (!isNumber("device.twoMaintenanceTime")) {
						alert("${action.getText('device.twoMaintenanceTime.isNotNumber')}");
						return false;
				      } else if (!isNumberBetweenBoolen(document.forms[0].elements["device.twoMaintenanceTime"].value, 1000000001, 0)){
						alert("${action.getText('device.twoMaintenanceTime.maxLength')}");
						return false;
				      }
				}
				
				var madeDate = document.forms["deviceExtInfo"].elements["device.madeDate"].value;
				var strMadeDate = new String(madeDate);
				
				var installedDate = document.forms["deviceExtInfo"].elements["device.installedDate"].value;
				var strInstalledDate = new String(installedDate);
				
				var inFactoryTime = document.forms["deviceExtInfo"].elements["device.inFactoryTime"].value;
				var strInFactoryTime = new String(inFactoryTime);
				
				
				if ('' != document.forms["deviceExtInfo"].elements["device.comment"].value) {
				  if (!isValidLength(document.forms["deviceExtInfo"],"device.comment",null,250)) {
				    alert("${action.getText('deviceExtInfo.comment.maxLength')}");
				    return false;
				  }
				}
				if(isNotEmpty(deviceExtInfo,"device.lastOneMaintenanceTime")) {
					if(!isDate("device.lastOneMaintenanceTime")){
						alert('${action.getText('deviceExtInfo.lastOneMaintenanceTime')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}
				}
				if(isNotEmpty(deviceExtInfo,"device.lastTwoMaintenanceTime")) {
					if(!isDate("device.lastTwoMaintenanceTime")){
						alert('${action.getText('deviceExtInfo.lastTwoMaintenanceTime')}'+'${action.getText('dateFormate.error')}');
						return false; 
					}
				}
				//alert(document.forms["deviceExtInfo"].elements["device.power"].value);
				return true;
			}
	  	  
		</script>
	</@ww.form>
</@framePage>