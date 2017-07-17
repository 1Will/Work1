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
<@htmlPage title="${action.getText('contractManagementAction.edit')}">
<@ww.form name="'listForm'" action="'saveContractManagementAction'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'contractManagement.isSaved'" value="''"/>
	<@ww.token name="saveContractManagementActionToken"/>
	<#if contractManagement.id?exists>
		<@ww.hidden name="'contractManagement.id'" value="#{contractManagement.id?if_exists}"/>
	</#if>
	<#if contractManagement.customerInfo?exists>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value="#{contractManagement.customerInfo.id?if_exists}"/>
	<#else>
		<@ww.hidden id="customerInfoid" name="'customerInfo.id'" value=""/>
	</#if>
	
	<#if contractManagement.linkman?exists>
		<@ww.hidden id="linkmanid" name="'linkman.id'" value="#{contractManagement.linkman.id?if_exists}"/>
	<#else>
		<@ww.hidden id="linkmanid" name="'linkman.id'" value=""/>
	</#if>
	
	<#if contractManagement.project?exists>
		<@ww.hidden id="projectid" name="'project.id'" value="#{contractManagement.project.id?if_exists}"/>
	<#else>
		<@ww.hidden id="projectid" name="'project.id'" value=""/>
	</#if>
	
	<#if contractManagement.saleman?exists>
		<@ww.hidden id="salemanid" name="'saleman.id'" value="#{contractManagement.saleman.id?if_exists}"/>
	<#else>
		<#if personnelF?exists>
			<@ww.hidden id="salemanid" name="'saleman.id'" value="#{personnelF.id?if_exists}"/>
		<#else>
			
			<@ww.hidden id="salemanid" name="'saleman.id'" value=""/>		
		</#if>
	</#if>
	
	<#if contractManagement.deparment?exists>
		<@ww.hidden  id="deparmentid" name="'deparment.id'" value="#{contractManagement.deparment.id?if_exists}"/>
	<#else>
		<#if contractManagement.saleman?exists>
			<@ww.hidden  id="deparmentid" name="'deparment.id'" value="#{contractManagement.saleman.dept.id?if_exists}"/>
		<#else>
			<@ww.hidden  id="deparmentid" name="'deparment.id'" value=""/>			
		</#if>
	</#if>
	<@ww.hidden  id="stateid" name="'state.id'" value=""/>
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('contractManagement.code')}" maxlength="10"  name="contractManagement.code"  value="${contractManagement.code?if_exists}"  required="false" anothername="checkcode" disabled="true"/>
			<@textfield id="contractName" label="${action.getText('contractManagement.contractName')}" maxlength="15"  name="contractManagement.contractName"  value="${contractManagement.contractName?if_exists}"  required="true" anothername="checkcontractName"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contractManagement.customerInfo')}:</label>
	     	</td>
	     	<td>
				<#if contractManagement.customerInfo?exists>
					<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="${contractManagement.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<#-- 以下td为添加内容(项目名称) -->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('contractManagement.project.name')}:</label>
	     	</td>
			<td>
			<#if contractManagement.project?exists>
			<input type="text" id="projectName" name="projectName" class="underline"  value="${contractManagement.project.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
			<input type="text" id="projectName"  name="projectName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
			<a onClick="projectName_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
			</a>
			</td>
	     	<#--  
			<@text2 label="${action.getText('contractManagement.address')}" name="contractManagement.address" value="${contractManagement.address?if_exists}" required="true" ></@text2>
			-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('contractManagement.linkman.name')}:</label>
	     	</td>
	     	<td>
	     		<#if contractManagement.linkman?exists>
		   		<input type="text" id="linkmanName" name="linkmanName" class="underline"  value="${contractManagement.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="linkmanName"  name="linkmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@textfield  label="${action.getText('contractManagement.address')}" name="contractManagement.address" value="${contractManagement.address?if_exists}" required="true"/>
		</tr>
		<tr>
			<@textfield id="telephone" label="${action.getText('contractManagement.telephone')}" maxlength="15"  name="contractManagement.telephone"  value="${contractManagement.telephone?if_exists}"  required="true" anothername="checkTelephone" readonly="false"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contractManagement.saleman.name')}:</label>
	     	</td>
	     	<td>
		     	<#if contractManagement.saleman?exists>
					<input type="text" id="salemanName" name="salemanName" class="underline"  value="${contractManagement.saleman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<#if personnelF?exists>
						<input type="text" id="salemanName" name="salemanName" class="underline"  value="${personnelF.name?if_exists}" maxlength="140" size="20" disabled="true"/>
					<#else>
						<input type="text" id="salemanName" name="salemanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
					</#if>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@select label="${action.getText('contractManagement.deparment.name')}" 
		   	   anothername="selectDepartment"
		   	   id="department"
		       name="department.id1" 
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
			<@pp.datePicker 
				label="'${action.getText('contractManagement.ciemdinghTime')}'" 
				name="'contractManagement.ciemdinghTime'" 
	   			value="'${(contractManagement.ciemdinghTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<@pp.datePicker 
				label="'${action.getText('contractManagement.startTime')}'" 
				name="'contractManagement.startTime'" 
	   			value="'${(contractManagement.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<@pp.datePicker 
				label="'${action.getText('contractManagement.endTime')}'" 
				name="'contractManagement.endTime'" 
	   			value="'${(contractManagement.endTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				readonly="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
		</tr>
		<tr>	
			<@ww.select label="'${action.getText('contractManagement.contractType')}'" 
				id="contractType"
				name="'contractType.id'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allComplaintType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<@textfield id="sum" label="${action.getText('contractManagement.contractMoney')}" maxlength="10"  name="contractManagement.contractMoney"  value="#{contractManagement.contractMoney?if_exists}"  required="false" anothername="contractMoney" readonly="true"/>
			<@textfield id="paidMoney" label="${action.getText('contractManagement.paidMoney')}" maxlength="10"  name="contractManagement.paidMoney"  value="#{contractManagement.paidMoney?if_exists}"  required="false" anothername="paidMoney" readonly="true"/>
		</tr>
		<tr>
			<@select label="${action.getText('contractManagement.moneyType')}" 
		   	   anothername="selectMoneyType"
		   	   id="moneyType"
		       name="moneyType.id" 
		       value="${req.getParameter('moneyType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allMoneyType" 
		       emptyOption="false" 
		       disabled="false" 
		       required="true">
		    </@select>
			<@ww.select label="'${action.getText('contractManagement.payType')}'" 
				id="payType"
				name="'payType.id'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allPayType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('contractManagement.state')}'" 
				id="state"
				name="'stateid'" 
				value="''"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="true">
			</@ww.select>		
		</tr>
		<tr>
		    <@textarea id="contractContent" name="contractManagement.contractContent" rows="4" cols="150" label="${action.getText('contractManagement.contractContent')}" anothername="contractContent" maxLength="500" required="true" value="${contractManagement.contractContent?if_exists}"/>	
	    </tr>
		<tr>
		    <@textarea name="contractManagement.remark" rows="4" cols="150" label="${action.getText('contractManagement.remark')}" anothername="remark" maxLength="500" required="false" value="${contractManagement.remark?if_exists}"/>	
		</tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#-- 
			<#if contractManagement.isSaved?exists &&contractManagement.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('提交')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('提交')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			 -->
			 
		</#if>
		<#-- 继续新建按钮   -->
		<#if contractManagement.id?exists>
		<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editContractManagementAction.html"/>
		<#else>
		<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editContractManagementAction.html"/>
		<script language="JavaScript" type="text/JavaScript"> 
		getObjByName("newNext").disabled="true";
		</script>
		</#if> 
		<#if popWindowFlag?exists&&popWindowFlag==popWindowFlag>
		<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
		<#else>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/contractManagement/listContractManagementAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
   		</#if>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
function instiChange(){
	DWREngine.setAsync(false); 
	//回调单位的值后触发DWR 级联部门 
    	InstitutionSelectDeptDWR("institution.id","deparment","${action.getText('')}","false");
    	getObjByName("deparment").options[0].value="";
	//重新设置为异步方式
	DWREngine.setAsync(true); 
	}
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
			getObjByName("customerInfo.id").value=result[0];
			getObjByName("customerInfoName").value=result[1];
			getObjByName('contractManagement.address').value=result[7]
		}
	}
	//联系人查询模态窗体
	function linkman_OpenDialog(){
		if(getObjByName('customerInfo.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitFlag=backVisit&customer.id="+getObjByName('customerInfo.id').value;
			popupModalDialog(url, 800, 600, creatorSelector2Handler);
		}else{
			alert('请先选择客户');
		}
	 }
	 //项目名称查询模态窗体(添加)
	function projectName_OpenDialog(){
		if(getObjByName('customerInfo.id').value !=''){
	   		var url = "${req.contextPath}/projectInfo/listProjectInfo.html?backVisitCheckBox=backVisitCheckBox&customer.id="+getObjByName('customerInfo.id').value;
	   		popupModalDialog(url, 800, 600, creatorSelector_Handler);
	   }else{
	   		alert('请先选择客户');
			}
	 }
	 //项目名称-获得模态窗体返回值
	function creatorSelector_Handler(result) {
		if (null != result) {
			getObjByName("projectid").value=(result[0]);
			getObjByName("projectName").value=(result[1]);
		}
	}
	 
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			getObjByName("linkmanid").value=(result[0]);
			getObjByName("linkmanName").value=(result[1]);
			getObjByName("telephone").value=(result[2]);
		}
	}
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			getObjByName("salemanid").value=(result[0]);
			getObjByName("salemanName").value=(result[2]);
			getObjByName("department").value=(result[4]);
			getObjByName("deparmentid").value=(result[4]);
		}
	}
	<#-- 提交验证-->
	function storeValidation(){
		if(!textfieldCheck_checkcontractName()){
			getObjByName("contractName").focus();
			return false;
		}
		if(getObjByName("customerInfoid").value==""){
			alert("${action.getText('validation.customerInfoid')}");
			return false;
		}
		
		if(getObjByName('contractManagement.address').value==""){
       		alert("${action.getText('validation.contractManagement.address')}");
	        getObjByName('contractManagement.address').focus();
			return false;
		}
		if(getObjByName("linkmanid").value==""){
			alert("${action.getText('validation.linkmanid')}");
			return false;
		}
		if(getObjByName("projectid").value==""){
			alert("${action.getText('validation.projectid')}");
			return false;
		}
		if(getObjByName("telephone").value==""){
       		alert("${action.getText('validation.telephone')}");
	        getObjByName('contractManagement.telephone').focus();
			return false;
		}
		if(getObjByName("salemanid").value==""){
			alert("${action.getText('validation.salemanid')}");
			return false;
		}
		if(!dateCheckPicker(true,'contractManagement.ciemdinghTime','${action.getText('validation.ciemdinghTime')}','%Y-%m-%d')){
			return false;
		}
		if(!dateCheckPicker(true,'contractManagement.startTime','${action.getText('validation.startTime')}','%Y-%m-%d')){
			return false;
		}	
		if(!dateCheckPicker(true,'contractManagement.endTime','${action.getText('validation.endTime')}','%Y-%m-%d')){
			return false;
		}	
		//验证开始日期是否大于终止时间
		var star = getObjByName('contractManagement.startTime').value;
		var end = getObjByName('contractManagement.endTime').value;
		if(isDateLessThenOldDate(star,end)){
			alert('${action.getText('validation.time.error')}');
			getObjByName('contractManagement.endTime').focus();
			return false;
		}
		
		if(!selectCheck_selectMoneyType()){
			getObjByName('moneyType.id').focus();
	  	    return false;
		}
        if(getObjByName("sum").value==""){
       		alert("${action.getText('validation.contractMoney1')}");
	        getObjByName('contractManagement.contractMoney').focus();
			return false;
		}
		if(!isDoubleNumber("sum")){
			alert("${action.getText('validation.contractMoney2')}");
			getObjByName("sum").focus();
			return false;
		}
		if(!isDoubleNumber("paidMoney")){
			alert("${action.getText('validation.paidMoney')}");
			getObjByName("paidMoney").focus();
			return false;
		}
		if(getObjByName("contractContent").value==""){
			alert("${action.getText('validation.contractContent')}");
			getObjByName("contractContent").focus();
			return false;
		}
		if(!textareaCheck_contractContent()){
			getObjByName("contractContent").focus();
			return false;
		}
		return true;
	}
	//提交
	function submitt(){
		getObjByName("contractManagement.isSaved").value = 1;
		return storeValidation();
	}
	//保存
	function savee(){
		getObjByName("contractManagement.isSaved").value = 0;
		return storeValidation();
	}
		
	getObjByName(function(){
	
		<#if contractManagement.contractType?exists>
			getObjByName("contractType").value=("${contractManagement.contractType.id?if_exists}");
		</#if>
		
		<#if contractManagement.payType?exists>
			getObjByName("payType").value=("${contractManagement.payType.id?if_exists}");
		</#if>
		<#if contractManagement.moneyType?exists>
			getObjByName("moneyType").value=("${contractManagement.moneyType.id?if_exists}");
		</#if>
		<#if contractManagement.state?exists>
			getObjByName("state").value=("${contractManagement.state.id?if_exists}");
			getObjByName("stateid").value=("${contractManagement.state.id?if_exists}");
		<#else>
			<#if stateDefault?exists>
				getObjByName("state").value=("${stateDefault?if_exists}");
				getObjByName("stateid").value=("${stateDefault?if_exists}");
			</#if>
		</#if>
		<#if contractManagement.saleman?exists>
			getObjByName("department").value=("${contractManagement.saleman.dept.id?if_exists}");
		</#if>
		
		getObjByName("moneyType").change(function(){
			getObjByName(getObjByName("moneyType option").get(0)).attr("selected",true);
		});
		
	});
</script>

</@htmlPage>
<#if contractManagement.id?exists>
<ul id="beautytab">
	<li>
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/productList/listProductList.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('产品明细')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/editContractAdditionalInfo.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附加信息')}</a>
	</li>
	<li>
		<a id="returnPlan" onclick="activeTab(this);" href='${req.contextPath}/contractManagement/listReturnPlanByCustomerAction.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('回款计划')}</a>
	</li>
	<li>
		<a id="financialManagement" onclick="activeTab(this);"  href='${req.contextPath}/financialManagement/listFinancialManagementTab.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('收款单')}</a>
	</li>
	<li>
		<a id="billingRecord" onclick="activeTab(this);"  href='${req.contextPath}/contractManagement/listBillingRecordByCustomerAction.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('开票记录')}</a>
	</li>
	<li>
		<a id="changeToHistory" onclick="activeTab(this);" href='${req.contextPath}/customerRelationship/listContactToHistory.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('变更历史')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/productList/listProductList.html?contractManagement.id=#{contractManagement.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>