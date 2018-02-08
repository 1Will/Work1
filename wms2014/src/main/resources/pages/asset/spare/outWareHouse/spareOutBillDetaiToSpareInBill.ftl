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
<#--$Id: spareOutBillDetailList.ftl 11328 2008-09-25 09:39:30Z smzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareOutBillDetail.title')}">
 
  <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
	<@ww.form namespace="'/spare'" name="'listForm'" action="'seacherOpenSpareOutBillDetail'" method="'post'">
	 
	    <#include "./openSpareOutBillDetailSeacher.ftl" />
	
		<@ww.token name="seacherOpenSpareOutBillDetailToken"/>
	 
	     <@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
	    <@buttonBar>
           <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        </@buttonBar>
		<#assign itemNo=1/>
		<@list title="${action.getText('list.title')}" 
		   excel=false setupTable=false 
		    includeParameters="spareOutBill.id|spareOutBillName|spareOutBillCode|spareNo|spareName|spareModelSpecs|spareType.id|spareDetailType.id|wareHouse.id|outWarehouseId" 
		    fieldMap="like:spareNo|spareName|spareModelSpecs|">
			<@vlh.checkbox property="id" name="spareOutBillDetailIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('spareOutBillDetail.itemNo')}">
                ${itemNo}
                <@alignCenter/>
            </@vcolumn>
             <#assign itemNo = itemNo + 1/>
               <@vcolumn title="${action.getText('出库单名称')}" property="spareOutBill.name" >
              	<@alignLeft />
             </@vcolumn>
             
               <@vcolumn title="${action.getText('spareOutBill.code')}" property="spareOutBill.code" >
              	<@alignLeft />
             </@vcolumn>
             
             <@vcolumn title="${action.getText('spareOutBillDetail.spareNo')}" property="code" >
              	<@alignLeft />
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBillDetail.name')}" property="name" >
              	<@alignLeft />
             </@vcolumn>
              <@vcolumn title="${action.getText('型号')}" property="spare.modelSpecs" >
              	<@alignLeft />
             </@vcolumn>
            
             <@vcolumn title="${action.getText('spareOutBillDetail.spareType')}" property="spare.category.name" >
              	<@alignLeft />
             </@vcolumn>
             <#--
             <@vcolumn title="${action.getText('spareOutBillDetail.spareDetailType')}" property="spare.spareDetailType.name" >
              	<@alignLeft />
             </@vcolumn>
           -->
             
             <#assign number = ''/>
             <#if '${object.number}'=='0'>
             	<#assign number = ''/>
             <#else>
             	<#assign number = '${object.number}'/>
             </#if>
            
			<#if object.status?exists>
				<#if '${object.status}' == 'OUTWAREHOUSEED'>
                	<@vcolumn title="出库数量" format="${action.getText('currencyFormat')}">
            			<input type="text" name="number" class="underline"  value="${number}" onchange="changValue()" maxlength="250" size="7" style="text-align:right" disabled/>
            			<@alignRight/>
             		</@vcolumn>
				</#if>
				<#if '${object.status}' == 'UNOUTWAREHOUS'>
					<@vcolumn title="出库数量" format="${action.getText('currencyFormat')}">
						<input type="text" name="number" class="underline"  value="${number}" onchange="changValue()" maxlength="250" size="7" style="text-align:right"/>
            			<@alignRight/>
             		</@vcolumn>
            </#if>   
            </#if>
	    	 <@vcolumn title="进库数量" property="inNumber" >
	          	<@alignRight />
	         </@vcolumn> 
         	<#-- 使用设备
            <@vcolumn title="${action.getText('使用设备')}" >
            <input type="text" name="number" class="underline"  value="" onchange="" maxlength="250" size="7" style="text-align:right"/>
				<@alignLeft />
            </@vcolumn>      -->
            <#-- 所属班组 
            <@vcolumn title="${action.getText('所属班组')}" >
            <input type="text" name="number" class="underline"  value="" onchange="" maxlength="250" size="7" style="text-align:right"/>
				<@alignLeft />
            </@vcolumn>       --> 
                     
             <@vcolumn title="${action.getText('spareOutBillDetail.unitPrice')}" format="${action.getText('currencyFormat')}">
                   ${(object.unitPrice?string('#,###,##0.00'))?if_exists}<input type="hidden" name="unitPrice" value="#{object.unitPrice?if_exists}"  class="underline" disabled="true" readonly="true" />
              	<@alignRight/>
             </@vcolumn>
             
             <@vcolumn title="${action.getText('spareOutBillDetail.totalPrice')}" format="${action.getText('currencyFormat')}">
                       <input type="text" name="totalPrice" 
			    		    class="noBorderLine"  value="${(object.totalPrice?string('#,###,##0.00'))?if_exists}" disabled="true" maxlength="250" size="7"/>
              	<@alignRight />
             </@vcolumn>
             <@vcolumn title="来源仓库" property="spareOutBill.warehouse.name" >
                  <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="入仓库" property="spareOutBill.inWarehouse.name" >
                  <@alignLeft/>
             </@vcolumn>
             <#--
             <@vcolumn title="${action.getText('spareOutBillDetail.stocksBeforeInOrOut')}" property="stocksBeforeInOrOut" >	
          		          <input type="text" name="stocksBeforeInOrOut" 
			    		      class="noBorderLine"  value="${object.stocksBeforeInOrOut}" disabled="true" maxlength="250" size="7"/>
          		<@alignRight />
             </@vcolumn>
            <@vcolumn title="${action.getText('spareOutBillDetail.stocksAfterInOrOut')}" property="stocksAfterInOrOut" >
             <@alignRight />
             </@vcolumn>
             -->
              <@ww.hidden name="'hiddenStocks'" value="${object.stocksAfterInOrOut?if_exists}"/>  
              <#assign spareOutDtlStatus = ''/>
            <#if object.status?exists>
              <#if '${object.status}' == 'UNOUTWAREHOUS'>
               <#assign spareOutDtlStatus = "${action.getText('UNOUTWAREHOUS')}"/>
               </#if>
              <#if '${object.status}' == 'OUTWAREHOUSEED'>
              <#assign spareOutDtlStatus = "${action.getText('OUTWAREHOUSEED')}"/>
            </#if>
            </#if>
               <@vcolumn title="${action.getText('status')}" attributes="width='50'">
           ${spareOutDtlStatus}
                 <@alignLeft/>
            </@vcolumn>
		</@list>
        <@buttonBar>
        	<#if !first>
        	<#if !(action.isReadOnly())>
           	<@vsubmit name="'select'" value="'${action.getText('选择')}'" onclick="'return confirmSelects(\"spareOutBillDetailIds\");'" >
           		<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
           	 	</@vsubmit>	
            </#if>
            </#if>
        </@buttonBar>
	</@ww.form>
 <script>
      function confirmSelects(boxname) {
       if (!hasChecked(boxname)) {
        
          alert("选择不能为空！");
	     	return false;
	   }
	     
	   chooseSubscribes(boxname);
	     return false;
	  
  }
   
 
	    	      	
   function chooseSubscribes(boxname) {
	     var selector = document.getElementsByName(boxname);
	     if (!selector) {
	       return false;
	     }
		 var subscribeDtlIds= "";
		 if (selector.length) {
		   for (i = 0; i < selector.length; i++) {
		     if (selector[i].checked) {
			   subscribeDtlIds += selector[i].value + ",";
			 }
		   }
		 }
		 
		 returnDialog(subscribeDtlIds,false);
	   }
 
 
 </script>
</@htmlPage>