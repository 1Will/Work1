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

<#include "../../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('applyCar.profile')}">
<@ww.form namespace="'/apply'" name="'applyCar'" action="'saveApplyCar'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveApplyCarToken"/>
	<@ww.hidden name="'isSaved'" value=""/>
    <@inputTable>
    	<#if applyCar.id?exists>
    		<@ww.hidden name="'applyCar.id'" value="#{applyCar.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('applyCar.code')}'" name="'applyCar.code'" value="'${applyCar.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('applyCar.crateDate')}'" 
				name="'applyCar.createDate'" 
	   			value="'${(applyCar.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="20"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("applyCar.createDate").value==''){
						getObjByName("applyCar.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#if applyCar.applyPerson?exists>
				<@ww.textfield label="'${action.getText('applyCar.applyPerson')}'" name="'applyCar.applyPerson'" value="'${applyCar.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('applyCar.applyPerson')}'" name="'applyCar.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('applyCar.dept')}'" 
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
					label="'${action.getText('applyCar.startTime')}'" 
					name="'applyCar.time_start'" 
		   			value="'${(applyCar.time_start?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"
					/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("applyCar.time_start").value==''){
						getObjByName("applyCar.time_start").value = date.format("yyyy-MM-dd hh:mm");
					}
				</script>
			<@pp.datePicker 
					label="'${action.getText('applyCar.endTime')}'" 
					name="'applyCar.time_end'" 
		   			value="'${(applyCar.time_end?string('yyyy-MM-dd HH:mm'))?if_exists}'"
					cssClass="'underline'" 
					required="true"
					dateFormat="datetime"
					showsTime="true"
					maxlength="20"
					/>
        </tr>
     
        <tr>
				<@ww.select label="'${action.getText('流程类型')}'" 
					name="'flow.id'" 
					value="${req.getParameter('flow.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allFlows"
					required="true"
					emptyOption="true"
					disabled="false">
				</@ww.select>
			<@ww.select label="'${action.getText('applyCar.type')}'" 
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
		   <@ww.select label="'${action.getText('车辆类型')}'" 
				name="'carType.id'" 
				value="${req.getParameter('carType.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allCarType"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		   <@ww.textfield label="'${action.getText('用车数量')}'" name="'applyCar.carCount'" value="'${applyCar.carCount?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		<tr>
		  <@ww.textfield label="'${action.getText('applyCar.waitPlace')}'" name="'applyCar.waitPlace'" value="'${applyCar.waitPlace?if_exists}'" cssClass="'underline'" required="true" />
		  <@ww.textfield label="'${action.getText('applyCar.destination')}'" name="'applyCar.destination'" value="'${applyCar.destination?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		<tr>
		  <@ww.textfield label="'${action.getText('applyCar.contacter')}'" name="'applyCar.contacter'" value="'${applyCar.contacter?if_exists}'" cssClass="'underline'" required="true" />
		  <@ww.textfield label="'${action.getText('applyCar.phone')}'" name="'applyCar.phone'" value="'${applyCar.phone?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		  <tr>
		  <@ww.textfield label="'${action.getText('applyCar.numOfPeople')}'" name="'applyCar.numOfPeople'" value="'${applyCar.numOfPeople?if_exists}'" cssClass="'underline'" required="true" />
			<@ww.select label="'${action.getText('applyCar.status')}'" 
				name="'status.id'" 
				value="'${req.getParameter('status.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				disabled="true">
			</@ww.select>
        </tr>
		
        </tr>
		<tr>
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('applyCar.betreffzeile')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="applyCar.reason" rows="4" cols="150" >${applyCar.reason?if_exists}</textarea>
			</td>
		</tr>
	 <script type="text/javascript">
		  <#if applyCar.type?exists>
			getObjByName('type.id').value='${applyCar.type.id?if_exists}';
		  </#if>
			<#if applyCar.flow?exists>
				getObjByName('flow.id').value='${applyCar.flow.id?if_exists}';
			</#if>
		   <#if applyCar.status?exists>
			getObjByName('status.id').value='${applyCar.status.id?if_exists}';
		   </#if>
		   <#if applyCar.carType?exists>
			getObjByName('carType.id').value='${applyCar.carType.id?if_exists}';
		  </#if>
		   <#if applyCar.applyPerson?exists>
				<#if applyCar.applyPerson.dept?exists>
					getObjByName('dept.id').value='${applyCar.applyPerson.dept.id}';
				</#if>
			</#if>
		</script>
		<#--
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('leaveBill.failReason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="leaveBill.failReason" rows="4" cols="150" readonly="true">${leaveBill.failReason?if_exists}</textarea>
			</td>
		</tr>
		-->
    </@inputTable>
    <@buttonBar>
    <#if activitiFLow?exists>
    <input type="button" name="close" value="关闭" onclick="window.close()">
    <#else>
    <#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'">
			</@vsubmit>
			<#if applyCar.isSaved?exists && applyCar.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			<#-- 继续新建按钮   -->
			<#if applyCar.id?exists>
				<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/apply/editApplyCar.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<#else>
				<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/apply/editApplyCar.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
			</#if>
		</#if>
		
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/apply/listApplyCar.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </#if>
    
    	
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	
//初始化页面
<#if activitiFLow?exists>
<#else>
<#if applyCar.status?exists>
var statusCode = "${applyCar.status.code?if_exists}";
if(statusCode != "02000"){
	if(statusCode == "02002"){
		getObjByName("save").style.display = "none";
		getObjByName("submit").value = "重新提交";
		getObjByName("submit").disabled = false;
	}else{
		getObjByName("save").disabled = "true";
		getObjByName("submit").disabled = "true";
	}
}
</#if>
</#if>

	function getDate(dt){
		var tem = dt.split(" ");
		var date = tem[0].split("-");
		var mon = parseInt(date[1])-1;
		
		var time =tem[1].split(":");
		var newDate=new Date(date[0],mon,date[2],time[0],time[1]);
		return newDate;
	}
	
	function submitt(){
		if(getObjByName("submit").value == "重新提交"){
			getObjByName('isSaved').value="2";
		}else{
			getObjByName('isSaved').value="1";
		}
		return storeValidation();
    }
    function savee(){
		getObjByName('isSaved').value="0";
     	return storeValidation();
    }
	
	//保存前给隐藏域赋值和验证字段
	function storeValidation(){
		if(getObjByName('applyCar.createDate').value ==''){
		 		alert("${action.getText('applyCar.createDate.not.null')}");
		 		getObjByName('applyCar.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('applyCar.createDate')){
				alert("${action.getText('applyCar.createDate.dateFormate.error')}");
	      	    getObjByName('applyCar.createDate').focus();
				return false;
			} 
		}
		if(getObjByName('applyCar.applyPerson').value==''){
			alert("${action.getText('applyCar.applyPerson.requiredstring')}");
			getObjByName('applyCar.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		}
		
		if(getObjByName('applyCar.time_start').value ==''){
	 		alert("${action.getText('applyCar.startTime.not.null')}");
	 		getObjByName('applyCar.time_start').focus();
	      	return false;   
		}else{
		 	if(!isDate_Hour_Min('applyCar.time_start')){
				alert("${action.getText('applyCar.startTime.dateFormate.error')}");
	      	    getObjByName('applyCar.time_start').focus();
				return false;
			} 
		}
		
		if(getObjByName('applyCar.time_end').value ==''){
		 		alert("${action.getText('applyCar.endTime.not.null')}");
		 		getObjByName('applyCar.time_end').focus();
		      	return false;   
		}else{
		 	if(!isDate_Hour_Min('applyCar.time_end')){
				alert("${action.getText('applyCar.endTime.dateFormate.error')}");
	      	    getObjByName('applyCar.time_end').focus();
				return false;
			} 
			if(getObjByName('applyCar.time_start').value>getObjByName('applyCar.time_end').value){
				alert('${action.getText('applyCar.endTime.earlyError')}');
	       		getObjByName('applyCar.time_end').focus();
				return false;
			}
		}
	     if(getObjByName('flow.id').value==''){
			alert("请选择流程类型");
			getObjByName('flow.id').focus();
			return false;
		}
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		 if(getObjByName('carType.id').value==''){
			alert("请选择车辆类型");
			getObjByName('carType.id').focus();
			return false;
		}
		if(getObjByName('applyCar.carCount').value==''){
			alert("请输入用车数量");
			getObjByName('applyCar.carCount').focus();
			return false;
		}else{
		   if(!(/(^[1-9]\d*$)/.test(getObjByName('applyCar.carCount').value))){
		    alert("请输入正确的用车数量！");
		    getObjByName('applyCar.carCount').focus();
			return false;
		   }
		}
		if(getObjByName('type.id').value==''){
			alert("${action.getText('type.id.requiredstring')}");
			getObjByName('type.id').focus();
			return false;
		}     
		if(getObjByName('applyCar.waitPlace').value==''){
			alert("${action.getText('applyCar.waitPlace.requiredstring')}");
			getObjByName('applyCar.waitPlace').focus();
			return false;
		}
		if(getObjByName('applyCar.destination').value==''){
			alert("${action.getText('applyCar.destination.requiredstring')}");
			getObjByName('applyCar.destination').focus();
			return false;
		}
		if(getObjByName('applyCar.contacter').value==''){
			alert("${action.getText('applyCar.contacter.requiredstring')}");
			getObjByName('applyCar.contacter').focus();
			return false;
		}
		if(getObjByName('applyCar.phone').value==''){
			alert("${action.getText('applyCar.phone.requiredstring')}");
			getObjByName('applyCar.phone').focus();
			return false;
		}
		if(getObjByName('applyCar.numOfPeople').value==''){
			alert("${action.getText('applyCar.numOfPeople.requiredstring')}");
			getObjByName('applyCar.numOfPeople').focus();
			return false;
		}else{
		   if(!(/(^[1-9]\d*$)/.test(getObjByName('applyCar.numOfPeople').value))){
		    alert("请输入正确的乘车人数！");
		    getObjByName('applyCar.numOfPeople').focus();
			return false;
		   }
		}
		
		if(getObjByName('applyCar.reason').value==''){
			alert("${action.getText('applyCar.betreffzeile.requiredstring')}");
			getObjByName('applyCar.reason').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
<#if applyCar.id?exists>
<ul id="beautytab">
	<li>
		<a id="runPoint" class="selectedtab" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${applyCar.flow.id?if_exists}&bussnessId=#{applyCar.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
	<li> 
		<a id="CopySendPerson" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listCopySendPerson.html?flow.id=${applyCar.flow.id?if_exists}&bussnessId=#{applyCar.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('抄送人')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?applyCar.id=#{applyCar.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${applyCar.flow.id?if_exists}&bussnessId=#{applyCar.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>