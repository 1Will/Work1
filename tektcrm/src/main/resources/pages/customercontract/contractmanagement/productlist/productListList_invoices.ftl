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

<@htmlPage title="${action.getText('产品选择页面')}">
	<@ww.form name="'listForm'" action="'searchProductList_invoices'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchProductListToken"/>
		 <#if invoicesList?exists&&invoicesList=='invoicesList'> 
			<#include "./productListSearcher.ftl" />
			<@buttonBar>
				<@vsubmit value="'${action.getText('search')}'"/>
				<@vbutton value="选择" class="button" onclick="selectThese()"/>
	        </@buttonBar>
         </#if>
         	 <#if productionOperation?exists&&productionOperation=='productionOperation'> 
			<#include "./productListSearcher.ftl" />
			<@buttonBar>
				<@vsubmit value="'${action.getText('search')}'"/>
	        </@buttonBar>
         </#if>
    	<@ww.hidden name="'contractManagement.id'" value="${contractManagementId?if_exists}"/>
    	<@ww.hidden name="'productionOperation'" value="${productionOperation?if_exists}"/>
    	<@ww.hidden name="'invoicesList'" value="${invoicesList?if_exists}"/>
    	<@ww.hidden name="'customerInfo.id'" value="${customerInfoId?if_exists}"/>
    	 <#assign itemNo=1/>
        <@list title="${action.getText('productList.list')}" 
            includeParameters="contractManagement.id|readOnly|onlyInvalid|onlyValid|contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name|contractManagement.linkman.name|contractManagement.deparment.name|contractManagement.saleman.name|product.mode|product.name|contractManagement.state.id|invoicesList|customerInfoI.id|productionOperation|" 
        	fieldMap="like:contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name|contractManagement.linkman.name|contractManagement.deparment.name|contractManagement.saleman.name|product.mode|product.name|,date:contractManagement.ciemdinghTime_start|contractManagement.ciemdinghTime_end|" >
        	<#if !(action.isReadOnly())>
	         <#if !productionOperation?exists>
	        	<@vlh.checkbox property="id" name="productListIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>  
            </#if>    
           	 <@alignCenter/>
           	 <#if !invoicesList?exists&&!productionOperation?exists>
           	  	<@vcolumn title="${action.getText('序号')}" property="id"  >
                  <a href="###" onclick="editProductList('#{object.id}')">#{itemNo}</a>
	            <@alignLeft attributes="width:20;"/>
	            </@vcolumn >
	         </#if>
	            <#assign itemNo=itemNo + 1/>
	         <#if invoicesList?exists>    
		        <@vcolumn title="${action.getText('合同编码')}"  property="contractManagement.code" >
	        		<a href="javascript: returnDialog(new Array('#{object.id}' ));">
	                    ${object.contractManagement.code}
                	</a>
	            <@alignLeft/>
	         	</@vcolumn>
	         	<@vcolumn title="${action.getText('合同名称')}"  property="contractManagement.contractName" >
	            	<@alignLeft/>
	         	</@vcolumn>
	         	 <@vcolumn title="${action.getText('客户名称')}" property="contractManagement.customerInfo.name" >
	            	<@alignRight/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('联系人')}" property="contractManagement.linkman.name" >
	         		<@alignLeft/>
	         	</@vcolumn>
	        </#if>
	        
	        <#if productionOperation?exists>    
		        <@vcolumn title="${action.getText('合同编码')}"  property="contractManagement.code" >
                	<a href="javascript: returnDialog(new Array('#{object.id}','#{object.contractManagement.id}','${object.contractManagement.contractName}','#{object.product.id}','${object.product.name}','${object.product.model}' ));">
	                    ${object.contractManagement.code}
                	</a>
	            <@alignLeft/>
	         	</@vcolumn>
	         	<@vcolumn title="${action.getText('合同名称')}"  property="contractManagement.contractName" >
	            	<@alignLeft/>
	         	</@vcolumn>
	         	 <@vcolumn title="${action.getText('客户名称')}" property="contractManagement.customerInfo.name" >
	            	<@alignRight/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('联系人')}" property="contractManagement.linkman.name" >
	         		<@alignLeft/>
	         	</@vcolumn>
	        </#if>
	        
	        <@vcolumn title="${action.getText('productList.product')}"  property="product.name" >
            	<@alignLeft/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('productList.model')}"  property="product.model" >
            	<@alignLeft/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('productList.count')}" property="count" >
            	<@alignRight/>
         	</@vcolumn> 
         	 <#if !invoicesList?exists>	
	         	<@vcolumn title="${action.getText('productList.unit')}" property="unit.name" >
	         		<@alignLeft/>
	         	</@vcolumn>
         	 </#if>
            <@vcolumn title="${action.getText('productList.unitPrice')}" property="unitPrice" >
            	#{object.unitPrice?if_exists}
            	<@alignRight/>
         	</@vcolumn>  
         	 <#if !invoicesList?exists>	
	         	<@vcolumn title="${action.getText('productList.discount')}" property="discount" >
	            	#{object.discount?if_exists}
	            	<@alignRight/>
	         	</@vcolumn>
	            <@vcolumn title="${action.getText('productList.totalPrice')}" property="totalPrice" >
	            	#{object.totalPrice?if_exists}
	            	<@alignRight/>
	         	</@vcolumn> 
	         	<@vcolumn title="${action.getText('质检')}" property="qualityControl.name" >
	         		<@alignLeft/>
	         	</@vcolumn>
	         </#if>	
         	<@vcolumn title="${action.getText('计划交付日期')}" property="plannedDeliveryDate" format="yyyy-MM-dd" >
     			<@alignLeft/>
            </@vcolumn>
         	 <@vcolumn title="${action.getText('已交付数量')}" property="deliveryedCount" >
            	<@alignRight/>
         	</@vcolumn>
         	<#if invoicesList?exists>  
	         	 <@vcolumn title="${action.getText('部门')}" property="contractManagement.deparment.name" >
	            	<@alignRight/>
	         	</@vcolumn>
	         	 <@vcolumn title="${action.getText('负责人')}" property="contractManagement.saleman.name" >
	            	<@alignRight/>
	         	</@vcolumn>
	         	 <@vcolumn title="${action.getText('合同状态')}" property="contractManagement.state.name" >
	            	<@alignRight/>
	         	</@vcolumn>
	         	 <@vcolumn title="${action.getText('签订日期')}" property="contractManagement.ciemdinghTime" format="yyyy-MM-dd">
	            	<@alignRight/>
	         	</@vcolumn>
	        </#if>
        </@list>
         <#if !invoicesList?exists>
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
		</#if>
   </@ww.form>
<script language="javascript">
	function editProductList(v){
      var url='${req.contextPath}/productList/editProductList.html?productList.id='+v+'&contractManagementid=${req.getParameter('contractManagement.id')?if_exists}'+"&readOnly=${req.getParameter('readOnly')?if_exists}";
      //popupModalDialog(url,800,600);
      openNewWindow(url);
      if(isIE()){self.location.reload();};
	}
	 //获得模态窗体返回值
	function newProductList(){
      var url='${req.contextPath}/productList/editProductList.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagementid=${req.getParameter('contractManagement.id')?if_exists}';
      //popupModalDialog(url,800,600);
      openNewWindow(url);
      if(isIE()){self.location.reload();};
	}
   function selectThese(){
 	   var name_="";
	   var selector = document.getElementsByName("productListIds");
	   var length = selector.length;
	   if(length){
	        for(var i=0;i<length;i++){
	           if(selector[i].checked == true){
	              var tempName = selector[i].value;
	              if(name_==""){
	             	 name_ =tempName;
	              }else{
	            	 name_+=","+tempName;
	              }
	           }
	        }
	    }
	    returnDialog(new Array(name_));
    }
</script>
</@htmlPage>
