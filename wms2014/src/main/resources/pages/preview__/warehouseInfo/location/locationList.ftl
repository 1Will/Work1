<#include "../../includes/macros2.ftl" />
<@htmlPage title="库位查询">
	<@ww.form name="'listForm'" action="" method="'post'">
	<#include "locationSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="newLocation_.html"/>
        </@buttonBar>
      <@titleBar title="库位列表"/>
       <@listTable>
   	  		<tr>
  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
  				<th>库位编码</th>
  				<th>仓库</th>
  				<th>库区</th>
  				<th>库位类型</th>
  				<th>承载类型</th>
  				<th>最大承重量</th>
  				<th>状态</th>
  			</tr>
  			<tr>
  				<td><input type="checkbox" name="checkbox" value="true"></td>
  				<td style="text-align:left"><a href="editLocation_.html">0001</a></td>
  				<td style="text-align:left">一号仓库</td>
  				<td style="text-align:left">收货库区</td>
  				<td style="text-align:left">收货</td>
  				<td style="text-align:left">平库</td>
  				<td style="text-align:left">100</td>
  				<td style="text-align:left">未用</td>
  			</tr>	  			 		
  			<tr>
  				<td><input type="checkbox" name="checkbox" value="true"></td>
  				<td style="text-align:left"><a href="editLocation_.html">0002</a></td>
  				<td style="text-align:left">一号仓库</td>
  				<td style="text-align:left">收货库区</td>
  				<td style="text-align:left">收货</td>
  				<td style="text-align:left">平库</td>
  				<td style="text-align:left">100</td>
  				<td style="text-align:left">未用</td>
  			</tr>	  			 		
  			<tr>
  				<td><input type="checkbox" name="checkbox" value="true"></td>
  				<td style="text-align:left"><a href="editLocation_.html">0003</a></td>
  				<td style="text-align:left">一号仓库</td>
  				<td style="text-align:left">收货库区</td>
  				<td style="text-align:left">收货</td>
  				<td style="text-align:left">平库</td>
  				<td style="text-align:left">100</td>
  				<td style="text-align:left">未用</td>
  			</tr>	  			 		
  			<tr>
  				<td><input type="checkbox" name="checkbox" value="true"></td>
  				<td style="text-align:left"><a href="editLocation_.html">0004</a></td>
  				<td style="text-align:left">一号仓库</td>
  				<td style="text-align:left">收货库区</td>
  				<td style="text-align:left">收货</td>
  				<td style="text-align:left">平库</td>
   				<td style="text-align:left">100</td>
  				<td style="text-align:left">未用</td>
  			</tr>	  			 		
  			<tr>
  				<td><input type="checkbox" name="checkbox" value="true"></td>
  				<td style="text-align:left"><a href="editLocation_.html">0005</a></td>
  				<td style="text-align:left">一号仓库</td>
  				<td style="text-align:left">收货库区</td>
  				<td style="text-align:left">收货</td>
  				<td style="text-align:left">平库</td>
  				<td style="text-align:left">100</td>
  				<td style="text-align:left">未用</td>
  			</tr>	  			 		
       </@listTable>
   		<@buttonBar>
	        <#assign confirmMessage = "${'您确定失效吗？'}" />
	        <@vsubmit name="'delete'" value="'${'失效'}'"/>
		</@buttonBar>
     </@ww.form>
</@htmlPage>