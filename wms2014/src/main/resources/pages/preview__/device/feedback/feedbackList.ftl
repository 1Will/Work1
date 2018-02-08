<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="反馈记录查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'反馈记录编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'反馈记录名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('device.code')}'" name="'device.code'" value="" cssClass="'underline'" required="false"/>	 			
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>	 			
	 		</tr>
	        <tr>
				<@pp.datePicker label="'从反馈时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'至反馈时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
			<tr>
				<@dept0/>
				<@feedback0/>
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
	  				<th>反馈记录编号</th>
	  				<th>反馈记录名称</th>
	  				<th>${action.getText('device.code')}</th>
	  				<th>${action.getText('device.name')}</th>
	  				<th>反馈时间</th>
	  				<th>反馈人</th>
	  				<th>反馈内容</th>
	  				<th>反馈类别</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editFeedback.html">0001</a></td>
	  				<td style="text-align:left">电焊机有问题</td>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>sa</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>
	  				<td style="text-align:left">提交</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editFeedback.html">0002</a></td>
	  				<td style="text-align:left">电焊机有问题</td>
	  				<td style="text-align:left">0001</td>
	  				<td style="text-align:left">HAN DIAN JI</td>
	  				<td>2005-6-1 12:00</td>
	  				<td>system</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别a</td>	 
	  				<td style="text-align:left">已审批</td> 			
	  			</tr>	  			
	  		</@listTable>
        </table>
         <@buttonBar>
	     		<@redirectButton value="新建" url="editFeedback.html"/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
</@htmlPage>