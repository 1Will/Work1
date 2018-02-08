<#--$Id: areaProfile.ftl 6197 2007-08-06 02:21:08Z qsun $ -->
<#include "../../includes/eam2008.ftl" />
<#assign unusedBillEditTitle=""/>
<#if toolingDevFlag?exists>
   <#if toolingDevFlag=='DEVICE'>
   <#assign unusedBillEditTitle="${action.getText('unusedBillDeviceTitle.title')}"/>
   <#else>
    <#assign unusedBillEditTitle="${action.getText('unusedBillToolingTitle.title')}"/>
   </#if>
</#if>
<@htmlPage title="${unusedBillEditTitle}">
    <@ww.form namespace="'/runmaintenance/unused'" name="'unused'" action="'saveUnused'" method="'post'">
        <@ww.token name="saveUnusedToken"/>
        <@inputTable>
            <#if unused.id?exists>
                <@ww.hidden name="'unused.id'" value="#{unused.id}"/>
            </#if>
            <@ww.hidden name="'flag'" value="'Unused'"/>
            <@ww.hidden name="'deviceState'" value=""/>
            <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
            <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
           <tr>
			<@ww.textfield label="'${action.getText('unusedBill_no')}'" name="'unused.code'" value="'${unused.code?if_exists}'" cssClass="'underline'"  readonly="true" disabled="true" required="false"/>
	 		
	 		<@ww.textfield label="'${action.getText('unusedBill_Name')}'" name="'unused.name'" value="'${unused.name?if_exists}'" cssClass="'underline'" required="true" required="true"/>
		</tr>
		<#if toolingDevFlag?exists>
          <#if toolingDevFlag=='DEVICE'>
		  <@eam2008_DeviceSelector flag="DeviceUnused"/>
		  <#else>
		   <@eam2008_ToolingSelector toolingStatus="false" flag="Unused"/>
		</#if>
		</#if>
	 	<tr> 
		    <#assign unusedPeopleName = ''/>
					<#if unused.applicant?exists>
					  <#assign unusedPeopleName = "${unused.applicant.name}" />
					  <#elseif loginUser?exists>
					  <#assign unusedPeopleName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('unused.people')}:</label></td>
	        	<td>
	        		<input type="text" name="applicant.name" 
	        			class="underline"  value="${unusedPeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		
		    		    <a onClick="receiveUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign unusedPeopleId = ''/>
				<#if unused.applicant?exists>
				 <#assign unusedPeopleId = "${unused.applicant.id}" />
				  <#elseif loginUser?exists>
				  <#assign unusedPeopleId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="applicant.id" value="${unusedPeopleId}" />
				<@pp.datePicker label="'${action.getText('unusedOccurDateTime')}'" name="'unused.unUseDate'"
	     							value="'${(unused.unUseDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" maxlength="10" required="true"/>
		    </tr>
    	     
		   </tr>
		   <tr>
		      <@ww.textarea  label="'${action.getText('unused.reason')}'" 
	        	         name="'unused.reason'" 
	        	         value="'${unused.reason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
	        	    <@ww.textarea  label="'${action.getText('unused.comment')}'" 
	        	         name="'unused.comment'" 
	        	         value="'${unused.comment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />     
		   </tr>
            <#if unused.id? exists>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
             	 <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('unused')}:</label></td>
            	<td>
		 			<input type="checkbox" name="onlyDisapprove" value="UNUSED"/>${action.getText('pizhun')}
		 		</td>
		 	 </tr>
		 	 <tr>
		 	   <#assign unusedpizhunPeopleName = ''/>
					<#if unused.unUsedApr?exists>
					 <#assign unusedpizhunPeopleName = "${unused.unUsedApr.name}" />
					  <#elseif loginUser?exists>
					   <#assign unusedpizhunPeopleName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('pizhun.people')}:</label></td>
	        	<td>
	        		<input type="text" name="unUsedApr.name" 
	        			class="underline"  value="${unusedpizhunPeopleName}"  maxlength="140" size="20" disabled="true"/>
	        		<label id=""></label>
		    		
		    		    <a name="unusedUserImg" onClick="unusedUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign unusedpizhunPeopleId = ''/>
				<#if unused.unUsedApr?exists>
				 <#assign unusedpizhunPeopleId = "${unused.unUsedApr.id}" />
				   <#elseif loginUser?exists>
				    <#assign unusedpizhunPeopleId = "${loginUser.id}" />
				   
				</#if>
				<input type="hidden" name="unUsedApr.id" value="${unusedpizhunPeopleId}" />
				 <@pp.datePicker label="'${action.getText('unusedpizhunOccurDateTime')}'" name="'unused.unUsedAprDate'"
	     							value="'${(unused.unUsedAprDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" maxlength="10" required="true"/>
		    </tr>
		   <tr>
		     <@ww.textarea  label="'${action.getText('unused.devise')}'" 
	        	         name="'unused.devise'" 
	        	         value="'${unused.devise?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		   </tr>
		   <#if unused.status=='UNUSED' || unused.isUsedAduit ==true>
		    <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
             	 <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('isUesd')}:</label></td>
            	<td>
		 			<input type="checkbox" name="onlyisUsed" value="NORMAL"/>${action.getText('pizhun')}
		 		</td>
		 	 </tr>
		 	 	 	 <tr>
		 	   <#assign unusedisUsedPeopleName = ''/>
					<#if unused.usedApr?exists>
					 <#assign unusedisUsedPeopleName = "${unused.usedApr.name}" />
					  <#elseif loginUser?exists>
					  <#assign unusedisUsedPeopleName = "${loginUser.name}" />
					</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('unused.user')}:</label></td>
	        	<td>
	        		<input type="text" name="usedApr.name" 
	        			class="underline"  value="${unusedisUsedPeopleName}"  maxlength="140" maxlength="10" disabled="true"/>
	        		<label id=""></label>
		    		
		    		    <a name="IsUsePeople" onClick="unusedUesdUser_OpenDialog();">
		        		    <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	    </a>
		        </td>
		        <#assign unusedpiUsedPeopleId = ''/>
				<#if unused.usedApr?exists>
				 <#assign unusedpiUsedPeopleId = "${unused.usedApr.id}" />
				  <#elseif loginUser?exists>
				   <#assign unusedpiUsedPeopleId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="usedApr.id" value="${unusedpiUsedPeopleId}" />
				 <@pp.datePicker label="'${action.getText('unusedisUsedOccurDateTime')}'" name="'unused.usedAprDate'"
	     							value="'${(unused.usedAprDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
		        
		    </tr>
		   <tr>
		     <@ww.textarea  label="'${action.getText('used.reason')}'" 
	        	         name="'unused.usedreason'" 
	        	         value="'${unused.usedreason?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" />
		   </tr>
		   </#if>
            </#if>   
        </@inputTable>
        <@buttonBar>
          <#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick= "'return unused_Vlidate();'"/>
           </#if>
            <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/unused/listunused.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
        </@buttonBar>
    </@ww.form>
  <script language="JavaScript" type="text/JavaScript">
    window.onload = function() {
	     a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["unused.unUseDate"].value=time;
		 <#if unused.id?exists>
		 a = new Date();
		 var time=a.format("yyyy-MM-dd");
		 document.forms[0].elements["unused.unUsedAprDate"].value=time;
		 </#if>
		<#if unused.status=="NORMAL" || unused.isUsedAduit == true>
		    document.forms[0].elements["onlyDisapprove"].checked="true";
		    document.forms[0].elements["onlyisUsed"].checked= "true";
		   // document.forms[0].elements["deviceState"].value='DEVICE_NORMAL';//如果闲置单状态为正常，那么设备状态也为正常
		    __disableAllElements__(document.forms[0], new Array("save", "back","onlyisUsed", "IsUsePeople", "unused.usedAprDate","unused.usedreason"));
		    disableCalendars(document.forms[0], new Array("unused.unUseDate", "unused.unUsedAprDate"), true);
		    document.forms[0].elements["onlyDisapprove"].disabled=true;
		 <#elseif unused.status=='UNUSED' || unused.isUnUsedAduit == true> 
		   a = new Date();
		   var time=a.format("yyyy-MM-dd");
		   document.forms[0].elements["unused.usedAprDate"].value=time;
		   document.forms[0].elements["onlyDisapprove"].checked="true";
		  // document.forms[0].elements["deviceState"].value = 'DEVICE_UNUSED'  //DEVICE_UNUSED为设备状态为"闲置"
		   __disableAllElements__(document.forms[0], new Array("save", "back", "onlyDisapprove", "unusedUserImg", "unused.unUsedAprDate","unused.devise","onlyisUsed", "IsUsePeople", "unused.usedAprDate"));
		   disableCalendars(document.forms[0], new Array("unused.unUseDate"), true);
		
		 </#if>
		
		 
     }
     function unusedUser_OpenDialog(){
              var url = "${req.contextPath}/popup/userSelector.html";
		      popupModalDialog(url, 800, 600,unusedUserSelectorHandler);
		   }
		 function unusedUserSelectorHandler(result) {
		    if (null != result) {
		      document.forms[0].elements["unUsedApr.name"].value=result[1];
		      document.forms[0].elements["unUsedApr.id"].value=result[0];
		    }          
     }
     function receiveUser_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600,managerSelectorHandler);
		  }
		 function managerSelectorHandler(result) {
		    if (null != result) {
		      document.forms[0].elements["applicant.name"].value=result[1];
		      document.forms[0].elements["applicant.id"].value=result[0];
		    }             	
		  }
	function unusedUesdUser_OpenDialog(){
		     var url = "${req.contextPath}/popup/userSelector.html";
		     popupModalDialog(url, 800, 600,UseSelectorHandler);
		  }
		  function UseSelectorHandler(result) {
		    if (null != result) {
		      document.forms[0].elements["usedApr.name"].value=result[1];
		      document.forms[0].elements["usedApr.id"].value=result[0];
		    }             	
		  }
   function unused_Vlidate(){
	    if(document.getElementById("unused.name").value==''){
	        alert('${action.getText('unused_name_not_null')}');
	        return false;
	     }else{
	         if(!isValidLength(document.forms[0], "unused.name", null, 50)){
				alert("${action.getText('unused.name.length')}");
				return  false;
			  }
	     }
	<#if toolingDevFlag?exists>
	     <#if toolingDevFlag=='DEVICE'>
	        if(document.getElementById("device.deviceNo").value==''){
	        alert('${action.getText('asset.deviceNo_not_null')}');
	        return false;
	     }
	     <#else>
	       if(document.getElementById("tooling.deviceNo").value==''){
	        alert('${action.getText('asset.tooling.not.null')}');
	        return false;
	     }
	    </#if>
   </#if>
	  if(document.getElementById("unused.unUseDate").value==''){       //验证闲置日期及格式
	        alert('${action.getText('unused.unUseDate_not_null')}');
	        return false;
	      }
	    var date=document.getElementById("unused.unUseDate").value;
		if(!isDate("unused.unUseDate")){
		    alert("${action.getText('select.right.unused.unUseDate')}");
			return false;
		  }
		if(!isDateLessEqualThenCurrent(date)){
			alert("${action.getText('afresh.unused.unUseDate')}");
		    return false;
		  }
	      if(document.getElementById("applicant.name").value==''){
	        alert('${action.getText('applicant.name.not.null')}');
	        return false;
	    }
		  if(document.getElementById("unused.reason").value!=''){
			 if(!isValidLength(document.forms[0], "unused.reason", null, 250)){
				 alert("${action.getText('unused.reason.length')}");
				 return  false;
				 }
		}
	     if(document.getElementById("unused.comment").value!=''){
			if(!isValidLength(document.forms[0], "unused.comment", null, 250)){
				alert("${action.getText('unused.comment.length')}");
				return  false;
				}
	   }
	/*
	*  闲置验证   
	*/   
	<#if unused.id?exists>
	  if(document.getElementById("onlyDisapprove").checked==true){ 
	        if(document.getElementById("unUsedApr.name").value==''){
	           alert('${action.getText('unUsedApr.name_not_null')}');
	           return false;
	       }
	     }
	  if(document.getElementById("unused.unUsedAprDate").value==''){
	        alert('${action.getText('unused.unUsedAprDate_not_null')}');
	        return false;
	      }
	    var date=document.getElementById("unused.unUsedAprDate").value;
	  if(!isDate("unused.unUsedAprDate")){
		       alert("${action.getText('select.right.unused.unUsedAprDate')}");
			   return false;
		  }
	  if(!isDateLessEqualThenCurrent(date)){
			    alert("${action.getText('afresh.unused.unUsedAprDate')}");
				return false;
		   }
	  if(document.getElementById("unused.devise").value!=''){
			if(!isValidLength(document.forms[0], "unused.devise", null, 250)){
				alert("${action.getText('unused.devise.length')}");
				return  false;
			}
		  }
	
	</#if> 
		/*
		 *  闲置单在启用时的验证
		 */ 
	<#if unused.id?exists &&unused.status=="NORMAL">
		 if(document.getElementById("onlyisUsed").checked==true){
		  if(document.getElementById("usedApr.name").value==''){
	        alert('${action.getText('usedApr.name_not_null')}');
	        return false;
	      }
	     }
	     if(document.getElementById("unused.usedAprDate").value==''){
	        alert('${action.getText('unused.usedAprDate_not_null')}');
	        return false;
	      }
	   
		 if(!isDate("unused.usedAprDate")){
		       alert("${action.getText('select.right.unused.usedAprDate')}");
			   return false;
		  }
		 var date=document.getElementById("unused.usedAprDate").value;
		 if(!isDateLessEqualThenCurrent(date)){
			    alert("${action.getText('afresh.unused.usedAprDate')}");
				return false;
		   }
		 if(document.getElementById("unused.usedreason").value!=''){
		   if(!isValidLength(document.forms[0], "unused.usedreason", null, 250)){
			   alert("${action.getText('unused.usedreason.length')}");
			   return  false;
			}
		 }
		</#if>
		return true;
	 }
  	  </script>
    </@htmlPage>