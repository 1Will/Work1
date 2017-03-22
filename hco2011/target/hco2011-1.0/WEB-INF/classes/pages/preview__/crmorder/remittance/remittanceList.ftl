<#include "../../../includes/macros.ftl" />
<@htmlPage title="汇款管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./remittanceSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newRemittance_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>日期</th>
			 	<th>类型</th>
			 	<th>汇款客户</th>
			 	<th>经办人员</th>
			 	<th>客户订单</th>
			 	<th>是否开票</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRemittance_.html">bh_001</a></td>
				<td style="text-align:left">2010-04-20</td>
				<td style="text-align:left">普通汇款</td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">关于购买EAM</td>
				<td style="text-align:left">是</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>