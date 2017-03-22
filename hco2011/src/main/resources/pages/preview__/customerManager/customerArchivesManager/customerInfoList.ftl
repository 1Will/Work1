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

<#-- $Id: customerInfoList.ftl 2009-11-26 8:50:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="客户档案管理">
	<@ww.form name="'listForm'" action="'searchCustomerInfo_'" method="'post'">
		<@ww.token name="searchCustomerInfoToken"/>
		<#include "./customerInfoSearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/customerBasicInfoManager_/saveCustomerInfo_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>客户编码</th>
			 	<th>客户名称</th>
			 	<th>客户类型</th>
			 	<th>行业</th>
			 	<th>企业性质</th>
			 	<th>省份</th>
			 	<th>主要联系人</th>
			 	<th>公司法人</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCustomerInfo_.html">customer_001</a></td>
				<td style="text-align:left">大连飞龙科技</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">IT行业</td>
				<td style="text-align:left">国营</td>
				<td style="text-align:left">辽宁</td>
				<td style="text-align:left">刘**</td>
				<td style="text-align:left">刘**</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCustomerInfo_.html">customer_002</a></td>
				<td style="text-align:left">安徽凤飞实业</td>
				<td style="text-align:left">已签</td>
				<td style="text-align:left">厂矿</td>
				<td style="text-align:left">民营</td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">李**</td>
				<td style="text-align:left">李**</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCustomerInfo_.html">customer_003</a></td>
				<td style="text-align:left">无锡百仕达服饰</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">服装</td>
				<td style="text-align:left">民营</td>
				<td style="text-align:left">江苏</td>
				<td style="text-align:left">张**</td>
				<td style="text-align:left">张**</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editCustomerInfo_.html">customer_004</a></td>
				<td style="text-align:left">安徽实德养殖基地</td>
				<td style="text-align:left">意向</td>
				<td style="text-align:left">养殖业</td>
				<td style="text-align:left">民营</td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">张旭敏</td>
				<td style="text-align:left">李**</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>