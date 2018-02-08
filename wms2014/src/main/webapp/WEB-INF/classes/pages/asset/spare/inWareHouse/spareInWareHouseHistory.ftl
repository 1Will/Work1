<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInWareHouseHistory.title')}">
 <base target="_self">
<@ww.form name="'spares'" action="'searchSpareInWareHouseHistory'" method="'post'">
	 <@ww.token name="searchSpareInWareHouseHistoryToken"/>
   	 <@inputTable>
	      <tr>
		      <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/> 
		      <@ww.select 
				label="'仓库'"
        		name="'warehouse.id'"
       	 		listKey="id" 
        		listValue="name"
        		value="'${req.getParameter('warehouse.id')?if_exists}'"
        		list="allWarehouse" 
        		emptyOption="false" 
        		disabled="false"
        		>
        		</@ww.select>  
		      <@pp.dateRanger label="'${action.getText('spareInWareHouseTime')}'" name="'spareInWareHouseTime'" 
			                  value="'${req.getParameter('spareInWareHouseTime_start')?if_exists}|${req.getParameter('spareInWareHouseTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
    </@inputTable> 
	 <@buttonBar>
	 	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return validateExcuteTime();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareInWareHouseHistory.list')}" 
                   includeParameters="id|spare.id|spareInWareHouseTime_start|spareInWareHouseTime_end|warehouse.id" 
                   fieldMap="like:id,date:spareInWareHouseTime_start|spareInWareHouseTime_end" >
             <@vcolumn title="${action.getText('spareInBill.No')}" property="spareInBill.code" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.spareNo')}" property="spare.spareNo" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.name')}" property="spare.name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.modelSpecs')}" property="spare.modelSpecs" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.specification')}" property="spare.specification" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.category.value')}" property="spare.category.name">
              <@alignLeft/>
             </@vcolumn>
             <#--
             <@vcolumn title="${action.getText('spare.spareDetailType.value')}" property="spare.spareDetailType.name">
              <@alignLeft/>
             </@vcolumn>
             -->
             <@vcolumn title="${action.getText('spare.unit.value')}" property="spare.unit.value" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBIllDetail.number')}" property="number" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBIllDetail.unitPrice')}" property="taxPrice" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBIllDetail.totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="入仓库" property="location.warehouse.name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="库位号" property="location.code" >
               <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBIllDetail.stocksBeforeInOrOut')}" property="stocksBeforeInOrOut" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBIllDetail.stocksAfterInOrOut')}" property="stocksAfterInOrOut" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInBill.inDate')}" property="spareInBill.inDate" format="yyyy-MM-dd">
              <@alignCenter/>
             </@vcolumn>
        </@list>
     </@ww.form>
</@htmlPage>
 <script language="JavaScript" type="text/JavaScript">
               //仓库
         var whSelector = document.all("warehouse.id");
         var whGroups = whSelector.options.length;  
         <#if req.getParameter('warehouse.id')?exists>
         for (var i=0; i< whGroups; i++){
            if (whSelector.options[i].value=="${req.getParameter('warehouse.id')?if_exists}"){
               whSelector.options[i].selected="true";
            }
         }
         </#if>
     function validateExcuteTime(){
          if(document.forms[0].elements["spareInWareHouseTime_start"].value!=""){
              if(!validateTime(document.forms[0].elements["spareInWareHouseTime_start"].value)){
                   alert("${action.getText('spareInWareHouse.start_EndTimeMistake')}");
                   return false;
              }
          }
          if(document.forms[0].elements["spareInWareHouseTime_end"].value!=""){
             if(!validateTime(document.forms[0].elements["spareInWareHouseTime_end"].value)){
                   alert("${action.getText('spareInWareHouse.start_EndTimeMistake')}");
                   return false;
              }
          }
         if ($("warehouse.id").value==-1){
              $("warehouse.id").value='';
           }
          return true;
     }
	
     </script>