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

<@htmlPage title="${action.getText('overTimeBill.profile')}">
<@ww.form namespace="'/overTimeBill'" name="'overTimeBill'" action="'saveOverTimeBill'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveOverTimeBillToken"/>
    <@inputTable>
    	<#if overTimeBill.id?exists>
    		<@ww.hidden name="'overTimeBill.id'" value="#{overTimeBill.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('overTimeBill.code')}'" name="'overTimeBill.code'" value="'${overTimeBill.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('overTimeBill.crateDate')}'" 
				name="'overTimeBill.createDate'" 
	   			value="'${(overTimeBill.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="10"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("overTimeBill.createDate").value==''){
						getObjByName("overTimeBill.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#if overTimeBill.applyPerson?exists>
				<@ww.textfield label="'${action.getText('overTimeBill.applyPerson')}'" name="'overTimeBill.applyPerson'" value="'${overTimeBill.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('overTimeBill.applyPerson')}'" name="'overTimeBill.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('overTimeBill.dept')}'" 
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
					label="'${action.getText('overTimeBill.startTime')}'" 
					name="'overTimeBill.startTime'" 
		   			value="'${(overTimeBill.startTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("overTimeBill.startTime").value==''){
						getObjByName("overTimeBill.startTime").value = date.format("yyyy-MM-dd hh:mm");
					}
				</script>
				<#--
			<@pp.datePicker 
					label="'${action.getText('overTimeBill.endTime')}'" 
					name="'overTimeBill.endTime'" 
		   			value="'${(overTimeBill.endTime?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10"/>
        </tr>-->      	
			<@pp.datePicker 
					label="'${action.getText('overTimeBill.endTime')}'" 
					name="'overTimeBill.endTime'" 
		   			value="'${(overTimeBill.endTime?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"/>
        	
		</tr>
        <tr>
        	<@ww.textfield label="'${action.getText('overTimeBill.manHour')}'" name="'overTimeBill.manHour'" value="'${overTimeBill.manHour?if_exists}'" cssClass="'underline'" required="false" readonly="true"/>
        	<#if overTimeBill.id?exists>
	        	<@ww.select label="'${action.getText('overTimeBill.status')}'" 
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
				<@ww.select label="'${action.getText('overTimeBill.status')}'" 
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
	       		<label class="label">${action.getText('overTimeBill.betreffzeile')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="overTimeBill.betreffzeile" rows="4" cols="150">${overTimeBill.betreffzeile?if_exists}</textarea>
			</td>
		</tr>
			<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('overTimeBill.failReason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="overTimeBill.failReason" rows="4" cols="150" readonly="true">${overTimeBill.failReason?if_exists}</textarea>
			</td>
		</tr>
    </@inputTable>
    <@buttonBar>
    	<#if !(action.isReadOnly())>
		<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
		</#if>
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/overTimeBill/listOverTimeBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	window.onload=function(){
		<#if overTimeBill.dept?exists>
			getObjByName('dept.id').value='${overTimeBill.dept.id?if_exists}';
		<#else>
			<#if overTimeBill.applyPerson?exists>
				<#if overTimeBill.applyPerson.dept?exists>
					getObjByName('dept.id').value='${overTimeBill.applyPerson.dept.id}';
				</#if>
			</#if>
		</#if>
		<#if overTimeBill.status?exists>
			getObjByName('status.id').value='${overTimeBill.status.id?if_exists}';
		</#if>
	}
	
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		<#-- 咨询时间 20 -->
		if(getObjByName('overTimeBill.createDate').value ==''){
		 		alert("${action.getText('overTimeBill.createDate.not.null')}");
		 		getObjByName('overTimeBill.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('overTimeBill.createDate')){
				alert("${action.getText('overTimeBill.createDate.dateFormate.error')}");
	      	    getObjByName('overTimeBill.createDate').focus();
				return false;
			} 
			if(isDateLessThenCurrent(getObjByName('overTimeBill.createDate').value)){
				alert('${action.getText('overTimeBill.createDate.earlyError')}');
	       		getObjByName('overTimeBill.createDate').focus();
				return false;
			}
		}
		if(getObjByName('overTimeBill.applyPerson').value==''){
			alert("${action.getText('overTimeBill.applyPerson.requiredstring')}");
			getObjByName('overTimeBill.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		}
		
		if(getObjByName('overTimeBill.startTime').value ==''){
		 		alert("${action.getText('overTimeBill.startTime.not.null')}");
		 		getObjByName('overTimeBill.startTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('overTimeBill.startTime')){
				alert("${action.getText('overTimeBill.startTime.dateFormate.error')}");
	      	    getObjByName('overTimeBill.startTime').focus();
				return false;
			} 
		}
		
		if(getObjByName('overTimeBill.endTime').value ==''){
		 		alert("${action.getText('overTimeBill.endTime.not.null')}");
		 		getObjByName('overTimeBill.endTime').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('overTimeBill.endTime')){
				alert("${action.getText('overTimeBill.endTime.dateFormate.error')}");
	      	    getObjByName('overTimeBill.endTime').focus();
				return false;
			} 
			if(getObjByName('overTimeBill.startTime').value>getObjByName('overTimeBill.endTime').value){
				alert('${action.getText('overTimeBill.endTime.earlyError')}');
	       		getObjByName('overTimeBill.endTime').focus();
				return false;
			}
		}
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		
		if(getObjByName('overTimeBill.betreffzeile').value==''){
			alert("${action.getText('overTimeBill.betreffzeile.requiredstring')}");
			getObjByName('overTimeBill.betreffzeile').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
