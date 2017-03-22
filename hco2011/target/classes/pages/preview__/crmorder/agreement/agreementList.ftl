<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户合同管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./agreementSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newAgreement_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>名称</th>
			 	<th>客户</th>
			 	<th>签署时间</th>
			 	<th>客户签约人</th>
			 	<th>我方签约人</th>
			 	<th>对方联系电话</th>
			 	<th>签署产品</th>
			 	<th>合同类型</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editAgreement_.html">bh_001</a></td>
				<td style="text-align:left">EAM系统开发授权协议</td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">2010-04-20</td>
				<td style="text-align:left">章旭敏</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left">EAM</td>
				<td style="text-align:left">买卖合同</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>