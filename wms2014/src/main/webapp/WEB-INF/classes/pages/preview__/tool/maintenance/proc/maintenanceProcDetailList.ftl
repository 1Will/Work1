<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="保养实施明细列表">
	 <@ww.form name="'listForm'" action="" method="'post'">
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>项目号</th>
	  				<th>保养部位</th>
	  				<th>保养内容</th>
	  				<th>保养标准</th>
	  				<th>实施结果</th>
	  				<th>保养辅材</th>
	  				<th>修理方案</th>
	  				<th>保养费用</th>
	  				<th>备注</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">前围上盖板内板总成自动送料机</th>
	  				<td style="text-align:left">皮带打蜡</th>
	  				<td style="text-align:left">……</th>
	  				<td style="text-align:left">1/4</td>
	  				<td style="text-align:left">打蜡仪</td>
	  				<td style="text-align:left">……</td>
	  				<td style="text-align:left">60</td>
	  				<td>……</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left">0002</td>
	  				<td style="text-align:left">右后轮罩大总成</th>
	  				<td style="text-align:left">切换台线路</th>
	  				<td style="text-align:left">……</th>
	  				<td style="text-align:left">2/3</td>
	  				<td style="text-align:left">电路器材</td>
	  				<td style="text-align:left">改装线路</td>
	  				<td style="text-align:left">2100</td>
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