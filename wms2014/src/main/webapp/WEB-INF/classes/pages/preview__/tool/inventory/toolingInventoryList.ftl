<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装清查查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'清查编号'" name="'area.code'" value="" cssClass="'underline'"  />
			 	<@ww.textfield label="'清查名称'" name="'area.code'" value="" cssClass="'underline'"  />
	        </tr>	   
	        <tr>
	            <@dept0/>
	        	<@pp.dateRanger label="'清查日期'" name="'cardCreatedTime'" 
		  				value="''" cssClass="'underline'" dateFormat="date"/>
		  		<@ww.checkbox label="'仅查询失效'" name="'exclude_disabled'" value="'false'" fieldValue="'true'"/>
		  	</tr>
        </@inputTable>
        <@buttonBar>
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="${action.getText('new')}" url="editToolingInventory.html"/>
        </@buttonBar>
            <@listTable>
            <tr>
            <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>清查编号</th>
				<th>清查名称</th>
				<th>部门</th>
				<th>清查日期</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="" value="true" disabled="true"></td>
				<td style="text-align:left"><a href="editToolingInventory.html">07010102-02</a></td>
				<td style="text-align:left">冲焊一厂清查</td>
				<td style="text-align:left">冲焊一厂</td>
				<td>2007-01-23</td>
			</tr>	
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editToolingInventory.html">07010102-02</a></td>
				<td style="text-align:left">涂装一厂清查</td>
				<td style="text-align:left">涂装一厂</td>
				<td>2007-01-23</td>
			</tr>		
	     	</@listTable>
	     	<@buttonBar>
	    		<@vbutton value="失效" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    		<@vbutton value="打印" class="button" />
	    	</@buttonBar>  	
     </@ww.form>
</@htmlPage>