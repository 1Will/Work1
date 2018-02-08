<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装标定查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#include "demarcateSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editDemarcate.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>项目号</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>制造单位</th>
			 	<th>使用单位</th>
			 	<th>负责人</th>
			 	<th>标定周期</th>
			 	<th>标定结果</th>
			 	<th>本次标定日期</th>
			 	<th>下次标定日期</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDemarcate.html">2008072911</a></td>
				<td style="text-align:left">FD010L</a></td>
				<td style="text-align:left">左前纵梁总成补焊</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">冲焊二厂</td>
				<td style="text-align:left">陈荣超</td>
				<td style="text-align:left">12个月</td>
				<td style="text-align:left">合格</td>
				<td >2007-6-22</td>
				<td >2008-6-21</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editDemarcate.html">2008072911</a></td>
				<td style="text-align:left">FD030L-1</a></td>
				<td style="text-align:left">前侧车门内板总成补焊（左）</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">12个月</td>
				<td style="text-align:left">合格</td>
				<td >2007-11-01</td>
				<td >2008-10-31</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>