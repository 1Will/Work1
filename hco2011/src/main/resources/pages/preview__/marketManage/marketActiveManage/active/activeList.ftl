<#include "../../../../includes/macros.ftl" />
<@htmlPage title="市场活动管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listActive_Token"/>
		<#include "./activeSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newActivity_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>主题</th>
			 	<th>对象客户</th>
			 	<th>活动目的</th>
			 	<th>开始时间</th>
			 	<th>结束时间</th>
			 	<th>地点</th>
			 	<th>活动类型</th>
			 	<th>进展情况</th>
			 	<th>优先级别</th>
			 	<th>负责人</th>
			 	<th>状态</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editActivity_.html">世博会</a></td>
				<td style="text-align:left">游客</td>
				<td style="text-align:left">介绍本公司产品</td>
				<td >2010-04-20</td>
				<td >2010-04-21</td>
				<td style="text-align:left">上海</td>
				<td style="text-align:left">重要活动</td>
				<td style="text-align:left">准备阶段</td>
				<td style="text-align:left">A级别</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">计划中</td>
				
			</tr>
			
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>