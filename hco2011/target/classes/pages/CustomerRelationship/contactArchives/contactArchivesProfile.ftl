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

<@htmlPage title="${action.getText('contactArchives.profile')}">
<@ww.form namespace="'/customerRelationship'" name="'contactArchivesInfo'" action="'saveContactArchives'" method="'post'">
	<@ww.token name="saveContactArchivesToken"/>
    <@inputTable>
    	<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>
    	<#--<@ww.hidden name="'temperament'" value="'${req.getParameter('temperament')?if_exists}'"/>-->
    	<#--<@ww.hidden name="'type'" value="'${req.getParameter('type')?if_exists}'"/>-->
    	<#if contactArchives.customerName?exists>
    	<@ww.hidden name="'customer.id'" value="#{contactArchives.customerName.id}"/>
    	<#else>
    	<@ww.hidden name="'customer.id'" value="''"/>
    	</#if>
    	<#if contactArchives.projectInfo?exists>
    	<@ww.hidden name="'projectInfo.id'"  value="#{contactArchives.projectInfo.id}"/>
    	<#else>
    	<@ww.hidden name="'projectInfo.id'" value="''"/>
    	</#if>
    	<@ww.hidden name="'leader.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if contactArchives.id?exists>
    		<@ww.hidden name="'contactArchives.id'" value="#{contactArchives.id}"/>
	 	</#if>
	
		<!--页面重新排版wclin 11.7.6————————————————————————————————————————————————————————————————-->
    <!--@@基本信息@@-->
    <tr>
	    <!--姓名-->
	    <@ww.textfield label="'${action.getText('contactArchives.name')}'" name="'contactArchives.name'" value="'${contactArchives.name?if_exists}'" cssClass="'underline'" required="true" />
	    <!--性别-->
	    <td align="right"><label for="" class="label">${action.getText('contactArchives.sex')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="man" name="contactArchivesSex" value="0" />男
	        	<input type="radio" id="woman" name="contactArchivesSex" value="1" />女
		</td>
    </tr>
     <tr>
      <!--所属公司-->
	    <td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('contactArchives.customerName')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contactArchives.custName" class="underline"  value="${contactArchives.custName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
	   
	    <!--直属领导-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('contactArchives.leader')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="leader" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				<a onClick="contactArchive_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
	    
    </tr>
    <tr>
     <!--称呼-->
	    <@ww.textfield label="'${action.getText('contactArchives.honorific')}'" name="'contactArchives.honorific'" value="'${contactArchives.honorific?if_exists}'" cssClass="'underline'"  />
			<!--行业-->
   		<@ww.textfield label="'${action.getText('contactArchives.industry')}'" name="'contactArchives.industry'" value="'${contactArchives.industry?if_exists}'" cssClass="'underline'" required="true" disabled="true"/>
    </tr>
     <tr>
     <!--所属项目-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('contactArchives.projectInfo')}:</label>
	     	</td>
	     	<td>
		   		<input type="text" name="contactArchives.proName" class="underline"  value="${contactArchives.proName?if_exists}" maxlength="140" size="20" disabled="true"/>
				<a onClick="projectInfo_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
			<@ww.select label="'${action.getText('业务属性')}'" 
				name="'businessType.id'" 
				value="'${req.getParameter('businessType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBusinessType"
				emptyOption="false" 
				>
			</@ww.select>
			<script language="javascript">
			<#if contactArchives.businessType?exists>
				getObjByName('businessType.id').value = '${contactArchives.businessType.id?if_exists}';
			</#if>
			</script>
   		
   		</tr>
   		<tr>
		<!--印象描述-->
		<td align="right" valign="top">
        		<label class="label">
        			${action.getText('联系人角色说明')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="contactArchives.outline" rows="3" cols="120" >${contactArchives.outline?if_exists}</textarea>
	        </td>
		<!---->
	</tr>
    
    <tr>
     <!--部门-->
	    <@ww.textfield label="'${action.getText('contactArchives.dept')}'" name="'contactArchives.dept'" value="'${contactArchives.dept?if_exists}'" cssClass="'underline'" />
	    <!--职位-->
	    <@ww.textfield label="'${action.getText('contactArchives.duty')}'" name="'contactArchives.duty'" value="'${contactArchives.duty?if_exists}'" cssClass="'underline'"/>
	   
	    
    </tr>
    <tr>
    <!--身份证-->
    <@ww.textfield label="'${action.getText('contactArchives.idCard')}'" name="'contactArchives.idCard'" value="'${contactArchives.idCard?if_exists}'" cssClass="'underline'"  />
    </tr>
   
   <tr>
		<!--印象描述-->
		<td align="right" valign="top">
			<span class="required">*</span>
        		<label class="label">
        			${action.getText('contactArchives.enterpriseSynopsis')}:
        		</label>
        	</td>
	        <td colspan="5">
	        	<textarea name="contactArchives.enterpriseSynopsis" rows="3" cols="120" >${contactArchives.enterpriseSynopsis?if_exists}</textarea>
	        </td>
		<!---->
	</tr>
   
    <tr>
	    <!--熟悉程度-->
	    <@ww.select label="'${action.getText('contactArchives.caType')}'" 
				name="'type.id'" 
				value="'${req.getParameter('type.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTypes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	    <!--信息完整度-->
        <@textfield label="${action.getText('contactArchives.customerInfoIntegrity')}" name="contactArchives.customerInfoIntegrity"  value="${contactArchives.customerInfoIntegrity?if_exists}" required="false" cssClass="underline" maxlength="20" readonly="true"/>		
    </tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if contactArchives.sex==false>
			getObjByName('man').checked=true;
		<#else>
		    getObjByName('woman').checked=true;
		</#if>
		<#if contactArchives.type?exists>
			getObjByName('type.id').value='${contactArchives.type.id?if_exists}';
		</#if>
		<#if contactArchives.leader?exists>
			getObjByName('leader.id').value='#{contactArchives.leader.id}';
			getObjByName('leader').value='${contactArchives.leader.name?if_exists}';
		</#if>
	}
	
	//弹出客户档案查询模态窗体
	function customer_OpenDialog(){
	   var url = "${req.contextPath}/customerRelationship/listCustInfo.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
		 	document.forms[0].elements["customer.id"].value = result[0];	
		 	document.forms[0].elements["contactArchives.custName"].value = result[1];
		 	document.forms[0].elements["customerType.id"].value = result[2];
		 	document.forms[0].elements["contactArchives.industry"].value = result[4];
		 	//document.forms[0].elements["contactArchives.enterpriseSynopsis"].value = result[10];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		//----------------------------------------------------------------------
		if(getObjByName('contactArchives.name').value==''){
	        alert('${action.getText('contactArchives.name.not.null')}');
	        getObjByName('contactArchives.name').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "contactArchives.name", null, 20)){
				alert("${action.getText('contactArchives.name.length')}");
				getObjByName('contactArchives.name').value="";
				getObjByName('contactArchives.name').focus();
				return false;
			}
		}
	
		if(getObjByName('contactArchives.custName').value==''){
	        alert('${action.getText('contactArchives.custName.not.null')}');
	        return false;
	     }
	     if(getObjByName('contactArchives.industry').value==''){
	        alert('${action.getText('contactArchives.industry.not.null')}');
	        getObjByName('contactArchives.industry').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "contactArchives.industry", null, 20)){
				alert("${action.getText('contactArchives.industry.length')}");
				getObjByName('contactArchives.industry').value="";
				getObjByName('contactArchives.industry').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.dept').value != ''){
	        if(!isValidLength(document.forms[0], "contactArchives.dept", null, 20)){
				alert("${action.getText('contactArchives.dept.length')}");
				getObjByName('contactArchives.dept').value="";
				getObjByName('contactArchives.dept').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.duty').value!=''){
    		if(!isValidLength(document.forms[0], "contactArchives.duty", null, 20)){
				alert("${action.getText('contactArchives.duty.length')}");
				getObjByName('contactArchives.duty').value="";
				getObjByName('contactArchives.duty').focus();
				return false;
			}
 		}
 		
		<#-- 印象描述-->
	    if(getObjByName('contactArchives.enterpriseSynopsis').value==""){
			alert('${action.getText('contactArchives.enterpriseSynopsis.not.null')}');
			getObjByName('contactArchives.enterpriseSynopsis').focus();
			return false;
		}
		
		if(getObjByName('type.id').value==""){
	        alert('${action.getText('contactArchives.type.not.null')}');
	        getObjByName('type.id').value="";
	        getObjByName('type.id').focus();
	        return false;
	     }
		return true;
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
		 	document.forms[0].elements["leader.id"].value = result[0];	
		 	document.forms[0].elements["leader"].value = result[1];
		}
	}
	function projectInfo_OpenDialog(){
		if(getObjByName('customer.id').value !=''){
			var  url = "${req.contextPath}/projectInfo/listProjectInfo.html?contactArchivesFlag=contactArchivesFlag&customer.id="+getObjByName('customer.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerProjectInfo);
		}else{
			alert('请先选择客户');
		}
	}
	
	function creatorSelectorHandlerProjectInfo(result) {
		if (null != result) {
		 	document.forms[0].elements["projectInfo.id"].value = result[0];	
		 	document.forms[0].elements["contactArchives.proName"].value = result[1];
		}
	}
</script>
</@htmlPage>
<#if contactArchives.id?exists>
<ul id="beautytab">
	<li>
		<a id="contactInformation" onclick="activeTab(this);" class="selectedtab" href="${req.contextPath}/customerRelationship/editContactArchivesContactInformation.html?contactArchives.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('contactArchives.contactInformation')}</a>
	</li>
	<li>
		<a id="additionalInfo" onclick="activeTab(this);"  href="${req.contextPath}/customerRelationship/editContactArchivesAdditional.html?contactArchives.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('contactArchives.additional')}</a>
	</li>
	<li>
		<a id="educationInfo" onclick="activeTab(this);"  href='${req.contextPath}/personnelFile/listEducation.html?contactArchives.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('contactArchives.educationBackground')}</a>
	</li>
	<li>
		<a id="workAndResume" onclick="activeTab(this);" href="${req.contextPath}/customerRelationship/listContactsJobResume.html?cr.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('contactArchives.wordExperience')}</a>
	</li>
	<li>
		<a id="societyRelation" onclick="activeTab(this);" href='${req.contextPath}/personnelFile/listSocialRelation.html?cr.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('contactArchives.socialRelation')}</a>
	</li>
	<li>
		<a id="backvisit" onclick="activeTab(this);" href='${req.contextPath}/backvisit/listBackVisitByContact.html?contactArchive.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('contactArchives.backvisit')}</a>
	</li>
	<li>
		<a id="changeToHistory" onclick="activeTab(this);" href='${req.contextPath}/customerRelationship/listContactToHistory.html?cr.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('contactArchives.changeToHistory')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5"  marginHeight="0" marginWidth="0"  src="${req.contextPath}/customerRelationship/editContactArchivesContactInformation.html?contactArchives.id=#{contactArchives.id}&readOnly=${req.getParameter('readOnly')?if_exists}" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
