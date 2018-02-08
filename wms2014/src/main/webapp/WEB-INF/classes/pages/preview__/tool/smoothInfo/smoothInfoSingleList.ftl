<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@framePage title="${action.getText('smoothInfo.title')}">
  <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
  	<@listTable>
  	    <th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
	   <th>${action.getText('smooth.part')}</th>
	   <th>${action.getText('smooth.oil')}</th>
	   <th>${action.getText('smooth.kind')}</th>
	   <th>${action.getText('smooth.measure')}</th>
	   <th>${action.getText('smooth.time')}</th>
	   <th>${action.getText('smooth.people')}</th>
	   <tr>
	   		<td><input type="checkbox"  name="checkbox" value="true"></td>
	       <td style="text-align:left">JI SHU DAO</td>
	       <td style="text-align:left">HUAN OIL</td>
	       <td style="text-align:left">ERASE</td>
	       <td style="text-align:right">1000</td>
	       <td>2006-7-1</td>
	       <td style="text-align:left">TOM</td>
	   </tr>
	   <tr>
	   		<td><input type="checkbox" name="checkbox" value="true"></td>
	       <td style="text-align:left">JI SHU DAO</td>
	       <td style="text-align:left">HUAN OIL</td>
	       <td style="text-align:left">ERASE</td>
	       <td style="text-align:right">500</td>
	       <td>2006-10-1</td>
	       <td style="text-align:left">JIM</td>
	   </tr>
	   <tr>
	  	 <td><input type="checkbox" name="checkbox" value="true"></td>
	       <td style="text-align:left">JI SHU DAO</td>
	       <td style="text-align:left">HUAN OIL</td>
	       <td style="text-align:left">ERASE</td>
	       <td style="text-align:right">700</td>
	       <td>2007-7-1</td>
	       <td style="text-align:left">HEER</td>
	   </tr>
	   <tr>
	   		<td><input type="checkbox" name="checkbox" value="true"></td>
	        <td style="text-align:left">JI SHU DAO</td>
	        <td style="text-align:left">HUAN OIL</td>
	        <td style="text-align:left">ERASE</td>
	        <td style="text-align:right">800</td>
	        <td>2005-7-1</td>
	        <td style="text-align:left">JIM</td>
	   </tr>
	   <tr>
	   		<td><input type="checkbox" name="checkbox" value="true"></td>
	        <td style="text-align:left">JI SHU DAO</td>
	        <td style="text-align:left">HUAN OIL</td>
	        <td style="text-align:left">ERASE</td>
	        <td style="text-align:right">555</td>
	        <td>2006-9-1</td>
	        <td style="text-align:left">TOM</td>
	   </tr>
	</@listTable>
  </table>
  <@buttonBar>
  	<@redirectButton value="新建" url="listSingleSmoothInfo.html"/>
  	<@redirectButton value="${action.getText('invalid')}" url="listSingleSmoothInfo.html"/>
  </@buttonBar>
	
</@framePage>

