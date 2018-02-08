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
	<@ww.form namespace="'/prophase'" action="'searchPurchaseBillReoprt'" method="'post'">
		<@ww.token name="searchPurchaseBillReoprtToken"/>
		<#include "purchaseBillReportSearcher.ftl"/>
		
		 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@buttonBar>
		   <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
          	
          	 <@blurbutton name="excelPrints" class="button" value="${action.getText('pdfSupplierReportPrints')}" 
                          onclick="excelButtonManagers('pdf')" 
                          onblur="onBlurs('excels')"/>
	   		  <div id="excels" style="display:none;position:relative;left:100px;top:0px;width:20px;">
				<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribeSupplierPdf()"/>
				<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeSupplierXls()"/>
	   		 </div>  
	   		 
		</@buttonBar>
		
		<@list title="${action.getText('list')}" 
		       includeParameters="supplier.name|purchaseDate_start|purchaseDate_end" 
		       fieldMap="like:supplier.name" >
			 <#if (object.disabled)>
			 
			   <@vlh.checkbox property="id" name="purchaseBillIds">
                 <@vlh.attribute name="width" value="30" />
               </@vlh.checkbox>
              
               <@vcolumn title="${action.getText('purchaseBill.billNo')}" property="billNo" sortable="desc">
            	 ${object.billNo}
            	<@alignLeft />
               </@vcolumn>
            <#else>
               <@vlh.checkbox property="id" name="purchaseBillIds">
                   <@vlh.attribute name="width" value="30" />
              </@vlh.checkbox>
              <@vcolumn title="${action.getText('purchaseBill.billNo')}" property="billNo" sortable="desc">
            	  <a href="editPurchaseBill.html?purchaseBill.id=#{object.id}&toolingDevFlag=${toolingDevFlag?if_exists}&readOnly=true&report=report">${object.billNo}</a>
            	  <@alignLeft />
              </@vcolumn>
            </#if>
            
            <@vcolumn title="${action.getText('purchaseBill.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
           
            <@vcolumn title="${action.getText('buyer')}" property="buyer.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('purchaseBill.purchaseDate')}" property="purchaseDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter/>
            </@vcolumn>
            <@vcolumn title="${action.getText('supplier')}" property="supplier.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <#assign purtypeStatus = ''/>
            <#if object.typeStatus?exists>
       			<#if '${object.typeStatus}' == 'DEVICE'>
       			<#assign purtypeStatus = "${action.getText('DEVICE')}"/>
       			<#elseif '${object.typeStatus}' == 'SPARE'>
       			<#assign purtypeStatus = "${action.getText('SPARE')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('purchase.typeStatus')}" >
            	${purtypeStatus}
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('税前总价(元)')}" property="taxTotalPrice" format="#,###,##0.00">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('税后总价(元)')}" property="totalPrice" format="#,###,##0.00">
            	<@alignRight/>
            </@vcolumn>
            
            <@vcolumn title="明细数量" property="sumDetail">
            	<@alignRight/>
            </@vcolumn>
            
            <#assign purchaseStatus = ''/>
            <#if '${object.status}' == 'NEWSTATUS'>
              <#assign purchaseStatus = "${action.getText('NEWSTATUS')}"/>
            <#elseif '${object.status}' == 'INSPECTING'>
              <#assign purchaseStatus = "${action.getText('INSPECTING')}"/>
            <#else>
              <#assign purchaseStatus = "${action.getText('INSPECTED')}"/>
            </#if>
             <@vcolumn title="${action.getText('purchase.state')}" attributes="width='50'">
               ${purchaseStatus}  
                 <@alignLeft/>
            </@vcolumn>
		</@list>
		
        <@buttonBar>
         <#if !first>
           <#if !(action.isReadOnly())>
           
               <@eam2008_disabledOrEnabled_button message="${action.getText('subscribesBill')}" boxName="purchaseBillIds" jsFunctionName="checkInvalidParms()"/>
             <#--  
                 <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('purchaseBill')}?" />
                <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                   <@ww.param name="'onclick'" value="'return confirmDeletes(\"purchaseBillIds\", \"${confirmMessage}\")'"/>
                   <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
               </@vsubmit> 
            
              -->
             
             </#if>
            </#if>
       </@buttonBar>
        <#if first>
        </#if>
	</@ww.form>
</@htmlPage>
<script language="JavaScript" type="text/JavaScript">
         
         
        //点击批量打印按钮控制层的显示和隐藏
	   function excelButtonManagers(type){
		 var op = document.getElementById('excels')
		 if(op.style.display=="none"){
			 $("excelPrints").value="${action.getText('pdfSupplierReportPrints')}";
			 op.style.display="block";
		 }else if(op.style.display=="block"){
			$("excelPrints").value="${action.getText('pdfSupplierReportPrints')}";
			op.style.display="none";
		 }
		 window.scrollTo(0,document.body.scrollHeight);	//滚动条到最下面
		
	   }
	   //失去焦点隐藏导航层
	   function onBlurs(op){
		$(op).style.display="none";
	   }
  
      //获得id
      function getCheckId(){
	       var purchaseBillIds = document.getElementsByName("purchaseBillIds");
	       var length = purchaseBillIds.length;
	       var ary = new Array();
	       if(length){
	          for(i=0;i<length;i++){
	             if(purchaseBillIds[i].checked){
	                ary.push(purchaseBillIds[i].value);
	             }
	          } 
	       }
          return ary;
       }
  

      //按月份供应商pdf
       function open_subscribeSupplierPdf(){
	       var purIds = getCheckId();
	       if(checkInvalidParms()==false){
	          return false;
	       }
	       var url="${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.pdf?supplier="+$("supplier.name").value+"&date_start="+$("purchaseDate_start").value+"&date_end="+$("purchaseDate_end").value+"&purIds="+purIds;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }
	   
	   
        //按月份供应商xls  
	   function open_subscribeSupplierXls(){
	       var purIds = getCheckId();
	      if(checkInvalidParms()==false){
	          return false;
	       }
	       var url="${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.xls?supplier="+$("supplier.name").value+"&date_start="+$("purchaseDate_start").value+"&date_end="+$("purchaseDate_end").value+"&purIds="+purIds;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   } 
	   
	   </script>
 