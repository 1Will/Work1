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
<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('techDoc.title')}">
     <@ww.form name="'listSpare'" action="'listSpareDocs'" method="'post'">
	     <#if spare.id?exists>
	     <#assign itemNo=1/>
	      <@ww.hidden name="'spare.id'" value="#{spare.id}"/>
	      <@titleBar title="${action.getText('spareDoc.list')}"/>
	      <@listTable>
	      	<tr>
	      		<th></th>
	      		<th>${action.getText('serialNo')}</th>
	      		<th>${action.getText('spareDoc.name')}</th>
	      		<th>${action.getText('spareDoc.comment')}</th>
	      		<th>${action.getText('spareDoc.createdTime')}</th>
	      		<th>${action.getText('spareDoc.operator')}</th>
	         </tr>
	         <#if (spare.spareDoc.size()>0)>
	         	<#list spare.spareDoc as doc>
	         		<tr>
	         			<td><input type="checkbox" name="spareDocIds" value="#{doc.id}"/></td>
	         			<td class="alignCenter">
	         				<a href="#" onclick="open_uploadDialog(#{spare.id}, #{doc.id});return false;">#{itemNo}</a></td>
	         			<#assign itemNo = itemNo+1/>
		                <td class="alignLeft"><a  title="${action.getText('download')}" href="downloadSpareDoc.html?doc.id=#{doc.id}" >${doc.name?if_exists}</a> </td>
		                <td class="alignLeft">${doc.description?if_exists}</td>
		                <td class="alignCenter">${doc.createdTime?if_exists}</td>
		                <td class="alignLeft">${doc.creator?if_exists}</td>
		             </tr>
		     	  </#list>
		      </#if>
	      </@listTable> 
	         <@buttonBar>
	         	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('spareDoc')}" />
	            <@vbutton name="'upload'" class="button" value="${action.getText('upload')}" onclick="open_uploadDialog(#{spare.id}, null);"/>
	         	<#if (spare.spareDoc.size()>0)>
	         	<@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	                <@ww.param name="'onclick'" value="'return confirmDeletes(\"spareDocIds\", \"${confirmMessage}\");'"/>
	            </@vsubmit>
	            </#if>
	         </@buttonBar>
	      </#if>        
     </@ww.form>
     <script language="javascript">
           /*
            *当该备件失效，该页面所有的控件都失效
           */
         　　<#if spare.enabled?exists>
	  　　　　　<#if !(spare.enabled)>
	   　　　　　　 __disableAllElements__(document.forms[0], new Array(""));
	  　　　　　</#if>
	　　　　</#if>	
	    	function open_uploadDialog(spareId, spareDocId) {
	    		var url;
	    		if (null == spareDocId) {
	    			 url = '${req.contextPath}/popup/editSpareDoc.html?spare.id='+spareId;
	    		} else {
	    			 url = '${req.contextPath}/popup/editModifySpareDoc.html?spare.id='+spareId+ '&doc.id=' + spareDocId;
	    		}
	    		popupModalDialog(url, 650, 300);
	    		self.location.reload();
	    	}
	 </script>
</@framePage>