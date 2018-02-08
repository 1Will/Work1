<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="故障记录查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	 <#include "errLogSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editErrLog.html"/>
        </@buttonBar>
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>项目号</th>
	  				<th>故障编号</th>
	  				<th>故障名称</th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>工装图号</th>
	  				<th>部门</th>
	  				<th>故障发生日期</th>
	  				<th>故障详细</th>
	  				<th>解决方案</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editErrLog.html">0001</a></td>
	  				<td style="text-align:left">E-023</td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td style="text-align:left">FD010L</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left"></td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">滑轮轴的齿轮错位</td>
	  				<td style="text-align:left">停止该机床，申请维修。</td>
	  				<td>已解决</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editErrLog.html">0002</a></td>
	  				<td style="text-align:left">E-058</td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td style="text-align:left">FDS05</td>
	  				<td style="text-align:left">HAN DIAN JI</td>
	  				<td style="text-align:left"></td>
	  				<td style="text-align:left">电喷二室</td>
	  				<td>2005-6-1 12:30</td>
	  				<td style="text-align:left">喷漆枪漏电</td>
	  				<td style="text-align:left">更换枪体底座，返厂维修</td>
	  				<td>待解决</td>
	  			</tr>	  		
	  		</@listTable>
        </table>
         <@buttonBar>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>