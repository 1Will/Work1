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
<#-- $Id: additionalInfo.ftl  2009-11-26 11:04:50Z wliu $-->

<#include "../../../includes/macros.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'saveContractAdditionalInfo'" method="'post'">
		<@ww.token name="listAdditionalInfoToken"/>
		<#if contractAdditionalInfo.financialPer?exists>
			<@ww.hidden id="contractAdditionalInfo.financialPer.id" name="'contractAdditionalInfo.financialPer.id'" value="#{contractAdditionalInfo.financialPer.id?if_exists}"/>
		<#else>
			<@ww.hidden id="contractAdditionalInfo.financialPer.id" name="'contractAdditionalInfo.financialPer.id'" value=""/>
		</#if>
		
		<@ww.hidden id="contractManagement.id" name="'contractManagement.id'" value="${contractManagement.id}"/>
		
		<@inputTable>
		<tr>
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('财务联系人')}:</label>
	     	</td>
			<td>
			<#if contractAdditionalInfo.financialPer?exists>
				<input type="text" id="contractAdditionalInfo.financialPer.name" name="contractAdditionalInfo.financialPer.name" class="underline"  value="${contractAdditionalInfo.financialPer.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="contractAdditionalInfo.financialPer.name" name="contractAdditionalInfo.financialPer.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
				<a onClick="financialPer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@text2 label="${action.getText('地址')}" name="contractAdditionalInfo.address" value="${contractAdditionalInfo.address?if_exists}"></@text2>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('开户行')}'" name="'contractAdditionalInfo.bank'" value="'${contractAdditionalInfo.bank?if_exists}'" cssClass="'underline'" required="false"/>
			<@text2 label="${action.getText('开户行账号')}" name="contractAdditionalInfo.bankAccount" value="${contractAdditionalInfo.bankAccount?if_exists}" ></@text2>
		</tr>
		<tr>
			<@textarea label="${action.getText('备注')}" name="contractAdditionalInfo.comment" value="${contractAdditionalInfo.comment?if_exists}" rows="4" cols="150"/>
		</tr>
		</@inputTable>
		<#if !(action.isReadOnly())>
		<@buttonBar>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation()'"/>
        </@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	//弹出财务联系人查询模态窗体
	function financialPer_OpenDialog(){
		var url = "${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisitFlag=backVisit&customer.id=#{contractManagement.customerInfo.id}";
	  	popupModalDialog(url, 800, 600, setPerson);
	 }
	 //获得模态窗体返回值
	function setPerson(result) {
		if (null != result) {
			getObjByName("contractAdditionalInfo.financialPer.id").value=result[0];
			getObjByName("contractAdditionalInfo.financialPer.name").value=result[1];
		}
	}
	
	function storeValidation(){
		if(getObjByName('contractAdditionalInfo.financialPer.name').value==''){
			alert("请选择财务联系人");
			getObjByName('contractAdditionalInfo.financialPer.name').focus();
			return false;
		}
		return true;
	}
</script>
