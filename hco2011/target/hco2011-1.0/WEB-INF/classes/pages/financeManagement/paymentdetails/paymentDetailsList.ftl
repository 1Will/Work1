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

<#-- $Id: customerInfoList.ftl 2009-12-11 8:48:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'searchPaymentDetailsAction'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchPaymentDetailsActionToken"/>
		<#include "./paymentDetailsSearcher.ftl" />
        <@list title="${action.getText('paymentDetails.list')}" 
            includeParameters="paymentDetails.paymentorder.id|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="paymentDetailsIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>   
           	 <@alignCenter/>
	        <@vcolumn title="${action.getText('paymentDetails.paymentorder')}"  property="id" >
	         <a href="###" onclick="editProductList('#{object.id}')">${object.paymentorder.code}</a>
            	<@alignLeft/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('paymentDetails.paymentMode')}" property="paymentMode.name" >
            	<@alignRight/>
         	</@vcolumn> 
         	<@vcolumn title="${action.getText('paymentDetails.stipple')}" property="stipple.name" >
         		<@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('paymentDetails.account')}" property="account" >
            	<@alignRight/>
         	</@vcolumn>   	
         	<@vcolumn title="${action.getText('paymentDetails.moneyType')}" property="moneyType.name" >
            	<@alignRight/>
         	</@vcolumn>
        </@list>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newProductList()"/>
		  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('paymentDetails.confirmMessage')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"paymentDetailsIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
		</@buttonBar>	
		</#if>
   </@ww.form>
</@framePage>
<script language="javascript">
	function editProductList(v){
      var url='${req.contextPath}/paymentDetails/editPaymentDetailsAction.html?paymentDetails.id='+v+'&paymentorderid='+'${req.getParameter('paymentorder.id')?if_exists}'+'&readOnly=${req.getParameter('readOnly')?if_exists}';
      popupModalDialog(url,800,600);
      self.location.reload();
	}
	function newProductList(){
      var url='${req.contextPath}/paymentDetails/editPaymentDetailsAction.html?paymentorderid='+'${req.getParameter('paymentorder.id')?if_exists}';
      popupModalDialog(url,800,600);
      self.location.reload();
	}
	function checkInvalidParms(){
		return true;
    }
</script>
