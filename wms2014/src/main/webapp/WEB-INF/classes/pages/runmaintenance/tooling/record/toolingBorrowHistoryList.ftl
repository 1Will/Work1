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

<@framePage title="${action.getText('borrowHistory.title')}">
     <@ww.form name="'borrowHistory'" action="'listborrowHistorys'" method="'post'">
	     <#if tooling.id?exists>
	     <#assign itemNo=1/>
	         <@titleBar title="${action.getText('borrowHistory.list')}"/>
	             <@listTable>
	        	     <tr>
	                     <th>${action.getText('borrowHistory.serialNo')}</th>
	                     <th>${action.getText('billNo')}</th>
	                     <th>${action.getText('billName')}</th>
	                     <th>${action.getText('usedDevice')}</th>
	                     <th>${action.getText('borrower')}</th>
	                     <th>${action.getText('borrowDateTime')}</th>
	                     <th>${action.getText('producrNumber')}</th>
	                     <th>${action.getText('productTailState')}</th>
	                     <th>${action.getText('checker')}</th>
	                     <th>${action.getText('checkDateTime')}</th>
	                     <th>${action.getText('storeman')}</th>
	                     <th>${action.getText('inDateTime')}</th>
	                 </tr>
	             	 <#if (tooling.toolingBorrowBill.size()>0)>
		             <#list tooling.toolingBorrowBill as borrowBill>
		                <tr>
		                    <td>
		                    	#{itemNo}
		                    </td>
		                    <#assign itemNo = itemNo+1/>
		                    <td>${borrowBill.billNo?if_exists}</td>
		                    <td>${borrowBill.billName?if_exists}</td>
		                    <td>
			                    <#if borrowBill.device?exists>
			                    ${borrowBill.device.name?if_exists}
			                    </#if>
		                    </td>
		                    <td>
			                    <#if borrowBill.borrower?exists>
			                    ${borrowBill.borrower.name?if_exists}
			                    </#if>
		                    </td>
		                    <td>
		                    	<#if borrowBill.borrowReturnDateTm?exists>
		                    	${borrowBill.borrowReturnDateTm?string('yyyy-MM-dd')?if_exists}</td>
		                    	</#if>
		                    <td>${borrowBill.produceNum?if_exists}</td>
		                    <td>${borrowBill.productTailState?if_exists}</td>
		                    <td>
		                      <#if borrowBill.checker?exists>
		                      ${borrowBill.checker.name?if_exists}
		                      </#if>
		                    </td>
		                    <td>
		                    	<#if borrowBill.checkDateTm?exists>
		                    	${borrowBill.checkDateTm?string('yyyy-MM-dd')?if_exists}</td>
		                    	</#if>
		                    <td>
		                      <#if borrowBill.storeman?exists>
		                      ${borrowBill.storeman.name?if_exists}
		                      </#if>
		                    </td>
		                    <td>
		                    	<#if borrowBill.inDateTm?exists>
		                    	${borrowBill.inDateTm?string('yyyy-MM-dd')?if_exists}</td>
		                    	</#if>
		                    </td>
		                </tr>
		     	    </#list>
		            </#if>
	             </@listTable>
	     </#if>
	 </@ww.form>
</@framePage>