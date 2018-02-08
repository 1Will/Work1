<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('toolingAccidentBillEdit.title')}">
    <@ww.form namespace="'/runmaintenance/accident'" name="'accidentBill'" action="'saveToolingAccidentBill'" method="'post'" validate="true">
        <@ww.token name="saveaccidentBillToken"/>
        <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
        <#if accidentBill.id?exists>
          <@ww.hidden name="'accidentBill.id'" value="#{accidentBill.id}"/>
        </#if>
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'${action.getText('accidentBillNo')}'" name="'accidentBill.billNo'" value="'${accidentBill.billNo?if_exists}'" cssClass="'underline'" disabled="true" readonly="true"  required="false" />
	 	  	  	<@ww.textfield label="'${action.getText('accidentBillName')}'" name="'accidentBill.billName'" value="'${accidentBill.billName?if_exists}'" cssClass="'underline'" required="true" />
        	</tr>
        	<@eam2008_ToolingSelector/>
	         <tr>
	            <@ww.textfield label="'${action.getText('department')}'" name="'tooling.department'" value="''" cssClass="'underline'" required="false" disabled="true" readonly="true"/>
	            <@pp.datePicker label="'${action.getText('accidentOccurDateTime')}'" name="'accidentBill.accidentOccurDateTm'"
	     							value="'${(accidentBill.accidentOccurDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
            </tr>
            <tr>
	        	<#assign managerName = ''/>
				<#if accidentBill.manager?exists>
				 <#assign managerName = "${accidentBill.manager.name}" />
				</#if>
	        	<td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('tooling.accidentManager')}:</label></td>
	        	<td>
	        		<input type="text" name="manager.name" 
	        			class="underline"  value="${managerName}"  maxlength="150" size="20" disabled="true"/>
	        		<label id=""></label>
		    		<a onClick="manager_OpenDialog();">
		        		<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
		        	</a>
		        </td>
		        <#assign managerId = ''/>
				<#if accidentBill.manager?exists>
				 	<#assign managerId = "${accidentBill.manager.id}" />
				</#if>
				<input type="hidden" name="manager.id" value="${managerId}" />
            </tr>
             <tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
             <tr>
	        	<@ww.textarea  label="'${action.getText('accidentDetailContent')}'" 
	        	         name="'accidentBill.accidentDetailContent'" 
	        	         value="'${accidentBill.accidentDetailContent?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false" required="true"/>
	        	<@ww.textarea  label="'${action.getText('accidentSolution')}'" 
	        	         name="'accidentBill.accidentSolution'" 
	        	         value="'${accidentBill.accidentSolution?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'" disabled="false"/>
			 </tr>
        </@inputTable>
        <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/accident/listToolingAccidentBills.html"/>
	    </@buttonBar>
	 </@ww.form>
		<script language="JavaScript" type="text/JavaScript">
		  window.onload = function() {
		    <#if accidentBill.toolingDev?exists>
		      <#if (accidentBill.toolingDev.department)?exists>
		        document.forms[0].elements["tooling.department"].value = '${(accidentBill.toolingDev.department.name)?if_exists}';
		      </#if>
		    </#if>
		    <#if (tooling.id)?exists>
	       	<#else>
		       	a = new Date();
				var time=a.format("yyyy-MM-dd");
				document.forms["accidentBill"].elements["accidentBill.accidentOccurDateTm"].value=time;
			</#if>
		  }
	    </script>
	    <script language="JavaScript" type="text/JavaScript">
	      function customize_validate() {
	        if (!eam2008_tooling_validate("${action.getText('select.tooling.no')}")) {
		      return false;
		    }
		    if (isEmpty(document.forms[0], "accidentBill.accidentOccurDateTm")) {
		      alert("${action.getText("select.accidentOccurDateTm")}");
		      return false;
		    } else {
		      if (!accidentOccurDateTmValidate()) {
		        return false;
		      }
		    }
		    if (isEmpty(document.forms[0], "manager.id")) {
		      alert("${action.getText("select.manager")}");
		      return false;
		    }
		    if (isNotEmpty(document.forms[0], "accidentBill.accidentDetailContent")) {
		      if (!isValidLength(document.forms[0], new Array("accidentBill.accidentDetailContent"), 0, 250)) {
		        alert("${action.getText("accidentDetailContent.length.overflow")}");
		        return false;
		      }
		    }
		    if (isNotEmpty(document.forms[0], "accidentBill.accidentSolution")) {
		      if (!isValidLength(document.forms[0], new Array("accidentBill.accidentSolution"), 0, 250)) {
		        alert("${action.getText("accidentSolution.length.overflow")}");
		        return false;
		      }
		    }
	        return true;
	      }
		  function manager_OpenDialog() {
		    var url = "${req.contextPath}/popup/userSelector.html";
		    popupModalDialog(url, 800, 600, managerSelectorHandler);
		  }
		  function managerSelectorHandler(result) {
		    document.forms["accidentBill"].elements["manager.id"].value = result[0];
		    document.forms["accidentBill"].elements["manager.name"].value = result[1];
		  }
		  function accidentOccurDateTmValidate() {
	        if (!isDate("accidentBill.accidentOccurDateTm")) {
	          alert("${action.getText('accidentOccurDateTime')}" + "," +"${action.getText('dateFormate.error')}");
	          return false;
	        }
	        var date = document.forms["accidentBill"].elements["accidentBill.accidentOccurDateTm"].value;
	        if (isDateLessThenCurrent(date)) {
	          alert("${action.getText('accidentOccurDateTm.later.error')}");
	          return false;
	        }
		    return true;
		  }
		</script>

</@htmlPage>