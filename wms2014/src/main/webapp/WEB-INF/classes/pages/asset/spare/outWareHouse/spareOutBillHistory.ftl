<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareOutHistory.title')}">
 <base target="_self">
<@ww.form name="'spares'" action="'searchSpareOutBillHistory'" method="'post'">
	 <@ww.token name="searchSpareInHistoryToken"/>
	 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
   	 <@inputTable>
	      <tr>
		      <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
		      <@pp.dateRanger label="'${action.getText('spareOutHistory.date')}'" name="'createdTime'" 
			                  value="'${req.getParameter('createdTime_start')?if_exists}|${req.getParameter('createdTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
    </@inputTable> 
	 <@buttonBar>
	 	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareOutHistory.list')}" 
                   includeParameters="id|spare.id|procExecTime_start|procExecTime_end" 
                   fieldMap="like:id,date:procExecTime_start|procExecTime_end" >
           <#-- <@vcolumn title="${action.getText('spareInHistory.spare_inout_bill_id')}" property="bill.billNo" sortable="desc">
              <a href="#" onclick="popupModalDialog('${req.contextPath}/asset/spare/editSpareIn.html?spareInBill.id=#{object.bill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&popupPage='+'T'+'&readOnly=${req.getParameter('readOnly')?if_exists}',850,600);spares.submit();">${object.bill.billNo}</a>
              <@alignLeft/>
            </@vcolumn>-->
             <@vcolumn title="${action.getText('spare.spareNo')}" property="code" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.name')}" property="name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.modelSpecs')}" property="spare.modelSpecs" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.category')}" property="spare.category.value">
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.number')}" property="number" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.unit')}" property="unitPrice" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.allPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.stocksBeforeInOrOut')}" property="stocksBeforeInOrOut" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.stocksAfterInOrOut')}" property="stocksAfterInOrOut" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareOutHistory.date')}" property="createdTime" format="yyyy-MM-dd" >
              <@alignCenter/>
             </@vcolumn>
        </@list>
     </@ww.form>
     <script language="JavaScript" type="text/JavaScript">
        function checkInvalidParms(){
         beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	       if(!queryDate("createdTime_start","createdTime_end",
	           beginDateMsg,null)){
	          return false;
	         }
	         return true;
        }
     </script>
</@htmlPage>