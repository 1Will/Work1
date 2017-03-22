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

<#-- $Id: institutionProfile.ftl 2009-11-02 14:20:35Z wliu $ -->

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('institution.profile')}">
<@ww.form namespace="'/baseInfoManager'" name="'institutionInfo'" action="'saveInstitution'" method="'post'">
 <@ww.token name="saveInstitutionToken"/>
    <@inputTable>
    	<#if institution.id?exists>
            <@ww.hidden name="'institution.id'" value="#{institution.id}"/>
        </#if>
        <@ww.hidden name="'institution.pId'" value="${req.getParameter('institution.pId')?if_exists}"/>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	<tr>
	 		<#if institution.new>
            	<@ww.textfield label="'${action.getText('institution.code')}'" name="'institution.code'" value="'${institution.code?if_exists}'" cssClass="'underline'" required="true"/>
            <#else>
            	<@ww.textfield label="'${action.getText('institution.code')}'" name="'institution.code'" value="'${institution.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" required="true"/>
            </#if>
            <@ww.textfield label="'${action.getText('institution.name')}'" name="'institution.name'" value="'${institution.name?if_exists}'" cssClass="'underline'" required="true"/>
            <@ww.select label="'${action.getText('institution.parentInst')}'" 
				name="'parentInsts'" 
				value="'${req.getParameter('parentInsts')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allParentInsts"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('institution.lader')}'" name="'institution.lader'" value="'${institution.lader?if_exists}'" cssClass="'underline'" required="true"/>
        	<@ww.textfield label="'${action.getText('institution.phone')}'" name="'institution.tel'" value="'${institution.tel?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('institution.address')}'" name="'institution.address'" value="'${institution.address?if_exists}'" cssClass="'underline'"/>
        </tr>   
        <tr>
			<@ww.textarea label="'${action.getText('institution.comment')}'" name="'institution.comment'" value="'${institution.comment?if_exists}'" rows='3' cols='30'/>
        </tr>
    </@inputTable>
    <@buttonBar>
    		<#if !(action.isReadOnly())>
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
            </#if>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/baseInfoManager/listInstitution.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	
	window.onload = function () {
		<#if institution.id?exists>
			<#if institution.parentInst?exists>
				document.forms[0].elements["parentInsts"].value = #{institution.parentInst.id};
			</#if>
			document.all.frame.src='${req.contextPath}/base/department/listDept.html?institution.id=#{institution.id}';
		</#if>
 		
 	}
	if('' != getObjByName('institution.pId').value){
		getObjByName('parentInsts').value = getObjByName('institution.pId').value;
	}
 	function storeValidation(){
        if(document.getElementById("institution.code").value==''){
	        alert('${action.getText('institution.code.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.code", null, 20)){
				alert("${action.getText('institution.code.length')}");
				return false;
			   }
		}
		if(document.getElementById("institution.name").value==''){
	        alert('${action.getText('institution.name.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.name", null, 20)){
				alert("${action.getText('institution.name.length')}");
				return false;
			}   
		} 

		if(document.getElementById("institution.lader").value==''){
	        alert('${action.getText('institution.lader.not.null')}');
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.lader", null, 20)){
				alert("${action.getText('institution.lader.length')}");
				return false;
			}   
		} 

		if(document.getElementById("institution.tel").value != ''){
			   var str = document.getElementById("institution.tel").value
			   var reg=/^([0-9]|[\-])+$/g ;
		       if(str.length<7 || str.length>18){
		       		alert('${action.getText('institution.phone.format')}');
		        	return false;
		       }
		     
		}
		if("" != document.getElementById("institution.address").value){
			if(!isValidLength(document.forms[0], "institution.address", null, 50)){
				alert("${action.getText('institution.address.length')}");
				return false;
			}
		}
		
		if("" != document.getElementById("institution.comment").value){
			if(!isValidLength(document.forms[0], "institution.comment", null, 250)){
				alert("${action.getText('institution.comment.length')}");
				return false;
			}
		}		
 	}
</script>
<#if institution.id?exists>
<ul id="beautytab">
	<li>
		<a id="department" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/base/department/listDept.html?institution.id=#{institution.id}' target="frame" >相关部门列表</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="100%"/>
</#if>
</@htmlPage>