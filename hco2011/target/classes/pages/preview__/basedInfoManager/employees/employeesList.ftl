<#include "../../../includes/macros.ftl" />
<@htmlPage title="员工管理">
	<@ww.form name="'listForm'" action="'searchEmployees_'" method="'post'">
		<@ww.token name="searchCityToken"/>
		<#include "./employeesSearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/employees/saveEmployees_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>工号</th>
			 	<th>姓名</th>
			 	<th>性别</th>
			 	<th>公司</th>
			 	<th>部门</th>
			 	<th>职务</th>
			 	<th>手机</th>
			 	<th>是否系统用户</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editEmployees_.html">YJ-YF-001</a></td>
				<td style="text-align:left">李飞鸿</td>
				<td style="text-align:left">男</td>
				<td style="text-align:left">永君(中国)有限公司合肥分公司</td>
				<td style="text-align:left">软件研发部</td>
				<td style="text-align:left">ORACLE DBA</td>
				<td style="text-align:left">13865809272</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editEmployees_.html">YJ-WL-001</a></td>
				<td style="text-align:left">黄广凤</td>
				<td style="text-align:left">女</td>
				<td style="text-align:left">永君(中国)有限公司南京分公司</td>
				<td style="text-align:left">网络事业部</td>
				<td style="text-align:left">配置组组长</td>
				<td style="text-align:left">13865809274</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editEmployees_.html">YJ-JC-001</a></td>
				<td style="text-align:left">张飞侠</td>
				<td style="text-align:left">男</td>
				<td style="text-align:left">永君(中国)有限公司大连分公司</td>
				<td style="text-align:left">系统集成部</td>
				<td style="text-align:left">部门经理</td>
				<td style="text-align:left">13865809275</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editEmployees_.html">YJ-AF-001</a></td>
				<td style="text-align:left">毛一行</td>
				<td style="text-align:left">男</td>
				<td style="text-align:left">永君(中国)有限公司上海分公司</td>
				<td style="text-align:left">电子安防部</td>
				<td style="text-align:left">项目组组长</td>
				<td style="text-align:left">13865809276</td>
				<td style="text-align:left">是</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editEmployees_.html">YJ-YF-002</a></td>
				<td style="text-align:left">王二飞</td>
				<td style="text-align:left">男</td>
				<td style="text-align:left">永君(中国)有限公司合肥分公司</td>
				<td style="text-align:left">软件研发部</td>
				<td style="text-align:left">程序员</td>
				<td style="text-align:left">13865809277</td>
				<td style="text-align:left">否</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>