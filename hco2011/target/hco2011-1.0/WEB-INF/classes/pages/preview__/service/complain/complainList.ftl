<#include "../../../includes/macros.ftl" />
<@htmlPage title="客户投诉管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listActive_Token"/>
		<#include "./complainSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newComplain_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>投诉主题</th>
			 	<th>客户</th>
			 	<th>服务人员</th>
			 	<th>联系人</th>
			 	<th>电话</th>
			 	<th>投诉类型</th>
			 	<th>紧急程度</th>
			 	<th>开始日期</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editComplain_.html">bm_001</a></td>
				<td style="text-align:left">EAM系统太慢</td>
				<td style="text-align:left">JAC</td>
				<td style="text-align:left">陈鲍秀</td>
				<td style="text-align:left">刘伟</td>
				<td style="text-align:left">12345678901</td>
				<td style="text-align:left">类型一</td>
				<td style="text-align:left">一般</td>
				<td >2010-04-20</td>
			</tr>
			
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>