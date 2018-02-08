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
<#-- $Id: $ -->
<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('signatureLogs.title')}">
     <@ww.form name="'signatureLogsForm'" action="'listSignatureLogs'" method="'post'">
	     <#if job.id?exists>
	     	<#assign itemNo=1/>
	         <@titleBar title="${action.getText('listSignatureLogs.title')}"/>
	             <@listTable>
	        	     <tr>
	                     <th>${action.getText('itemNo')}</th>
	                     <th>${action.getText('doc.result')}</th>
	                     <th>${action.getText('doc.approver')}</th>
	                     <th>${action.getText('doc.approvedTime')}</th>
	                     <th>${action.getText('doc.docText')}</th>
	                 </tr>
	                 <#if (jobApprovers.size()>0)>
			             <#list jobApprovers as approver>			             	
			                <tr>		
			                    <td>#{itemNo}</td>
			                    <#assign itemNo = itemNo+1/>
			                    <td>${approver.docState.value?if_exists} </td>
			                    <td>${approver.approver.name?if_exists}</td>
			                    <td>${approver.createdTime?if_exists}</td>
			                    <td>${approver.job.comment?if_exists}</td>
			                </tr>
			     	    </#list>
		            </#if>
	             </@listTable>	             
	     </#if>	    
	 </@ww.form>
</@framePage>