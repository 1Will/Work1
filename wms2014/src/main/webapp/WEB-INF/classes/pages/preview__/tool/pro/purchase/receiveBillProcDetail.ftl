<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="验收详细列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
              <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>工装编号</th>
			 	<th>工装图号</th>
			 	<th>工装名称</th>
			 	<th>使用设备</th>
			 	<th>验收内容</th>
				<th>验收结果</th>
				<th>备注</th>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#"  onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillProcDetail.html',600,300); return false;">JH-021</a></td>
				<td style="text-align:left">00012312</td>
				<td style="text-align:left">45688845</td>
				<td style="text-align:left">拉延模</td>
				<td style="text-align:left">机床</td>
				<td style="text-align:left">开箱</td>
				<td style="text-align:left">包装完好</td>
				<td style="text-align:left">.......</td>
			</tr>		
	     	</@listTable>
	     	</table>	    
	     	<@buttonBar>
	    		<@vbutton class="button" value="${action.getText('new')}" onclick="popupModalDialog('${req.contextPath}/popup/editReceiveBillProcDetail.html',600,300)"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  	
     </@ww.form>
</@framePage>