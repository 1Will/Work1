<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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

<#-- $Id: dutyList.ftl 2009-09-18 13:34:35Z wliu $ -->

<#include "../../../includes/macros.ftl" />

<@htmlPage title="联系人信息管理">
	<@ww.form name="'listForm'" action="'listContactArchivesInfo_'" method="'post'">
		<@ww.token name="listContactArchivesInfoToken"/>
		<#include "./contactArchivesSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/contactArchivesManager/saveConArchInfo_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>姓名</th>
			 	<th>性别</th>
			 	<th>手机</th>
			 	<th>类型</th>
			 	<th>职务</th>
			 	<th>客户名称</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/contactArchivesManager/editConArchInfo_.html">盛晓雯</a></td>
				<td style="text-align:left">男</td>
				<td style="text-align:left">15906452187</td>
				<td style="text-align:left">潜在</td>
				<td style="text-align:left">测试员</td>
				<td style="text-align:left">合肥矿物集团</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>