<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('会议室列表')}">
	<@ww.form name="'listForm'" namespace="'/workReport'" action="'searchBoardroom'" method="'post'">
		<@ww.token name="searchBoardroomToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#include "./boardroomSearcher.ftl" />
        <#assign returnName='replaceWord'>
		<@buttonBar>
			<@vsubmit name="'search'" cssClass="'button'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
        
		<@list title="会议室列表"  
		includeParameters="personnelFiles.id|id|boardroom.name|boardroom.code|state.id|" fieldMap="like:boardroom.name|boardroom.code|" >
			<@vcolumn title="${action.getText('会议室编码')}" >
				<a href="javascript: returnDialog(new Array('${object.id}','${object.name}'));" >${object.code}</a>
					<@vlh.attribute name="width" value="10%" />
				<@alignLeft/>
			</@vcolumn>
	 
			<@vcolumn title="${action.getText('会议室名称')}" property="name" >
				<@alignLeft/>
			</@vcolumn>  
			
			<@vcolumn title="${action.getText('面积')}" property="square" sortable="desc" format="0.00" >
	            <@alignLeft/>
            </@vcolumn>
						
			<@vcolumn title="${action.getText('最大人数')}" property="maxPeople" sortable="desc" format="0" >
	            <@alignLeft/>
            </@vcolumn>
			
			<@vcolumn title="${action.getText('有投影仪')}" property="hasProjector" sortable="desc" >
	            <@alignLeft/>
				<#if object.hasProjector>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('有网络')}" property="hasNet" sortable="desc" >
	            <@alignLeft/>
				<#if object.hasNet>
					${action.getText('是')}
				<#else>
					${action.getText('否')}
				</#if>
	            <@vlh.attribute name="width" value="8%" />
            </@vcolumn>
			
			<@vcolumn title="${action.getText('状态')}" property="state.name" sortable="desc" >
	            <@alignLeft/>
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
</@ww.form>
<script type="text/javascript">
</script>
</@htmlPage>
