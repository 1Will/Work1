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

<@framePage title="附件资料">
	<@ww.form name="'listForm'" action="'listAccessoriesInfo_'" method="'post'">
		<@ww.token name="listAccessorInfoToken"/>
        <@listTable>
        	<tr>
        		<th><input type="checkbox" name="checkbox" value="true"></th>
			 	<th>资料名称</th>
			 	<th>上传人</th>
			 	<th>上传时间</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
		     	<td style="text-align:left">
			       <a href="#" onClick="scorePeople_OpenDialog();">
		    	     中文指导手册
		    	   </a>
	       		</td>
				<td style="text-align:left">刘先生</td>
				<td style="text-align:center">2009-10-6</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">
			       <a href="#" onClick="scorePeople_OpenDialog();">
		    	     技术方案书
		    	   </a>
	       		</td>
				<td style="text-align:left">张小文</td>
				<td style="text-align:center">2008-09-07</td>
			</tr>
        </@listTable>
	    <@buttonBar>
	        <@vbutton value="${'上传'}" class="button" onclick="Open();"/>
	        <@vsubmit name="'delete'" value="'${'删除'}'"/>
	    </@buttonBar>
    </@ww.form>
<script type="text/javascript">
function scorePeople_OpenDialog(){
	   var url = "${req.contextPath}/reBaseInfoManager/editAccessoriesInfo_.html";
	   popupModalDialog(url, 800, 600);
	 }
function Open(){
	   var url = "${req.contextPath}/reBaseInfoManager/uploadAccessoriesInfo_.html";
	   popupModalDialog(url, 800, 600);
	 }
</script>
</@framePage>