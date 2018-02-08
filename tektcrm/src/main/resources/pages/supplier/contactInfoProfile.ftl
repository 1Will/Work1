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

<#-- $Id: contactArchivesProfile.ftl 2009-12-16 14:50:35Z mfzhang $ -->

<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('contactArchives.profile')}">
<@ww.form namespace="'/supplierManager'" name="'contactArchivesInfo'" action="'saveContactInfo'" method="'post'">
	<@ww.token name="saveContactInfoToken"/>
    <@inputTable>
    	<@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>
    	<#--<@ww.hidden name="'temperament'" value="'${req.getParameter('temperament')?if_exists}'"/>-->
    	<#--<@ww.hidden name="'type'" value="'${req.getParameter('type')?if_exists}'"/>-->
    	<@ww.hidden name="'customer.id'" value="''"/>
    	<@ww.hidden name="'customerType.id'" value="''"/>
    	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    	<#if contactArchives.id?exists>
    		<@ww.hidden name="'contactArchives.id'" value="#{contactArchives.id}"/>
	 	</#if>
        <#if supplier?exists>
        <#if supplier.id?exists>
            <@ww.hidden name="'supplier.id'" value="#{supplier.id}"/>
        </#if>
        </#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('contactArchives.name')}'" name="'contactArchives.name'" value="'${contactArchives.name?if_exists}'" cssClass="'underline'" required="true" />
			<@ww.textfield label="'${action.getText('contactArchives.abbreviations')}'" name="'contactArchives.abbreviations'" value="'${contactArchives.abbreviations?if_exists}'" cssClass="'underline'" required="false" />
			<@ww.select label="'${action.getText('contactArchives.sex')}'" 
				name="'contactArchives.sex'" 
				value="'${req.getParameter('contactArchives.sex')?if_exists}'"
				headerKey="id"
				headerValue="name"
				list="{
					'${action.getText('contactArchives.man')}',
					'${action.getText('contactArchives.women')}'
				}"
				required="false"
				emptyOption="false" 
				disabled="false" >
			</@ww.select>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('contactArchives.dept')}'" name="'contactArchives.dept'" value="'${contactArchives.dept?if_exists}'" cssClass="'underline'" required="true" />
         	<@ww.textfield label="'${action.getText('contactArchives.duty')}'" name="'contactArchives.duty'" value="'${contactArchives.duty?if_exists}'" cssClass="'underline'"/>
			<#--
			<@ww.select label="'${action.getText('contactArchives.duty')}'"
				name="'duty.id'" 
				value="'${req.getParameter('duty.id')?if_exists}'" 
				listKey="id"
				listValue="name"
				list="allDuty"
		    	emptyOption="true" 
		    	disabled="false">
		    </@ww.select>
		    -->
			<@ww.select label="'${action.getText('contactArchives.birthplace')}'"
				name="'birthplace.id'" 
				value="'${req.getParameter('birthplace.id')?if_exists}'" 
				listKey="id"
				listValue="name"
				list="allBirthplace"
		    	emptyOption="true" 
		    	disabled="false"
		    	required="false">
		    </@ww.select> 
        </tr>
        <tr>	
			<@ww.select label="'${action.getText('contactArchives.nationality')}'" 
				name="'nationality.id'" 
				value="'${req.getParameter('nationality.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allNationality"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<@ww.textfield label="'${action.getText('contactArchives.phone')}'" name="'contactArchives.phone'" value="'${contactArchives.phone?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('contactArchives.mobilePhone')}'" name="'contactArchives.mobilePhone'" value="'${contactArchives.mobilePhone?if_exists}'" cssClass="'underline'" required="true"/>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('contactArchives.homePhone')}'" name="'contactArchives.homePhone'" value="'${contactArchives.homePhone?if_exists}'" cssClass="'underline'"/>
        	<@ww.textfield label="'${action.getText('contactArchives.fax')}'" name="'contactArchives.fax'" value="'${contactArchives.fax?if_exists}'" cssClass="'underline'"/>
			<@ww.textfield label="'${action.getText('contactArchives.qq')}'" name="'contactArchives.qq'" value="'${contactArchives.qq?if_exists}'" cssClass="'underline'"/>
        	
        </tr>
        <tr>	
        	<@ww.textfield label="'${action.getText('contactArchives.msn')}'" name="'contactArchives.msn'" value="'${contactArchives.msn?if_exists}'" cssClass="'underline'" />
        	<@ww.textfield label="'${action.getText('contactArchives.email')}'" name="'contactArchives.email'" value="'${contactArchives.email?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('contactArchives.postCode')}'" name="'contactArchives.postCode'" value="'${contactArchives.postCode?if_exists}'" cssClass="'underline'" />
        </tr>
		<tr>
			<td align="right" valign="top">
	       		<!--<span class="required">*</span>-->
	       		<label class="label">${action.getText('contactArchives.address')}:</label>
	     	</td>
			<td colspan="8">
				<input type="text" name="contactArchives.address" class="underline"  value="${contactArchives.address?if_exists}" maxlength="140" size="120" />
			</td>
		</tr>
		<tr>
			<@ww.textfield label="'${action.getText('contactArchives.school')}'" name="'contactArchives.school'" value="'${contactArchives.school?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('contactArchives.professional')}'" name="'contactArchives.professional'" value="'${contactArchives.professional?if_exists}'" cssClass="'underline'" />
			<@ww.textfield label="'${action.getText('contactArchives.favorite')}'" name="'contactArchives.favorite'" value="'${contactArchives.favorite?if_exists}'" cssClass="'underline'" />
		</tr>
		<tr>
			<@pp.datePicker
				label="'${action.getText('contactArchives.birthday')}'" 
				name="'contactArchives.birthday'" 
	   			value="'${(contactArchives.birthday?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				maxlength="10">
			</@pp.datePicker>
			<#--
			<@ww.select label="'${action.getText('contactArchives.temperament')}'"
				name="'temperament.id'" 
				value="${req.getParameter('temperament.id')?if_exists}" 
				headerKey="id"
				headerValue="name"
			    list="{
			    	'${action.getText('contactArchives.outward')}',
			    	'${action.getText('contactArchives.inward')}'
			    }"
		    	emptyOption="true" 
		    	disabled="false">
		    </@ww.select>
		    -->
		    <@ww.select label="'${action.getText('contactArchives.temperament')}'" 
				name="'temperament.id'" 
				value="'${req.getParameter('temperament.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTemperament"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		    <#--
			<@ww.select label="'${action.getText('contactArchives.caType')}'" 
				name="'type.id'" 
				value="'${req.getParameter('type.id')?if_exists}'"
				headerKey="id"
				headerValue="name"
				list="{
			    	'${action.getText('contactArchives.acquaintance')}',
			    	'${action.getText('contactArchives.stranger')}'
			    }"
			    required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			-->
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
	        		<label class="label">
	        			${action.getText('contactArchives.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="contactArchives.comment" rows="4" cols="150" >${contactArchives.comment?if_exists}</textarea>
		        </td>
		</tr>
    </@inputTable>
    <#if !(action.isReadOnly())>
    <@buttonBar>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		<@vbutton class="button" name="${action.getText('close')}" value="${action.getText('close')}" onclick="closeThis()"/>
    </@buttonBar>
	</#if>
</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if sex?exists>
			<#if sex?string == 'true'>
				getObjByName('contactArchives.sex').value = '${action.getText('contactArchives.women')}';
			<#elseif sex?string == 'false'>
				getObjByName('contactArchives.sex').value = '${action.getText('contactArchives.man')}';
			</#if>
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
		<#--
		<#if contactArchives.duty?exists>
			getObjByName('duty.id').value='${contactArchives.duty.id?if_exists}';
		</#if>
		-->
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('contactArchives.sex').value=="${action.getText('contactArchives.man')}"){
			getObjByName('sex').value=false;
		}else if(getObjByName('contactArchives.sex').value=="${action.getText('contactArchives.women')}"){
			getObjByName('sex').value=true;
		}
		<#--
		if(getObjByName('temperament.id').value=="${action.getText('contactArchives.outward')}"){
			getObjByName('temperament').value="${action.getText('contactArchives.outward')}";
		}else if(getObjByName('temperament.id').value=="${action.getText('contactArchives.inward')}"){
			getObjByName('temperament').value="${action.getText('contactArchives.inward')}";
		}
		if(getObjByName('type.id').value=="${action.getText('contactArchives.acquaintance')}"){
			getObjByName('type').value="${action.getText('contactArchives.acquaintance')}";
		}else if(getObjByName('type.id').value=="${action.getText('contactArchives.stranger')}"){
			getObjByName('type').value="${action.getText('contactArchives.stranger')}";
		}
		-->
		//----------------------------------------------------------------------
		//姓名
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
		//称呼
		 if(getObjByName('contactArchives.abbreviations').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.abbreviations", null, 20)){
            	alert('${action.getText('contactArchives.abbreviations.length')}');
            	getObjByName('contactArchives.abbreviations').focus();
	        	return false;
            }
	     }
		//部门
		if(getObjByName('contactArchives.dept').value==''){
	        alert('${action.getText('contactArchives.dept.not.null')}');
	        getObjByName('contactArchives.dept').focus();
	        return false;
	     }else{
	        if(!isValidLength(document.forms[0], "contactArchives.dept", null, 20)){
				alert("${action.getText('contactArchives.dept.length')}");
				getObjByName('contactArchives.dept').focus();
				return false;
			}
		}
		//职务
		<#---->
	     if(getObjByName('contactArchives.duty').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.duty", null, 20)){
            	alert('${action.getText('contactArchives.duty.length')}');
            	getObjByName('contactArchives.duty').focus();
	        	return false;
            }
	     }	
	     	
		// 办公电话   
	     if(getObjByName('contactArchives.phone').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.phone", null, 20)){
            	alert('${action.getText('contactArchives.phone.length')}');
            	getObjByName('contactArchives.phone').focus();
	        	return false;
            }
	     }
	    //手机
		if(getObjByName('contactArchives.mobilePhone').value==''){
	        alert('${action.getText('contactArchives.mobilePhone.not.null')}');
	        getObjByName('contactArchives.mobilePhone').focus();
	        return false;
	     }else{
	     	if(isNaN(getObjByName('contactArchives.mobilePhone').value)){
	     		alert('${action.getText('contactArchives.mobilePhone.type')}');
	     		getObjByName('contactArchives.mobilePhone').value=""
	     		getObjByName('contactArchives.mobilePhone').focus();
	        	return false;
	     	}
            if(!isValidLength(document.forms[0], "contactArchives.mobilePhone", null, 20)){
            	alert('${action.getText('contactArchives.mobilePhone.length')}');
            	getObjByName('contactArchives.mobilePhone').focus();
	        	return false;
            }
		}
		//家庭电话
	     if(getObjByName('contactArchives.homePhone').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.homePhone", null, 20)){
            	alert('${action.getText('contactArchives.homePhone.length')}');
            	getObjByName('contactArchives.homePhone').focus();
	        	return false;
            }
	     }			    
		//传真
	     if(getObjByName('contactArchives.fax').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.fax", null, 20)){
            	alert('${action.getText('contactArchives.fax.length')}');
            	getObjByName('contactArchives.fax').focus();
	        	return false;
            }
	     }
	     //QQ
	     if(getObjByName('contactArchives.qq').value!=""){
	      <#--
	     	if(isNaN(getObjByName('contactArchives.qq').value)){
	     		alert('${action.getText('contactArchives.qq.type')}');
	     		return false;
	     	}else{
	     	-->
	     		if(!isValidLength(document.forms[0], "contactArchives.qq", null, 20)){
            		alert('${action.getText('contactArchives.qq.length')}');
            		getObjByName('contactArchives.qq').focus();
	        		return false;
	        	}
            
	     }	     
	     //MSN
	     if(getObjByName('contactArchives.msn').value!=""){
	     <#--
	     	if(isNaN(getObjByName('contactArchives.msn').value)){
	     		alert('${action.getText('contactArchives.msn.type')}');
	     		return false;
	     	}else{
	     -->	
	     		if(!isValidLength(document.forms[0], "contactArchives.msn", null, 20)){
            		alert('${action.getText('contactArchives.msn.length')}');
            		getObjByName('contactArchives.msn').focus();
	        		return false;
	        	}
           
	     }
	     //Email
	     if(getObjByName('contactArchives.email').value!=""){
	        if (!getObjByName('contactArchives.email').value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert('${action.getText('contactArchives.email.type')}');
          		getObjByName('contactArchives.email').focus();
          		return false;
          	}
          	else{
	     		if(!isValidLength(document.forms[0], "contactArchives.email", null, 50)){
            		alert('${action.getText('contactArchives.email.length')}');
            		getObjByName('contactArchives.email').focus();
	        		return false;
	        	}
            }
	     }	     	     
	     //邮编
	     <#--
	     if(getObjByName('contactArchives.postCode').value!=""){
	     	var reg = "/^[0-9]{6}$/";
	     	if(!reg.test(getObjByName('contactArchives.postCode').value)){
            	alert('${action.getText('contactArchives.postCode.type')}');
	        	return false;
	        }
	     }
	     -->
	     if(getObjByName('contactArchives.postCode').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.postCode", null, 20)){
        		alert('${action.getText('contactArchives.postCode.length')}');
        		getObjByName('contactArchives.postCode').focus();
        		return false;
        	}	     	
	     }
	     //家庭住址
		if(!isValidLength(document.forms[0], "contactArchives.address", null, 50) && '' !=getObjByName('contactArchives.address').value){
		   alert("${action.getText('contactArchives.address.length')}");
		   getObjByName('contactArchives.address').focus();
		   return  false;
	    }
	     //学校
	     if(getObjByName('contactArchives.school').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.school", null, 50)){
            	alert('${action.getText('contactArchives.school.length')}');
            	getObjByName('contactArchives.school').focus();
	        	return false;
            }
	     }
	     //专业
	     if(getObjByName('contactArchives.professional').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.professional", null, 20)){
            	alert('${action.getText('contactArchives.professional.length')}');
            	getObjByName('contactArchives.professional').focus();
	        	return false;
            }
	     }
	     //兴趣
	     if(getObjByName('contactArchives.favorite').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.favorite", null, 20)){
            	alert('${action.getText('contactArchives.favorite.length')}');
            	getObjByName('contactArchives.favorite').focus();
	        	return false;
            }
	     }	     	     	     	     
	     //出生日期
	     if(getObjByName('contactArchives.birthday').value!=""){
	     	if(!isDate('contactArchives.birthday')){
				alert("${action.getText('contactArchives.birthday.type')}");
				getObjByName('contactArchives.birthday').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('contactArchives.birthday').value)){
				alert('${action.getText('contactArchives.birthday.earlyError')}');
				getObjByName('contactArchives.birthday').focus();
				return false;
			}
	     }
	     //类型
		if(getObjByName('type.id').value==""){
	        alert('${action.getText('contactArchives.type.not.null')}');
	        getObjByName('type.id').focus();
	        return false;
	     }
	     //备注
	     if(getObjByName('contactArchives.comment').value!=""){
	        if(!isValidLength(document.forms[0], "contactArchives.comment", null, 255)){
            	alert('${action.getText('contactArchives.comment.length')}');
            	getObjByName('contactArchives.comment').focus();
	        	return false;
            }
	     }	     
		return true;
	}
</script>
</@htmlPage>
