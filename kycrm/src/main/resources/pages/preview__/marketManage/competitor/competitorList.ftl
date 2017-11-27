<#include "../../../includes/macros.ftl" />
<@htmlPage title="竞争对手管理">
	<@ww.form name="'listForm'" action="''" method="'post'">
		<@ww.token name="listPlanning_Token"/>
		<#include "./competitorSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="newCompetitor_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>编码</th>
			 	<th>竞争公司名称</th>
			 	<th>行业</th>
			 	<th>性质</th>
			 	<th>网址</th>
			 	<th>企业规模</th>
			 	<th>主要经营</th>
			 	<th>目标市场</th>
			 	<th>竞争客户名称</th>
			 	<th>竞争能力</th>
			 	<th>竞争产品</th>
			</tr>     
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompetitor_.html">bh_001</a></td>
				<td style="text-align:left">江淮汽车</td>
				<td style="text-align:left">制造</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">www.jac.cn</td>
				<td style="text-align:left">500人以上</td>
				<td style="text-align:left">汽车</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">AAAA</td>
				<td style="text-align:left">弱</td>
				<td style="text-align:left">CRM</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompetitor_.html">bh_002</a></td>
				<td style="text-align:left">江淮汽车</td>
				<td style="text-align:left">制造</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">www.jac.cn</td>
				<td style="text-align:left">500人以上</td>
				<td style="text-align:left">汽车</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">AAAA</td>
				<td style="text-align:left">弱</td>
				<td style="text-align:left">CRM</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompetitor_.html">bh_003</a></td>
				<td style="text-align:left">江淮汽车</td>
				<td style="text-align:left">制造</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">www.jac.cn</td>
				<td style="text-align:left">500人以上</td>
				<td style="text-align:left">汽车</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">AAAA</td>
				<td style="text-align:left">弱</td>
				<td style="text-align:left">CRM</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompetitor_.html">bh_004</a></td>
				<td style="text-align:left">江淮汽车</td>
				<td style="text-align:left">制造</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">www.jac.cn</td>
				<td style="text-align:left">500人以上</td>
				<td style="text-align:left">汽车</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">AAAA</td>
				<td style="text-align:left">弱</td>
				<td style="text-align:left">CRM</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCompetitor_.html">bh_005</a></td>
				<td style="text-align:left">江淮汽车</td>
				<td style="text-align:left">制造</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">www.jac.cn</td>
				<td style="text-align:left">500人以上</td>
				<td style="text-align:left">汽车</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">AAAA</td>
				<td style="text-align:left">弱</td>
				<td style="text-align:left">CRM</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>