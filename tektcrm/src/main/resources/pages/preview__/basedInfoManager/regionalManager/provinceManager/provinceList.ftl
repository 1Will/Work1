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

<#-- $Id: provinceList.ftl 2009-11-26 17:32:35Z wliu $ -->

<#include "../../../../includes/macros.ftl" />

<@htmlPage title="省份信息管理">
	<@ww.form name="'listForm'" action="'searchProvince_'" method="'post'">
		<@ww.token name="searchProvinceToken"/>
		<#include "./provinceSearcher.ftl" />
        <@buttonBar>
			<@vbutton name="'search'" class="button" value="${'查询'}" onclick=""/>
			<#if !(action.isReadOnly())>
			<@redirectButton value="${'新建'}" url="${req.contextPath}/provinceManager_/saveProvince_.html"/>
			</#if>
        </@buttonBar>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>省份编码</th>
			 	<th>省份名称</th>
			 	<th>所属国家</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editProvince_.html">province_001</a></td>
				<td style="text-align:left">安徽省</td>
				<td style="text-align:left">中国</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editProvince_.html">province_002</a></td>
				<td style="text-align:left">福建省</td>
				<td style="text-align:left">中国</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editProvince_.html">province_003</a></td>
				<td style="text-align:left">广东省</td>
				<td style="text-align:left">中国</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editProvince_.html">province_004</a></td>
				<td style="text-align:left">黑龙江省</td>
				<td style="text-align:left">中国</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="editProvince_.html">province_005</a></td>
				<td style="text-align:left">贵州省</td>
				<td style="text-align:left">中国</td>
			</tr>
        </@listTable>
        <#if !(action.isReadOnly())>
	    <@buttonBar>
	        <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
        </#if>
    </@ww.form>
</@htmlPage>