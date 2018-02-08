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

<#--$Id: codeValueProfile.ftl 11326 2008-03-15 06:48:54Z wzou $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('发货单维护页面')}">
     <@ww.form  name="'listForm'" action="'saveDelivery'" namespace="'/delivery'" method="'post'" >
         <@ww.token name="saveDliveryToken"/>
            <#if invoices.customerInfo?exists>
         		<@ww.hidden name="'customerInfo.id'" value="#{invoices.customerInfo.id?if_exists}"/>
         	<#else>
         		<@ww.hidden name="'customerInfo.id'"/>
         	</#if>
         	<#if invoices.deliveryPerson?exists>
         		<@ww.hidden name="'deliveryPerson.id'" value="#{invoices.deliveryPerson.id?if_exists}"/>
         	<#else>
         		<@ww.hidden name="'deliveryPerson.id'"/>
         	</#if>
    		<@ww.hidden name="'department.id'" value="''"/>
    		<@ww.hidden name="'invoices.isSaved'" value="'${invoices.isSaved?if_exists}'"/>
    		<#if invoices.id?exists>
				<@ww.hidden name="'invoices.id'" value="#{invoices.id?if_exists}"/>
			</#if>
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('发货单编码')}'" name="'invoices.deliveryNum'" value="'${invoices.deliveryNum?if_exists}'" cssClass="'underline'" required="true"/>
                 <@pp.datePicker label="'${action.getText('发货日期')}'" 
	            		   name="'invoices.deliveryDate'"
	     				   value="'${(invoices.deliveryDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     		<script language="javascript">
					var date = new Date();
					if(getObjByName("invoices.deliveryDate").value==''){
						getObjByName("invoices.deliveryDate").value = date.format("yyyy-MM-dd");
					}
		  	   </script>
	     		 <@ww.select label="'${action.getText('发货方式')}'" 
					id="deliveryWay.id" 
					name="'deliveryWay.id'" 
					value="'${req.getParameter('deliveryWay.id')?if_exists}'"
					listKey="id"
					listValue="name"
					list="allDeliveryWay"
					required="true"
					emptyOption="false" 
					disabled="false">
				</@ww.select>
             </tr> 
             <tr>
             	<td align="right" valign="top">
             		<span class="required">*</span>
		       		<label class="label">${action.getText('客户名称')}:</label>
		     	</td>
		     	<td>
					<input type="text" name="customerInfo.name" class="underline"  value="<#if invoices.customerInfo?exists>${invoices.customerInfo.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
					<a onClick="customer_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				<td align="right" valign="top">
             		<span class="required">*</span>
		       		<label class="label">${action.getText('联系人')}:</label>
		     	</td>
		     	<td>
		     		<@ww.hidden name="'invoices.contacter'" value="'${invoices.contacter?if_exists}'"/>
					<input type="text" name="invoices.contacterShow" class="underline"  value="${invoices.contacter?if_exists}" maxlength="140" size="20" disabled="true"/>
					<a onClick="linkman_OpenDialog()">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
                <@ww.textfield label="'${action.getText('联系电话')}'" name="'invoices.telephone'"  value="'${invoices.telephone?if_exists}'" required="true" cssClass="'underline'" />
              </tr>
             <tr>
				<@ww.textfield label="'${action.getText('发货数量')}'" name="'invoices.deliveryCount'" readonly="true" value="'#{invoices.deliveryCount?if_exists}'" cssClass="'underline'" />
				<@ww.textfield label="'${action.getText('发货金额')}'" name="'invoices.deliveryPrice'" readonly="true" value="'#{invoices.deliveryPrice?if_exists}'" cssClass="'underline'" required="false" />
                <@ww.select label="'${action.getText('发货状态')}'" 
					id="deliveryStatus.id" 
					name="'deliveryStatus.id'" 
					value="'${req.getParameter('deliveryStatus.id')?if_exists}'"
					listKey="id"
					listValue="name"
					list="allDeliveryStatus"
					required="false"
					emptyOption="false" 
					disabled="false">
				</@ww.select>
              </tr>
             <tr>
	     		<td align="right" valign="top">
		       		<span class="required">*</span>
		       		<label class="label">${action.getText('负责人')}:</label>
		     	</td>
		     	<td>
		     	<input type="text" name="deliveryPerson.name" class="underline"  value="<#if invoices.deliveryPerson?exists>${invoices.deliveryPerson.name?if_exists}</#if>" maxlength="140" size="20" disabled="true"/>
					<a onClick="salesman_OpenDialog();">
						<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
					</a>
				</td>
				<!--负责人部门-->
				<@ww.select label="'${action.getText('部门')}'" 
					name="'departmentShow'" 
					value=""
					listKey="id"
					listValue="name"
					list="allDepartment"
					required="false"
					emptyOption="true" 
					disabled="true">
				</@ww.select>
				<@ww.textfield label="'${action.getText('联系方式')}'" name="'invoices.contactWay'" value="'${invoices.contactWay?if_exists}'" cssClass="'underline'" required="false" />
             </tr>
             <tr>
				<@pp.datePicker label="'${action.getText('回单日期')}'" 
	            		   name="'invoices.receiptDate'"
	     				   value="'${(invoices.receiptDate?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="false" 
	     				   maxlength="10"/>
             </tr>
             <tr>
             	<td align="right" valign="top">
		       		<label class="label">${action.getText('发货地址')}:</label>
		     	</td>
				<td colspan="2">
					<input type="text" name="invoices.address" class="underline"  value="${invoices.delivreyAddress?if_exists}" maxlength="100" size="80" />
				</td>
             </tr>
             <tr>
             	<!--备注-->
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('备注')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="invoices.mark" rows="4" cols="150" >${invoices.mark?if_exists}</textarea>
		        </td>
				<!---->
             </tr>
         </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
		         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		       
		        <#if invoices.isSaved?exists &&invoices.isSaved=='0' >
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  >
		            </@vsubmit>
		        <#else>
		            <@vsubmit name="'save'" value="'${action.getText('refer')}'" onclick="'return submitt();'"  disabled="true">
		            </@vsubmit>
		        </#if>	
		        <#if notNewFlag?exists&&notNewFlag=='notNewFlag'>
				<#else>
					<#-- 继续新建按钮   -->
					<#if invoices.id?exists>
					<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/delivery/saveDelivery.html"/>
					<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/delivery/saveDelivery.html"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
					</#if>
				</#if>
		        <#--<@vbutton class="button" value="${action.getText('签回收单')}" onclick="closeThis()"/>-->
		     </#if>
	         <#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
			<!-- 关闭按钮 -->
			<@vbutton name="close" value="'${action.getText('close')}'" class="button" onclick="closeThis();"/>
	  		<#else>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/delivery/listDeliveryTemplet.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	  		</#if>
	  		
         </@buttonBar>	
     </@ww.form>
     <script type="text/javascript">
     /*选择框默认数据*/
	<#if invoices.department?exists>
	getObjByName("departmentShow").value = '${invoices.department.id}';
	getObjByName("department.id").value = '${invoices.department.id}';
	</#if>
	<#if invoices.department?exists>
	getObjByName("department.id").value = '${invoices.department.id}';
	</#if>
	<#if invoices.deliveryStatus?exists>
	getObjByName("deliveryStatus.id").value = '${invoices.deliveryStatus.id}';
	</#if>
	<#if invoices.deliveryWay?exists>
	getObjByName("deliveryWay.id").value = '${invoices.deliveryWay.id}';
	</#if>
	
	
     //弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelector1Handler);
	 }
	 //获得模态窗体返回值
	function creatorSelector1Handler(result) {
		if (null != result) {
			var length = result.length;
		 	document.forms[0].elements["customerInfo.id"].value = result[0];
		 	document.forms[0].elements["customerInfo.name"].value = result[1];
		 	document.forms[0].elements["invoices.contacterShow"].value = result[length-4];
		 	document.forms[0].elements["invoices.contacter"].value = result[length-4];
		 	document.forms[0].elements["invoices.telephone"].value = result[length-2];
		 	if("" == result[length-2]){
		 		document.forms[0].elements["invoices.telephone"].value = result[length-3];
		 	}
		 	document.forms[0].elements["invoices.address"].value = result[length-1];
		}
	}
     //弹出负责人查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["deliveryPerson.id"].value = result[0];
			document.forms[0].elements["deliveryPerson.name"].value = result[2];
	   		getObjByName("departmentShow").value = result[4];
	   		getObjByName("department.id").value = result[4];
	   		getObjByName("invoices.contactWay").value = result[5];
		}
	}
	//联系人查询模态窗体
	function linkman_OpenDialog(){
		if(getObjByName('customerInfo.id').value !=''){
			var url = "${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisitFlag=backVisit&customer.id="+getObjByName('customerInfo.id').value;
			popupModalDialog(url, 800, 600, creatorSelector2Handler);
		}else{
			alert('请先选择客户');
		}
	 }
	  //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			getObjByName("customerInfo.id").value=(result[0]);
			getObjByName("invoices.contacter").value=(result[1]);
			if(result[2] !=''){
				getObjByName("invoices.telephone").value=(result[2]);
			}else{
				getObjByName("invoices.telephone").value=(result[3]);
			}
		}
	}
	function submitt(){
		getObjByName('invoices.isSaved').value="1";
		return storeValidation();
	}
	
	function savee(){
		getObjByName('invoices.isSaved').value="0";
		return storeValidation();
	}
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('invoices.deliveryNum').value == ""){
			alert('发货单编码不能为空！');
			getObjByName('invoices.deliveryNum').focus()
			return false;
		}
		if(getObjByName('invoices.deliveryDate').value == ""){
			alert('发货日期不能为空！');
			getObjByName('invoices.deliveryDate').focus()
			return false;
		}
		if(getObjByName('deliveryWay.id').value == ""){
			alert('发货方式不能为空！');
			getObjByName('deliveryWay.id').focus()
			return false;
		}
		if(getObjByName('customerInfo.id').value == ""){
			alert('客户名称不能为空！');
			getObjByName('customerInfo.id').focus()
			return false;
		}
		if(getObjByName('invoices.contacter').value == ""){
			alert('联系人不能为空！');
			getObjByName('invoices.contacter').focus()
			return false;
		}
		if(getObjByName('invoices.telephone').value == ""){
			alert('联系电话不能为空！');
			getObjByName('invoices.telephone').focus()
			return false;
		}
		if(getObjByName('deliveryPerson.id').value == ""){
			alert('负责人不能为空!');
			getObjByName('deliveryPerson.id').focus()
			return false;
		}
		if("" != getObjByName('invoices.deliveryDate').value){
			if(!isDate('invoices.deliveryDate')){
				alert("发货日期：${action.getText('dateTimeFormat.error')}");
				getObjByName('invoices.deliveryDate').value = "";
				getObjByName('invoices.deliveryDate').focus();
				return false;
			}
		}
		if("" != getObjByName('invoices.receiptDate').value){
			if(!isDate('invoices.receiptDate')){
				alert("回单日期：${action.getText('dateTimeFormat.error')}");
				getObjByName('invoices.receiptDate').value = "";
				getObjByName('invoices.receiptDate').focus();
				return false;
			}
		}
		//验证开始日期是否大于终止时间
		var star = getObjByName('invoices.deliveryDate').value;
		var end = getObjByName('invoices.receiptDate').value;
		if(star != "" && end != ""){
			if(isDateLessThenOldDate(star,end)){
				alert('回单日期不能早于发货日期！');
				getObjByName('invoices.receiptDate').focus();
				return false;
			}
		}
		return true;
	}
    </script>
</@htmlPage>
<#if invoices.id?exists>
<ul id="beautytab">
	<li>
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/delivery/listInvoicesList.html?invoices.id=#{invoices.id?if_exists}&customerInfo.id=#{invoices.customerInfo.id?if_exists}' target="frame" >${action.getText('发货明细')}</a>
	</li>
	<li>
		<a id="contractPlan" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?invoices.id=#{invoices.id?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/delivery/listInvoicesList.html?invoices.id=#{invoices.id?if_exists}&customerInfo.id=#{invoices.customerInfo.id?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>