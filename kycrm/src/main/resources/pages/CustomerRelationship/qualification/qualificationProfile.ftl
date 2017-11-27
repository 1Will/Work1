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

<#-- $Id: additionalInfoProfile.ftl 2009-12-08 10:00:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />
<@htmlPage title='荣誉资质信息维护'>
<@ww.form name="'qualification'" namespace="'/customerRelationship'" action="'saveQualification'" method="'post'">
	<@ww.token name="saveQualificationToken"/>
	<#if qualification.id?exists>
    		<@ww.hidden name="'qualification.id'" value="#{qualification.id}"/>
    		</#if>
	<@ww.hidden name="'customerInfo.id'" value="'#{customerInfoId?if_exists}'" />
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@inputTable>
 	<tr>
        <@ww.textfield label="'${action.getText('name')}'" name="'qualification.name'"  cssClass="'underline'" required="false"/>
    
         <@ww.select label="'${action.getText('level')}'" 
				name="'level.id'" 
				value="'${req.getParameter('level.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allLevels"
				required="true"
				emptyOption="false" 
				>
			</@ww.select>
			<script language="javascript">
		     	<#if qualification.level?exists>
		     		getObjByName('level.id').value = ${qualification.level.id};
		     	</#if>
			</script>
    </tr>
    <tr>
   		<@ww.textfield label="'${action.getText('certificateNumber')}'" name="'qualification.certificateNumber'" cssClass="'underline'" required="false"/>
        <@pp.datePicker 
				label="'${action.getText('obtainDate')}'" 
				name="'qualification.obtainDate'" 
	   			value="'${(qualification.obtainDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10" />
				<script language="javascript">
				<#if qualification.id?exists>
				<#else>
					var date = new Date();
					if(getObjByName("qualification.obtainDate").value==''){
						getObjByName("qualification.obtainDate").value = date.format("yyyy-MM-dd");
					}
				</#if>
		   </script>
    
	</tr>
	 <tr>
   		<@ww.textfield label="'${action.getText('approvalUnit')}'" name="'qualification.approvalUnit'"  cssClass="'underline'" required="false"/>
    
	</tr>
	</@inputTable>
	<#if !(action.isReadOnly())>
		 <@buttonBar>
    	<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'"  onclick="'return storeValidation();'" />
		
				<#-- 继续新建按钮   -->
				<#if qualification.id?exists>
						<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
				<#else>
					<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/customerRelationship/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&customerInfo.id=#{customerInfoId?if_exists}"/>
						<script language="JavaScript" type="text/JavaScript"> 
						getObjByName("newNext").disabled="true";
						</script>
				</#if>
		</#if>
		<!-- 关闭按钮 -->
		<@vbutton name="close" value="${action.getText('close')}" class="button" onclick="closeThis();"/>
    </@buttonBar>
    </#if>
</@ww.form>
<script>

function storeValidation(){
  if(getObjByName('qualification.name').value ==''){
  alert("请输入"+"${action.getText('name')}");
  getObjByName('qualification.name').value="";
				getObjByName('qualification.name').focus();
				return false;
  }else {
	        if(!isValidLength(document.forms[0], "qualification.name", null, 125)){
				alert("${action.getText('name')}"+"字数不能超过125");
				getObjByName('qualification.name').value="";
				getObjByName('qualification.name').focus();
				return false;
			}
		}
		
		 if(getObjByName('level.id').value ==''){
  alert("请选择"+"${action.getText('level')}");
  getObjByName('level.id').value="";
				getObjByName('level.id').focus();
				return false;
  }
		 if(getObjByName('qualification.certificateNumber').value ==''){
  alert("请输入"+"${action.getText('certificateNumber')}");
  getObjByName('qualification.certificateNumber').value="";
				getObjByName('qualification.certificateNumber').focus();
				return false;
  }else {
	        if(!isValidLength(document.forms[0], "qualification.certificateNumber", null, 125)){
				alert("${action.getText('certificateNumber')}"+"字数不能超过125");
				getObjByName('qualification.certificateNumber').value="";
				getObjByName('qualification.certificateNumber').focus();
				return false;
			}
		}
		 if(getObjByName('qualification.obtainDate').value ==''){
  alert("请输入"+"${action.getText('obtainDate')}");
  getObjByName('qualification.obtainDate').value="";
				getObjByName('qualification.obtainDate').focus();
				return false;
  }
		 if(getObjByName('qualification.approvalUnit').value ==''){
  alert("请输入"+"${action.getText('approvalUnit')}");
  getObjByName('qualification.approvalUnit').value="";
				getObjByName('qualification.approvalUnit').focus();
				return false;
  }else {
	        if(!isValidLength(document.forms[0], "qualification.approvalUnit", null, 125)){
				alert("${action.getText('approvalUnit')}"+"字数不能超过125");
				getObjByName('qualification.approvalUnit').value="";
				getObjByName('qualification.approvalUnit').focus();
				return false;
			}
		}
		
		}
</script>
</@htmlPage>
