<#include "../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" namespace="'/customerRelationship'" action="'searchParticipant'" method="'post'">
		<@ww.token name="searchParticipantToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'caFlag'" value="'${req.getParameter('caFlag')?if_exists}'"/>
		<@ww.hidden name="'pfFlag'" value="'${req.getParameter('pfFlag')?if_exists}'"/>
		
		<@ww.hidden name="'backVisit.id'" value="'${req.getParameter('backVisit.id')?if_exists}'"/>
		<@ww.hidden name="'projectInfoPlan.id'" value="'${req.getParameter('projectInfoPlan.id')?if_exists}'"/>
			<#assign itemNo=1/>
		<@list title="" 
				includeParameters="participant.id|backVisit.id|projectInfoPlan.id|contactArchives.id|personnelFiles.id|caFlag|pfFlag|readOnly|onlyInvalid|onlyValid" 
				fieldMap="" >
            <#if !(action.isReadOnly())>
	            <@vlh.checkbox property="id" name="participantIds">
	                <@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            
            <@vcolumn title="${action.getText('序号')}" property="id" >
              <a href="javascript:editParticipant_OpenDialog_update('#{object.id}');">#{itemNo}</a> 
            <@alignCenter attributes="width:80;"/>
            </@vcolumn>
            <#assign itemNo=itemNo + 1/>
            
            <#if caFlag?exists && caFlag=='caFlag'>
	             <@vcolumn title="${title?if_exists}" property="contactArchives.name" sortable="desc" >
	            	<@alignLeft attributes="width:150;"/>
	            </@vcolumn>
            <#else>
	            <@vcolumn title="${title?if_exists}" property="personnelFiles.name" sortable="desc" >
	            	<@alignLeft attributes="width:150;"/>
	            </@vcolumn>
            </#if>
            
            <@vcolumn title="${action.getText('责任')}" property="duty"   >
            	<@alignLeft />
            </@vcolumn>
            
        </@list>
         <#if !first>
			<@buttonBar>
				<#if !(action.isReadOnly())>
					<@vbutton class="button" name="${action.getText('new')}" value="${action.getText('new')}" onclick="editParticipant_OpenDialog();"/>
					<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('参与者')}?" />
		            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
		                <@ww.param name="'onclick'" value="'return confirmDeletes(\"participantIds\", \"${confirmMessage}\");'"/>
		            </@vsubmit>
	            </#if>
			</@buttonBar>
		</#if>
    </@ww.form>
</@framePage>
<script>
	function editParticipant_OpenDialog(){
	   var url="";
	   <#if backVisit?exists>
	   <#if backVisit.id?exists>
		   <#if caFlag?exists && caFlag=='caFlag'>
		   		url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&caFlag=caFlag&backVisit.id=#{backVisit.id?if_exists}";
		   <#else>
		   		url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisit.id=#{backVisit.id?if_exists}";
		   </#if>
	   </#if>
	   </#if>
	   
		<#if projectInfoPlan?exists>
		<#if projectInfoPlan.id?exists>
	   		url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfoPlan.id=#{projectInfoPlan.id}";
	   </#if>
	   </#if>
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 function editParticipant_OpenDialog_update(id){
	   var url="";
		<#if backVisit?exists>
		<#if backVisit.id?exists>
			<#if caFlag?exists && caFlag=='caFlag'>
	   			url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&caFlag=caFlag&backVisit.id=#{backVisit.id?if_exists}&participant.id="+id;
			<#else>
		   		url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&backVisit.id=#{backVisit.id?if_exists}&participant.id="+id;
			</#if>
	   </#if>
	   </#if>
			
		<#if projectInfoPlan?exists>
		<#if projectInfoPlan.id?exists>
	   		url= "${req.contextPath}/customerRelationship/editParticipant.html?readOnly=${req.getParameter('readOnly')?if_exists}&projectInfoPlan.id=#{projectInfoPlan.id}&participant.id="+id;
	   </#if>
	   </#if>
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
	 
</script>