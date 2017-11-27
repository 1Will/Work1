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

<@htmlPage title="${action.getText('new.technology')}">
<@ww.form namespace="'/customerRelationship'" name="'technology'" action="'saveeditTechnology'" method="'post'">
	<@ww.token name="saveeditTechnologyToken"/>
	<#assign returnUrl="${req.contextPath}/customerRelationship/editTechnology.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	
    <@inputTable>
    	<#if technology.customerInfo ?exists>
	    		<@ww.hidden name="'customer.id'" value="#{technology.customerInfo.id}"/>
	    		<#assign returnUrl=returnUrl + '&customer.id=#{technology.customerInfo.id}'/>
	    	<#else>
	    		<@ww.hidden name="'customer.id'" value="''"/>
    	</#if>
    	<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag' >
    		<@ww.hidden  name="popWindowFlag"  value="${popWindowFlag}"/>
    		<#assign returnUrl=returnUrl + '&popWindowFlag=${popWindowFlag}'/>
    	</#if>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	
    	<#if technology.id?exists>
    		<@ww.hidden name="'technology.id'" value="#{technology.id}"/>
	 	</#if>
	<tr>
	    <@ww.select label="'${action.getText('technology.type')}'" 
				name="'technology.type'" 
				id="'technology.type'"
				value="'${req.getParameter('technology.type')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allStyleTypes"
				required="true"
				emptyOption="false" 
				onchange="'show_sub()'" >
			</@ww.select>
	     <@ww.textfield label="'${action.getText('technology.name')}'" name="'technology.name'" value="'${technology.name?if_exists}'" cssClass="'underline'" required="true" />
	    <!--知识产权类别-->
	     <@ww.select label="'${action.getText('technology.genre')}'" 
				name="'technology.genre'" 
				id="'technology.genre'"
				value="'${req.getParameter('technology.genre')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allgenre"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	</tr>
     <tr id="pn" name="pn" style="display:none">
     <!-- 申请时间 -->
		 <@pp.datePicker 
				label="'${action.getText('technology.applyTime')}'" 
				name="'technology.applyTime'" 
				id="'technology.applyTime'" 
	   			value="'${(technology.applyTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
		maxlength="10"/>
		
		 <!-- 授权公告日 -->
		 <@pp.datePicker 
				label="'${action.getText('technology.authorizeTime')}'" 
				name="'technology.authorizeTime'" 
				id="'technology.authorizeTime'" 
	   			value="'${(technology.authorizeTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
		maxlength="10"/>
		<!--专利号-->
	    <@ww.textfield label="'${action.getText('technology.applyNumber')}'" name="'technology.applyNumber'" id="'technology.applyNumber'"   value="'${technology.applyNumber?if_exists}'" cssClass="'underline'" required="true" />
    </tr>
    <tr  id="t_auz" name="t_auz" >
    
     <!-- 申请时间 -->
		 <@pp.datePicker 
				label="'${action.getText('technology.applyPassTime')}'" 
				name="'technology.applyPassTime'" 
				id="'technology.applyPassTime'" 
	   			value="'${(technology.applyPassTime?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
		maxlength="10"/>
        <!--申请号-->
	    <@ww.textfield label="'${action.getText('technology.patentNumber')}'" name="'technology.patentNumber'" id="'technology.patentNumber'"  value="'${technology.patentNumber?if_exists}'" cssClass="'underline'" required="true" />
    </tr>
    
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
		
			<#if notNewFlag?exists&&notNewFlag=='notNewFlag'>
			<#else>
				<#-- 继续新建按钮   -->
				<#if technology.id?exists>
						<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editTechnology.html?customer.id=#{technology.customerInfo.id} "/>
				<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editTechnology.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
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
	<#if technology.type?exists>
			getObjByName('technology.type').value='${technology.type.id?if_exists}';
			show_first();
	</#if>
	<#if technology.genre?exists>
			getObjByName('technology.genre').value='${technology.genre.id?if_exists}';
	</#if>
	
	}
	function show_first(){
	    if(getObjByName('technology.type').value=='692'){
	      getObjByName('pn').style.display="none";
	      getObjByName('t_auz').style.display='';
	    }
	    if(getObjByName('technology.type').value=='693'){
	        getObjByName('pn').style.display='';
	        getObjByName('t_auz').style.display="none";
	    }
	}
	function show_sub(){
	    if(getObjByName('technology.type').value=='692'){
	      getObjByName('pn').style.display="none";
	      getObjByName('t_auz').style.display='';
          cleandata();	       
	    }
	   
	    if(getObjByName('technology.type').value=='693'){
	        getObjByName('pn').style.display='';
	        getObjByName('t_auz').style.display="none";
	        cleandata();
	    }
	}
	function cleandata(){
	getObjByName('technology.applyTime').value='';
	getObjByName('technology.applyNumber').value='';
	getObjByName('technology.authorizeTime').value='';
	getObjByName('technology.patentNumber').value='';
	getObjByName('technology.applyPassTime').value='';
	}
	function savee(){
		return storeValidation();
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('technology.name').value==''){
	        alert('${action.getText('technology.name.notNull')}');
	        getObjByName('technology.name').focus();
	        return false;
	     }
	     if(getObjByName('technology.genre').value==''){
	        alert('${action.getText('technology.genre.notNull')}');
	        getObjByName('technology.genre').focus();
	        return false;
	     }
	     if(getObjByName('technology.type').value=='693'){
	         if(getObjByName('technology.applyTime').value==''){
		        alert('${action.getText('technology.applyTime.notNull')}');
		        getObjByName('technology.applyTime').focus();
		        return false;
	         }
		     if(getObjByName('technology.authorizeTime').value==''){
		         alert('${action.getText('technology.authorizeTime.notNull')}');
		         getObjByName('technology.authorizeTime').focus();
		         return false;
		     }
		     if(getObjByName('technology.applyTime').value>getObjByName('technology.authorizeTime').value){
				alert('${action.getText('invalidParms.ciemdinghTime3')}');
				getObjByName('technology.authorizeTime').value="";
	       		getObjByName('technology.authorizeTime').focus();
				return false;
		     }
		     if(getObjByName('technology.applyNumber').value==''){
		         alert('${action.getText('technology.applyNumber.notNull')}');
		         getObjByName('technology.genre').focus();
		         return false;
	         }
	     }
	     if(getObjByName('technology.type').value=='692'){
	      if(getObjByName('technology.applyPassTime').value==''){
		        alert('${action.getText('technology.applyPassTime.notNull')}');
		        getObjByName('technology.applyPassTime').focus();
		        return false;
		        }
		         if(getObjByName('technology.patentNumber').value==''){
		        alert('${action.getText('technology.patentNumber.notNull')}');
		        getObjByName('technology.patentNumber').focus();
		        return false;
		        }
	      
	     }
		return true;
	}
	
</script>
</@htmlPage>
