<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装转移查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	    <@inputTable>
		<tr>
			<@ww.textfield label="'转移编号'" name="'discardNo'" value="'" cssClass="'underline'" />
	 		<@ww.textfield label="'转移名称'" name="'name'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'工装编号'" name="'toolingNo'" value="''" cssClass="'underline'" />
	 		<@ww.textfield label="'工装名称'" name="'toolingName'" value="''" cssClass="'underline'"/>
	 		<@ww.textfield label="'工装图号'" name="'toolingName'" value="''" cssClass="'underline'"/>
	 	</tr>
		<tr>
	        <@pp.dateRanger label="'转移日期'" name="'applyDatetime'" 
		       value="''"
		       cssClass="'underline'" dateFormat="date"/> 
	       <@ww.select label="'审批状态'"
                	name="device"
               	 	headerKey="1" 
                	headerValue="Select Month"
                	list="{'${action.getText('所有')}', 
                			'${action.getText('已审批')}', 
                			'${action.getText('未审批')}'
                	  	  }"
                	value="selectedDevice"
	        	/> 
	        <@ww.checkbox label="'仅查询失效'" name="'includeDisabled'" value="'false'" fieldValue="'value'" onclick="'changeDisabledStatus();'"/>
	    </tr> 
</@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editMigrate.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>转移编号</th>
			    <th>转移名称</th>		
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>转移申请人</th>
			 	<th>转移日期</th>
			 	<th>原地点</th>
			 	<th>新地点</th>
			 	<th>转移原因</th>
			 	<th>审批状态</th>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editMigrate.html">0001</a></td>
			 	<td style="text-align:left">模具转移</td>
			 	<td style="text-align:left">JQ0023</td>
			 	<td style="text-align:left">机器人</td>
			 	<td style="text-align:left">MJ-096</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂</td>
			 	<td style="text-align:left">机动部</td>
			 	<td style="text-align:left">该工装在维修</td>
			 	<td style="text-align:left">未审批</td>	
			 </tr>
			 <tr>
			 	<td><input type="checkbox" value="true" disabled="true"></td>
			 	<td style="text-align:left"><a href="editMigrate.html">0002</a></td>
			 	<td style="text-align:left">机床转移</td>
			 	<td style="text-align:left">JQ0025</td>
			 	<td style="text-align:left">机床</td>
			 	<td style="text-align:left">MJ-0083</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂</td>
			 	<td style="text-align:left">机动部</td>
			 	<td style="text-align:left">该工装在维修</td>
			 	<td style="text-align:left">已审批</td>	
			 </tr>
	     	</@listTable>
	     	</table>   
	     	<@buttonBar>
	     		<input name="delete" type="button" value="失效" class="button"/>
	     		<input name="print" type="button" value="打印" class="button"/>
	     	</@buttonBar>  	
     </@ww.form>
     <script language="javascript">
	           function changeDisabledStatus() {
	             if (getObjByNameRe("includeDisabled").checked) {
	               getObjByNameRe("delete").value="有效";
	             } else {
	               getObjByNameRe("delete").value="失效";
	             }
	           }
	    </script>  
</@htmlPage>