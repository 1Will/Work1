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
	<@ww.form namespace="'/tooling/report'" name="'searchSubscribeDetailSupplierReport'" action="'searchSubscribeDetailSupplierReport'" method="'post'">
		<@ww.token name="searchSubscribeDetailSupplierReportToken"/>
		<#include "subscribeSupplierReportSearcher.ftl"/>
		 
		<@buttonBar>
			<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
			<#--
			<@vbutton name="print"  class="button" value="${action.getText('pdfPrint')}" onclick="open_subscribePdf()"/>
		    <@vbutton name="print"  class="button" value="${action.getText('xlsPrint')}" onclick="open_subscribeXls()"/>-->
	
		 
		    <@blurbutton name="excelPrint" class="button" value="${action.getText('pdfDeptReportPrints')}" 
	                          onclick="excelButtonManager('pdf')" onblur="onBlur('excel')"/>
		   		  <div id="excel" style="display:none;position:relative;left:50px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribePdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeXls()"/>
		   		 </div> 
		 	  
		   	 <@blurbutton name="excelPrints" class="button" value="${action.getText('pdfSupplierReportPrints')}" 
	                          onclick="excelButtonManagers('pdf')" 
	                          onblur="onBlurs('excels')"/>
		   		  <div id="excels" style="display:none;position:relative;left:200px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_subscribeSupplierPdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_subscribeSupplierXls()"/>
		   		 </div>  
		  	
		</@buttonBar>
	 
		
		<@list title="${action.getText('subscribeList')}" 
		       includeParameters="subscribeDate_start|subscribeDate_end|onlyInvalid|onlyValid" 
		       fieldMap="like:">
		       
            <#if (object.disabled)>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="billNo" sortable="desc">
            	${object.billNo}
            	<@alignLeft />
            </@vcolumn>
            <#else>
            <@vlh.checkbox property="id" name="subscribeIds">
                <@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
        	<@ww.hidden name="'isStatus'" value="'${object.status?string}'"/>
            <@vcolumn title="${action.getText('subscribe.billNo')}" property="billNo" sortable="desc">
            	<a href="editSubscribe.html?toolingDevFlag=${toolingDevFlag?if_exists}&subscribe.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&eamAuthentication=${req.getParameter('eamAuthentication')?if_exists}">${object.billNo}</a>
            	<@alignLeft />
            </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('subscribe.name')}" property="name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribe.department')}" property="department.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscriber')}" property="buyingPerson.name" sortable="asc">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('subscribe.subscribeDate')}" property="subscribeDate" format="yyyy-MM-dd" sortable="asc">
            	<@alignCenter />
            </@vcolumn>
            <#assign subtypeStatus = ''/>
            <#if object.typeStatus?exists>
       			<#if '${object.typeStatus}' == 'DEVICE'>
       			<#assign subtypeStatus = "${action.getText('DEVICE')}"/>
       			<#elseif '${object.typeStatus}' == 'SPARE'>
       			<#assign subtypeStatus = "${action.getText('SPARE')}"/>
       			</#if>
       		</#if>
            <@vcolumn title="${action.getText('subscribes.typeStatus')}" sortable="desc">
            	${subtypeStatus}
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}" sortable="desc">
            	<@alignRight/>
            </@vcolumn>
            
           <@vcolumn title="单据类型" property="detailKind.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="${action.getText('reason')}" property="reason" >
            	<@alignLeft />
            </@vcolumn>
            <#--
             <@vcolumn title="明细数量" property="sumDetail" >
            	<@alignLeft />
            </@vcolumn>-->
             <@vcolumn title="申购项" property="sumDetail" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="采购项" property="purNum" >
            	<@alignLeft />
            </@vcolumn>
             <@vcolumn title="入库项" property="insNum" >
            	<@alignLeft />
            </@vcolumn>
            <#assign subscribeStatus = ''/>
       		<#if object.status?exists>
       			<#if '${object.status}' == 'NEWPURCHASE'>
             		<#assign subscribeStatus = "${action.getText('新建')}"/>
             	<#elseif '${object.status}' == 'SUMMARYED'>
             		<#assign subscribeStatus = "${action.getText('已汇总')}"/>
             	<#elseif '${object.status}' == 'PURCHASING'>
             		<#assign subscribeStatus = "${action.getText('采购中')}"/>
             	<#elseif '${object.status}' == 'PURCHASEED'>
             		<#assign subscribeStatus = "${action.getText('已采购')}"/>
             	<#elseif '${object.status}' == 'STORAGING'>
             		<#assign subscribeStatus = "${action.getText('入库中/验收中')}"/>
             	<#elseif '${object.status}' == 'STORAGED'>
             		<#assign subscribeStatus = "${action.getText('已入库/已验收')}"/>
             	</#if>
       		</#if>
            <@vcolumn title="${action.getText('status')}" attributes="width='50'">
            	${subscribeStatus}
            	<@alignLeft />
            </@vcolumn>
		</@list>
		<#if !first>
        <@buttonBar>
             <#if !(action.isReadOnly())>
              <#if !(action.isOnlyInvalid())>
               <#if '${eamAuthentication?if_exists}' == 'Collect'>
              <@vbutton value="${action.getText('summary')}" onclick="summary()" class="button"/>
              </#if>
              </#if>
             </#if>
        </@buttonBar>
        </#if>
	</@ww.form>
<script language="JavaScript" type="text/JavaScript">
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
 
       
 
       //按月份部门pdf
       function open_subscribePdf(){
	       checkInvalidParms();
	       var url='${req.contextPath}/reports/subscribe/subscribeDetailMonthView.pdf?date_start='+$("subscribeDate_start").value+"&date_end="+$("subscribeDate_end").value+"&department.id="+$("department.id").value;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }	
	   //按月份部门xls  
	   function open_subscribeXls(){
	       checkInvalidParms();
	       var url='${req.contextPath}/reports/subscribe/subscribeDetailMonthView.xls?date_start='+$("subscribeDate_start").value+"&date_end="+$("subscribeDate_end").value+"&department.id="+$("department.id").value;  
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
