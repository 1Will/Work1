<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户需求管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./requirementSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newRequirement_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>联系日期</th>
			 	<th>标题</th>
			 	<th>客户</th>
			 	<th>提供者</th>
			 	<th>重要性</th>
			 	<th>类型</th>
			 	<th>业务员</th>
			 	<th>负责人</th>
			 	<th>客户联系人</th>
			 	<th>客户电话</th>
			 	<th>状态</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRequirement_.html">bh_001</a></td>
				<td style="text-align:left">2010-04-20</td>
				<td style="text-align:left">客户需求</td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">一般</td>
				<td style="text-align:left">私营</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">章旭敏</td>
				<td style="text-align:left">张明峰</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left">紧急</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>