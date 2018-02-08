<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="故障记录查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'故障记录编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'故障记录名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('device.code')}'" name="'device.code'" value="" cssClass="'underline'"  />	       		
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'"  />	       		
	 		</tr>
	        <tr>
				<@pp.datePicker label="'从故障时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'至故障时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
			<tr>
				<@dept0/>
	 			<@err0/>
	 			<@status/>
			</tr>
        </@inputTable> 
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <table id="vltable" class="defaultLook" width="100%" border="1">
	        <@listTable>
	  			<tr>
	  				<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	  				<th>故障记录编号</th>
	  				<th>故障记录名称</th>
	  				<th>${action.getText('device.code')}</th>
	  				<th>${action.getText('device.name')}</th>
	  				<th>部门</th>
	  				<th>故障开始时间</th>
	  				<th>故障结束时间</th>
	  				<th>故障原因</th>
	  				<th>故障类别</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editErrLog.html">0001</a></td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td style="text-align:left">0023</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>2005-5-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>
	  				<td style="text-align:left">提交</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editErrLog.html">0002</a></td>
	  				<td style="text-align:left">HAN ZHONG JI故障</th>
	  				<td style="text-align:left">0023</td>
	  				<td style="text-align:left">HAN DIAN JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-6-1 12:00</td>
	  				<td>2005-6-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>
	  				<td style="text-align:left">审批中</td>
	  			</tr>	  		
	  		</@listTable>
        </table>
         <@buttonBar>
	     		<@redirectButton value="新建" url="editErrLog.html"/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>