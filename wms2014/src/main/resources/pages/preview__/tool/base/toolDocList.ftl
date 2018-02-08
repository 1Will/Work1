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
<#-- $Id: -->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form name="'listForm'" action="'listDeviceDocs'" method="'post'">
	      <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
	      <@listTable>
	      	<tr>
	      		<th></th>
	      		<th>项目号</th>
	      		<th>资料名称</th>
	      		<th>资料描述</th>
	      		<th>上传时间</th>
	      		<th>创建人</th>
	         </tr>

	         		<tr>
	         			<td><input type="checkbox" name="deviceDocIds" value=""/></td>
	         			<td><a href="#" onclick="open_uploadDialog();return false;">1</a></td>
		                <td><a  title="下载" href="" >定位销修改方案</a> </td>
		                <td>......</td>
		                <td>2007-02-03</td>
		                <td>sa</td>
		             </tr>
		             <tr>
		             	<td><input type="checkbox" name="deviceDocIds" value=""/></td>
		             	<td><a href="#" onclick="open_uploadDialog();return false;">2</a></td>
		                <td><a  title="下载" href="" >套头修改方案</a> </td>
		                <td>......</td>
		                <td>2007-02-03</td>
		                <td>sa</td>
		             </tr>

	      </@listTable> 
	      </table>
	         <@buttonBar>
	         	<#assign confirmMessage = "${action.getText('deviceDoc.deleted')}" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog();"/>

	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"deviceDocIds\", \"${confirmMessage}\");'"/>
	            </@vsubmit>

	         </@buttonBar>
      
     </@ww.form>
     <script language="javascript">
	      var item = '${req.getParameter('item')?if_exists}';
		  var view = '${req.getParameter('view')?if_exists}';
		  if (item==1) {
		    __disableAllElements__(document.forms[0], new Array(""));
		  }
	    	function open_uploadDialog() {
	    		var url = '${req.contextPath}/popup/editToolDoc.html';
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>