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
<#--$Id: contractProfile.ftl 10914 2008-02-14 01:50:22Z qsun $ -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('purchaseContract')}">	
		
	<@ww.form name="'purchaseBill'" action="'saveContract'" method="'post'">
		<@ww.token name="saveContractToken"/>
		<@ww.hidden name="'purchaseBill.id'" value="'${req.getParameter('purchaseBill.id')?if_exists}'"/>
		<@ww.hidden name="'feeSource'" />
		<script language="javascript">
			var feeSource = window.parent.document.forms[0].elements["feeSource"].value;
			document.forms[0].elements["feeSource"].value = feeSource;
		</script>
		
		<@inputTable>
			<tr>
			    <@ww.textfield label="'${action.getText('contractMoney')}'" name="'purchaseBill.contractMoney'" value="'${purchaseBill.contractMoney?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('alreadyPayOut')}'" name="'purchaseBill.alreadyPayOut'" value="'${purchaseBill.alreadyPayOut?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textarea label="'${action.getText('article')}'"  name="'purchaseBill.article'" value="'${purchaseBill.article?if_exists}'" rows="'3'" cols="'80'" />
				<@ww.textarea label="'${action.getText('payMethod')}'"  name="'purchaseBill.payMethod'" value="'${purchaseBill.payMethod?if_exists}'" rows="'3'" cols="'80'" />
			</tr>
			<tr>
				 <@pp.datePicker label="'${action.getText('reqDeliveryDate')}'" name="'purchaseBill.reqDeliveryDate'"
					value="'${(purchaseBill.reqDeliveryDate?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15"/>
				<@ww.textarea label="'${action.getText('contractComment')}'"  name="'purchaseBill.contractComment'" value="'${purchaseBill.contractComment?if_exists}'" rows="'3'" cols="'80'" />
			</tr>
		</@inputTable>
		<@buttonBar>
		    <@vsubmit name="'save'" value="'${action.getText('save')}'" />
		</@buttonBar>
		
	</@ww.form>
</@framePage>