<#include "../../../includes/macros.ftl" />
<@htmlPage title="退货管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./refundSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newRefund_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>客户</th>
			 	<th>销售订单</th>
			 	<th>退货时间</th>
			 	<th>退货产品</th>
			 	<th>退货金额</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRefund_.html">bh_001</a></td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">关于购买EAM</td>
				<td style="text-align:left">2010-04-20</td>
				<td style="text-align:left">EAM</td>
				<td style="text-align:left">20000</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>