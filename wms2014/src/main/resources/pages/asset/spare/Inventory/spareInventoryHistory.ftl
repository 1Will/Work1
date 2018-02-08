<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInventoryHistory.title')}">
 <base target="_self">
<@ww.form name="'spares'" action="'searchSpareInventoryBillHistory'" method="'post'">
	 <@ww.token name="searchSpareInHistoryToken"/>
   	 <@inputTable>
	      <tr>
		      <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
		      <@pp.dateRanger label="'${action.getText('spareInventoryHistory.date')}'" name="'InventoryTime'" 
			                  value="'${req.getParameter('InventoryTime_start')?if_exists}|${req.getParameter('InventoryTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
    </@inputTable> 
	 <@buttonBar>
	 	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareInventoryHistory.list')}" 
                   includeParameters="id|spare.id|InventoryTime_start|InventoryTime_end" 
                   fieldMap="like:id,date:InventoryTime_start|InventoryTime_end" >
           <#-- <@vcolumn title="${action.getText('spareInHistory.spare_inout_bill_id')}" property="bill.billNo" sortable="desc">
              <a href="#" onclick="popupModalDialog('${req.contextPath}/asset/spare/editSpareIn.html?spareInBill.id=#{object.bill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&popupPage='+'T'+'&readOnly=${req.getParameter('readOnly')?if_exists}',850,600);spares.submit();">${object.bill.billNo}</a>
              <@alignLeft/>
            </@vcolumn>-->
             <@vcolumn title="${action.getText('spareInventory.No')}" property="inventory.inventoryNo" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.spareNo')}" property="code" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.name')}" property="name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.modelSpecs')}" property="model" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.specification')}" property="specification" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.category')}" property="category">
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.unit')}" property="unit">
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.unitPrice')}" property="unitPrice" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.actualNumber')}" property="actualNumber" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.inventoryNum')}" property="inventoryNum" >
              <@alignRight/>
             </@vcolumn>
            <#assign differNum=""/>
            <#assign actualtNum=""/>
            <#assign invNum=""/>
            <#if object.inventoryNum?exists>
                <#assign invNum="${object.inventoryNum}"/>
            </#if>
             <#if object.actualNumber?exists>
                <#assign actualtNum="${object.actualNumber}"/>
            </#if>
            <#if object.inventoryNum?exists&&object.actualNumber?exists>
               <#assign differNum=(object.inventoryNum)-(object.actualNumber)/>
            </#if>
            <@vcolumn title="${action.getText('difference')}" property="">
            ${differNum}
            	<@alignRight />
            </@vcolumn>
             <@vcolumn title="${action.getText('spareInventory.inventoryDateTm')}" property="inventory.inventoryDateTm" format="yyyy-MM-dd">
              <@alignCenter/>
             </@vcolumn>
        </@list>
     </@ww.form>
     <script language="JavaScript" type="text/JavaScript">
        function checkInvalidParms(){
         beginDateMsg="${action.getText('spareInventory.TimeMistake')}";
	       if(!queryDate("InventoryTime_start","InventoryTime_end",
	           beginDateMsg,null)){
	          return false;
	         }
	         return true;
        }
     </script>
</@htmlPage>