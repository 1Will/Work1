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

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="回访基本信息管理">
	<@ww.form name="'listForm'" action="'listReBaseInfo_'" method="'post'">
		<@ww.token name="listReBaseInfoToken"/>
		<#include "./reBaseInfoSearcher.ftl" />
        <@buttonBar>
			<@vsubmit value="'${'查询'}'" onclick="'checkInvalidParms()'"/>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/reBaseInfoManager/saveReBaseInfo_.html"/>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
        		<th>访问主题</th>
			 	<th>客户</th>
			 	<th>联系人</th>
			 	<th>回访日期</th>
			 	<th>花费时间(时)</th>
			 	<th>回访方式</th>
			 	<th>回访人</th>
			 	<th>继续回访</th>
			 	<th>下次回访日期</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/reBaseInfoManager/editReBaseInfo_.html">业务访问</a></td>
				<td style="text-align:left">丰登实业</td>
				<td style="text-align:left">李先生</td>
				<td style="text-align:center">2008-09-06</td>
				<td style="text-align:right">4</td>
				<td style="text-align:left">登门回访</td>
				<td style="text-align:left">张三丰</td>
				<td style="text-align:left">是</td>
				<td style="text-align:center">2009-10-6</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/reBaseInfoManager/editReBaseInfo_.html">合作讨论</a></td>
				<td style="text-align:left">丰宝水泥集团</td>
				<td style="text-align:left">张无忌</td>
				<td style="text-align:center">2004-04-06</td>
				<td style="text-align:right">5</td>
				<td style="text-align:left">电话回访</td>
				<td style="text-align:left">江泽民</td>
				<td style="text-align:left">否</td>
				<td style="text-align:center"></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/reBaseInfoManager/editReBaseInfo_.html">经济往来</a></td>
				<td style="text-align:left">金鑫矿业</td>
				<td style="text-align:left">李小毛</td>
				<td style="text-align:center">2005-09-06</td>
				<td style="text-align:right">4</td>
				<td style="text-align:left">网络回访</td>
				<td style="text-align:left">陈涛</td>
				<td style="text-align:left">是</td>
				<td style="text-align:center">2009-10-4</td>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="${req.contextPath}/reBaseInfoManager/editReBaseInfo_.html">合同事项</a></td>
				<td style="text-align:left">金鑫矿业</td>
				<td style="text-align:left">李先生</td>
				<td style="text-align:center">2008-03-06</td>
				<td style="text-align:right">4</td>
				<td style="text-align:left">电话回访</td>
				<td style="text-align:left">张三丰</td>
				<td style="text-align:left">是</td>
				<td style="text-align:center">2009-10-6</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
</@htmlPage>