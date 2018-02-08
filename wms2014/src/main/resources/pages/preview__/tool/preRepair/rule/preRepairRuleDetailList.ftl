<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修标准明细列表">
	 <@ww.form name="'listForm'" action="" method="'post'">
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>项目号</th>
	  				<th>维修部位</th>
	  				<th>维修内容</th>
	  				<th>工序</th>
	  				<th>维修辅材</th>
	  				<th>维修方案</th>
	  				<th>维修费用</th>
	  				<th>备注</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">前侧车门外板总成（左）</th>
	  				<td style="text-align:left">外板焊接口打磨</th>
	  				<td style="text-align:left">1/5</td>
	  				<td style="text-align:left">交齿打磨机</td>
	  				<td style="text-align:left">……</td>
	  				<td style="text-align:left">150</td>
	  				<td>……</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">散热器支架总成工位器具切换台</th>
	  				<td style="text-align:left">切换台线路</th>
	  				<td style="text-align:left">2/3</td>
	  				<td style="text-align:left">电路器材</td>
	  				<td style="text-align:left">改装线路</td>
	  				<td style="text-align:left">2600</td>
	  				<td>……</td>
	  			</tr>	  		
	  		</@listTable>
        </table>  
        <@buttonBar>
        	<@vbutton value="新建" class="button" onclick="#"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>    
      </@ww.form>
</@framePage>