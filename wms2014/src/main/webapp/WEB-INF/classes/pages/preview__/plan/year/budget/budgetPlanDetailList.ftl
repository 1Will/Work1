<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="维修计划列表">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">

            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>项目号</th>
            		<th>设备编号</th>
	        		<th>设备名称</th>
	        		<th>设备型号</th>
	        		<th>设备规格</th>
	        		<th>日常维修总金额(元)</th>
	        		<th>精度检查总金额(元)</th>
	        		<th>大项修总金额(元)</th>
	        		<th>改造总金额(元)</th>
	        		<th>关键备件总金额(元)</th>
	        		<th>总费用(元)</th>
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="return openBudgetPlanDetailDialog();">1</a></td>
            		<td style="text-align:left">123--083</td>
            		<td style="text-align:left">C1-1压力机</td>
            		<td style="text-align:left">SB02312</td>
            		<td style="text-align:left">Wx1212</td>
            		<td style="text-align:right">1000</td>  
            		<td style="text-align:right">12</td>   
            		<td style="text-align:right">10</td>  
            		<td style="text-align:right">105</td>
            		<td style="text-align:right">10</td>
            		<td style="text-align:right">1147</td>            		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td><a href="#" onclick="return openBudgetPlanDetailDialog();">2</a></td>
            		<td style="text-align:left">123--085</td>
            		<td style="text-align:left">C1-2压力机</td>
            		<td style="text-align:left">SB0231245</td>
            		<td style="text-align:left">Wx121244</td>
            		<td style="text-align:right">1000</td>  
            		<td style="text-align:right">12</td>   
            		<td style="text-align:right">10</td>  
            		<td style="text-align:right">105</td>  
            		<td style="text-align:right">10</td> 
            		<td style="text-align:right">1147</td>        		
            	</tr>

            </@listTable>
            </table>
            <@buttonBar>
	    		<input type="button" value="新建" class="button" onclick="popupModalDialog('${req.contextPath}/preview/plan/year/budget/editBudgetPlanDetail.html',750,400);"/>
	    		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />
	    	</@buttonBar>
	    	
	    	<script language="javascript">
	    		function openBudgetPlanDetailDialog() {
	    			popupModalDialog('${req.contextPath}/preview/plan/year/budget/editBudgetPlanDetail.html',800,600);
	    			return false;
	    		}
	    	</script>
     </@ww.form>
</@framePage>