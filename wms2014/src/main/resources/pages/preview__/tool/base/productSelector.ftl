<#include "../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="产品选择列表">

	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 		 <@inputTable>
	 		 	<tr>
	 		 		<@ww.textfield label="'产品编号'" name="'area.code'" value="" cssClass="'underline'" />
	 		 		<@ww.textfield label="'产品名称'" name="'area.code'" value="" cssClass="'underline'" />
	 		 	</tr>
	 		 </@inputTable>
	 		<@buttonBar>
        		<@redirectButton value="${action.getText('search')}" url="#"/>
        	</@buttonBar>
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th>选择备件</th>
			 	<th>产品编号</th>
			 	<th>产品名称</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521154654</td>
				<td style="text-align:left">后横梁下板</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">200521158888</td>
				<td style="text-align:left">内板</td>
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