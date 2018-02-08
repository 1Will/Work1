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

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('middleCheck.title')}">
<base target="_self">
	<@ww.form name="'middleCheck'" action="'saveMiddleCheck'" method="'post'" validate="true">
		<@ww.token name="savemiddleCheckToken"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
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
		<#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validateInsepect()'" />
		    </#if>
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
	if(getObjByNameRe("middleCheck.resultCheck").value!=''){
		if(!isValidLength(document.forms[0], "middleCheck.resultCheck", null, 250)){
			alert("${action.getText('middleCheck.resultCheck.length')}");
			return  false;
			   }
			}
		 if(getObjByNameRe("middleCheck.checkDate").value==''){ //验证日期
	    alert('${action.getText('middleCheck.checkDate.not.null')}');
	    return false;
	    }
	 var date=getObjByNameRe("middleCheck.checkDate").value;
		if(!isDate("middleCheck.checkDate")){
		  alert("${action.getText('select.right.middleCheck.checkDate')}");
		 return false;
		 }
		   if(!isDateLessEqualThenCurrent(date)){
			  alert("${action.getText('afresh.middleCheck.checkDate')}");
		      return false;
		    }		
	  return true;
	}
	</script>
</@htmlPage>