<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="工装润滑标准查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'润滑标准编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'润滑标准名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'工装编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'工装名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>  
	        <tr>
	        
	        </tr>     
	        <tr>
	        	<@dept0/>
	        	<@workflow/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="listSmoothRules.html"/>
        </@buttonBar>
          <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  			  <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  			  	<th>润滑标准编号</th>
	  			  	<th>润滑标准名称</th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>部门</th>
	  				<th>润滑周期</th>
	  				<th>润滑负责人</th>
	  				<th>标准创建时间</th>
	  				<th>标准创建人</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editSmoothRule.html">JAC-0982</a></td>
	  				<td style="text-align:left">机床润滑</td>
	  				<td style="text-align:left">023</td>
	  				<td style="text-align:left">机床</td>
	  				<td>...</td>
	  				<td>...</td>
	  				<td>...</td>
	  				<td>...</td>
	  				<td>...</td>
	  				<td style="text-align:left">已签字</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editSmoothRule.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>