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
	
	<@ww.form name="'discardBillDtl'" action="'saveDiscardBillDtl'" method="'post'" validate="true">
		<@ww.token name="saveDiscardBillDtlToken"/>
		
		<#if discardBill.id?exists>
			<@ww.hidden name="'discardBill.id'" value="#{discardBill.id}"/>
		</#if>
		<#if discardBillDtl.id?exists>
			<@ww.hidden name="'discardBillDtl.id'" value="#{discardBillDtl.id}"/>
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
			  
			   <@ww.textarea label="'${action.getText('content')}'"  name="'payDetail.content'" value="'${payDetail.content?if_exists}'" rows="'3'" cols="'50'" />
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" />
		    <@vbutton name="'close'"  class="button" value="${action.getText('close')}" onclick="javascript:window.close();"/>
		</@buttonBar>
		
	</@ww.form>
	
</@htmlPage>