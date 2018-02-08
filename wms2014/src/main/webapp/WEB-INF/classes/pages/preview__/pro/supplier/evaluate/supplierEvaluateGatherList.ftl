<#include "../../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="供应商评定汇总查询">
 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
 	 	<@inputTable>
		<tr>
		    <@ww.textfield label="'供应商编号'" name="'manufuture.name'" value="" cssClass="'underline'" />	 
		    <@ww.textfield label="'供应商名称'" name="'manufuture.name'" value="" cssClass="'underline'" />	
		   <@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
		</tr>
	</@inputTable> 
	<@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
    </@buttonBar>
    <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>供应商编号</th>
				<th>供应商名称</th>
				<th>年度</th>
				<th>总平均分</th>
				<th>评定结果</th>
				<th>评定人数</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">GBS-00000004</td>
				<td style="text-align:left">南京起重机</td>
				<td>2007</td>
				<td style="text-align:right">60</td>
				<td style="text-align:left">合格</td>
				<td >10</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="" value="true" disabled="true"></td>
				<td style="text-align:left">GBS-00000005</td>
				<td style="text-align:left">南京挖掘机</td>
				<td>2007</td>
				<td style="text-align:right">40</td>
				<td style="text-align:left">不合格</td>
				<td >10</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     	<@buttonBar>
	    		<@vbutton value="确认" class="button"/>	
	    		<@vbutton value="汇总打印" class="button"/>	
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