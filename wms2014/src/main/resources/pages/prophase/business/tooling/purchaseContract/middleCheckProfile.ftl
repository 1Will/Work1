<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: payDetailProfile.ftl 10914 2008-02-14 01:50:22Z qsun $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('middleCheck.title')}">
<base target="_self">
	<@ww.form name="'middleCheck'" action="'saveMiddleCheck'" method="'post'" validate="true">
		<@ww.token name="savemiddleCheckToken"/>
		
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
		<#if middleCheck.id?exists>
			<@ww.hidden name="'middleCheck.id'" value="#{middleCheck.id}"/>
		</#if>
		<@inputTable>
			<tr>
			 <@ww.textfield label="'${action.getText('content')}'" name="'middleCheck.content'" value="'${middleCheck.content?if_exists}'"  cssClass="'underline'" required="true" />
			 <@ww.textarea label="'${action.getText('resultCheck')}'"  name="'middleCheck.resultCheck'" value="'${middleCheck.resultCheck?if_exists}'" rows="'3'" cols="'50'" />
			</tr>
			<tr>
			<@pp.datePicker label="'${action.getText('checkDate')}'" name="'middleCheck.checkDate'"
					value="'${(middleCheck.checkDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"
					required="true"/>
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" />
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
<script language="javascript">	
	function validateInsepect(){
	if(getObjByNameRe("middleCheck.content").value==''){
	    alert('${action.getText('middleCheck.content')}');
	    return false;
	  }else{
	      if(!isValidLength(document.forms[0], "middleCheck.content", null, 50)){
				alert("${action.getText('middleCheck.content.MaxLength')}");
				return  false;
			   }   
	    }
	if(getObjByNameRe("inspectStandard.inspectContent").value!=''){
		if(!isValidLength(document.forms[0], "inspectStandard.inspectContent", null, 250)){
			alert("${action.getText('inspectStandard.inspectContent.length')}");
			return  false;
			   }
			}
	if(getObjByNameRe("inspectStandard.Standard").value!=''){
		if(!isValidLength(document.forms[0], "inspectStandard.Standard", null, 250)){
			alert("${action.getText('inspectStandard.inspectStandard.length')}");
			return  false;
			   }
			}		
	  return true;
	}
	</script>
</@htmlPage>