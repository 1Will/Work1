<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#--$Id: codeValueProfile.ftl 11326 2008-03-15 06:48:54Z wzou $ -->

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('employee.title')}">
     <@ww.form  name="'employeeProfile'" action="'saveEmployee'" namespace="'/employees'" method="'post'" >
       <@ww.token name="saveEmployeeToken"/>
       		<#if employee.id?exists>
                <@ww.hidden name="'employee.id'" value="#{employee.id}"/>
            </#if>
            <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
            <@ww.hidden name="'employee.instId'" value="'${req.getParameter('employee.instId')?if_exists}'" />
            <@ww.hidden name="'employee.deptId'" value="'${req.getParameter('employee.deptId')?if_exists}'" />
            <@ww.hidden name="'employee.duty'" value="'${req.getParameter('employee.duty')?if_exists}'" />
            <@ww.hidden name="'employee.workingMode'" value="'${req.getParameter('employee.workingMode')?if_exists}'" />
            <@ww.hidden name="'employee.sex'" value="'${req.getParameter('employee.sex')?if_exists}'" />
            <@ww.hidden name="'employee.nationalityId'" value="'${req.getParameter('employee.nationalityId')?if_exists}'" />
            <@ww.hidden name="'employee.politicsId'" value="'${req.getParameter('employee.politicsId')?if_exists}'" />
            <@ww.hidden name="'employee.isSale'" value="'${req.getParameter('employee.isSale')?if_exists}'" />
            <@ww.hidden name="'employee.isSys'" value="'${req.getParameter('employee.isSys')?if_exists}'" />
            <@ww.hidden name="'employee.isMarried'" value="'${req.getParameter('employee.isMarried')?if_exists}'" />
            <@ww.hidden name="'employee.status'" value="'${req.getParameter('employee.status')?if_exists}'" />
         <@inputTable>
             <tr>
                 <@ww.textfield label="'${action.getText('employee.no')}'" name="'employee.employeeNo'" value="'${employee.employeeNo?if_exists}'" cssClass="'underline'" required="true" />
                 <@ww.textfield label="'${action.getText('employee.name')}'" name="'employee.name'" value="'${employee.name?if_exists}'" cssClass="'underline'" required="true" />
                 <@ww.textfield label="'${action.getText('employee.archivecode')}'" name="'employee.archiveCode'"  value="'${employee.archiveCode?if_exists}'" cssClass="'underline'" />
             </tr> 
             <tr>
             	<@ww.select 
		    		label="'${action.getText('employee.inst')}'"
					required="true"
					name="'inst.id'" 
					value="'${req.getParameter('inst.id')?if_exists}'"
					listKey="id"
					listValue="name" 
					list="allInsts"
			    	emptyOption="false" 
			    	disabled="false" 
			    	onchange="'InstitutionSelectDeptDWR(\"inst.id\",\"dept.id\",\"${action.getText('')}\",\"true\")'"
			    	/>

			    	<@ww.select 
			    		label="'${action.getText('employee.dept')}'"
						required="true"
						name="'dept.id'" 
						value="'${req.getParameter('dept.id')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allDepts"
				    	emptyOption="false" 
				    	disabled="false" />
				    	
				    <@ww.select 
			    		label="'${action.getText('employee.duty')}'"
						required="true"
						name="'duty'" 
						value="'${req.getParameter('duty')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allDutys"
				    	emptyOption="false" 
				    	disabled="false"/>
				    <#--	
		    		<@ww.textfield label="'${action.getText('employee.duty')}'" name="'employee.duty'"  value="'${employee.duty?if_exists}'" cssClass="'underline'" required="true" />
		    		-->
    			</td>
             </tr>
             <tr>
             <#--
             	<td align="right" valign="top">   	
	       			<label class="label">${action.getText('employee.workingmode')}:</label>
	       		</td>
	       		<td>
					<select name="workingmode">
	    				<option value="${action.getText('employee.workingmode.in')}">${action.getText('employee.workingmode.in')}</option>
	    				<option value="${action.getText('employee.workingmode.out')}">${action.getText('employee.workingmode.out')}</option>
	    			</select>
	    		</td>-->
	    		<@ww.select 
			    		label="'${action.getText('employee.workingmode')}'"
						required="false"
						name="'workingmode'" 
						value="'${req.getParameter('workingmode')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allWorkingModes"
				    	emptyOption="true" 
				    	disabled="false"/>
	    		<@ww.textfield label="'${action.getText('employee.workingplace')}'" name="'employee.workingPlace'" value="'${employee.workingPlace?if_exists}'" cssClass="'underline'" required="false" />
	    		<td align="right" valign="top">
			    	<label class="label">${action.getText('employee.sex')}:</label>
			    </td>
			    <td>
			    	<select name="sex">
		    			<option value="true">${action.getText('employee.sex.man')}</option>
		    			<option value="false">${action.getText('employee.sex.woman')}</option>
		    		</select>
    			</td>
             </tr>
             <tr>
             	<@ww.textfield label="'${action.getText('employee.identityCard')}'" name="'employee.identityCard'" value="'${employee.identityCard?if_exists}'" cssClass="'underline'" required="true" />
             	<@ww.select 
			    		label="'${action.getText('employee.nationality')}'"
						name="'nationality.id'" 
						value="'${req.getParameter('nationality.id')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allNationality"
				    	emptyOption="false" 
				    	disabled="false"
				    	required="true"
				    	/>
				<@ww.select 
			    		label="'${action.getText('employee.politics')}'"
						name="'politics.id'" 
						value="'${req.getParameter('politics.id')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allPolitics"
				    	emptyOption="true" 
				    	disabled="false"
				    	required="true"
				    	/>
             </tr>    
             <tr>
             	<@ww.textfield label="'${action.getText('employee.telephone')}'" name="'employee.telephone'" value="'${employee.telephone?if_exists}'" cssClass="'underline'" required="false" />
             	<@ww.textfield label="'${action.getText('employee.cellphone')}'" name="'employee.cellphone'" value="'${employee.cellphone?if_exists}'" cssClass="'underline'" required="true" />
             	<@ww.textfield label="'${action.getText('employee.email')}'" name="'employee.email'" value="'${employee.email?if_exists}'" cssClass="'underline'" required="false" />
             </tr>    
             <tr>
             	<td align="right" valign="top">
			    	<label class="label">${action.getText('employee.isSale')}:</label>
			    </td>
			    <td>
			    	<input type="radio" name="sales" value="true" >${action.getText('employee.is.yes')}
			    	<input type="radio" name="sales" value="false">${action.getText('employee.is.no')}
    			</td>
    			<td align="right" valign="top">
			    	<label class="label">${action.getText('employee.isSysUser')}:</label>
			    </td>
			    <td>
			    	<input type="radio" name="sys" value="true">${action.getText('employee.is.yes')}
			    	<input type="radio" name="sys" value="false">${action.getText('employee.is.no')}
    			</td>
             		<@ww.textfield label="'${action.getText('employee.userName')}'" name="'employee.userName'" value="'${employee.userName?if_exists}'" cssClass="'underline'" required="false" />
             </tr> 
             <tr>
             <@pp.datePicker label="'${action.getText('employee.birthday')}'" 
	            		   name="'employee.birthday'"
	     				   value="'${(employee.birthday?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     	<@pp.datePicker label="'${action.getText('employee.staffTime')}'" 
	            		   name="'employee.staffTime'"
	     				   value="'${(employee.staffTime?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" maxlength="10" required="true" 
	     				   maxlength="10"/>
	     	<@pp.datePicker label="'${action.getText('employee.officiallyTime')}'" 
	            		   name="'employee.officiallyTime'"
	     				   value="'${(employee.officiallyTime?string('yyyy-MM-dd'))?if_exists}'" 
	     				   cssClass="'underline'" 
	     				   size="15" required="false" maxlength="10"
	     				   maxlength="10"/>
             </tr>
             <tr>
             <#--
             	<td align="right" valign="top">
			    	<label class="label">${action.getText('employee.isMarried')}:</label>
			    </td>
			    <td>
			    	<select name="isMarried">
		    			<option value="${action.getText('employee.isMarried.yes')}">${action.getText('employee.isMarried.yes')}</option>
		    			<option value="${action.getText('employee.isMarried.no')}">${action.getText('employee.isMarried.no')}</option>
		    			<option value="${action.getText('employee.isMarried.other')}">${action.getText('employee.isMarried.other')}</option>
		    		</select>
    			</td>
    			<td align="right" valign="top">
			    	<label class="label">${action.getText('employee.status')}:</label>
			    </td>
			    <td>
			    	<select name="status">
		    			<option value="${action.getText('employee.status.yes')}">${action.getText('employee.status.yes')}</option>
		    			<option value="${action.getText('employee.status.no')}">${action.getText('employee.status.no')}</option>
		    		</select>
    			</td>-->
    			<@ww.select 
			    		label="'${action.getText('employee.isMarried')}'"
						name="'isMarried'" 
						value="'${req.getParameter('isMarried')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allMarries"
				    	emptyOption="false" 
				    	disabled="false"
				    	required="false"
				    	/>
				<@ww.select 
			    		label="'${action.getText('employee.status')}'"
						name="'status'" 
						value="'${req.getParameter('status')?if_exists}'" 
						listKey="id"
						listValue="name" 
						list="allStatus"
				    	emptyOption="false" 
				    	disabled="false"
				    	required="false"
				    	/>
             </tr>
         </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        </#if>	          
	         <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/employees/listEmployees.html"/>
         </@buttonBar>	
     </@ww.form>
     <script language="javascript">
     		//新建时保留值
	      <#if employee.inst?exists>
	      		getObjByName('inst.id').value=${employee.inst.id};
	      </#if>
	<#if employee.inst?exists>
		getObjByName('inst.id').value=${employee.inst.id};
		//设置同步
	    DWREngine.setAsync(false); 
	    //回调单位的值后触发DWR 级联部门  
	    InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","true");
	    //重新设置为异步方式
	    DWREngine.setAsync(true);   
	    getObjByName('dept.id').value = ${employee.dept.id};	
	<#else>
			//记录已存在时触发DWR 单位级联部门
			if('' != getObjByName('employee.instId').value){
			getObjByName('inst.id').value = getObjByName('employee.instId').value;
				if('' != getObjByName('employee.deptId').value){
					//设置同步
				    DWREngine.setAsync(false); 
				    //回调单位的值后触发DWR 级联部门  
				    InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","true");
				    //重新设置为异步方式
				    DWREngine.setAsync(true);  
					getObjByName('dept.id').value = getObjByName('employee.deptId').value;
				}
		}else{
			// 保持刷新时单位级联部门
			InstitutionSelectDeptDWR("inst.id","dept.id","${action.getText('')}","true");
		}
	</#if>	      
	      <#if employee.dept?exists>
	      		getObjByName('dept.id').value=${employee.dept.id};
	      </#if>
	      <#if employee.duty?exists>
	      		getObjByName('duty').value=${employee.duty.id};
	      </#if>
	      <#if employee.workingMode?exists>
	      	getObjByName('workingmode').value = ${employee.workingMode.id};
	      </#if>
	      <#if employee.new>
	      		getObjByName('sex').value='true';
	      <#else>
		      <#if (employee.sex)>
		      		getObjByName('sex').value='true';
		      	<#else>
		      		getObjByName('sex').value='false';
		      </#if>
	      </#if>
	      <#if employee.nationality?exists>
	      	getObjByName('nationality.id').value=${employee.nationality.id};
	      </#if>
	      <#if employee.politics?exists>
	      	getObjByName('politics.id').value=${employee.politics.id};
	      </#if>
	      var radios = document.getElementsByName("sales");
	      <#if employee.new>
				radios[0].checked='true';
	      <#else>
	
			      <#if (employee.isSale)>
			      		radios[0].checked='true';
			      	<#else>
			      		radios[1].checked='true';
			      </#if>
		  </#if>
		  var syss = document.getElementsByName("sys");
		  <#if employee.new>
		  		syss[0].checked='true';
		  	<#else>
	      		<#if (employee.isSysUser)>
	      			syss[0].checked='true';
	      		<#else>
	      			syss[1].checked='true';
	      		</#if>
	      </#if>
	      <#if employee.isMarried?exists>
	      	getObjByName('isMarried').value=${employee.isMarried.id};
	      </#if>
	      <#if employee.status?exists>
	      	getObjByName('status').value=${employee.status.id};
	      </#if>
	      //记录已经存在时保留值
	      if('' !=getObjByName('employee.instId').value ){
	      	getObjByName('inst.id').value=getObjByName('employee.instId').value
	      }
	      if('' !=getObjByName('employee.deptId').value ){
	      	getObjByName('dept.id').value=getObjByName('employee.deptId').value
	      }
	      if('' !=getObjByName('employee.duty').value ){
	      	getObjByName('duty').value=getObjByName('employee.duty').value
	      }
	      if('' !=getObjByName('employee.workingMode').value ){
	      	getObjByName('workingmode').value=getObjByName('employee.workingMode').value
	      }
	      if('' !=getObjByName('employee.sex').value ){
	      	getObjByName('sex').value=getObjByName('employee.sex').value
	      }
	      if('' !=getObjByName('employee.nationalityId').value ){
	      	getObjByName('nationality.id').value=getObjByName('employee.nationalityId').value
	      }
	      if('' !=getObjByName('employee.politicsId').value ){
	      	getObjByName('politics.id').value=getObjByName('employee.politicsId').value
	      }
	      if('' !=getObjByName('employee.isSale').value){
	      	if('true'==getObjByName('employee.isSale').value){
	      		radios[0].checked='true';
	      	}else{
	      		radios[1].checked='true';
	      	}
	      }
	      if('' !=getObjByName('employee.isSys').value ){
	      	if('true'==getObjByName('employee.isSys').value){
	      		syss[0].checked='true';
	      	}else{
	      		syss[1].checked='true';
	      	}
	      }
	      if('' !=getObjByName('employee.isMarried').value ){
	      	getObjByName('isMarried').value=getObjByName('employee.isMarried').value
	      }
	       if('' !=getObjByName('employee.status').value ){
	      	getObjByName('status').value=getObjByName('employee.status').value
	      }
	 </script>
     <script language="javascript">
     	function validate(){

     		/*
     		*验证编码名称是否为空，长度是否溢出
     		*/
     		if('' == getObjByName('employee.employeeNo').value){
     			alert("${action.getText('employee.no.required')}");
     			return false;
     		}else{
     			if(!isValidLengthValue(getObjByName('employee.employeeNo').value,0,20)){
     				alert("${action.getText('employee.no.maxLength')}");
     				return false;
     			}
     		}
     		/*验证姓名*/
     		if('' == getObjByName('employee.name').value){
     			alert("${action.getText('employee.name.required')}");
     			return false;
     		}else{
     			if(!isValidLengthValue(getObjByName('employee.name').value,0,20)){
     				alert("${action.getText('employee.name.maxLength')}");
     				return false;
     			}
     		}
     		
     		/*验证档案编码*/
     		if(''!= getObjByName('employee.archiveCode').value){
	     		if(!isValidLengthValue(getObjByName('employee.archiveCode').value,0,20)){
	     			alert("${action.getText('employee.archiveCode.maxLength')}");
	     			return false;
	     		}
	     	}
     		/*验证工作地点*/
     		if('' != getObjByName('employee.workingPlace').value){
     			if(!isValidLengthValue(getObjByName('employee.workingPlace').value,0,20)){
     				alert("${action.getText('employee.workingPlace.maxLength')}");
     				return false;
     			}
     		}
     		/*验证电话*/
     		if('' != getObjByName('employee.telephone').value){
			   var str = getObjByName('employee.telephone').value
		       if(str.length>20){
		       		alert('${action.getText('employee.telephone.maxLength')}');
		       		return false;
		       }
     		}
     		/*验证用户名*/
     		if('' != getObjByName('employee.userName').value){
			   if(!isValidLengthValue(getObjByName('employee.userName').value,0,20)){
     				alert("${action.getText('employee.userName.maxLength')}");
     				return false;
     			}
     		}
     		/*验证公司*/
     		if(-1==getObjByName('inst.id').value){
     			alert("${action.getText('employee.inst.not.null')}");
     			return false;
     		}
     		
     		/*验证部门*/
     		if(-1==getObjByName('dept.id').value){
     			alert("${action.getText('employee.dept.not.null')}");
     			return false;
     		}
     		/*验证职务*/
     		if(-1==getObjByName('duty').value){
     			alert("${action.getText('employee.duty.not.null')}");
     			return false;
     		}     		
     		/*验证身份证号*/
     		if('' == getObjByName('employee.identityCard').value){
     			alert("${action.getText('employee.identityCard.required')}");
     			return false;
     		}else{
     			if(!checkIdcard(getObjByName('employee.identityCard').value)){
	 				alert("${action.getText('employee.identityCard.error')}");
	 				return false;
	 			}
     		}
     		
     		/*验证籍贯*/
     		if(-1==getObjByName('nationality.id').value){
     			alert("${action.getText('employee.nationality.id.not.null')}");
     			return false;
     		}
     		/*验证政治面貌*/
     		if(-1==getObjByName('politics.id').value){
     			alert("${action.getText('employee.politics.id.not.null')}");
     			return false;
     		}
     		/*验证手机号*/
     		if('' == getObjByName('employee.cellphone').value){
     			alert("${action.getText('employee.cellphone.required')}");
     			return false;
     		}else{
     		   var str = getObjByName('employee.cellphone').value
		        if(str.length>20){
		       		alert('${action.getText('employee.cellphone.maxLength')}');
		        	return false;
		        }
     		}
     		/*验证email*/
     		if ('' != getObjByName('employee.email').value) {
				var value = getObjByName('employee.email').value;
	            if (!value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.org)|(\.info)|(\.edu)|(\.mil)|(\.gov)|(\.biz)|(\.ws)|(\.us)|(\.tv)|(\.cc)|(\..{2,2}))$)\b/gi)) {
	          		emailMsg="${action.getText('employee.email.error')}";
	     			alert(emailMsg);
	          		return false;
	          	}
	          	if (!isValidLengthValue(getObjByName('employee.email').value,0,50)) {
					      alert("${action.getText('employee.email.maxLength')}");
					      return false;
			    }
          }
     	//出生时间
    	if(getObjByName('employee.birthday').value == ''){
    		alert("${action.getText('employee.birthday.not.null')}");
          	return false;
    	}else{
		 	if(!isDate('employee.birthday')){
			 	beginDateMsg="${action.getText('employee.birthday')}" + "${action.getText('dateFormate.error')}";
				alert(beginDateMsg);
				return false;
			}
			/*
			if(isDateLessThenCurrent(getObjByName('employee.birthday').value)){
				alert('${action.getText('employee.birthday.earlyError')}');
				return false;
			}*/
		}
     	//入职时间
    	if(getObjByName('employee.staffTime').value == ''){
    		alert("${action.getText('employee.staffTime.not.null')}");
          	return false;
    	}else{
		 	if(!isDate('employee.staffTime')){
			 	beginDateMsg="${action.getText('employee.staffTime')}" + "${action.getText('dateFormate.error')}";
				alert(beginDateMsg);
				return false;
			}
		}   
    //转正时间

		 if(!validateTime(getObjByName('employee.officiallyTime').value)){
		 	beginDateMsg="${action.getText('employee.officiallyTime')}" + "${action.getText('dateFormate.error')}";
			alert(beginDateMsg);
			return false;
		} 
}
     </script>
</@htmlPage>
