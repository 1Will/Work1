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

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('paymentorder.edit')}">
<@ww.form name="'listForm'" action="'savePaymentorderAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="savePaymentorderActionToken"/>
	<#if paymentorder.id?exists>
		<@ww.hidden name="'paymentorder.id'" value="#{paymentorder.id?if_exists}"/>
	</#if>
	
	<#if paymentorder.paymentPersion?exists>
		<@ww.hidden id="paymentPersionid" name="'paymentPersion.id'" value="#{paymentorder.paymentPersion.id?if_exists}"/>
	<#else>
		<@ww.hidden id="paymentPersionid" name="'paymentPersion.id'" value=""/>	
	</#if>
	
	<#if paymentorder.supplier?exists>
		<@ww.hidden id="supplierid" name="'supplier.id'" value="#{paymentorder.supplier.id?if_exists}"/>
	<#else>
		<@ww.hidden id="supplierid" name="'supplier.id'" value=""/>	
	</#if>
	
	
	<#if paymentorder.department?exists>
		<@ww.hidden id="deparmentid" name="'department.id'" value="#{paymentorder.department.id?if_exists}"/>
	<#else>
		<#if personnelF?exists>
			<@ww.hidden id="deparmentid" name="'department.id'" value="#{personnelF.dept.id?if_exists}"/>
		<#else>
			<@ww.hidden id="deparmentid" name="'department.id'" value=""/>		
		</#if>
	</#if>
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('paymentorder.code')}" maxlength="10"  name="paymentorder.code"  value="${paymentorder.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('paymentorder.supplier')}:</label>
	     	</td>
	     	<td>
		     	<#if paymentorder.supplier?exists>
					<input type="text" id="supplierName" name="supplierName" class="underline"  value="${paymentorder.supplier.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="supplierName" name="supplierName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="supplier_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@select label="${action.getText('paymentorder.produceType')}" 
		   	   anothername="selectproduceType" 
		   	   id="produceType"
		       name="produceType.id" 
		       value="${req.getParameter('produceType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allProduceType" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
			
		</tr>
		
		<tr>
			<@textfield id="totalMoney" label="${action.getText('paymentorder.totalMoney')}" maxlength="10"  name="paymentorder.totalMoney"  value="#{paymentorder.totalMoney?if_exists}"  type="N" required="true" anothername="checktotalMoney"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('paymentorder.paymentPersion')}:</label>
	     	</td>
	     	<td>
			<#if paymentorder.paymentPersion?exists>
				<input type="text" id="paymentPersionName" name="paymentPersionName" class="underline"  value="${paymentorder.paymentPersion.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<#if personnelF?exists>
					<input type="text" id="paymentPersionName" name="paymentPersionName" class="underline"  value="${personnelF.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="paymentPersionName" name="paymentPersionName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</#if>
			<a onClick="paymentPersion_OpenDialog();">
				<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
			</a>
			</td>
		    <@select label="${action.getText('paymentorder.deparment')}" 
		   	   anothername="selectDepartment"
		   	   id="department"
		       name="departmentid" 
		       value="${req.getParameter('department.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allDepartment" 
		       emptyOption="true" 
		       disabled="true" 
		       required="false">
		    </@select>
		</tr>
		<tr>
		    <@textarea name="paymentorder.remark" label="${action.getText('paymentorder.remark')}" anothername="content" maxLength="500" required="false" value="${paymentorder.remark?if_exists}"/>	
	    </tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/paymentorder/listPaymentorderAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
	//弹出业务员查询模态窗体
	function supplier_OpenDialog(){
	   var url = "${req.contextPath}/supplierManager/listSupplierWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			jgetObjByName("#supplierid").val(result[0]);
			jgetObjByName("#supplierName").val(result[2]);
		}
	}
	//弹出付款人查询模态窗体
	function paymentPersion_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, paymentPersion_creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function paymentPersion_creatorSelectorHandler(result) {
		if (null != result) {
			jgetObjByName("#paymentPersionid").val(result[0]);
			jgetObjByName("#paymentPersionName").val(result[2]);
			jgetObjByName("#department").val(result[4]);
			jgetObjByName("#deparmentid").val(result[4]);
			
		}
	}
		<#-- 提交验证-->
		function storeValidation(){
			if(jgetObjByName("#supplierid").val()==""){
				alert("${action.getText('validation.supplierid')}");
				return false;
			}
			if(!selectCheck_selectproduceType()){
				jgetObjByName("#produceType").focus();
				return false;
			}
			if(!textfieldCheck_checktotalMoney()){
				jgetObjByName("#totalMoney").focus();
				return false;
			}
			if(parseInt(jgetObjByName("#totalMoney").val()) <= 0){
				alert("${action.getText('validation.totalMoney')}");
				jgetObjByName("#totalMoney").focus();
				return false;
			}
			
			if(jgetObjByName("#paymentPersionid").val()==""){
				alert("${action.getText('validation.paymentPersion')}");
				return false;
			}
			return true;
		}
	jgetObjByName(function(){
		<#if paymentorder.department?exists>
			jgetObjByName("#department").val("${paymentorder.department.id?if_exists}");
			jgetObjByName("#deparmentid").val("${paymentorder.department.id?if_exists}");
			
		<#else>
			<#if personnelF?exists>
				jgetObjByName("#department").val("#{personnelF.dept.id?if_exists}");
				jgetObjByName("#deparmentid").val("#{personnelF.dept.id?if_exists}");
			</#if>
		</#if>
		
		<#if paymentorder.produceType?exists>
			jgetObjByName("#produceType").val("${paymentorder.produceType.id?if_exists}");
		</#if>
	});
</script>

</@htmlPage>
<#if paymentorder.id?exists>
<ul id="beautytab">
	<li>
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/paymentDetails/listPaymentDetailsAction.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('付款明细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/paymentDetails/listPaymentDetailsAction.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>