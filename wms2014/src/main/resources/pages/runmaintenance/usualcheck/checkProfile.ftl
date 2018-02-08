<#include "../../includes/eam2008.ftl" />

<#assign checkEditTitle = ''/>
<#if toolingDevFlag?exists>
  <#if toolingDevFlag == 'DEVICE'>
    <#assign checkEditTitle = "${action.getText('deviceCheck.edit')}"/>
  <#else>
    <#assign checkEditTitle = "${action.getText('toolingCheck.edit')}"/>
  </#if>
</#if>
<@htmlPage title="${checkEditTitle}">
  <@ww.form namespace="'/runmaintenance/usualcheck'" name="'check'" action="'saveCheck'" method="'post'" validate="true">
    <@ww.token name="saveCheckToken"/>
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
      <@inputTable>
        <#if check.id?exists>
          <@ww.hidden name="'check.id'" value="#{check.id?if_exists}"/>
        </#if>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
        <tr>
          <@ww.textfield label="'${action.getText('usualcheck.billNo')}'" name="'check.billNo'"   value="'${check.billNo?if_exists}'" cssClass="'underline'"  disabled="true" readonly="true"/>
          <@ww.textfield label="'${action.getText('usualcheck.name')}'" name="'check.name'"   value="'${check.name?if_exists}'" cssClass="'underline'"  required="true"/>
        </tr>
        <#if toolingDevFlag?exists>
          <#if toolingDevFlag == 'DEVICE'>
            <@eam2008_DeviceSelector flag="DeviceCheck" required="false" select="true"/>
          <#else>
            <@eam2008_ToolingSelector flag="ToolingCheck" required="false"/>
            <#assign toolingCategory = ''/>
            <#assign toolingDepartment = ''/>
            <#if check?exists>
              <#if check.asset?exists>
                <#if check.asset.toolingType?exists>
                  <#assign toolingCategory = '${check.asset.toolingType.value?if_exists}'/>
                </#if>
                <#if check.asset?exists>
                <#if check.asset.department?exists>
                  <#assign toolingDepartment = '${check.asset.department.name?if_exists}'/>
                </#if>
                </#if>
              </#if>
            </#if>
            <tr>
              <@ww.textfield label="'${action.getText('tooling.toolingName')}'" name="'check.toolingName'"   value="'${check.toolingName?if_exists}'" cssClass="'underline'"/>
              <@ww.textfield label="'${action.getText('tooling.category')}'" name="'tooling.toolingType'"   value="'${toolingCategory}'" cssClass="'underline'"  disabled="true" readonly="true"/>
            </tr>
          </#if>
        </#if>
        <tr>
        <#if toolingDevFlag=='DEVICE'>
        <@ww.textfield label="'${action.getText('tooling.toolingName')}'" name="'check.toolingName'"   value="'${check.toolingName?if_exists}'" cssClass="'underline'"/>
        </#if>
        
       <#-- <@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'"   value="'${toolingDepartment}'" cssClass="'underline'"  disabled="true" readonly="true"/>-->
          <#assign checkerName = ''/>
		  <#if check.checker?exists>
		    <#assign checkerName = "${check.checker}" />
		  <#elseif loginUser?exists>
		    <#assign checkerName = "${loginUser.name}" />
		  </#if>
	      <td align="right" valign="top">
	        <span class="required">*</span>
	        <label class="label">${action.getText('checker')}:</label>
	      </td>
	      <td>
	        <input type="text" name="check.checker" 
	        	   class="underline"  value="${checkerName}"  maxlength="140" size="20" disabled="true"/>
	        <label id=""></label>
            <a onClick="checker_OpenDialog();">
		      <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	        </a>
		  </td>
		  <input type="hidden" name="checker.name" value="${checkerName}" />
		  <input type="hidden" name="checker.id" value="" />
        </tr>  
        <tr>
        	<#if toolingDevFlag=='TOOLING'>
	        	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		   listKey="id" listValue="name"
		               list="departments" emptyOption="true" disabled="false"   required="true">
		    	</@ww.select>
	    	</#if>
        	<@pp.datePicker label="'${action.getText('checkDate')}'" name="'check.checkDate'"
	     				  value="'${(check.checkDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" 
	     				  required="true" maxlength="10"/>
          
	     
        </tr>
        <tr>
            <@ww.textarea  label="'${action.getText('checkResult')}'" 
	        	         name="'check.result'" 
	        	         value="'${check.result?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false" required="true"/>
            <@ww.textarea  label="'${action.getText('checkHandleResult')}'" 
	        	         name="'check.handle'" 
	        	         value="'${check.handle?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false"/>
        </tr>
        <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
             	 <tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('status')}:</label></td>
            	<td>
		 			<input type="checkbox" name="status" onclick="return usualCheckStatus() "><#--${action.getText('status')}-->
		 		</td>
		         <@ww.select label="'${action.getText('checkStatus')}'"  name="'checkStatus'" 
	    			     listKey="value" listValue="label" value="'${check.status?if_exists}'"
	                     list="results"
	                     required="false" emptyOption="true" disabled="true">
	    		</@ww.select>  	
	    		<@ww.hidden name="'check.status'" value="'${check.status?if_exists}'"/>
	    		<@ww.hidden name="'checkBox.status'" value="'${check.unEnrol?string}'"/>	
	  </tr>	
      </@inputTable>
      <@buttonBar>
      <#if !(action.isReadOnly())>
	    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	   </#if>
	    <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/usualcheck/listChecks.html?toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}"/>
	  </@buttonBar>
    </@ww.form>
    <script language="JavaScript" type="text/JavaScript">
      window.onload = function() {
     
      <#if toolingDevFlag=='TOOLING'>
      	<#if check.department?exists>
	     	getObjByNameRe("department.id").value = #{check.department.id};
	   <#elseif loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>
      </#if>
      <#if toolingDevFlag=='DEVICE'>
      	<#if check.department?exists>
      		<#if check.asset?exists>
      		<#else>
	     	  getObjByNameRe("department.id").value = #{check.department.id};
	     	</#if>
	   <#elseif loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>
	  </#if>
        <#if check.status?exists>
         <#if check.unEnrol>
           document.forms["check"].elements["status"].checked=true;
           document.forms["check"].elements["checkStatus"].value ='${check.status}';
         </#if> 
          if(document.forms["check"].elements["checkStatus"].value=='Enrol'){
            disableElements(document.forms[0],new Array("checkStatus","status"),true );
         }
        </#if>
		<#if check.id?exists>
		<#else>
		  a = new Date();
		  var time=a.format("yyyy-MM-dd");
		  document.forms[0].elements["check.checkDate"].value=time;
		</#if>
		 <#if check.id?exists>
		   var url = '${req.contextPath}/runmaintenance/usualcheck/listUsualCheckBill.html?check.id=#{check.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}';
	          document.all.frame.src = url;
		      getObjByNameRe("details").className = "selectedtab";
	    </#if>
	     
	  }
	function usualCheckStatus(){
	 if(document.forms["check"].elements["status"].checked==true){
	  document.forms["check"].elements["checkStatus"].value ='unEnrol';
	  document.forms["check"].elements["check.status"].value ='unEnrol';
	  document.forms["check"].elements["checkBox.status"].value="true";
	  
	 
	 }else{
	  document.forms["check"].elements["checkStatus"].value ='';
	  document.forms["check"].elements["check.status"].value='';
	  document.forms["check"].elements["checkBox.status"].value="false";
	 }
	}
	
      /*
       * 检查人选择
      */
      function checker_OpenDialog() {
        var url = "${req.contextPath}/popup/userSelector.html";
    	popupModalDialog(url, 800, 600, checkerSelectorHandler);
      }
      function checkerSelectorHandler(result) {
        if (null != result) {
          document.forms["check"].elements["checker.id"].value = result[0];
    	  document.forms["check"].elements["check.checker"].value = result[1];
          document.forms["check"].elements["checker.name"].value = result[1];
        }
      }
      function validate() {
       if (document.forms["check"].elements["check.name"].value == '') {
	      alert("${action.getText('select.check.name')}");
	      return false;
	    } else if (!isValidLength(document.forms[0],"check.name",0,50)){
	      alert("${action.getText('check.name.maxLength')}");
	      return false;
	    }
       <#-- <#if toolingDevFlag?exists>
          <#if toolingDevFlag == 'DEVICE'>
            if(!eam2008_device_validate("${action.getText('select.device.no')}")){
	          return false;
	        }
	      <#else>
	        if(!eam2008_tooling_validate("${action.getText('select.tooling.no')}")){
	          return false;
	        }
	      </#if>
	    </#if>-->
	    if (document.forms["check"].elements["check.toolingName"].value !='') {
	      
	    if (!isValidLength(document.forms[0],"check.toolingName",0,50)){
	      alert("${action.getText('check.toolingName.maxLength')}");
	      return false;
	      }
	    }
	    if ('' == document.forms[0].elements["checker.name"].value) {
	      alert("${action.getText('slect.checker')}");
	      return false;
	    }
	    if (!validateTime_checkDate()) {
	      return false;
	    }
	    if (document.forms["check"].elements["check.result"].value == '') {
	      alert("${action.getText('select.check.result')}");
	      return false;
	    } else if (!isValidLength(document.forms[0],"check.result",0,250)){
	      alert("${action.getText('check.result.maxLength')}");
	      return false;
	    }
	    if (document.forms["check"].elements["check.handle"].value != '') {
	      if (!isValidLength(document.forms[0],"check.handle",0,250)) {
	        alert("${action.getText('check.handle.maxLength')}");
	        return false;
	      }
	    }
	    return true;
      }
      /*
       * 验证检查时间的必须输入项，和格式
      */
      function validateTime_checkDate(){
		if(document.forms["check"].elements["check.checkDate"].value ==""){
			alert("${action.getText('select.checkDate')}");
			return false;
		}
		if(!isDate("check.checkDate")){
			alert("${action.getText('select.right.checkDate')}");
			return false;
		}
		return true;
	  }
    </script>
    <#if check.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="${req.contextPath}/runmaintenance/usualcheck/listUsualCheckBill.html?check.id=#{check.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('technical')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	</#if>
</@htmlPage>