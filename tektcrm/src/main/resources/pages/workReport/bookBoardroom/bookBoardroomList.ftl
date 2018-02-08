<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('会议室预订列表')}">
	<@ww.form name="'listForm'" namespace="'/workReport'" action="'searchBookBoardroom'" method="'post'">
		<@ww.token name="searchBookBoardroomToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./bookBoardroomSearcher.ftl" />
        <#assign returnName='replaceWord'>
		<@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#if !(action.isReadOnly())>
				<@redirectButton value="${action.getText('new')}" url="${req.contextPath}/workReport/editBookBoardroom.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
       		</#if>
        </@buttonBar>
        
		<@list title="会议室预订列表"  
		includeParameters="boardroom.name|state.id|boardroom.code|bookBoardroom.startDate|bookBoardroom.endDate|" fieldMap="like:boardroom.name|boardroom.code|bookBoardroom.startDate|bookBoardroom.endDate|" >
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="bookBoardroomIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	 
			<@vcolumn title="${action.getText('会议室名称')}" property="boardroom.name" >
				<a href="${req.contextPath}/workReport/editBookBoardroom.html?readOnly=${req.getParameter('readOnly')?if_exists}&bookBoardroom.id=#{object.id}" >${object.boardroom.name}</a>
					<@vlh.attribute name="width" value="10%" />
				<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('开始时间')}" property="startDate" sortable="desc" format="yyyy-MM-dd kk:mm:ss">
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="12%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('结束时间')}" property="endDate" sortable="desc" format="yyyy-MM-dd kk:mm:ss">
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="12%" />
            </@vcolumn>
	 
			<@vcolumn title="${action.getText('申请人')}" property="applicant.name" >
				<@alignLeft/>
				<@vlh.attribute name="width" value="8%" />
			</@vcolumn>
			
			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc" >
	            <@alignLeft/>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
            
			<@vcolumn title="${action.getText('是否提交')}" property="isSaved" sortable="desc" >
	            <@alignLeft/>
				<#if object.isSaved?exists && object.isSaved=='1'>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
            
			<@vcolumn title="${action.getText('说明')}" property="explain" >
			<#assign returnName=returnName +'replaceWord'>
            <@ww.hidden name="'${returnName}'" value="'${object.explain?if_exists}'"/>
            <span title="${object.explain?if_exists}">
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
			<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('会议室预订')}?" />
			<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
			   <@ww.param name="'onclick'" value="'return confirmDeletes(\"bookBoardroomIds\", \"${confirmMessage}\");'"/>
			</@vsubmit>
		</#if>
	</@buttonBar>
</@ww.form>
<script type="text/javascript">
</script>
</@htmlPage>
