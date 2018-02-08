<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="事故记录查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 	 <#include "accidentLogSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        	<@redirectButton value="新建" url="editAccidentLog.html"/>
        </@buttonBar>
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>项目号</th>
	  				<th>事故编号</th>
	  				<th>事故名称</th>
	  				<th>工装编号</th>
	  				<th>工装名称</th>
	  				<th>工装图号</th>
	  				<th>部门</th>
	  				<th>事故发生日期</th>
	  				<th>事故详细</th>
	  				<th>解决方案</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editAccidentLog.html">0001</a></td>
	  				<td style="text-align:left">E-023</td>
	  				<td style="text-align:left">HAN ZHONG JI事故</th>
	  				<td style="text-align:left">FD010L</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left"></td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td style="text-align:left">模具摆放错位，造成零件损坏</td>
	  				<td style="text-align:left">无法维修，重新购买</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editAccidentLog.html">0002</a></td>
	  				<td style="text-align:left">E-058</td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td style="text-align:left">FDS05</td>
	  				<td style="text-align:left">HAN DIAN JI</td>
	  				<td style="text-align:left"></td>
	  				<td style="text-align:left">电喷二室</td>
	  				<td>2005-6-1 12:30</td>
	  				<td style="text-align:left">喷漆枪漏电</td>
	  				<td style="text-align:left">更换枪体底座，返厂维修</td>
	  			</tr>	  		
	  		</@listTable>
        </table>
         <@buttonBar>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>