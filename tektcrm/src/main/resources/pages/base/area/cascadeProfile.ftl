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

<#-- $Id: cascadeProfile.ftl 2009-11-30 16:40:35Z wliu $ -->

<#include "../../includes/macros.ftl" />
<#if area.type == "country">
	<#assign title = "${action.getText('country.manager')}" />
<#elseif area.type == "province">
	<#assign title = "${action.getText('province.manager')}" />
<#elseif area.type == "city">
	<#assign title = "${action.getText('city.manager')}" />
</#if>
<@fsPage title="${title}">
	<@ww.form name="'listForm'" action="'saveArea'" method="'post'">
	<@ww.token name="saveAreaToken"/>
	
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	
	<#if parentArea?exists>
		<@ww.hidden name="'parentId'" value="'${parentArea.id}'"/>
	</#if>
	<#if area.id?exists>
		<@ww.hidden name="'area.id'" value="'${area.id}'"/>
	</#if>
	<#if area.type?exists>
		<@ww.hidden name="'flag'" value="'${area.type}'"/>
		<#if area.type == "country">
			<@inputTable>
				<tr>
					<#if area.new>
					<@ww.textfield label="'${action.getText('area.country.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true"/>
					<#else>
					<@ww.textfield label="'${action.getText('area.country.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" disabled="true"/>
					</#if>
					<@ww.textfield label="'${action.getText('area.country.name')}'" name="'area.name'" value="'${area.name?if_exists}'" cssClass="'underline'" required="true"/>
				</tr>
			</@inputTable>
			<#if !(action.isReadOnly())>
			<#if area.id?exists>
				<@buttonBar>
					<@redirectButton value="${action.getText('新建国家')}" url="${req.contextPath}/areaManager/editArea.html?flag=country"/>
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('area.country.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
		            </@vsubmit>
		            <@redirectButton value="${action.getText('area.newProvince')}" url="${req.contextPath}/areaManager/editArea.html?parentId=#{area.id}&flag=province"/>
	   	 		</@buttonBar>
			<#else>
				<@buttonBar>
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
				</@buttonBar>
			</#if>
			</#if>
		</#if>
		<#if area.type == "province">
			<@inputTable>
				<tr>
					<#if area.new>
					<@ww.textfield label="'${action.getText('area.province.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true"/>
					<#else>
					<@ww.textfield label="'${action.getText('area.province.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" disabled="true"/>
					</#if>
					<@ww.textfield label="'${action.getText('area.province.name')}'" name="'area.name'" value="'${area.name?if_exists}'" cssClass="'underline'" required="true"/>
				</tr>
			</@inputTable>
			<#if !(action.isReadOnly())>
   	 		<#if area.id?exists>
				<@buttonBar>
					<@redirectButton value="${action.getText('新建省')}" url="${req.contextPath}/areaManager/editArea.html?parentId=#{area.parentArea.id}&flag=province"/>
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('area.province.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
		            </@vsubmit>
		            <@redirectButton value="${action.getText('area.newCity')}" url="${req.contextPath}/areaManager/editArea.html?parentId=#{area.id}&flag=city"/>
	   	 		</@buttonBar>
			<#else>
				<@buttonBar>
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
				</@buttonBar>
			</#if>
			</#if>
		</#if>
		<#if area.type == "city">
			<@inputTable>
				<tr>
					<#if area.new>
					<@ww.textfield label="'${action.getText('area.city.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true"/>
					<#else>
					<@ww.textfield label="'${action.getText('area.city.code')}'" name="'area.code'" value="'${area.code?if_exists}'" cssClass="'underline'" required="true" readonly="true" disabled="true"/>
					</#if>
					<@ww.textfield label="'${action.getText('area.city.name')}'" name="'area.name'" value="'${area.name?if_exists}'" cssClass="'underline'" required="true"/>
				</tr>
			</@inputTable>
			<#if !(action.isReadOnly())>
			<#if area.id?exists>
				<@buttonBar>
					<@redirectButton value="${action.getText('新建城市')}" url="${req.contextPath}/areaManager/editArea.html?parentId=#{area.parentArea.id}&flag=city"/>
					<#--<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/areaManager/editArea.html?flag=${area.type}"/>-->
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	   	 			<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('area.city.info')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
		            </@vsubmit>
	   	 		</@buttonBar>
   	 		<#else>
				<@buttonBar>
					<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
				</@buttonBar>
			</#if>
			</#if>
		</#if>
	</#if>
	
</@ww.form>
<script>

	function confirmDelete(msg){
		var param = confirm(msg);
		if(param){
			parent.frames["dTreeFrame"].location.reload();
		}
	}
	function storeValidation(){
        if(document.getElementById("area.code").value==''){
        	<#if area.type == "country">
	        	alert('${action.getText('area.country.code.not.null')}');
	        <#elseif area.type == "province">
	        	alert('${action.getText('area.province.code.not.null')}');
	        <#elseif area.type == "city">
	        	alert('${action.getText('area.city.code.not.null')}');
	        </#if>
	        getObjByName('area.code').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "area.code", null, 20)){
				<#if area.type == "country">
	        		alert("${action.getText('area.country.code.length')}");
	        	<#elseif area.type == "province">
	        		alert("${action.getText('area.province.code.length')}");
	        	<#elseif area.type == "city">
	        		alert("${action.getText('area.city.code.length')}");
	        	</#if>
	        	getObjByName('area.code').focus();
				return  false;
			   }
		}
		if(document.getElementById("area.name").value==''){
	       	<#if area.type == "country">
	        	alert('${action.getText('area.country.name.not.null')}');
	        <#elseif area.type == "province">
	        	alert('${action.getText('area.province.name.not.null')}');
	        <#elseif area.type == "city">
	        	alert('${action.getText('area.city.name.not.null')}');
	        </#if>
	        getObjByName('area.name').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "area.name", null, 20)){
				<#if area.type == "country">
	        		alert("${action.getText('area.country.name.length')}");
	        	<#elseif area.type == "province">
	        		alert("${action.getText('area.province.name.length')}");
	        	<#elseif area.type == "city">
	        		alert("${action.getText('area.city.name.length')}");
	        	</#if>
	        	getObjByName('area.name').focus();
				return  false;
			}   
		} 
		parent.frames["dTreeFrame"].location.reload();
		return true;
	}
</script>
</@fsPage>