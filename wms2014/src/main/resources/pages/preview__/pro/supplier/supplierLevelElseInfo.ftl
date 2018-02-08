 <#include "../../includes/macros2.ftl" />
 <#include "/javascripts/selectAll.js" />
<@framePage title="供应商附加信息">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th>供应商编号</th>
                <th>公司名称</th>
			 	<th>企业所属行业</th>
			 	<th>企业生产经营品种</th>
			 	<th>营业执照内容</th>
			 	<th>供应商的服务质量</th>
			<tr>
			<tr>
				<td style="text-align:left">GYS001</a></td>
				<td>中天</td>
				<td>科技</td>
				<td>中国</td>
				<td>软件</td>
				<td>....</td>
			</tr>
	     	</@listTable>
	     	</table>  
    </@ww.form> 
  </@framePage>