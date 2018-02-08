<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备托管查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	    <@inputTable>
		<tr>
			<@ww.textfield label="'托管编号'" name="'discardNo'" value="'" cssClass="'underline'" />
	 		<@ww.textfield label="'托管名称'" name="'name'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
	        <@pp.dateRanger label="'申请日期'" name="'applyDatetime'" 
		       value="''"
		       cssClass="'underline'" dateFormat="date"/> 
	        <@ww.checkbox label="'仅查询失效'" name="'includeDisabled'" value="'false'" fieldValue="'value'" onclick="'changeDisabledStatus();'"/>
	    </tr>  
	</@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editTrusteeship.html"/>
        </@buttonBar>
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>托管编号</th>
			    <th>托管名称</th>		
			 	<th>托管人</th>
			 	<th>托管日期</th>
			 	<th>托管地点</th>
			 	<th>托管原因</th>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editTrusteeship.html">0001</a></td>
			 	<td style="text-align:left">机器人托管</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂车间</td>
			 	<td style="text-align:left">成品仓已满</td>
			 </tr>
			 <tr>
			 	<td><input type="checkbox" name="checkbox" value="true"></td>
			 	<td style="text-align:left"><a href="editTrusteeship.html">0002</a></td>
			 	<td style="text-align:left">机床托管</td>
			 	<td style="text-align:left">sa</td>
			 	<td>2007-02-12</td>
			 	<td style="text-align:left">旱冲一厂</td>
			 	<td style="text-align:left">目前未入库</td>
			 </tr>
	     	</@listTable>
	     	</table>   
	     	<@buttonBar>
	     		<input name="delete" type="button" value="失效" class="button"/>
	     		<input name="print" type="button" value="打印" class="button"/>
	     	</@buttonBar>  	
     </@ww.form>
</@htmlPage>