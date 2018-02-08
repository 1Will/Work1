<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="采购进度查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>		
	 	<tr>
	 		<@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
	 		<@ww.textfield label="'申购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>	 		
	 	</tr>
		<tr>
			<@pp.datePicker label="'从采购时间'" name="'repair.estimate.time0'"
		     			value="" cssClass="'underline'" size="15"/>
		     			
		    <@pp.datePicker label="'至采购时间'" name="'time1'"
		     			value="" cssClass="'underline'" size="15"/>
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
			 	<th>申购单编号</th>
			 	<th>供应商</th>
			 	<th>采购数量</th>
			 	<th>采购总金额(元)</th>
			 	<th>采购时间</th>
			 	<th>进度</th>
			 	<th>备注</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editPurchaseProgress.html">08072001</a></td>
				<td style="text-align:left">JH-SG-08072001</td>
				<td style="text-align:left">TCL</td>
				<td style="text-align:right">1000</td>
				<td style="text-align:right">10000</td>
				<td>2007-02-12</td>
				<td>50%</td>
				<td>...</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	    		<@redirectButton value="${action.getText('new')}"  url="editPurchaseProgress.html"/>
	    	</@buttonBar>
     </@ww.form>
</@htmlPage>