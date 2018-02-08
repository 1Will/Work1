<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="历史采购单">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">    
             <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>采购单编号</th>
			 	<th>采购单名称</th>
			 	<th>预算编号</th>
			 	<th>采购计划编号</th>
			 	<th>申购单编号</th>
			 	<th>采购时间</th>
			 	<th>供应商</th>
			 	<th>采购数量</th>
			 	<th>采购总金额(元)</th>
			 	<th>状态</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editPurchaseBill.html">JH-CG-08072001</a></td>
				<td>采购轴承</td>
				<td style="text-align:left">JH-YS-08072001</td>
				<td style="text-align:left">JH-CGJH-08072001</td>
				<td style="text-align:left">JH-SG-08072001</td>
				<td>2007-09-22</td>
				<td style="text-align:left">TCL</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">10000</td>
				<td style="text-align:left">提交</td>
			</tr>			
	     	</@listTable>
	     	</table>
	  <@buttonBar>
     	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
     </@buttonBar>
      </@ww.form>
</@htmlPage>