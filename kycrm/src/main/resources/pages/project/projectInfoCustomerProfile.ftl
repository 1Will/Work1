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

<@htmlPage title="${action.getText('projectInfo.proCus')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProCus'" method="'post'">
	<@ww.token name="saveProjectInfoCusToken"/>
    <@inputTable>
    	<@ww.hidden name="'projectInfo.id'" value="'${projectInfoId?if_exists}'"/>
    	<@ww.hidden name="'customerInfo.id'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectInfoCustomer.id?exists>
    		<@ww.hidden name="'projectInfoCustomer.id'" value="#{projectInfoCustomer.id}"/>
	 	</#if>
    <tr>
    <!--项目客户-->
    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfoCustomer.proCus')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfoCustomer.customerInfo?exists>
	     	<input type="text" name="customerInfo.name" class="underline"  value="${projectInfoCustomer.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="customerInfo.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
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
        			${action.getText('proCusOutline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfoCustomer.outline" rows="4" cols="150" >${projectInfoCustomer.outline?if_exists}</textarea>
	        </td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		
		<#-- 继续新建按钮   -->
			<#if projectInfoCustomer.id?exists>
			<@redirectButton value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProCus.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfo.id=${projectInfoId?if_exists}"/>
			<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" 
				url="${req.contextPath}/projectInfo/editProCon.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<script language="JavaScript" type="text/JavaScript"> 
			getObjByName("newNext").disabled="true";
			</script>
			</#if>
		</#if>
		
		<input class="button" type="button" value="${action.getText('close')}"  onclick="closeThis();">
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerCustomer);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerCustomer(result) {
		if (null != result) {
		 	document.forms[0].elements["customerInfo.id"].value = result[0];	
		 	document.forms[0].elements["customerInfo.name"].value = result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		/* 验证项目组成员*/
		if('' == getObjByName('customerInfo.name').value){
			alert("${action.getText('customerInfo.name.required')}");
     		return false;
		}
	
		return true;
	}
	
</script>
</@htmlPage>
