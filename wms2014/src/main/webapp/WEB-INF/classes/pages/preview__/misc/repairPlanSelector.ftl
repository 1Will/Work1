<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修计划选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 		 	
	 		 <@inputTable>
	 		 	<tr>
	 		 		<@ww.textfield label="'维修计划编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		 		<@ww.textfield label="'维修计划名称'" name="'area.code'" value="" cssClass="'underline'" />
	 		 	</tr>
	 		 </@inputTable>
	 		<@buttonBar>
        		<@redirectButton value="${action.getText('search')}" url="#"/>
        	</@buttonBar>
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择维修计划</th>
			 	<th>维修计划编号</th>
			 	<th>维修计划名称</th>
			 	<th>维修内容摘要</th>
			 	<th>创建人</th>
			 	<th>创建时间</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">机床修理</td>
				<td style="text-align:left">.......</td>
				<td style="text-align:left">TOM</td>
				<td>2007-08-01</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154645</td>
				<td style="text-align:left">螺杆修理</td>
				<td style="text-align:left">........</td>
				<td style="text-align:left">JIM</td>
				<td>2007-08-01</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200045678787</td>
				<td style="text-align:left">电机修理</td>
				<td style="text-align:left">.......</td>
				<td style="text-align:left">Green</td>
				<td>2007-08-01</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="选择" onclick="javascript:window.close()"/>	
	     		<@vbutton class="button" value="关闭" onclick="javascript:window.close()"/>	
	     	</@buttonBar>
     </@ww.form>
</@htmlPage>