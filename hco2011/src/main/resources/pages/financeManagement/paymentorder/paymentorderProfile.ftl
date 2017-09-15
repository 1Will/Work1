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
<@ww.form namespace="'/paymentorder'"  name="'paymentorder'" action="'savePaymentorderAction'" method="'post'">
	<@ww.token name="savePaymentorderActionToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'origFileName'" value=""/>
	<@ww.hidden name="'paymentorder.isSaved'" value="''"/>
	<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag' >
    	<@ww.hidden  name="popWindowFlag"  value="${popWindowFlag}"/>
    </#if>
    
	<#if paymentorder.contractManagement?exists>
		<@ww.hidden  name="'contractManagement.id'" value="#{paymentorder.contractManagement.id?if_exists}"/>
	<#else>
		<@ww.hidden  name="'contractManagement.id'" value=""/>
	</#if>
	
    <#if paymentorder.customerInfo?exists>
        <@ww.hidden id="customerInfo.id"  name="'customerInfo.id'" value="#{paymentorder.customerInfo.id?if_exists}"/>
    <#else>
        <@ww.hidden id="customerInfo.id"  name="'customerInfo.id'" value=""/>
    </#if>

	<#if paymentorder.id?exists>
		<@ww.hidden name="'paymentorder.id'" value="#{paymentorder.id?if_exists}"/>
	</#if>
	
	<#if paymentorder.projectInfo?exists>
		<@ww.hidden name="'project.id'" value="#{paymentorder.projectInfo.id?if_exists}"/>
	<#else>
		<@ww.hidden  name="'project.id'" value=""/>
	</#if>
	
	<#if paymentorder.paymentPersion?exists>
		<@ww.hidden id="paymentPersionid" name="'paymentPersion.id'" value="#{paymentorder.paymentPersion.id?if_exists}"/>
	<#else>
		<#if personnelF?exists>
			<@ww.hidden id="paymentPersionid" name="'paymentPersion.id'" value="#{personnelF.id?if_exists}"/>
		<#else>	
			<@ww.hidden id="paymentPersionid" name="'paymentPersion.id'" value=""/>
		</#if>
	</#if>
	
	<#--
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
	-->
	<@inputTable>
		<tr>
			<@textfield id="code" label="${action.getText('paymentorder.code')}" maxlength="10"  name="paymentorder.code"  value="${paymentorder.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
	     	
	     	<#-- 以下td为添加内容(项目名称) -->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('项目名称')}:</label>
	     	</td>
			<td>
				<#if paymentorder.projectInfo?exists>
					<input type="text" id="projectName" name="projectName" class="underline"  value="${paymentorder.projectInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" id="projectName"  name="projectName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="projectName_OpenDialog();">
							<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
			</td>
	     	
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('合同名称')}:</label>
	     	</td>
	     	<td>
	     		<#if paymentorder.contractManagement?exists>
		   			<input type="text" name="paymentorder.contractManagement" class="underline"  value="${paymentorder.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="paymentorder.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
					<a onClick="contractManagement_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
			</td>
	     	
		</tr>
		
		<tr>
			<@textfield id="totalMoney" label="${action.getText('paymentorder.totalMoney')}" maxlength="10"  name="paymentorder.totalMoney"  value="#{paymentorder.totalMoney?if_exists}"  type="N" required="true" anothername="checktotalMoney"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('paymentorder.supplier')}:</label>
	     	</td>
	     	
     		<td>
			<#if paymentorder.customerInfo?exists>
	     		<input type="text" name="customerInfo.name" class="underline"  value="${paymentorder.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     		<input type="text" name="customerInfo.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
				<a onClick="customerInfo_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
	     	<#--
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
			-->
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
		    <script language="JavaScript" type="text/JavaScript">
			    <#if paymentorder.produceType?exists>
			    	getObjByName("produceType.id").value=${paymentorder.produceType.id}
		    	</#if>
		    </script>
		</tr>
		<tr>
			<@select label="${action.getText('币种')}" 
		   	   anothername="selectPayType"
		       name="payType.id" 
		       value="${req.getParameter('payType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allPayType" 
		       emptyOption="false" 
		       disabled="false" 
		       required="false">
		    </@select>
		   	<script language="JavaScript" type="text/JavaScript">
			    <#if paymentorder.payType?exists>
			    	getObjByName("payType.id").value=${paymentorder.payType.id}
		    	</#if>
		    </script>
		    <#--
		    <@select label="${action.getText('开户行')}" 
		   	   anothername="selectBankName"
		       name="bankName.id" 
		       value="${req.getParameter('bankName.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allBankName" 
		       emptyOption="false" 
		       disabled="false" 
		       required="false">
		    </@select>
			<script language="JavaScript" type="text/JavaScript">
			    <#if paymentorder.bankName?exists>
			    	getObjByName("bankName.id").value=${paymentorder.bankName.id}
		    	</#if>
		    </script>
		    -->
			<@select label="${action.getText('支付方式')}" 
		   	   anothername="selectMoneyType"
		       name="moneyType.id" 
		       value="${req.getParameter('moneyType.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allMoneyType" 
		       emptyOption="false" 
		       disabled="false" 
		       required="false">
		    </@select>
			<script language="JavaScript" type="text/JavaScript">
			    <#if paymentorder.moneyType?exists>
			    	getObjByName("moneyType.id").value=${paymentorder.moneyType.id}
		    	</#if>
		    </script>			
		</tr>
		
		<tr>
		<@textfield id="paymentorder.bankName" label="${action.getText('开户行')}" maxlength="10"  name="paymentorder.bankName"  value="${paymentorder.bankName?if_exists}"  required="false"/>
		<@textfield name="paymentorder.bankNum" label="${action.getText('银行账号')}" required="false" value="${paymentorder.bankNum?if_exists}"/>	
		
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
			<#--
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
		     <script language="JavaScript" type="text/JavaScript">
		    <#if paymentorder.department?exists>
		    getObjByName("department").value=${paymentorder.department.id}
		    </#if>
		    </script>
			-->
		</tr>
		
		<tr>
		    <@textarea name="paymentorder.remark" label="${action.getText('paymentorder.remark')}" anothername="content" rows="4" cols="150" maxLength="500" required="false" value="${paymentorder.remark?if_exists}"/>	
	    </tr>
		</@inputTable>
	<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		</#if>
		
		<#if paymentorder.isSaved?exists &&paymentorder.isSaved=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
	    <#else>
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
		
		<#-- 继续新建按钮   -->
		<#if paymentorder.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/paymentorder/editPaymentorderAction.html?popWindowFlag=${popWindowFlag?if_exists}"/>
		<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/paymentorder/editPaymentorderAction.html"/>
				<script language="JavaScript" type="text/JavaScript"> 
				getObjByName("newNext").disabled="true";
				</script>
		</#if>
		
		<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
			<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
		<#else>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/paymentorder/listPaymentorderAction.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
   		</#if>
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
			getObjByName("supplier.id").value=result[0];
			getObjByName("supplierName").value=result[2];
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
			getObjByName("paymentPersion.id").value=result[0];
			getObjByName("paymentPersionName").value=result[2];
			//getObjByName("departmentid").value=result[4];
			//getObjByName("department.id").value=result[4];
			
		}
	}
	
	//弹出供应商模态窗体
	function customerInfo_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?isPartner=1";
	   popupModalDialog(url, 800, 600, setCustomerInfo);
	 }
	 //获得模态窗体返回值
	function setCustomerInfo(result) {
		if (null != result) {
		 	getObjByName("customerInfo.id").value = result[0];	
		 	getObjByName("customerInfo.name").value = result[1];
		}
	}
	
		//提交
		function submitt(){
			getObjByName("paymentorder.isSaved").value = 1;
			return storeValidation();
		}
		//保存
		function savee(){
			getObjByName("paymentorder.isSaved").value = 0;
			return storeValidation();
		}
	
		<#-- 提交验证-->
		function storeValidation(){
			if(getObjByName("projectName").value==""){
				alert("${action.getText('请选择项目')}");
				return false;
			}
			
			if(getObjByName("customerInfo.id").value==""){
				alert("${action.getText('validation.supplierid')}");
				return false;
			}
			if(!textfieldCheck_checktotalMoney()){
				getObjByName("paymentorder.totalMoney").focus();
				return false;
			}
			
			if(parseInt(getObjByName("paymentorder.totalMoney").value) <= 0){
				alert("${action.getText('validation.totalMoney')}");
				getObjByName("paymentorder.totalMoney").focus();
				return false;
			}
			
			if(!selectCheck_selectproduceType()){
				getObjByName("produceType.id").focus();
				return false;
			}
			
			if(getObjByName("paymentPersion.id").value==""){
				alert("${action.getText('validation.paymentPersion')}");
				return false;
			}
			return true;
		}
	
	//合同管理模态窗体
	function contractManagement_OpenDialog(){
		var pjId = getObjByName("project.id").value;
		var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?project.id="+pjId;
	   	popupModalDialog(url, 800, 600, creatorSelector3Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			getObjByName("contractManagement.id").value = result[0];
	   		getObjByName('paymentorder.contractManagement').value=result[1];
		}
	}
	 //项目名称查询模态窗体(添加)
	function projectName_OpenDialog(){
	   		var url = "${req.contextPath}/projectInfo/listProjectInfo.html?backVisitCheckBox=backVisitCheckBox";
	   		popupModalDialog(url, 800, 600, creatorSelector_Handler);
	 }
	 //项目名称-获得模态窗体返回值
	function creatorSelector_Handler(result) {
		if (null != result) {
			getObjByName("project.id").value=(result[0]);
			getObjByName("projectName").value=(result[1]);
		}
	}
	 function getFileName() {
	      var filename = document.forms["paymentorder"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if paymentorder.new>
		  <#else>
		  //如果不是新建，如果上传文件选择了，则提示是否覆盖原来的文件
		    if (filename != '' && ary.length>0) { 
		      if (!confirm("${action.getText('confirm.overwrite.old.file')}")) {
		         return false;
		      }
		    }
		  </#if>
		  //验证手册名称
		  getObjByName("paymentorder.fileName").value=ary[ary.length-1];
		  return true;
		}
	
	function getName() {
		  getFileNameByFile(document.forms["paymentorder"],"paymentorder.fileName");
		}
</script>

</@htmlPage>
<#if paymentorder.id?exists>
<ul id="beautytab">
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/applicationDocManager/listApplicationDoc.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>