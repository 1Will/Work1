<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="停机记录查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
	 
	 	<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'停机记录编号'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 			<@ww.textfield label="'停机记录名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>
	 		</tr>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('device.code')}'" name="'device.code'" value="" cssClass="'underline'" required="false"/>	 			
	 			<@ww.textfield label="'设备名称'" name="'device.code'" value="" cssClass="'underline'" required="false"/>	 			
	 		</tr>
	        <tr>
				<@pp.datePicker label="'从停机时间'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15"/>
			     			
			    <@pp.datePicker label="'至停机时间'" name="'repair.estimate.time1'"
			     			value="" cssClass="'underline'" size="15"/>
			</tr>
			<tr>
	 			<@dept0/>
	 			<@stop0/>
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
	  				<th>停机记录编号</th>
	  				<th>停机记录名称</th>
	  				<th>${action.getText('device.code')}</th>
	  				<th>${action.getText('device.name')}</th>
	  				<th>部门</th>
	  				<th>${action.getText('stop.time0')}</th>
	  				<th>${action.getText('stop.time1')}</th>
	  				<th>停机原因</th>
	  				<th>停机原因类别</th>
	  				<th>状态</th>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editRunRecordInfo.html">0001</a></td>
	  				<td style="text-align:left">HAN ZHONG JI停机</td>
	  				<td style="text-align:left">0021</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>2005-5-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别c</td>
	  				<td style="text-align:left">审批中</td>
	  			</tr>
	  			<tr>
	  				<td><input type="checkbox" name="checkbox" value="true"></td>
	  				<td style="text-align:left"><a href="editRunRecordInfo.html">0002</a></td>
	  				<td style="text-align:left">HAN ZHONG JI停机</td>
	  				<td style="text-align:left">0021</td>
	  				<td style="text-align:left">HAN ZHONG JI</td>
	  				<td style="text-align:left">装配一厂</td>
	  				<td>2005-5-1 12:00</td>
	  				<td>2005-5-1 12:30</td>
	  				<td style="text-align:left">...</td>
	  				<td style="text-align:left">类别c</td>
	  				<td style="text-align:left">审批中</td>
	  			</tr>	  			
	  		</@listTable>
        </table>
        <@buttonBar>
	     		<@redirectButton value="新建" url="editRunRecordInfo.html"/>
        		<@vbutton class="button" value="删除" onclick="confirm('${action.getText('delete.msg')}')" />	
	     </@buttonBar>
      </@ww.form>
      
</@htmlPage>