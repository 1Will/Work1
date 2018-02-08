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
     <@ww.form name="'listToolingChange'" action="'listToolingChangeDocs'" method="'post'">
     <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <#if toolingChangeBill.id?exists>
	     <#assign itemNo=1/>
	      <@ww.hidden name="'toolingChangeBill.id'" value="#{toolingChangeBill.id}"/>
	      <@titleBar title="${action.getText('toolingChangeDoc.list')}"/>
	      <@listTable>
	      	<tr>
	      		<th></th>
	      		<th>${action.getText('serialNo')}</th>
	      		<th>${action.getText('toolingChangeDoc.no')}</th>
	      		<th>${action.getText('toolingChangeDoc.name')}</th>
	      		<th>${action.getText('toolingChangeDoc.comment')}</th>
	      		<th>${action.getText('toolingChangeDoc.createdTime')}</th>
	      		<th>${action.getText('toolingChangeDoc.operator')}</th>
	         </tr>
	         <#if (toolingChangeBill.changeDoc.size()>0)>
	         	<#list toolingChangeBill.changeDoc as doc>
	         		<tr>
	         			<td><input type="checkbox" name="toolingChangeDocIds" value="#{doc.id}"/></td>
	         			<td class="alignCenter">
	         				<a href="#" onclick="open_uploadDialog(#{toolingChangeBill.id}, #{doc.id});return false;">#{itemNo}</a></td>
	         			<#assign itemNo = itemNo+1/>
	         			<td class="alignLeft">${doc.fileNo?if_exists}</td>
		                <td class="alignLeft"><a  title="${action.getText('download')}" href="downloadToolingChangeDoc.html?doc.id=#{doc.id}" >${doc.name?if_exists}</a> </td>
		                <td class="alignLeft">${doc.description?if_exists}</td>
		                <td class="alignCenter">${doc.createdTime?string('yyyy-MM-dd')?if_exists}</td>
		                <td class="alignLeft">${doc.creator?if_exists}</td>
		             </tr>
		     	  </#list>
		      </#if>
	      </@listTable> 
	         <@buttonBar>
	            <#if !(action.isReadOnly())>
	         	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('changeDoc')}" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{toolingChangeBill.id}, null);"/>
	         	<#if (toolingChangeBill.changeDoc.size()>0)>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"toolingChangeDocIds\", \"${confirmMessage}\");'"/>
	            </@vsubmit>
	            </#if>
	            </#if>
	         </@buttonBar>
	      </#if>        
     </@ww.form>
     <script language="javascript">
	    	function open_uploadDialog(toolingChangeBillId, toolingChangeDocId) {
	    		var url;
	    		if (null == toolingChangeDocId) {
	    			 url = '${req.contextPath}/popup/editToolingChangeDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&toolingChangeBill.id='+toolingChangeBillId;
	    		} else {
	    			 url = '${req.contextPath}/popup/editToolingChangeDoc.html?readOnly=${req.getParameter('readOnly')?if_exists}&toolingChangeBill.id='+toolingChangeBillId+ '&toolingChangeDoc.id=' + toolingChangeDocId;
	    		}
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>