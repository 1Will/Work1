<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="采购单查询">
<script language="JavaScript" type="text/JavaScript"> 
	function people_OpenDialog() {
		popupModalDialog('${req.contextPath}/popup/peopleSelector.html',600,300);
	}	

</script>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" />
			<@ww.textfield label="'采购单名称'" name="'area.code'" value="" cssClass="'underline'" />
		</tr>
			<@ww.textfield label="'预算编号'" name="'area.code'" value="" cssClass="'underline'" />
			<@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
			<@pp.dateRanger label="'采购时间'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>			
		</tr>
	</@inputTable>  

         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>采购单编号</th>
			 	<th>采购单名称</th>
			 	<th>预算编号</th>
			 	<th>申购单编号</th>
			 	<th>采购时间</th>
			 	<th>供应商</th>
			 	<th>采购数量</th>
			 	<th>采购总金额(元)</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editPurchaseBill.html">JH-CG-08072001</a></td>
				<td>采购轴承</td>
				<td style="text-align:left">JH-YS-08072001</td>
				<td style="text-align:left">JH-SG-08072001</td>
				<td>2007-09-22</td>
				<td style="text-align:left">TCL</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">10000</td>
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>     
     	<@redirectButton value="新建" url="${req.contextPath}/pro/purchase/editPurchaseBill.html"/>
     	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>