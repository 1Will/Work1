<#include "../../../includes/macros.ftl" />
<@htmlPage title="市场情报管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./intelligenceSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newIntelligence_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>主题</th>
			 	<th>日期</th>
			 	<th>相关客户</th>
			 	<th>业务员</th>
			 	<th>对方联系人</th>
			 	<th>联系电话</th>
			 	<th>内容</th>
			 	<th>情报重要性</th>
			 	<th>状态</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editIntelligence_.html">crm销售</a></td>
				<td >2010-04-20</td>
				<td style="text-align:left">aaa</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">重要</td>
				<td style="text-align:left"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editIntelligence_.html">crm销售</a></td>
				<td >2010-04-20</td>
				<td style="text-align:left">aaa</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">重要</td>
				<td style="text-align:left"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editIntelligence_.html">crm销售</a></td>
				<td >2010-04-20</td>
				<td style="text-align:left">aaa</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">重要</td>
				<td style="text-align:left"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editIntelligence_.html">crm销售</a></td>
				<td >2010-04-20</td>
				<td style="text-align:left">aaa</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">重要</td>
				<td style="text-align:left"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editIntelligence_.html">crm销售</a></td>
				<td >2010-04-20</td>
				<td style="text-align:left">aaa</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">重要</td>
				<td style="text-align:left"></td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>