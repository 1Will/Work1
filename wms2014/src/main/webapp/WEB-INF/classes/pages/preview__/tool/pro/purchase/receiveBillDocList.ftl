<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="验收单附加文档列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>文档名称</th>
				<th>文档描述</th>
				<th>文档</th>
				<th>所属设备</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillDoc.html',600,300); return false;">JH-021</a></td>
				<td style="text-align:left">轴承TX使用手册</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left"><a href="javascript:void(0)">轴承TX使用手册</a></td>	
				<td style="text-align:left">轴承</td>				
			</tr>		
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}"  onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillDoc.html',600,300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>