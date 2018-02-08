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
		<@ww.hidden name="'refreashFlag'" value="'${req.getParameter('refreashFlag')?if_exists}'"/>
		<@ww.token name="searchProductListToken"/>
    	<@ww.hidden name="'contractManagement.id'" value="${req.getParameter('contractManagement.id')?if_exists}"/>
    	 <#assign itemNo=1/>
        <@list title="${action.getText('productList.list')}" 
            includeParameters="contractManagement.id|readOnly|onlyInvalid|onlyValid|contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name|contractManagement.linkman.name|contractManagement.deparment.name|contractManagement.saleman.name|product.mode|product.name|contractManagement.state.id|invoicesList" 
        	fieldMap="like:contractManagement.code|contractManagement.contractName|contractManagement.customerInfo.name|contractManagement.linkman.name|contractManagement.deparment.name|contractManagement.saleman.name|product.mode|product.name|,date:contractManagement.ciemdinghTime_start|contractManagement.ciemdinghTime_end|" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="productListIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>   
           	 <@alignCenter/>
       	  	<@vcolumn title="${action.getText('序号')}" property="id"  >
              <a href="###" onclick="editProductList('#{object.id}')">#{itemNo}</a>
            <@alignLeft attributes="width:20;"/>
            </@vcolumn >
            <#assign itemNo=itemNo + 1/>
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
         	<@vcolumn title="${action.getText('productList.discount')}" property="discount" >
            	#{object.discount?if_exists}
            	<@alignRight/>
         	</@vcolumn>
            <@vcolumn title="${action.getText('productList.totalPrice')}" property="totalPrice" >
            	#{object.totalPrice?if_exists}
            	<@alignRight/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('生产状态')}" property="proStatuString" >
            	<@alignLeft/>
         	</@vcolumn>  
         	<@vcolumn title="${action.getText('质检')}" property="qualityControl.name" >
         		<@alignLeft/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('计划交付日期')}" property="plannedDeliveryDate" format="yyyy-MM-dd" >
     			<@alignLeft/>
            </@vcolumn>
         	 <@vcolumn title="${action.getText('已交付数量')}" property="deliveryedCount" >
            	<@alignRight/>
         	</@vcolumn>
         	 <@vcolumn title="${action.getText('未发数量')}" property="deliveryedCount" >
         	 	#{object.count-object.deliveryedCount?if_exists}
            	<@alignRight/>
         	</@vcolumn>
        </@list>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  	<@vbutton class="button" value="${action.getText('new')}" onclick="newProductList()"/>
	  		<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('productList.confirmMessage')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'" onclick="'return addRefreshFlag();'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"productListIds\", \"${confirmMessage}\");'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
		</@buttonBar>	
		</#if>
   </@ww.form>
</@framePage>
<script language="javascript">
	//判断是否需要刷新上层 页面
    if(getObjByName('refreashFlag').value != ""){
   		self.parent.location.reload();
    }
   
	function checkInvalidParms(){
		document.forms['listForm'].submit();
	}
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
    //增加刷新上层窗口flag
	function addRefreshFlag(){
		getObjByName('refreashFlag').value = "refreashFlag";
	}
</script>
