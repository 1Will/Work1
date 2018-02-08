<#--$Id: extInfoList.ftl 6197 2007-08-09 10:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />
<@htmlPage title="工装润滑标准查询">
	 <@ww.form name="'listForm'" action="" method="'post'">
		<@inputTable>
	 		<tr>
	 			<@ww.textfield label="'${action.getText('device.code')}'" name="'device.code'" value="" cssClass="'underline'" required="true"/>
	        </tr>
        </@inputTable>  
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        <tr id="techDocTable" style="display:none">
	        <table id="vltable" class="defaultLook" width="100%" border="1">
	        	<tr>
	        	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	        	<th>${action.getText('device.code')}</th>
	        	<th>${action.getText('device.name')}</th>
	        	<th>${action.getText('smooth.part')}</th>
	        	<th>${action.getText('smooth.oil')}</th>
	        	<th>${action.getText('smooth.kind')}</th>
	        	<th>${action.getText('smooth.measure')}</th>
	        	<th>${action.getText('smooth.time')}</th>
	        	<th>${action.getText('smooth.people')}</th>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td  style="text-align:left"><a href="updateSmoothInfo.html?device.id=0001">0001</a></td>
	        		<td style="text-align:left">ZHENG HE JI</td>
	        		<td style="text-align:left">JI SHU DAO</td>
	        		<td style="text-align:left">HUAN OIL</td>
	        		<td style="text-align:left">ERASE</td>
	        		<td style="text-align:right">1000</td>
	        		<td>2006-7-1</td>
	        		<td style="text-align:left">TOM</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td  style="text-align:left"><a href="updateSmoothInfo.html?device.id=0001">0002</a></td>
	        		<td style="text-align:left">ZHENG HE JI</td>
	        		<td style="text-align:left">JI SHU DAO</td>
	        		<td style="text-align:left">HUAN OIL</td>
	        		<td style="text-align:left">ERASE</td>
	        		<td style="text-align:right">500</td>
	        		<td>2006-10-1</td>
	        		<td style="text-align:left">JIM</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td  style="text-align:left"><a href="updateSmoothInfo.html?device.id=0001">0003</a></td>
	        		<td style="text-align:left">ZHENG HE JI</td>
	        		<td style="text-align:left">JI SHU DAO</td>
	        		<td style="text-align:left">HUAN OIL</td>
	        		<td style="text-align:left">ERASE</td>
	        		<td style="text-align:right">700</td>
	        		<td>2007-7-1</td>
	        		<td style="text-align:left">HEER</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td style="text-align:left"><a href="updateSmoothInfo.html?device.id=0001">0004</a></td>
	        		<td style="text-align:left">ZHENG HE JI</td>
	        		<td style="text-align:left">JI SHU DAO</td>
	        		<td style="text-align:left">HUAN OIL</td>
	        		<td style="text-align:left">ERASE</td>
	        		<td style="text-align:right">800</td>
	        		<td>2005-7-1</td>
	        		<td style="text-align:left">JIM</td>
	        	</tr>
	        	<tr>
	        		<td><input type="checkbox" name="checkbox" value="true"></td>
	        		<td  style="text-align:left"><a href="updateSmoothInfo.html?device.id=0001">0005</a></td>
	        		<td style="text-align:left">ZHENG HE JI</td>
	        		<td style="text-align:left">JI SHU DAO</td>
	        		<td style="text-align:left">HUAN OIL</td>
	        		<td style="text-align:left">ERASE</td>
	        		<td style="text-align:right">555</td>
	        		<td>2006-9-1</td>
	        		<td style="text-align:left">TOM</td>
	        	</tr>
	        </table>
        </tr>
        <@buttonBar>
     		<@redirectButton value="${action.getText('techDoc.new')}" url="${req.contextPath}/preview/device/smoothInfo/newSmoothInfo.html"/> 
   	 		<@redirectButton value="${action.getText('invalid')}" url="#"/>
   	 	</@buttonBar>
     </@ww.form> 
    
</@htmlPage>
 