<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="${action.getText('extInfo.maintain')}">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
       <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th>产品编号</th>
              <th>采购单号</th>
              <th>验收单号</th>
              <th>采购时间</th>
              <th>验收时间</th>
              <th>产品名称</th>
              <th>数量</th>
              <th>金额(元)</th>
			 <th>评价</th>
			
			<tr>
			<tr>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/listSupplierAccount.html',600,300);return false;">CP001</a></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/listSupplierPurchase.html',600,300);return false;">CG001</a></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/pro/supplier/listSupplierReceive.html',600,300);return false;">YS001</a></td>
				<td >2007-01-05</td>
				<td >2007-01-06</td>
				<td style="text-align:left">产品名称</td>
			    <td style="text-align:right">100</td>
				<td style="text-align:right">100,000</td>
				<td style="text-align:left">.......</td>
				
			</tr>
	     	</@listTable>
	     	</table>   
	     	 </@ww.form> 	
</@framePage>