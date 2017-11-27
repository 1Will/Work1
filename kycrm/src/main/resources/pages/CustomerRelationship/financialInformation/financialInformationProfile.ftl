<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: additionalInfoProfile.ftl 2009-12-08 10:00:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title='财务信息维护'>
<@ww.form name="'financialInformation'" namespace="'/customerRelationship'" action="'saveFinancialInformation'" method="'post'">
	<@ww.token name="saveFinancialInformationToken"/>
	<#if financialInformation.id?exists>
    		<@ww.hidden name="'financialInformation.id'" value="#{financialInformation.id}"/>
    		</#if>
	<@ww.hidden name="'customerInfo.id'" value="'#{customerInfoId?if_exists}'" />
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
 	<tr>
 	   <@pp.datePicker 
				label="'${action.getText('year')}'" 
				name="'financialInformation.year'" 
	   			value="'${(financialInformation.year?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10" />
				<script language="javascript">
				<#if financialInformation.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("financialInformation.year").value==''){
						getObjByName("financialInformation.year").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
        <@ww.textfield label="'${action.getText('totalAssets')}'" name="'financialInformation.totalAssets'"  cssClass="'underline'" required="false"/>
    </tr>
    <tr>
   		<@ww.textfield label="'${action.getText('totalLiabilities')}'" name="'financialInformation.totalLiabilities'" cssClass="'underline'" required="false"/>
	    <@ww.textfield label="'${action.getText('nearProfit')}'" name="'financialInformation.nearProfit'" cssClass="'underline'" required="false"/>
    
	</tr>
	 <tr>
   		<@ww.textfield label="'${action.getText('outputValue')}'" name="'financialInformation.outputValue'"  cssClass="'underline'" required="false"/>
	    <@ww.textfield label="'${action.getText('totalTax')}'" name="'financialInformation.totalTax'"  cssClass="'underline'" required="false"/>
    
	</tr>
	</@inputTable>
	<#if !(action.isReadOnly())>
		 <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'"  onclick="'return storeValidation();'" />
		
				<#-- 继续新建按钮   -->
				<#if financialInformation.id?exists>
						<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editFinancialInformation.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
				<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editFinancialInformation.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
						<script language="JavaScript" type="text/JavaScript"> 
						getObjByName("newNext").disabled="true";
						</script>
				</#if>
		</#if>
		<!-- 关闭按钮 -->
		<@vbutton name="close" value="${action.getText('close')}" class="button" onclick="closeThis();"/>
    </@buttonBar>
    </#if>
</@ww.form>
<script>

function storeValidation(){
if(getObjByName('financialInformation.totalAssets').value !=''){
			if(!isDoubleNumber('financialInformation.totalAssets')){
				alert('${action.getText('totalAssets')}'+'必须是数字');
				getObjByName('financialInformation.totalAssets').value="";
				getObjByName('financialInformation.totalAssets').focus();
				return false;
			}
		}
		if(getObjByName('financialInformation.totalLiabilities').value !=''){
			if(!isDoubleNumber('financialInformation.totalLiabilities')){
				alert('${action.getText('totalLiabilities')}'+'必须是数字');
				getObjByName('financialInformation.totalLiabilities').value="";
				getObjByName('financialInformation.totalLiabilities').focus();
				return false;
			}
		}
		if(getObjByName('financialInformation.nearProfit').value !=''){
			if(!isDoubleNumber('financialInformation.nearProfit')){
				alert('${action.getText('nearProfit')}'+'必须是数字');
				getObjByName('financialInformation.nearProfit').value="";
				getObjByName('financialInformation.nearProfit').focus();
				return false;
			}
		}
		if(getObjByName('financialInformation.outputValue').value !=''){
			if(!isDoubleNumber('financialInformation.outputValue')){
				alert('${action.getText('outputValue')}'+'必须是数字');
				getObjByName('financialInformation.outputValue').value="";
				getObjByName('financialInformation.outputValue').focus();
				return false;
			}
		}
		if(getObjByName('financialInformation.totalTax').value !=''){
			if(!isDoubleNumber('financialInformation.totalTax')){
				alert('${action.getText('totalTax')}'+'必须是数字');
				getObjByName('financialInformation.totalTax').value="";
				getObjByName('financialInformation.totalTax').focus();
				return false;
			}
		}
		}
</script>
</@htmlPage>
