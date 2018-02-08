<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('月报表查询')}">
	 <@ww.form name="'listFrom'" namespace="'/inAndOutAndStoreReport'" action="'searchspareInAndOutAndStoreMonthReport'" method="'post'">
		<@ww.token name="spareInAndOutAndStoreMonthReportToken"/>
	 	<#include "spareInAndOutAndStoreMonthReportSeacher.ftl"/>
	 	<@ww.hidden name="'flag'" value=""/>
	 	<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        <@list title="${action.getText('spareInAndOutAndStoreMonthReport.list')}" 
                   includeParameters="spareNo|spareName|modelSpecs|flag|month|onlyCheck|custos|warehouse|regional|locationCode" 
                   fieldMap="like:spareNo|spareName|modelSpecs|locationCode">
        <@vcolumn title="${action.getText('spareNo')}" property="spareNo" sortable="desc">
           <@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('spareName')}" property="spareName" sortable="desc">
          <@alignLeft />
        </@vcolumn>
          <@vcolumn title="${action.getText('modelSpecs')}" property="modelSpecs" sortable="desc">
          <@alignLeft />
        </@vcolumn>
        
        <#-- 仓库，库区，库位 -->
        <@vcolumn title="${action.getText('warehouse')}" property="warehouseName" sortable="desc">
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('regional')}" property="regionalName" sortable="desc" >
        	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('locationCode')}" property="locationCode" sortable="desc" >
        	<@alignRight />
        </@vcolumn>
        
         <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" sortable="desc" format="${action.getText('currencyFormat')}">
        	<@alignRight />
        </@vcolumn>
		<@vcolumn title="${action.getText('beforeStocks')}" property="beforeStocks" sortable="desc">
         	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('inStocks')}" property="inStocks" sortable="desc">
         	<@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('outStocks')}" property="outStocks" sortable="desc">
            <@alignRight />
        </@vcolumn>
        <@vcolumn title="${action.getText('afterStocks')}" property="afterStocks" sortable="desc">
            <@alignRight />
        </@vcolumn>
          <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" sortable="desc" format="${action.getText('currencyFormat')}">
        	<@alignRight />
        </@vcolumn>
         <#--保管人-->
        <@vcolumn title="${action.getText('custos')}"  property="spareCustos.name" sortable="asc">
        	<@alignLeft/>
        </@vcolumn>
         <@vcolumn title="${action.getText('month')}" property="month" sortable="desc" attributes="width='50'" format="yyyy-MM">
         <@alignCenter />
         </@vcolumn>
       <@vcolumn title="${action.getText('toolingDevFlag')}" property="toolingDevFlag" sortable="desc">
         <@alignCenter />
         </@vcolumn> 
       <@vcolumn title="${action.getText('createTime')}" property="createTime" sortable="desc" format="yyyy-MM-dd">
         <@alignCenter />
         </@vcolumn>
       </@list>
       <#if !first>
       <@buttonBar>
            <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_spareInOutAndStoreMonthPdf()"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_spareInOutAndStoreMonthXls()"/>
       </@buttonBar>
      </#if>
	 </@ww.form>
</@htmlPage>
  <script language="JavaScript" type="text/JavaScript">
	var now = new Date();//当前时间 
	var month = now.format('yyyy-MM');//当前月份
	document.forms[0].elements["month"].value=month;
	<#if req.getParameter('month')?exists>
		document.forms[0].elements["month"].value="${req.getParameter('month')?if_exists}";
	</#if>
    function open_spareInOutAndStoreMonthPdf(){
     if(checkInvalidParms()){
     var spareNo = document.forms[0].elements["spareNo"].value;
     var spareName = document.forms[0].elements["spareName"].value;
     var modelSpecs = document.forms[0].elements["modelSpecs"].value;
     if (document.forms[0].elements["warehouse"].value != '-1') {
         var warehouse = document.forms[0].elements["warehouse"].value;
     } else {
         var warehouse = "";
     }
     if (document.forms[0].elements["regional"].value != '-1') {
         var regional = document.forms[0].elements["regional"].value;
     } else {
         var regional = "";
     }
     var locationCode = document.forms[0].elements["locationCode"].value;
     var month = document.forms[0].elements["month"].value;
     var toolingDevFlag = document.forms[0].elements["toolDevFlag"].value;
     var onlyCheck = document.forms[0].elements["onlyCheck"].value;
     var url='${req.contextPath}/reports/spareInAndOutReport/spareStoreMonthReport2.pdf?spareNo='+spareNo+
	       	  '&spareName='+spareName + '&modelSpecs=' + modelSpecs + '&warehouse=' + warehouse + '&regional=' + regional + 
	       	  '&locationCode=' + locationCode + '&month='+ month +
        	  '&toolingDevFlag='+ toolingDevFlag + '&onlyCheck='+onlyCheck;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     }
    function open_spareInOutAndStoreMonthXls(){
     if(checkInvalidParms()){
     var spareNo = document.forms[0].elements["spareNo"].value;
     var spareName = document.forms[0].elements["spareName"].value;
     var modelSpecs = document.forms[0].elements["modelSpecs"].value;
     if (document.forms[0].elements["warehouse"].value != '-1') {
         var warehouse = document.forms[0].elements["warehouse"].value;
     } else {
         var warehouse = "";
     }
     if (document.forms[0].elements["regional"].value != '-1') {
         var regional = document.forms[0].elements["regional"].value;
     } else {
         var regional = "";
     }
     var locationCode = document.forms[0].elements["locationCode"].value;
     var month = document.forms[0].elements["month"].value;
     var toolingDevFlag = document.forms[0].elements["toolDevFlag"].value;
     var onlyCheck = document.forms[0].elements["onlyCheck"].value;
     var url='${req.contextPath}/reports/spareInAndOutReport/spareStoreMonthReport2.xls?spareNo='+spareNo+
	       	  '&spareName='+spareName + '&modelSpecs=' + modelSpecs + '&warehouse=' + warehouse + '&regional=' + regional + 
	       	  '&locationCode=' + locationCode + '&month='+ month +
        	  '&toolingDevFlag='+ toolingDevFlag + '&onlyCheck='+onlyCheck;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
    }
     }
  </script>