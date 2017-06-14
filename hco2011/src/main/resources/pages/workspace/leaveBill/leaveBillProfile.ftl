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

<@htmlPage title="${action.getText('leaveBill.profile')}">
<@ww.form namespace="'/leaveBill'" name="'leaveBill'" action="'saveLeaveBill'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveLeaveBillToken"/>
    <@inputTable>
    	<#if leaveBill.id?exists>
    		<@ww.hidden name="'leaveBill.id'" value="#{leaveBill.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('leaveBill.code')}'" name="'leaveBill.code'" value="'${leaveBill.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('leaveBill.crateDate')}'" 
				name="'leaveBill.createDate'" 
	   			value="'${(leaveBill.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="20"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("leaveBill.createDate").value==''){
						getObjByName("leaveBill.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#if leaveBill.applyPerson?exists>
				<@ww.textfield label="'${action.getText('leaveBill.applyPerson')}'" name="'leaveBill.applyPerson'" value="'${leaveBill.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('leaveBill.applyPerson')}'" name="'leaveBill.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('leaveBill.dept')}'" 
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
					label="'${action.getText('leaveBill.startTime')}'" 
					name="'leaveBill.startTime'" 
		   			value="'${(leaveBill.startTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("leaveBill.startTime").value==''){
						getObjByName("leaveBill.startTime").value = date.format("yyyy-MM-dd hh:mm");
					}
				</script>
			<@pp.datePicker 
					label="'${action.getText('leaveBill.endTime')}'" 
					name="'leaveBill.endTime'" 
		   			value="'${(leaveBill.endTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
        </tr>
        <tr>
        	<@ww.textfield label="'${action.getText('leaveBill.manHour')}'" name="'leaveBill.manHour'" value="'${leaveBill.manHour?if_exists}'" cssClass="'underline'" required="true" readonly="false"/>
        	<#if leaveBill.id?exists>
	        	<@ww.select label="'${action.getText('leaveBill.status')}'" 
					name="'status.id'" 
					value="${req.getParameter('status.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					emptyOption="true" 
					disabled="true">
				</@ww.select>
			<#else>
				<@ww.select label="'${action.getText('leaveBill.status')}'" 
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
        	<@ww.select label="'${action.getText('leaveBill.type')}'" 
				name="'type.id'" 
				value="${req.getParameter('type.id')?if_exists}"
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
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('leaveBill.betreffzeile')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="leaveBill.betreffzeile" rows="4" cols="150" >${leaveBill.betreffzeile?if_exists}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('leaveBill.failReason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="leaveBill.failReason" rows="4" cols="150" readonly="true">${leaveBill.failReason?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'">
		</@vsubmit>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/leaveBill/listLeaveBill.html"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if leaveBill.dept?exists>
			getObjByName('dept.id').value='${leaveBill.dept.id?if_exists}';
		<#else>
			<#if leaveBill.applyPerson?exists>
				<#if leaveBill.applyPerson.dept?exists>
					getObjByName('dept.id').value='${leaveBill.applyPerson.dept.id}';
				</#if>
			</#if>
		</#if>
		<#if leaveBill.status?exists>
			getObjByName('status.id').value='${leaveBill.status.id?if_exists}';
		</#if>
		<#if leaveBill.type?exists>
			getObjByName('type.id').value='${leaveBill.type.id?if_exists}';
		</#if>
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('leaveBill.createDate').value ==''){
		 		alert("${action.getText('leaveBill.createDate.not.null')}");
		 		getObjByName('leaveBill.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('leaveBill.createDate')){
				alert("${action.getText('leaveBill.createDate.dateFormate.error')}");
	      	    getObjByName('leaveBill.createDate').focus();
				return false;
			} 
		}
		if(getObjByName('leaveBill.applyPerson').value==''){
			alert("${action.getText('leaveBill.applyPerson.requiredstring')}");
			getObjByName('leaveBill.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		}
		
		if(getObjByName('leaveBill.startTime').value ==''){
	 		alert("${action.getText('leaveBill.startTime.not.null')}");
	 		getObjByName('leaveBill.startTime').focus();
	      	return false;   
		}else{
		 	if(!isDate_Hour_Min('leaveBill.startTime')){
				alert("${action.getText('leaveBill.startTime.dateFormate.error')}");
	      	    getObjByName('leaveBill.startTime').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('leaveBill.startTime').value)){
				alert('${action.getText('leaveBill.startTime.earlyError')}');
	       		getObjByName('leaveBill.startTime').focus();
				return false;
			}
		}
		
		if(getObjByName('leaveBill.endTime').value ==''){
		 		alert("${action.getText('leaveBill.endTime.not.null')}");
		 		getObjByName('leaveBill.endTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('leaveBill.endTime')){
				alert("${action.getText('leaveBill.endTime.dateFormate.error')}");
	      	    getObjByName('leaveBill.endTime').focus();
				return false;
			} 
			if(getObjByName('leaveBill.startTime').value>getObjByName('leaveBill.endTime').value){
				alert('${action.getText('leaveBill.endTime.earlyError')}');
	       		getObjByName('leaveBill.endTime').focus();
				return false;
			}
		}
		
		if(getObjByName('leaveBill.manHour').value==''){
			alert("${action.getText('leaveBill.manHour.requiredstring')}");
			getObjByName('leaveBill.manHour').focus();
			return false;
		}
		 //验证费用为double类型
		if(getObjByName('leaveBill.manHour').value!=''){
	     	if(!isDoubleNumber("leaveBill.manHour")){
				alert("${action.getText('number.must.be.double')}");
				getObjByName('leaveBill.manHour').value="";
				getObjByName('leaveBill.manHour').focus();
				return false;
			}
	     }
	     
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		if(getObjByName('type.id').value==''){
			alert("${action.getText('type.id.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}
		
		if(getObjByName('leaveBill.betreffzeile').value==''){
			alert("${action.getText('leaveBill.betreffzeile.requiredstring')}");
			getObjByName('leaveBill.betreffzeile').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
