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
<#-- $Id: purchaseInfoSelector.ftl 2009-09-27 14:00:50Z wliu $-->
<#include "../../../includes/eam2008.ftl" />

<@framePage title="">
<@ww.form  name="'listForm'" action="'listPurchaseInfo'" method="'post'">
	<@ww.token name="listPurchaseInfoToken"/>
	<#assign itemNo=1/>
	<@list title="" 
		includeParameters="deviceId" fieldMap="like:" excel=false setupTable=false >
		<@vcolumn title="${action.getText('purchaseInfo.itemNo')}">
			${itemNo}
			<@alignCenter/>
		</@vcolumn>
		<#assign itemNo = itemNo+1/>
		<@vcolumn title="${action.getText('purchaseInfo.billNo')}" property="purchaseBill.billNo" >
        	<#--${object.billNo}-->
        	<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('purchaseInfo.purchaseDate')}" property="purchaseBill.purchaseDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('purchaseInfo.reqDeliveryDate')}" property="reqDeliveryDate" format="yyyy-MM-dd">
			<@alignCenter/>
		</@vcolumn>
		<@vcolumn title="${action.getText('purchaseInfo.buyer')}" property="purchaseBill.buyer.name">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('purchaseInfo.totalPrice')}" property="purchaseBill.totalPrice">
			<@alignRight/>
		</@vcolumn>
		<@vcolumn title="${action.getText('purchaseInfo.supplierName')}" property="supplierName">
			<@alignLeft />
		</@vcolumn>
		
	</@list>
</@ww.form>
</@framePage>