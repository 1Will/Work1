<#include "../../includes/macros.ftl"/>

<@htmlPage title="${action.getText('job.profile')}">
	<@ww.form name="'listform'" action="'saveJob'" method="'post'">
		<@ww.token name="'saveJobToken'"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if job.id?exists>
			<@ww.hidden name="'job.id'" value="#{job.id}"/>
		</#if>
		<#if jobsType?exists>
			<@ww.hidden name="'jobType'" value="#{jobsType.id}"/>
		</#if>
		<@inputTable>
			<tr>
			<#if job.id?exists>
				<@textfield anothername="code" label="${action.getText('job.code')}" value="${job.code?if_exists}" name="job.code" id="job.code" disabled="true" cssClass="underline" required="false"/>
			<#else>
				<@textfield anothername="code" label="${action.getText('job.code')}" value="${job.code?if_exists}" name="job.code" id="job.code" disabled="true" cssClass="underline" required="false"/>
			</#if>
				<@textfield anothername="name" label="${action.getText('job.name')}" value="${job.name?if_exists}" name="job.name" id="job.name" cssClass="underline" required="true"/>
			</tr>
			<tr>
				<#if jobsType?exists>
					<@select label="${action.getText('job.type')}"
						anothername="jobsType"
						id="jobsType"
						name="jobsType"
						value="${jobsType.id?if_exists}"
						list="allJobType"
						listKey="id"
						listValue="name"
						required="true"
						emptyOption="true"
						disabled="true"	/>
				   <#else>
				    	<@select label="${action.getText('job.type')}"
							anothername="jobsType"
							id="jobsType"
							name="jobsType"
							value="${req.getParameter('job.jobType.id')?if_exists}"
							list="allJobType"
							listKey="id"
							listValue="name"
							required="true"
							emptyOption="true"
							disabled="false"/>
				</#if>
				<@select label="${action.getText('job_hierarchy')}"
							anothername="hierarchy"
							id="hierarchy"
							name="hierarchy"
							value="${req.getParameter('job.hierarchy.id')?if_exists}"
							list="allhierarchy"
							listKey="id"
							listValue="name"
							required="true"
							emptyOption="true"
							disabled="false"/>
			</tr>
			<tr>
		    	<@select label="${action.getText('job_serialNumber')}"
							anothername="serialNumber"
							id="serialNumber"
							name="serialNumber"
							value="${req.getParameter('job.serialNumbe.id')?if_exists}"
							list="allSerialNumbe"
							listKey="id"
							listValue="name"
							required="true"
							emptyOption="true"
							disabled="false"/>
		
		 <@select label="${action.getText('job_rank')}"
							anothername="rank"
							id="rank"
							name="rank"
							value="${req.getParameter('job.rank.id')?if_exists}"
							list="allRank"
							listKey="id"
							listValue="name"
							required="true"
							emptyOption="true"
							disabled="false"/>		
		</tr>
		<tr>
		
		   <@select label="${action.getText('job_grade')}"
							anothername="grade"
							id="grade"
							name="grade"
							value="${req.getParameter('job.grade.id')?if_exists}"
							list="allGrade"
							listKey="id"
							listValue="name"
							required="true"
							emptyOption="true"
							disabled="false"/>		
		</tr>
		<tr>
			<td align="right" valign="top">
			<#--
				 <span class="required">*</span>
				--> 
	        		<label class="label">
	        			${action.getText('job_remark')}:
	        		</label>
	        	</td>
		        <td colspan="10">
		        	<textarea  name="jobReaker" id="jobReaker" rows="4"  value="${job.jobReaker ? if_exists}">${job.jobReaker ? if_exists}</textarea>
		        </td>
		        <script language="javascript">
					var width=document.body.clientWidth/9;
								getObjByName("jobReaker").cols =width;
				</script>
			</tr>
		
		</@inputTable>
		<@buttonBar>
		<#if !(action.isReadOnly())>
			<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick ="'return checkField()'" />
		</#if>
		<#if !jobsType?exists>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/jobs/listAllJob.html"/>
		<#else>
			<@redirectButton value="${action.getText('back')}" url="${req.contextPath}/jobs/listAllJob.html?jobsType=${jobsType.id}"/>
		</#if>
    </@buttonBar>
	</@ww.form>	
</@htmlPage>
<script  language="JavaScript" type="text/JavaScript">

	function checkField(){
		if(!textfieldCheck_code()){
			getObjByName('job.code').focus();
			return false;

		}
		if(!textfieldCheck_name()){
			getObjByName('job.name').focus();
			return false;
		}
		if(!selectCheck_jobsType()){
			getObjByName('jobsType').focus();
			return false;
		}
		
		if(!selectCheck_hierarchy()){
			getObjByName('hierarchy').focus();
			return false;
		}
		if(!selectCheck_serialNumber()){
			getObjByName('serialNumber').focus();
			return false;
		}
		
		if(!selectCheck_rank()){
			getObjByName('rank').focus();
			return false;
		}
		if(!selectCheck_grade()){
			getObjByName('grade').focus();
			return false;
		}
		
	      if("" != getObjByName("jobReaker").value){
				if(!isValidLength(document.forms[0], "jobReaker", null, 250)){
					alert("${action.getText('job_remark_length')}");
					getObjByName('jobReaker').value="";
					getObjByName('jobReaker').focus();
					return false;
				}
			}
		return true;
	}
     //新建时保留值
    window.onload=function(){
    
	      <#if job.jobType?exists>
			    getObjByName('jobsType').value=${job.jobType.id};
			</#if>
	      <#if jobsType?exists>
	      		getObjByName('jobsType').value=${jobsType.id};
	      </#if>
	      
	      <#if job.hierarchy ? exists>
			    getObjByName('hierarchy').value=${job.hierarchy.id};
		 </#if>
		 <#if job.serialNumbe ? exists>
		     
		       getObjByName('serialNumber').value=${job.serialNumbe.id};
	 	 </#if>
		 <#if job.rank ? exists>
		       getObjByName('rank').value=${job.rank.id};
		 </#if>
		 <#if job.grade ? exists>
		   
		       getObjByName('grade').value=${job.grade.id};
		 </#if>
	      
  		}
	     
	      
</script>