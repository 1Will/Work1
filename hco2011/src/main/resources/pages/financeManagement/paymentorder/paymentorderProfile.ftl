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
<@ww.form namespace="'/paymentorder'"  name="'paymentorder'" action="'savePaymentorderAction'" enctype="'multipart/form-data'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'origFileName'" value=""/>
	<@ww.hidden name="'paymentorder.isSaved'" value="''"/>
	<#if paymentorder.contractManagement?exists>
		<@ww.hidden  name="'contractManagement.id'" value="#{paymentorder.contractManagement.id?if_exists}"/>
	<#else>
		<@ww.hidden  name="'contractManagement.id'" value=""/>
	</#if>
	<@ww.token name="savePaymentorderActionToken"/>
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
		    <script language="JavaScript" type="text/JavaScript">
		    <#if paymentorder.produceType?exists>
		    getObjByName("produceType.id").value=${paymentorder.produceType.id}
		    </#if>
		    </script>
			
		</tr>
		
		<tr>
			<@textfield id="totalMoney" label="${action.getText('paymentorder.totalMoney')}" maxlength="10"  name="paymentorder.totalMoney"  value="#{paymentorder.totalMoney?if_exists}"  type="N" required="true" anothername="checktotalMoney"/>
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
		</tr>
		<tr>
		
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
		     <script language="JavaScript" type="text/JavaScript">
		    <#if paymentorder.department?exists>
		    getObjByName("department").value=${paymentorder.department.id}
		    </#if>
		    </script>
		</tr>
		<tr>
		    <td align="right" valign="top">
	    		<label class="label">${action.getText('上传文件')}:</label>
	    	</td>
			<td colspan="8">
				<input type="file" name="file" size="68" onchange="getName();"/>
			</td>
		</tr>
		<tr>
		<#if paymentorder.id?exists>
				<@ww.textfield label="'${action.getText('附件名称')}'" name="'paymentorder.fileName'" value="'${paymentorder.fileName?if_exists}'"  cssClass="'underline'" maxlength="80" size="80"/>
			<#else>
				<@ww.textfield label="'${action.getText('附件名称')}'" name="'paymentorder.fileName'" value="''"  cssClass="'underline'" maxlength="80" size="80"/>
			</#if>
		
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
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/paymentorder/editPaymentorderAction.html"/>
		<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/paymentorder/editPaymentorderAction.html"/>
				<script language="JavaScript" type="text/JavaScript"> 
				getObjByName("newNext").disabled="true";
				</script>
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
			getObjByName("departmentid").value=result[4];
			getObjByName("department.id").value=result[4];
			
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
			if(getObjByName("supplier.id").value==""){
				alert("${action.getText('validation.supplierid')}");
				return false;
			}
			if(!selectCheck_selectproduceType()){
				getObjByName("produceType.id").focus();
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
			if(getObjByName("projectName").value==""){
				alert("${action.getText('请选择项目')}");
				return false;
			}
			
			if(getObjByName("paymentPersion.id").value==""){
				alert("${action.getText('validation.paymentPersion')}");
				return false;
			}
			 var filename = document.forms["paymentorder"].elements["file"].value;
		  ary = filename.split("\\");
		  //如果是新建，则要求上传文件必须选择
		  <#if paymentorder.new>
		  <#else>
		  //如果不是新建，如果上传文件选择了，则提示是否覆盖原来的文件
		    if (filename != '' && ary.length>0) { 
		      if (!confirm("${action.getText('否覆盖原来的文件')}")) {
		         return false;
		      }
		    }
		  </#if>
		  //验证手册名称
		  getObjByName("paymentorder.fileName").value=ary[ary.length-1];
			return true;
		}
	
		//合同管理模态窗体
	function contractManagement_OpenDialog(){
		var over ="no";
		var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html";
	   popupModalDialog(url, 800, 600, creatorSelector3Handler);
	   //window.open(url);
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
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/paymentDetails/listPaymentDetailsAction.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('付款明细')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/paymentDetails/listPaymentDetailsAction.html?paymentorder.id=#{paymentorder.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>