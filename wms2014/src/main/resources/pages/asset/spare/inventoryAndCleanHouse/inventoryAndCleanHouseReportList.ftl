<#--清仓盘点查询页面-->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('title')}">
	 <@ww.form name="'listFrom'" namespace="'/inAndOutAndStoreReport'" action="'searchInventoryAndCleanHouseReport'" method="'post'">
		<@ww.token name="inventoryAndCleanHouseReportToken"/>
	 	<#include "inventoryAndCleanHouseReportSeacher.ftl"/>
	 	<@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
		<@list title="${action.getText('inventoryAndCleanHouseReport.list')}" 
               includeParameters="spareNo|spareNamewarehouse|regional|locationCode|modelSpecs|month|department.id|flag|onlyCheck|custos" 
               fieldMap="like:spareNo|spareNamewarehouse|regional|locationCode|modelSpecs" >
        <@vcolumn title="${action.getText('spareNo')}" property="spareNo" sortable="desc">
			<@alignLeft />
		</@vcolumn>
        <@vcolumn title="${action.getText('spareName')}" property="spareName" sortable="desc">
        	<@alignLeft />
        </@vcolumn>
        
        <#-- 仓库，库区 -->
        <@vcolumn title="${action.getText('warehouse')}" property="warehouseName" sortable="desc">
            <@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('regional')}" property="regionalName" sortable="desc">
            <@alignLeft />
        </@vcolumn>
        
        <@vcolumn title="${action.getText('locationCode')}" property="locationCode" sortable="desc">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('modelSpecs')}" property="modelSpecs" sortable="desc">
        	<@alignLeft />
        </@vcolumn>
        <@vcolumn title="${action.getText('unit')}" property="unit" sortable="desc">
        	<@alignLeft />
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
		<@vcolumn title="${action.getText('department')}" property="deptName" sortable="desc">
			<@alignLeft />
		</@vcolumn>
		<@vcolumn title="${action.getText('toolingDevFlag')}" property="toolingDevFlag" sortable="desc">
			<@alignCenter />
		</@vcolumn>
		<#--保管员-->
        <@vcolumn title="${action.getText('custos')}"  property="spareCustos.name" sortable="asc">
        	<@alignLeft/>
        </@vcolumn>
		<@vcolumn title="${action.getText('month')}" property="month" attributes="width='50'" sortable="desc">
			<@alignCenter />
		</@vcolumn>	
			<@vcolumn title="${action.getText('createTime')}" property="createTime" sortable="desc" format="yyyy-MM-dd">
			<@alignCenter />
		</@vcolumn>	
		</@list>
		<#if !first>
			<@buttonBar>
				<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_spareInventoryAndCleanHousePdf()"/>
		    	<@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_spareInventoryAndCleanHouseXls()"/>
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
	//打印pdf
    function open_spareInventoryAndCleanHousePdf(){
         if(checkInvalidParms()){
		var url='${req.contextPath}/reports/inventoryAndCleanHouse/cleanHouseReport.pdf?'+
				'spareNo='+document.forms[0].elements["spareNo"].value+
	       	    '&spareName='+(document.forms[0].elements["spareName"].value) +
	       	    '&locationCode='+(document.forms[0].elements["locationCode"].value) +
	       	    '&month='+(document.forms[0].elements["month"].value)+
	       	    '&department='+ (document.forms[0].elements["department.id"].value)+
        	    '&toolingDevFlag='+ (document.forms[0].elements["toolDevFlag"].value)+
        	    '&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
        	    '&onlyCheck='+ (document.forms[0].elements["onlyCheck"].value);
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
    }
    //打印xls
    function open_spareInventoryAndCleanHouseXls(){
         if(checkInvalidParms()){
		var url='${req.contextPath}/reports/inventoryAndCleanHouse/cleanHouseReport.xls?'+
				'spareNo='+document.forms[0].elements["spareNo"].value+
	       	    '&spareName='+(document.forms[0].elements["spareName"].value) +
	       	    '&locationCode='+(document.forms[0].elements["locationCode"].value) +
	       	    '&month='+(document.forms[0].elements["month"].value)+
	       	    '&department='+ (document.forms[0].elements["department.id"].value)+
        	    '&toolingDevFlag='+ (document.forms[0].elements["toolDevFlag"].value)+
        	    '&modelSpecs='+ (document.forms[0].elements["modelSpecs"].value)+
        	    '&onlyCheck='+ (document.forms[0].elements["onlyCheck"].value);
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
    }
</script>