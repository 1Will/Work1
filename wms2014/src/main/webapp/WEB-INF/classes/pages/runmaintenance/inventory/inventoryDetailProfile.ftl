<#include "../../includes/eam2008.ftl" />
<#assign inventoryDetail=''/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
    <#assign inventoryDetail = "${action.getText('deviceDetailInventory.edit')}"/>
    <#else>
    <#assign inventoryDetail = "${action.getText('toolingDetailInventory.edit')}"/>
   </#if> 
</#if>
<@htmlPage title="${inventoryDetail}">
 <base target="_self">
	 <@ww.form namespace="'/runmaintenance/inventory'"  name="'listForm'" action="'saveInventoryDetail'" method="'post'">
	 	  <@ww.token name="saveAreaToken"/>
	 	  <@inputTable>
	 	     <#if inventoryBill.id?exists>
                <@ww.hidden name="'inventoryBill.id'" value="#{inventoryBill.id}"/>
            </#if>
              <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
              <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
            <#if inventoryBillDetail.id?exists>
              <@ww.hidden name="'inventoryBillDetail.id'" value="'${inventoryBillDetail.id?if_exists}'"/>
		 	</#if>
		 	<tr>
		 	<#if toolingDevFlag?exists>
		 	   <#if toolingDevFlag=='DEVICE'>
				<@eam2008_DeviceSelector flag="DeviceInventory"/>
				<#else>
				  <@eam2008_ToolingSelector toolingStatus="false" flag="ToolingInventory"/>
				</#if>
				</#if>
		   <#if toolingDevFlag?exists>
		 	   <#if toolingDevFlag=='DEVICE'>		
				<#assign assetNo =''/>
				<#if device?exists>
				  <#assign assetNo = '${device.assetNo?if_exists}'/>
				</#if>
		 	  	<@ww.textfield label="'${action.getText('assets_no')}'" name="'device.assetNo'" value="'${assetNo}'" cssClass="'underline'" disabled="true" readonly="true"/>
		 	  	<#assign assetModel =''/>
		 	  	<#if device?exists>
		 	  	  <#if device.model?exists>
				  <#assign assetModel = '${device.model?if_exists}'/>
				  </#if> 
				</#if>
		 	  	<@ww.textfield label="'${action.getText('equipment_model')}'" name="'device.model'" value="'${assetModel}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	</tr>
		 	<tr>
		 	  <#assign assetSpecification =''/>
		 	  	<#if device?exists>
		 	  	   <#if device.specification?exists>
				     <#assign assetSpecification = '${device.specification?if_exists}'/>
				   </#if>
				</#if>
		 	  	<@ww.textfield label="'${action.getText('equipment_specification')}'" name="'device.specification'" value="'${assetSpecification}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	  	<#assign assetCategory=''/>
		 	  	<#if device?exists>
					<#if device.deviceType?exists>
			 	    	<#assign assetCategory='${device.deviceType.name? if_exists}'/>
			 	  	</#if>
		 	  	</#if>
		 	  	<@ww.textfield label="'${action.getText('equipment_category')}'" name="'device.category'" value="'${assetCategory}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	    
		 	</tr>
		 	<tr>
            	<@ww.textarea  label="'${action.getText('check_result')}'" 
	        	          	   name="'inventoryBillDetail.inventoryResult'" 
	        	         	   value="'${inventoryBillDetail.inventoryResult? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
            	<@ww.textarea  label="'${action.getText('process_result')}'" 
	        	          	   name="'inventoryBillDetail.handleResult'" 
	        	         	   value="'${inventoryBillDetail.handleResult? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
		 	</tr>
		 	<#else>
		 	<#assign assetNo =''/>
		 	  	<#assign assetModel =''/>
		 	  	<#if device?exists>
				  <#assign assetModel = '${device.model?if_exists}'/>
				</#if>
		 	  	<@ww.textfield label="'${action.getText('tooling.model')}'" name="'tooling.model'" value="'${assetModel}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	</tr>
		 	<tr>
		 	  <#assign assetSpecification =''/>
		 	  	<#if device?exists>
				  <#assign assetSpecification = '${device.specification?if_exists}'/>
				</#if>
		 	  	<@ww.textfield label="'${action.getText('equipment.specification')}'" name="'tooling.specification'" value="'${assetSpecification}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	  	<#assign assetCategory=''/>
		 	  	<#if device?exists>
					<#if device.toolingType?exists>
			 	    	<#assign assetCategory='${device.toolingType.name? if_exists}'/>
			 	  	</#if>
		 	  	</#if>
		 	  	<@ww.textfield label="'${action.getText('tooling.category')}'" name="'tooling.toolingType'" value="'${assetCategory}'" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
		 	</tr>
		 	 	<tr>
            	<@ww.textarea  label="'${action.getText('check_result')}'" 
	        	          	   name="'inventoryBillDetail.inventoryResult'" 
	        	         	   value="'${inventoryBillDetail.inventoryResult? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
            	<@ww.textarea  label="'${action.getText('process_result')}'" 
	        	          	   name="'inventoryBillDetail.handleResult'" 
	        	         	   value="'${inventoryBillDetail.handleResult? if_exists}'"  
	        	               rows="3" cols="50" cssClass="'underline'" required="false"/>
		    </tr>
		  </#if>
		  </#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	  <#if !(action.isReadOnly())>
	        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return inventoryDetail_vilidate();'"/>
	      </#if>
	        <input type="button" value="关闭" class="button" onclick="window.close();"
	     </@buttonBar>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript"> 
	
	function requestPurchaseBill_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/requestPurchaseBillSelector.html',800,700);
	}	
	function inventoryDetail_vilidate(){
	  <#if toolingDevFlag?exists>
	     <#if toolingDevFlag=='DEVICE'>
	  if(document.getElementById("device.deviceNo").value==''){
	     alert('${action.getText('device.device.not.null')}');
	     return false;
	  }
	  <#else>
	      if(document.getElementById("tooling.deviceNo").value==''){
	       alert('${action.getText('tooling.tooling.not.null')}');
	       return false;
	  }
	  </#if>
	  </#if>
	
	  if(document.getElementById("inventoryBillDetail.inventoryResult").value!=''){
			if(!isValidLength(document.forms[0], "inventoryBillDetail.inventoryResult", null, 250)){
				alert("${action.getText('inventoryBillDetail.inventoryResult')}");
				return  false;
			}
		  }
	  if(document.getElementById("inventoryBillDetail.handleResult").value!=''){
		    if(!isValidLength(document.forms[0], "inventoryBillDetail.handleResult", null, 250)){
				alert("${action.getText('inventoryBillDetail.handleResult')}");
				return  false;
			}
		  }
	  return true;
	}
	</script>
</@htmlPage>
