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

<@framePage title="${action.getText('supplierBusinessHistory.title')}">
     <@ww.form name="'supplierBusinessHistory'" action="'listsupplierBusinessHistory'">
     	<@ww.hidden name="'supplier.id'" value="'${req.getParameter('supplier.id')?if_exists}'"/>
	    	<#assign itemNo=1/>
	        <@titleBar title="${action.getText('supplierBusinessHistory.list')}"/>
     	    <@listTable>
     	        <tr>
	                <th>${action.getText('supplierBusinessHistory.serialNo')}</th>
	                <th>${action.getText('supplierBusinessHistory.purchaseBillNo')}</th>
	                <th>${action.getText('supplierBusinessHistory.purchaseBillName')}</th>
	                <th>${action.getText('supplierBusinessHistory.receiptBillNo')}</th>
	                <th>${action.getText('supplierBusinessHistory.purchaseTime')}</th>
	                <th>${action.getText('supplierBusinessHistory.receiptTime')}</th>
	                <th>${action.getText('supplierBusinessHistory.productPiece')}</th>
	                <th>${action.getText('supplierBusinessHistory.productAccount')}</th>
	                <th>${action.getText('supplierBusinessHistory.comment')}</th>
	            </tr>
	            <#if (supplier.supplierBusinessHistory.size()>0)>
		        <#list supplier.supplierBusinessHistory as supplierBusinessHistory>
		        <tr>
		            <td class="alignCenter">
		            	#{itemNo} 
		            </td>
		            <#assign itemNo = itemNo+1/>
		            <td class="alignLeft">
		            
			            <#if supplierBusinessHistory.purchaseBill?exists>
			            <a href="#" onclick="open_purchaseBillDialog(#{supplierBusinessHistory.purchaseBill.id});return false;">
			            	${supplierBusinessHistory.purchaseBill.billNo?if_exists}
			            </a>
			            </#if>
			        
		            </td>
		            <td class="alignLeft">
		            	<#if supplierBusinessHistory.purchaseBill?exists>
			            	${supplierBusinessHistory.purchaseBill.name?if_exists}
			            </#if>
		            </td>
		            <td class="alignLeft">
		            	<#if supplierBusinessHistory.receiptBill?exists>
		            	<a href="#" onclick="open_receiptBillDialog(#{supplierBusinessHistory.receiptBill.id});return false;">
		            		${supplierBusinessHistory.receiptBill.billNo?if_exists}
		            	 </a>
		            	</#if>
		            </td>
		            <td class="alignLeft">
		            	<#if supplierBusinessHistory.purchaseBill?exists>
			            	${supplierBusinessHistory.purchaseBill.purchaseDateTm?if_exists}
			            </#if>
		            </td>
		            <td class="alignLeft">
		            	<#if supplierBusinessHistory.receiptBill?exists>
		            		${supplierBusinessHistory.receiptBill.receiptDateTm?if_exists}
		            	</#if>
		            </td>
		            <td class="alignLeft">
		            	<#assign productPiece=0/>
		            	<#if supplierBusinessHistory.purchaseBill?exists>
		            	<#if ((supplierBusinessHistory.purchaseBill.purchaseBillDetail).size()>0)>
		            	<#list supplierBusinessHistory.purchaseBill.purchaseBillDetail as purchaseBillDetail>
		            		<#assign productPiece = productPiece + purchaseBillDetail.piece/>
		            	</#list>
		            	</#if>
		            	</#if>
		            	#{productPiece}
		            </td>
		            <td class="alignLeft">
		            	<#assign productAccount=0/>
		            	<#if supplierBusinessHistory.purchaseBill?exists>
		            	<#if ((supplierBusinessHistory.purchaseBill.purchaseBillDetail).size()>0)>
		            	<#list supplierBusinessHistory.purchaseBill.purchaseBillDetail as purchaseBillDetail>
		            		<#assign productAccount = productAccount + purchaseBillDetail.amount/>
		            	</#list>
		            	</#if>
		            	</#if>
		            	#{productAccount}
		            </td>
		            <td class="alignLeft">
		            	<#if supplierBusinessHistory.receiptBill?exists>
		            		${supplierBusinessHistory.receiptBill.comment?if_exists}
		            	</#if>
		            </td>
		        </tr>
		        </#list>
		        </#if>
     	    </@listTable>
	     <script language="javascript">
	         function open_purchaseBillDialog(purchaseBillId) {
	      		var url = '${req.contextPath}/popup/editProduct.html?supplier.id=' + supplierId;	 
	      		if (productId != null) {
	      			url = url + "&product.id=" + productId;
	      		}
	      		popupModalDialog(url, 650,300);
	      		self.location.reload();
	      	 }
	      	 
	      	 function open_receiptBillDialog(receiptBillId) {
	      	 
	      	 }
	    </script>
     </@ww.form>
</@framePage>