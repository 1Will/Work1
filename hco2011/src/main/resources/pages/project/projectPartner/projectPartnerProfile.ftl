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

<@htmlPage title="${action.getText('合作伙伴')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProjectPartner'" method="'post'">
	<@ww.token name="saveProjectPartnerToken"/>
    <@inputTable>
    	<@ww.hidden name="'projectInfo.id'" value="'${req.getParameter('projectInfo.id')?if_exists}'"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
    	<#if projectPartner.id?exists>
    		<@ww.hidden name="'projectPartner.id'" value="#{projectPartner.id}"/>
    		<@ww.hidden name="'projectPartner.customerInfo.id'" value="#{projectPartner.customerInfo.id}"/>
	 	<#else>
    		<@ww.hidden name="'projectPartner.customerInfo.id'" value=""/>
	 	</#if>
	
    <tr>
    <!-- 合作客户 -->
		<td align="right" valign="top">
       		<span class="required">*</span>
       		<label class="label">${action.getText('客户名称')}:</label>
     	</td>
     	<td>
	     	<#if projectPartner.customerInfo?exists>
	     	<input type="text" name="projectPartner.customerInfo.name" class="underline"  value="${projectPartner.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="projectPartner.customerInfo.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
	   		
			<a onClick="customer_OpenDialog();">
				<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
			</a>
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
        	<textarea name="projectPartner.remarks" rows="4" cols="150" >${projectPartner.remarks?if_exists}</textarea>
        </td>
		<!---->
	</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
			<#if projectPartner.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProjectPartner.html?projectInfo.id=${projectInfo.id?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProjectPartner.html?projectInfo.id=${projectInfo.id?if_exists}"/>
				<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
				</script>
			</#if>
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>
    

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		
	}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?isPartner=1";
	   popupModalDialog(url, 800, 600, setCustomer);
	 }
	 //获得模态窗体返回值
	function setCustomer(result) {
		if (null != result) {
			getObjByName("projectPartner.customerInfo.id").value=result[0];
			getObjByName("projectPartner.customerInfo.name").value=result[1];
			//getObjByName('contractManagement.address').value=result[7]
		}
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('customerInfo.name').value){
			alert("${action.getText('请选择客户')}");
     		return false;
		}
		return true;
	}
	
</script>
</@htmlPage>
