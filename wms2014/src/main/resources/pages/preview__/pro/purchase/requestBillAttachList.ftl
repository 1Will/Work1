<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="申购单附件列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>附件名称</th>
				<th>附件描述</th>
				<th>附件</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editRequestBillAttach.html', 700, 300); return false;">JH-021</a></td>
				<td style="text-align:left">轴承</td>
				<td style="text-align:left">TX-32图纸参数</td>
				<td style="text-align:left"><a href="javascript:void(0)">图纸参数手册</a></td>				
				<td style="text-align:left">...</td>
			</tr>			
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editRequestBillAttach.html', 700, 300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>