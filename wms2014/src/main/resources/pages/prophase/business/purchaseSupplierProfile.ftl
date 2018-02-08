<#--
   Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
   Rights Reserved.
   
   This software is the confidential and proprietary information of YongJun
   Digital Information Technology Co.,Ltd. ("Confidential Information"). You
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
<#--$Id: purchaseSupplierProfile.ftl 10914 2008-02-14 01:50:22Z qsun $ -->

<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('supplierProfile')}">	
		
	<@ww.form name="'supplier'" action="'viewSupplier'" method="'post'">
		<@ww.token name="viewSupplierToken"/>
		<@ww.hidden name="'supplier.id'" value="'${req.getParameter('supplier.id')?if_exists}'"/>
		
		<#if supplier?exists>
		<@inputTable>
			<tr>
			    <@ww.textfield label="'${action.getText('supplier.code')}'" name="'supplier.code'" value="'${supplier.supplierNo?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.name')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('supplier.category')}'" name="'supplier.code'" value="'${supplier.supplierType.value?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.regFunds')}'" name="'supplier.name'" value="'${supplier.registeredFunds?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('supplier.belongsTo')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.property')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
			    <@ww.textfield label="'${action.getText('supplier.country')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.zone')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.quality')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.corporater')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.contact')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.telphone')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.fax')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.postcode')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.address')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textfield label="'${action.getText('supplier.email')}'" name="'supplier.code'" value="'${supplier.code?if_exists}'"  cssClass="'underline'" required="true" />
			    <@ww.textfield label="'${action.getText('supplier.website')}'" name="'supplier.name'" value="'${supplier.name?if_exists}'"  cssClass="'underline'" required="true" />
			</tr>
			<tr>
				<@ww.textarea label="'${action.getText('comment')}'"  name="'supplier.comment'" value="'${supplier.name?if_exists}'" rows="'3'" cols="'80'" />
			</tr>
		</@inputTable>
		<#else>
			<label>please choose supplier from list</label>
		</#if>
	</@ww.form>
</@framePage>