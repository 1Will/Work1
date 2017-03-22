<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: cityList.ftl 2009-11-26 17:32:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="城市信息管理">
	<@ww.form name="'listForm'" action="'searchCity_'" method="'post'">
		<@ww.token name="searchCityToken"/>
		<#include "./citySearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/cityManager_/saveCity_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>城市编码</th>
			 	<th>城市名称</th>
			 	<th>所属省份</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCity_.html">city_001</a></td>
				<td style="text-align:left">合肥市</td>
				<td style="text-align:left">安徽省</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCity_.html">city_002</a></td>
				<td style="text-align:left">马鞍山市</td>
				<td style="text-align:left">安徽省</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCity_.html">city_003</a></td>
				<td style="text-align:left">芜湖市</td>
				<td style="text-align:left">安徽省</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCity_.html">city_004</a></td>
				<td style="text-align:left">蚌埠市</td>
				<td style="text-align:left">安徽省</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCity_.html">city_005</a></td>
				<td style="text-align:left">阜阳市</td>
				<td style="text-align:left">安徽省</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>