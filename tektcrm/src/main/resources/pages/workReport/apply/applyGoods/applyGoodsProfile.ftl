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

<@htmlPage title="${action.getText('applyGoods.profile')}">
<@ww.form namespace="'/apply'" name="'applyGoods'" action="'saveApplyGoods'" method="'post'">
<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.token name="saveApplyGoodsToken"/>
	<@ww.hidden name="'isSaved'" value=""/>
    <@inputTable>
    	<#if applyGoods.id?exists>
    		<@ww.hidden name="'applyGoods.id'" value="#{applyGoods.id?if_exists}"/>
	 	</#if>
	 	<tr>
            <@ww.textfield label="'${action.getText('applyGoods.code')}'" name="'applyGoods.code'" value="'${applyGoods.code?if_exists}'" cssClass="'underline'" required="false" disabled="true"/>
			<@pp.datePicker 
				label="'${action.getText('applyGoods.crateDate')}'" 
				name="'applyGoods.createDate'" 
	   			value="'${(applyGoods.createDate?string('yyyy-MM-dd'))?if_exists}'"
				cssClass="'underline'" 
				required="true"
				dateFormat="'%Y-%m-%d'"
				maxlength="20"/>
			<script language="javascript">
					var date = new Date();
					if(getObjByName("applyGoods.createDate").value==''){
						getObjByName("applyGoods.createDate").value = date.format("yyyy-MM-dd");
					}
			</script>
		</tr>
		<tr>
			<#if applyGoods.applyPerson?exists>
				<@ww.textfield label="'${action.getText('applyGoods.applyPerson')}'" name="'applyGoods.applyPerson'" value="'${applyGoods.applyPerson.name?if_exists}'" cssClass="'underline'" required="true" readonly="true"/>
			<#else>
				<@ww.textfield label="'${action.getText('applyGoods.applyPerson')}'" name="'applyGoods.applyPerson'" value="''" cssClass="'underline'" required="true" readonly="true"/>
			</#if>
			<@ww.select label="'${action.getText('applyGoods.dept')}'" 
				name="'dept.id'" 
				value="${req.getParameter('applyGoods.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				emptyOption="true" 
				disabled="true">
			</@ww.select>
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
			<@ww.select label="'${action.getText('applyGoods.purpose')}'" 
				name="'purpose.id'" 
				value="${req.getParameter('purpose.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allPurposes"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
		<tr>
		<@ww.select label="'${action.getText('applyGoods.goodsName')}'" 
				name="'goodsName.id'" 
				value="'${req.getParameter('goodsName.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allGoodsName"
				required="true"
				>
			</@ww.select>
		  <@ww.textfield label="'${action.getText('applyGoods.goodsCount')}'" name="'applyGoods.goodsCount'" value="'${applyGoods.goodsCount?if_exists}'" cssClass="'underline'" required="true" />
		</tr>
		  <tr>
		  <@ww.select label="'${action.getText('applyGoods.unit')}'" 
				name="'unit.id'" 
				value="'${req.getParameter('unit.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allUnitList"
				required="true"
				>
			</@ww.select>
			<@ww.select label="'${action.getText('applyGoods.status')}'" 
				name="'status.id'" 
				value="'${req.getParameter('status.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allStatus"
				required="true"
				disabled="true">
			</@ww.select>
        </tr>
        <tr>
		 <@ww.textfield label="'${action.getText('applyGoods.bussinessUnit')}'" name="'applyGoods.bussinessUnit'" value="'${applyGoods.bussinessUnit?if_exists}'" cssClass="'underline'" required="true" />
	    </tr> 
	    <tr>
	       <td align="right" valign="top">
	       		<span class="required"></span>
	       		<label class="label">${action.getText('备注')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="applyGoods.remark" rows="4" cols="150" >${applyGoods.remark?if_exists}</textarea>
			</td>
	    </tr>
	 <script type="text/javascript">
		  <#if applyGoods.purpose?exists>
			getObjByName('purpose.id').value='${applyGoods.purpose.id?if_exists}';
		  </#if>
			<#if applyGoods.flow?exists>
				getObjByName('flow.id').value='${applyGoods.flow.id?if_exists}';
			</#if>
			<#if applyGoods.status?exists>
			getObjByName('status.id').value='${applyGoods.status.id?if_exists}';
		   </#if>
		<#if applyGoods.applyPerson?exists>
				<#if applyGoods.applyPerson.dept?exists>
					getObjByName('dept.id').value='${applyGoods.applyPerson.dept.id}';
				</#if>
			</#if>
		<#if applyGoods.unit?exists>
			getObjByName('unit.id').value='${applyGoods.unit.id?if_exists}';
		   </#if>	
		   <#if applyGoods.goodsName?exists>
			getObjByName('goodsName.id').value='${applyGoods.goodsName.id?if_exists}';
		   </#if>
		</script>
    </@inputTable>
    <@buttonBar>
    <#if activitiFLow?exists>
    <input type="button" name="close" value="关闭" onclick="window.close()">
    <#else>
    <#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'">
			</@vsubmit>
			<#if applyGoods.isSaved?exists && applyGoods.isSaved=='0' >
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
		    <#else>
		    	<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
		    </#if>
			<#-- 继续新建按钮   -->
			<#if applyGoods.id?exists>
				<@redirectButton value="${action.getText('newNext')}" url="${req.contextPath}/apply/editApplyGoods.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			<#else>
				<@redirectButton name="newNext" value="${action.getText('newNext')}" url="${req.contextPath}/apply/editApplyGoods.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
					<script language="JavaScript" type="text/JavaScript"> 
					getObjByName("newNext").disabled="true";
					</script>
			</#if>
		</#if>
		
		
		<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/apply/listApplyGoods.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
    </#if>
    
    	
    </@buttonBar>

</@ww.form>

<script type="text/javascript">
	
//初始化页面
<#if activitiFLow?exists>
<#else>
<#if applyGoods.status?exists>
var statusCode = "${applyGoods.status.code?if_exists}";
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
		if(getObjByName('applyGoods.createDate').value ==''){
		 		alert("${action.getText('applyGoods.createDate.not.null')}");
		 		getObjByName('applyGoods.createDate').focus();
		      	return false;   
		}else{
		 	if(!isDate('applyGoods.createDate')){
				alert("${action.getText('applyGoods.dateFormate.error')}");
	      	    getObjByName('applyGoods.createDate').focus();
				return false;
			} 
		}
		if(getObjByName('applyGoods.applyPerson').value==''){
			alert("${action.getText('applyGoods.applyPerson.requiredstring')}");
			getObjByName('applyGoods.applyPerson').focus();
			return false;
		}
		if(getObjByName('dept.id').value==''){
			alert("${action.getText('dept.id.requiredstring')}");
			getObjByName('dept.id').focus();
			return false;
		} 
		if(getObjByName('flow.id').value==''){
			alert("请选择流程类型！");
			getObjByName('flow.id').focus();
			return false;
		}
		if(getObjByName('purpose.id').value==''){
			alert("${action.getText('purpose.id.requiredstring')}");
			getObjByName('purpose.id').focus();
			return false;
		}
		if(getObjByName('goodsName.id').value==''){
			alert("${action.getText('applyGoods.goodsName.requiredstring')}");
			getObjByName('applyGoods.applyPerson').focus();
			return false;
		}
		if(getObjByName('applyGoods.goodsCount').value==''){
			alert("${action.getText('applyGoods.goodsCount.requiredstring')}");
			getObjByName('applyGoods.goodsCount').focus();
			return false;
		}else {
		if(isNaN(getObjByName('applyGoods.goodsCount').value)){
		  alert("${action.getText('number.must.be.double')}");
			getObjByName('applyGoods.goodsCount').focus();
			return false;
		}
		}
		
	     if(getObjByName('unit.id').value==''){
			alert("${action.getText('unit.id.requiredstring')}");
			getObjByName('unit.id').focus();
			return false;
		}
		if(getObjByName('status.id').value==''){
			alert("${action.getText('status.id.requiredstring')}");
			getObjByName('status.id').focus();
			return false;
		}
		if(getObjByName('applyGoods.bussinessUnit').value==''){
			alert("${action.getText('applyGoods.bussinessUnit.requiredstring')}");
			getObjByName('applyGoods.bussinessUnit').focus();
			return false;
		}
		return true;
	}
</script>
</@htmlPage>
<#if applyGoods.id?exists>
<ul id="beautytab">
	<li>
		<a id="runPoint" class="selectedtab" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${applyGoods.flow.id?if_exists}&bussnessId=#{applyGoods.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('审批人')}</a>
	</li>
	<li>
		<a id="CopySendPerson" onclick="activeTab(this);"  href='${req.contextPath}/activitiFlow/listCopySendPerson.html?flow.id=${applyGoods.flow.id?if_exists}&bussnessId=#{applyGoods.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('抄送人')}</a>
	</li>
	<li>
		<a id="additionalInformation" onclick="activeTab(this);"  href='${req.contextPath}/applicationDocManager/listApplicationDoc.html?applyGoods.id=#{applyGoods.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('附件资料')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/activitiFlow/listRunPoint.html?flow.id=${applyGoods.flow.id?if_exists}&bussnessId=#{applyGoods.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="60%"/>
</#if>