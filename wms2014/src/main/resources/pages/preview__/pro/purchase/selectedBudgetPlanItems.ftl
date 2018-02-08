<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="所选预算单">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  onClick="selectedAll()" value="true"></th>
			 	<th>预算号</th>
			 	<th>预算名称</th>
			 	<th>预算制定人</th>
			 	<th>预算制定日期</th>
			 	<th>预算实施部门</th>
			 	<th>预算说明</th>
			 	<th>预算状况</th>
			 	<th>预算审核</th>
			 	<th>预算费用</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td><a href="listBudgetBillProfile.html">08072001</a></td>
				<td>耗材添加</td>
				<td>System</td>
				<td>2008-8-23</td>
				<td>乘务车1部</td>
				<td>购买耗材</td>
				<td>制定完成</td>
				<td>已通过</td>	
				<td>200000</td>	
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td><a href="listBudgetBillProfile.html">08072001</a></td>
				<td>办公材料</td>
				<td>System</td>
				<td>2008-8-23</td>
				<td>乘务车3部</td>
				<td>办公用品</td>
				<td>制定完成</td>
				<td>未通过</td>	
				<td>80000</td>	
			</tr>

	     	</@listTable>
	     <@buttonBar>																
       		   <@redirectButton value="审核" url="#"/>	
        	   <@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
        </@buttonBar>
	     	</table>

     </@ww.form>
</@htmlPage>