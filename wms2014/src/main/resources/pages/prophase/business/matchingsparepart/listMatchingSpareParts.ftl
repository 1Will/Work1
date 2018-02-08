<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@framePage>
	 <@ww.form name="'listForm'" action="'searchMatchingSparePart'" method="'post'">
	 	<@ww.token name="searchMatchingSparePartToken"/>
 	
        <@listTable>	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>	 	         	
			 	<th>图号</th>
			 	<th>名称</th>
			 	<th>规格型号</th>
			 	<th>种类</th>
			 	<th>明细种类</th>
			 	<th>单位</th>
			 	<th>生产厂家</th>
			<tr>
			<tr>
				<td  ><a href="">3201</a></td>
				<td  style="text-align:left">拉延</td>
				<td  style="text-align:left">模具</td>
				<td  style="text-align:left">工具类</td>
				<td  style="text-align:left">销类</td>
				<td  style="text-align:left">型部</td>
				<td  style="text-align:left">总装一厂</td>
			</tr>
			<tr>
				<td  ><a href="">3202</a></td>
				<td  style="text-align:left">拉延</td>
				<td  style="text-align:left">模具</td>
				<td  style="text-align:left">机械类</td>
				<td  style="text-align:left">销类</td>
				<td  style="text-align:left">销部</td>
				<td  style="text-align:left">总装二厂</td>				
			</tr>
	     	</@listTable>
	     	</table>	    
        </@listTable>   		
     </@ww.form>
</@framePage>