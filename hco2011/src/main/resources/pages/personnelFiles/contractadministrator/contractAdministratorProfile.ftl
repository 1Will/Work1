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
<#include "../../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('ContractAdministrator.profile.title')}">
     <@ww.form action="'saveContractAdministrator'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveContractAdministratorToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         <#if contractAdministrator.id?exists>
                <@ww.hidden name="'contractAdministrator.id'" value="#{contractAdministrator.id}"/>
         </#if>
         <@ww.hidden name="'personnelName.id'" value="'${req.getParameter('personnelName.id')?if_exists}'"/>
         <@ww.hidden name="'principalName.id'" value="'${req.getParameter('principalName.id')?if_exists}'"/>
         <@inputTable>
         <#--
             <tr>
             
				
            </tr>
			<tr>
				
				<@select 
	        		label="${action.getText('contractAdministrator.job')}" 
					name="job.id" 
					value="${req.getParameter('job.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allJobs"
					required="true"
					emptyOption="true" 
					disabled="false">
			    </@select>
				
			</tr>
       		<tr>
       			 <@select 
					anothername="selectCheckCrft"
	        		label="${action.getText('contractAdministrator.crft')}" 
					name="crft.id" 
					value="${req.getParameter('crft.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allCrfts"
					required="true"
					emptyOption="true" 
					disabled="false"
					>
				</@select>
       			<@select 
					anothername="selectCheckLevel"
	        		label="${action.getText('contractAdministrator.level')}" 
					name="level.id" 
					value="${req.getParameter('level.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allLevels"
					required="true"
					emptyOption="true" 
					disabled="false">
				</@select>
			 
             </tr>
             <tr>
             	
               	<@select 
					label="${action.getText('contractAdministrator.vntu')}"
					name="vntu.id"
					value="${req.getParameter('vntu.id')?if_exists}"
					list="allVntu"
					listKey="id"
					listValue="name"
					required="true"
					emptyOption="true"/>
				 <@select 
					label="${action.getText('contractAdministrator.becomes')}"
					anothername="becomes" 
					name="becomes.id"
					listKey="id"
					listValue="name"
					value="${req.getParameter('becomes.id')?if_exists}"
					list="allBecomes"
					emptyOption="true"
					required="true"/>
             </tr>
             <tr>
             	
				<@textfield label="${action.getText('contractAdministrator.trialPeriods')}" name="contractAdministrator.trialPeriods" value="${contractAdministrator.trialPeriods?if_exists}" required="true" anothername="trialPeriods" maxlength="20" />
				<@datePickerRanger
					anothername="effectDate"
	        		label="${action.getText('contractAdministrator.effectDate')}"
		           	name="contractAdministrator.effectDate"
		     		value="${(contractAdministrator.effectDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					required="true"
					flag="true">
				</@datePickerRanger>
             </tr>
             <tr>
             <@textfield label="${action.getText('contractAdministrator.trialWage')}" name="contractAdministrator.trialWage" value="${contractAdministrator.trialWage?if_exists}"  anothername="trialWage" required="true" maxlength="20" />
             <@datePickerRanger
					anothername="trialEndDate"
	        		label="${action.getText('contractAdministrator.trialEndDate')}"
		           	name="contractAdministrator.trialEndDate"
		     		value="${(contractAdministrator.trialEndDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					flag="true">
				</@datePickerRanger>
                <@textfield label="${action.getText('contractAdministrator.contractPeriods')}" name="contractAdministrator.contractPeriods" value="${contractAdministrator.contractPeriods?if_exists}"  anothername="contractPeriods" maxlength="50"/>
             </tr>
             <tr>
             	<@datePickerRanger
					anothername="contractEffectDate"
	        		label="${action.getText('contractAdministrator.contractEffectDate')}"
		           	name="contractAdministrator.contractEffectDate"
		     		value="${(contractAdministrator.contractEffectDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					flag="true">
				</@datePickerRanger>
				<@textfield label="${action.getText('contractAdministrator.becomesWage')}" name="contractAdministrator.becomesWage" value="${contractAdministrator.becomesWage?if_exists}"  anothername="becomesWage" maxlength="50"/>
			
             </tr>
             <tr>
             	<td align="right" valign="top">
	       			<label class="label">${action.getText('contractAdministrator.remark')}:</label>
	     		</td>
				<td colspan=10">
					<textarea name="contractAdministrator.remark" rows="4" cols="150" >${contractAdministrator.remark?if_exists}</textarea>
				</td>
            </tr>-->
            <!--*********************************************************************************-->
            <!--页面重新排版wclin-->
            <tr>
            	<!--工号（员工编号）-->
            	<@textfield label="${action.getText('contractAdministrator.personnelCode')}" name="contractAdministrator.personnelCode" anothername="personnelCode" value="${contractAdministrator.personnelCode?if_exists}" required="false" readonly="true" cssClass="underline" maxlength="20"/>
            	<!--合同类型-->
            	<@select 
					label="${action.getText('contractAdministrator.contractType')}"
					name="contractType.id"
					value="${req.getParameter('contractType.id')?if_exists}"
					list="allContractType"
					listKey="id"
					listValue="name"
					required="true"
					emptyOption="true"/>
            </tr>
            <tr>
            	<!--合同编码-->
            	<@textfield label="${action.getText('contractAdministrator.contractCode')}" name="contractAdministrator.contractCode" value="${contractAdministrator.contractCode?if_exists}" disabled="true" anothername="contractCode" maxlength="20"/>	
            	<!---->
            </tr>
            <tr>
            	<#--用人单位-->
            <@ww.select label="'${action.getText('institution')}'" 
	                   name="'institution.id'" 
	                   value="'${req.getParameter('institution.id')?if_exists}'"
	                   listKey="id" listValue="name"
	                   list="institutions" emptyOption="false" 
	                   required="true"
		               >
	        </@ww.select>
	        <#if contractAdministrator.institustion?exists>
      			<script language="javascript">
      				document.forms[0].elements["institution.id"].value = #{contractAdministrator.institustion.id};
      			</script>
      		</#if>	
            	<#--员工姓名-->
            	<td align="right" valign="top">
	 			<span class="required">*</span>
	       		<label class="label">${action.getText('contractAdministrator.personnelName')}:</label>
	     	</td>
	     	<td>
	     		<#if contractAdministrator.personnelName?exists>
		   		<input type="text" name="contractAdministrator.personnelName" class="underline"  value="${contractAdministrator.personnelName.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="contractAdministrator.personnelName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="personnelName_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
            </tr>
            <tr>
            	<!--部门-->
            	<@select 
					anothername="selectCheckDept"
	        		label="${action.getText('contractAdministrator.dept')}" 
					name="dept.id" 
					value="${req.getParameter('dept.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allDepts"
					required="false"
					emptyOption="true" 
					disabled="false">
				</@select>
            	<!--身份证号-->
            	 <@textfield label="${action.getText('contractAdministrator.idNumber')}" type="IDCARD" name="contractAdministrator.idNumber" value="${contractAdministrator.idNumber?if_exists}"  required="true" anothername="idNumber" maxlength="20"/>	
            </tr>
            <tr>
            	<!--职位-->
            	<@select 
					anothername="selectCheckDuty"
	        		label="${action.getText('contractAdministrator.duty')}" 
					name="duty.id" 
					value="${req.getParameter('duty.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allDutys"
					required="true"
					emptyOption="true" 
					disabled="false">
			   </@select>
            	<#--期望薪资 -->
            	<@textfield label="${action.getText('contractAdministrator.expectationStipend')}" name="contractAdministrator.expectationStipend" value="${contractAdministrator.expectationStipend?if_exists}" disabled="false" anothername="expectationStipend" maxlength="20"/>	
           
            </tr>
            <tr>
            	<#--合同负责人-->
            	<td align="right" valign="top">
	 			<span class="required">*</span>
	       		<label class="label">${action.getText('contractAdministrator.principalName')}:</label>
	     	</td>
	     	<td>
	     		<#if contractAdministrator.principalName?exists>
		   		<input type="text" name="contractAdministrator.principalName" class="underline"  value="${contractAdministrator.principalName.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
				<input type="text" name="contractAdministrator.principalName" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="principalName_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
            	
            	<#--负责人联系方式-->
            	<@textfield label="${action.getText('contractAdministrator.relationWay')}" name="contractAdministrator.relationWay" value="${contractAdministrator.relationWay?if_exists}" disabled="false" anothername="relationWay" maxlength="20"/>	
            
            </tr>
            <tr>
            	<!--合同签订-->
            	<@datePickerRanger
					anothername="signingDate"
	        		label="${action.getText('contractAdministrator.signingDate')}"
		           	name="contractAdministrator.signingDate"
		     		value="${(contractAdministrator.signingDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					required="true"
					flag="true">
				</@datePickerRanger>
            	<!--合同到期-->
            		<@datePickerRanger
					anothername="contractEndDate"
	        		label="${action.getText('contractAdministrator.contractEndDate')}"
		           	name="contractAdministrator.contractEndDate"
		     		value="${(contractAdministrator.contractEndDate?string('yyyy-MM-dd'))?if_exists}"
					cssClass="underline" 
					maxlength="10" 
					flag="true">
				</@datePickerRanger>
            </tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         </#if>
	         <@redirectButton value="${action.getText('back')}" url="listContractAdministrator.html"/>
         </@buttonBar>	
     </@ww.form>

<script>
		window.onload=function(){
		<#if contractAdministrator.id?exists>
			document.all.frame.src='${req.contextPath}/applicationDocManager/listApplicationDoc.html?contractAdministrator.id=#{contractAdministrator.id}&readOnly=${req.getParameter('readOnly')?if_exists}';
		</#if>
        //保存成功或者更新时保留下拉列表的值
		<#if contractAdministrator.dept?exists>
			getObjByName('dept.id').value=${contractAdministrator.dept.id};
		</#if>
		<#if contractAdministrator.duty?exists>
			getObjByName('duty.id').value=${contractAdministrator.duty.id};
		</#if>
		<#--
		<#if contractAdministrator.crft?exists>
			getObjByName('crft.id').value=${contractAdministrator.crft.id};
		</#if>
		-->
		<#--
		<#if contractAdministrator.level?exists>
			getObjByName('level.id').value=${contractAdministrator.level.id};
		</#if>
		-->
		<#if contractAdministrator.contractType?exists>
			getObjByName('contractType.id').value=${contractAdministrator.contractType.id};
		</#if>
		<#--
		<#if contractAdministrator.vntu?exists>
			getObjByName('vntu.id').value=${contractAdministrator.vntu.id};
		</#if>
		-->
		<#--
		<#if contractAdministrator.becomes?exists>
			getObjByName('becomes.id').value=${contractAdministrator.becomes.id};
		</#if>
		-->
		<#--
		<#if contractAdministrator.job?exists>
			getObjByName('job.id').value=${contractAdministrator.job.id};
		</#if>
		-->
	 }
	 
	function principalName_OpenDialog(){
	 var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorprincipalNameHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorprincipalNameHandler(result) {
		if (null != result) {
			document.forms[0].elements["principalName.id"].value = result[0];
	   		getObjByName('contractAdministrator.principalName').value=result[2];
		}
	}
	//员工姓名模态窗体弹出框
	function personnelName_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html";
	   popupModalDialog(url, 800, 600, creatorPersonnelNameHandler);
	   //window.open(url);
	 }
	 //获得模态窗体返回值
	function creatorPersonnelNameHandler(result) {
		if (null != result) {
			document.forms[0].elements["personnelName.id"].value = result[0];
	   		getObjByName('contractAdministrator.personnelName').value=result[2];
	   		getObjByName('contractAdministrator.personnelCode').value=result[1];
	   		getObjByName('contractAdministrator.idNumber').value=result[10];
	   		getObjByName('dept.id').value=result[4];
	   		getObjByName('duty.id').value=result[8];
		}
	}
  //验证各属性是否必填、格式
  function validate(){
        //员工姓名
        if(getObjByName('contractAdministrator.personnelName').value==''){
        	alert("${action.getText('contractAdministrator.personnelName.requiredstring')}");
			return false;
		}
		  //职位
      //  if(getObjByName('job.id').value==''){
      // 	 	alert("${action.getText('job.id.requiredstring')}");
	  //      getObjByName('job.id').focus();
		//	return false;
	//	}
		  //职位
        if(getObjByName('duty.id').value==''){
       	 	alert("${action.getText('duty.id.requiredstring')}");
	        getObjByName('duty.id').focus();
			return false;
		}
		  //工种
        //if(getObjByName('crft.id').value==''){
       	// 	alert("${action.getText('crft.id.requiredstring')}");
	    //    getObjByName('crft.id').focus();
		//	return false;
		//}
		  //等级
       // if(getObjByName('level.id').value==''){
       // 	alert("${action.getText('level.id.requiredstring')}");
	   //     getObjByName('level.id').focus();
		//	return false;
		//}
		//身份证号
		if(!textfieldCheck_idNumber()){
			getObjByName('contractAdministrator.idNumber').focus();
			return false;
		}
		  //合同类型
        if(getObjByName('contractType.id').value==''){
       	 	alert("${action.getText('contractType.id.requiredstring')}");
	        getObjByName('contractType.id').focus();
			return false;
		}
		  //有无期限
        //if(getObjByName('vntu.id').value==''){
       	// 	alert("${action.getText('vntu.id.requiredstring')}");
	    //    getObjByName('vntu.id').focus();
		//	return false;
		//}
		  //是否转正
       // if(getObjByName('becomes.id').value==''){
       //	 	alert("${action.getText('becomes.id.requiredstring')}");
	   //     getObjByName('becomes.id').focus();
		//	return false;
		//}
		//签约时间
		if(getObjByName('contractAdministrator.signingDate').value==''){
			alert("${action.getText('contractAdministrator.signingDate.requiredstring')}");
			getObjByName('contractAdministrator.signingDate').focus();
			return false;
		}
		
		if(getObjByName('contractAdministrator.signingDate').value !=''){
		 	if(!isDate('contractAdministrator.signingDate')){
				alert("${action.getText('contractAdministrator.signingDate.dateFormate.error')}");
	      	    getObjByName('contractAdministrator.signingDate').focus();
				return false;
			}
		}
		  //试用期限
       // if(getObjByName('contractAdministrator.trialPeriods').value==''){
       	// 	alert("${action.getText('contractAdministrator.trialPeriods.requiredstring')}");
	    //    getObjByName('contractAdministrator.trialPeriods').focus();
		//	return false;
		//}
		//试用生效时间
		//签约时间
		//if(getObjByName('contractAdministrator.effectDate').value==''){
		//	alert("${action.getText('contractAdministrator.effectDate.requiredstring')}");
		//	getObjByName('contractAdministrator.effectDate').focus();
		//	return false;
		//}
		//if(getObjByName('contractAdministrator.effectDate').value !=''){
		// 	if(!isDate('contractAdministrator.effectDate')){
		//		alert("${action.getText('contractAdministrator.effectDate.dateFormate.error')}");
	   //   	    getObjByName('contractAdministrator.effectDate').focus();
		//		return false;
		//	}
		//}
		  //试用工资
       // if(getObjByName('contractAdministrator.trialWage').value==''){
       	// 	alert("${action.getText('contractAdministrator.trialWage.requiredstring')}");
	    //    getObjByName('contractAdministrator.trialWage').focus();
		//	return false;
		//}
		 //验证试用工资为double类型
		//if(getObjByName('contractAdministrator.trialWage').value!=''){
	    // 	if(!isDoubleNumber("contractAdministrator.trialWage")){
		//		alert("${action.getText('trialWage.must.be.double')}");
		//		getObjByName('contractAdministrator.trialWage').focus();
		//		return false;
		//	}
	  //   }
	      //验证转正工资为double类型
		//	if(getObjByName('contractAdministrator.becomesWage').value!=''){
		 //    	if(!isDoubleNumber("contractAdministrator.becomesWage")){
		//			alert("${action.getText('becomesWage.must.be.double')}");
		//			getObjByName('contractAdministrator.becomesWage').focus();
		//			return false;
		//		}
		//     }
		//试用到期时间
		//if(getObjByName('contractAdministrator.trialEndDate').value !=''){
		// 	if(!isDate('contractAdministrator.trialEndDate')){
		//		alert("${action.getText('contractAdministrator.trialEndDate.dateFormate.error')}");
	    //  	    getObjByName('contractAdministrator.trialEndDate').focus();
		//		return false;
		//	}
		//}
		//合同生效日期
		//if(getObjByName('contractAdministrator.contractEffectDate').value !=''){
		// 	if(!isDate('contractAdministrator.contractEffectDate')){
		//		alert("${action.getText('contractAdministrator.contractEffectDate.dateFormate.error')}");
	    //  	    getObjByName('contractAdministrator.contractEffectDate').focus();
		//		return false;
		//	}
		//}
			//合同到期时间
		if(getObjByName('contractAdministrator.contractEndDate').value !=''){
		 	if(!isDate('contractAdministrator.contractEndDate')){
				alert("${action.getText('contractAdministrator.contractEndDate.dateFormate.error')}");
	      	    getObjByName('contractAdministrator.contractEndDate').focus();
				return false;
			}
		}
		   //验证单位是否为空
           if(-1==document.forms["contractAdministrator"].elements["institution.id"].value){
                alert("${action.getText('contractAdministrator.institustion.required')}");
                getObjByName('institution.id').focus();
                return false;
            }
    	return true;
  }
</script>
 <#if contractAdministrator.id?exists>
		<ul id="beautytab">
			<li><a id="listContractAdministratorDoc" onclick="activeTab(this);"  class="selectedtab"
				href="${req.contextPath}/applicationDocManager/listApplicationDoc.html?contractAdministrator.id=#{contractAdministrator.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('Attachments')}</a>
			</li>
			<li><a id="editContractAdministrator"  onclick="activeTab(this);"  
				href="${req.contextPath}/personnelFile/editAdditionInfo.html?contractAdministrator.id=#{contractAdministrator.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('additionInfo')}</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	 </#if>
</@htmlPage>