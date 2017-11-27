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
<@htmlPage title="${action.getText('客户服务维护页面')}">
<@ww.form name="'listForm'" action="'saveCustomerServiceAction'" method="'post'">
	<@ww.token name="saveCustomerServiceActionToken"/>
	<#if customerService.customerInfo?exists>
		<@ww.hidden  id="customerInfo.id" name="'customerInfo.id'" value="'${customerService.customerInfo.id?if_exists}'"/>
	<#else>
		<@ww.hidden  id="customerInfo.id" name="'customerInfo.id'" value="''"/>
	</#if>
	<#if customerService.salesman?exists>
		<@ww.hidden id="salesman.id" name="'salesman.id'" value="'${customerService.salesman.id?if_exists}'"/>
	<#else>
		<@ww.hidden id="salesman.id" name="'salesman.id'" value="''"/>
	</#if>
	<#if customerService.linkman?exists>
		<@ww.hidden id="linkman.id" name="'linkman.id'" value="'${customerService.linkman.id?if_exists}'"/>
	<#else>
		<@ww.hidden id="linkman.id" name="'linkman.id'" value="''"/>
	</#if>
	
	
	<#if customerService.id?exists>
		<@ww.hidden name="'customerService.id'" value="#{customerService.id}"/>
	</#if>
	<@inputTable>
		<tr>
		<@textfield id="code" label="${action.getText('编号')}" maxlength="3"  name="customerService.code"  value="${customerService.code?if_exists}"  required="false" anothername="checkcode" readonly="true"/>
		<@textfield id="serviceTitle" label="${action.getText('服务主题')}" maxlength="3"  name="customerService.serviceTitle"  value="${customerService.serviceTitle?if_exists}"  required="true" anothername="checkserviceTitle"/>
		<#assign custName = ''/>
		 	<#if customerService.customerInfo?exists>
		   		<#assign custName = "${customerService.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('客户')}:</label>
	     	</td>
	     	<td>
			<#if customerService.customerInfo?exists>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="${customerService.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="customerInfoName" name="customerInfoName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
			
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('服务人员')}:</label>
	     	</td>
	     	<td>
	     	<#if customerService.salesman?exists>
				<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="${customerService.salesman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
			<#else>
				<input type="text" id="salesmanName" name="salesmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
			</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('联系人')}:</label>
	     	</td>
	     	<td>
	     		<#if customerService.linkman?exists>
		   		<input type="text" id="linkmanName" name="linkmanName" class="underline"  value="${customerService.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" id="linkmanName"  name="linkmanName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@textfield id="code" label="${action.getText('电话')}" maxlength="3"  name="customerService.telephone"  value="${customerService.telephone?if_exists}"  required="false" anothername="checktelephone"/>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('服务类型')}'" 
				id="serviceType"
				name="'serviceType.id'" 
				value="'${req.getParameter('customerService.serviceType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allServiceType"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	<@ww.select label="'${action.getText('服务方式')}'" 
				id="serviceWay"
				name="'serviceWay.id'" 
				value="'${req.getParameter('customerService.serviceWay.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allServiceWay"
				emptyOption="true" 
				disabled="false">
			</@ww.select>		
			<#--<@textfield id="serviceType" label="${action.getText('服务类型')}" maxlength="3"  name="customerService.serviceType"  value="${customerService.serviceType?if_exists}"  required="false" anothername="checkserviceType"/>
			<@textfield id="serviceWay" label="${action.getText('服务方式')}" maxlength="3"  name="customerService.serviceWay"  value="${customerService.serviceWay?if_exists}"  required="false" anothername="checkserviceType"/>
			-->
			<@textfield id="costTime" label="${action.getText('花费时间')}" maxlength="6"  name="customerService.costTime"  value="${customerService.costTime?if_exists}"  required="false" anothername="checkcostTime"/>
			
		</tr>		
		<tr>
			<@textfield id="expense" label="${action.getText('费用')}" maxlength="10"  name="customerService.expense"  value="${customerService.expense?if_exists}"  required="false" anothername="checkexpense"/>
			<@pp.datePicker 
				label="'${action.getText('开始时间')}'" 
				name="'customerService.startTime'" 
	   			value="'${(customerService.startTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				
			<#--<@textfield id="code" label="${action.getText('状态')}" maxlength="3"  name="customerService.state"  value="${customerService.state?if_exists}"  required="true" anothername="checkstate"/>
			-->
			<@ww.select label="'${action.getText('状态')}'" 
				id="state"
				name="'state.id'" 
				value="'${req.getParameter('customerService.state.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allStates"
				emptyOption="true" 
				disabled="false">
			</@ww.select>		
		
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('服务内容')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerService.serviceContent" rows="4"  >${customerService.serviceContent?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerService.serviceContent").cols =width;
			</script>
		</tr>
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('客户反馈')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerService.customerFeedback" rows="4" >${customerService.customerFeedback?if_exists}</textarea>
	        </td>
	         <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerService.customerFeedback").cols =width;
			</script>
		</tr>
		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('对应QA')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerService.qa" rows="4"  >${customerService.qa?if_exists}</textarea>
	        </td>
	          <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerService.qa").cols =width;
			</script>
		</tr>
		
		<tr>
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('备注')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="customerService.remark" rows="4">${customerService.remark?if_exists}</textarea>
	        </td>
	          <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("customerService.remark").cols =width;
			</script>
		</tr>
		
	</@inputTable>
	<@buttonBar>
	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/customerService/listCustomerServiceAction.html"/>
    </@buttonBar>
</@ww.form>
<script language="JavaScript" type="text/JavaScript"> 
		<#-- 提交验证-->
		function storeValidation(){
			<#--if(!textfieldCheck_checkcode()){
				jgetObjByName("#code").focus();
				return false;
			}-->
			if(!textfieldCheck_checkserviceTitle()){
				jgetObjByName("#serviceTitle").focus();
				return false;
			}
			if(jgetObjByName("#customerInfoName").val()==""){
					alert("请选择客户!");
					jgetObjByName("#customerInfoName").focus();
					return false;
			}
			if(jgetObjByName("#salesmanName").val()==""){
					alert("请选择服务人员!");
					jgetObjByName("#salesmanName").focus();
					return false;
			}
			if(jgetObjByName("#linkmanName").val()==""){
					alert("请选择联系人!");
					jgetObjByName("#linkmanName").focus();
					return false;
			}
			if(jgetObjByName("#customerService\\.startTime").val()==""){
					alert("请输入开始时间");
					jgetObjByName("#customerService\\.startTime").focus();
					return false;
			}
			
			if(isNaN(jgetObjByName("#costTime").val())){
				alert("花费时间是数字!");
				jgetObjByName("#costTime").focus();
				return false;
			}
			
			
			if(isNaN(jgetObjByName("#expense").val())){
				alert("费用是数字!");
				jgetObjByName("#expense").focus();
				return false;
			}
			if(!dateCheckPicker(true,'customerService.startTime','开始时间','%Y-%m-%d')){
				return false;
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
			jgetObjByName("#customerInfo\\.id").val(result[0]);
			jgetObjByName("#customerInfoName").val(result[1]);
			
		}
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			jgetObjByName("#salesman\\.id").val(result[0]);
			jgetObjByName("#salesmanName").val(result[2]);
		}
	}
	
	//联系人查询模态窗体
	function linkman_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			getObjByName("linkman.id").value =result[0];
			getObjByName("linkmanName").value =result[2];
		}
	}
	
	jgetObjByName(function(){
		<#if customerService.serviceType?exists>
			getObjByName("serviceType").value ="${customerService.serviceType.id?if_exists}";
		</#if>
		<#if customerService.serviceWay?exists>
			getObjByName("serviceWay").value ="${customerService.serviceWay.id?if_exists}";
		</#if>
		<#if customerService.state?exists>
			getObjByName("state").value ="${customerService.state.id?if_exists}";
		</#if>
		
	});
</script>

</@htmlPage>
