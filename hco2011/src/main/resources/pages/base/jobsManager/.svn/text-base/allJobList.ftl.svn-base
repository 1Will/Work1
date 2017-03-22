<#include "../../includes/hco2011.ftl"/>
<@htmlPage title="${action.getText('job.searcher')}" >
	<@ww.form name="'listform'"  action="'searchJob'" method="'post'">
		<@ww.token name="'searchJobToken'"/>
			<#include "./jobSearcher.ftl"> 
			<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
			<@buttonBar>
				<@vsubmit name="'search'" value="'${action.getText('search')}'"/>
			<#if jobsType?exists>
       			<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/jobs/editJob.html?jobType=${jobsType.id}"/>
       			<#else>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/jobs/editJob.html"/>
      		</#if>
			</@buttonBar>
		<#if jobsType?exists>
			<@ww.hidden name="'jobsType'" value="#{jobsType.id}"/>
			<@ww.hidden name="'jobsType.id'" value="#{jobsType.id}"/>
		</#if>
		<@list  title="${action.getText('job.list')}" includeParameters="code|name|onlyInvalid|onlyValid|jobsType" fieldMap="like:code|name">
			<@vlh.checkbox property="id" name="jobs.ids">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
	       <#if !object.disabled>
		            <@vcolumn title="${action.getText('job.code')}" property="code" sortable="asc">
		            <#if jobsType?exists>		        
		                <a href="editJob.html?jobType=#{jobsType.id}&job.id=#{object.id}">${object.code}</a>
		             <#else>
		             	<a href="editJob.html?job.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}">${object.code}</a>
		            </#if>   
		                <@alignLeft/>
		            </@vcolumn>
	            <#else>
	            	<@vcolumn title="${action.getText('job.code')}" property="code" sortable="asc">
		                ${object.code}
		                <@alignLeft/>
		            </@vcolumn>
	         </#if>
            <@vcolumn title="${action.getText('job.name')}" property="name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn> 
            <@vcolumn title="${action.getText('job.type')}" property="jobType.name" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn> 	        
		</@list>
		 <#if !first>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@crm_disabledOrEnabled_button message="${action.getText('job')}" boxName="jobs.ids" jsFunctionName="checkInvalidParms()"/>
		  <script language="JavaScript" type="text/JavaScript"> 
			   function validateInvalid(delFun, checkFun) {
					      if (delFun) {
					        checkFun;
					        return true;
					      }
					      return false;
					   }
			</script>
       </@buttonBar>
       </#if>
       </#if>
	</@ww.form>
</@htmlPage>
