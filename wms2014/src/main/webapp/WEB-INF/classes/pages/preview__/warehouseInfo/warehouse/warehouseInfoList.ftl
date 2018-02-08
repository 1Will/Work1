<#include "../../includes/macros2.ftl" />

<@htmlPage title="创库查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<#include "warehouseInfoSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="newWarehouseInfo_.html"/>
        </@buttonBar>
        <@titleBar title="创库列表"/>
         <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>仓库编码</th>
	  				<th>仓库名</th>
	  				<th>仓库负责人</th>
	  				<th>电话</th>				
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editWarehouseInfo_.html">0001</a></td>
	  				<td style="text-align:left">一号创库</td>
	  				<td style="text-align:left">刘伟</td>
	  				<td style="text-align:left">740740740</th>
	  			</tr>	  			 		
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editWarehouseInfo_.html">0002</a></td>
	  				<td style="text-align:left">二号创库</td>
	  				<td style="text-align:left">陈鲍秀</td>
	  				<td style="text-align:left">740740740</th>
	  			</tr>	  			 		
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editWarehouseInfo_.html">0003</a></td>
	  				<td style="text-align:left">三号创库</td>
	  				<td style="text-align:left">章旭敏</td>
	  				<td style="text-align:left">740740740</th>
	  			</tr>	  			 		
	  		</@listTable>
			<@buttonBar>
		        <#assign confirmMessage = "${'您确定失效吗？'}" />
		        <@vsubmit name="'delete'" value="'${'失效'}'"/>
			</@buttonBar>
	 </@ww.form>
</@htmlPage>