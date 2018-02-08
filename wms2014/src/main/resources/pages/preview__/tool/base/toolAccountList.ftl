<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<@htmlPage title="工装台帐查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<#include "toolCardSearcher.ftl"/>
	 	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" value="true"/></th>
			 	<th>工装编号</th>
			 	<th>工装图号</th>
			 	<th>工装名称</th>
			 	<th>工装类别</th>
			 	<th>分类</th>
			 	<th>工序号</th>
			 	<th>产品名称</th>
			 	<th>产品图号</th>
			 	<th>领用人或所在班组</th>
			 	<th>使用设备</th>
			 	<th>数量</th>
			 	<th>投入日期</th>
			 	<th>产量累计</th>
			 	<th>配置清单</th>
			 	<th>图纸</th>
			 	<th>供应商名称</th>
			 	<th>备注</th>
			 	<th>履历</th>
			 	<th>状态</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"/></td>
				<td  ><a href="editTool.html">3201</a></td>
				<td  >07010102</td>
				<td  >拉延</td>
				<td  >模具</td>
				<td  >冲头</td>
				<td style="text-align:right">1</td>
				<td  >后地板</td>
				<td  >3201</td>
				<td  >普通车床</td>
				<td  >穆立峰</td>
				<td style="text-align:right">1</td>
				<td >2007-02-03</td>
				<td style="text-align:right">1000</td>
				<td  ><a href="#" onclick="item_OpenDialog();return false;">查看</a></td>
				<td  ><a href="#" onclick="graphPaper_OpenDialog();return false;">查看</a></td>
				<td  >总装一厂</td>
				<td  >........</td>
				<td  ><a href="#" onclick="record_OpenDialog();return false;">查看</a></td>	
				<td  >使用中</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"/></td>
				<td  ><a href="editTool.html">5201</a></td>
				<td  >07015555</td>
				<td  >冲头</td>
				<td  >模具</td>
				<td  >冲头</td>
				<td style="text-align:right">1</td>
				<td  >内板</td>
				<td  >3201</td>
				<td  >普通车床</td>
				<td  >韦平</td>
				<td style="text-align:right">1</td>
				<td >2007-02-03</td>
				<td style="text-align:right">1000</td>
				<td  ><a href="#" onclick="item_OpenDialog();return false;">查看</a></td>
				<td  ><a href="#" onclick="graphPaper_OpenDialog();return false;">查看</a></td>
				<td  >总装一厂</td>
				<td  >........</td>
				<td  ><a href="#" onclick="irecord_OpenDialog();return false;">查看</a></td>			
				<td  >使用中</td>
			</tr>
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	     		<@vbutton class="button" value="失效" onclick="javascript:void(0);"/>
	     		<@vbutton class="button" value="打印" onclick="javascript:void(0);"/>
	     	</@buttonBar>
	 <script language="JavaScript" type="text/JavaScript"> 
		function item_OpenDialog() {
			popupModalDialog('${req.contextPath}/preview/tool/base/listToolSpares.html?item=1',600,400);
		}	
		function graphPaper_OpenDialog() {
			popupModalDialog('${req.contextPath}/preview/tool/base/listToolDocs.html?item=1',600,400);
		}
		function record_OpenDialog() {
			popupModalDialog('${req.contextPath}/popup/listToolRecordViews.html',600,400);
		}
	</script>
     </@ww.form>
</@htmlPage>