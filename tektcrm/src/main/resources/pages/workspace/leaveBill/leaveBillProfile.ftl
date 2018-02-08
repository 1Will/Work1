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
	<@ww.hidden name="'isSaved'" value=""/>
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
					maxlength="20"
					onchange="'saveDays()'"/>
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
					maxlength="20"
					onchange="'saveDays()'"/>
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
			<#if leaveBill.status?exists && leaveBill.status.code != '02000'>
				<@ww.select label="'${action.getText('流程类型')}'" 
					name="'flow.id'" 
					value="${req.getParameter('flow.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allFlows"
					required="true"
					emptyOption="true"
					disabled="true">
				</@ww.select>
				<#else>
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
			</#if>
			<script type="text/javascript">
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
		<#if leaveBill.flow?exists>
			getObjByName('flow.id').value='${leaveBill.flow.id?if_exists}';
		</#if>
		</script>
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
			<#if leaveBill.isSaved?exists && leaveBill.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			<#-- 继续新建按钮   -->
			<#if leaveBill.id?exists>
				<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/leaveBill/editLeaveBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<#else>
				<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/leaveBill/editLeaveBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
			</#if>
		</#if>
		
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/leaveBill/listLeaveBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </#if>
    
    	
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
//计算请假天数
	function saveDays(){
		var star = getObjByName("leaveBill.startTime").value;
		var end = getObjByName("leaveBill.endTime").value;
		if(star!="" && end!=""){
			if(!isDate_Hour_Min('leaveBill.startTime')){
			    alert("请假开始时间格式不对请修改！");
			    return false;
			}
			if(!isDate_Hour_Min('leaveBill.endTime')){
			    alert("请假结束时间格式不对请修改！");
			    return false;
			}
			if(end<star){
			   alert("请假结束时间必须大于请假开始时间！");
			   getObjByName("leaveBill.endTime").value="";
			}else{
				var c= getDate(end)-getDate(star);
				var hours=(c/3600000).toFixed(1)*1;//请假累计小时数
				var days=0;//请假累计天数
				while (true)
				  {
				    if(hours<=0){
				      break;
				    }else if(hours<4 ){
				      days+=0.5;
				      break;
				    }else if(hours<24){
				      days+=1;
				      break;
				    }else{
				      days+=parseInt(hours/24);
				      hours=hours%24;
				    }
				  }
				getObjByName("leaveBill.manHour").value =days;
			}
		}
	}
	
//初始化页面
<#if activitiFLow?exists>
<#else>
<#if leaveBill.status?exists>
var statusCode = "${leaveBill.status.code?if_exists}";
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
		if(getObjByName('flow.id').value==''){
			alert("请选择流程类型！");
			getObjByName('flow.id').focus();
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
<#if leaveBill.id?exists>
<ul id="beautytab">
	<li>
		<a id="runPoint" class="selectedtab" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${leaveBill.flow.id?if_exists}&bussnessId=#{leaveBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
	<li>
		<a id="CopySendPerson" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listCopySendPerson.html?flow.id=${leaveBill.flow.id?if_exists}&bussnessId=#{leaveBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('抄送人')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?leaveBill.id=#{leaveBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${leaveBill.flow.id?if_exists}&bussnessId=#{leaveBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>