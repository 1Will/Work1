<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInAndOutAndStoreReport.title')}">
	 <@ww.form name="'spareInAndOutAndStoreReport'" namespace="'/inAndOutAndStoreReport'" action="'searchspareInAndOutAndStoreReport'" method="'post'">
	   <@ww.token name="spareInAndOutAndStoreReportToken"/>
	    <#include "spareInAndOutAndStoreReportSeacher.ftl"/>
	     <@buttonBar>        
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/> 
        </@buttonBar>
        <@list title="${action.getText('spareInAndOutAndStoreReport.list')}" 
                   includeParameters="spareNo|spareName|modelwarehouse|regional|locationCode|department.id|createTime_start|createTime_end|toolDevFlag|onlyCheck|custos" 
                   fieldMap="like:spareNo|spareName|modelwarehouse|regional|locationCode|custos,date:createTime_start|createTime_end" >
             <@vcolumn title="${action.getText('spareNo')}" property="spareNo" sortable="desc">
               <@alignLeft />
             </@vcolumn>
            <@vcolumn title="${action.getText('spareName')}" property="spareName" sortable="desc">
              <@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('model')}" property="model" sortable="desc">
             <@alignLeft />
             </@vcolumn>
             <@vcolumn title="${action.getText('unit')}" property="unit" sortable="desc">
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
             <@vcolumn title="${action.getText('departmentName')}" property="department.name" sortable="desc">
              <@alignLeft />
            </@vcolumn>
        	 <#--保管人-->
             <@vcolumn title="${action.getText('custos')}"  property="spareCustos.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <#assign toolingDevFlag=''/>
        	<#if object.toolingDevFlag?exists>
		       <#if '${object.toolingDevFlag}' == 'DEVICE'>
		       <#assign toolingDevFlag = "${action.getText('DEVICE')}"/>
		       <#elseif '${object.toolingDevFlag}' == 'TOOLING'>
		       <#assign toolingDevFlag = "${action.getText('TOOLING')}"/>
        	   </#if>
        	</#if>
             <@vcolumn title="${action.getText('toolingDevFlag')}" sortable="desc">
             	${toolingDevFlag}
             <@alignCenter />
             </@vcolumn>
             <@vcolumn title="${action.getText('createTime')}" property="createTime" sortable="desc" format="yyyy-MM-dd">
             <@alignCenter />
             </@vcolumn>
        </@list>  
          <#if !first>
       <@buttonBar>
            <@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_spareInOutAndStorePdf()"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_spareInOutAndStoreXls()"/>
      </@buttonBar>
      </#if> 
     </@ww.form>
</@htmlPage>
  <script language="JavaScript" type="text/JavaScript">
   window.onload=function(){
   <#if req.getParameter('fristLink')?has_content>
   <#if first>
   <#if req.getParameter('fristLink')=='true'>
          var now = new Date();//当前时间 
          var n0=now.getYear();//年 
          var y0=now.getMonth()+1;//月 
          var d0=now.getDate();//日 
          var h0="0"+now.getHours(); //时 
          var m0="0"+now.getMinutes(); //分 
          var s0="0"+now.getSeconds();//秒 
          if(h0>9){h0=now.getHours();} 
          if(m0>9){m0=now.getMinutes();} 
          if(s0>9){s0=now.getSeconds();} 
         getCurrentDayNextDay(document.forms[0].elements["createTime_end"],n0,y0,d0,h0,m0,s0);
         </#if>
         </#if>
         <#if req.getParameter('createTime_end')?has_content>
	      <#else>
		  var now = new Date();//当前时间 
		  now.setDate(now.getDate()-1); 
		  var yesterday; 
		  if((now.getMonth()+1)<10){
		  		if(now.getDate()<10){
				   yesterday = now.getFullYear() +"-0"+ (now.getMonth()+1) +"-0"+ now.getDate();   
		 		}else{
				   yesterday = now.getFullYear() +"-0"+ (now.getMonth()+1) +"-"+ now.getDate();
		  		}
		  }
		else{
		  		if(now.getDate()<10){
					yesterday = now.getFullYear() +"-"+ (now.getMonth()+1) +"-0"+ now.getDate();   
		  		}else{
					yesterday = now.getFullYear() +"-"+ (now.getMonth()+1) +"-"+ now.getDate();
		  		}
		  }
		  
            document.forms[0].elements["createTime_end"].value=yesterday;
        </#if>   
         </#if>
   }
 
    function open_spareInOutAndStorePdf(){
         var spareNo = document.forms[0].elements["spareNo"].value;
         var spareName = document.forms[0].elements["spareName"].value;
         var model = document.forms[0].elements["model"].value;
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
         var departmentId = document.forms[0].elements["department.id"].value;
         var toolingDevFlag = document.forms[0].elements["toolDevFlag"].value;
         var onlyCheck = document.forms[0].elements["onlyCheck"].value;
         var createTime_start = document.forms[0].elements["createTime_start"].value;
         var createTime_end = document.forms[0].elements["createTime_end"].value;
     var url='${req.contextPath}/reports/spareInAndOutReport/SpareOutAndInAndStoreReport.pdf?spareNo='+ spareNo +
	       	  '&spareName='+ spareName + '&model='+ model + '&warehouse=' + warehouse + '&regional=' + regional + 
        	  '&locationCode='+ locationCode + '&department.id='+ departmentId + '&toolingDevFlag='+ toolingDevFlag +
        	  '&createTime_start='+ createTime_start + '&createTime_end='+ createTime_end +
        	  '&onlyCheck='+ onlyCheck;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     function open_spareInOutAndStoreXls(){
         var spareNo = document.forms[0].elements["spareNo"].value;
         var spareName = document.forms[0].elements["spareName"].value;
         var model = document.forms[0].elements["model"].value;
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
         var departmentId = document.forms[0].elements["department.id"].value;
         var toolingDevFlag = document.forms[0].elements["toolDevFlag"].value;
         var onlyCheck = document.forms[0].elements["onlyCheck"].value;
         var createTime_start = document.forms[0].elements["createTime_start"].value;
         var createTime_end = document.forms[0].elements["createTime_end"].value;
         
         var url='${req.contextPath}/reports/spareInAndOutReport/SpareOutAndInAndStoreReport.xls?spareNo='+ spareNo +
	       	  '&spareName='+ spareName + '&model='+ model + '&warehouse=' + warehouse + '&regional=' + regional + 
        	  '&locationCode='+ locationCode + '&department.id='+ departmentId + '&toolingDevFlag='+ toolingDevFlag +
        	  '&createTime_start='+ createTime_start + '&createTime_end='+ createTime_end +
        	  '&onlyCheck='+ onlyCheck;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
  </script>