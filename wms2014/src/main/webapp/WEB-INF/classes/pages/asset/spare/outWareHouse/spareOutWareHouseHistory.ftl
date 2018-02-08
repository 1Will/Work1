<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareOutWareHouseHistory.title')}">
 <base target="_self">
<@ww.form name="'spares'" action="'searchSpareOutWareHouseHistory'" method="'post'">
	 <@ww.token name="searchSpareOutWareHouseHistoryToken"/>
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
		      <@pp.dateRanger label="'${action.getText('spareOutWareHouseTime')}'" name="'spareOutWareHouseTime'" 
			                  value="'${req.getParameter('spareOutWareHouseTime_start')?if_exists}|${req.getParameter('spareOutWareHouseTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
    </@inputTable> 
	 <@buttonBar>
	 	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return validateExcuteTime();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareOutWareHouseHistory.list')}" 
                   includeParameters="id|spare.id|spareOutWareHouseTime_start|spareOutWareHouseTime_end|warehouse.id" 
                   fieldMap="like:id,date:spareOutWareHouseTime_start|spareOutWareHouseTime_end" >
             <@vcolumn title="${action.getText('spareOutBill.No')}" property="spareOutBill.code" >
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
             <@vcolumn title="${action.getText('spare.unit.value')}" property="spare.unit.value" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBIllDetail.number')}" property="number" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBIllDetail.unitPrice')}" property="unitPrice" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBIllDetail.totalPrice')}" property="totalPrice" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="出仓库" property="location.warehouse.name" >
                  <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="库区" property="location.regional.name" >
                  <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="库位号" property="location.code" >
                  <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBIllDetail.stocksBeforeInOrOut')}" property="stocksBeforeInOrOut" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBIllDetail.stocksAfterInOrOut')}" property="stocksAfterInOrOut" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutBill.outDate')}" property="spareOutBill.outDate" format="yyyy-MM-dd">
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
          if(document.forms[0].elements["spareOutWareHouseTime_start"].value!=""){
              if(!validateTime(document.forms[0].elements["spareOutWareHouseTime_start"].value)){
                   alert("${action.getText('spareOutWareHouse.start_EndTimeMistake')}");
                   return false;
              }
          }
          if(document.forms[0].elements["spareOutWareHouseTime_end"].value!=""){
             if(!validateTime(document.forms[0].elements["spareOutWareHouseTime_end"].value)){
                   alert("${action.getText('spareOutWareHouse.start_EndTimeMistake')}");
                   return false;
              }
          }
          if ($("warehouse.id").value==-1){
              $("warehouse.id").value='';
           }
          return true;
     }
	
     </script>