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

<#-- $Id: customerInfoProfile.ftl 2009-12-14 8:48:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('salaryStandard.edit')}">
<@ww.form name="'listForm'" action="'saveSalaryStandardAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveSalaryStandardActionToken"/>
	<#if salaryStandard.id?exists>
		<@ww.hidden name="'salaryStandard.id'" value="#{salaryStandard.id?if_exists}"/>
	</#if>
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('salaryStandard.code')}" maxlength="10"  name="salaryStandard.code"  value="${salaryStandard.code?if_exists}"  required="false" anothername="checkcode" disabled="true"/>
			<@textfield id="positionName" label="${action.getText('salaryStandard.positionName')}" maxlength="15"  name="salaryStandard.positionName"  value="${salaryStandard.positionName?if_exists}"  required="true" anothername="checkpositionName"/>
		</tr>
		<tr>
			<@textfield id="salaryName" label="${action.getText('salaryStandard.salaryName')}" maxlength="15"  name="salaryStandard.salaryName"  value="${salaryStandard.salaryName?if_exists}"  required="true" anothername="checksalaryName"/>
		</tr>
		<tr>
		    <@textarea id="synopsis" name="salaryStandard.synopsis" label="${action.getText('salaryStandard.synopsis')}" anothername="contractContent" maxLength="250" required="false" value="${salaryStandard.synopsis?if_exists}"/>	
	    </tr>
		<tr>
		    <@textarea name="salaryStandard.remark" label="${action.getText('salaryStandard.remark')}" anothername="remark" maxLength="250" required="false" value="${salaryStandard.remark?if_exists}"/>	
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/salaryStandard/listSalaryStandardAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			if(!textfieldCheck_checkpositionName()){
				jgetObjByName("#positionName").focus();
				return false;
			}
			if(!textfieldCheck_checksalaryName()){
				jgetObjByName("#salaryName").focus();
				return false;
			}
			
		<#--	
			if(jgetObjByName("#customerInfoid").val()==""){
				alert("${action.getText('validation.customerInfoid')}");
				return false;
			}
			
			if(getObjByName('salaryStandard.address').value==""){
	       		alert("${action.getText('validation.salaryStandard.address')}");
		        getObjByName('salaryStandard.address').focus();
				return false;
			}
			if(jgetObjByName("#linkmanid").val()==""){
				alert("${action.getText('validation.linkmanid')}");
				return false;
			}
			if(jgetObjByName("#telephone").val()==""){
	       		alert("${action.getText('validation.telephone')}");
		        getObjByName('salaryStandard.telephone').focus();
				return false;
			}
			if(jgetObjByName("#salemanid").val()==""){
				alert("${action.getText('validation.salemanid')}");
				return false;
			}
			if(!dateCheckPicker(true,'salaryStandard.ciemdinghTime','${action.getText('validation.ciemdinghTime')}','%Y-%m-%d')){
				return false;
			}
			if(!dateCheckPicker(true,'salaryStandard.startTime','${action.getText('validation.startTime')}','%Y-%m-%d')){
				return false;
			}	
			if(!dateCheckPicker(true,'salaryStandard.endTime','${action.getText('validation.endTime')}','%Y-%m-%d')){
				return false;
			}	
			if(!selectCheck_selectMoneyType()){
				getObjByName('moneyType.id').focus();
		  	    return false;
			}
	        if(jgetObjByName("#sum").val()==""){
	       		alert("${action.getText('validation.contractMoney1')}");
		        getObjByName('salaryStandard.contractMoney').focus();
				return false;
			}
			if(!isDoubleNumber("sum")){
				alert("${action.getText('validation.contractMoney2')}");
				jgetObjByName("#sum").focus();
				return false;
			}
			if(!isDoubleNumber("paidMoney")){
				alert("${action.getText('validation.paidMoney')}");
				jgetObjByName("#paidMoney").focus();
				return false;
			}
			if(jgetObjByName("#contractContent").val()==""){
				alert("${action.getText('validation.contractContent')}");
				jgetObjByName("#contractContent").focus();
				return false;
			}
			if(!textareaCheck_contractContent()){
				jgetObjByName("#contractContent").focus();
				return false;
			}
			-->
			return true;
		}
	jgetObjByName(function(){
	
		
	});
</script>

</@htmlPage>
<#if salaryStandard.id?exists>
<ul id="beautytab">
	<li>
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/salaryDetail/listSalaryDetailAction.html?salaryDetail.salaryStandard=#{salaryStandard.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('薪酬明细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/salaryDetail/listSalaryDetailAction.html?salaryDetail.salaryStandard=#{salaryStandard.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>