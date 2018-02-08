<#include "../../../../includes/eam2008.ftl" />
<@framePage title="工装标定计划查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  value="true"></th>
			 	<th>项目号</th>
			 	<th>点检项目</th>
			 	<th>点检内容</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="open_detailDialog();return false;">1</a></td>
				<td style="text-align:left">刀块</td>
				<td style="text-align:left">刀块工作部位是否有胶污等杂物</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="open_detailDialog();return false;">2</a></td>
				<td style="text-align:left">顶料及送料装置</td>
				<td style="text-align:left">顶料及送料装置是否齐备,动作是否良好</td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
        <@vbutton value="新增" class="button" onclick="open_detailDialog();"/>
     	<@vbutton value="删除" class="button" />
     </@buttonBar>
     </@ww.form>
     <script language="javascript">	        
	      	function open_detailDialog(ruleId, detailId) {
	      		var url = '${req.contextPath}/runmaintenance/tooling/checkpoint/editCheckPointRuleDetail.html';	      		
	      		popupModalDialog(url, 650,350);
	      		self.location.reload();
	      	}
	    </script>
</@framePage>