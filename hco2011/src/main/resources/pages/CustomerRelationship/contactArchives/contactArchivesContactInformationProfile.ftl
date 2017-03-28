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

<@framePage title="">
<@ww.form namespace="'/customerRelationship'" name="'contactArchivesInfoContactInformation'" action="'saveContactArchivesContactInformation'" method="'post'">
	<@ww.token name="saveContactArchivessaveContactArchivesTokenToken"/>
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
    <tr>
	    <!--手机号码-->
       		<@ww.textfield label="'${action.getText('contactArchives.mobilePhone')}'" name="'contactArchives.mobilePhone'" value="'${contactArchives.mobilePhone?if_exists}'" cssClass="'underline'" />
	    <!--QQ号码-->
	    <@ww.textfield label="'${action.getText('contactArchives.qq')}'" name="'contactArchives.qq'" value="'${contactArchives.qq?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
	    <!--微信号码-->
       		<@ww.textfield label="'${action.getText('contactArchives.weChat')}'" name="'contactArchives.weChat'" value="'${contactArchives.weChat?if_exists}'" cssClass="'underline'" />
	    <!--msn-->
	    <@ww.textfield label="'${action.getText('contactArchives.msn')}'" name="'contactArchives.msn'" value="'${contactArchives.msn?if_exists}'" cssClass="'underline'"/>
    </tr>
    <tr>
    	 <!--办公电话-->
	    <@ww.textfield label="'${action.getText('contactArchives.phone')}'" name="'contactArchives.phone'" value="'${contactArchives.phone?if_exists}'" cssClass="'underline'"/>
	    <!--E-mail-->
	    <@ww.textfield label="'${action.getText('contactArchives.email')}'" name="'contactArchives.email'" value="'${contactArchives.email?if_exists}'" cssClass="'underline'" />
    </tr>
     <tr>
	    <!--传真-->
	    
       	<@ww.textfield label="'${action.getText('contactArchives.chuanzhen')}'" name="'contactArchives.chuanzhen'" value="'${contactArchives.chuanzhen?if_exists}'" cssClass="'underline'" />
	    <!--家庭电话-->
	    <@ww.textfield label="'${action.getText('contactArchives.homePhone')}'" name="'contactArchives.homePhone'" value="'${contactArchives.homePhone?if_exists}'" cssClass="'underline'"/>
	   
    </tr>
    <tr>
	    <!--家庭住址-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('contactArchives.address')}:</label>
	     	</td>
			<td colspan="8">
				<input type="text" name="contactArchives.address" class="underline"  value="${contactArchives.address?if_exists}" maxlength="140" size="120" />
		</td>
    </tr>
    <tr>
	    <!--通讯住址-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('contactArchives.postalAddress')}:</label>
	     	</td>
			<td colspan="8">
				<input type="text" name="contactArchives.postalAddress" class="underline"  value="${contactArchives.postalAddress?if_exists}" maxlength="140" size="120" />
		</td>
    </tr>
    <tr>
	    <!--邮编-->
       	<@ww.textfield label="'${action.getText('contactArchives.zipCode')}'" name="'contactArchives.zipCode'" value="'${contactArchives.zipCode?if_exists}'" cssClass="'underline'" />
	    <!--其他联系方式-->
	    <@ww.textfield label="'${action.getText('contactArchives.qitalink')}'" name="'contactArchives.qitalink'" value="'${contactArchives.qitalink?if_exists}'" cssClass="'underline'"/>
	   
    </tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<#-- <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/customerRelationship/listContactArchives.html?readOnly=${req.getParameter('readOnly')?if_exists}"/> -->
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('contactArchives.phone').value=='' && getObjByName('contactArchives.mobilePhone').value==''){
	        alert('${action.getText('mobyePhone.or.telePhone')}');
	        getObjByName('contactArchives.phone').focus();
	        return false;
	     }
 		
		
		if(getObjByName('contactArchives.phone').value!=''){
            if(!isValidLength(document.forms[0], "contactArchives.phone", null, 20)){
            	alert('${action.getText('contactArchives.phone.length')}');
            	getObjByName('contactArchives.phone').value=""
            	getObjByName('contactArchives.phone').focus();
	        	return false;
            }
		}
		if(getObjByName('contactArchives.mobilePhone').value!=''){
            if(!isValidLength(document.forms[0], "contactArchives.mobilePhone", null, 20)){
            	alert('${action.getText('contactArchives.mobilePhone.length')}');
            	getObjByName('contactArchives.mobilePhone').value=""
            	getObjByName('contactArchives.mobilePhone').focus();
	        	return false;
            }
		}
	     if(getObjByName('contactArchives.email').value!=""){
	        if (!getObjByName('contactArchives.email').value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
          		alert('${action.getText('contactArchives.email.type')}');
          		getObjByName('contactArchives.email').value="";
          		getObjByName('contactArchives.email').focus();
          		return false;
          	}else{
	     		if(!isValidLength(document.forms[0], "contactArchives.email", null, 50)){
            		alert('${action.getText('contactArchives.email.length')}');
            		getObjByName('contactArchives.email').value="";
          			getObjByName('contactArchives.email').focus();
	        		return false;
	        	}
            }
	     }
	     if(getObjByName('contactArchives.qq').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.qq", null, 20)){
        		alert('${action.getText('contactArchives.qq.length')}');
        		getObjByName('contactArchives.qq').value="";
        		getObjByName('contactArchives.qq').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.homePhone').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.homePhone", null, 20)){
        		alert('${action.getText('contactArchives.homePhone.length')}');
        		getObjByName('contactArchives.homePhone').value="";
        		getObjByName('contactArchives.homePhone').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.address').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.address", null, 50)){
        		alert('${action.getText('contactArchives.address.length')}');
        		getObjByName('contactArchives.address').value="";
        		getObjByName('contactArchives.address').focus();
        		return false;
        	}
	     }
		return true;
	}
</script>
</@framePage>

