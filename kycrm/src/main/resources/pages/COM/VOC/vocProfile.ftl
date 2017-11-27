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

<@htmlPage title="${action.getText('voc.profile')}">
<@ww.form namespace="'/com'" name="'com'" action="'saveVoc'" method="'post'">
	<@ww.token name="saveVocToken"/>
    <@inputTable>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'salesman.id'" value="'${req.getParameter('salesman.id')?if_exists}'"/>
    	<@ww.hidden name="'principal.id'" value="'${req.getParameter('principal.id')?if_exists}'"/>
    	<@ww.hidden name="'linkman.id'" value="'${req.getParameter('linkman.id')?if_exists}'"/>
    	<#if voc.id?exists>
    		<@ww.hidden name="'voc.id'" value="#{voc.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('voc.code')}'" name="'voc.code'" value="'${voc.code?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<@pp.datePicker 
				label="'${action.getText('voc.connectDate')}'" 
				name="'voc.connectDate'" 
	   			value="'${(voc.connectDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
				<#--
			<script language="javascript">
					var date = new Date();
					if(getObjByName("voc.createDate").value==''){
						getObjByName("voc.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>-->
			<@ww.textfield label="'${action.getText('voc.title')}'" name="'voc.title'" value="'${voc.title?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		<tr>
		<#--
			<#if voc.cInfo?exists>
				<@ww.textfield label="'${action.getText('voc.customerInfo')}'" name="'voc.customerInfo'" value="'${voc.customerInfo.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('voc.customerInfo')}'" name="'voc.customerInfo'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			-->
			<#assign custName = ''/>
		 	<#if voc.customerInfo?exists>
		   		<#assign custName = "${voc.customerInfo}" />
		 	</#if>
	     	<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('voc.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if voc.customerInfo?exists>
		   			<input type="text" name="voc.customerInfo" class="underline"  value="${voc.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="voc.customerInfo" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<@ww.textfield label="'${action.getText('voc.supplier')}'" name="'voc.supplier'" value="'${voc.supplier?if_exists}'" cssClass="'underline'"/>
			<@ww.select label="'${action.getText('voc.importance')}'" 
				name="'importance.id'" 
				value="${req.getParameter('importance.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allImportances"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<@ww.select label="'${action.getText('voc.type')}'" 
				name="'type.id'" 
				value="${req.getParameter('type.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('voc.salesman')}:</label>
	     	</td>
	     	<td>
	     		<#if voc.salesman?exists>
		   		<input type="text" name="voc.salesman" class="underline"  value="${voc.salesman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="voc.salesman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('voc.principal')}:</label>
	     	</td>
	     	<td>
	     		<#if voc.principal?exists>
		   		<input type="text" name="voc.principal" class="underline"  value="${voc.principal.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="voc.principal" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="principal_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('voc.odds')}'" name="'voc.odds'" value="'${voc.odds?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('voc.budqet')}'" name="'voc.budqet'" value="'${voc.budqet?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('voc.decisionMaker')}'" name="'voc.decisionMaker'" value="'${voc.decisionMaker?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
		<#--
			<@ww.textfield label="'${action.getText('voc.linkman')}'" name="'voc.linkman'" value="'${voc.linkman?if_exists}'" cssClass="'underline'" required="true" />
			-->
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('voc.linkman')}:</label>
	     	</td>
	     	<td>
	     		<#if voc.linkman?exists>
		   		<input type="text" name="voc.linkman" class="underline"  value="${voc.linkman.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="voc.linkman" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="linkman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.textfield label="'${action.getText('voc.phone')}'" name="'voc.phone'" value="'${voc.phone?if_exists}'" cssClass="'underline'" required="true" />
			<@ww.textfield label="'${action.getText('voc.email')}'" name="'voc.email'" value="'${voc.email?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
			<@pp.datePicker 
					label="'${action.getText('voc.nextTime')}'" 
					name="'voc.nextTime'" 
		   			value="'${(voc.nextTime?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>
			<@ww.select label="'${action.getText('voc.status')}'" 
				name="'status.id'" 
				value="${req.getParameter('status.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.select label="'${action.getText('voc.resource')}'" 
				name="'resource.id'" 
				value="${req.getParameter('resource.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allResources"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
        </tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('voc.resolvent')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="voc.resolvent" rows="4"  >${voc.resolvent?if_exists}</textarea>
			</td>
			<script language="javascript">
			var width=document.body.clientWidth/9;
						getObjByName("voc.resolvent").cols =width;
		   </script>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('voc.feedback')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="voc.feedback" rows="4"  >${voc.feedback?if_exists}</textarea>
			</td>
			<script language="javascript">
			var width=document.body.clientWidth/9;
						getObjByName("voc.feedback").cols =width;
		   </script>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('voc.content')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="voc.content" rows="4"  >${voc.content?if_exists}</textarea>
			</td>
			<script language="javascript">
			var width=document.body.clientWidth/9;
						getObjByName("voc.content").cols =width;
		   </script>
		</tr>
    </@inputTable>
    <@buttonBar>
	    <#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	    </#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/com/listVoc.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if voc.importance?exists>
			getObjByName('importance.id').value='${voc.importance.id?if_exists}';
		</#if>
		<#if voc.status?exists>
			getObjByName('status.id').value='${voc.status.id?if_exists}';
		</#if>
		<#if voc.type?exists>
			getObjByName('type.id').value='${voc.type.id?if_exists}';
		</#if>
		<#if voc.resource?exists>
			getObjByName('resource.id').value='${voc.resource.id?if_exists}';
		</#if>
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
		 	document.forms[0].elements["voc.customerInfo"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		}
	}
	
	//弹出负责人查询模态窗体
	function principal_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorPrincipalHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPrincipalHandler(result) {
		if (null != result) {
			document.forms[0].elements["principal.id"].value = result[0];
	   		getObjByName('voc.principal').value=result[2];
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
			document.forms[0].elements["salesman.id"].value = result[0];
	   		getObjByName('voc.salesman').value=result[2];
		}
	}
	
	//联系人查询模态窗体
	function linkman_OpenDialog(){
	   var url = "${req.contextPath}/com/listContactArchivesWindow.html";
	   popupModalDialog(url, 800, 600, creatorSelector2Handler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelector2Handler(result) {
		if (null != result) {
			document.forms[0].elements["linkman.id"].value = result[0];
	   		getObjByName('voc.linkman').value=result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('voc.connectDate').value !=''){
		 	if(!isDate('voc.connectDate')){
				alert("${action.getText('voc.connectDate.dateFormate.error')}");
				getObjByName('voc.connectDate').value="";
	      	    getObjByName('voc.connectDate').focus();
				return false;
			}
		}
		if(getObjByName('voc.title').value ==''){
			alert("${action.getText('voc.title.requiredstring')}");
			getObjByName('voc.title').focus();
			return false;
		}
		if(getObjByName('voc.customerInfo').value ==''){
			alert("${action.getText('voc.customerInfo.requiredstring')}");
			return false;
		}
		if(getObjByName('importance.id').value ==''){
			alert("${action.getText('voc.importance.requiredstring')}");
			getObjByName('importance.id').focus();
			return false;
		}
		if(getObjByName('type.id').value ==''){
			alert("${action.getText('voc.type.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}
		if(getObjByName('voc.principal').value ==''){
			alert("${action.getText('voc.principal.requiredstring')}");
			return false;
		}
		
		if(getObjByName('voc.principal').value ==''){
			alert("${action.getText('voc.principal.requiredstring')}");
			return false;
		}
		//验证费用为double类型
		if(getObjByName('voc.odds').value!=''){
	     	if(!isDoubleNumber("voc.odds")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('voc.odds').value="";
				getObjByName('voc.odds').focus();
				return false;
			}
	     }
	     
	     //验证费用为double类型
		if(getObjByName('voc.budqet').value!=''){
	     	if(!isDoubleNumber("voc.budqet")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('voc.budqet').value="";
				getObjByName('voc.budqet').focus();
				return false;
			}
	     }
	     
		if(getObjByName('voc.linkman').value ==''){
			alert("${action.getText('voc.linkman.requiredstring')}");
			return false;
		}
		if(getObjByName('voc.phone').value ==''){
			alert("${action.getText('voc.phone.requiredstring')}");
			getObjByName('voc.phone').focus();
			return false;
		}
		
		if(getObjByName('voc.nextTime').value ==''){
		 		alert("${action.getText('voc.nextTime.not.null')}");
		 		getObjByName('voc.nextTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('voc.nextTime')){
				alert("${action.getText('voc.nextTime.dateFormate.error')}");
				getObjByName('voc.nextTime').value="";
	      	    getObjByName('voc.nextTime').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('voc.nextTime').value)){
				alert('${action.getText('voc.nextTime.earlyError')}');
				getObjByName('voc.nextTime').value="";
	       		getObjByName('voc.nextTime').focus();
				return false;
			}
		}
		if(getObjByName('status.id').value ==''){
			alert("${action.getText('voc.status.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		if(getObjByName('voc.content').value ==''){
			alert("${action.getText('voc.content.requiredstring')}");
			getObjByName('voc.content').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
