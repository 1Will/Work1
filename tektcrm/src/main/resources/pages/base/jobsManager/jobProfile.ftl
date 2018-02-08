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
				<@textfield anothername="code" label="${action.getText('job.code')}" value="${job.code?if_exists}" name="job.code" id="job.code" disabled="true" cssClass="underline" required="true"/>
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
		if(!textfieldCheck_name()){
			getObjByName('job.name').focus();
			return false;
		}
		if(!selectCheck_jobsType()){
			getObjByName('jobsType').focus();
			return false;
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
	      
  		}
	     
	      
</script>