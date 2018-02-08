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
<#-- $Id: contactInfoList.ftl  2009-11-26 11:30:50Z wliu $-->

<#include "../../../includes/macros.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'listContactInfo_'" method="'post'">
		<@ww.token name="listContactInfoToken"/>
		<@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>姓名</th>
			 	<th>性别</th>
			 	<th>手机</th>
			 	<th>类型</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="editContactInfo_OpenDialog();" >余婷婷</a></td>
				<td style="text-align:left">女</td>
				<td style="text-align:left">13900000000</td>
				<td style="text-align:left">熟悉</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left"><a href="#" onclick="editContactInfo_OpenDialog();" >李晨晨</a></td>
				<td style="text-align:left">女</td>
				<td style="text-align:left">13800000000</td>
				<td style="text-align:left">熟悉</td>
			</tr>
		</@listTable>
		<@buttonBar>
			<@vbutton name="'add'"  class="button" value="${'新建'}" onclick="contactInfo_OpenDialog();"/>
            <#assign confirmMessage = "${'您确定删除吗？'}" />
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
        </@buttonBar>
    </@ww.form>
<script>
	function contactInfo_OpenDialog(){
		var url = "${req.contextPath}/customerBasicInfoManager_/saveContactInfo_.html";
		popupModalDialog(url, 800, 600);
	}
	function editContactInfo_OpenDialog(){
		var url = "${req.contextPath}/customerBasicInfoManager_/editContactInfo_.html";
		popupModalDialog(url, 800, 600);
	}
</script>
</@framePage>
