<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="供应商评定查询">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 	 	<@inputTable>
		<tr>
		    <@ww.textfield label="'供应商编号'" name="'manufuture.name'" value="" cssClass="'underline'" />	 
		    <@ww.textfield label="'供应商名称'" name="'manufuture.name'" value="" cssClass="'underline'" />	
		</tr>
		<tr>
            <@dept0/>
            <td align="right" valign="top"><span class="required"></span><label class="label">评定人:</label></td>
		 	<td >
		 			<input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="sa"  maxlength="150" disabled/>
		 			<@ww.hidden name="'alteration.unSealPeople'" value="'sa'"/>
		 			<a onClick='user_OpenUnSealedDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 	</td>
		</tr> 
		<tr>
		   <@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
		</tr>
	</@inputTable> 
	<@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editSupplierEvaluate.html"/>
    </@buttonBar>
    <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>供应商编号</th>
				<th>供应商名称</th>
				<th>部门</th>
				<th>评定人</th>
				<th>年度</th>
				<th>平均分</th>
				<th>评定结果</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSupplierEvaluate.html">GBS-00000004</a></td>
				<td style="text-align:left">南京起重机</td>
				<td style="text-align:left">焊冲一厂</td>
				<td style="text-align:left">张辉</td>
				<td>2007</td>
				<td style="text-align:right">60</td>
				<td style="text-align:left">合格</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editSupplierEvaluate.html">GBS-00000004</a></td>
				<td style="text-align:left">南京起重机</td>
				<td style="text-align:left">焊冲一厂</td>
				<td style="text-align:left">sa</td>
				<td>2007</td>
				<td style="text-align:right">40</td>
				<td style="text-align:left">不合格</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	    		<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>	
	    	</@buttonBar>    
 </@ww.form>
 <script>
 	function user_OpenUnSealedDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userUnSealedSelectorHandler);
	 }	
	 
	function userUnSealedSelectorHandler(result) {
	  		document.forms[0].elements["alteration.unSealPeople"].value = result[1];
	  		document.forms[0].elements["alteration.unsealedPeopleDisplay"].value = result[1];
	  	}
 </script>
</@htmlPage>