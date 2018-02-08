<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装领用查询">
	 <@ww.form name="'listForm'" action="'searchRequisitionLog'" method="'post'">
	 	<#include "requisitionLogSearcher.ftl"/>
        <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editRequisitionLog.html"/>  
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>项目号</th>
			 	<th>领用单编号</th>
			 	<th>领用单名称</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>工装类别</th>
			 	<th>领用人</th>
			 	<th>领用日期</th>
			 	<th>验收人</th>
			 	<th>验收日期</th>
			 	<th>部门</th>
			 	<th>保管员</th>
			 	<th>状态</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRequisitionLog.html">025</a></td>
				<td style="text-align:left">BH-45</a></td>
				<td style="text-align:left"></td>
				<td style="text-align:left">JAC-LY-2008072911</a></td>
				<td style="text-align:left">冲头（异型）</td>
				<td style="text-align:left"></td>
				<td>模具</td>
				<td style="text-align:left">sa</td>
				<td >2007-7-23</td> 
				<td >张敏</td>
				<td >2007-7-30</td>
				<td >总装一厂</td>
				<td >吴用</td>
				<td>领用</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editRequisitionLog.html">025</a></td>
				<td style="text-align:left">BH-47</a></td>
				<td style="text-align:left"></td>
				<td style="text-align:left">JAC-LY-2008072911</a></td>
				<td style="text-align:left">冲头（异型）</td>
				<td style="text-align:left"></td>
				<td>工位器具</td>
				<td style="text-align:left">sa</td>
				<td >2007-7-23</td>
				<td >张敏</td>
				<td >2007-7-30</td>
				<td>吊装车间</td>
				<td >阮小七</td>
				<td>归还</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>