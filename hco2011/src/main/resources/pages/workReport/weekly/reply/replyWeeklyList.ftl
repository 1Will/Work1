<#include "../../../includes/hco2011.ftl" />
<@framePage>
<@ww.form name="'listForm'" action="'saveReplyWeekly'" namespace="'/workReport'" method="'post'">
	 <@ww.token name="'replyWeeklyManager'"/>
	 <#if req.getParameter('weekly.id')?exists>
     <@ww.hidden name="'weekly.id'" value="${req.getParameter('weekly.id')?if_exists}"/>
     </#if>
	 <@list title="" includeParameters="" fieldMap="" >
	        <@vcolumn title="${action.getText('回复人')}">
	        <@vlh.attribute name="width" value="8%" />
	        	${object.user.name?if_exists}
	        	<@alignCenter/>
	        </@vcolumn>
	        
	        <@vcolumn title="${action.getText('回复时间')}" >
            	<@vlh.attribute name="width" value="10%" />
            	${object.replydate?if_exists}
            	<@alignCenter/>
         	</@vcolumn>
         	
         	<@vcolumn title="${action.getText('回复内容')}" >
         	    <@vlh.attribute name="width" value="60%" />
         	    ${object.content?if_exists}
         	<@alignLeft/>
         	</@vcolumn>
         	
        </@list>
        <#--
        <#if req.getParameter('daily.id')?exists>
        <tr><td>
        <textarea id="daily.content" name="daily.content" value="" rows="3" cols="220" style="width:100%;height:50px;"></textarea>
        </td></tr>
        <@buttonBar>
	            <@vsubmit name="'reply'" value="'${action.getText('回复')}'" onclick="'return submitt();'" />
		</@buttonBar>
		</#if>
        -->
</@ww.form>
<script language="javascript">
function submitt(){
	if(''== getObjByName('daily.content').value){
		alert('请输入回复内容！');
		getObjByName('daily.content').focus();
        return false;
	}
	return true;
}
</script>
</@framePage>