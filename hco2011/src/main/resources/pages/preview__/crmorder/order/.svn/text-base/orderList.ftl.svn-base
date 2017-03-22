<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户订单管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./orderSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newOrder_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>客户</th>
			 	<th>订单类型</th>
			 	<th>订购产品</th>
			 	<th>送货时间</th>
			 	<th>送货方式</th>
			 	<th>联系人</th>
			 	<th>联系电话</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editOrder_.html">bh_001</a></td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">紧急</td>
				<td style="text-align:left">轮胎</td>
				<td style="text-align:left">2010-04-20</td>
				<td style="text-align:left">快递</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">12345678901</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>