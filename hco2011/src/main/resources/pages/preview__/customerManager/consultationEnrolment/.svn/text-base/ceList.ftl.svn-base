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

<#-- $Id: productList.ftl 2009-11-27 14:32:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="咨询登记管理">
	<@ww.form name="'listForm'" action="'searchCEInfo_'" method="'post'">
		<@ww.token name="searchCEInfoToken"/>
		<#include "./ceSearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/CEInfoManager_/saveCEInfo_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>客户名称</th>
			 	<th>客户类型</th>
			 	<th>行业</th>
			 	<th>企业性质</th>
			 	<th>省份</th>
			 	<th>联系人</th>
			 	<th>客服人员</th>
			 	<th>咨询时间</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCEInfo_.html">诗音</a></td>
				<td style="text-align:left">意向</td>
				<td style="text-align:left">金融行业</td>
				<td style="text-align:left">国企</td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">诗音</td>
				<td style="text-align:left">小李</td>
				<td style="text-align:center">2009-11-02</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCEInfo_.html">美佳</a></td>
				<td style="text-align:left">意向</td>
				<td style="text-align:left">服务行业</td>
				<td style="text-align:left">民营</td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">美佳</td>
				<td style="text-align:left">小李</td>
				<td style="text-align:center">2009-11-03</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCEInfo_.html">华少</a></td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">IT行业</td>
				<td style="text-align:left">民营</td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">华少</td>
				<td style="text-align:left">小李</td>
				<td style="text-align:center">2009-11-03</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>