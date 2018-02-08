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
<#--$Id: purchaseBillList.ftl 11328 2008-03-15 09:39:30Z mwei $ -->

<#include "../../../../includes/eam2008.ftl" />

<@htmlPage title="${action.getText('purchaseListReport.title')}">
	<@ww.form namespace="'/tooling/report'" action="'searchSubscribeDetailSupplierReport'" method="'post'">
		<@ww.token name="searchSubscribeDetailSupplierReportToken"/>
		<#include "purchaseBillReportSearcher2.ftl"/>
		
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@buttonBar>
		   <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
          	<#--
          	 <@blurbutton name="excelPrints" class="button" value="${action.getText('pdfSupplierReportPrints')}" 
                          onclick="excelButtonManagers('pdf')" 
                          onblur="onBlurs('excels')"/>
	   		  <div id="excels" style="display:none;position:relative;left:100px;top:0px;width:20px;">
				<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribeSupplierPdf()"/>
				<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeSupplierXls()"/>
	   		 </div>  
	   		 -->
		</@buttonBar>
		
		<@list title="${action.getText('list')}" 
		       includeParameters="supplier.name|month" 
		       fieldMap="like:supplier.name" >
		       
		    <@vcolumn title="${action.getText('view.supplier')}" property="supplierName" sortable="asc" attributes="width='100'">
            	<@alignLeft />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('view.categoryName')}" property="categoryName" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('采购数量')}" property="purNum" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('采购费用')}" property="sumPrice" format="#,###,##0.00" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('月份')}"  property="month" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
		</@list>
	   <#if !first>
        <@buttonBar>
				<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribeSupplierPdf()"/>
				<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeSupplierXls()"/>
        </@buttonBar>
       </#if> 
	</@ww.form>
</@htmlPage>
<script language="JavaScript" type="text/JavaScript">
         
       <#if !first && valueListNoRecords>
         document.forms[0].elements["printPdf"].disabled = true;
         document.forms[0].elements["printXls"].disabled = true;
       </#if>
        //点击批量打印按钮控制层的显示和隐藏
	   function excelButtonManagers(type){
		 var op = getObjByNameRe('excels')
		 if(op.style.display=="none"){
			 getObjByName("excelPrints").value="${action.getText('pdfSupplierReportPrints')}";
			 op.style.display="block";
		 }else if(op.style.display=="block"){
			getObjByName("excelPrints").value="${action.getText('pdfSupplierReportPrints')}";
			op.style.display="none";
		 }
		 window.scrollTo(0,document.body.scrollHeight);	//滚动条到最下面
		
	   }
	   //失去焦点隐藏导航层
	   function onBlurs(op){
		getObjByName(op).style.display="none";
	   }
  
      //获得id
    //  function getCheckId(){
	    //   var purchaseBillIds = document.getElementsByName("purchaseBillIds");
	     //  var length = purchaseBillIds.length;
	      // var ary = new Array();
	      // if(length){
	          //for(i=0;i<length;i++){
	           //  if(purchaseBillIds[i].checked){
	            //    ary.push(purchaseBillIds[i].value);
	            // }
	         // } 
	     //  }
        //  return ary;
      // }
  

      //按月份供应商pdf
       function open_subscribeSupplierPdf(){
	      // var purIds = getCheckId();
	       if(checkInvalidParms()==false){
	          return false;
	       }
	       var url="${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.pdf?supplier="+getObjByName("supplier.name").value+"&month="+getObjByName("month").value;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }
	   
	   
        //按月份供应商xls  
	   function open_subscribeSupplierXls(){
	      // var purIds = getCheckId();
	      if(checkInvalidParms()==false){
	          return false;
	       }
	       var url="${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.xls?supplier="+getObjByName("supplier.name").value+"&month="+getObjByName("month").value;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   } 
	   
	   </script>
 