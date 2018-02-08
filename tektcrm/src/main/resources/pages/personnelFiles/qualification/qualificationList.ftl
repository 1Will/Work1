<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('证书列表')}">
	<@ww.form name="'listForm'" namespace="'/personnelFile'" action="'searchQualification'" method="'post'">
		<@ww.token name="searchQualificationToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./qualificationSearcher.ftl" />
        <#if personnelFiles?exists>
	        <#if personnelFiles.id?exists>
	                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
	        </#if>
        </#if>
        
                 
         <#if institution?exists>
         	<#if institution.id?exists>
                <@ww.hidden name="'institution.id'" value="#{institution.id?if_exists}"/>
         	</#if>
         </#if>
         
        <#assign returnName='replaceWord'>
        
		<@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        
		<@list title="证书列表"  
		includeParameters="personnelFiles.id|id|starTime|endTime|qualification.name|qualification.starTime_start|qualification.starTime_end|qualification.endTime_start|qualification.endTime_end|" 
		fieldMap="like:qualification.name,date:qualification.starTime_start|qualification.starTime_end|qualification.endTime_start|qualification.endTime_end|" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="qualificationIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	 
			<@vcolumn title="${action.getText('证书名称')}" property="name" >
				<a href="${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&qualification.id=#{object.id}" >${object.name}</a>
					<@vlh.attribute name="width" value="8%" />
				<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('开始时间')}" property="starTime" sortable="desc" format="yyyy-MM-dd" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('到期时间')}" property="endTime" sortable="desc" format="yyyy-MM-dd" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('管理部门')}" property="dept.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('证书类型')}" property="type.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('所属人')}" property="personnelFiles.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('所属单位')}" property="institution.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="15%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('说明')}" property="explain" >
			<#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.explain?if_exists}'"/>
            <span title="${object.explain?if_exists}">
	            <script>
	            	var s = getObjByName('${returnName}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
            </span>
			</@vcolumn>
		</@list>
 	<@buttonBar>
		<#if !(action.isReadOnly())>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('资格证书')}?" />	 
			<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			   <@ww.param name="'onclick'" value="'return confirmDeletes(\"qualificationIds\", \"${confirmMessage}\");'"/>
			</@vsubmit>
		</#if>
	</@buttonBar>
</@ww.form>
<script type="text/javascript">
	function editQualification(id){
		var url ='${req.contextPath}/personnelFile/editQualification.html?readOnly=${req.getParameter('readOnly')?if_exists}&qualification.id='+id;
    	openNewWindow(url);
    	if(isIE()){self.location.reload();};
	}
</script>
</@htmlPage>
