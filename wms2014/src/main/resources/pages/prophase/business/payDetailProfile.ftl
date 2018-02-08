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
<#--$Id: payDetailProfile.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('payDetail.title')}">
<base target="_self">
	
	<@ww.form name="'payDetail'" action="'savePayDetail'" method="'post'" validate="true">
		<@ww.token name="savePayDetailToken"/>
		<#if purchaseBill.id?exists>
			<@ww.hidden name="'purchaseBill.id'" value="#{purchaseBill.id}"/>
		</#if>
      <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if payDetail.id?exists>
			<@ww.hidden name="'payDetail.id'" value="#{payDetail.id}"/>
		</#if>
		<@inputTable>
			
			
			<tr>
			 <@ww.textfield label="'${action.getText('payMoney')}'" name="'payDetail.payMoney'" value="'${payDetail.payMoney?if_exists}'"  cssClass="'underline'" required="true" />
				<@pp.datePicker label="'${action.getText('payDate')}'" name="'payDetail.payDate'"
					value="'${(payDetail.payDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"
					required="true"/>
			</tr>
			<tr>
				<@ww.textarea label="'${action.getText('comment')}'"  name="'payDetail.comment'" value="'${payDetail.comment?if_exists}'" rows="'3'" cols="'50'" />
			  <#--<@ww.textarea label="'${action.getText('content')}'"  name="'payDetail.content'" value="'${payDetail.content?if_exists}'" rows="'3'" cols="'50'" />-->
			</tr>
		</@inputTable>
		<@buttonBar>
		
          <#if !(action.isReadOnly())>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return saveVlidate()'" />
		   </#if> 
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
	  <script language="javascript">
	function  saveVlidate(){
	  if(isNotEmpty(document.forms[0],"payDetail.payMoney")) {
	     if (!isDoubleNumber("payDetail.payMoney")){
		    alert("${action.getText('subscribeDtl.unitPrice.isNotNumber')}");
		       return false;
	       } else if (!isDoubleNumberBetweenBoolean(document.forms[0].elements["payDetail.payMoney"].value, 10000000001, 0)){  //验证范围
		     alert("${action.getText('subscribeDtl.unitPrice.maxLength')}");
		      return false;
	      }
       }
         if(getObjByNameRe("payDetail.payDate").value==''){ //验证日期
	    alert('${action.getText('middleCheck.payDetail.payDate')}');
	    return false;
	    }
	 var date=getObjByNameRe("payDetail.payDate").value;
		if(!isDate("payDetail.payDate")){
		  alert("${action.getText('select.right.payDetail.payDate')}");
		 return false;
		 }
		   if(!isDateLessEqualThenCurrent(date)){
			  alert("${action.getText('afresh.payDetail.payDate')}");
		      return false;
		    }
	  if(getObjByNameRe("payDetail.comment").value!=''){
		if(!isValidLength(document.forms[0], "payDetail.comment", null, 250)){
				alert("${action.getText('payDetail.comment.length')}");
				return  false;
			   }
			}	    
		    return true;
     }
    </script>
</@htmlPage>