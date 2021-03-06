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

<#--$Id: dutyProfile.ftl 2010-1-23  zsz $ -->

<#include "../../includes/hco2011.ftl" /> 
<@htmlPage title="${action.getText('duty.new')}">
     <@ww.form  name="'dutyProfile'" action="'saveDuty'" namespace="'/duty'" method="'post'" >
       <@ww.token name="saveDutyToken"/>
       	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       		<#if duty.id?exists>
                <@ww.hidden name="'duty.id'" value="#{duty.id}"/>
            </#if>
            <#if departmentId?exists>
                 <@ww.hidden name="'dept.id'" value="${req.getParameter('dept.id')?if_exists}"/>
             </#if>
         <@inputTable>
             <tr>
                 <#if duty.id?exists>
                 <@textfield label="${action.getText('duty.code')}" anothername="code" maxlength="20" name="duty.code" value="${duty.code?if_exists}" cssClass="underline" required="false" disabled="true"/>
                 <#else>
                 <@textfield label="${action.getText('duty.code')}" anothername="code" maxlength="20" name="duty.code" value="${duty.code?if_exists}" cssClass="underline" required="false" disabled="true"/>
                 </#if>
                 <@textfield label="${action.getText('duty.name')}" anothername="name" maxlength="20" name="duty.name" value="${duty.name?if_exists}" cssClass="underline" required="true" />
                </tr>
                <tr>
             <#if req.getParameter('dept.id')?exists && req.getParameter('dept.id')!="">
                <@select  
	    		label="${action.getText('duty.dept.name')}"
				required="true"
				name="dept"
				id="dept"
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name" 
				list="allDepts"
		    	emptyOption="false" 
		    	disabled="true">
		    	<#-- onchange="DutyCascadeDWR(\"dept.id\",\"jobName.id\",14,\"${action.getText('')}\",\"edit\")"> -->
		    	</@select> 
		    	<script>
					//DutyCascadeDWR('dept.id','jobName.id',14,'${action.getText('')}','edit');
				</script>
			 <#else>
			  	<@select 
			  	label="${action.getText('duty.dept.name')}" 
				name="dept" 				
				id="dept" 
				value="${req.getParameter('dept.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allDepts"
				required="true"
				emptyOption="false" 
				disabled="false">
				<#-- onchange="DutyCascadeDWR(\"dept\",\"jobName.id\",14,\"${action.getText('')}\",\"edit\")" -->
				</@select>  
			 </#if>
				    <@select 
		    		label="${action.getText('duty.jobName.name')}"
					required="true"
					name="jobName.id" 
					id="jobName.id"
					anothername="jobName"
					value="${req.getParameter('jobName.id')?if_exists}"
					listKey="id"
					listValue="name" 
					list="allJobNames"
			    	emptyOption="true" 
			    	disabled="false" 
			    	/>
                  </tr>
                  <tr>
                  	<@select label="${action.getText('duty.perType')}" 
				   	   anothername="selectCheckPerType"
				       name="perType.id" 
				       value="${req.getParameter('perType.id')?if_exists}"
				       listKey="id" 
				       listValue="name"
				       list="allPerType" 
				       emptyOption="false" 
				       disabled="false" 
				       required="true">
				    </@select>
                  </tr>
				<tr>
			        <td align="right" valign="top">
			    		<label class="label">
			    			${action.getText('duty.comment')}:
			    		</label>
			    	</td>
			        <td colspan="10">
			        	<textarea name="duty.comment" rows="4" name="duty.comment">${duty.comment?if_exists}</textarea>
			        </td>
			        <script language="javascript">
			             var width=document.body.clientWidth/9;
						 getObjByName("duty.comment").cols =width;
		             </script>
		        </tr>
                  
               <tr>
			        <td align="right" valign="top">
			    		<label class="label">
			    			${action.getText('duty.condition')}:
			    		</label>
			    	</td>
			        <td colspan="10">
			        	<textarea name="duty.condition" rows="4"  name="duty.condition">${duty.condition?if_exists}</textarea>
			        </td>
			         <script language="javascript">
			             var width=document.body.clientWidth/9;
						 getObjByName("duty.condition").cols =width;
		             </script>
		        </tr>
              </@inputTable>
         <@buttonBar>
         	<#if !(action.isReadOnly())>
	         <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return validate();'"/>
	        </#if>
	        <#if departmentId?exists>
	        	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/duty/listDuties.html?dept.id=#{departmentId?if_exists}"/>
	        	<#else>
	         	<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/duty/listDuties.html"/>
	         </#if>
         </@buttonBar>	
     </@ww.form>
     <script language="javascript">
	<#if duty.jobName?exists>
		 getObjByName('jobName.id').value=${duty.jobName.id};
	<#else>
		  
		 <#if req.getParameter('jobName.id')?exists>
			  getObjByName('jobName.id').value=${req.getParameter('jobName.id')?if_exists};
		 </#if>
	</#if>
	
		 <#if duty.dept?exists>
		  	 getObjByName('dept').value='${duty.dept.id}';
		 <#else>
		    
		    <#if !departmentId?exists>
		    getObjByName('dept').value='${req.getParameter('dept')?if_exists}';
		    <#else>
		    getObjByName('dept').value='${req.getParameter('dept.id')?if_exists}';
	      </#if>
	      </#if>
	      
		<#if duty.perType?exists>
			getObjByName('perType.id').value='${duty.perType.id?if_exists}';
		</#if>
	 </script>
     <script language="javascript">
     	function validate(){
	     	/*if(!textfieldCheck_code()){
	     		getObjByName('duty.code').focus();
	     		return false;
	     	}*/
	     	if(!textfieldCheck_name()){
	     		getObjByName('duty.name').focus();
	     		return false;
	     	}
	     	if(!selectCheck_jobName()){
	     		getObjByName('jobName.id').focus();
	     		return false;
	     	}
	     	if(!selectCheck_dept()){
	     		getObjByName('dept').focus();
	     		return false;
	     	}
	     	
	     	if("" != getObjByName("duty.comment").value){
				if(!isValidLength(document.forms[0], "duty.comment", null, 250)){
					alert("${action.getText('duty.comment.length')}");
					getObjByName('duty.comment').value="";
					getObjByName('duty.comment').focus();
					return false;
				}
			}
			if("" != getObjByName("duty.condition").value){
				if(!isValidLength(document.forms[0], "duty.condition", null, 250)){
					alert("${action.getText('duty.condition.length')}");
					getObjByName('duty.condition').value="";
					getObjByName('duty.condition').focus();
					return false;
				}
			}
	     return true;
 		}
     </script>
</@htmlPage>
