<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="制度文档查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	 	<#include "docArchSearcher.ftl"/>
	
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="上传" url="${req.contextPath}/preview/tool/docarch/editdocArch.html"/> 
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>序号</th>
			 	<th>名称</th>
			 	<th>种类</th>
			 	<th>描述</th>			 	
			 	<th>上传人</th>
			 	<th>上传时间</th>
			<tr>
			<tr>
			<td><input type="checkbox"  name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/preview/tool/docarch/modifyDocArch.html">1</a></td>
				<td style="text-align:left"><a href="#" title="下载">工装维修管理办法</a></td>
				<td style="text-align:left">工装管理制度</td>
				<td style="text-align:left">工装维修管理办法</td>
				<td style="text-align:left">system</td>
				<td >2007-09-01</td>
			</tr>			
	     	</@listTable>
	     	</table>
	    <@buttonBar>
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@htmlPage>