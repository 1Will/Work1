<#include "../../includes/hco2011.ftl" />
<@htmlPage title="日程列表">
	<@ww.form name="'listForm'" namespace="'/workReport'" action="'searchNewTask'" method="'post'">
		<@ww.token name="searchNewTaskToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./newTaskSearcher.ftl" />
        <#assign returnName='replaceWord'>
		<@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/workReport/editNewTask.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
		</@buttonBar>

		<@list title="日程列表" includeParameters="personnelFiles.id|id|newTask.name|newTask.code|state.id|" fieldMap="like:newTask.name|newTask.code|" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="newTaskIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	
			<@vcolumn title="${action.getText('日程编码')}" property="code" >
				<a href="${req.contextPath}/workReport/editNewTask.html?readOnly=${req.getParameter('readOnly')?if_exists}&newTask.id=#{object.id}" >${object.code}</a>
					<@vlh.attribute name="width" value="10%" />
				<@alignLeft/>
			</@vcolumn>  
	 
			<@vcolumn title="${action.getText('日程名称')}" property="name" >
				<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('开始时间')}" property="startTime" sortable="desc" format="yyyy-MM-dd kk:mm:ss">
				<@alignLeft/>
				<@vlh.attribute name="width" value="12%" />
			</@vcolumn>
			
			<@vcolumn title="${action.getText('结束时间')}" property="endTime" sortable="desc" format="yyyy-MM-dd kk:mm:ss">
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="12%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('内容')}" property="content" >
			<#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.content?if_exists}'"/>
            <span title="${object.content?if_exists}">
	            <script>
	            	var s = getObjByName('${returnName}').value;
	            	if(s.length<= 18){
		            	document.write(s);
	            	}else{
		            	s=s.replace(/[\r\n]/g, "");
		            	document.write(s.slice(0,18)+"...");
	            	}
	            </script>
            </span>
			</@vcolumn>
		</@list>
 	<@buttonBar>
		<#if !(action.isReadOnly())>
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('会议室')}?" />	 
			<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			   <@ww.param name="'onclick'" value="'return confirmDeletes(\"newTaskIds\", \"${confirmMessage}\");'"/>
			</@vsubmit>
		</#if>
	</@buttonBar>
</@ww.form>
<script type="text/javascript">
</script>
</@htmlPage>
