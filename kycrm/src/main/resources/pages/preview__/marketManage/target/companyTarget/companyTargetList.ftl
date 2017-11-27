<#include "../../../../includes/macros.ftl" />
<@htmlPage title="公司目标管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listActive_Token"/>
		<#include "./companyTargetSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newCompanyTarget_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编号</th>
			 	<th>目标名称</th>
			 	<th>地区</th>
			 	<th>产品</th>
			 	<th>月份</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompanyTarget_.html">bh_001</a></td>
				<td style="text-align:left">卖出2套</td>
				<td style="text-align:left">合肥</td>
				<td style="text-align:left">crm</td>
				<td >2月份</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompanyTarget_.html">bh_002</a></td>
				<td style="text-align:left">卖出2套</td>
				<td style="text-align:left">合肥</td>
				<td style="text-align:left">crm</td>
				<td >2月份</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompanyTarget_.html">bh_003</a></td>
				<td style="text-align:left">卖出2套</td>
				<td style="text-align:left">合肥</td>
				<td style="text-align:left">crm</td>
				<td >2月份</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompanyTarget_.html">bh_004</a></td>
				<td style="text-align:left">卖出2套</td>
				<td style="text-align:left">合肥</td>
				<td style="text-align:left">crm</td>
				<td >2月份</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompanyTarget_.html">bh_005</a></td>
				<td style="text-align:left">卖出2套</td>
				<td style="text-align:left">合肥</td>
				<td style="text-align:left">crm</td>
				<td >2月份</td>
			</tr>

        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>