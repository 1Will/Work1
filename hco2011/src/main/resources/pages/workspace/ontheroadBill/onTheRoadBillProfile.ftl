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

<@htmlPage title="${action.getText('onTheRoadBill.profile')}">
<@ww.form namespace="'/onTheRoadBill'" name="'onTheRoadBill'" action="'saveOnTheRoadBill'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveOnTheRoadBillToken"/>
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
    <@inputTable>
    	<#if onTheRoadBill.id?exists>
    		<@ww.hidden name="'onTheRoadBill.id'" value="#{onTheRoadBill.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('onTheRoadBill.code')}'" name="'onTheRoadBill.code'" value="'${onTheRoadBill.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('onTheRoadBill.crateDate')}'" 
				name="'onTheRoadBill.createDate'" 
	   			value="'${(onTheRoadBill.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("onTheRoadBill.createDate").value==''){
						getObjByName("onTheRoadBill.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#if onTheRoadBill.applyPerson?exists>
				<@ww.textfield label="'${action.getText('onTheRoadBill.applyPerson')}'" name="'onTheRoadBill.applyPerson'" value="'${onTheRoadBill.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('onTheRoadBill.applyPerson')}'" name="'onTheRoadBill.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('onTheRoadBill.dept')}'" 
				name="'dept.id'" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				emptyOption="true" 
				disabled="true">
			</@ww.select>
        </tr>
        <tr>
        	<@pp.datePicker 
					label="'${action.getText('onTheRoadBill.startTime')}'" 
					name="'onTheRoadBill.startTime'" 
		   			value="'${(onTheRoadBill.startTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("onTheRoadBill.startTime").value==''){
						getObjByName("onTheRoadBill.startTime").value = date.format("yyyy-MM-dd hh:mm");
					}
				</script>
			<@pp.datePicker 
					label="'${action.getText('onTheRoadBill.endTime')}'" 
					name="'onTheRoadBill.endTime'" 
		   			value="'${(onTheRoadBill.endTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
        </tr>
        <tr>
        <#if onTheRoadBill.id?exists>
        	<@ww.select label="'${action.getText('onTheRoadBill.status')}'" 
				name="'status.id'" 
				value="${req.getParameter('status.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				emptyOption="true" 
				disabled="true">
			</@ww.select>
		<#else>
			<@ww.select label="'${action.getText('onTheRoadBill.status')}'" 
				name="'status.id'" 
				value="${req.getParameter('status.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				disabled="true">
			</@ww.select>
		</#if>
        </tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('onTheRoadBill.betreffzeile')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="onTheRoadBill.betreffzeile" rows="4" cols="150" >${onTheRoadBill.betreffzeile?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('onTheRoadBill.failReason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="onTheRoadBill.failReason" rows="4" cols="150" readonly="true">${onTheRoadBill.failReason?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
   	    <#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/onTheRoadBill/listOnTheRoadBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if onTheRoadBill.dept?exists>
			getObjByName('dept.id').value='${onTheRoadBill.dept.id?if_exists}';
		<#else>
			<#if onTheRoadBill.applyPerson?exists>
				<#if onTheRoadBill.applyPerson.dept?exists>
					getObjByName('dept.id').value='${onTheRoadBill.applyPerson.dept.id}';
				</#if>
			</#if>
		</#if>
		<#if onTheRoadBill.status?exists>
			getObjByName('status.id').value='${onTheRoadBill.status.id?if_exists}';
		</#if>
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('onTheRoadBill.createDate').value ==''){
		 		alert("${action.getText('onTheRoadBill.createDate.not.null')}");
		 		getObjByName('onTheRoadBill.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('onTheRoadBill.createDate')){
				alert("${action.getText('onTheRoadBill.createDate.dateFormate.error')}");
	      	    getObjByName('onTheRoadBill.createDate').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('onTheRoadBill.createDate').value)){
				alert('${action.getText('onTheRoadBill.createDate.earlyError')}');
	       		getObjByName('onTheRoadBill.createDate').focus();
				return false;
			}
		}
		if(getObjByName('onTheRoadBill.applyPerson').value==''){
			alert("${action.getText('onTheRoadBill.applyPerson.requiredstring')}");
			getObjByName('onTheRoadBill.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		}
		if(getObjByName('onTheRoadBill.startTime').value ==''){
		 		alert("${action.getText('onTheRoadBill.startTime.not.null')}");
		 		getObjByName('onTheRoadBill.startTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('onTheRoadBill.startTime')){
				alert("${action.getText('onTheRoadBill.startTime.dateFormate.error')}");
	      	    getObjByName('onTheRoadBill.startTime').focus();
				return false;
			} 
		}
		
		if(getObjByName('onTheRoadBill.endTime').value ==''){
		 		alert("${action.getText('onTheRoadBill.endTime.not.null')}");
		 		getObjByName('onTheRoadBill.endTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('onTheRoadBill.endTime')){
				alert("${action.getText('onTheRoadBill.endTime.dateFormate.error')}");
	      	    getObjByName('onTheRoadBill.endTime').focus();
				return false;
			} 
			if(getObjByName('onTheRoadBill.startTime').value>getObjByName('onTheRoadBill.endTime').value){
				alert('${action.getText('onTheRoadBill.endTime.earlyError')}');
	       		getObjByName('onTheRoadBill.endTime').focus();
				return false;
			}
		}
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		
		if(getObjByName('onTheRoadBill.betreffzeile').value==''){
			alert("${action.getText('onTheRoadBill.betreffzeile.requiredstring')}");
			getObjByName('onTheRoadBill.betreffzeile').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
