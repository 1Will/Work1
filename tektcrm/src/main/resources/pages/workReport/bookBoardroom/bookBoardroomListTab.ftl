<#include "../../includes/hco2011.ftl" />
<@framePage title="">
	<@ww.form name="'listForm'" namespace="'/workReport'" action="'searchBookBoardroom'" method="'post'">
		<@ww.token name="searchBookBoardroomToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
        <#assign returnName='replaceWord'>
        
		<@list title="">
		<#if !(action.isReadOnly())>
			<@vlh.checkbox property="id" name="bookBoardroomIds">
	            <@vlh.attribute name="width" value="30" />
	        </@vlh.checkbox>
        </#if>
	 
			<@vcolumn title="${action.getText('会议室名称')}" property="boardroom.name" >
				<a href="#" onclick = "openBookBoardroom(${object.id})" >${object.boardroom.name}</a>
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
	function openBookBoardroom(id){
		var url = "${req.contextPath}/workReport/editBookBoardroom.html?readOnly=${req.getParameter('readOnly')?if_exists}&popWindowFlag=popWindowFlag&bookBoardroom.id="+id;
		openNewWindow(url);
	}
</script>
</@framePage>
