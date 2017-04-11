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

<#include "../../../includes/hco2011.ftl" />

<@framePage>
	<@ww.form name="'listForm'" action="'searchProductList'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchProductListToken"/>
		<#include "./productListSearcher.ftl" />
    	<@ww.hidden name="'contractManagement.id'" value="${req.getParameter('contractManagement.id')?if_exists}"/>
        <@list title="${action.getText('productList.list')}" 
            includeParameters="contractManagementid|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="productListIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>   
           	 <@alignCenter/>
	        <@vcolumn title="${action.getText('productList.product')}"  property="product.name" >
	         <a href="###" onclick="editProductList('#{object.id}')"><#if object.product?exists>${object.product.name?if_exists}</#if></a>
            	<@alignLeft/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('productList.count')}" property="count" >
            	<@alignRight/>
         	</@vcolumn> 
         	<@vcolumn title="${action.getText('productList.unit')}" property="unit.name" >
         		<@alignLeft/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('productList.unitPrice')}" property="unitPrice" >
            	#{object.unitPrice?if_exists}
            	<@alignRight/>
         	</@vcolumn>   	
         	<@vcolumn title="${action.getText('productList.discount')}" property="discount" >
            	#{object.discount?if_exists}
            	<@alignRight/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('productList.totalPrice')}" property="totalPrice" >
            	#{object.totalPrice?if_exists}
            	<@alignRight/>
         	</@vcolumn> 
        </@list>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newProductList()"/>
		  	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('productList.confirmMessage')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"productListIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
		</@buttonBar>	
		</#if>
   </@ww.form>
</@framePage>
<script language="javascript">
	function editProductList(v){
      var url='${req.contextPath}/productList/editProductList.html?productList.id='+v+'&contractManagementid='+${req.getParameter('contractManagement.id')?if_exists}+"&readOnly=${req.getParameter('readOnly')?if_exists}";
      popupModalDialog(url,800,600);
      if(isIE()){self.location.reload();};
	}
	 //获得模态窗体返回值
	function newProductList(){
      var url='${req.contextPath}/productList/editProductList.html?contractManagementid='+${req.getParameter('contractManagement.id')?if_exists};
      popupModalDialog(url,800,600);
      if(isIE()){self.location.reload();};
	}
	function checkInvalidParms(){
		return true;
    }
</script>
