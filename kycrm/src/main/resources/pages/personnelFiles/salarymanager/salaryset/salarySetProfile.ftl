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

<#-- $Id: dTreeStyle.ftl 2010-02-04 zsz $ -->
<#include "../../../includes/hco2011.ftl" />
<#--
<@htmlPage title="${action.getText('salarySet.profile.title')}">
-->
<@framePage title="">
     <@ww.form action="'saveSalarySet'" namespace="'/salarySet'" method="'post'" >
       <@ww.token name="saveSalarySetToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <#if salarySet.id?exists>
                <@ww.hidden name="'salarySet.id'" value="#{salarySet.id}"/>
         </#if>
         <@inputTable>
         <#if salarySet.id?exists>
	         <@ww.hidden name="'emplyee.id'" value="'${salarySet.emplyee.id?if_exists}'"/>
         <#else>
	         <@ww.hidden name="'emplyee.id'" value="'${personnelFiles.id?if_exists}'"/>
         </#if>
             <tr>
             	<#--员工弹出框-->
	 		<td align="right" valign="top">
	 			<span class="required">*</span>
	       		<label class="label">${action.getText('salarySet.emplyee')}:</label>
	       		 
	     	</td>
	     	<td>
	     		<#if salarySet.emplyee?exists>
		   		<input type="text" name="salarySet.emplyee" class="underline"  value="${salarySet.emplyee.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="salarySet.emplyee" class="underline"  value="${personnelFiles.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				</#if>
			</td>
				<#--
				<a onClick="emplyee_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
               <@textfield label="${action.getText('salarySet.standard')}" name="salarySet.standard" value="${salarySet.standard?if_exists}"  required="true" anothername="name" maxlength="20"/>
				-->

               <@pp.datePicker 
					label="'${action.getText('salarySet.createDate')}'" 
					name="'salarySet.createDate'" 
		   			value="'${(salarySet.createDate?string('yyyy-MM-dd'))?if_exists}'"
					cssClass="'underline'" 
					dateFormat="'%Y-%m-%d'"
					required="false"
					maxlength="10"/>
				<script language="javascript">
					var date = new Date();
					if(getObjByName("salarySet.createDate").value==''){
						getObjByName("salarySet.createDate").value = date.format("yyyy-MM-dd");
					}
				</script>
				<@textfield label="${action.getText('基本工资')}" name="salarySet.baseSalary" value="#{salarySet.baseSalary?if_exists}"  required="true" anothername="name" maxlength="20"/>
            </tr>
            <tr>
				<@textfield label="${action.getText('岗位工资')}" name="salarySet.postSalary" value="#{salarySet.postSalary?if_exists}"  required="true" anothername="name" maxlength="20"/>
				<@textfield label="${action.getText('工龄工资')}" name="salarySet.senioritySalary" value="#{salarySet.senioritySalary?if_exists}"  required="true" anothername="name" maxlength="20"/>
				<@textfield label="${action.getText('通讯交通补贴')}" name="salarySet.trafficSubsidy" value="#{salarySet.trafficSubsidy?if_exists}"  required="true" anothername="name" maxlength="20"/>
            </tr>
            
            <tr>
				<@textfield label="${action.getText('伙食补贴')}" name="salarySet.dinnerSubsidy" value="#{salarySet.dinnerSubsidy?if_exists}"  required="true" anothername="name" maxlength="20"/>
				<@textfield label="${action.getText('节日补贴')}" name="salarySet.holidaySubsidy" value="#{salarySet.holidaySubsidy?if_exists}"  required="true" anothername="name" maxlength="20"/>
				<@textfield label="${action.getText('旅游补贴')}" name="salarySet.travelSubsidy" value="#{salarySet.travelSubsidy?if_exists}"  required="true" anothername="name" maxlength="20"/>
            </tr>
            <#--
			<tr>
		   	   <@select 
					anothername="selectCheckInst"
	        		label="${action.getText('salarySet.status')}" 
					name="status.id" 
					value="${req.getParameter('status.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					required="true"
					emptyOption="true" 
					disabled="false"
					>
				</@select>
			</tr>
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('salarySet.reason')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="salarySet.reason" rows="4" cols="150" >${salarySet.reason?if_exists}</textarea>
			</td>
		</tr>
            -->
		<tr>
			<td align="right" valign="top">
	       		<label class="label">${action.getText('salarySet.remark')}:</label>
	     	</td>
			<td colspan=10">
				<textarea name="salarySet.remark" rows="4" >${salarySet.remark?if_exists}</textarea>
			</td>
			<script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("salarySet.remark").cols =width;
			</script>
		</tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         </#if>
	         <#--
	        <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/salarySet/listSalarySet.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         -->
         </@buttonBar>	
     </@ww.form>

<script>
	//业务员模态窗体弹出框
	function emplyee_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorSalemanHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorSalemanHandler(result) {
		if (null != result) {
			document.forms[0].elements["emplyee.id"].value = result[0];
	   		getObjByName('salarySet.emplyee').value=result[2];
		}
	}
  //验证各属性是否必填、格式
  function validate(){
		if(getObjByName('salarySet.emplyee').value==''){
			alert("${action.getText('salarySet.emplyee.requiredstring')}");
			return false;
		}
		
		if(getObjByName('salarySet.baseSalary').value==''){
			alert("${action.getText('请输入基本工资')}");
			getObjByName('salarySet.baseSalary').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.baseSalary').value)){
			alert("${action.getText('基本工资应该为数字')}");
			getObjByName('salarySet.baseSalary').focus();
			return false;
		}
		if(getObjByName('salarySet.postSalary').value==''){
			alert("${action.getText('请输入岗位工资')}");
			getObjByName('salarySet.postSalary').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.postSalary').value)){
			alert("${action.getText('岗位工资应该为数字')}");
			getObjByName('salarySet.postSalary').focus();
			return false;
		}
		if(getObjByName('salarySet.senioritySalary').value==''){
			alert("${action.getText('请输入工龄工资')}");
			getObjByName('salarySet.senioritySalary').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.senioritySalary').value)){
			alert("${action.getText('工龄工资应该为数字')}");
			getObjByName('salarySet.senioritySalary').focus();
			return false;
		}
		if(getObjByName('salarySet.trafficSubsidy').value==''){
			alert("${action.getText('请输入通讯交通补贴')}");
			getObjByName('salarySet.trafficSubsidy').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.trafficSubsidy').value)){
			alert("${action.getText('通讯交通补贴应该为数字')}");
			getObjByName('salarySet.trafficSubsidy').focus();
			return false;
		}
		if(getObjByName('salarySet.dinnerSubsidy').value==''){
			alert("${action.getText('请输入伙食补贴')}");
			getObjByName('salarySet.dinnerSubsidy').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.dinnerSubsidy').value)){
			alert("${action.getText('伙食补贴应该为数字')}");
			getObjByName('salarySet.dinnerSubsidy').focus();
			return false;
		}
		if(getObjByName('salarySet.holidaySubsidy').value==''){
			alert("${action.getText('请输入节日补贴')}");
			getObjByName('salarySet.holidaySubsidy').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.holidaySubsidy').value)){
			alert("${action.getText('节日补贴应该为数字')}");
			getObjByName('salarySet.holidaySubsidy').focus();
			return false;
		}
		if(getObjByName('salarySet.travelSubsidy').value==''){
			alert("${action.getText('请输入旅游补贴')}");
			getObjByName('salarySet.travelSubsidy').focus();
			return false;
		}
		if(isNaN(getObjByName('salarySet.travelSubsidy').value)){
			alert("${action.getText('旅游补贴应该为数字')}");
			getObjByName('salarySet.travelSubsidy').focus();
			return false;
		}
		<#--
		//类型
		if(getObjByName('salarySet.standard').value==''){
			alert("${action.getText('salarySet.standard.requiredstring')}");
	        getObjByName('salarySet.standard').focus();
			return false;
		}
		//序号
		if(getObjByName('status.id').value==''){
			alert("${action.getText('salarySetFile.status.requiredstring')}");
	        getObjByName('status.id').focus();
			return false;
		}
		-->
    	return true;
  }
</script>
<#--
<script>
	
	window.onload=function(){
        //保存成功或者更新时保留下拉列表的值
		<#if salarySet.status?exists>
			getObjByName('status.id').value=${salarySet.status.id};
		</#if>
	 }
</script>
-->
</@framePage>
