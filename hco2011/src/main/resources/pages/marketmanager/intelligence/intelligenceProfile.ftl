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

<#-- $Id: intelligenceProfile.ftl 2011-2-24  hmguan $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('title')}">
<@ww.form namespace="'/intelligence'" name="'intelligenceProfile'" action="'saveIntelligence'" method="'post'">
	<@ww.token name="saveIntelligenceToken"/>
    <@inputTable>
		<@ww.hidden name="'persons.id'" value="'${req.getParameter('persons.id')?if_exists}'"/>
		<@ww.hidden name="'customerInfo.id'" value="'${req.getParameter('customerInfo.id')?if_exists}'"/>
		<@ww.hidden name="'contactArchives.id'" value="'${req.getParameter('contactArchives.id')?if_exists}'"/>
    	<#if intelligence.id?exists>
    		<@ww.hidden name="'intelligence.id'" value="#{intelligence.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@textfield label="${action.getText('intelligence.code')}" name="intelligence.code" anothername="code" value="${intelligence.code?if_exists}" required="false" cssClass="underline" maxlength="20" disabled="true"/>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('intelligence.customerInfo')}:</label>
	     	</td>
	     	<td>
	     		<#if intelligence.customerInfo?exists>
		   		<input type="text" name="intelligence.customerInfo" class="underline"  value="${intelligence.customerInfo.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="intelligence.customerInfo" class="underline"  value="${req.getParameter('intelligence.customerInfo')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="customer_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>   
			<td align="right" valign="top">
				<span class="required">*</span>
	       		<label class="label">${action.getText('intelligence.persons')}:</label>
	     	</td>
	     	<td>
	     		<#if intelligence.persons?exists>
		   		<input type="text" name="intelligence.persons" class="underline"  value="${intelligence.persons.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="intelligence.persons" class="underline"  value="${req.getParameter('intelligence.persons')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="persons_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>   
		</tr>	
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('intelligence.contactArchives')}:</label>
	     	</td>
	     	<td>
	     		<#if intelligence.contactArchives?exists>
		   		<input type="text" name="intelligence.contactArchives" class="underline"  value="${intelligence.contactArchives.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="intelligence.contactArchives" class="underline"  value="${req.getParameter('intelligence.contactArchives')?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="contactArchive_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>   
            <@textfield label="${action.getText('intelligence.telephone')}" name="intelligence.telephone" anothername="telephone" value="${intelligence.telephone?if_exists}" required="false" cssClass="underline" maxlength="20"/>  
            <@textfield label="${action.getText('intelligence.email')}" name="intelligence.email" anothername="email" value="${intelligence.email?if_exists}" required="false" cssClass="underline" maxlength="20"/>  
		</tr>
		<tr>
			<@pp.datePicker 
				label="'${action.getText('intelligence.analysisTime')}'" 
				name="'intelligence.analysisTime'" 
	   			value="'${(intelligence.analysisTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>	
			<script language="javascript">
					var date = new Date();
					if(getObjByName("intelligence.analysisTime").value==''){
						getObjByName("intelligence.analysisTime").value = date.format("yyyy-MM-dd");
					}
			</script>
		    <@select label="${action.getText('intelligence.important')}" 
		   	   anothername="selectCheckImportant"
		       name="important.id" 
		       value="${req.getParameter('important.id')?if_exists}"
		       listKey="id" 
		       listValue="name"
		       list="allImportant" 
		       emptyOption="true" 
		       disabled="false" 
		       required="true">
		    </@select>
		</tr>
		<tr>
		    <@textarea name="intelligence.theme" label="${action.getText('intelligence.theme')}" anothername="theme" maxLength="500" required="true" value="${intelligence.theme?if_exists}"/>
		</tr>
		<tr>
		    <@textarea name="intelligence.content" label="${action.getText('intelligence.content')}" anothername="content" maxLength="500" required="true" value="${intelligence.content?if_exists}"/>
		</tr>
		<tr>		
			<@textarea name="intelligence.competProductTrend" label="${action.getText('intelligence.competProductTrend')}" anothername="competProductTrend" maxLength="500" required="false" value="${intelligence.competProductTrend?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="intelligence.competPriceTrend" label="${action.getText('intelligence.competPriceTrend')}" anothername="competPriceTrend" maxLength="500" required="false" value="${intelligence.competPriceTrend?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="intelligence.competPromoteTrend" label="${action.getText('intelligence.competPromoteTrend')}" anothername="competPromoteTrend" maxLength="500" required="false" value="${intelligence.competPromoteTrend?if_exists}"/>
   		</tr>		
		<tr>		
			<@textarea name="intelligence.competAdTrend" label="${action.getText('intelligence.competAdTrend')}" anothername="competAdTrend" maxLength="500" required="false" value="${intelligence.competAdTrend?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="intelligence.competDistributeChannel" label="${action.getText('intelligence.competDistributeChannel')}" anothername="competDistributeChannel" maxLength="500" required="false" value="${intelligence.competDistributeChannel?if_exists}"/>
   		</tr>
		<tr>		
			<@textarea name="intelligence.customerFeedback" label="${action.getText('intelligence.customerFeedback')}" anothername="customerFeedback" maxLength="500" required="false" value="${intelligence.customerFeedback?if_exists}"/>
   		</tr>	
		<tr>		
			<@textarea name="intelligence.remark" label="${action.getText('intelligence.remark')}" anothername="remark" maxLength="500" required="false" value="${intelligence.remark?if_exists}"/>
   		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/intelligence/listIntelligence.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if intelligence.important?exists>
			getObjByName('important.id').value='${intelligence.important.id?if_exists}';
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
			getObjByName('contactArchives.id').value = "";	
		 	getObjByName('intelligence.contactArchives').value = "";
		 	getObjByName('customerInfo.id').value = result[0];	
		 	getObjByName('intelligence.customerInfo').value = result[1];
		 	getObjByName('persons.id').value = result[5];	
		 	getObjByName('intelligence.persons').value = result[6];
		}
	}

	function contactArchive_OpenDialog(){
		if(getObjByName('customerInfo.id').value !=''){
			var  url = "${req.contextPath}/customerRelationship/listContactArchives.html?backVisitFlag=backVisit&customer.id="+getObjByName('customerInfo.id').value;
			popupModalDialog(url, 800, 600, creatorSelectorHandlerContactArchives);
		}else{
			alert('请先选择客户');
		}
	}

	function creatorSelectorHandlerContactArchives(result) {
		if (null != result) {
		 	document.forms[0].elements["contactArchives.id"].value = result[0];	
		 	document.forms[0].elements["intelligence.contactArchives"].value = result[1];
		 	getObjByName('intelligence.telephone').value = result[2];	
		 	getObjByName('intelligence.email').value = result[3];
		}
	}

	//弹出负责人查询模态窗体
	function persons_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSelectorHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandler(result) {
		if (null != result) {
			document.forms[0].elements["persons.id"].value = result[0];
	   		document.forms[0].elements["intelligence.persons"].value = result[2];
		}
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		//相关客户
	    if(getObjByName('intelligence.customerInfo').value==""){
	        alert('${action.getText('intelligence.customerInfo.not.null')}');
	        return false;
	    }
		//业务员
	    if(getObjByName('intelligence.persons').value==""){
	        alert('${action.getText('intelligence.persons.not.null')}');
	        return false;
	    }
		//日期
		if(getObjByName('intelligence.analysisTime').value ==''){
		 		alert("${action.getText('intelligence.analysisTime.not.null')}");
		 		getObjByName('intelligence.analysisTime').focus();
		      	return false;   
		}else{
		 	if(!isDate('intelligence.analysisTime')){
				alert("${action.getText('intelligence.analysisTime.dateFormate.error')}");
				getObjByName('intelligence.analysisTime').value="";
	      	    getObjByName('intelligence.analysisTime').focus();
				return false;
			} 
		}
		//情报重要性
		if(!selectCheck_selectCheckImportant()){
			getObjByName('important.id').focus();
		    return false;
		}
		//主题
        if(!textareaCheck_theme()){
	        getObjByName('intelligence.theme').focus();
			return false;
		}
		//内容
	    if(!textareaCheck_content()){
		    getObjByName('intelligence.content').focus();
		    return false;
	    }
		return true;
	}
</script>
</@htmlPage>
