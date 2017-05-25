<#include "../../../includes/hco2011.ftl" />
<@framePage>
<@ww.form name="'listForm'" action="'saveReplyDaily'" namespace="'/workReport'" method="'post'">
	 <@ww.token name="'replyDailyManager'"/>
	 <#if req.getParameter('daily.id')?exists>
     <@ww.hidden name="'daily.id'" value="${req.getParameter('daily.id')?if_exists}"/>
     </#if>
	 <#if req.getParameter('backVisit.id')?exists>
     <@ww.hidden name="'backVisit.id'" value="${req.getParameter('backVisit.id')?if_exists}"/>
     </#if>
	 <@list title="" includeParameters="" fieldMap="" >
	        <@vcolumn title="${action.getText('回复人')}">
	        <@vlh.attribute name="width" value="8%" />
	        	${object.userName?if_exists}
	        	<@alignCenter/>
	        </@vcolumn>
	        
	        <@vcolumn title="${action.getText('回复时间')}" >
            	<@vlh.attribute name="width" value="10%" />
            	${object.replyDate?if_exists}
            	<@alignCenter/>
         	</@vcolumn>
         	
         	<@vcolumn title="${action.getText('回复内容')}" >
         	    <@vlh.attribute name="width" value="60%" />
         	    ${object.content?if_exists}
         	<@alignLeft/>
         	</@vcolumn>
         	
        </@list>
        <#if req.getParameter('daily.id')?exists>
        <tr><td>
        <textarea id="daily.content" name="daily.content" value="" rows="3" cols="220" style="width:100%;height:50px;"></textarea>
        </td></tr>
        <@buttonBar>
	            <@vsubmit name="'reply'" value="'${action.getText('回复')}'" onclick="'return submitt();'" />
		</@buttonBar>
		</#if>
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