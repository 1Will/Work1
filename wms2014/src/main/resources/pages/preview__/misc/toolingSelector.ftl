<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 		 <@inputTable>
	 		 	<tr>
	 		 		<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		 		<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" />
	 		 	</tr>
	 		 </@inputTable>
	 		<@buttonBar>
        		<@redirectButton value="${action.getText('search')}" url="#"/>
        	</@buttonBar>
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择工装</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
				<th>备注</th>	
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">斧头</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">.......</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left"钳子</td>
				<td style="text-align:left">TX-32</td>
				<td style="text-align:left">.......</td>
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