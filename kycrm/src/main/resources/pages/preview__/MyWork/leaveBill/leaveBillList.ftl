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

<@htmlPage title="请假/调休管理查询">
	<@ww.form name="'listForm'" action="'listLeaveBill_'" method="'post'">
		<@ww.token name="searchCEInfoToken"/>
		<#include "./leaveBillSearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/leaveBill_/saveLeaveBill_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>编号</th>
			 	<th>申请人</th>
			 	<th>部门</th>
			 	<th>事由</th>
			 	<th>状态</th>
			 	<th>开始时间</th>
			 	<th>结束时间</th>
			 	<th>创建时间</th>
			 	<th>审批人</th>
			 	<th>时长（天）</th>
			 	<th>类型</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editLeaveBill_.html">001</a></td>
				<td style="text-align:left">管侯明</td>
				<td style="text-align:left">研发部</td>
				<td style="text-align:left">去富银公司...</td>
				<td style="text-align:left">通过</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:center">2011-01-16 18:00</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:left">陈晓松</td>
				<td style="text-align:right">2</td>
				<td style="text-align:left">调休</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCEInfo_.html">002</a></td>
				<td style="text-align:left">贾宏</td>
				<td style="text-align:left">研发部</td>
				<td style="text-align:left">去山水公司...</td>
				<td style="text-align:left">通过</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:center">2011-01-16 18:00</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:left">陈晓松</td>
				<td style="text-align:right">2</td>
				<td style="text-align:left">调休</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCEInfo_.html">001</a></td>
				<td style="text-align:left">梁戈</td>
				<td style="text-align:left">研发部</td>
				<td style="text-align:left">去瑶海分局...</td>
				<td style="text-align:left">通过</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:center">2011-01-16 18:00</td>
				<td style="text-align:center">2011-01-16 13:30</td>
				<td style="text-align:left">陈晓松</td>
				<td style="text-align:right">2</td>
				<td style="text-align:left">调休</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>