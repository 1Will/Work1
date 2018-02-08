<#include "../../includes/macros2.ftl" />
<@htmlPage title="库区查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<#include "regionalSearcher.ftl"/>
	 	 <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="newRegional_.html"/>
        </@buttonBar>
        <@titleBar title="库区列表"/>
         <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>库区编码</th>
	  				<th>库区名</th>
	  				<th>仓库</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editRegional_.html">0001</a></td>
	  				<td style="text-align:left">一号库区</td>
	  				<td style="text-align:left">一号仓库</td>
	  			</tr>	  			 		
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editRegional_.html">0002</a></td>
	  				<td style="text-align:left">二号库区</td>
	  				<td style="text-align:left">二号仓库</td>
	  			</tr>	  			 		
	  		</@listTable>
			<@buttonBar>
		        <#assign confirmMessage = "${'您确定失效吗？'}" />
		        <@vsubmit name="'delete'" value="'${'失效'}'"/>
			</@buttonBar>
	 </@ww.form>
</@htmlPage>