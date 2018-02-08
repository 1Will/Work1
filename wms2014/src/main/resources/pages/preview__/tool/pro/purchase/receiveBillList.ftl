<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="验收单查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'验收单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'验收单名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
			<@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@workflow/>
		</tr>	
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="${req.contextPath}/preview/tool/purchase/editReceiveBill.html"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
            	<th>验收单编号</th>
            	<th>验收单名称</th>
			 	<th>采购单编号</th>
			 	<th>验收人</th>
			 	<th>验收时间</th>
			 	<th>状态</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editReceiveBill.html">08072001</a></td>
				<td style="text-align:left">夹具验收</a></td>
				<td style="text-align:left">08122001</a></td>
				<td style="text-align:left">system</td>
				<td>2007-11-12</td>	
				<td style="text-align:left">审批中</td>	
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	 <@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     	<@vbutton class="button" onclick="javascript:void(0)" value="创建设备卡片"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>