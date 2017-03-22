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

<#include "../../includes/hco2011.ftl" />

<@fsPage title="${action.getText('institution.profile')}">
<@ww.form namespace="'/orgstructure'" name="'institutionInfo'" action="'saveInstitution'" method="'post'">
 <@ww.token name="saveInstitutionToken"/>
    <@inputTable>
    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if institution.id?exists>
            <@ww.hidden name="'institution.id'" value="#{institution.id}"/>
        </#if>
        <#--如果建立的是根级别的目录，则不显示添加子单位-->
        <#if root?exists>
        <#else>
	        <#if parentId?exists>
	        	<@ww.hidden name="'parentid'" value="${parentId?if_exists}"/>
	        </#if>
        </#if>
        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	<tr>
	 		<#if institution.new>
            	<@ww.textfield label="'${action.getText('institution.code')}'" name="'institution.code'" value="'${institution.code?if_exists}'" cssClass="'underline'" required="true"/>
            <#else>
            	<@ww.textfield label="'${action.getText('institution.code')}'" name="'institution.code'" value="'${institution.code?if_exists}'" cssClass="'underline'" readonly="true" disabled="true" required="true"/>
            </#if>
            <@ww.textfield label="'${action.getText('institution.name')}'" name="'institution.name'" value="'${institution.name?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('institution.lader')}'" name="'institution.lader'" value="'${institution.lader?if_exists}'" cssClass="'underline'" required="true"/>
        	<@select 
	        		anothername="selectCheckIndustry"
	        		label="${action.getText('institution.industry')}" 
					name="industry.id" 
					value="${req.getParameter('industry.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allIndustry"
					required="false"
					emptyOption="true" 
					disabled="false">
				</@select>
        </tr>
        <tr>
        	<@select 
					anothername="selectCheckNature"
	        		label="${action.getText('institution.nature')}" 
					name="nature.id" 
					value="${req.getParameter('nature.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allNature"
					required="false"
					emptyOption="true" 
					disabled="false">
				</@select>
				<@textfield anothername="checkLegalPerson" label="${action.getText('institution.legalPerson')}" name="institution.legalPerson" value="${institution.legalPerson?if_exists}" cssClass="underline" maxlength="20"/>
        </tr>
        <tr>
        	<@textfield anothername="checkEmail" label="${action.getText('institution.email')}" name="institution.email" value="${institution.email?if_exists}" type="E" cssClass="underline" maxlength="50"/>
        	<@textfield anothername="checkFax" label="${action.getText('institution.fax')}" name="institution.fax" value="${institution.fax?if_exists}" type="P" cssClass="underline" maxlength="50"/>
        </tr>
        <tr>
        	<@textfield anothername="checkPostCode" label="${action.getText('institution.postCode')}" name="institution.postCode" value="${institution.postCode?if_exists}" type="P" cssClass="underline" maxlength="20"/>
        	<@ww.textfield label="'${action.getText('institution.phone')}'" name="'institution.tel'" value="'${institution.tel?if_exists}'" cssClass="'underline'"/>
        </tr>
        <tr>
        	<@textfield anothername="checkWebsite" label="${action.getText('institution.website')}" name="institution.website" value="${institution.website?if_exists}" cssClass="underline" maxlength="50"/>
			<@datePickerRanger
				anothername="dateCheckSetupTime"
        		label="${action.getText('institution.setupTime')}"
	           	name="institution.setupTime"
	     		value="${(institution.setupTime?string('yyyy-MM-dd'))?if_exists}" 
				cssClass="underline" 
				maxlength="10" 
				flag="true">
			</@datePickerRanger>
        </tr>
        <tr>
        <tr>
			<td align="right" valign="top">
	       		<label class="label">
        			${action.getText('institution.address')}:
        		</label>
	     	</td>
			<td colspan="10">
				<input type="text" name="institution.address" class="underline"  value="${institution.address?if_exists}" maxlength="140" size="120" />
			</td>
        </tr>
        <tr>
	        <td align="right" valign="top">
	    		<label class="label">
	    			${action.getText('institution.comment')}:
	    		</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="institution.comment" rows="4" cols="95" name="institution.comment">${institution.comment?if_exists}</textarea>
	        </td>
        </tr>
    </@inputTable>
    <@buttonBar>
    		<#if !(action.isReadOnly())>
    		<#--
            <@redirectButton value="${action.getText('new')}" url="editInstitution.html?new_inst=new_inst"/>
            -->
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
            <#if institution.id?exists>
            	<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('institution.info')}?" />
	             <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDelete(\"${confirmMessage}\");'"/>
	                <#--<@ww.param name="'disabled'" value="${isParent?if_exists}"/>-->
	             </@vsubmit>
            	<@redirectButton value="${action.getText('添加子单位')}" url="editInstitution.html?parentid=#{institution.id}"/>
            	<@redirectButton value="${action.getText('添加下属部门')}" url="editDepartment.html?institution.id=#{institution.id}"/>
            <#else>
            	<#--如果建立的是根级别的目录，则不显示添加子单位-->
            	<#if root?exists>
        		<#elseif parentId?exists>
        		<#else>
        		<@vsubmit name="'delete'" value="'${action.getText('delete')}'"/>
            	<@redirectButton value="${action.getText('添加子单位')}" url="editInstitution.html?parentid=${parentId?if_exists}"/>
            	<@redirectButton value="${action.getText('添加下属部门')}" url="editDepartment.html?institution.id=${parentId?if_exists}"/>
            	</#if>
            </#if>
            </#if>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload = function () {
	
	
		//所属行业
	    <#if institution.industry?exists>
			getObjByName('industry.id').value='${institution.industry.id?if_exists}';
		</#if>
		//所属性质
	    <#if institution.nature?exists>
			getObjByName('nature.id').value='${institution.nature.id?if_exists}';
		</#if>
	}

	function confirmDelete(msg){
			var param = confirm(msg);
			if(param){
				parent.frames["dTreeFrame"].location.reload();
			}
			return param;
		}

 	function storeValidation(){
        if(document.getElementById("institution.code").value==''){
	        alert('${action.getText('institution.code.not.null')}');
	        getObjByName('institution.code').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.code", null, 20)){
				alert("${action.getText('institution.code.length')}");
				getObjByName('institution.code').value="";
				getObjByName('institution.code').focus();
				return  false;
			   }
		}
		if(document.getElementById("institution.name").value==''){
	        alert('${action.getText('institution.name.not.null')}');
	        getObjByName('institution.name').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.name", null, 20)){
				alert("${action.getText('institution.name.length')}");
				getObjByName('institution.name').value="";
				getObjByName('institution.name').focus();
				return  false;
			}   
		} 

		if(document.getElementById("institution.lader").value==''){
	        alert('${action.getText('institution.lader.not.null')}');
	        getObjByName('institution.lader').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "institution.lader", null, 20)){
				alert("${action.getText('institution.lader.length')}");
				getObjByName('institution.lader').value="";
				getObjByName('institution.lader').focus();
				return  false;
			}   
		} 
		<#--
		//验证邮编
		if(!textfieldCheck_checkPostCode()){
			return false;
		}
		//验证电子邮件
		if(!textfieldCheck_checkEmail()){
			return false;
		}
		//验证传真
		if(!textfieldCheck_checkFax()){
			return false;
		}-->
		if(document.getElementById("institution.tel").value != ''){
			   var str = document.getElementById("institution.tel").value
		       if(str.length>20){
		       		alert('${action.getText('institution.phone.length')}');
		       		getObjByName('institution.tel').value="";
		       		getObjByName('institution.tel').focus();
		        	return false;
		       }
		     
		}
		<#--
		//验证网址
		if(getObjByName('institution.website').value !=''){
		alert(9);
			var urlreg=/^[A-Za-z0-9://]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
			if (!urlreg.test(getObjByName('agency.website').value)){
				alert('${action.getText('institution.website.type')}');
				return false;
	     	}  
		}
		//验证创立时间
		if(!dateCheck_dateCheckSetupTime()){
			return false;
		}-->
		if("" != document.getElementById("institution.address").value){
			if(!isValidLength(document.forms[0], "institution.address", null, 50)){
				alert("${action.getText('institution.address.length')}");
				getObjByName('institution.address').value="";
				getObjByName('institution.address').focus();
				return false;
			}
		}
		
		if("" != document.getElementById("institution.comment").value){
			if(!isValidLength(document.forms[0], "institution.comment", null, 250)){
				alert("${action.getText('institution.comment.length')}");
				getObjByName('institution.comment').value="";
				getObjByName('institution.comment').focus();
				return false;
			}
		}	
		parent.frames["dTreeFrame"].location.reload();
		return true;	
 	}
</script>
</@fsPage>