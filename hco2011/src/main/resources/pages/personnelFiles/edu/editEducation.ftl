<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('educationList')}">
     <@ww.form  name="'saveEducation'" action="'saveEducation'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveEducationToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <#if education?exists>
       	<@ww.hidden name="'education.id'" value="'${education.id?if_exists}'" />
       	</#if>
		<#if personnelFiles?exists>
	           <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id}"/>
		</#if>
		<#if contactArchives?exists>
            <@ww.hidden name="'contactArchives.id'" value="#{contactArchives.id}"/>
		</#if>
         <@inputTable>
			<tr>
				<@textfield label="${action.getText('education.degree')}" anothername="degree" name="education.degree" id="education.degree" value="${education.degree?if_exists}" maxlength="20"/>
				<@select label="${action.getText('education.edu')}" 
					name="education.edu1.id"
					anothername="edu"
					listKey="id"
					listValue="name" 
					list="allEdu"
					required="true"
					emptyOption="true"
					/>
			</tr>
			<tr>
				<@textfield label="${action.getText('education.profession')}" 
					name="education.profession" id="education.profession"
					value="${education.profession?if_exists}"  maxlength="20"/>
				<@textfield label="${action.getText('education.forms')}" name="education.forms" id="education.forms" value="${education.forms?if_exists}"  maxlength="20"/>
				</tr>
				<tr>
				<@datePickerRanger
						anothername="admissionDate"
		        		label="${action.getText('education.admissionDate')}"
		        		value="${(education.admissionDate?string('yyyy-MM-dd'))?if_exists}"
			           	name="education.admissionDate"
						cssClass="underline" 
						maxlength="10" 
						required="true"
						flag="true">
					</@datePickerRanger>
					<@datePickerRanger
						anothername="graduationDate"
		        		label="${action.getText('education.graduationDate')}"
		        		value="${(education.graduationDate?string('yyyy-MM-dd'))?if_exists}"
			           	name="education.graduationDate"
						cssClass="underline" 
						maxlength="10" 
						required="false"
						flag="true">
					</@datePickerRanger>
			</tr>
			<tr>
				<@textfield label="${action.getText('education.school')}" name="education.school" value="${education.school?if_exists}"  maxlength="50"/>
			</tr>
			<tr>
				<td align="right" valign="top">
	        		<label class="label">
	        			${action.getText('education.comment')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea name="education.comment" rows="4" cols="95" >${education.comment?if_exists}</textarea>
		        </td>
			</tr>
			</tr>
		</@inputTable>
         <@buttonBar>
         	 <#if !(action.isReadOnly())>
	        	<@vsubmit value="'${action.getText('save')}'" onclick ="'return checkField()'"/>
	         </#if>
	         <@vbutton value="${action.getText('close')}" onclick="closeThis()"/>
         </@buttonBar>	
     </@ww.form>
</@htmlPage>
<script language="javascript">
	//window.onload=function(){
		<#if education.edu?exists>
			getObjByName('education.edu1.id').value=#{education.edu.id};
		</#if>
	//}
	function checkField(){
		if(!selectCheck_edu()){
			getObjByName('education.edu1.id').focus();
			return false;
		}
		if(!dateCheck_admissionDate()){
			return false;
		}
		if(!getObjByName('education.graduationDate').value==''){
			if(!dateCheck_graduationDate()){
				return false;
			}else{
			//验证入学时间是否大于毕业时间
				var star = getObjByName('education.admissionDate').value;
				var end = getObjByName('education.graduationDate').value;
				if(isDateLessThenOldDate(star,end)){
					alert('${action.getText('education.time.error')}');
					getObjByName('education.admissionDate').focus();
					return false;
					}
				}
		}
		if(getObjByName('education.comment').value.length>500){
			alert('${action.getText('education.comment.alert')}')
			return false;
		}
		return true;
	}
</script>