<#include "../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />


<@htmlPage title="设备定期检查标准查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'定期检查标准编号'" name="'device.code'" value="" cssClass="'underline'"  />
	 			<@ww.textfield label="'定期检查标准名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	       
	        <tr>
	            <@ww.textfield label="'设备编号'" name="'device.code'" value="" cssClass="'underline'"  />
	        	<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />
	        </tr>
	        <tr>
	        	<@dept0/>
	        	<@status/>
	        </tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
         <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	        <@listTable>
	  			<tr>
	  			    <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>定期检查标准编号</th>
	  				<th>定期检查标准名称</th>
	  				<th>设备编号</th>
	  				<th>设备名称</th>
	  				<th>检查周期</th>
	  				<th>检查负责人</th>
	  				<th>标准创建时间</th>
	  				<th>标准创建人</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox"  name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckRules.html">20070801003</a></td>
	  				<td style="text-align:left">J-89</td>
	  				<td style="text-align:left">078</td>
	  				<td style="text-align:left">吊桥</td>
	  				<td style="text-align:left">一个星期</td>
	  				<td style="text-align:left">System</td>
	  				<td>2007-8-20</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">已审核</td>
	  			</tr><tr>
	  				<td><input type="checkbox"  name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editCheckRules.html">20070801009</a></td>
	  				<td style="text-align:left">JAC-07</td>
	  				<td style="text-align:left">07</td>
	  				<td style="text-align:left">滑轮</td>
	  				<td style="text-align:left">一月</td>
	  				<td style="text-align:left">Tom</td>
	  				<td>2007-8-20</td>
	  				<td style="text-align:left">System</td>
	  				<td style="text-align:left">未审核</td>
	  			</tr>
	  		</@listTable>
        </table>
     	<@buttonBar>
	    	<@redirectButton value="${action.getText('new')}" url="editCheckRules.html"/>
	    	<@vbutton value="删除" class="button" onclick="confirm('${action.getText('delete.msg')}')"/>
	    </@buttonBar>
      </@ww.form>
</@htmlPage>