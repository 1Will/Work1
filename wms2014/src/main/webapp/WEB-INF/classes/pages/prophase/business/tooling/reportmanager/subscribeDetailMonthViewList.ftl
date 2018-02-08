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
<#--$Id: subscribeList.ftl 11279 2008-03-12 01:12:13Z mwei $ -->
<#include "../../../../includes/macros.ftl" />
<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('subscribeList.title')}">
	<@ww.form namespace="'/tooling/report'" name="'searchSubscribeDetailMonthReport'" action="'searchSubscribeDetailMonthReport'" method="'post'">
		<@ww.token name="searchSubscribeDetailMonthReportToken"/>
		<#include "subscribeDetailMonthViewSearcher.ftl"/>
		 
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#--
			<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_subscribePdf()"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_subscribeXls()"/>-->

		 <#--
		    <@blurbutton name="excelPrint" class="button" value="${action.getText('pdfDeptReportPrints')}" 
	                          onclick="excelButtonManager('pdf')" onblur="onBlur('excel')"/>
		   		  <div id="excel" style="display:none;position:relative;left:50px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribePdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeXls()"/>
		   		 </div> 
		   		 -->
		 <#--	  
		   	 <@blurbutton name="excelPrints" class="button" value="${action.getText('pdfSupplierReportPrints')}" 
	                          onclick="excelButtonManagers('pdf')" 
	                          onblur="onBlurs('excels')"/>
		   		  <div id="excels" style="display:none;position:relative;left:200px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribeSupplierPdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeSupplierXls()"/>
		   		 </div>  -->
		  	
		</@buttonBar>
	 
		
		<@list title="${action.getText('subscribeList')}" 
		       includeParameters="department.id|month" 
		       fieldMap="like:">
		   <#--    
            <@vlh.checkbox property="id" name="viewIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>-->
              <@vcolumn title="${action.getText('view.department')}" property="dept" sortable="desc" attributes="width='80'">
            	<@alignLeft />
            </@vcolumn>
            
            <@vcolumn title="${action.getText('view.categoryName')}" property="categoryName" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
            
             <@vcolumn title="${action.getText('view.planNum')}" property="planNum" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('view.purNum')}" property="purNum" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('view.calNum')}" property="calNum" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('view.conNum')}" property="conNum" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('view.sumPrice')}" property="sumPrice" format="#,###,##0.00" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('view.month')}" property="month" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="${action.getText('view.comment')}" property="comment" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            
            
		</@list>
	   <#if !first>
        <@buttonBar>
			<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribePdf()"/>
			<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeXls()"/>
        </@buttonBar>
       </#if> 
	</@ww.form>
<script language="JavaScript" type="text/JavaScript">
       <#if !first && valueListNoRecords>
         document.forms[0].elements["printPdf"].disabled = true;
         document.forms[0].elements["printXls"].disabled = true;
       </#if>
        <#--自定义一个trim函数--> 
         String.prototype.Trim = function() {
             return this.replace(/(^\s*)|(\s*$)/g, "");
         }
	 
	   //点击批量打印按钮控制层的显示和隐藏
	   function excelButtonManager(type){
		  var op = document.getElementById('excel')
		  if(op.style.display=="none"){
			 $("excelPrint").value="${action.getText('pdfDeptReportPrints')}";
			 op.style.display="block";
		  }else if(op.style.display=="block"){
			 $("excelPrint").value="${action.getText('pdfDeptReportPrints')}";
			 op.style.display="none";
		 }
		 //window.scrollTo(0,document.body.scrollHeight);	//滚动条到最下面
		
	   }
	
	   //失去焦点隐藏导航层
	   function onBlur(op){
		 $(op).style.display="none";
	   }
	    
	    
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
 
       function getCheckId(){
	       var subscribeIds = document.getElementsByName("subscribeIds");
	       var length = subscribeIds.length;
	       var ary = new Array();
	       if(length){
	          for(i=0;i<length;i++){
	             if(subscribeIds[i].checked){
	                ary.push(subscribeIds[i].value);
	             }
	          } 
	       }
          return ary;
       }
       
 
       //按月份部门pdf
       function open_subscribePdf(){
	       var subIds = getCheckId();
	       var dept = $("department.id").value;
	       if(checkInvalidParms()==false){
	          return false;
	       }
	       if(-1==dept){
	          dept=""
	       }
	       
	       var url='${req.contextPath}/reports/subscribe/subscribeDetailMonthView.pdf?month='+$("month").value+"&department.id="+dept+"&subIds="+subIds;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }	
	   //按月份部门xls  
	   function open_subscribeXls(){
	       var subIds = getCheckId();
	       var dept = $("department.id").value;
	        if(checkInvalidParms()==false){
	          return false;
	        }
	        if(-1==dept){
	          dept=""
	       }
	       var url='${req.contextPath}/reports/subscribe/subscribeDetailMonthView.xls?month='+$("month").value+"&department.id="+dept+"&subIds="+subIds; 
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }   
       
       
       //按月份供应商pdf
       function open_subscribeSupplierPdf(){
	       var subscribeDate = $("subscribeDate").value;
	       if(""==subscribeDate ||!isYearMonth(subscribeDate)){
	          alert("请输入正确的时间格式，正确格式2011-01");
	          $("subscribeDate").focus();
	          return false;
	       } 
	       var url='${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.pdf?supplier=supplier&subscribeDate='+subscribeDate;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }
        //按月份供应商xls  
	   function open_subscribeSupplierXls(){
	       var subscribeDate = $("subscribeDate").value;
	       if(""==subscribeDate ||!isYearMonth(subscribeDate)){
	          alert("请输入正确的时间格式，正确格式2011-01");
	          $("subscribeDate").focus();
	          return false;
	       } 
	       var url='${req.contextPath}/reports/subscribe/subscribeDtlMonthSupplierView.xls?supplier=supplier&subscribeDate='+subscribeDate;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   } 
     
       
    </script>

</@htmlPage>
