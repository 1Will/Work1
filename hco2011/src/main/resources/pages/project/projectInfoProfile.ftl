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

<@htmlPage title="${action.getText('projectInfo.profile')}">
<@ww.form namespace="'/projectInfo'" name="'projectInfo'" action="'saveProjectInfo'" method="'post'">
	<@ww.token name="saveProjectInfoToken"/>
    <@inputTable>
    	<@ww.hidden name="'stateId'" value="''"/>
    	<@ww.hidden name="'controller.id'" value="''"/>
    	<@ww.hidden name="'projectInfo.isSaved'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if projectInfo.id?exists>
    		<@ww.hidden name="'contact.id'" value="'#{projectInfo.contact.id?if_exists}'"/>
    		<@ww.hidden name="'projectInfo.id'" value="#{projectInfo.id}"/>
    		<@ww.hidden name="'customer.id'" value="'#{projectInfo.customer.id?if_exists}'"/>
	 	<#else>
    		<@ww.hidden name="'contact.id'" value="''"/>
    		<@ww.hidden name="'customer.id'" value="''"/>
	 	</#if>
	
    
     <tr>
	    <!--项目编码-->
	    <@ww.textfield label="'${action.getText('code')}'" name="'projectInfo.code'" value="'${projectInfo.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" />
	     <!--客户名称-->
	    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfo.customerInfoName')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfo.customer?exists>
	     	<input type="text" name="customer.name" class="underline"  value="${projectInfo.customer.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="customer.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
		   		
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
    </tr>
    <tr>
		   	<!--项目名称-->
		    <@ww.textfield label="'${action.getText('name')}'" name="'projectInfo.name'" value="'${projectInfo.name?if_exists}'" cssClass="'underline'" required="true" />
			<!--联系人-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfo.contact')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfo.contact?exists>
	     	<input type="text" name="contact.name" class="underline"  value="${projectInfo.contact.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="contact.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
		   		
				<a onClick="contactArchive_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.select label="'${action.getText('业务属性')}'" 
				name="'businessType.id'" 
				value="'${req.getParameter('businessType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBusinessType"
				required="true"
				emptyOption="false" 
				>
			</@ww.select>
			<script language="javascript">
			<#if projectInfo.businessType?exists>
				getObjByName('businessType.id').value = '${projectInfo.businessType.id?if_exists}';
			</#if>
			</script>
	    
    </tr>
     <tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('联系人角色说明')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfo.conOutline" rows="4" cols="150" >${projectInfo.conOutline?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
    
    <tr>
    <!--项目负责人-->
    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('projectInfo.controller')}:</label>
	     	</td>
	     	<td>
	     	<#if projectInfo.controller?exists>
	     	<input type="text" name="controller.name" class="underline"  value="${projectInfo.controller.name?if_exists}" maxlength="140" size="20" disabled="true"/>
	     	<#else>
	     	<input type="text" name="controller.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
	     	</#if>
		   		
				<a onClick="salesman_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.select label="'${action.getText('项目状态')}'" 
				name="'state.id'" 
				value="'${req.getParameter('state.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allStates"
				required="true"
				emptyOption="false" 
				disabled="true">
			</@ww.select>
			<script language="javascript">
			<#if req.getParameter('projectInfo.state')?exists>
				getObjByName('state.id').value = '${projectInfo.state.id?if_exists}';
			</#if>
		</script>
    </tr>
    <tr>
			<!--备注-->
			<td align="right" valign="top">
        		<label class="label">
        			${action.getText('outline')}:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="projectInfo.outline" rows="4" cols="150" >${projectInfo.outline?if_exists}</textarea>
	        </td>
			<!---->
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		</#if>
		
		<#if projectInfo.isSaved?exists &&projectInfo.isSaved=='0' >
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
	    <#else>
	    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
	    </#if>
	
	<#if notNewFlag?exists&&notNewFlag==notNewFlag>
	<#else>
		<#-- 继续新建按钮   -->
		<#if projectInfo.id?exists>
			<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/projectInfo/editProjectInfo.html"/>
		<#else>
			<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/projectInfo/editProjectInfo.html"/>
				<script language="JavaScript" type="text/JavaScript"> 
				getObjByName("newNext").disabled="true";
				</script>
		</#if>
	</#if>
		 
		<#if openFlag?exists>
		<@vbutton name="close" value="${action.getText('关闭')}" class="button" onclick="closeThis();"/>
       <#else>
       <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/projectInfo/listProjectInfo.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       </#if>
		
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
	    <#if projectInfo.state?exists>
			getObjByName('state.id').value='${projectInfo.state.id?if_exists}';
			<#elseif req.getParameter('state.id')?exists>
			getObjByName('state.id').value='${req.getParameter('state.id')}';
		</#if>
		
		
	}
	
	
	
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerCustomer);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerCustomer(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["customer.name"].value = result[1];
		}
	}
	
	//弹出业务员查询模态窗体
	function salesman_OpenDialog(){
	   var url =  "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["controller.id"].value = result[0];
	   		document.forms[0].elements["controller.name"].value = result[2];		 	
		}
	}
	
	function contactArchive_OpenDialog(){
		if(getObjByName('customer.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitFlag=backVisit&customer.id="+getObjByName('customer.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerContactArchives);
		}else{
			alert('请先选择客户');
		}
	}
	function creatorSelectorHandlerContactArchives(result) {
		if (null != result) {
		 	document.forms[0].elements["contact.id"].value = result[0];	
		 	document.forms[0].elements["contact.name"].value = result[1];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
	/* 验证项目名称*/
		if('' == getObjByName('projectInfo.name').value){
			alert("${action.getText('name.required')}");
     		return false;
		}
		/* 验证客户名称*/
		if('' == getObjByName('customer.name').value){
			alert("${action.getText('customer.name.required')}");
     		return false;
		}
		/* 验证项目联系人*/
		if('' == getObjByName('contact.name').value){
			alert("${action.getText('contact.name.required')}");
     		return false;
		}
		/* 验证项目负责人*/
		if('' == getObjByName('controller.name').value){
			alert("${action.getText('controller.name.required')}");
     		return false;
		}
		/* 验证项目概要 */
 		if(''!= getObjByName('projectInfo.outline').value&&!isValidLengthValue(getObjByName('projectInfo.outline').value,0,500)){
 			alert("${action.getText('outline.maxLength')}");
 			getObjByName('projectInfo.outline').value="";
 			getObjByName('projectInfo.outline').focus();
 			return false;
 		}
		return true;
	}
	
	function savee(){
     	getObjByName('saveProjectInfo_projectInfo.isSaved').value=0;
		return storeValidation();
	}
	function submitt(){
     	getObjByName('saveProjectInfo_projectInfo.isSaved').value=1;
		return storeValidation();
	}
	
</script>
</@htmlPage>
<#if projectInfo.id?exists>
<ul id="beautytab">
	<li>
		<a id="customers" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProCus.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('projectInfo.customers')}</a>
	</li>
	<li>
		<a id="contactArchives" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProCon.html?projectInfo.id=#{projectInfo.id}&customerInfo.id=#{projectInfo.customer.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('projectInfo.contactArchives')}</a>
	</li>																																																				
	<li>
		<a id="projectPer" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProPer.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('projectInfo.personnelFiles')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >附件资料</a>
	</li>
	<li>
		<a id="product" onclick="activeTab(this);" href='${req.contextPath}/projectInfo/listProPro.html?projectInfo.id=#{projectInfo.id}&customerInfo.id=#{projectInfo.customer.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('projectInfo.product')}</a>
	</li>
	<li>
		<a id="plan" onclick="activeTab(this);" href='${req.contextPath}/projectInfo/listProPlan.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >项目总计划</a>
	</li>
	<li>
		<a id="backvisit" onclick="activeTab(this);" href='${req.contextPath}/backvisit/listBackVisitByContact.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('projectInfo.backvisit')}</a>
	</li>
	
	<li>
		<a id="weekPlanInfo" onclick="activeTab(this);"  href='${req.contextPath}/workReport/listWeekPlanTab.html?projectInfo.id=#{projectInfo.id}' target="frame" >${action.getText('项目周计划')}</a>
	</li>
	<#-- 
	<li>
		<a id="projectPartner" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProjectPartner.html?projectInfo.id=#{projectInfo.id}' target="frame" >${action.getText('合作伙伴')}</a>
	</li>
	<li>
		<a id="projectPartner" onclick="activeTab(this);"  href='${req.contextPath}/projectInfo/listProjectContactArchives.html?projectInfo.id=#{projectInfo.id}' target="frame" >${action.getText('合作伙伴项目组成员')}</a>
	</li>
	-->
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/projectInfo/listProCus.html?projectInfo.id=#{projectInfo.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>

