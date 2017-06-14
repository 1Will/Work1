<#include "../../includes/hco2011.ftl" />
<@framePage >
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<#assign number=1/>
	<#assign lastp='lastp'>
	<#assign thisp='thisp'>
	<#assign nextp='nextp'>
	<@ww.form  name="'listFrom'" action="" namespace="'/workReport'" method="'post'" >
   	<@list title="" includeParameters="id|projectInfo.name|weekly.code|lastPlan|thisPlan|nextPlan|onlyInvalid|onlyValid|projectInfo.id|weekly.id|" fieldMap="" >
        <@vcolumn title="${action.getText('序号')}" sortable="asc" property="id">
        <@vlh.attribute name="width" value="4%" />
            <a href="#" onclick="showWeekPlan('#{object.id}')">${number}</a>
            <#assign number=number+1 />
            <@alignCenter/>
        </@vcolumn>
        
        <#if req.getParameter('weekly.id')?exists>
        <@vcolumn title="${action.getText('项目名称')}" sortable="asc" property="projectInfo.name">
        <@vlh.attribute name="width" value="15%" />
            ${(object.projectInfo.name)}
            <@alignLeft/>
        </@vcolumn>
        </#if>
        
        <#if req.getParameter('projectInfo.id')?exists>
        <@vcolumn title="${action.getText('周报')}" sortable="asc" property="weekly.code">
        <@vlh.attribute name="width" value="15%" />
            ${(object.weekly.code)}
            <@alignLeft/>
        </@vcolumn>
        </#if>
        
        
        
        <@vcolumn title="${action.getText('上周计划')}" sortable="asc" property="lastPlan">
        <#assign lastp = lastp +'a'>
	     <@ww.hidden name="'${lastp}'" value="'${object.lastPlan?if_exists}'"/>
	        <span title="${object.lastPlan?if_exists}">
	            <script>
	            	var s = getObjByName('${lastp}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
	        </span>
            <@alignLeft/>
        </@vcolumn>
        
        <@vcolumn title="${action.getText('本周计划')}" sortable="asc" property="thisPlan">
         <#assign thisp=thisp +'b'>
	     <@ww.hidden name="'${thisp}'" value="'${object.thisPlan?if_exists}'"/>
	        <span title="${object.thisPlan?if_exists}">
	            <script>
	            	var s = getObjByName('${thisp}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
	        </span>
            <@alignLeft/>
        </@vcolumn>
        
        <@vcolumn title="${action.getText('下周计划')}" sortable="asc" property="nextPlan">
         <#assign nextp = nextp +'c'>
	     <@ww.hidden name="'${nextp}'" value="'${object.nextPlan?if_exists}'"/>
	        <span title="${object.nextPlan?if_exists}">
	            <script>
	            	var s = getObjByName('${nextp}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
	        </span>
            <@alignLeft/>
        </@vcolumn>
    </@list>
<@buttonBar>
<#if req.getParameter('weekly.id')?exists>
	<@vbutton value="${action.getText('new')}" onclick="weekPlan_OpenDialog(${req.getParameter('weekly.id')?if_exists})"/>
</#if>
<#if req.getParameter('projectInfo.id')?exists>
	<@vbutton value="${action.getText('new')}" onclick="weekPlan_OpenDialog(${req.getParameter('projectInfo.id')?if_exists})"/>
</#if>
</@buttonBar>
</@ww.form>
	<script>
		function weekPlan_OpenDialog(id){
		<#if req.getParameter('weekly.id')?exists>
			var url = "${req.contextPath}/workReport/editWeekPlan.html?weekly.id="+id;
		</#if>
		<#if req.getParameter('projectInfo.id')?exists>
			var url = "${req.contextPath}/workReport/editWeekPlan.html?projectInfo.id="+id;
		</#if>
			openNewWindow(url);
		}
		function showWeekPlan(id,selectId){
			var url = "${req.contextPath}/workReport/editWeekPlan.html?weekPlan.id="+id;
			openNewWindow(url);
		}
	</script>
</@framePage >