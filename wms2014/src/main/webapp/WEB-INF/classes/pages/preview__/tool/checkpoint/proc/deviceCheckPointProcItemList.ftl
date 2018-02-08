 <#include "../../../../includes/eam2008.ftl" />
 <#include "/javascripts/selectAll.js" />
	<@framePage title="${action.getText('')}"> 
	<@ww.form name="'listForm'" action="'searchProducts'" method="'post'">  
                       <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
                <th>设备编号</th>
                <th>资产编号</th>
                <th>设备名称</th>
			 	<th>使用部门</th>
			 	<th>设备型号</th>
			 	<th>设备规格</th>
			 	<th>设备类别</th>
			 	<th>运行台时(时)</th>
			 	<th>停机台时(时)</th>
			 	<th>故障台时(时)</th>
			<tr>
			<tr>
					<td style="text-align:left">JAC-DJ-01</td>
					<td style="text-align:left">JAC-DJ-043</td>
					<td style="text-align:left">冲压机器人</td>
					<td style="text-align:left">冲压一厂</td>
					<td style="text-align:left">WT-123456</td>
					<td style="text-align:left">YT-123456</td>
					<td style="text-align:left">机器人</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>				
			</tr>
			<tr>
					<td style="text-align:left">JAC-DJ-3</td>
					<td style="text-align:left">JAC-DJ-054</td>
					<td style="text-align:left">冲压机床</td>
					<td style="text-align:left">冲压一厂</td>
					<td style="text-align:left">WT-123456</td>
					<td style="text-align:left">YT-123456</td>
					<td style="text-align:left">机床</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>
					<td style="text-align:left">
					  <input type="text" size="10" class="underline" value=""/>
					</td>				
			</tr>
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="保存" url="#"/>
   	 	</@buttonBar>
	     	<#--
	     	<@buttonBar>
	    		<input type="button" name="new"value="保存" class="button" onclick="#"/>
	    	</@buttonBar>  
	    	-->
	    	<script>
	    		  function change(obj) {
	    		  alert(obj.activeRow);
				  	if (!document.getElementsByTagName) return; 
				  	var anchors = document.getElementsByTagName("a"); 
				  	for (var i=0; i<anchors.length; i++) { 
				  		anchors[i].style.color="black";
				  		anchors[i].style.fontWeight="normal";
				  	}
				  	obj.style.color="red";
				  	obj.style.fontWeight="bold";
				  	return false;
				  }
	    	</script>
	    </@ww.form>
	</@framePage>