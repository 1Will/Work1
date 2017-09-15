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

<@htmlPage title="${action.getText('personnel.profile.title')}">
     <@ww.form action="'savePersonnelFile'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="savePersonnelToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       <@ww.hidden name="'notOpen'" value="'${notOpen?if_exists}'"/>
         <#if personnelFile.id?exists>
                <@ww.hidden name="'personnelFile.id'" value="#{personnelFile.id?if_exists}"/>
         </#if>
         <#if personnelFile.superiorLeader?exists>
         	<@ww.hidden name="'personnelFile.superiorLeader'" value="'#{personnelFile.superiorLeader.id}'"/>
         <#else>
         	<@ww.hidden name="'personnelFile.superiorLeader'" value=""/>
         </#if>
         <@ww.hidden name="'sex'" value="'${req.getParameter('sex')?if_exists}'"/>
         <@inputTable>
		 	<@ww.hidden name="'personnel.isSys'" value="'${req.getParameter('personnel.isSys')?if_exists}'" />
            <tr>
            	<!--姓名-->
            	<@textfield label="${action.getText('personnel.name')}" name="personnelFile.name" value="${personnelFile.name?if_exists}"  required="true" anothername="name" maxlength="20"/>
				<!--性别-->
			<td align="right" valign="top">
	       		<span class="required">*</span>
	       		<label class="label">${action.getText('personnel.sex')}:</label>
	     	</td>
	        <td align="left">
	        	<input type="radio" id="sex0" name="personnelFile.sex" value="false" />男
	        	<input type="radio" id="sex1" name="personnelFile.sex" value="true" />女
			</td>
            </tr>
            <tr>
            	<!--出生日期-->
            	<@datePickerRanger
						anothername="birthday"
		        		label="${action.getText('personnel.birthday')}"
			           	name="personnelFile.birthday"
			     		value="${(personnelFile.birthday?string('yyyy-MM-dd'))?if_exists}" 
						cssClass="underline" 
						maxlength="10" 
						required="true"
						flag="true">
				</@datePickerRanger>
            	<!--身份证号-->
            	<@textfield label="${action.getText('personnel.idNumber')}" type="IDCARD" name="personnelFile.idNumber" value="${personnelFile.idNumber?if_exists}"  anothername="idNumber" maxlength="20"/>	
            	
            </tr>
            <tr>
            	<!--籍贯-->
            	<@select 
					label="${action.getText('personnel.birthplace')}"
					anothername="birthplace" 
					name="personnelFile.birthplace"
					listKey="id"
					listValue="name"
					value="${personnelFile.birthplace?if_exists}"
					list="allBirthplace"
					emptyOption="true"
					required="true"/>
            	
            	<!--民族-->
            	 
				 <@select 
					label="${action.getText('personnel.national')}"
					name="personnelFile.national"
					listKey="id"
					listValue="name"
					value="${personnelFile.national?if_exists}"
					list="allNational"
					emptyOption="true"/>
            	
            </tr>
            <tr>
            	<!--婚姻状况-->
            			<@select 
					label="${action.getText('personnel.marriage')}"
					name="personnelFile.marriage"
					value="${personnelFile.marriage?if_exists}"
					list="allMarriage"
					listKey="id"
					listValue="name"
					emptyOption="true"/>
            	
            	<!--政治面貌-->
            	<@select 
					label="${action.getText('personnel.politice')}"
					anothername="politice" 
					name="personnelFile.politice"
					value="${personnelFile.politice?if_exists}"
					listKey="id"
					listValue="name"
					list="allPolitice"
					emptyOption="true"
					required="true" 
					/>
        	</tr>
        	<tr>
        		<!--学历-->
        		<@select 
					label="${action.getText('personnel.education')}"
					 name="personnelFile.education"
					 value="${personnelFile.education?if_exists}"
					 listKey="id"
					 listValue="name"
					 list="allEducation"
					 emptyOption="true"/>
        		
        		<!--手机号码-->
        		<@textfield label="${action.getText('personnel.mobile')}" name="personnelFile.mobile" value="${personnelFile.mobile?if_exists}"  required="true" anothername="mobile" maxlength="20"/>
        		
        	</tr>
        	<tr>
        		<!--家庭电话-->
        		<@textfield label="${action.getText('personnel.homeTel')}" name="personnelFile.homeTel" value="${personnelFile.homeTel?if_exists}"  anothername="homeTel" maxlength="20" />
        		<!--Email-->
        		<@textfield label="Email" type="E" name="personnelFile.email" value="${personnelFile.email?if_exists}"  anothername="email" maxlength="50"/>
        	</tr>
        	<tr><td colspan="8">
        	<hr/></td>
        	</tr>
        	<tr>
        		<!--工号-->
        		<@textfield label="${action.getText('personnel.code')}" name="personnelFile.code" anothername="code" value="${personnelFile.code?if_exists}" required="false" readonly="true" cssClass="underline" maxlength="20"/>
        		<!--档案编码-->
        		<@textfield label="${action.getText('personnel.fileNo')}"  name="personnelFile.fileNo" value="${personnelFile.fileNo?if_exists}"  required="true"  anothername="fileNo" maxlength="20"/>
        	</tr>
        	<tr>
        		<!--单位-->
        			<@select 
				anothername="selectCheckInst"
        		label="${action.getText('personnel.inst')}" 
				name="inst.id" 
				value="${req.getParameter('inst.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allInsts"
				required="true"
				emptyOption="false" 
				disabled="false"
				onchange="InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('')}\",\"false\")">
				<#--onchange="AgencyInstCascadeDWR(\"inst.id\",\"dept.id\",\"duty.id\",#{user.id?if_exists},#{user.organization.id?if_exists},\"${action.getText('')}\",\"edit\")"-->
			</@select>
        		
        		<!--部门-->
        		<@select 
				anothername="selectCheckDept"
        		label="${action.getText('personnel.dept')}" 
				name="dept.id" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				required="true"
				emptyOption="false" 
				disabled="false"
				onchange="DutyCascadeDWR(\"dept.id\",\"duty.id\",#{user.organization.id?if_exists},\"${action.getText('')}\",\"edit\")">
			</@select>
        	</tr>
        	<tr>
        		<!--职位-->
        		<@select 
					anothername="selectCheckDuty"
	        		label="${action.getText('personnel.duty')}" 
					name="duty.id" 
					value="${req.getParameter('duty.id')?if_exists}"
					listKey="id"
					listValue="name"
					list="allDutys"
					required="true"
					emptyOption="true" 
					disabled="false">
				</@select>
        		<!--上级领导 -->
        	<td align="right" valign="top">
	       		<label class="label">${action.getText('personnel.superiorLeader')}:</label>
	     	</td>
	     	<td>
		     	<#if personnelFile.superiorLeader?exists>
		     	<input type="text" name="superiorLeader.name" class="underline"  value="${personnelFile.superiorLeader.name?if_exists}" maxlength="140" size="20" disabled="true"/>
		     	<#else>
		     	<input type="text" name="superiorLeader.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
		     	</#if>
				<a onClick="personnelFile_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td>
        	</tr>
        	<tr>
        		<!--办公电话-->
        		   <@textfield label="${action.getText('personnel.telphone')}" name="personnelFile.telphone" value="${personnelFile.telphone?if_exists}"  anothername="telphone" maxlength="20" />
        		<!--入职日期-->
        		<@datePickerRanger
					anothername="entryDate"
	        		label="${action.getText('personnel.entryDate')}"
		           	name="personnelFile.entryDate"
		     		value="${(personnelFile.entryDate?string('yyyy-MM-dd'))?if_exists}" 
					cssClass="underline" 
					maxlength="10" 
					required="true"
					flag="true">
				</@datePickerRanger>
        		
        	</tr>
        	<tr>
        				
        		<!--转正日期-->
        		<@datePickerRanger
					anothername="regularizedDate"
	        		label="${action.getText('personnel.regularizedDate')}"
		           	name="personnelFile.regularizedDate"
		     		value="${(personnelFile.regularizedDate?string('yyyy-MM-dd'))?if_exists}"
					cssClass="underline" 
					maxlength="10" 
					flag="true">
				</@datePickerRanger>
				
        		<!--离职日期-->
        		<@datePickerRanger
					anothername="separationDate"
	        		label="${action.getText('personnel.separationDate')}"
		           	name="personnelFile.separationDate"
		     		value="${(personnelFile.separationDate?string('yyyy-MM-dd'))?if_exists}"
					cssClass="underline" 
					maxlength="10" 
					flag="true">
				</@datePickerRanger>
        	</tr>
        	<tr>	
        		<!--工作地点-->
        		<@textfield label="${action.getText('personnel.address')}" name="personnelFile.address"  value="${personnelFile.address?if_exists}" anothername="address" maxlength="100"  />
        	
        	<!---->
        	<@ww.select label="'${action.getText('员工属性')}'" 
				name="'businessType.id'" 
				value="'${req.getParameter('businessType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allBusinessType"
				required="true"
				emptyOption="true" 
				disabled="false">
			</@ww.select>
        	</tr>
        	<tr>
        		<!--工作方式-->
        		<@select 
					label="${action.getText('personnel.workway')}"
					name="personnelFile.workway"
					value="${personnelFile.workway?if_exists}"
					listKey="id"
					listValue="name"
					list="allWorkway"
					emptyOption="true"/>
        		
        		<!--状态-->
        		<@select 
					label="${action.getText('personnel.status')}"
					anothername="status" 
					name="personnelFile.status"
					id="personnelFile.status"
					value="${personnelFile.status?if_exists}"
					listKey="id"
					listValue="name"
					list="allStatus"
					emptyOption="true"
					required="true"/>
        	</tr>
            
            <!--      ______________________________________________________________________________________________________________   -->
             
             
             	<#--
                 <#if personnelFile.id?exists>
                     <@textfield label="${action.getText('personnel.code')}" name="personnelFile.code" value="${personnelFile.code?if_exists}" required="false" anothername="code" maxlength="20" disabled="true"/>
                 <#else>
                     <@textfield label="${action.getText('personnel.code')}" name="personnelFile.code" value="${personnelFile.code?if_exists}" required="false" anothername="code" maxlength="20"/>
                 </#if>
                 -->
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	         </#if>
	         <@redirectButton value="${action.getText('back')}" url="listPerson.html?&notOpen=notOpen"/>
         </@buttonBar>	
     </@ww.form>

<script>
   //保存失败时保存下拉列表的值
   getObjByName('personnelFile.marriage').value='${req.getParameter('personnelFile.marriage')?if_exists}';
   getObjByName('personnelFile.birthplace').value='${req.getParameter('personnelFile.birthplace')?if_exists}';
   getObjByName('personnelFile.national').value='${req.getParameter('personnelFile.national')?if_exists}';
   getObjByName('personnelFile.politice').value='${req.getParameter('personnelFile.politice')?if_exists}';
   getObjByName('personnelFile.education').value='${req.getParameter('personnelFile.education')?if_exists}';
   getObjByName('personnelFile.workway').value='${req.getParameter('personnelFile.workway')?if_exists}';
   getObjByName('personnelFile.status').value='${req.getParameter('personnelFile.status')?if_exists}';
</script>

<script>
  //验证各属性是否必填、格式
  function validate(){
  //姓名
		if(!textfieldCheck_name()){
			getObjByName('personnelFile.name').focus();
			return false;
		}
		//出生日期
		if(!dateCheck_birthday()){
			getObjByName('personnelFile.birthday').focus();
			return false;
		}
		//身份证号
		//if(!textfieldCheck_idNumber()){
			//getObjByName('personnelFile.idNumber').focus();
			//return false;
		//}
		//籍贯
		if(!selectCheck_birthplace()){
		    return false;
		}
		//政治面貌
		if(!selectCheck_politice()){
		    return false;
		}
		
		//手机
		if(!textfieldCheck_mobile()){
			getObjByName('personnelFile.mobile').focus();
			return false;
		}
		//Email
		if(!textfieldCheck_email()){
			getObjByName('personnelFile.email').focus();
			return false;
		} 
  		if(getObjByName('personnelFile.sex').value=="${action.getText('personnel.sex.man')}"){
			getObjByName('sex').value=false;
		}else if(getObjByName('personnelFile.sex').value=="${action.getText('personnel.sex.women')}"){
			getObjByName('sex').value=true;
		}
        //工号
        if(!textfieldCheck_code()){
	        getObjByName('personnelFile.code').focus();
			return false;
		}
		
		//档案编码
		if(!textfieldCheck_fileNo()){
	        getObjByName('personnelFile.fileNo').focus();
			return false;
		}
		//单位
		if(!selectCheck_selectCheckInst()){
		getObjByName('inst.id').focus();
		    return false;
		}
		//部门
		if(!selectCheck_selectCheckDept()){
		getObjByName('dept.id').focus();
		    return false;
		}
		//职位
		if(!selectCheck_selectCheckDuty()){
			getObjByName('duty.id').focus();
		    return false;
		}
		
		//入职日期
		if(!dateCheck_entryDate()){
			getObjByName('personnelFile.entryDate').focus();
			return false;
		}
		 //验证出生时间是否大于入职时间
	    var birth = getObjByName('personnelFile.birthday').value;
	    var entry = getObjByName('personnelFile.entryDate').value;
	    if(isDateLessThenOldDate(birth,entry)){
			alert('${action.getText('personnelFile.time.birth.error')}');
			getObjByName('personnelFile.birthday').focus();
			return false;
		}
		//转正日期
		if(!dateCheck_regularizedDate()){
			getObjByName('personnelFile.regularizedDate').focus();
			return false;
		}
	    var regul = getObjByName('personnelFile.regularizedDate').value;
	    if(regul != ""){
	    	//验证出生时间是否大于入职时间
	    	if(isDateLessThenOldDate(birth,regul)){
				alert('${action.getText('personnelFile.time.birthregul.error')}');
				getObjByName('personnelFile.birthday').focus();
				return false;
			}
			 //验证入职时间是否大于等于转正时间
		    if(isDateLessEqualThenOldDate(entry,regul)){
				alert('${action.getText('personnelFile.time.entry.error')}');
				getObjByName('personnelFile.entryDate').focus();
				return false;
			}
		}
		//离职日期
		if(!dateCheck_separationDate()){
			getObjByName('personnelFile.separationDate').focus();
			return false;
		}
	    var separa = getObjByName('personnelFile.separationDate').value;
	    if(separa != ""){
	    	//验证出生时间是否大于离职时间
	    	if(isDateLessThenOldDate(birth,separa)){
				alert('${action.getText('personnelFile.time.birthsepara.error')}');
				getObjByName('personnelFile.birthday').focus();
				return false;
			}
			//验证入职时间是否大于等于离职时间
			if(isDateLessEqualThenOldDate(entry,separa)){
				alert('${action.getText('personnelFile.time.speal.error')}');
				getObjByName('personnelFile.entryDate').focus();
				return false;
			}
			//验证转正时间是否大于等于离职时间
		    if(isDateLessEqualThenOldDate(regul,separa)){
				alert('${action.getText('personnelFile.time.regual.error')}');
				getObjByName('personnelFile.regularizedDate').focus();
				return false;
			}
		}
		//状态
		if(!selectCheck_status()){
		    return false;
		}
    	return true;
  }
  
  //弹出人事档案查询模态窗体
	function personnelFile_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPerson.html?popWindowFlag=popWindowFlag&readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorSelectorHandlerPersonnel);
	 }
	 //获得模态窗体返回值
	function creatorSelectorHandlerPersonnel(result) {
		if (null != result) {
		 	getObjByName("personnelFile.superiorLeader").value = result[0];	
		 	getObjByName("superiorLeader.name").value = result[1];
		}
	}
  
</script>

<script>
	
//	window.onload=function(){
		<#if personnelFile.sex?exists>
			<#if personnelFile.sex>
				getObjByName('sex1').checked=true;
			<#else>
				getObjByName('sex0').checked=true;
			</#if>
		<#else>
		    getObjByName('sex0').checked=true;
		</#if>
 		//单位
		<#if personnelFile.inst?exists>
			getObjByName('inst.id').value='${personnelFile.inst.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联部门  
			InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","false"); 
	    	<#--
	    	AgencyInstCascadeDWR("inst.id","dept.id","duty.id",#{user.id?if_exists},#{user.organization.id?if_exists},"${action.getText('')}","edit");
	    	-->
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    <#else>
    	    <#if req.getParameter('inst.id')?exists>
    	        getObjByName('inst.id').value='${req.getParameter('inst.id')?if_exists}';
		    	//设置同步
		    	DWREngine.setAsync(false); 
		    	//回调单位的值后触发DWR 级联部门  
				InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","false"); 
		    	<#--
		    	AgencyInstCascadeDWR("inst.id","dept.id","duty.id",#{user.id?if_exists},#{user.organization.id?if_exists},"${action.getText('')}","edit");
		    	-->
		    	//重新设置为异步方式
		    	DWREngine.setAsync(true); 
    	    </#if>
	    </#if>
 	
		//部门
		<#if personnelFile.dept?exists>
			getObjByName('dept.id').value='${personnelFile.dept.id?if_exists}';
	    	//设置同步
	    	DWREngine.setAsync(false); 
	    	//回调单位的值后触发DWR 级联职务 
			DutyCascadeDWR('dept.id','duty.id',#{user.organization.id?if_exists},'${action.getText('')}',"edit")
	    	//重新设置为异步方式
	    	DWREngine.setAsync(true); 
	    <#else>
    	     <#if req.getParameter('dept.id')?exists>
		    	 getObjByName('dept.id').value='${req.getParameter('dept.id')?if_exists}';
		    	 //设置同步
		    	 DWREngine.setAsync(false); 
		    	 //回调单位的值后触发DWR 级联职务 
				 DutyCascadeDWR('dept.id','duty.id',#{user.organization.id?if_exists},'${action.getText('')}',"edit")
		    	 //重新设置为异步方式
		    	 DWREngine.setAsync(true); 
    	     </#if> 
	    </#if>

		 //职位
		<#if personnelFile.duty?exists>
			getObjByName('duty.id').value='${personnelFile.duty.id?if_exists}';
		<#else>
			<#if req.getParameter('duty.id')?exists>
				getObjByName('duty.id').value='${req.getParameter('duty.id')?if_exists}';
			</#if>    
		</#if>
        //保存成功或者更新时保留下拉列表的值
		<#if personnelFile.marriage?exists>
			getObjByName('personnelFile.marriage').value=${personnelFile.marriage.id};
		</#if>
		<#if personnelFile.national?exists>
			getObjByName('personnelFile.national').value=${personnelFile.national.id};
		</#if>
		<#if personnelFile.birthplace?exists>
			getObjByName('personnelFile.birthplace').value=${personnelFile.birthplace.id};
		</#if>
		<#if personnelFile.politice?exists>
			getObjByName('personnelFile.politice').value=${personnelFile.politice.id};
		</#if>
		
		<#if personnelFile.education?exists>
			getObjByName('personnelFile.education').value=${personnelFile.education.id};
		</#if>
		
		 //业务属性
		<#if personnelFile.businessType?exists>
			getObjByName('businessType.id').value='${personnelFile.businessType.id?if_exists}';
			<#elseif req.getParameter('businessType.id')?exists>
			getObjByName('businessType.id').value='${req.getParameter('businessType.id')}';
		</#if>
		
		<#if personnelFile.workway?exists>
			getObjByName('personnelFile.workway').value=${personnelFile.workway.id};
		</#if>
		<#if personnelFile.status?exists>
			getObjByName('personnelFile.status').value=${personnelFile.status.id};
		</#if>
		
	// }
</script>
</@htmlPage>
<#if !first>
<ul id="beautytab">
	<li>
		<a id="additionalInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/personnelFile/editAdditionalInfo.html?personnelFiles.id=${personnelFile.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('personnel.additionnlInfo')}</a>
	</li>
	<li>
		<a id="educationInfo" onclick="activeTab(this);"  href='${req.contextPath}/personnelFile/listEducation.html?personnelFiles.id=${personnelFile.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('personnel.educationBackground')}</a>
	</li>
	<li>
		<a id="workAndResume" onclick="activeTab(this);" href="${req.contextPath}/personnelFile/listWorkExperience.html?pf.id=${personnelFile.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >${action.getText('personnel.wordExperience')}</a>
	</li>
	<li>
		<a id="societyRelation" onclick="activeTab(this);" href='${req.contextPath}/personnelFile/listSocialRelation.html?pf.id=${personnelFile.id?if_exists}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('personnel.socialRelation')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/personnelFile/editAdditionalInfo.html?personnelFiles.id=${personnelFile.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>