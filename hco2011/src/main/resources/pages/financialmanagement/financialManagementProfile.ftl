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

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('financialManagement.profile')}">
<@ww.form namespace="'/financialManagement'" name="'financialManagement'" action="'saveFinancialManagement'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveFinancialManagementToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'payee.id'" value="'${req.getParameter('payee.id')?if_exists}'"/>
    	<@ww.hidden name="'saleman.id'" value="'${req.getParameter('saleman.id')?if_exists}'"/>
    	<@ww.hidden name="'contractManagement.id'" value="'${req.getParameter('contractManagement.id')?if_exists}'"/>
    	<#if financialManagement.id?exists>
    		<@ww.hidden name="'financialManagement.id'" value="#{financialManagement.id?if_exists}"/>
	 	</#if>
	 	<tr>										 
	 		<@ww.textfield label="'${action.getText('financialManagement.code')}'" name="'financialManagement.code'" value="'${financialManagement.code?if_exists}'" cssClass="'underline'" disabled="true"/>
				<#--相关合同弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('financialManagement.contractManagement')}:</label>
	     	</td>
	     	<td>
	     		<#if financialManagement.contractManagement?exists>
		   		<input type="text" name="financialManagement.contractManagement" class="underline"  value="${financialManagement.contractManagement.contractName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="financialManagement.contractManagement" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contractManagement_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
				<#--相关客户弹出框弹出框-->
				<#assign custName = ''/>
		 	<#if financialManagement.customerInfo?exists>
		   		<#assign custName = "${financialManagement.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<label class="label">${action.getText('financialManagement.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if financialManagement.customerInfo?exists>
		   			<input type="text" name="financialManagement.customerInfo" class="underline"  value="${financialManagement.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="financialManagement.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
		</tr>
		<tr>
			<#--业务员弹出框-->
	 		<td align="right" valign="top">
	 			<span class="required">*</span>
	       		<label class="label">${action.getText('financialManagement.saleman')}:</label>
	     	</td>
	     	<td>
	     		<#if financialManagement.saleman?exists>
		   		<input type="text" name="financialManagement.saleman" class="underline"  value="${financialManagement.saleman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="financialManagement.saleman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="saleman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@ww.select label="'${action.getText('financialManagement.collectionType')}'" 
				name="'collectionType.id'" 
				value="${req.getParameter('collectionType.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allCollectionTypes"
				required="true"
				disabled="false">
			</@ww.select>
			
			<@ww.select label="'${action.getText('financialManagement.payment')}'" 
				name="'payment.id'" 
				value="${req.getParameter('payment.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allPayments"
				required="true"
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<@text2 label="${action.getText('financialManagement.accountNumber')}" name="financialManagement.accountNumber" value="${financialManagement.accountNumber?if_exists}"></@text2>
			<#--<span id="id_select">	-->
				<@ww.select label="'${action.getText('financialManagement.batch')}'" 
					name="'batch.id'" 
					value="${req.getParameter('batch.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allBatchs"
					required="true"
					emptyOption="false" 
					disabled="false">
				</@ww.select>
			<#--</span>	-->
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('financialManagement.sumReceivable')}'" name="'financialManagement.sumReceivable'" value="'#{financialManagement.sumReceivable?if_exists}'" cssClass="'underline'" readonly="true" required="true"/>
			<@ww.textfield label="'${action.getText('financialManagement.trueSum')}'" name="'financialManagement.trueSum'" value="'#{financialManagement.trueSum?if_exists}'" cssClass="'underline'" required="true" onfocus="'return getTrueSum();'" onchange="'chooseValue()'" onblur="'checkSum()'"/>
			<span id="sum_id" name=""></span>
			<@ww.textfield label="'${action.getText('financialManagement.totalSum')}'" name="'financialManagement.totalSum'" value="'#{financialManagement.totalSum?if_exists}'" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('financialManagement.withoutGotSum')}'" name="'financialManagement.withoutGotSum'" value="'#{financialManagement.withoutGotSum?if_exists}'" cssClass="'underline'" readonly="true"/>
			<td align="right">
			<span class="required">*</span>
			<label for="" class="label">${action.getText('financialManagement.invoice')}:</label>
			</td>
	        <td align="left">
	        	<input type="radio" id="yes" name="invoice" value="0" />已开
	        	<input type="radio" id="no" name="invoice" value="1" />未开
			</td>
			<@ww.textfield label="'${action.getText('financialManagement.invoiceCode')}'" name="'financialManagement.invoiceCode'" value="'${financialManagement.invoiceCode?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@pp.datePicker 
				label="'${action.getText('financialManagement.collectionDate')}'" 
				name="'financialManagement.collectionDate'" 
	   			value="'${(financialManagement.collectionDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				required="true"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("financialManagement.collectionDate").value==''){
						getObjByName("financialManagement.collectionDate").value = date.format("yyyy-MM-dd");
					}
			</script>
				<#--收款人弹出框-->
	 		<td align="right" valign="top">
	 			<span class="required">*</span>
	       		<label class="label">${action.getText('financialManagement.payee')}:</label>
	     	</td>
	     	<td>
	     		<#if financialManagement.payee?exists>
		   		<input type="text" name="financialManagement.payee" class="underline"  value="${financialManagement.payee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="financialManagement.payee" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="payee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@ww.select label="'${action.getText('financialManagement.dept')}'" 
				name="'dept.id'" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
				<#assign dept=""/>
	            <#if financialManagement.payee?exists>
	            	<#if financialManagement.payee.dept?exists>
	            		<#assign dept=financialManagement.payee.dept>
	            		<script language="javascript">
							getObjByName('dept.id').value=${dept.id}
						</script>
	            	</#if>
	            
			    </#if>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('financialManagement.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="financialManagement.remark" rows="4" cols="150" >${financialManagement.remark?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/financialManagement/listFinancialManagement.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	function checkSum(){
			if(getObjByName('financialManagement.trueSum').value==''){
				return false;
			}
			if(getObjByName('financialManagement.trueSum').value!=''){
		     	if(!isDoubleNumber("financialManagement.trueSum")){
					alert("${action.getText('trueSum.must.be.double')}");
					getObjByName('financialManagement.trueSum').value="";
					getObjByName('financialManagement.trueSum').focus();
					return false;
				}
	     	}
			//验证预付款金额
	    	DWREngine.setAsync(false); 
	    	//失去焦点出发改函数  																			  
			checkSumDWR("contractManagement.id","financialManagement.trueSum","sum_id","${action.getText('')}",false); 
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    	}
	window.onload=function(){
		<#if financialManagement.invoice?exists>
			<#if financialManagement.invoice=="0">
				getObjByName('yes').checked=true;
			<#else>
				getObjByName('no').checked=true;
			</#if>
		<#else>
			getObjByName('no').checked=true;
		</#if>
		
		<#if financialManagement.payment?exists>
			getObjByName('payment.id').value='${financialManagement.payment.id?if_exists}';
		</#if>
		<#if financialManagement.collectionType?exists>
			getObjByName('collectionType.id').value='${financialManagement.collectionType.id?if_exists}';
		</#if>
		<#if financialManagement.batch?exists>
			getObjByName('batch.id').value='${financialManagement.batch.id?if_exists}';
		</#if>
		<#if financialManagement.dept?exists>
			getObjByName('dept.id').value='${financialManagement.dept.id?if_exists}';
		</#if>
		//init();
		//通过选择批次的时候带出一系列的值
		<#if financialManagement.batch?exists>
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联部门  																			  
			ContractManagementAndBatchDWR("contractManagement.id","batch.id","financialManagement.sumReceivable","financialManagement.trueSum","financialManagement.totalSum","financialManagement.withoutGotSum","trueSum","${action.getText('')}",false); 
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    <#else>
    	    <#if req.getParameter('batch.id')?exists>
		    	//设置同步
		    	DWREngine.setAsync(false); 
		    	//回调单位的值后触发DWR 级联部门  
				ContractManagementAndBatchDWR("contractManagement.id","batch.id","financialManagement.sumReceivable","financialManagement.trueSum","financialManagement.totalSum","financialManagement.withoutGotSum","trueSum","${action.getText('')}",false); 
		    	//重新设置为异步方式
		    	DWREngine.setAsync(true); 
    	    </#if>
	    </#if>
	    
	}
		//将下来菜单设为只读
	function selectReadOnly(selectedId){
	  var obj = document.getElementById(selectedId);
	     obj.onmouseover = function(){
	     obj.setCapture();
	    }
	    obj.onmouseout = function(){
	     obj.releaseCapture();
	    }
	    obj.onfocus = function(){
	     obj.blur();
	    }
	    obj.onbeforeactivate = function(){
	     return false;
	    }
 	}

	function init(){
      selectReadOnly("id_select");
    }
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["financialManagement.customerInfo"].value = result[1];
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
	   		getObjByName('financialManagement.payee').value=result[2];
	   		getObjByName('dept.id').value=result[4];
		}
	}
	
	
		//业务员模态窗体弹出框
	function saleman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSalemanHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSalemanHandler(result) {
		if (null != result) {
			document.forms[0].elements["saleman.id"].value = result[0];
	   		getObjByName('financialManagement.saleman').value=result[2];
		}
	}
	
		//合同管理模态窗体
	function contractManagement_OpenDialog(){
		var over ="no";
	   var url = "${req.contextPath}/contractManagement/listContractManagementWindowAction.html?flag="+over;
	   popupModalDialog(url, 800, 600, creatorSelector3Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector3Handler(result) {
		if (null != result) {
			document.forms[0].elements["contractManagement.id"].value = result[0];
	   		getObjByName('financialManagement.contractManagement').value=result[1];
	   		getObjByName('financialManagement.customerInfo').value=result[2];
	   		document.forms[0].elements["customer.id"].value = result[4];
	   		if(null !=result[9]){
	   			getObjByName('financialManagement.accountNumber').value=result[9];
	   		}
	   		
	   		// 选择合同的时候带出，该合同下所有回款计划的批次
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联部门  
			ContractAndBatchDWR("contractManagement.id","batch.id","${action.getText('')}","false"); 
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	   		ContractAndBatchDWR(result[0],"batch.id","${action.getText('')}","false"); 
		}
	}
	function chooseFirst(){
		if(getObjByName('financialManagement.contractManagement').value==''){
			alert("${action.getText('please.choose.contractManagement.first')}");
			getObjByName('batch.id').value="";
			return false;
		}
		ContractManagementAndBatchDWR("contractManagement.id","batch.id","financialManagement.sumReceivable","financialManagement.trueSum","financialManagement.totalSum","financialManagement.withoutGotSum","trueSum","${action.getText('')}","false");
	}
	
	var trueSums="";
	function getTrueSum(){
		trueSums = getObjByName('financialManagement.trueSum').value;
		return trueSums;
	}
	function getNowSum(){
		nowSums = getObjByName('financialManagement.trueSum').value;
		return nowSums;
	}
	function chooseValue(){
		var a =getNowSum();
		var a =trueSums-a;
		getObjByName('financialManagement.totalSum').value=getObjByName('financialManagement.totalSum').value-a;
		getObjByName('financialManagement.withoutGotSum').value=getObjByName('financialManagement.withoutGotSum').value-(-a);
	}
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('batch.id').value=='' || getObjByName('batch.id').value == -1){
			alert("${action.getText('financialManagement.batch.requiredstring')}");
			getObjByName('batch.id').focus();
			return false;
		}
		if(getObjByName('financialManagement.contractManagement').value==''){
			alert("${action.getText('financialManagement.contractManagement.requiredstring')}");
			return false;
		}
		if(getObjByName('financialManagement.saleman').value==''){
			alert("${action.getText('financialManagement.saleman.requiredstring')}");
			return false;
		}
		if(getObjByName('payment.id').value==''){
			alert("${action.getText('financialManagement.payment.requiredstring')}");
			getObjByName('payment.id').focus();
			return false;
		}
		if(getObjByName('financialManagement.sumReceivable').value==''){
			alert("${action.getText('financialManagement.sumReceivable.requiredstring')}");
			getObjByName('financialManagement.sumReceivable').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('financialManagement.sumReceivable').value!=''){
	     	if(!isDoubleNumber("financialManagement.sumReceivable")){
				alert("${action.getText('sumReceivable.must.be.double')}");
				getObjByName('financialManagement.sumReceivable').value="";
				getObjByName('financialManagement.sumReceivable').focus();
				return false;
			}
	     }
		
		
		if(getObjByName('financialManagement.trueSum').value==''){
			alert("${action.getText('financialManagement.trueSum.requiredstring')}");
			getObjByName('financialManagement.trueSum').focus();
			return false;
		}
		
		 //验证费用为double类型
		if(getObjByName('financialManagement.trueSum').value!=''){
	     	if(!isDoubleNumber("financialManagement.trueSum")){
				alert("${action.getText('trueSum.must.be.double')}");
				getObjByName('financialManagement.trueSum').value="";
				getObjByName('financialManagement.trueSum').focus();
				return false;
			}
	     }
		if(getObjByName('yes').checked){
			if(getObjByName('financialManagement.invoiceCode').value==''){
			alert("${action.getText('financialManagement.invoiceCode.requiredstring')}");
			getObjByName('financialManagement.invoiceCode').focus();
			return false;
			}
		}
		
		 //验证费用为double类型
		if(getObjByName('financialManagement.totalSum').value!=''){
	     	if(!isDoubleNumber("financialManagement.totalSum")){
				alert("${action.getText('totalSum.must.be.double')}");
				getObjByName('financialManagement.totalSum').value="";
				getObjByName('financialManagement.totalSum').focus();
				return false;
			}
	     }
	     
	      //验证费用为double类型
		if(getObjByName('financialManagement.withoutGotSum').value!=''){
	     	if(!isDoubleNumber("financialManagement.withoutGotSum")){
				alert("${action.getText('withoutGotSum.must.be.double')}");
				getObjByName('financialManagement.withoutGotSum').value="";
				getObjByName('financialManagement.withoutGotSum').focus();
				return false;
			}
	     }
		
		
		
		if(getObjByName('financialManagement.collectionDate').value==''){
			alert("${action.getText('financialManagement.collectionDate.requiredstring')}");
			getObjByName('financialManagement.collectionDate').focus();
			return false;
		}
		
		if(getObjByName('financialManagement.collectionDate').value !=''){
		 	if(!isDate('financialManagement.collectionDate')){
				alert("${action.getText('financialManagement.collectionDate.dateFormate.error')}");
	      	    getObjByName('financialManagement.collectionDate').focus();
				return false;
			}
		}
		if(getObjByName('financialManagement.payee').value==''){
			alert("${action.getText('financialManagement.payee.requiredstring')}");
			return false;
		}
		
		return true;
	}
</script>
</@htmlPage>
