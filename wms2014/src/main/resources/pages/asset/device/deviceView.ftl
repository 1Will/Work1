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

<@htmlPage title="${action.getText('device.title1')}">

	 <@ww.form namespace="'/asset/device'" name="'device'" action="'saveDevice'" method="'post'" validate="true">
		 <@ww.token name="saveDeviceToken"/>
		 <@inputTable>
		    <#if device.id?exists>
                <@ww.hidden name="'device.id'" value="#{device.id}"/>
            </#if>
            <@ww.hidden name="'device.version'" value="#{device.version?if_exists}"/>
		 	<tr>
		 		<@ww.textfield label="'${action.getText('device.no')}'" name="'device.deviceNo'" value="'${device.deviceNo?if_exists}'" cssClass="'underline'" required="flase" readonly="true" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('device.assetno')}'" name="'device.assetNo'" value="'${device.assetNo?if_exists}'" cssClass="'underline'" required="false" />
		 		<@ww.textfield label="'${action.getText('device.name')}'" name="'device.name'" value="'${device.name?if_exists}'" cssClass="'underline'" required="true"/>
		 	</tr>
		 	<tr>
		 	    <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('device.category')}:</label></td>
		 		<td>
		 			<#assign deviceTypeName = ''/>
		 			<#if device.deviceType?exists>
		 				<#assign deviceTypeName = "${device.deviceType.name?if_exists}"/>
		 			</#if>
		 			<input type="text" name="deviceType.name" class="underline"  value="${deviceTypeName}"  maxlength="150" disabled="true"/>
		 			<a onClick='deviceType_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>
		 		<#assign deviceTypeId = ''/>
		 			<#if device.deviceType?exists>
		 				<#assign deviceTypeId = "${device.deviceType.id?if_exists}"/>
		 			</#if>
		 		 <@ww.hidden name="'deviceType.id'" value="'${deviceTypeId}'"/>
		 		<@ww.textfield label="'${action.getText('device.model')}'" name="'device.model'" value="'${device.model?if_exists}'" cssClass="'underline'"/>
		 		<@ww.textfield label="'${action.getText('device.specification')}'" name="'device.specification'" value="'${device.specification?if_exists}'" cssClass="'underline'"/>
		 	</tr>
		 	<tr>		 		
		 	 	<@ww.select label="'${action.getText('device.department')}'" required="true" name="'department.id'" 
		 	 		listKey="id" listValue="name" onchange="'departmentValueChange()'"
                	list="departments" emptyOption="false" disabled="false" >
                	<#if device.department?exists>
        				<@ww.param name="'value'"  value="'${device.department.id?if_exists}'" />
        			</#if>
        		</@ww.select>
        		<@ww.select label="'${action.getText('device.line')}'" required="false" name="'productionLine.id'" 
        			listKey="id" listValue="name"
        			list="productionLines" emptyOption="false" disabled="false">
        			<#if device.productionLine?exists>
        				<@ww.param name="'value'"  value="'${device.productionLine.id?if_exists}'" />
        			</#if>
       			</@ww.select>

		 		 <@ww.textfield label="'${action.getText('device.installPlace')}'" name="'device.installPlace'" value="'${device.installPlace?if_exists}'" cssClass="'underline'" />
		 	</tr>
		 	<tr>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('device.supplier')}:</label></td>
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
		 		<@ww.textfield label="'${action.getText('device.factory')}'" name="'device.factory'" value="'${device.factory?if_exists}'" cssClass="'underline'"/>
		 		<@pp.datePicker label="'${action.getText('device.cardCreatedTime')}'" name="'device.cardCreatedTime'"
	     						value="'${(device.cardCreatedTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
		 	</tr>
		 	<tr>
		 		<@ww.select label="'${action.getText('device.property')}'" required="true" name="'property.id'" 
        			listKey="id" listValue="value"
        			list="deviceProps" emptyOption="true" disabled="false">
       			</@ww.select>
       			<@ww.select label="'${action.getText('device.sepecDevicePro')}'" required="false" name="'specificationProp.id'" 
        			listKey="id" listValue="value"
        			list="deviceSpecifictionProps" emptyOption="true" disabled="false">
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
		 		<@ww.select label="'${action.getText('device.status')}'" required="false" name="'device.status.id'" 
        			listKey="id" listValue="value"
        			list="deviceStatus" emptyOption="true" disabled="true">
       			</@ww.select>
       			<@ww.textfield readonly="true" label="'${action.getText('device.lastModifiedMgrStatusDate')}'" name="'device.lastModifiedMgrStatusDate'" 
       							value="'${device.lastModifiedMgrStatusDate?if_exists}'"  cssClass="'underline'" disabled="true"/>
		 	</tr>
		 	<tr>
		 		<@eam2008_RecordLog creator="${device.creator?if_exists}" createdTime="${device.createdTime?if_exists?datetime}" 
		 			   							lastOperator="${device.lastOperator?if_exists}" lastModifiedTime="${device.lastModifiedTime?if_exists?datetime}"/>
		 	</tr>
		 	<tr>
		 		<#assign docText = ""/>
		 		<#assign docState = ""/>
		 		<#if job?exists>
		 			<#assign docText = "${job.comment?if_exists}" />
		 			<#if job.docState?exists>
		 				<#assign docState = "${job.docState.value?if_exists}"/>
		 			</#if>
		 		</#if>
            	<@ww.textfield label="'${action.getText('audit.explain')}'" name="'approv.comment'" value="'${docText}'" cssClass="'underline'" />
		 		<@ww.textfield label="'${action.getText('state')}'" name="'state'" value="'${docState}'" cssClass="'underline'" readonly="true"/>
		 	</tr>
		 	<tr>
	        	<td align="right" valign="top"><label class="label">${action.getText('audit.people')}</label></td>
	        	<#assign wfDocId = "${device.class.name}" />
	        	<td>
	        		<select name="approverIds" multiple="multiple" style="display:none" />
	        		<input type="text" name="approver.names" 
	        			class="underline"  value="${approvers?if_exists}"  maxlength="150" />
		    		<a onClick="approver_OpenDialog('${wfDocId}');">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        	<@ww.select label="'${action.getText('last.audit.people')}'"
	                    	name="'finalApproverId'"
	                   	 	listKey="id" 
	                   	 	listValue="name"
	                    	list="docFinalApprovers"
	                    	emptyOption="true" disabled="false"
	                    	required="true"
	        		/>
		        </td>
        	</tr>
		 </@inputTable>
		 <@buttonBar>
            <input type="button" name="close" value="${action.getText('close')}" class="button" onclick="window.close()">
         </@buttonBar>
     </@ww.form>
     	<script language="javascript" type="text/JavaScript">

		window.onload = function () {
			<#if device.id?exists>
				var url = '${req.contextPath}/asset/device/editDeviceExtInfo.html?device.id=' + ${device.id} + '&disabled=1';
		 		document.all.frame.src= url;
		 		getObjByNameRe("extInfo").className = "selectedtab";
	 		</#if>

	 		<#if device.department?exists>
	 			document.forms["device"].elements["department.id"].value = #{device.department.id?if_exists};
	 		</#if>
	 		<#if device.productionLine?exists>
	 			document.forms["device"].elements["productionLine.id"].value = #{device.productionLine.id?if_exists};
	 		</#if>
	 		<#if device.property?exists>
	 			document.forms["device"].elements["property.id"].value = #{device.property.id?if_exists};
	 		</#if>
	 		<#if device.specificationProp?exists>
	 			document.forms["device"].elements["specificationProp.id"].value = #{device.specificationProp.id?if_exists};
	 		</#if>
	 		<#if device.deviceStatus?exists>
	 			document.forms["device"].elements["device.status.id"].value = #{device.deviceStatus.id?if_exists};
	 		</#if>
	 		//alert(window.frames["frame"].src);
	 		
	 	}
	 	<#if finalApprover?exists>
				selector = document.forms[0].elements["finalApproverId"];
				var groups=selector.options.length;  
				for (i=0; i<groups; i++){
					if (selector.options[i].value== "${finalApprover}"){
						selector.options[i].selected="true";
					}
				}
		</#if>
			__disableAllElements__(document.forms[0], new Array("close"));
			
			var x = document.forms[0].name + "_" + "device.cardCreatedTime" + "_trigger";
			document.forms[0].elements[x].disabled="true";

	</script>
	<#if device.id?exists>
		<ul id="beautytab">
			<li><a id="extInfo" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/editDeviceExtInfo.html?device.id=#{device.id}&disabled=1" target="frame" >${action.getText('editDeviceExtInfo')}</a></li>
			<li><a id="financeInfo" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/editDeviceFinanceInfo.html?device.id=#{device.id}&disabled=1" target="frame">${action.getText('deviceFinanceInfo')}</a></li>
			<li><a id="deviceDoc"   onclick="activeTab(this);" href="${req.contextPath}/asset/device/listDeviceDocs.html?device.id=#{device.id}&disabled=1" target="frame" >${action.getText('device.doc')}</a></li>
			<li><a id="accessoryDevice" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/listAccessoryDevices.html?device.id=#{device.id}&disabled=1" target="frame">${action.getText('accessoryDevice')}</a></li>		 
			<li><a id="attachTool" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/listAttachTools.html?device.id=#{device.id}&disabled=1" target="frame">${action.getText('attachTool')}</a></li>
			<li><a id="spare" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/listSpares.html?device.id=#{device.id}&disabled=1" target="frame">${action.getText('spare')}</a></li>		 
			<li><a id="args" onclick="activeTab(this);"  href="${req.contextPath}/asset/device/listDeviceArgs.html?device.id=#{device.id}&disabled=1" target="frame">${action.getText('deviceArgs')}</a></li>		 
		</ul>
		<iframe  id="frame" name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
	

</@htmlPage>