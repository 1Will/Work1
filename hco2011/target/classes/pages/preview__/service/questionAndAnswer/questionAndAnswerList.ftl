<#include "../../../includes/macros.ftl" />
<@htmlPage title="QA库管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listActive_Token"/>
		<#include "./questionAndAnswerSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newQuestionAndAnswer_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>类型</th>
			 	<th>状态</th>
			 	<th>严重程度</th>
			 	<th>问题</th>
			 	<th>版本号</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editQuestionAndAnswer_.html">bm_001</a></td>
				<td style="text-align:left">系统升级</td>
				<td style="text-align:left">已解决</td>
				<td style="text-align:left">严重</td>
				<td style="text-align:left">系统无法升级</td>
				<td style="text-align:left">1.0.1</td>
			</tr>
			
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>