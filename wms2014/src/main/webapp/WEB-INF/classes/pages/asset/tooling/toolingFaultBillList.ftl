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

<@framePage title="${action.getText('toolingFaultBillSearch.title')}">
     <@ww.form name="'toolingFaultBill'" action="'listFaultBillAction'" method="'post'">
	     <#if tooling.id?exists>
	     <#assign itemNo=1/>
	         <@titleBar title="${action.getText('faultBill.list')}"/>
	             <@listTable>
	        	     <tr>
	                     <th>${action.getText('borrowHistory.serialNo')}</th>
	                     <th>${action.getText('faultBillNo')}</th>
	                     <th>${action.getText('faultBillName')}</th>
	                     <th>${action.getText('faultManager')}</th>
	                     <th>${action.getText('faultOccurDateTime')}</th>
	                     <th>${action.getText('faultBillstatus')}</th>
	                 </tr>
	             	 <#if (tooling.toolingFaultBill.size()>0)>
		             <#list tooling.toolingFaultBill as faultBilll>
		                <tr>
		                    <td class="alignCenter">
		                    <a href="#" onclick="open_edit_faultDialog(#{faultBilll.id});return false;">#{itemNo}</a>
		                    </td>
		                    <#assign itemNo = itemNo+1/>
		                    <td class="alignLeft">${faultBilll.billNo?if_exists}</td>
		                    <td class="alignLeft">${faultBilll.billName?if_exists}</td>
		                    <td class="alignLeft">${faultBilll.manager.name?if_exists}</td>
		                    <td class="alignCenter">${faultBilll.faultOccurDateTm?string('yyyy-MM-dd')?if_exists}</td>
		                    <#if (faultBilll.faultFlag?string)=='true'>
                                 <#assign status="${action.getText('faultBill.solution')}"/>
                            <#else>
                                 <#assign status="${action.getText('faultBill.noSolution')}"/>
                            </#if>
                            <td class="alignLeft">${status}</td> 
		                </tr>
		     	    </#list>
		            </#if>
	             </@listTable>
	     </#if>
	 </@ww.form>
	 <script>
	    function open_edit_faultDialog(detailId) {
	        var url = '${req.contextPath}/runmaintenance/fault/editToolingFaultBill.html?faultBill.id='+detailId+'&&toolingDevFlag=TOOLING'+'&&IsPopWindow=T';	      		
	      		popupModalDialog(url, 800,400);  	
	      		self.location.reload();	         	    
	    }
	 </script>
</@framePage>