<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('周列表')}">
<@ww.form name="'listForm'" action="'listWeek'" namespace="'/workReport'" method="'post'">
	 <@ww.token name="'searchWeekToken'"/>
	<#include "./weekSearcher.ftl" />
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	  <@buttonBar>
			<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'checkInvalidParms()'" />
      </@buttonBar>
	 <@list title="" includeParameters="week.id|week.name|checkbox|" fieldMap="like:week.name|" >
	 
		<@vcolumn title="${action.getText('名称')}">
			<@vlh.attribute name="width" value="5%" />
			<a href="javascript: returnDialog(new Array(#{object.id},'${object.name}','${object.startDate}','${object.endDate}'));">
		        ${object.name}
		    </a>
			<@alignCenter/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('开始日期')}" format="yyyy-MM-dd" property="startDate" >
			<@vlh.attribute name="width" value="11%" />
			<@alignCenter/>
		</@vcolumn>
		
		<@vcolumn title="${action.getText('结束日期')}" format="yyyy-MM-dd" property="endDate"  >
			<@vlh.attribute name="width" value="6%" />
			<@alignCenter/>
		</@vcolumn>
	 </@list>
</@ww.form>
<script language="javascript">
function checkInvalidParms() {
  	return true;
	}
</script>
</@htmlPage>