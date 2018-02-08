<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@framePage title="设备点检标准查询">
	 <@ww.form name="'listTool'" action="'searchProducts'" method="'post'">

	
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
           <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
                <th>项目号</th>
                <th>标定部位</th>
			 	<th>标定内容</th>
			 	<th>标定标准</th>
			 	<th>标定方法</th>
			 	<th>标定要求</th>
			<tr>		
	     	</@listTable>
	     	</table>
	    <@buttonBar>
	    	<@redirectButton value="新建" url="#"/> 
   	 		<@redirectButton value="删除" url="#"/>
   	 	</@buttonBar>
     </@ww.form>
</@framePage>