<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />


<@htmlPage title="工装润滑标准列表">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'润滑标准编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'润滑标准名称'" name="'device.code'" value="" cssClass="'underline'"  />
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'工装编号'" name="'device.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'工装名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@dept0/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="listCheckPointRules.html"/>
        </@buttonBar>
         <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  			    <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>润滑标准编号</th>
	  				<th>润滑标准名称</th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>润滑负责人</th>
	  				<th>润滑周期</th>
	  				<th>标准创建时间</th>
	  				<th>标准创建人</th>
	  				<th>部门</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox"  name="checkbox" value="true"></td>
	  				<td style="text-align:left">JAC-0293-01</td>
	  				<td style="text-align:left">机床轴润滑</td>
	  				<td style="text-align:left">0293</td>
	  				<td style="text-align:left">机床</td>
	  				<td style="text-align:left">TOM</td>
	  				<td style="text-align:left">2天</td>
	  				<td>2006-12-12</td>
	  				<td style="text-align:left">system</td>
	  				<td style="text-align:left">机动部</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@vbutton value="选择" class="button" onclick="javascript:void(0);"/>
	    	<@vbutton value="关闭" class="button" onclick="javascript:window.close();"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>