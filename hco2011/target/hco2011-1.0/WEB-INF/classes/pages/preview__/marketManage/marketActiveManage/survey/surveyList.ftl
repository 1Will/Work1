<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场活动管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listActive_Token"/>
		<#include "./surveySearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newSurvey_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>业务编号</th>
			 	<th>调查主题</th>
			 	<th>调查时间</th>
			 	<th>调查对象</th>
			 	<th>调查内容</th>
			 	<th>客户反馈</th>
			 	<th>负责人</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSurvey_.html">bh_001</a></td>
				<td style="text-align:left">关于CRM</td>
				<td >2010-04-20</td>
				<td style="text-align:left">使用客户</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">很好</td>
				<td style="text-align:left">陈鲍秀</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSurvey_.html">bh_002</a></td>
				<td style="text-align:left">关于CRM</td>
				<td >2010-04-20</td>
				<td style="text-align:left">使用客户</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">很好</td>
				<td style="text-align:left">陈鲍秀</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSurvey_.html">bh_003</a></td>
				<td style="text-align:left">关于CRM</td>
				<td >2010-04-20</td>
				<td style="text-align:left">使用客户</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">很好</td>
				<td style="text-align:left">陈鲍秀</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSurvey_.html">bh_004</a></td>
				<td style="text-align:left">关于CRM</td>
				<td >2010-04-20</td>
				<td style="text-align:left">使用客户</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">很好</td>
				<td style="text-align:left">陈鲍秀</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSurvey_.html">bh_005</a></td>
				<td style="text-align:left">关于CRM</td>
				<td >2010-04-20</td>
				<td style="text-align:left">使用客户</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">很好</td>
				<td style="text-align:left">陈鲍秀</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>