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

<#-- $Id: contactArchivesProfile.ftl 2009-12-08 14:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('billingRecord.profile')}">
<@ww.form namespace="'/contractManagement'" name="'contractManagement'" action="'saveBillingRecord'" method="'post'">
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveBillingRecordToken"/>
    <@inputTable>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'billingRecord.isSaved'" value="''"/>
    	<@ww.hidden name="'popWindowFlag'" value="'${req.getParameter('popWindowFlag')?if_exists}'"/>
    	<#if billingRecord.id?exists>
	    	<@ww.hidden name="'payee.id'" value="'#{billingRecord.payee.id?if_exists}'"/>
    		<@ww.hidden name="'billingRecord.id'" value="#{billingRecord.id?if_exists}"/>
    	<#else>
	    	<@ww.hidden name="'payee.id'" value="''"/>
	 	</#if>
	 	<#if billingRecord.customerInfo?exists>
    		<@ww.hidden name="'customer.id'" value="'#{billingRecord.customerInfo.id?if_exists}'"/>
	 	<#else>
    		<@ww.hidden name="'customer.id'" value="''"/>
	 	</#if>
	 	
 		<#if billingRecord.contractManagement?exists>
    		<@ww.hidden name="'contractManagement.id'" value="'#{billingRecord.contractManagement.id?if_exists}'"/>
	 	<#else>
    		<@ww.hidden name="'contractManagement.id'" value="''"/>
	 	</#if>
	 	
	 	<#if billingRecord.contactArchives?exists>
    		<@ww.hidden name="'contactArchives.id'" value="'#{billingRecord.contactArchives.id?if_exists}'"/>
	 	<#else>
    		<@ww.hidden name="'contactArchives.id'" value="''"/>
	 	</#if>
	 	
	 	<tr>
	 		<@ww.textfield label="'${action.getText('开票单编码')}'" name="'billingRecord.myCode'" value="'${billingRecord.myCode?if_exists}'" cssClass="'underline'" disabled="true"/>
			<#--相关合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('billingRecord.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.contractManagement?exists>
		   		<input type="text" name="billingRecord.contractManagement" class="underline"  value="${billingRecord.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
				<#--相关客户-->
				<#assign custName = ''/>
		 	<#if billingRecord.customerInfo?exists>
		   		<#assign custName = "${billingRecord.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.customerInfo?exists>
		   			<input type="text" name="billingRecord.customerInfo" class="underline"  value="${billingRecord.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="billingRecord.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
			
	</tr>
	<tr>
	     	<#if billingRecord.contractManagement?exists>
		     	<#if billingRecord.contractManagement.project?exists>
		     		<@ww.textfield label="'${action.getText('项目名称')}'" name="'projectInfo.name'" value="'${billingRecord.contractManagement.project.name?if_exists}'" disabled="true" cssClass="'underline'" />
		     	<#else>
		     		<@ww.textfield label="'${action.getText('项目名称')}'" name="'projectInfo.name'" value="" cssClass="'underline'" disabled="true"/>
		     	</#if>
	     	<#else>
	     		<@ww.textfield label="'${action.getText('项目名称')}'" name="'projectInfo.name'" value="" cssClass="'underline'" disabled="true"/>
	     	</#if>
	     	</td>
	     	
		
		
			<#--相关联系人弹出框-->
			<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.contactArchives')}:</label>
	     	</td>
	     	<td>
	     	   		
	     		<#if billingRecord.contactArchives?exists>
		   		<input type="text" name="billingRecord.contactArchives" class="underline"  value="${billingRecord.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.contactArchives" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contactArchives_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
	     		
	     	<@ww.select label="'${action.getText('批次')}'" 
				id="batch.id" 
				name="'batch.id'" 
				value="${req.getParameter('batch.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allBatchs"
				required="true"
				emptyOption="false" 
				onchange="'getReturnPlan(this.options[this.options.selectedIndex].value)'"
				disabled="false">
			</@ww.select>
			<#if billingRecord.id?exists>
				<script language="javascript">
					getObjByName('batch.id').disabled="true";
				</script>
			</#if>
	     		
		</tr>
		
		
		<tr>
			<@ww.textfield label="'${action.getText('计划开票金额')}'" name="'billingRecord.planSum'" value="'#{billingRecord.planSum?if_exists}'" cssClass="'underline'" readonly="true" />
			<@ww.textfield label="'${action.getText('已开票金额')}'" name="'billingRecord.hasBillSum'" value="'#{billingRecord.hasBillSum?if_exists}'" cssClass="'underline'" readonly="true" />
			
			<td align="right">
				<label for="" class="label">${action.getText('是否收款')}:</label>
			</td>
	        <td align="left">
	        	<input type="radio" id="yes" name="billingRecord.isPay" value="0" />已收
	        	<input type="radio" id="no" name="billingRecord.isPay" value="1" />未收
			</td>
			<script language="javascript">
				var xradio = document.getElementsByName("billingRecord.isPay");
				<#if billingRecord.id?exists>
	                for(var i=0;i<xradio.length;i++){
	                    if(xradio[i].value == ${billingRecord.isPay?if_exists}){
	                        xradio[i].checked = true;
	                        break;
	                    }
	                }
	             <#else>
	             	xradio[0].checked = true;
	             </#if>
			</script>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('本次开票金额')}'" name="'billingRecord.sum'" value="'#{billingRecord.sum?if_exists}'" cssClass="'underline'" required="true" onblur="'getRest()'"/>
			<@ww.textfield label="'${action.getText('剩余开票金额')}'" name="'billingRecord.restSum'" value="'#{billingRecord.restSum?if_exists}'" cssClass="'underline'" readonly="true"/>
		
		<#--
			<@ww.textfield label="'${action.getText('billingRecord.currency')}'" name="'billingRecord.currency'" value="'${billingRecord.currency?if_exists}'" cssClass="'underline'"/>
		-->
			<@ww.textfield label="'${action.getText('收款凭证号')}'" name="'billingRecord.payCode'" value="'${billingRecord.payCode?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>	
            <@ww.textfield label="'${action.getText('billingRecord.code')}'" name="'billingRecord.code'" value="'${billingRecord.code?if_exists}'" cssClass="'underline'" required="true"/>
			<@pp.datePicker 
				label="'${action.getText('billingRecord.billingTime')}'" 
				name="'billingRecord.billingTime'" 
	   			value="'${(billingRecord.billingTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("billingRecord.billingTime").value==''){
						getObjByName("billingRecord.billingTime").value = date.format("yyyy-MM-dd");
					}
			</script>
		<#--开票人弹出框-->
	 		<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('billingRecord.payee')}:</label>
	     	</td>
	     	<td>
	     		<#if billingRecord.payee?exists>
		   		<input type="text" name="billingRecord.payee" class="underline"  value="${billingRecord.payee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="billingRecord.payee" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="payee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('billingRecord.invoiceTitle')}'" name="'billingRecord.invoiceTitle'" value="'${billingRecord.invoiceTitle?if_exists}'" cssClass="'underline'" />
		</tr>
		<tr>	
			<td align="right" valign="top">
	       		<label class="label">${action.getText('billingRecord.content')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="billingRecord.content" rows="4" cols="150" >${billingRecord.content?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#if billingRecord.isSaved?exists &&billingRecord.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			
			<#-- 继续新建按钮   -->
			<#if billingRecord.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/contractManagement/editBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		</#if>
		
		<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
			<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
		<#else>
			<@redirectButton class="button" value="${action.getText('back')}" url="${req.contextPath}/contractManagement/listBillingRecord.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
   		</#if>
		
    </@buttonBar>

</@ww.form>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/ReturnPlanBill.js'></script>
<script type="text/javascript">
	
	window.onload=function(){
		 <#if billingRecord.id?exists>
	    <#else>
	    	getBatch();
	    </#if>
	}
	
	function getBatch(){
		// 选择合同的时候带出，该合同下所有收款计划的批次
    	DWREngine.setAsync(false); 
    	//回调单位的值后触发DWR 级联部门  
		BatchForBillDWR("contractManagement.id","batch.id","${action.getText('')}","false"); 
    	//重新设置为异步方式
    	DWREngine.setAsync(true); 
	}
	
	function getReturnPlan(batchId){
		var cmid = getObjByName('contractManagement.id').value
		if(batchId>0){
			DWREngine.setAsync(false); 
			ReturnPlanBill.getReturnPlanBill(cmid,batchId,setMoney)
			//重新设置为异步方式
			DWREngine.setAsync(true); 
		}else{
			getObjByName('billingRecord.planSum').value ="";
			getObjByName('billingRecord.hasBillSum').value ="";
		}
	}
	function setMoney(data){
		getObjByName('billingRecord.planSum').value =data[0];
		getObjByName('billingRecord.hasBillSum').value =data[1];
	}
	
	
	function getRest(){
		if(getObjByName('batch.id').value=='' || getObjByName('batch.id').value == -1){
			alert("${action.getText('请选择批次！')}");
			getObjByName('batch.id').focus();
			return false;
		}
		var plan = getObjByName('billingRecord.planSum').value;
		var sum = getObjByName('billingRecord.sum').value;
		var has = getObjByName('billingRecord.hasBillSum').value;
		if(sum==""){
			getObjByName('billingRecord.restSum').value="";
			return;
		}
		var rest = plan-has-sum;
		if(rest>=0){
			getObjByName('billingRecord.restSum').value =rest;
		}else{
			alert("剩余开票金额不能小于0！")
			getObjByName('billingRecord.sum').value="";
			getObjByName('billingRecord.restSum').value="";
		}
	}
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["billingRecord.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		}
	}
	
	//弹出收款人查询模态窗体
	function payee_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["payee.id"].value = result[0];
	   		getObjByName('billingRecord.payee').value=result[2];
		}
	}
	
	//合同管理模态窗体
	function contractManagement_OpenDialog(){
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["contractManagement.id"].value = result[0];
	   		getObjByName('billingRecord.contractManagement').value=result[1];
	   		getObjByName('billingRecord.customerInfo').value=result[2];
	   		document.forms[0].elements["customer.id"].value = result[4];
	   		document.forms[0].elements["contactArchives.id"].value=result[5];
	   		getObjByName('billingRecord.contactArchives').value=result[3];
	   		getObjByName('billingRecord.invoiceTitle').value=result[2]
	   		getObjByName('projectInfo.name').value=result[10]
		}
		getBatch();
	}
	
		//联系人查询模态窗体
	function contactArchives_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorContactArchivesHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorContactArchivesHandler(result) {
		if (null != result) {
			document.forms[0].elements["contactArchives.id"].value = result[0];
	   		getObjByName('billingRecord.contactArchives').value=result[2];
		}
	}
	
	//提交
	function submitt(){
		getObjByName("billingRecord.isSaved").value = 1;
		return storeValidation();
	}
	//保存
	function savee(){
		getObjByName("billingRecord.isSaved").value = 0;
		return storeValidation();
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('billingRecord.contractManagement').value==''){
			alert("${action.getText('billingRecord.contractManagement.requiredstring')}");
			return false;
		}
		
		if(getObjByName('batch.id').value=='' || getObjByName('batch.id').value == -1){
			alert("${action.getText('请选择批次！')}");
			getObjByName('batch.id').focus();
			return false;
		}
		
		if(getObjByName('billingRecord.sum').value==''){
			alert("${action.getText('billingRecord.sum.requiredstring')}");
			getObjByName('billingRecord.sum').focus();
			return false;
		}
		 //验证费用为double类型
		if(getObjByName('billingRecord.sum').value!=''){
	     	if(!isDoubleNumber("billingRecord.sum")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('billingRecord.sum').value="";
				getObjByName('billingRecord.sum').focus();
				return false;
			}
	     }
	     
	    var radios = document.getElementsByName("billingRecord.isPay");
		var tag = true;
		var val;
		for(var i = 0 ;i< radios.length;i++) {
		   if(radios[i].checked) {
		      tag = false;
		      val = radios[i].value;
		      break;
		   }
		}
		if(tag){
			alert("请选择是否收款！");
			return false;
		}else{
			if(getObjByName('billingRecord.payCode').value==''&&val==0){
				alert("${action.getText('请输入收款凭证号！')}");
				getObjByName('billingRecord.payCode').focus();
				return false;
			}
		}
	     
	     if(getObjByName('billingRecord.code').value==''){
			alert("${action.getText('billingRecord.code.requiredstring')}");
			getObjByName('billingRecord.code').focus();
			return false;
		}
	     
	     if(getObjByName('billingRecord.billingTime').value==''){
			alert("${action.getText('billingRecord.billingTime.requiredstring')}");
			getObjByName('billingRecord.billingTime').focus();
			return false;
		}
		if(getObjByName('billingRecord.billingTime').value !=''){
		 	if(!isDate('billingRecord.billingTime')){
				alert("${action.getText('billingRecord.billingTime.dateFormate.error')}");
	      	    getObjByName('billingRecord.billingTime').focus();
				return false;
			}
		}
		
		if(getObjByName('billingRecord.payee').value==''){
			alert("${action.getText('billingRecord.payee.requiredstring')}");
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
<#if billingRecord.id?exists>
<ul id="beautytab">
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?billingRecord.id=#{billingRecord.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/applicationDocManager/listApplicationDoc.html?billingRecord.id=#{billingRecord.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>
