<#-- $Id: discardProfile.ftl 8911 2007-12-02 09:30:05Z wzou $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingDiscardEdit.title')}">
    <@ww.form namespace="'/runmaintenance/discard'" name="'discard'" action="'saveToolingDiscard'" method="'post'" validate="true">
        <@ww.token name="saveDiscardToken"/>
        <@ww.hidden name="'toolingDevFlag'" value="${(bTooling?string)?if_exists}"/>
         <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
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
        	<@eam2008_ToolingSelector flag="ToolingDiscard"/>
        	<tr>
            	<#assign toolingDepartment = ''/>
            	<#if discard.tooling?exists>
					<#if discard.tooling.department?exists>
					 	<#assign toolingDepartment = "${discard.tooling.department.name?if_exists}" />
					</#if>
				</#if>
				<#assign toolingType = ''/>
            	<#if discard.tooling?exists>
					<#if discard.tooling.toolingType?exists>
					 	<#assign toolingType = "${discard.tooling.toolingType.value?if_exists}" />
					</#if>
				</#if>
            	<@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'" value="${toolingDepartment}" cssClass="'underline'"  disabled="true"/>
            	<@ww.textfield label="'${action.getText('tooling.toolingType')}'" name="'tooling.toolingType'" value="${toolingType}" cssClass="'underline'"  disabled="true"/>
            </tr>
        	<#assign totalOutput = ''/>
				<#if discard.tooling?exists>
				 	<#assign totalOutput = "${discard.tooling.totalOutput?if_exists}" />
				</#if>
			<#assign usedQuota = ''/>
				<#if discard.tooling?exists>
				 <#assign usedQuota = "${discard.tooling.usedQuota?if_exists}" />
				</#if>
        	<tr>
        		<@ww.textfield label="'${action.getText('totalOutput')}'" name="'tooling.totalOutput'" value="${totalOutput}" cssClass="'underline'"  disabled="true"/>
	 	  	  	<@ww.textfield label="'${action.getText('usedQuota')}'" name="'tooling.usedQuota'" value="${usedQuota}" cssClass="'underline'"  disabled="true"/>
        	</tr>
            <tr>
        		<@ww.textfield label="'${action.getText('value')}'" name="'discard.value'" value="'${(discard.value?string('#,##0.00'))?if_exists}'" cssClass="'underline'"  />
	 	  	  	<@ww.textfield label="'${action.getText('status')}'" name="'discard.status'" value="'${discard.status?if_exists}'" cssClass="'underline'"  />
        	</tr>
            <tr>	
        		<@ww.select label="'${action.getText('apply.department')}'" required="false" name="'department.id'" 
		    			 listKey="id" listValue="name"
		                list="departments" emptyOption="true" disabled="false" required="true">
		    	</@ww.select>
	        	<#assign managerName = ''/>
					<#if discard.manager?exists>
					 <#assign managerName = "${discard.manager.name}" />
					<#elseif loginUser?exists>
					 <#assign managerName = "${loginUser.name}" />
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
				<#elseif loginUser?exists>
				   <#assign managerId = "${loginUser.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" />
            </tr>
            <tr>
            	<@pp.datePicker label="'${action.getText('discard.applyDatetime')}'" name="'discard.applyDatetime'"

	     							value="'${(discard.applyDatetime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" maxlength="10" required="true"/>	
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

	     							value="'${(discard.descardDatetime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" maxlength="10" required="true"/>


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
          
          <#if !(action.isReadOnly())>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	      </#if>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/discard/listToolingDiscards.html?&toolingDevFlag=true&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	      <#if discard.id?exists>
	      	<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_discardPdf('#{discard.id}')"/>
	      	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_discardXls('#{discard.id}')"/>
	      </#if>	
	    </@buttonBar>
		<script language="JavaScript" type="text/JavaScript">
	       window.onload = function () {
	       <#if loginUser.department?exists>
	        document.getElementById("department.id").value = #{loginUser.department.id};
	       </#if>
       		var discardFlag = ${discard.discardFlag?string};
       		if(discardFlag){
       			document.forms["discard"].elements["discardAgree"].checked = true;
	       		document.getElementById("descardDatetime").style.display='inline';	
	        }
	        <#if (tooling.id)?exists>
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
		     if(result!=null){
		        document.forms[0].elements["manager.name"].value = result[1];
		        document.forms[0].elements["manager.id"].value = result[0];
		     }
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
		       		<#if !(action.isReadOnly())>
		       		document.forms["discard"].elements["save"].disabled=false;
		       		</#if>
			    	document.getElementById("descardDatetime").style.display='none';
			    	document.forms["discard"].elements["discard.descardDatetime"].value='';
		    	<#if bTooling>
		      		document.forms["discard"].elements["toolingState"].value = 'TOOLING_NORMAL';
		        <#else>
		      		document.forms["discard"].elements["deviceState"].value = 'DEVICE_NORMAL';
		        </#if>
		    	}
		    }
	 function validateDepartment() {
       var dept = document.forms[0].elements["department.id"].value;
       if (dept =='' || dept == '-1') {
         alert("${action.getText('department.id.requried')}");
         return false;
       }
      return true;
     }	  
	function validate(){
		   if(document.getElementById("discard.name").value=='' ){
            alert('${action.getText('discard.name.not.null')}');
           return false;
          }else{
		     if(!isValidLength(document.forms[0], "discard.name", null, 50)){
				alert("${action.getText('discard.name.length')}");
				return  false;
			   }
       }
		
		  	if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		      return false;
		    }
			if (!discard_value()){
				return false;
			}
			if(document.getElementById("discard.status").value!=''){
			 if(!isValidLength(document.forms[0], "discard.status", null, 50)){
				alert("${action.getText('toolingDiscard.status.length')}");
				return  false;
			   }
			}
		   <#-- if (document.forms["discard"].elements["department.id"].value == '') {
		      alert("${action.getText('select.department.name')}");
		      return false;
		    }-->
		  if(!validateDepartment()){
             return false;
          }
		    if (document.forms["discard"].elements["manager.name"].value == '') {
		      alert("${action.getText('select.manager.name')}");
		      return false;
		    }
		  	<#--if(!discard_status()){  
		  		return false;
		  	}-->
		  	 
		    
               
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
		  	return true;
		  }
		  function discard_name(){
			  var name=document.forms["discard"].elements["discard.name"].value;
				if(!(name=='')){
				  	if(!isValidLength(document.forms[0], "discard.name", null, 50)){
				  		alert("${action.getText('discard.name.length')}");
				  		return  false;
				  	}
			  	}
				 //return true;
			}
		
		    
		  		
		  		<#--var str = document.forms["discard"].elements["discard.value"].value;
		  		if(!(str=='')){
		  			if(!isInteger(document.forms[0],"discard.value")){
		  				alert("${action.getText('discard.error.value')}");
		  				return false;
		  			}else if(!isIntegerLength(document.forms[0],"discard.value",null,10)){
		  				alert("${action.getText('discard.value.length')}");
		  				return false;
		  			}	
		  		}-->
	function discard_value(){
		 if(document.getElementById("discard.value").value!=''){
		
		  if (!isDoubleNumber("discard.value")){
		
		    alert("${action.getText('tooling.unitPrice')}");
		       return false;
	       } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["discard.value"].value, 10000000001, 0)){  //验证范围
		     alert("${action.getText('tooling.unitPrice.maxLength')}");
		      return false;
	      }
	      
	    }
	    return true;
  }
		  function discard_status(){
		  	var name=document.forms["discard"].elements["discard.status"].value;
				if(!(name=='')){
					if(!isValidLength(document.forms[0], "discard.status", null, 50)){
				  		alert("${action.getText('toolingDiscard.status.length')}");
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
				<#--
				if(!isDateLessEqualThenCurrent(date)){
					alert("${action.getText('afresh.discard.applyDatetime')}");
					return false;
				}
				-->
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
				<#--
				if(!isDateLessThenOldDate(date,dateApply)){
					alert("${action.getText('afresh.discard.descardDatetime')}");
					return false;
				}
				-->
				return true;
			}
			function open_discardPdf(id) {
				var discardId=id;
				var url='${req.contextPath}/reports/tooling/toolingDiscard.pdf?discardId='+discardId;
	      		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
	      	function open_discardXls(id) {
				var discardId=id;
				var url='${req.contextPath}/reports/tooling/toolingDiscard.xls?discardId='+discardId;
	      		window.open(url,"_new","toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
	      	}
		</script>

    </@ww.form>
</@htmlPage>