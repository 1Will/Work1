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

<@htmlPage title="${action.getText('new.newStandard')}">
<@ww.form namespace="'/customerRelationship'" name="'newStandard'" action="'saveeNewStandardByCustomer'" method="'post'">
	<@ww.token name="savesaveeNewStandardByCustomerToken"/>
	<#assign returnUrl="${req.contextPath}/customerRelationship/editnewStandByCustomer.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	
    <@inputTable>
    	<#if customerInfoId ?exists>
	    		<@ww.hidden name="'customerInfo.id'" value="#{customerInfoId}"/>
	    		<#assign returnUrl=returnUrl + '&customerInfo.id=#{customerInfoId}'/>
	    	<#else>
	    		<@ww.hidden name="'customerInfo.id'" value="''"/>
    	</#if>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
    	<#if newStandard.id?exists>
    		<@ww.hidden name="'newStandard.id'" value="#{newStandard.id}"/>
	 	</#if>
	<tr>
	     <!--标准名称-->
	     <@ww.textfield label="'${action.getText('newStandard.name')}'" name="'newStandard.name'" value="'${newStandard.name?if_exists}'" cssClass="'underline'" required="true" />
	    <!--标准类型-->
	     <@ww.select label="'${action.getText('newStandard.genre')}'" 
				name="'newStandard.genre'" 
				id="'newStandard.genre'"
				value="'${req.getParameter('newStandard.genre')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allgenre"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			
	</tr>
     <tr>
     <!--参与类型-->
	     <@ww.select label="'${action.getText('newStandard.partakeGenre')}'" 
				name="'newStandard.partakeGenre'" 
				id="'newStandard.partakeGenre'"
				value="'${req.getParameter('newStandard.partakeGenre')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allpartakeGenre"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	 <!--排名-->
		 <@ww.textfield label="'${action.getText('newStandard.ranking')}'" name="'newStandard.ranking'" cssClass="'underline'" required="true" />
     </tr>
    <tr>
    <!-- 批准年度 -->
		 <@pp.datePicker 
				label="'${action.getText('newStandard.approveTime')}'" 
				name="'newStandard.approveTime'" 
				id="'newStandard.approveTime'" 
	   			value="'${(newStandard.approveTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
		maxlength="10"/>
		
		<!--批准文号-->
	    <@ww.textfield label="'${action.getText('newStandard.approveNumber')}'" name="'newStandard.approveNumber'" id="'newStandard.approveNumber'"   value="'${newStandard.approveNumber?if_exists}'" cssClass="'underline'" required="true" />
    </tr>
    <tr>
    <!--批准单位-->
	    <@ww.textfield label="'${action.getText('newStandard.approveUnit')}'" name="'newStandard.approveUnit'" id="'newStandard.approveUnit'"  value="'${newStandard.approveUnit?if_exists}'" cssClass="'underline'" required="true" />
   
    </tr>
    
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#if notNewFlag?exists&&notNewFlag=='notNewFlag'>
			<#else>
				<#-- 继续新建按钮   -->
				<#if newStandard.id?exists>
						<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editnewStandByCustomer.html?customerInfo.id=#{customerInfoId} "/>
				<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editnewStandByCustomer.html?readOnly=${req.getParameter('readOnly')?if_exists}$customer.id=#{customerInfoId}"/>
						<script language="JavaScript" type="text/JavaScript"> 
						getObjByName("newNext").disabled="true";
						</script>
				</#if>
			</#if>
		</#if>
		<!-- 关闭按钮 -->
		<@vbutton name="close" value="${action.getText('close')}" class="button" onclick="closeThis();"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
window.onload=function(){
	<#if newStandard.partakeGenre?exists>
			getObjByName('newStandard.partakeGenre').value='${newStandard.partakeGenre.id?if_exists}';
	</#if>
	<#if newStandard.genre?exists>
			getObjByName('newStandard.genre').value='${newStandard.genre.id?if_exists}';
	</#if>
	
	}
	function savee(){
		return storeValidation();
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('newStandard.name').value==''){
	        alert('${action.getText('newStandard.name.notNull')}');
	        getObjByName('newStandard.name').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.genre').value==''){
	        alert('${action.getText('newStandard.genre.notNull')}');
	        getObjByName('newStandard.genre').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.partakeGenre').value==''){
	        alert('${action.getText('newStandard.partakeGenre.notNull')}');
	        getObjByName('newStandard.partakeGenre').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.ranking').value==''){
	        alert('${action.getText('newStandard.ranking.notNull')}');
	        getObjByName('newStandard.ranking').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.ranking').value!=''){
	        if (isNaN(getObjByName('newStandard.ranking').value)) { 
		　　　　alert('${action.getText('insertnumber')}'); 
		　　　　getObjByName('newStandard.ranking').focus();
		　　　　return false;
		　} 
	     }
	     if(getObjByName('newStandard.approveTime').value==''){
	        alert('${action.getText('newStandard.approveTime.notNull')}');
	        getObjByName('newStandard.approveTime').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.approveNumber').value==''){
	        alert('${action.getText('newStandard.approveNumber.notNull')}');
	        getObjByName('newStandard.approveNumber').focus();
	        return false;
	     }
	     if(getObjByName('newStandard.approveUnit').value==''){
	        alert('${action.getText('newStandard.approveUnit.notNull')}');
	        getObjByName('newStandard.approveUnit').focus();
	        return false;
	     }
		return true;
	}
	
</script>
</@htmlPage>
