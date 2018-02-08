<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInventoryHistory.title')}">
 <base target="_self">
	 <@ww.form name="'spares'" action="'searchSpareInventory'" method="'post'">
	   <@ww.token name="searchSpareInventoryToken"/>
	    <#include "inventoryDeviceSearcher.ftl"/>
	    <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
	    <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	    <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	    <@inputTable>
	      <tr>
		      <@pp.dateRanger label="'${action.getText('spareInventoryHistory.date')}'" name="'inventory.inventoryDateTm'" 
			                  value="'${req.getParameter('inventory.inventoryDateTm_start')?if_exists}|${req.getParameter('inventory.inventoryDateTm_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
        </@inputTable> 
	 <@buttonBar>        
        	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return validateInventoryTime();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareInventoryHistory.list')}" 
                   includeParameters="id|spare.id|inventory.inventoryDateTm_start|inventory.inventoryDateTm_end" 
                   fieldMap="like:id,date:inventory.inventoryDateTm_start|inventory.inventoryDateTm_end" >
            <@vcolumn title="${action.getText('spareInventoryHistory.inventoryNo')}" property="inventory.inventoryNo" sortable="desc">
             <a href="#" onclick="popupModalDialog('${req.contextPath}/asset/spare/editSpareInventory.html?spareInventory.id=#{object.inventory.id}&toolingDevFlag=${toolingDevFlag?if_exists}&popupPage='+'T'+'&readOnly=${req.getParameter('readOnly')?if_exists}',850,600);spares.submit();">${object.inventory.inventoryNo?if_exists}
             </a>
            </@vcolumn>
            <@vcolumn title="${action.getText('spareInventoryHistory.spareNo')}" property="spare.spareNo" />
            <@vcolumn title="${action.getText('spareInventoryHistory.chineseName')}" property="spare.name" />
            <@vcolumn title="${action.getText('spareInventoryHistory.englishName')}" property="spare.enName" />
            <@vcolumn title="${action.getText('spareInventoryHistory.modelSpecs')}" property="spare.modelSpecs" />
            <@vcolumn title="${action.getText('spareInventoryHistory.category')}" property="spare.category.value" />
            <@vcolumn title="${action.getText('spareInventoryHistory.inventoryNum')}" property="inventoryNum" />
            <@vcolumn title="${action.getText('spareInventoryHistory.currentSysStocks')}" property="currentSysStocks" />
            <@vcolumn title="${action.getText('spareInventoryHistory.unit')}" property="spare.unit.value" />
            <@vcolumn title="${action.getText('spareInventoryHistory.inventoryPeople')}" property="inventory.inventoryPeople" />
            <@vcolumn title="${action.getText('spareInventoryHistory.comment')}" property="comment" />
            <@vcolumn title="${action.getText('department')}" property="spare.department.name" />
            <@vcolumn title="${action.getText('spareInventoryHistory.inventoryDateTm')}" property="inventory.inventoryDateTm" format="yyyy-MM-dd" />
        </@list>      
     </@ww.form>
     <script>
     
       function validateInventoryTime(){
          if(document.forms[0].elements["inventory.inventoryDateTm_start"].value!=""){
              if(!isDate("inventory.inventoryDateTm_start")){
                   alert("${action.getText('inventory.inventoryDateTm_start_Mistake')}");
                   return false;
              }
          }
          if(document.forms[0].elements["inventory.inventoryDateTm_end"].value!=""){
             if(!isDate("inventory.inventoryDateTm_end")){
                   alert("${action.getText('inventory.inventoryDateTm_end_Mistake')}");
                   return false;
              }
          }
          return true;
     }
     
     </script>
</@htmlPage>