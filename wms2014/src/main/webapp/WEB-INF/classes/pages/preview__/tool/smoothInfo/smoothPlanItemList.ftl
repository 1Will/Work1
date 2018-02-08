<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="润滑计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            	<@listTable>
            		<tr>
            			<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	            		<th>项目号</th>
		  				<th>润滑标准</th>
		  				<th>润滑部位</th>
		  				<th>润滑材质</th>
		  				<th>润滑计量</th>
		  				<th>润滑负责人</th>
            		</tr>
            		<tr>
            			<td><input type="checkbox" name="checkbox" value="true"></td>
            			<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothPlanItem.html', 600, 300); return false;">JAC-RH-02</a></td>
		  				<td style="text-align:left">BZ05</td>
		  				<td>...</td>
		  				<td>...</td>
		  				<td>...</td>
		  				<td>...</td>
            		</tr>
            		<tr>
            			<td><input type="checkbox" name="checkbox" value="true"></td>
            			<td style="text-align:left"><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothPlanItem.html', 600, 300); return false;">JAC-RH-05</a></td>
		  				<td style="text-align:left">BZ08</td>
		  				<td>...</td>
		  				<td>...</td>
		  				<td>...</td>
		  				<td>...</td>
            		</tr>
            	</@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editSmoothPlanItem.html',600,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  
     </@ww.form>
</@framePage>