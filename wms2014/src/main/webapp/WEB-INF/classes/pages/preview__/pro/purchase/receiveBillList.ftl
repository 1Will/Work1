<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="验收单查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	
	 	<@inputTable>
		<tr>
			<@ww.textfield label="'验收单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			<@ww.textfield label="'采购单编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
		</tr>
		<tr>
		    <td align="right" valign="top"><span class="required"></span><label class="label">验收人:</label></td>
		 	<td >
		 			<input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="sa"  maxlength="150" disabled/>
		 			<@ww.hidden name="'alteration.unSealPeople'" value="'sa'"/>
		 			<a onClick='user_OpenUnSealedDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 	</td>
		 	<@pp.dateRanger label="'验收时间'" name="'cardCreatedTime'" 
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
            	<th>验收单编号</th>
			 	<th>采购单编号</th>
			 	<th>采购单名称</th>
			 	<th>验收人</th>
			 	<th>验收时间</th>
			<tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editReceiveBill.html">08072001</a></td>
				<td style="text-align:left">08122001</a></td>
				<td style="text-align:left">办公用品采购</a></td>
				<td style="text-align:left">system</td>
				<td>2007-11-12</td>	
			</tr>			
	     	</@listTable>
	     	</table>
     
     <@buttonBar>
     	<@redirectButton value="新建" url="${req.contextPath}/pro/purchase/editReceiveBill.html"/>
     	
     	<@vbutton class="button" onclick="javascript:void(0)" value="创建设备卡片"/>
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