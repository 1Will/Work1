<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingsealed.title')}">
	 <@ww.form name="'ToolingSealed'" action="'saveToolingSealed'" method="'post'">
	 	  <@ww.token name="saveToolingSealedToken"/>
	 	   <#if alteration.id?exists>
               <@ww.hidden name="'alteration.id'" value="#{alteration.id}"/>
           </#if>
               <@ww.hidden name="'alteration.sealedStatus'" value="'Noaudit'"/>
               <@ww.hidden name="'sealedFlag'" value="'T'"/>
	 	  <@inputTable>
	 	  	   <tr>
		 	  	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillNo')}'" name="'alteration.sealedBillNo'" value="'${alteration.sealedBillNo?if_exists}'" cssClass="'underline'" disabled="true"/>
		 	  	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillName')}'" name="'alteration.sealedBillName'" value="'${alteration.sealedBillName?if_exists}'" cssClass="'underline'" required="true" />
		 	  </tr>
		 	 <@eam2008_ToolingSelector/>
		 	  <tr>
		 	     <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingsealed.sealedPeopleProfile')}:</label></td>
		 	    <td>
		 			<input type="text" name="alteration.sealedPeopleDisplay" class="underline"  value="${alteration.sealedPeople?if_exists}"  maxlength="150" disabled/>
		 			<@ww.hidden name="'alteration.sealedPeople'" value="'${alteration.sealedPeople?if_exists}'"/>
		 			<a onClick='user_OpenSealedDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>		 	  
		 	  	<@pp.datePicker label="'${action.getText('toolingsealed.sealedDateTmProfile')}'" name="'alteration.sealedDateTm'"
	     				value="'${(alteration.sealedDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
	     	</tr>
	     	<tr>
	     	    <@ww.textarea  label="'${action.getText('toolingsealed.sealedComment')}'" 
	        	         name="'alteration.sealedComment'" 
	        	         value="'${alteration.sealedComment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		 	</tr>
		    <#if  alteration.id?exists>
	     	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	     	<tr>
		 	  	<td align="right" valign="top"><label class="label">${action.getText('toolingsealed.unsealFlag')}:</label></td>
            	<td>
		 			<input type="checkbox" name="faultStateFlag" value="TOOLING_NORMAL" onclick="changeSealedFaultState()"/>
		 		</td>
		 	 </tr>
		  </#if>
		  <#if alteration.id?exists>
		      <@ww.hidden name="'alteration.unSealStatus'" value="'Noaudit'"/>
		      <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>		     
		 	  <tr>
		 	    <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingunsealed.unsealedPeopleProfile')}:</label></td>
		 	    <td>
		 			<input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="${alteration.unSealPeople?if_exists}"  maxlength="150" disabled/>
		 			<@ww.hidden name="'alteration.unSealPeople'" value="'${alteration.unSealPeople?if_exists}'"/>
		 			<a onClick='user_OpenUnSealedDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>		 
		 	  	<@pp.datePicker label="'${action.getText('toolingunsealed.unsealedDateTmProfile')}'" name="'alteration.unSealDateTm'"
	     				value="'${(alteration.unSealDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
		 	  </tr> 	  
		 	  <tr>
	     	    <@ww.textarea  label="'${action.getText('toolingunsealed.unsealedComment')}'" 
	        	         name="'alteration.unSealComment'" 
	        	         value="'${alteration.unSealComment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"  />
		 	  </tr>	
		 	  <#--
		 	  <#if (alteration.unSealBillNo?exists)>	  
	     	  <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	     	  <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('toolingunsealed.unsealedStatus')}:</label></td>
            	<td>
		 			<input type="checkbox" name="unSealedfaultStateFlag" value="TOOLING_NORMAL" onclick="changeUnSealedFaultState()"/>${action.getText('toolingunsealed.audit')}
		 		</td>
		 	  </tr>
		 	  </#if>
		 	  -->
		      </#if>
	 	  </@inputTable>
	 	  <@buttonBar>
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateStore()'"/>  
	        	<@vsubmit name="'print'" value="'${action.getText('print')}'" onclick="'javascript:void(0)'"/>  
	        	<@redirectButton  value="${action.getText('back')}" url="listToolingSealeds.html"/>
	     </@buttonBar>
	</@ww.form>
	
	<script>
	
	 window.onload=function(){
	  <#if  alteration.id?exists>
	    var checkBoxChecked='${alteration.sealedStatus}';
	    if(checkBoxChecked=="audit"){
	       document.forms["ToolingSealed"].elements["faultStateFlag"].checked=true; 
	        document.forms["ToolingSealed"].elements["alteration.sealedStatus"].value='audit';
	         disableElements(document.forms["ToolingSealed"],new Array("alteration.sealedBillNo","alteration.sealedBillName","alteration.sealedDateTm","alteration.sealedComment","faultStateFlag"),true );
	          var x = document.forms[0].name + "_" + "alteration.sealedDateTm" + "_trigger";
			   document.forms[0].elements[x].disabled="true"; 
			   document.forms["ToolingSealed"].elements["sealedFlag"].value='F';
	    }
		<#if (alteration.unSealStatus?exists)&&(alteration.sealedStatus=="audit")>
		        checkBoxChecked='${alteration.unSealStatus}'; 
		        if(checkBoxChecked=="audit"){
		           document.forms["ToolingSealed"].elements["unSealedfaultStateFlag"].checked=true;
		            disableElements(document.forms["ToolingSealed"],new Array("alteration.unSealBillNo","alteration.unSealBillName","alteration.unSealDateTm","alteration.unSealComment","unSealedfaultStateFlag","save"),true );
	                 var x = document.forms[0].name + "_" + "alteration.unSealDateTm" + "_trigger";
			          document.forms[0].elements[x].disabled="true";    
		        }		          
		</#if>
	 </#if>	
	 	 
	 }
	
	  function changeSealedFaultState(){
	  	var isUnSeal = document.forms["ToolingSealed"].elements["faultStateFlag"].checked;
	  	var sealedAry = new Array("alteration.sealedBillName","alteration.sealedDateTm","alteration.sealedComment");
	  	var unSealAry = new Array("alteration.unsealedPeopleDisplay","alteration.unSealDateTm","alteration.unSealComment");
	  	
	  	// 启封，关闭封存
	  	if (isUnSeal) {
	  		document.forms["ToolingSealed"].elements["alteration.sealedStatus"].value='Noaudit';
	        disableElements(document.forms["ToolingSealed"], sealedAry, true );
	        disableElements(document.forms["ToolingSealed"], unSealAry, false );
	        disableCalendars(document.forms["ToolingSealed"], new Array("alteration.sealedDateTm"), true );
	        disableCalendars(document.forms["ToolingSealed"], new Array("alteration.unSealDateTm"), false );
			document.forms["ToolingSealed"].elements["sealedFlag"].value='F';  
	  	} else {
	  		document.forms["ToolingSealed"].elements["alteration.sealedStatus"].value='audit';
	  		disableElements(document.forms["ToolingSealed"], unSealAry, true );
	  		disableElements(document.forms["ToolingSealed"], sealedAry, false );
	  		disableCalendars(document.forms["ToolingSealed"], new Array("alteration.unSealDateTm"), true );
	  		disableCalendars(document.forms["ToolingSealed"], new Array("alteration.sealedDateTm"), false );
			document.forms["ToolingSealed"].elements["sealedFlag"].value='T';
	  	}
	  }
	  
	  
	  function changeUnSealedFaultState(){
	     if (document.forms["ToolingSealed"].elements["unSealedfaultStateFlag"].checked == true) {
	       document.forms["ToolingSealed"].elements["alteration.unSealStatus"].value='audit';
	        disableElements(document.forms["ToolingSealed"],new Array("alteration.unSealBillNo","alteration.unSealBillName","alteration.unSealDateTm","alteration.unSealComment"),true );
	         var x = document.forms[0].name + "_" + "alteration.unSealDateTm" + "_trigger";
			   document.forms[0].elements[x].disabled="true";   
	     }else{
	        document.forms["ToolingSealed"].elements["alteration.unSealStatus"].value='Noaudit';
	        disableElements(document.forms["ToolingSealed"],new Array("alteration.unSealBillNo","alteration.unSealBillName","alteration.unSealDateTm","alteration.unSealComment"),false );
	         var x = document.forms[0].name + "_" + "alteration.unSealDateTm" + "_trigger";
			   document.forms[0].elements[x].disabled="";   
	     }
	  }
	  
	  function validateSealedBillName(min,max){
	     if(isNotEmpty(ToolingSealed,"alteration.sealedBillName")==true){
	        if(isValidLength(ToolingSealed,"alteration.sealedBillName",min,max)==false){
	           alert("${action.getText('toolingsealed.sealedBillName.length')}"+max);
	           return false;
	        }
	     }else{
	          alert("${action.getText('toolingsealed.sealedBillName.isEmpty')}");
	          return false;
	     }
	     return true;
	  }
	  
	  function validateSealedTooling(){
	        if(isNotEmpty(ToolingSealed,"tooling.id")==false){
	           alert("${action.getText('toolingsealed.tooling.exists')}");
	           return false;
	        }
	        return true;
	  }
	  
	  function validatesealedPeople(){
	        if(isNotEmpty(ToolingSealed,"alteration.sealedPeople")==false){
	           alert("${action.getText('toolingsealed.sealedPeople')}");
	           return false;
	        }
	        return true;
	  }
	  
	  function validatesealedDateTm(){
	     if(isNotEmpty(ToolingSealed,"alteration.sealedDateTm")==false){
	           alert("${action.getText('toolingsealed.sealedDateTm.isEmpty')}");
	           return false;
	     }else{
	        if(isDate("alteration.sealedDateTm")==false){
	            alert("${action.getText('toolingsealed.sealedDateTm')}");
	            return false;
	        }
	      }
	      return true;
	  }
	  
	  function validateSealedcomment(min,max){
	    if(isNotEmpty(ToolingSealed,"alteration.sealedComment")==true){
	        if(isValidLength(ToolingSealed,"alteration.sealedComment",min,max)==false){
	           alert("${action.getText('toolingsealed.comment.length')}"+max);
	           return false;
	        }
	     }
	     return true;
	  }
	  
	  function validateUnsealedBillName(min,max){
	     if(isNotEmpty(ToolingSealed,"alteration.unSealBillName")==true){
	        if(isValidLength(ToolingSealed,"alteration.unSealBillName",min,max)==false){
	           alert("${action.getText('toolingunsealed.unsealedBillName.length')}"+max);
	           return false;
	        }
	     }else{
	           alert("${action.getText('toolingunsealed.unsealedBillName.isEmpty')}");
	           return false;
	     }
	     return true;
	  }
	  
	  function validateunsealedPeople(){
	        if(isNotEmpty(ToolingSealed,"alteration.unSealPeople")==false){
	           alert("${action.getText('toolingunsealed.unsealedPeople')}");
	           return false;
	        }
	        return true;
	  }
	  
	  function validateUnsealedDateTm(){
	     if(isNotEmpty(ToolingSealed,"alteration.unSealDateTm")==false){
	           alert("${action.getText('toolingunsealed.unsealedDateTm.isEmpty')}");
	           return false;
	     }else{
	        if(isDate("alteration.unSealDateTm")==false){
	            alert("${action.getText('toolingunsealed.unsealedDateTm')}");
	            return false;
	        }else{
	           if(isDateLessThenOtherDate(document.forms[0].elements["alteration.sealedDateTm"].value,document.forms["ToolingSealed"].elements["alteration.unSealDateTm"].value)==false){
	              alert("${action.getText('toolingunsealedDateLessThantoolingsealedDate')}");
	              return false;
	           }
	        }
	      }
	      return true;
	  }
	  
	  function validateUnSealedcomment(min,max){
	    if(isNotEmpty(ToolingSealed,"alteration.unSealComment")==true){
	        if(isValidLength(ToolingUnSeal,"alteration.unSealComment",min,max)==false){
	           alert("${action.getText('toolingunsealed.comment.length')}"+max);
	           return false;
	        }
	     }
	     return true;
	  }
	  
	  function validateStore(){
	     /*  验证工装封存名称 */
	      if(!validateSealedBillName(0,50)){
	        return false;  
	       }
	     /*  验证工装是否存在 */
	     if(!validateSealedTooling()){
	        return false;
	     }
	     if(!validatesealedPeople()){
	        return false;
	     }
	     if(!validatesealedDateTm()){
	        return false;
	     }
	    if(!validateSealedcomment(0,250)){
	        return false;
	    } 	    
	    <#if alteration.id?exists>
		      <#if (alteration.sealedStatus=="audit")>
		          if(!validateUnsealedBillName(0,50)){
	                   return false;  
	              }
	              if(!validateunsealedPeople()){
	                   return false;
	              }
	              if(!validateUnsealedDateTm()){
	                   return false;
	              }
	              if(!validateUnSealedcomment(0,250)){
	                   return false;
	              }	         
		      </#if>
	    </#if>   
	       
	  }
	function user_OpenSealedDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSealedSelectorHandler);
	 }
	 
 	function userSealedSelectorHandler(result) {
  		document.forms[0].elements["alteration.sealedPeople"].value = result[1];
  		document.forms[0].elements["alteration.sealedPeopleDisplay"].value = result[1];
  	}	
	 
	function user_OpenUnSealedDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
	 }	
	 
	function userUnSealedSelectorHandler(result) {
	  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
	  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
	}
	  
	</script>
</@htmlPage>