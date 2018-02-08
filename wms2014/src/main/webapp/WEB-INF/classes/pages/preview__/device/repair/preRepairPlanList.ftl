<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="维修报告查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'维修报告编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'维修报告名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
				<@dept0/>
				<@RepairGrade/>
	 		</tr>
	 	</@inputTable>
	 	<@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	        	<tr>
            		<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            		<th>维修报告编号</th>
            		<th>维修报告名称</th>
	        		<th>设备编号</th>
	        		<th>设备名称</th>	
	        		<th>部门</th>
	        		<th>维修等级</th>
	        		<th>维修费用（元）</th>	        		
	        		<th>责任单位</th>
	        		<th>责任人</th>
	        		<th>执行人</th>	        			        		
	        		<th>执行结果</th>	        		
            	</tr>
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td  style="text-align:center"><a href="${req.contextPath}/preview/device/repair/editPreRepairProcDetailX.html?proc=1">20080809110401001</a></td>
            		<td  style="text-align:center">行车维修</td>
            		<td  style="text-align:center">123--459</td>
            		<td  style="text-align:center">行车</td> 
            		<td  style="text-align:center">机动部</td> 
            		<td  style="text-align:center">A</td>          		  
            		<td  style="text-align:center">20000</td>            		           		       		
            		<td  style="text-align:center">冲压一车间</td>
            		<td  style="text-align:center">张三</td>
            		<td  style="text-align:center">李四</td>
            		<td>未完成</td>            		
            	</tr>
            	
            	<tr>
            		<td><input type="checkbox" name="checkbox" value="true"></td>
            		<td  style="text-align:center"><a href="${req.contextPath}/preview/device/repair/editPreRepairProcDetailX.html?proc=1">20080809110401002</a></td>
            		<td  style="text-align:center">机器人维修</td>
            		<td  style="text-align:center">456--567</td>
            		<td  style="text-align:center">机器人</td>
            		<td  style="text-align:center">机动部</td> 
            		<td  style="text-align:center">A</td>     
            		<td  style="text-align:center">180000</td>         		
            		<td  style="text-align:center">维修班</td>
            		<td  style="text-align:center" >张三</td>
            		<td  style="text-align:center" >李四</td>
            		<td>未完成</td>            		
            	</tr>
            	
            	
	        </@listTable>
	    </table>
	    <@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="RepairReport.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
	    <script>
	    		function popup() {
	    			popupModalDialog('${req.contextPath}/preview/device/repair/editPreRepairProcDetailX.html?proc=1',1024,768);
	    		}
	    </script>
	 </@ww.form>
</@htmlPage>