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

<@framePage title="${action.getText('attachTool.title')}">
  <@ww.form name="'toolingExtInfo'" action="'saveToolingExtInfo'" method="'post'" validate="true">
  <@ww.token name="saveToolingExtInfoToken"/>
  <#if tooling.id?exists>
    <@ww.hidden name="'tooling.id'" value="#{tooling.id}"/>
  </#if>
   <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
   <@ww.hidden name="'eamAuthentication'" value="'${req.getParameter('eamAuthentication')?if_exists}'"/>
    <@inputTable>
     <tr>
	 <#--<#assign desigerName = ''/>
	 <#if tooling.toolingDesigner?exists>
	   <#assign desigerName = "${tooling.toolingDesigner.name}" />
	 </#if>
	   <td align="right" valign="top"><label class="label">${action.getText('designer')}:</label></td>
	   <td>
	     <input type="text" name="desiger.name" 
	            class="underline"  value="${desigerName}"  maxlength="150" size="20" disabled="true"/>
	     <label id=""></label>
	     <a onClick="desiger_OpenDialog();">
	       <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	     </a>
	   </td>
	 <#assign desigerId = ''/>
	 <#if tooling.toolingDesigner?exists>
	   <#assign desigerId = "${tooling.toolingDesigner.id}" />
	 </#if>
	 <input type="hidden" name="desiger.id" value="${desigerId}" />-->
	   <@ww.textfield label="'${action.getText('designer')}'" name="'tooling.stringToolingDesigner'" value="'${tooling.stringToolingDesigner?if_exists}'"  required="false" cssClass="'underline'" />
	   <@pp.datePicker label="'${action.getText('designeDate')}'" name="'tooling.designTime'"
	     			   value="'${(tooling.designTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	 </tr>
	 <tr>	
	 <#assign madeInName = ''/>
	 <#if tooling.supplier?exists>
	   <#assign madeInName = "${tooling.supplier.name}" />
	 </#if>
	   <td align="right" valign="top"><label class="label">${action.getText('supplier')}:</label></td>
	   <td>
	     <input type="text" name="supplier.name" class="underline"  value="${madeInName}"  maxlength="150" disabled="true"/>
		 <a onClick='supplier_OpenDialog();'>
	 	   <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 	 </a>
	   </td>
	 <#assign madeInId = ''/>
	 <#if tooling.supplier?exists>
	   <#assign madeInId = "${tooling.supplier.id}" />
	 </#if>
	   <@ww.hidden name="'supplier.id'" value="'${madeInId}'"/>
	   <@ww.textfield readonly="false" label="'${action.getText('maker')}'" name="'tooling.maker'" value="'${tooling.maker?if_exists}'"  cssClass="'underline'"/> 
	 </tr>
	 <tr>
	   <@pp.datePicker label="'${action.getText('madeDate')}'" name="'tooling.madeTime'"
	     			   value="'${(tooling.madeTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	      
	   <@pp.datePicker label="'${action.getText('completeDate')}'" name="'tooling.completeTime'"
	     			   value="'${(tooling.completeTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	 </tr>
	 <tr>
	 	<@ww.textfield readonly="false" label="'${action.getText('tooling.mouldParam')}'" name="'tooling.mouldParam'" value="'${tooling.mouldParam?if_exists}'"  cssClass="'underline'"/> 
	 	<@ww.textfield readonly="false" label="'${action.getText('putNo')}'" name="'tooling.putNo'" value="'${tooling.putNo?if_exists}'"  cssClass="'underline'"/> 
	 </tr>	
	 <tr>
	   <@ww.textfield readonly="false" label="'${action.getText('tooling.mouldWeight')}'" name="'tooling.mouldWeight'" value="'${tooling.mouldWeight?if_exists}'"  cssClass="'underline'"/> 
	 </tr>
	 <#assign checkerName = ''/>
	 <#if tooling.checker?exists>
	   <#assign checkerName = "${tooling.checker.name}" />
	 </#if>
	 <td align="right" valign="top"><label class="label">${action.getText('checker')}:</label></td>
	 <td>
	   <input type="text" name="checker.name" 
	          class="underline"  value="${checkerName}"  maxlength="150" size="20" disabled="true"/>
	   <label id=""></label>
	   <a onClick="checker_OpenDialog();">
	     <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	   </a>
	 </td>
	 <#assign checkerId = ''/>
	 <#if tooling.checker?exists>
	   <#assign checkerId = "${tooling.checker.id}" />
	 </#if>
	   <input type="hidden" name="checker.id" value="${checkerId}" />
	   <@pp.datePicker label="'${action.getText('checkeDate')}'" name="'tooling.checkTime'"
	     			   value="'${(tooling.checkTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	 </tr>
	 <tr>
	   <@ww.textarea  label="'${action.getText('checkerMind')}'" name="'tooling.suggestion'" value="'${tooling.suggestion?if_exists}'"  rows="3" cols="40" cssClass="'underline'" disabled="false"/>
       <@pp.datePicker label="'${action.getText('receivedDate')}'" name="'tooling.acceptanceTime'"
	     							value="'${(tooling.acceptanceTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />	
	 </tr>
	 <tr>
	 	<@pp.datePicker label="'${action.getText('disableDate')}'" name="'tooling.disabledTime'" value="'${(tooling.disabledTime?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" />
	 </tr>
	 
    </@inputTable>
    <#if !(action.isReadOnly()) || '${eamAuthentication?if_exists}' == 'Write'>
      <@buttonBar>
        <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'"/>
      </@buttonBar>
    </#if>
  </@ww.form>
  <script language="javascript" type="text/Javascript">
     /*
      *当该工装失效，该页面的所有控件都失效
     */
  	　<#if tooling.enabled?exists>
	 　　<#if !(tooling.enabled)>
	      __disableAllElements__(document.forms[0], new Array(""));
	      disableCalendars(document.forms[0],new Array("tooling.designTime","tooling.madeTime","tooling.completeTime","tooling.checkTime","tooling.acceptanceTime","tooling.disabledTime"),true);
	    </#if>
	 　</#if>
	  function desiger_OpenDialog() {
	    var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, desigerSelectorHandler);
	  }
	  function desigerSelectorHandler(result) {
	    document.forms[0].elements["desiger.id"].value = result[0];
		document.forms[0].elements["desiger.name"].value = result[1];
	  }
	  function checker_OpenDialog() {
	  	var url = "${req.contextPath}/popup/userSelector.html";
		popupModalDialog(url, 800, 600, checkerSelectorHandler);
	  }
	  function checkerSelectorHandler(result) {
	    document.forms[0].elements["checker.id"].value = result[0];
		document.forms[0].elements["checker.name"].value = result[1];
	  }
	  function supplier_OpenDialog() {
	    var url = "${req.contextPath}/popup/supplierSelector.html";
	    popupModalDialog(url, 800, 600, supplierSelectorHandler);
	  }
	  function supplierSelectorHandler(result) {
	    document.forms[0].elements["supplier.id"].value = result[0];
	    document.forms[0].elements["supplier.name"].value = result[1];
	  }
	  function customize_validate() {
	     if(document.forms[0].elements["tooling.stringToolingDesigner"].value !='' ){
	        if (!isValidLength(document.forms[0],"tooling.stringToolingDesigner",0,50)){
				alert("${action.getText('tooling.stringToolingDesigner.lengthMax')}");
				return false;
		    }
	     }
	  	if(isNotEmpty(toolingExtInfo,"tooling.designTime")) {
			if(!isDate("tooling.designTime")){
				alert('${action.getText('designeDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		if(isNotEmpty(toolingExtInfo,"tooling.maker")) {
			if (!isValidLength(document.forms[0],"tooling.maker",0,50)){
				alert("${action.getText('tooling.maker.maxlength')}");
				return false;
		    }
		}
		
		if(isNotEmpty(toolingExtInfo,"tooling.madeTime")) {
			if(!isDate("tooling.madeTime")){
				alert('${action.getText('madeDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		if(isNotEmpty(toolingExtInfo,"tooling.completeTime")) {
			if(!isDate("tooling.designTime")){
				alert('${action.getText('completeDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		if(isNotEmpty(toolingExtInfo,"tooling.mouldParam")) {
			if (!isValidLength(document.forms[0],"tooling.mouldParam",0,50)){
				alert("${action.getText('tooling.mouldParam.maxlength')}");
				return false;
		    }
		}
		if(isNotEmpty(toolingExtInfo,"tooling.putNo")) {
			if (!isValidLength(document.forms[0],"tooling.putNo",0,50)){
				alert("${action.getText('tooling.putNo.maxlength')}");
				return false;
		    }
		}
		
		<#--if(isNotEmpty(toolingExtInfo,"tooling.mouldWeight")) {
		      if (!isNumber("tooling.mouldWeight")) {
				alert("${action.getText('tooling.mouldWeight.isNotNumber')}");
				return false;
		      } else if (!isNumberBetweenBoolen(document.forms[0].elements["tooling.mouldWeight"].value, 100000001, 0)){
				alert("${action.getText('tooling.mouldWeight.maxLength')}");
				return false;
		      }
		}-->
		if(isNotEmpty(toolingExtInfo,"tooling.mouldWeight")) {
		      if (!isDoubleNumber("tooling.mouldWeight")) {
				alert("${action.getText('tooling.mouldWeight.isNotNumber')}");
				return false;
		      } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["tooling.mouldWeight"].value, 100000001, 0)){
				alert("${action.getText('tooling.mouldWeight.maxLength')}");
				return false;
		      }
		}
		<#--if ('' != document.getElementById("tooling.mouldWeight").value) {
	      if (!isNumber("tooling.mouldWeight")) {
	        alert("${action.getText('tooling.mouldWeight.isNotNumber')}");
	        return false;
	      }
	    }-->
		
		if(isNotEmpty(toolingExtInfo,"tooling.checkTime")) {
			if(!isDate("tooling.checkTime")){
				alert('${action.getText('checkeDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		if(isNotEmpty(toolingExtInfo,"tooling.suggestion")) {
			if (!isValidLength(document.forms[0],"tooling.suggestion",0,250)){
				alert("${action.getText('tooling.suggestion.maxlength')}");
				return false;
		    }
		}
		
		if(isNotEmpty(toolingExtInfo,"tooling.acceptanceTime")) {
			if(!isDate("tooling.acceptanceTime")){
				alert('${action.getText('receivedDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}
		
		if(isNotEmpty(toolingExtInfo,"tooling.disabledTime")) {
			if(!isDate("tooling.disabledTime")){
				alert('${action.getText('disableDate')}'+'${action.getText('dateFormate.error')}');
				return false; 
			}
		}

		return true;
	  }
  </script>
</@framePage>