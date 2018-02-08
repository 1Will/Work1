<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@framePage title="清查明细">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
           <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>工装编号</th>
			 	<th>工装图号</th>
			 	<th>工装名称</th>
			 	<th>工装型号</th>
			 	<th>工装规格</th>
			 	<th>工装类别</th>
			 	<th>使用部门</th>
			 	<th>清查结果</th>
			 	<th>处理结果</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="return popUpInventoryDialog();">07010102-02</a></td>
				<td style="text-align:left">07010102-02</td>
				<td style="text-align:left"> 后天窗加强板</td>
				<td style="text-align:left">CSM6105</td>
				<td style="text-align:left">500mm*1500mm</td>
				<td style="text-align:left">工位器具</td>
				<td style="text-align:left">总装一厂</td>
				<td style="text-align:left">已损坏</td>
				<td style="text-align:left">...</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="return popUpInventoryDialog();">53010000-01</a></td>
				<td style="text-align:left">53010000-01</td>
				<td style="text-align:left"> 顶盖（豪华型）</td>
				<td style="text-align:left">01</td>
				<td style="text-align:left">50*80</td>
				<td style="text-align:left">模具</td>
				<td style="text-align:left">总装一厂</td>	
				<td style="text-align:left">已遗失</td>		
				<td style="text-align:left">...</td>		
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<input type="button" value="新建" class="button" onclick=""/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     	</@buttonBar>
	     	
	     	<script language="javascript">
	     		function popUpInventoryDialog() {
	     			popupModalDialog('${req.contextPath}/preview/tooling/inventory/editToolingInventoryDetails.html',750,400);
	     			return false;
	     		}
	     	</script>
     </@ww.form>
</@framePage>