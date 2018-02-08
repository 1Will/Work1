 <#include "../../../../includes/eam2008.ftl" />
 <#include "/javascripts/selectAll.js" />
	<@framePage title="${action.getText('')}"> 
	<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">        
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
                <th>点检部位</th>
			 	<th>点检内容</th>
			 	<th>点检标准</th>
			 	<th>点检方法</th>
			 	<th>点检要求</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300); return false;">1</a></td>
				<td style="text-align:left">电器数控按钮</td>	
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>				
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300);return false;">2</a></td>
				<td style="text-align:left">机械传动料道</td>		
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>						
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300);return false;">3</a></td>
				<td style="text-align:left">液压气动压力</td>		
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>						
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300);return false;">4</a></td>
				<td style="text-align:left">润滑油位渗漏</td>		
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>						
			</tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td><a href="#" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300);return false;">5</a></td>
				<td style="text-align:left">温升异音、防护装置</td>	
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>
				<td style="text-align:left">...</td>							
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	    		<input type="button" name="new"value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/popup/editCheckPointRuleItem.html',600,300);"/>
	    		<@vbutton name="delete" class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>  
	    </@ww.form>
	    <script language="javascript" type="text/JavaScript">
	      var item = '${req.getParameter('item')?if_exists}';
		  var view = '${req.getParameter('view')?if_exists}';
		  if (item==1) {
		    __disableAllElements__(document.forms[0], new Array(""));
		  }
	    </script>
	</@framePage>