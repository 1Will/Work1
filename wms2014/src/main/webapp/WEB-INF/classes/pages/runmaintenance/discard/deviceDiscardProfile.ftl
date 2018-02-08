<#-- $Id: discardProfile.ftl 8911 2007-12-02 09:30:05Z wzou $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('discardDiscardEdit.title')}">
    <@ww.form namespace="'/runmaintenance/discard'" name="'discard'" action="'saveDeviceDiscard'" method="'post'" validate="true">
        <@ww.token name="saveDiscardToken"/>
        <@ww.hidden name="'toolingDevFlag'" value="${(bTooling?string)?if_exists}"/>
        <#if discard.id?exists>
          <@ww.hidden name="'discard.id'" value="#{discard.id}"/>
          <@ww.hidden name="'toolingState'" value=""/>
          <@ww.hidden name="'deviceState'" value=""/>
        </#if>
        <@inputTable>
        
        	<tr>
        		<@ww.textfield label="'${action.getText('discadeNo')}'" name="'discard.discardNo'" value="'${discard.discardNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true" />
	 	  	  	<@ww.textfield label="'${action.getText('discardName')}'" name="'discard.name'" value="'${discard.name?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<@eam2008_DeviceSelector/>
		 	<tr>
		 		<#assign deviceModel = ''/>
				<#if discard.device?exists>
				 	<#assign deviceModel = "${discard.device.model?if_exists}" />
					</#if>
				<#assign deviceSpecification = ''/>
				<#if discard.device?exists>
				 	<#assign deviceSpecification = "${discard.device.specification?if_exists}" />
					</#if>
		 		<@ww.textfield label="'${action.getText('device.model')}'" name="'model'" value="'${deviceModel?if_exists}'" cssClass="'underline'" disabled="true"/>
		 		<@ww.textfield label="'${action.getText('device.specification')}'" name="'specification'" value="'${deviceSpecification?if_exists}'" cssClass="'underline'" disabled="true"/>   
	    	</tr>
	    	<tr>
	    		<#assign deviceType = ''/>
	            	<#if discard.device?exists>
						<#if discard.device.deviceType?exists>
						 	<#assign deviceType = "${discard.device.deviceType.name?if_exists}" />
						</#if>
					</#if>
				<#assign deviceValue = ''/>
	            	<#if discard.device?exists>
						<#if discard.device.deviceFinanceInfo?exists>
						 	<#assign deviceValue = "${discard.device.deviceFinanceInfo.origPrice?if_exists}" />
						</#if>
					</#if>
				<@ww.textfield label="'${action.getText('device.deviceType')}'" name="'device.deviceType'" value="'${deviceType?if_exists}'" cssClass="'underline'"  disabled="true"/>
	    		<@ww.textfield label="'${action.getText('device.value')}'" name="'device.value'" value="'${deviceValue?if_exists}'" cssClass="'underline'" disabled="true"/> 
	    	</tr>
	    	<tr>
	    		<#assign deviceMadeTime = ''/>
	            	<#if discard.device?exists>
						<#if discard.device.deviceExtInfo?exists>
						 	<#assign deviceMadeTime = "${discard.device.deviceExtInfo.madeDate?if_exists}" />
						</#if>
					</#if>
	    		<@ww.textfield label="'${action.getText('device.madeTime')}'" name="'device.madeTime'" value="'${deviceMadeTime?if_exists}'" cssClass="'underline'" disabled="true"/>   
		    	<#assign deviceFactory = ''/>
	            	<#if discard.device?exists>
						<#assign deviceFactory = "${discard.device.factory?if_exists}" />
					</#if>
				<@ww.textfield label="'${action.getText('supplier')}'" name="'factory'" value="'${deviceFactory?if_exists}'" cssClass="'underline'"  disabled="true"/>
		   	</tr>
		   	<tr>
		   		<#assign deviceYearLimit = ''/>
	            	<#if discard.device?exists>
						<#if discard.device.deviceFinanceInfo?exists>
						 	<#assign deviceYearLimit = "${discard.device.deviceFinanceInfo.yearLimit?if_exists}" disabled="true"/>
						</#if>
					</#if>
		   		<@ww.textfield label="'${action.getText('device.yearLimit')}'" name="'device.yearLimit'" value="${deviceYearLimit}" cssClass="'underline'" disabled="true"/>
   	            <@ww.select label="'报废分类'"
                	name="device"
               	 	headerKey="1" 
                	headerValue="Select Month"
                	list="{'', 
                			'IT等办公类设备', 
                			'计量检测、定扭、风动、电动类设备',
                			'非标线、数控床等大型生产设备和闲置设备',
                			'其他类设备'
                	  	  }"
                	value="selectedDevice"
    	         /> 
		    </tr>
            <tr>	
        		<@ww.select label="'${action.getText('apply.department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		                list="departments" emptyOption="true" disabled="false" required="true">
		    	</@ww.select>
	        	<#assign managerName = ''/>
					<#if discard.manager?exists>
					 <#assign managerName = "${discard.manager.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('discard.manager')}:</label></td>
	        	<td>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="manager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign managerId = ''/>
				<#if discard.manager?exists>
				 <#assign managerId = "${discard.manager.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" />
	           
            </tr>
            <tr>
            	<@pp.datePicker label="'${action.getText('discard.applyDatetime')}'" name="'discard.applyDatetime'"
	     							value="'${(discard.applyDatetime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>	
            </tr>
            <tr>
            	<@ww.textarea  label="'${action.getText('discard.cause')}'" 
	        	         name="'discard.cause'" 
	        	         value="'${discard.cause?if_exists}'"  
	        	         rows="5" cols="60" cssClass="'underline'" required="true"/>
            </tr>
            <#if discard.id?exists>
            <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
            <tr>
            	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('discard.status')}:</label></td>
            	<td>
		 			<input type="checkbox" name="discardAgree" value="0151" onclick="changediscardStateAgree()"/>${action.getText('discard.agree')}
		 		</td>
            </tr>
            <tr id='descardDatetime' style='display:none'>
            	<@pp.datePicker label="'${action.getText('discard.descardDatetime')}'" name="'discard.descardDatetime'"
	     							value="'${(discard.descardDatetime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
            </tr>
            <tr>
            	<@ww.textarea  label="'${action.getText('discard.checkupResult')}'" 
	        	         name="'discard.checkupResult'" 
	        	         value="'${discard.checkupResult?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            	<@ww.textarea  label="'${action.getText('discard.qmDeparOpinion')}'" 
	        	         name="'discard.qmDeparOpinion'" 
	        	         value="'${discard.qmDeparOpinion?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            </tr>
            <tr>
            	<@ww.textarea  label="'${action.getText('discard.techDeparOpinion')}'" 
	        	         name="'discard.techDeparOpinion'" 
	        	         value="'${discard.techDeparOpinion?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
            	<@ww.textarea  label="'${action.getText('discard.manuDeparOpinion')}'" 
	        	         name="'discard.manuDeparOpinion'" 
	        	         value="'${discard.manuDeparOpinion?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	
            </tr>
           </#if>
        </@inputTable>
        <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/discard/listDeviceDiscards.html?&&toolingDevFlag=false"/>
	      <#if discard.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('print')}" onclick="open_discardPdf('${discard.id}')"/>
	      </#if>	
	    </@buttonBar>
		<script language="JavaScript" type="text/JavaScript">
		   var oldApplyDatetime;
	       window.onload = function () {
       		var discardFlag = ${discard.discardFlag?string};
       		if(discardFlag){
       			document.forms["discard"].elements["discardAgree"].checked = true;
	       		document.getElementById("descardDatetime").style.display='inline';	
	       		//disableElements(discard, new Array("discard.descardDatetime"), true);
	       		document.forms["discard"].elements["discard.descardDatetime"].disabled=true;
	       		document.forms["discard"].elements["discard.checkupResult"].disabled=true;
	       		document.forms["discard"].elements["discard.qmDeparOpinion"].disabled=true;
	       		document.forms["discard"].elements["discard.techDeparOpinion"].disabled=true;
	       		document.forms["discard"].elements["discard.manuDeparOpinion"].disabled=true;
	       		document.forms["discard"].elements["save"].disabled=true;
	        }
	        <#if (device.id)?exists>
	        	oldApplyDatetime=document.forms["discard"].elements["discard.applyDatetime"].value;
	       	<#else>
		       	a = new Date();
				var time=a.format("yyyy-MM-dd");
				document.forms["discard"].elements["discard.applyDatetime"].value=time;
			</#if>
			<#if bTooling>
				<#if (tooling.department)?exists>
		     		document.forms["discard"].elements["tooling.department"].value='${tooling.department.name?if_exists}';
		     	</#if>
		       	<#if (tooling.toolingType)?exists>
		     		document.forms["discard"].elements["tooling.toolingType"].value='${tooling.toolingType.value?if_exists}';
		     	</#if>
		    </#if> 	
	       	<#if discard.department?exists>
	     		document.forms["discard"].elements["department.id"].value=#{discard.department.id?if_exists};
	     	</#if>
	       }
	      function manager_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, desigerSelectorHandler);
		  }
		  function desigerSelectorHandler(result) {
		    document.forms["discard"].elements["manager.id"].value = result[0];
		    document.forms["discard"].elements["manager.name"].value = result[1];
		  }
		  function changediscardStateAgree() {
		    if (document.forms["discard"].elements["discardAgree"].checked == true) {
		      document.getElementById("descardDatetime").style.display='inline';
		      document.forms["discard"].elements["discard.descardDatetime"].disabled=""
		      <#if bTooling>
		      	document.forms["discard"].elements["toolingState"].value = 'TOOLING_DISCARD';
		      <#else>
		      	document.forms["discard"].elements["deviceState"].value = 'DEVICE_DISCARD';
		      </#if>
		    }else{
			    	document.forms["discard"].elements["discard.descardDatetime"].disabled=false;
		       		document.forms["discard"].elements["discard.checkupResult"].disabled=false;
		       		document.forms["discard"].elements["discard.qmDeparOpinion"].disabled=false;
		       		document.forms["discard"].elements["discard.techDeparOpinion"].disabled=false;
		       		document.forms["discard"].elements["discard.manuDeparOpinion"].disabled=false;
		       		document.forms["discard"].elements["save"].disabled=false;
			    	document.getElementById("descardDatetime").style.display='none';
			    	document.forms["discard"].elements["discard.descardDatetime"].value='';
		    	<#if bTooling>
		      		document.forms["discard"].elements["toolingState"].value = 'TOOLING_NORMAL';
		        <#else>
		      		document.forms["discard"].elements["deviceState"].value = 'DEVICE_NORMAL';
		        </#if>
		    	}
		    }
		  function validate(){
		  	if(!discard_name()){
		  		return false;
		  	}
		  	<#if bTooling>
			  	if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
			      return false;
			    }
		    <#else>
		    	if(!eam2008_device_validate("${action.getText('select.device.no')}")){
					return false;
				}
		    </#if>
		    if (document.forms["discard"].elements["department.id"].value == '') {
		      alert("${action.getText('select.department.name')}");
		      return false;
		    }
		    if (document.forms["discard"].elements["manager.name"].value == '') {
		      alert("${action.getText('select.manager.name')}");
		      return false;
		    }
		    <#if bTooling>
			  	if(!discard_status()){  
			  		return false;
			  	}
		  	</#if>
		  	if(!validateTime_applyDatetime()){
		  		return false;
		  	}
		  	if(!discard_cause()){
		  		return false;
		  	}
		  	if(document.forms["discard"].elements["discardAgree"].checked == true){
		  		if(document.forms["discard"].elements["discard.descardDatetime"].value ==""){
		  			alert("${action.getText('select.discard.descardDatetime')}");
		  			return false;
		  		}else{
		  			if(!validateTime_descardDatetime()){
		  				return false;
		  			}
		  			return true;
		  		}
		  	}
		  	if(!discard_checkupResult()){
		  		return false;
		  	}
		  	if(!discard_qmDeparOpinion()){
		  		return false;
		  	}
		  	if(!discard_techDeparOpinion()){
		  		return false;
		  	}
		  	if(!discard_manuDeparOpinion()){
		  		return false;
		  	}
		  	
		  }
		  function discard_name(){
			  var name=document.forms["discard"].elements["discard.name"].value;
				if(!(name=='')){
				  	if(!isValidLength(document.forms[0], "discard.name", null, 50)){
				  		alert("${action.getText('discard.name.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
		  function discard_status(){
		  	var name=document.forms["discard"].elements["discard.status"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.status", null, 50)){
				  		alert("${action.getText('discard.status.length')}");
				  		return  false;
				  	}
		  		}
				return true;
			}
		  function discard_cause(){
			var name=document.forms["discard"].elements["discard.cause"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.cause", null, 250)){
				  		alert("${action.getText('discard.cause.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
	      function discard_checkupResult(){
	    	var name=document.forms["discard"].elements["discard.checkupResult"].value;
				if(!(name=='')){
			    	if(!isValidLength(document.forms[0], "discard.checkupResult", null, 250)){
				  		alert("${action.getText('discard.checkupResult.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
		  function discard_qmDeparOpinion(){
			var name=document.forms["discard"].elements["discard.qmDeparOpinion"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.qmDeparOpinion", null, 250)){
				  		alert("${action.getText('discard.qmDeparOpinion.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
		  function discard_techDeparOpinion(){
			var name=document.forms["discard"].elements["discard.techDeparOpinion"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.techDeparOpinion", null, 250)){
				  		alert("${action.getText('discard.techDeparOpinion.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
			function discard_manuDeparOpinion(){
			var name=document.forms["discard"].elements["discard.manuDeparOpinion"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.manuDeparOpinion", null, 250)){
				  		alert("${action.getText('discard.manuDeparOpinion.length')}");
				  		return  false;
				  	}
			  	}
				return true;
			}
			function validateTime_applyDatetime(){
				if(document.forms["discard"].elements["discard.applyDatetime"].value ==""){
					alert("${action.getText('select.discard.applyDatetime')}");
					return false;
				}
				var date=document.getElementById("discard.applyDatetime").value;
				if(!isDate("discard.applyDatetime")){
					alert("${action.getText('select.right.discard.applyDatetime')}");
					return false;
				}
				if(!isDateLessEqualThenCurrent(date)){
					alert("${action.getText('afresh.discard.applyDatetime')}");
					return false;
				}
				return true;
			}
			function validateTime_descardDatetime(){
				if(document.forms["discard"].elements["discard.descardDatetime"].value ==""){
					alert("${action.getText('select.discard.descardDatetime')}");
					return false;
				}
				var date=document.getElementById("discard.descardDatetime").value;
				var dateApply=document.getElementById("discard.applyDatetime").value; 
				if(!isDate("discard.descardDatetime")){
					alert("${action.getText('select.right.discard.descardDatetime')}");
					return false;
				}
				if(!isDateLessThenOldDate(date,dateApply)){
					alert("${action.getText('afresh.discard.descardDatetime')}");
					return false;
				}
				return true;
			}
			function open_discardPdf(id) {
				var discardId=id;
				var url='${req.contextPath}/reports/device/deviceDiscard.pdf?discardId='+discardId;
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=no,width=screen.width,height=screen.height,left=0,top=0");
	      	}
		
		</script>

    </@ww.form>
</@htmlPage>