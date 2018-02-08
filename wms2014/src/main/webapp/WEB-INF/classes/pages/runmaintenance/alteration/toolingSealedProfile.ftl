<#--$Id: toolingSealedProfile.ftl 11015 2008-02-19 01:57:30Z wzou $ -->
<#include "../../includes/eam2008.ftl" />

<#if toolingDevFlag=="TOOLING">
  <#assign pageTitle="${action.getText('toolingsealed.title')}"/>
<#else>
  <#assign pageTitle="${action.getText('devicesealed.title')}"/>
</#if>
<@htmlPage title="${pageTitle}">
	 <@ww.form name="'ToolingSealed'" action="'saveToolingSealed'" method="'post'">
	 	  <@ww.token name="saveToolingSealedToken"/>
	 	   <#if alteration.id?exists>
               <@ww.hidden name="'alteration.id'" value="#{alteration.id}"/>
           </#if>
           <@ww.hidden name="'flag'" value="'ToolingSealed'"/>
           <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
           <@ww.hidden name="'toolingState'" value=""/>
           <@ww.hidden name="'deviceState'" value=""/>
           <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <@inputTable>
	 	  	   <tr>
		 	  	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillNo')}'" name="'alteration.sealedBillNo'" value="'${alteration.sealedBillNo?if_exists}'" cssClass="'underline'" disabled="true"/>
		 	  	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillName')}'" name="'alteration.sealedBillName'" value="'${alteration.sealedBillName?if_exists}'" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <#if toolingDevFlag=="TOOLING">
		 	   <@eam2008_ToolingSelector flag="ToolingSealed"/>
		 	  <#else>
		 	   <@eam2008_DeviceSelector flag="DeviceSealed"/>
		 	  </#if>
		 	  <tr>
	 	  	 	<#assign sealedUsedName = ''/>
				<#if alteration.sealedUsed?exists>
				 	<#assign sealedUsedName = "${alteration.sealedUsed.name}" />
				<#elseif loginUser?exists>
				 	<#assign sealedUsedName = "${loginUser.name}" />	
				</#if>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingsealed.sealedUsed')}:</label></td>
	        	<td>
	        		<input type="text" name="sealedUsed.name" 
	        			class="underline"  value="${sealedUsedName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="user_OpenSealedUsedDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign sealedUsedId = ''/>
				<#if alteration.sealedUsed?exists>
				 	<#assign sealedUsedId = "${alteration.sealedUsed.id}" />
				<#elseif loginUser?exists>
					<#assign sealedUsedId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="sealedUsed.id" value="${sealedUsedId}" />
						 	  
		 	  	<@pp.datePicker label="'${action.getText('toolingsealed.sealedDateApp')}'" name="'alteration.sealedDateApp'"
	     				value="'${(alteration.sealedDateApp?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
	     	</tr>
	     	<tr>
	     	    <@ww.textarea  label="'${action.getText('toolingsealed.reason')}'" 
	        	         name="'alteration.reason'" 
	        	         value="'${alteration.reason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 	</tr>
		 	
			<#if alteration.id?exists>
				 <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
			 	 <tr>
			 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('alteration.sealed')}:</label></td>
	            	<td>
			 			<input type="checkbox" name="sealedStateFlag" value="isSealed_true" onclick="changeSealedState()"/>${action.getText('alteration.approve')}
			 		</td>
			 	 </tr>
				 <tr>
	 	  	 	<#assign sealedUsedAprName = ''/>
				<#if alteration.sealedUsedApr?exists>
				 <#assign sealedUsedAprName = "${alteration.sealedUsedApr.name}" />
				    <#elseif loginUser?exists>
					    <#assign sealedUsedAprName = "${loginUser.name}" />
				</#if>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingsealed.sealedUsedApr')}:</label></td>
		        	<td>
		        		<input type="text" name="sealedUsedApr.name" 
		        			class="underline"  value="${sealedUsedAprName}"  maxlength="150" maxlength="10"  disabled="true"/>
		        		<label id=""></label>
			    		<a onClick="user_OpenSealedUsedAprDialog();">
			        		<img name="sealedUsedAprImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
			        </td>
			        <#assign sealedUsedAprId = ''/>
					<#if alteration.sealedUsedApr?exists>
					 	<#assign sealedUsedAprId = "${alteration.sealedUsedApr.id}" />
					 	 <#elseif loginUser?exists>
					    <#assign sealedUsedAprId = "${loginUser.id}" />
					</#if>
					<input type="hidden" name="sealedUsedApr.id" value="${sealedUsedAprId}" />
							 	  
			 	  	<@pp.datePicker label="'${action.getText('toolingsealed.sealedDateTm')}'" name="'alteration.sealedDateTm'"
		     				value="'${(alteration.sealedDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" maxlength="10" required="true"/>
	     		</tr>
	     		<tr>
	     	    <@ww.textarea  label="'${action.getText('toolingsealed.sealedComment')}'" 
	        	         name="'alteration.sealedComment'" 
	        	         value="'${alteration.sealedComment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 		</tr>
				</#if>
				<#if alteration.status=='SEALED' || alteration.isSealed ==true>
				 <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
			 	 <tr>
			 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('alteration.unSealed')}:</label></td>
	            	<td>
			 			<input type="checkbox" name="unSealedStateFlag" value="isUnSealed_true" onclick="changeUnSealedState()"/>${action.getText('alteration.approve')}
			 		</td>
			 	 </tr>
			 	 <#assign unSealedUsedAprName = ''/>
				<#if alteration.unSealedUsedApr?exists>
				 <#assign unSealedUsedAprName = "${alteration.unSealedUsedApr.name}" />
				 <#elseif loginUser?exists>
					    <#assign unSealedUsedAprName = "${loginUser.name}" />
				</#if>
		 		<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingsealed.unSealedUsedApr')}:</label></td>
		        	<td>
		        		<input type="text" name="unSealedUsedApr.name" 
		        			class="underline"  value="${unSealedUsedAprName}"  maxlength="150" maxlength="10"  disabled="true"/>
		        		<label id=""></label>
			    		<a onClick="user_OpenUnSealedUsedAprDialog();">
			        		<img name="unSealedUsedAprImg" src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
			        	</a>
			        </td>
			        <#assign unSealedUsedAprId = ''/>
					<#if alteration.unSealedUsedApr?exists>
					 	<#assign unSealedUsedAprId = "${alteration.unSealedUsedApr.id}" />
					 	<#elseif loginUser?exists>
					    <#assign unSealedUsedAprId = "${loginUser.id}" />
					</#if>
					<input type="hidden" name="unSealedUsedApr.id" value="${unSealedUsedAprId}" />
							 	  
			 	  	<@pp.datePicker label="'${action.getText('toolingsealed.unSealedDateTm')}'" name="'alteration.unSealedDateTm'"
		     				value="'${(alteration.unSealedDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
	     		</tr>
	     		<tr>
	     	    <@ww.textarea  label="'${action.getText('toolingsealed.unSealComment')}'" 
	        	         name="'alteration.unSealComment'" 
	        	         value="'${alteration.unSealComment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 		</tr>
			</#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	 	     
              <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateStore()'"/>  
	         </#if>	
	        	<#--<@vbutton name="print"  class="button" value="${action.getText('print.sealedRequestBill')}" onclick="open_discardPdf('')"/>
	        	<@vbutton name="unsealedPrint"  class="button" value="${action.getText('print.unsealedRequestBill')}" onclick="open_discardPdf('')"/>-->
	        	<@redirectButton  value="${action.getText('back')}" url="listToolingSealeds.html?toolingDevFlag=${toolingDevFlag}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	            <#if alteration.id?exists>
	      	         <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_SealedPdf('#{alteration.id}')"/>
	      	         <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_SealedXls('#{alteration.id}')"/>
	            </#if>
	     </@buttonBar>
	</@ww.form>
	
	<script>
		date = new Date();
		var nowTime=date.format("yyyy-MM-dd");				//获取当前时间
		
		<#if loginUser?exists>
			var usedName = "${loginUser.name}";				//获取当前用户
			var usedId = "${loginUser.id}";	
		</#if>
	 window.onload=function(){
	 	<#if (alteration.id)?exists>
		  	var sealedFlag = ${(alteration.isSealed)?string};
		  	if (sealedFlag) {
		  		document.forms[0].elements["sealedStateFlag"].checked=true;
		  	}
		  	
		  	var unSealedFlag = ${(alteration.isUnSealed)?string};
		  	if (unSealedFlag) {
		  		document.forms[0].elements["unSealedStateFlag"].checked=true;
		  	}
	  	</#if>
	  	
	  	/*
		 *	在新建时，将申请日期默认为当前日期
		*/ 
	  	<#if (alteration.id)?exists>
       	<#else>
	       	a = new Date();
			var time=a.format("yyyy-MM-dd");
			document.forms["ToolingSealed"].elements["alteration.sealedDateApp"].value=time;
		</#if>
		
		/*
		 *	根据封存的checkbox的对象是否存在、其值是true或false,来置设备或工装的状态
		*/
		if (document.forms[0].elements["sealedStateFlag"] != null) {
			if (document.forms[0].elements["sealedStateFlag"].checked == true) {
			   disableElements(document.forms["ToolingSealed"],new Array("alteration.sealedBillName","alteration.reason","alteration.sealedDateApp"),true);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.sealedDateApp"),true);
              
              	<#if toolingDevFlag=="TOOLING">
              		document.forms[0].elements["toolingState"].value='TOOLING_DEMARCATE';       //TOOLING_DEMARCATE为工装状态为"封存中"
              	<#else>
              		document.forms[0].elements["deviceState"].value='DEVICE_DEMARCATE';       //DEVICE_DEMARCATE为设备状态为"封存中"
              	</#if>
			} 
		}
		
		/*
		 *	根据封存的checkbox的对象是否存在、其值是true或false,来置设备或工装的状态
		*/
		
		
		
		/*
		 *	根据启封的checkbox的对象是否存在、其值是true或false,来置设备或工装的状态
		*/
		if (document.forms[0].elements["unSealedStateFlag"] != null) {
			if (document.forms[0].elements["unSealedStateFlag"].checked == true) {
				disableElements(document.forms["ToolingSealed"],new Array("sealedStateFlag","alteration.sealedComment","alteration.sealedDateTm"),true);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.sealedDateTm"),true);
              	document.forms[0].elements["sealedUsedAprImg"].style.display="none";
              	
              	<#if toolingDevFlag=="TOOLING">
              		document.forms[0].elements["toolingState"].value='TOOLING_NORMAL';       //TOOLING_NORMAL为工装状态为"正常"
              	<#else>
              		document.forms[0].elements["deviceState"].value='DEVICE_NORMAL';       //DEVICE_NORMAL为设备状态为"正常"
              	</#if>
			} 
		}
	 }
	 
	/*
	 *	封存人的选择
	*/ 
	function user_OpenSealedUsedDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSealedUsedSelectorHandler);
	 }	
	
	function userSealedUsedSelectorHandler(result) {
	 if (null != result){
	  		document.forms[0].elements["sealedUsed.id"].value = result[0];
	  		document.forms[0].elements["sealedUsed.name"].value = result[1];
	  		}
	}
	
	/*
	 *	封存批准人的选择
	*/ 
	function user_OpenSealedUsedAprDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSealedUsedAprSelectorHandler);
	 }	
	
	function userSealedUsedAprSelectorHandler(result) {
	     if(null !=result){
	  		document.forms[0].elements["sealedUsedApr.id"].value = result[0];
	  		document.forms[0].elements["sealedUsedApr.name"].value = result[1];
	  		}
	}
	
	/*
	 *	启封批准人的选择
	*/ 
	function user_OpenUnSealedUsedAprDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedUsedAprSelectorHandler);
	 }	
	
	function userUnSealedUsedAprSelectorHandler(result) {
	       if(null!=result){
	  		  document.forms[0].elements["unSealedUsedApr.id"].value = result[0];
	  		  document.forms[0].elements["unSealedUsedApr.name"].value = result[1];
	  		}
	}
	  	
	function changeSealedState() {
			if (document.forms[0].elements["sealedStateFlag"].checked == true) {
			
              	<#if toolingDevFlag=="TOOLING">
              		document.forms[0].elements["toolingState"].value='TOOLING_DEMARCATE';       //TOOLING_DEMARCATE为工装状态为"封存中"
              	<#else>
              		document.forms[0].elements["deviceState"].value='DEVICE_DEMARCATE';       //DEVICE_DEMARCATE为设备状态为"封存中"
              	</#if>
				
				disableElements(document.forms["ToolingSealed"],new Array("alteration.sealedComment","alteration.sealedDateTm"),false);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.sealedDateTm"),false);
              	document.forms[0].elements["sealedUsedAprImg"].style.display="inline";
              	
              	document.forms[0].elements["sealedUsedApr.id"].value = usedId;				//默认当前时间、用户
	  			document.forms[0].elements["sealedUsedApr.name"].value = usedName;
	  			document.forms[0].elements["alteration.sealedDateTm"].value = nowTime;
			} else {
				<#if toolingDevFlag=="TOOLING">
              		document.forms[0].elements["toolingState"].value='TOOLING_NORMAL';       //TOOLING_DEMARCATE为工装状态为"正常中"
              	<#else>
              		document.forms[0].elements["deviceState"].value='DEVICE_NORMAL';       //DEVICE_DEMARCATE为设备状态为"正常中"
              	</#if>
				disableElements(document.forms["ToolingSealed"],new Array("alteration.sealedComment","alteration.sealedDateTm"),true);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.sealedDateTm"),true);
              	document.forms[0].elements["sealedUsedAprImg"].style.display="none";
			}
	}
	
	function changeUnSealedState() {
			if (document.forms[0].elements["unSealedStateFlag"].checked == true) {
			
              	<#if toolingDevFlag=="TOOLING">
              		document.forms[0].elements["toolingState"].value='TOOLING_NORMAL';       //TOOLING_NORMAL为工装状态为"正常"
              	<#else>
              		document.forms[0].elements["deviceState"].value='DEVICE_NORMAL';       //DEVICE_NORMAL为设备状态为"正常"
              	</#if>
				disableElements(document.forms["ToolingSealed"],new Array("alteration.unSealComment","alteration.unSealedDateTm"),false);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.unSealedDateTm"),false);
              	document.forms[0].elements["unSealedUsedAprImg"].style.display="inline";
              	
              	document.forms[0].elements["unSealedUsedApr.id"].value = usedId;			
	  			document.forms[0].elements["unSealedUsedApr.name"].value = usedName;
	  			document.forms[0].elements["alteration.unSealedDateTm"].value = nowTime;
			} else {
				<#if toolingDevFlag=="TOOLING">
              		if(document.forms[0].elements["toolingState"].value=='TOOLING_NORMAL'){       //TOOLING_NORMAL为工装状态为"正常"
              			document.forms[0].elements["toolingState"].value='TOOLING_DEMARCATE';
              		}
              	<#else>
              		if(document.forms[0].elements["deviceState"].value='DEVICE_NORMAL'){       //DEVICE_NORMAL为设备状态为"正常"
              			document.forms[0].elements["deviceState"].value='DEVICE_DEMARCATE';
              		}
              	</#if> 
				disableElements(document.forms["ToolingSealed"],new Array("alteration.unSealComment","alteration.unSealedDateTm"),true);
              	disableCalendars(document.forms["ToolingSealed"],new Array("alteration.unSealedDateTm"),true);
              	document.forms[0].elements["unSealedUsedAprImg"].style.display="none";
              	
			}
	}
	function validateStore() {
		if(!alteration_sealedBillName()){
			return false;
		}
		<#if toolingDevFlag=="TOOLING">
			if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		      		return false;
		    }
		<#else>
			if(!eam2008_device_validate("${action.getText('select.device.no')}")){
					return false;
			}
		</#if>
		/*
         *  验证封存人字段是否为空
        */
        if ('' == document.forms["ToolingSealed"].elements["sealedUsed.name"].value) {
          alert("${action.getText('select.sealedUsed')}");
          return false;
        }
        
        /*
         *  验证申请日期字段的时间格式
        */
   
        if ('' != document.forms["ToolingSealed"].elements["alteration.sealedDateApp"].value) {
             if (!isDate("alteration.sealedDateApp")) {
		      alert("${action.getText('toolingsealed.sealedDateApp')}" + "," + "${action.getText('dateFormate.error')}");
		      return false;
		       }
		       
		    }
        
       if(document.getElementById("alteration.reason").value!=''){
		 if(!isValidLength(document.forms[0], "alteration.reason", null, 250)){
				alert("${action.getText('alteration.reason.length')}");
				return  false;
	     }
	   }
	 <#if alteration.id?exists>
       if (document.forms[0].elements["sealedStateFlag"].checked == true) {
         if ('' == document.forms["ToolingSealed"].elements["sealedUsedApr.name"].value) {
	       alert("${action.getText('select.sealedUsedApr')}");
	       return false;
	     }
	   }
	   if ('' != document.forms["ToolingSealed"].elements["alteration.sealedDateTm"].value) {
	     if (!sealedDateTmValidate()) {
	       return false;
	     }
	   } else {
	     alert("${action.getText('select.sealedDateTm')}");
	     return false;
	   }
	   if ('' != document.forms["ToolingSealed"].elements["alteration.sealedComment"].value) {
         if (!isValidLength(document.forms["ToolingSealed"], new Array("alteration.sealedComment"), 0, 250)) {
           alert("${action.getText('sealedComment.length.overflow')}");
           return false;
         }
       }
	 </#if>
	 <#if alteration.status=='SEALED' || alteration.isSealed ==true>
	   if (document.forms[0].elements["unSealedStateFlag"].checked == true) {
         if ('' == document.forms["ToolingSealed"].elements["unSealedUsedApr.name"].value) {
	       alert("${action.getText('select.unSealedUsedApr')}");
	       return false;
	     }
	   }
	  if ('' != document.forms["ToolingSealed"].elements["alteration.unSealedDateTm"].value) {
	    if (!unSealedDateTmValidate()) {
	      return false;
	    }
	  } else {
	    alert("${action.getText('select.unSealedDateTm')}");
	    return false;
	  }
	  if ('' != document.forms["ToolingSealed"].elements["alteration.unSealComment"].value) {
        if (!isValidLength(document.forms["ToolingSealed"], new Array("alteration.unSealComment"), 0, 250)) {
          alert("${action.getText('unSealComment.length.overflow')}");
          return false;
        }
      }
	 </#if>
        <#--}-->
        
        if ('' != document.forms["ToolingSealed"].elements["alteration.reason"].value) {
            if (!isValidLength(document.forms["ToolingSealed"], new Array("alteration.reason"), 0, 250)) {
              alert("${action.getText('reason.length.overflow')}");
              return false;
            }
          }
       return true; 
	}
	
	
		
	function sealedDateTmValidate() {
		    if (!isDate("alteration.sealedDateTm")) {
		      alert("${action.getText('toolingsealed.sealedDateTm')}" + "," + "${action.getText('dateFormate.error')}");
		      return false;
		    }
		    var date = document.forms["ToolingSealed"].elements["alteration.sealedDateTm"].value;
		    if (date == nowTime){
		    	return true;
		    }
	       <#-- if (!isDateLessThenCurrent(date)) {
	          alert("${action.getText('sealedDateTm.front.error')}");
	          return false;
	        }-->
		    return true;
		  }
	
	function unSealedDateTmValidate() {
		    if (!isDate("alteration.unSealedDateTm")) {
		      alert("${action.getText('toolingsealed.unSealedDateTm')}" + "," + "${action.getText('dateFormate.error')}");
		      return false;
		    }
		    var date = document.forms["ToolingSealed"].elements["alteration.unSealedDateTm"].value;
		    if (date == nowTime){
		    	return true;
		    }
	        if (!isDateLessThenCurrent(date)) {
	          alert("${action.getText('unSealedDateTm.front.error')}");
	          return false;
	        }
		    return true;
		  }
		  
	function alteration_sealedBillName(){
		  if(document.forms[0].elements["alteration.sealedBillName"].value == ''){
		  	alert("${action.getText('alteration.sealedBillName.requiredstring')}");
		  	return false;
		  }
		  var name=document.forms[0].elements["alteration.sealedBillName"].value;
			if(!(name=='')){
			  	if(!isValidLength(document.forms[0], "alteration.sealedBillName", null, 50)){
			  		alert("${action.getText('alteration.sealedBillName.length')}");
			  		return  false;
			  	}
		  	}
	  	
			return true;
      }
      
      function open_SealedXls(id) {
				var alterationId=id;
				<#if toolingDevFlag=='TOOLING'>
			          var url='${req.contextPath}/reports/tooling/toolingSealed.xls?alterationId='+alterationId;
		        <#else>
			          var url='${req.contextPath}/reports/device/deviceSealed.xls?alterationId='+alterationId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	  function open_SealedPdf(id) {
				var alterationId=id;
				<#if toolingDevFlag=='TOOLING'>
			          var url='${req.contextPath}/reports/tooling/toolingSealed.pdf?alterationId='+alterationId;
		        <#else>
			          var url='${req.contextPath}/reports/device/deviceSealed.pdf?alterationId='+alterationId;
		        </#if>
	      		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	</script>
</@htmlPage>