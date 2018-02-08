<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="备件选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 		 <@inputTable>
	 		 	<tr>
	 		 		<@ww.textfield label="'备件编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		 		<@ww.textfield label="'备件名称'" name="'area.code'" value="" cssClass="'underline'" />
	 		 	</tr>
	 		 </@inputTable>
	 		<@buttonBar>
        		<@redirectButton value="${action.getText('search')}" url="#"/>
        	</@buttonBar>
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择备件</th>
			 	<th>备件编号</th>
			 	<th>备件名称</th>
			 	<th>备件型号</th>
				<th>备件规格</th>
				<th>备件类别</th>
				<th>库存量(件)</th>
				<th>记录创建时间</th>	
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">斧头</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">200m</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td>2007-07-12</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">斧头</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">200m</td>
				<td style="text-align:left">A</td>
				<td style="text-align:right">10</td>
				<td>2007-07-12</td>
			</tr>
			<tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>