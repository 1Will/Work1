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
<@ww.form namespace="'/customerRelationship'" name="'contactArchivesInfo'" action="'saveCA'" method="'post'">
	<@ww.token name="saveCAToken"/>
    <@inputTable>
    	<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>
    	<#if contactArchives.id?exists>
    		<@ww.hidden name="'contactArchives.id'" value="#{contactArchives.id}"/>
	 	</#if>
	 	<@ww.hidden name="'customer.id'" value="'#{contactArchives.customerName.id}'"/>
	 	<#if contactArchives.customerType?exists>
	 	<@ww.hidden name="'customerType.id'" value="'#{contactArchives.customerType.id}'"/>
	 	<#else>
	 	<@ww.hidden name="'customerType.id'" value="''"/>
    	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('contactArchives.name')}'" name="'contactArchives.name'" value="'${contactArchives.name?if_exists}'" cssClass="'underline'" required="true" />
			<@ww.textfield label="'${action.getText('contactArchives.abbreviations')}'" name="'contactArchives.abbreviations'" value="'${contactArchives.abbreviations?if_exists}'" cssClass="'underline'" required="false" />
			<@ww.textfield label="'${action.getText('contactArchives.customerName')}'" name="'contactArchives.custName'" value="'${contactArchives.custName?if_exists}'" cssClass="'underline'" required="true" readonly="true" />
        </tr>
        <tr>
        	<#if contactArchives.customerType?exists>
        	<@ww.textfield label="'${action.getText('contactArchives.customerType')}'" name="'contactArchives.custType'" value="'${contactArchives.customerType.name?if_exists}'" cssClass="'underline'" required="true" readonly="true" />    	
        	<#else>
        	<@ww.textfield label="'${action.getText('contactArchives.customerType')}'" name="'contactArchives.custType'" value="'${contactArchives.custType?if_exists}'" cssClass="'underline'" required="true" readonly="true" />    		
        	</#if>
        	<@ww.textfield label="'${action.getText('contactArchives.industry')}'" name="'contactArchives.industry'" value="'${contactArchives.industry?if_exists}'" cssClass="'underline'" required="true" readonly="true" />
        	<@ww.textfield label="'${action.getText('contactArchives.dept')}'" name="'contactArchives.dept'" value="'${contactArchives.dept?if_exists}'" cssClass="'underline'" />
        </tr>
        <tr>	
        	<@ww.textfield label="'${action.getText('contactArchives.duty')}'" name="'contactArchives.duty'" value="'${contactArchives.duty?if_exists}'" cssClass="'underline'"/>
			<td align="right"><label for="" class="label">${action.getText('contactArchives.sex')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="man" name="contactArchivesSex" value="0" />男
	        	<input type="radio" id="woman" name="contactArchivesSex" value="1" />女
			</td>
			<@ww.textfield label="'${action.getText('contactArchives.phone')}'" name="'contactArchives.phone'" value="'${contactArchives.phone?if_exists}'" cssClass="'underline'"/>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('contactArchives.mobilePhone')}'" name="'contactArchives.mobilePhone'" value="'${contactArchives.mobilePhone?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('contactArchives.fax')}'" name="'contactArchives.fax'" value="'${contactArchives.fax?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('contactArchives.email')}'" name="'contactArchives.email'" value="'${contactArchives.email?if_exists}'" cssClass="'underline'" />
        </tr>
        <tr>	
			<@ww.textfield label="'${action.getText('contactArchives.qq')}'" name="'contactArchives.qq'" value="'${contactArchives.qq?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('contactArchives.msn')}'" name="'contactArchives.msn'" value="'${contactArchives.msn?if_exists}'" cssClass="'underline'" />
        	<@ww.textfield label="'${action.getText('contactArchives.homePhone')}'" name="'contactArchives.homePhone'" value="'${contactArchives.homePhone?if_exists}'" cssClass="'underline'"/>
        </tr>
		<tr>
			<@ww.textfield label="'${action.getText('contactArchives.postCode')}'" name="'contactArchives.postCode'" value="'${contactArchives.postCode?if_exists}'" cssClass="'underline'" />
			<@ww.select label="'${action.getText('contactArchives.birthplace')}'"
				name="'birthplace.id'" 
				value="'${req.getParameter('birthplace.id')?if_exists}'" 
				listKey="id"
				listValue="name"
				list="allBirthplace"
		    	emptyOption="true" 
		    	disabled="false">
		    </@ww.select>
			<@ww.select label="'${action.getText('contactArchives.nationality')}'" 
				name="'nationality.id'" 
				value="'${req.getParameter('nationality.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allNationality"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('contactArchives.address')}:</label>
	     	</td>
			<td colspan="10">
				<input type="text" name="contactArchives.address" class="underline"  value="${contactArchives.address?if_exists}" maxlength="140" size="120" />
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('contactArchives.school')}'" name="'contactArchives.school'" value="'${contactArchives.school?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('contactArchives.professional')}'" name="'contactArchives.professional'" value="'${contactArchives.professional?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('contactArchives.favorite')}'" name="'contactArchives.favorite'" value="'${contactArchives.favorite?if_exists}'" cssClass="'underline'" />
		</tr>
		<tr>
		    <@ww.select label="'${action.getText('contactArchives.temperament')}'" 
				name="'temperament.id'" 
				value="'${req.getParameter('temperament.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTemperament"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@pp.datePicker
				label="'${action.getText('contactArchives.birthday')}'" 
				name="'contactArchives.birthday'" 
	   			value="'${(contactArchives.birthday?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'"  
				maxlength="10">
			</@pp.datePicker>
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
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('contactArchives.comment')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="3" cols="120" >${contactArchives.comment?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="javascript:window.close();"/>
    </@buttonBar>
</@ww.form>
<script type="text/javascript">
	window.onload=function(){
		<#if contactArchives.sex==false>
			getObjByName('man').checked=true;
		<#else>
		    getObjByName('woman').checked=true;
		</#if>
		<#if contactArchives.birthplace?exists>
			getObjByName('birthplace.id').value='${contactArchives.birthplace.id?if_exists}';
		</#if>
		<#if contactArchives.nationality?exists>
			getObjByName('nationality.id').value='${contactArchives.nationality.id?if_exists}';
		</#if>
		<#if contactArchives.temperament?exists>
			getObjByName('temperament.id').value='${contactArchives.temperament.id?if_exists}';
		</#if>
		<#if contactArchives.type?exists>
			getObjByName('type.id').value='${contactArchives.type.id?if_exists}';
		</#if>
	}
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('contactArchives.name').value==''){
	        alert('${action.getText('contactArchives.name.not.null')}');
	        getObjByName('contactArchives.name').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "contactArchives.name", null, 20)){
				alert("${action.getText('contactArchives.name.length')}");
				getObjByName('contactArchives.name').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.abbreviations').value!=''){
    		if(!isValidLength(document.forms[0], "contactArchives.abbreviations", null, 20)){
				alert("${action.getText('contactArchives.abbreviations.length')}");
				getObjByName('contactArchives.abbreviations').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.custName').value==''){
	        alert('${action.getText('contactArchives.custName.not.null')}');
	        getObjByName('contactArchives.custName').focus();
	        return false;
	     }
	     if(getObjByName('contactArchives.custType').value==''){
	        alert('${action.getText('contactArchives.custType.not.null')}');
	        getObjByName('contactArchives.custType').focus();
	        return false;
	     }
	     if(getObjByName('contactArchives.industry').value==''){
	        alert('${action.getText('contactArchives.industry.not.null')}');
	        getObjByName('contactArchives.industry').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "contactArchives.industry", null, 20)){
				alert("${action.getText('contactArchives.industry.length')}");
				getObjByName('contactArchives.industry').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.dept').value!=''){
	        if(!isValidLength(document.forms[0], "contactArchives.dept", null, 20)){
				alert("${action.getText('contactArchives.dept.length')}");
				getObjByName('contactArchives.dept').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.duty').value!=''){
    		if(!isValidLength(document.forms[0], "contactArchives.duty", null, 20)){
				alert("${action.getText('contactArchives.duty.length')}");
				getObjByName('contactArchives.duty').focus();
				return false;
			}
 		}
 		if(getObjByName('contactArchives.phone').value!=""){
    		if(!isValidLength(document.forms[0], "contactArchives.phone", null, 20)){
    			alert('${action.getText('contactArchives.phone.length')}');
    			getObjByName('contactArchives.phone').focus();
    			return false;
    		}
		}
		if(getObjByName('contactArchives.phone').value!=""){
			 if(!isValidLength(document.forms[0], "contactArchives.phone", null, 20)){
            	alert('${action.getText('contactArchives.phone.length')}');
            	getObjByName('contactArchives.phone').focus();
	        	return false;
            }
		}
		if(getObjByName('contactArchives.mobilePhone').value=='' && getObjByName('contactArchives.phone').value ==''){
	        alert('${action.getText('contactArchives.phoneOrmobilePhone.notnull')}');
	        getObjByName('contactArchives.mobilePhone').focus();
	        return false;
	     }
		if(getObjByName('contactArchives.mobilePhone').value!=''){
            if(!isValidLength(document.forms[0], "contactArchives.mobilePhone", null, 20)){
            	alert('${action.getText('contactArchives.mobilePhone.length')}');
            	getObjByName('contactArchives.mobilePhone').focus();
	        	return false;
            }
		}
		if(getObjByName('contactArchives.fax').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.fax", null, 20)){
            	alert('${action.getText('contactArchives.fax.length')}');
            	getObjByName('contactArchives.fax').focus();
	        	return false;
            }
	     }
	     if(getObjByName('contactArchives.email').value!=""){
	        if (!getObjByName('contactArchives.email').value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert('${action.getText('contactArchives.email.type')}');
          		getObjByName('contactArchives.email').focus();
          		return false;
          	}else{
	     		if(!isValidLength(document.forms[0], "contactArchives.email", null, 50)){
            		alert('${action.getText('contactArchives.email.length')}');
            		getObjByName('contactArchives.email').focus();
	        		return false;
	        	}
            }
	     }
	     if(getObjByName('contactArchives.qq').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.qq", null, 20)){
        		alert('${action.getText('contactArchives.qq.length')}');
        		getObjByName('contactArchives.qq').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.msn').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.msn", null, 20)){
        		alert('${action.getText('contactArchives.msn.length')}');
        		getObjByName('contactArchives.msn').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.homePhone').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.homePhone", null, 20)){
        		alert('${action.getText('contactArchives.homePhone.length')}');
        		getObjByName('contactArchives.homePhone').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.postCode').value!=""){
        	if(!isValidLength(document.forms[0], "contactArchives.postCode", null, 20)){
        		alert('${action.getText('contactArchives.postCode.length')}');
        		getObjByName('contactArchives.postCode').focus();
        		return false;
    		}
	     }
	     if(getObjByName('contactArchives.address').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.address", null, 50)){
        		alert('${action.getText('contactArchives.address.length')}');
        		getObjByName('contactArchives.address').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.school').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.school", null, 50)){
        		alert('${action.getText('contactArchives.school.length')}');
        		getObjByName('contactArchives.school').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.professional').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.professional", null, 20)){
        		alert('${action.getText('contactArchives.professional.length')}');
        		getObjByName('contactArchives.professional').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.favorite').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.favorite", null, 20)){
        		alert('${action.getText('contactArchives.favorite.length')}');
        		getObjByName('contactArchives.favorite').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.birthday').value !=''){
			if(!isDate('contactArchives.birthday')){
				alert("${action.getText('contactArchives.birthday.type')}");
				getObjByName('contactArchives.birthday').focus();
				return false;
			}
		}
		if(getObjByName('type.id').value==""){
	        alert('${action.getText('contactArchives.type.not.null')}');
	        getObjByName('type.id').focus();
	        return false;
	     }
		if(getObjByName('contactArchives.comment').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.comment", null, 500)){
        		alert('${action.getText('contactArchives.comment.length')}');
        		getObjByName('contactArchives.comment').focus();
        		return false;
        	}
	     }
		return true;
	}
</script>
</@htmlPage>
