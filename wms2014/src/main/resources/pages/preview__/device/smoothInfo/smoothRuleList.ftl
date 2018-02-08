<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="设备润滑标准查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
			 	<@ww.textfield label="'润滑标准编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'润滑标准名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>	
	        <tr>
	        	<@ww.textfield label="'设备编号'" name="'area.code'" value="" cssClass="'underline'" />
			 	<@ww.textfield label="'设备名称'" name="'area.code'" value="" cssClass="'underline'" />
	        </tr>  
	        <tr>
	        
	        </tr>     
	        <tr>
	        	<@dept0/>
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
	  				<th>设备编号</th>
	  				<th>设备名称</th>
	  				<th>部门</th>
	  				<th>定标日期</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editSmoothRule.html">YLJ-RH-123--095</a></td>
	  				<td style="text-align:left">闭式四点单动多连杆机械压力机润滑</td>
	  				<td style="text-align:left">123--095</td>
	  				<td style="text-align:left">闭式四点单动多连杆机械压力机</td>
	  				<td>冲压一厂</td>
	  				<td>2007-12-19</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editSmoothRule.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>