<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: monthReport.ftl 2010-04-02 8:32:35Z wliu $ -->

<#include "../../includes/macros.ftl" />

<@htmlPage title="客户联系月报表管理">
	<@ww.form name="'listForm'" action="'searchMonthReport_'" method="'post'">
		<@ww.token name="searchMonthReportToken"/>
		<@inputTable>
		<tr>
			<@pp.datePicker 
				label="'月份'" 
				name="'month'" 
	   			value="''"
				cssClass="'underline'" 
				dateFormat="'%Y-%m'">
			</@pp.datePicker>
			<@ww.textfield label="'开拓者'" name="'keyPerson'" value="''" cssClass="'underline'"/>
		</tr>
		</@inputTable>
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>客户编码</th>
			 	<th>客户名称</th>
			 	<th>客户类型</th>
			 	<th>国家</th>
			 	<th>省份</th>
			 	<th>城市</th>
			 	<th>行业</th>
			 	<th>企业性质</th>
			 	<th>内容</th>
			 	<th>后期注意</th>
			 	<th>月份</th>
			 	<th>开拓者</th>
			 	<th>跟踪者</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">cust_001</td>
				<td style="text-align:left">华为科技</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">中国</td>
				<td style="text-align:left">江苏</td>
				<td style="text-align:left">南京</td>
				<td style="text-align:left">IT行业</td>
				<td style="text-align:left">国营</td>
				<td style="text-align:left">了解情况</td>
				<td style="text-align:left">继续跟踪</td>
				<td style="text-align:left">2010-04</td>
				<td style="text-align:left">刘</td>
				<td style="text-align:left">刘</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">cust_002</td>
				<td style="text-align:left">紫光科技</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">中国</td>
				<td style="text-align:left">上海</td>
				<td style="text-align:left"></td>
				<td style="text-align:left">IT行业</td>
				<td style="text-align:left">国营</td>
				<td style="text-align:left">了解情况</td>
				<td style="text-align:left">继续跟踪</td>
				<td style="text-align:left">2010-04</td>
				<td style="text-align:left">刘</td>
				<td style="text-align:left">刘</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">cust_003</td>
				<td style="text-align:left">飞龙科技</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">中国</td>
				<td style="text-align:left">辽宁</td>
				<td style="text-align:left">大连</td>
				<td style="text-align:left">IT行业</td>
				<td style="text-align:left">国营</td>
				<td style="text-align:left">了解情况</td>
				<td style="text-align:left">继续跟踪</td>
				<td style="text-align:left">2010-04</td>
				<td style="text-align:left">刘</td>
				<td style="text-align:left">刘</td>
			</tr>
        </@listTable>
        <@buttonBar>
			<@vbutton name="'printReport'" class="button" value="${'打印报表'}" onclick=""/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>