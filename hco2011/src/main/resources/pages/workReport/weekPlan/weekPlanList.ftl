<#include "../../includes/hco2011.ftl" />
<@framePage >
	<@ww.form  name="'listFrom'" action="searchWeekPlan" namespace="'/workReport'" method="'post'" >
	<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	<@ww.hidden name="'week.id'" value="'${req.getParameter('week.id')?if_exists}'"/>
	<#assign number=1/>
	<#assign lastp='lastp'>
	<#assign thisp='thisp'>
	<#assign nextp='nextp'>
    <@ww.token name="searchWeekPlanToken"/>
   	<@list title="" includeParameters="id|projectInfo.name|weekly.code|lastPlan|thisPlan|nextPlan|onlyInvalid|onlyValid|projectInfo.id|weekly.id|week.id|" fieldMap="" >
        <#if !(action.isReadOnly())>
            <@vlh.checkbox property="id" name="weekPlanIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
        </#if>
        
        <@vcolumn title="${action.getText('序号')}" sortable="asc" property="id">
        <@vlh.attribute name="width" value="4%" />
            <a href="#" onclick="showWeekPlan('#{object.id}')">${number}</a>
            <#assign number=number+1 />
            <@alignLeft/>
        </@vcolumn>
        
        <#if req.getParameter('weekly.id')?exists>
        <@vcolumn title="${action.getText('项目名称')}" sortable="asc" property="projectInfo.name">
            <@alignLeft />
        </@vcolumn>
        </#if>
        
        <#if req.getParameter('projectInfo.id')?exists>
        <@vcolumn title="${action.getText('周名称')}" sortable="asc" property="week.name">
            <@alignLeft />
        </@vcolumn>
        </#if>
        
        
        <@vcolumn title="${action.getText('计划人')}" sortable="asc" property="user.name">
            <@alignLeft attributes="width:60;"/>
        </@vcolumn>
        
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
            <@alignLeft attributes="width:280;"/>
        </@vcolumn>
        
        <@vcolumn title="${action.getText('本周实绩')}" sortable="asc" property="thisPlan">
         <#assign thisp=thisp +'b'>
	     <@ww.hidden name="'${thisp}'" value="'${object.thisPlan?if_exists}'"/>
	        <span title="${object.thisPlan?if_exists}">
	            <script>
	            	var s = getObjByName('${thisp}').value;
	            	s=s.replace(/[\r\n]/g, "");
	            	document.write(s.slice(0,18)+"...");
	            </script>
	        </span>
            <@alignLeft attributes="width:280;"/>
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
            <@alignLeft attributes="width:280;"/>
        </@vcolumn>
    </@list>
<@buttonBar>
	<#if req.getParameter('weekly.id')?exists>
		<@vbutton class="button" value="${action.getText('new')}" onclick="weekPlan_OpenDialog(${req.getParameter('weekly.id')},${req.getParameter('week.id')})"/>
	</#if>
	<#if req.getParameter('projectInfo.id')?exists>
		<@vbutton class="button" value="${action.getText('new')}" onclick="weekPlan_OpenDialog(${req.getParameter('projectInfo.id')?if_exists},0)"/>
	</#if>

	<#assign confirmMessage = "${action.getText('delete.msg')}${action.getText('项目周计划')}?" />
	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	    <@ww.param name="'onclick'" value="'return confirmDeletes(\"weekPlanIds\", \"${confirmMessage}\");'"/>
	    <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	</@vsubmit>
</@buttonBar>
</@ww.form>
	<script>
		function weekPlan_OpenDialog(id,weekId){
		<#if req.getParameter('weekly.id')?exists>
			var url = "${req.contextPath}/workReport/editWeekPlan.html?weekly.id="+id+"&week.id="+weekId;
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