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

<#-- $Id: stockStructureProfile.ftl 2009-12-08 14:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('股本结构')}">
<@ww.form namespace="'/customerRelationship'" name="'stockStructure'" action="'saveStockStructure'" method="'post'">
	<@ww.token name="saveStockStructureToken"/>
	<#if stockStructure.id?exists>
    		<@ww.hidden name="'stockStructure.id'" value="#{stockStructure.id}"/>
    		</#if>
	<@ww.hidden name="'customerInfo.id'" value="'#{customerInfoId?if_exists}'" />
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	
	<@inputTable>
 	<tr>
 	    <@ww.textfield label="'${action.getText('contactArchives.stockholderName')}'" name="'stockStructure.stockholderName'"  cssClass="'underline'" required="false"/>
   		<@ww.textfield label="'${action.getText('contactArchives.mshare')}'" name="'stockStructure.mshare'" id="num" cssClass="'underline'" required="false"/>
	</tr>
	<tr>
	<@ww.select 
	    		label="'${action.getText('contactArchives.ownership')}'"
				required="true"
				name="'stockStructure.ownership'" 
				value="${req.getParameter('stockStructure.ownership')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allOwnership"
			    emptyOption="false" 
		    	disabled="false"/>
		<@ww.select 
	    		label="'${action.getText('contactArchives.contributive')}'"
				required="true"
				name="'contributive.id'" 
				value="${req.getParameter('contributive.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allContributive"
			    emptyOption="false" 
		    	disabled="false"/>
	</tr>
	</@inputTable>
   <#if !(action.isReadOnly())>
		 <@buttonBar>
    <#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		
		<#-- 继续新建按钮   -->
				<#if stockStructure.id?exists>
						<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editStockStructure.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
				<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editStockStructure.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
						<script language="JavaScript" type="text/JavaScript"> 
						getObjByName("newNext").disabled="true";
						</script>
				</#if>
	</#if>
		<!-- 关闭按钮 -->
		<@vbutton name="close" value="${action.getText('close')}" class="button" onclick="closeThis();"/>
  	
    </@buttonBar>
    	
  	</#if>
</@ww.form>
<script type="text/javascript">
		window.onload=function(){
			<#if stockStructure.ownership?exists>
					getObjByName('stockStructure.ownership').value='${stockStructure.ownership.id?if_exists}';
			</#if>
			<#if stockStructure.contributive?exists>
					getObjByName('contributive.id').value='${stockStructure.contributive.id?if_exists}';
			</#if>
			
			}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('stockStructure.stockholderName').value==''){
	        alert('${action.getText('contactArchives.stockholderName.not.null')}');
	        getObjByName('stockStructure.stockholderName').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "stockStructure.stockholderName", null, 50)){
				alert("${action.getText('contactArchives.stockholderName.length')}");
				getObjByName('stockStructure.stockholderName').focus();
				return false;
			}
		}
	
	var mshare = getObjByName('stockStructure.mshare').value;
	if(mshare!=''){
		if(!isDoubleNumber('stockStructure.mshare')){
		alert('${action.getText('contactArchives.mshare')}'+'必须是正数');
				getObjByName('stockStructure.mshare').value="";
				getObjByName('stockStructure.mshare').focus();
				return false;
		}else{
		if(mshare>100){
		alert('${action.getText('contactArchives.mshare')}'+'不能超过100');
					getObjByName('stockStructure.mshare').value="";
					getObjByName('stockStructure.mshare').focus();
					return false;
		}
		}
	}else{
	alert('${action.getText('contactArchives.mshare')}'+'不能为空');
					getObjByName('stockStructure.mshare').value="";
					getObjByName('stockStructure.mshare').focus();
					return false;
	}
	
			
	     
	      //点击保存时，页面显示下拉框必填
	   	
		if(getObjByName('stockStructure.ownership').value==''){
	        alert('${action.getText('stockStructure.ownership.notNull')}');
	        getObjByName('stockStructure.ownership').focus();
	        return false;
	     }
	     if(getObjByName('stockStructure.contributive').value==''){
	        alert('${action.getText('stockStructure.contributive.notNull')}');
	        getObjByName('stockStructure.contributive').focus();
	        return false;
	     }
		
		
	}
</script>
</@htmlPage>
