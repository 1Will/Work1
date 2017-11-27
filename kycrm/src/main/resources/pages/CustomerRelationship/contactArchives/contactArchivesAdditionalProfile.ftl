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

<@framePage  title="">
<@ww.form namespace="'/customerRelationship'" name="'contactArchivesInfoAdditional'" action="'saveContactArchivesAdditional'" method="'post'">
	<@ww.token name="saveContactArchivesAdditionalToken"/>
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
    
     <!--@@附加信息@@-->
    <tr>
	    <!--籍贯-->
	  
		    	    <@ww.textfield label="'${action.getText('contactArchives.birthplace')}'" name="'contactArchives.birthplace'" value="'${contactArchives.birthplace?if_exists}'"  cssClass="'underline'" />
		    
		    
		    <!--名族-->
			<@ww.select label="'${action.getText('contactArchives.nationality')}'" 
				name="'nationality.id'" 
				value="'${req.getParameter('nationality.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allNationality"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
    </tr>
    <tr>
	    <!--毕业院校-->
	    <@ww.textfield label="'${action.getText('contactArchives.school')}'" name="'contactArchives.school'" value="'${contactArchives.school?if_exists}'" cssClass="'underline'" />
	    <!--专业-->
	    <@ww.textfield label="'${action.getText('contactArchives.professional')}'" name="'contactArchives.professional'" value="'${contactArchives.professional?if_exists}'" cssClass="'underline'" />
    </tr>
    <tr>
    <!--学历-->
	     <@ww.select label="'${action.getText('contactArchives.education')}'" 
				name="'education.id'" 
				value="'${req.getParameter('education.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allEducation"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
	<!--政治面貌-->
	     <@ww.select label="'${action.getText('contactArchives.politicalOutlook')}'" 
				name="'politicalOutlook.id'" 
				value="'${req.getParameter('politicalOutlook.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allPolitice"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>
    <tr>
	    <!--生日-->
	    <@pp.datePicker
				label="'${action.getText('contactArchives.birthday')}'" 
				name="'contactArchives.birthday'" 
	   			value="'${(contactArchives.birthday?string('yyyy'))?if_exists}'"
				cssClass="'underline'"  
				maxlength="10">
			</@pp.datePicker>
	    <!--性格-->
	     <@ww.select label="'${action.getText('contactArchives.temperament')}'" 
				name="'temperament.id'" 
				value="'${req.getParameter('temperament.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allTemperament"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>
    <tr>
	    <!--血型-->
	     <@ww.select label="'${action.getText('contactArchives.bloodType')}'" 
				name="'bloodType.id'" 
				value="'${req.getParameter('bloodType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBlood"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--星座-->
	     <@ww.select label="'${action.getText('contactArchives.constellation')}'" 
				name="'constellation.id'" 
				value="'${req.getParameter('constellation.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allConstellation"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>
    <tr>
	    <!--生肖-->
	     <@ww.select label="'${action.getText('contactArchives.chineseZodiac')}'" 
				name="'chineseZodiac.id'" 
				value="'${req.getParameter('chineseZodiac.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allChineseZodiac"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--九型人格 -->
	     <@ww.select label="'${action.getText('contactArchives.enneagram')}'" 
				name="'enneagram.id'" 
				value="'${req.getParameter('enneagram.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allEnneagram"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>
    
    <tr>
	    <!--宗教信仰-->
	     <@ww.select label="'${action.getText('contactArchives.religiousBelief')}'" 
				name="'religiousBelief.id'" 
				value="'${req.getParameter('religiousBelief.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allReligion"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--健康状况-->
	     <@ww.select label="'${action.getText('contactArchives.health')}'" 
				name="'health.id'" 
				value="'${req.getParameter('health.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allHealth"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>
    <tr>
	    <!--身高-->
	    <@ww.textfield label="'${action.getText('contactArchives.height')}'" name="'contactArchives.height'" value="'${contactArchives.height?if_exists}'" cssClass="'underline'" />
	    <!--体重-->
	    <@ww.textfield label="'${action.getText('contactArchives.weight')}'" name="'contactArchives.weight'" value="'${contactArchives.weight?if_exists}'" cssClass="'underline'" />
    </tr>
    
    
    
    <tr>
	    <!--视力-->
	     <@ww.select label="'${action.getText('contactArchives.vision')}'" 
				name="'vision.id'" 
				value="'${req.getParameter('vision.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allHealth"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
			<!--婚姻状况-->
	     <@ww.select label="'${action.getText('contactArchives.maritalStatus')}'" 
				name="'maritalStatus.id'" 
				value="'${req.getParameter('maritalStatus.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allMarriage"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
    </tr>    
    <tr>
	    <!--备注-->
	    <td align="right" valign="top">
	       		<label class="label">${action.getText('contactArchives.comment')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="contactArchives.comment" rows="4"  >${contactArchives.comment?if_exists}</textarea>
		</td>
		<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("contactArchives.comment").cols =width;
			</script>
	    <!---->
    </tr>
    <tr>
	    <!--兴趣爱好-->
	    <@ww.textfield label="'${action.getText('contactArchives.favorite')}'" name="'contactArchives.favorite'" value="'${contactArchives.favorite?if_exists}'" cssClass="'underline'" />
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
	
		<#if contactArchives.nationality?exists>
			getObjByName('nationality.id').value='${contactArchives.nationality.id?if_exists}';
		</#if>
		<#if contactArchives.temperament?exists>
			getObjByName('temperament.id').value='${contactArchives.temperament.id?if_exists}';
		</#if>
		
		<#if contactArchives.bloodType?exists>
			getObjByName('bloodType.id').value='${contactArchives.bloodType.id?if_exists}';
		</#if>
		
		<#if contactArchives.constellation?exists>
			getObjByName('constellation.id').value='${contactArchives.constellation.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.chineseZodiac?exists>
			getObjByName('chineseZodiac.id').value='${contactArchives.chineseZodiac.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.enneagram?exists>
			getObjByName('enneagram.id').value='${contactArchives.enneagram.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.religiousBelief?exists>
			getObjByName('religiousBelief.id').value='${contactArchives.religiousBelief.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.health?exists>
			getObjByName('health.id').value='${contactArchives.health.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.vision?exists>
			getObjByName('vision.id').value='${contactArchives.vision.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.maritalStatus?exists>
			getObjByName('maritalStatus.id').value='${contactArchives.maritalStatus.id?if_exists}';
		</#if>
		
		<#if contactArchives.education?exists>
			getObjByName('education.id').value='${contactArchives.education.id?if_exists}';
		</#if>
		
		
		<#if contactArchives.politicalOutlook?exists>
			getObjByName('politicalOutlook.id').value='${contactArchives.politicalOutlook.id?if_exists}';
		</#if>
		
		
	}
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
 		
	     if(getObjByName('contactArchives.school').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.school", null, 50)){
        		alert('${action.getText('contactArchives.school.length')}');
        		getObjByName('contactArchives.school').value="";
        		getObjByName('contactArchives.school').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.professional').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.professional", null, 20)){
        		alert('${action.getText('contactArchives.professional.length')}');
        		getObjByName('contactArchives.professional').value="";
        		getObjByName('contactArchives.professional').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.favorite').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.favorite", null, 20)){
        		alert('${action.getText('contactArchives.favorite.length')}');
        		getObjByName('contactArchives.favorite').value="";
        		getObjByName('contactArchives.favorite').focus();
        		return false;
        	}
	     }
	     if(getObjByName('contactArchives.birthday').value !=''){
			if(!isDate('contactArchives.birthday')){
				alert("${action.getText('contactArchives.birthday.type')}");
				getObjByName('contactArchives.birthday').value="";
				getObjByName('contactArchives.birthday').focus();
				return false;
			}
		}
		if(getObjByName('contactArchives.comment').value!=""){
     		if(!isValidLength(document.forms[0], "contactArchives.comment", null, 500)){
        		alert('${action.getText('contactArchives.comment.length')}');
        		getObjByName('contactArchives.comment').value="";
        		getObjByName('contactArchives.comment').focus();
        		return false;
        	}
	     }
		return true;
	}
</script>
</@framePage>
